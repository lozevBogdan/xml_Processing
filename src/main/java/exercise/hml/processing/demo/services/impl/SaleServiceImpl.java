package exercise.hml.processing.demo.services.impl;

import exercise.hml.processing.demo.dto.exports.CarSaleExportDto;
import exercise.hml.processing.demo.dto.exports.SaleExportSalesDto;
import exercise.hml.processing.demo.dto.exports.SalesExportSalesRootDto;
import exercise.hml.processing.demo.entities.Car;
import exercise.hml.processing.demo.entities.Customer;
import exercise.hml.processing.demo.entities.Part;
import exercise.hml.processing.demo.entities.Sale;
import exercise.hml.processing.demo.repositories.CarRepository;
import exercise.hml.processing.demo.repositories.CustomerRepository;
import exercise.hml.processing.demo.repositories.SaleRepository;
import exercise.hml.processing.demo.services.SaleService;
import exercise.hml.processing.demo.util.ValidatorUtil;
import exercise.hml.processing.demo.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.util.*;

@Service
public class SaleServiceImpl implements SaleService {


    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper,
                           ValidatorUtil validatorUtil, XmlParser xmlParser, CarRepository carRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    //@Transactional
    public void seedSales() {

        for (int i = 0; i < 10; i++) {
            Sale sale = new Sale();
            sale.setDiscount(getRandomDisscount());
            sale.setCar(getRandomCar());
            sale.setCustomer(getRandomCustomer());
            this.saleRepository.saveAndFlush(sale);
        }




    }

    @Override
    public void getSalesWithAppliedDiscount() throws JAXBException {

        List<Sale> allSales = this.saleRepository.findAll();

        Set<SaleExportSalesDto> dtoSet = new HashSet<>();

        for (Sale sale : allSales) {

            SaleExportSalesDto saleExportSalesDto =
                    new SaleExportSalesDto();

            CarSaleExportDto carDto = this.modelMapper.map(sale.getCar(),
                    CarSaleExportDto.class);

            saleExportSalesDto.setCarSaleExportDto(carDto);
            saleExportSalesDto.setCustomerName(sale.getCustomer().getName());
            saleExportSalesDto.setDiscount(sale.getDiscount()/100);
            saleExportSalesDto.setPrice(getPrice(sale.getCar()));
            double priceWithDiscount = saleExportSalesDto.getPrice() -
                    ( saleExportSalesDto.getPrice()*
                    (sale.getDiscount()/100));
            saleExportSalesDto.setPriceWithDiscount(priceWithDiscount);

            dtoSet.add(saleExportSalesDto);

        }

        SalesExportSalesRootDto salesExportSalesRootDto =
                new SalesExportSalesRootDto();
        salesExportSalesRootDto.setDtoSet(dtoSet);

        this.xmlParser.exportHml(salesExportSalesRootDto,
                SalesExportSalesRootDto.class,
                "src/main/resources/exports/sales-discounts.xml");



    }

    private double getPrice(Car car) {

        double sum = 0;

        for (Part part : car.getParts()) {

            sum += part.getPrice().doubleValue();
        }

        return sum;
    }


    private Customer getRandomCustomer() {
        List<Customer> customers = this.customerRepository.findAll();
        Random rnd = new Random();
        int index = rnd.nextInt(customers.size() - 1);

        return customers.get(index);

    }

    private Car getRandomCar() {
        List<Car> cars = this.carRepository.findAll();
        Random rnd = new Random();
        int index = rnd.nextInt(cars.size() - 1);

        return cars.get(index);

    }

    private double getRandomDisscount() {
        List<Integer> discounts =
                List.of(0,5,10,15,20,30,40,50);

        Random rnd = new Random();
        int index = rnd.nextInt(discounts.size() - 1);

        return discounts.get(index);
    }
}
