package exercise.hml.processing.demo.services.impl;

import exercise.hml.processing.demo.dto.exports.CustomerExportCarsDto;
import exercise.hml.processing.demo.dto.exports.CustomerExportDto;
import exercise.hml.processing.demo.dto.exports.CustomerExportPartsRootDto;
import exercise.hml.processing.demo.dto.exports.CustomersExportRootDto;
import exercise.hml.processing.demo.dto.imports.CustomerImportDto;
import exercise.hml.processing.demo.dto.imports.CustomerImportRoorDto;
import exercise.hml.processing.demo.entities.Car;
import exercise.hml.processing.demo.entities.Customer;
import exercise.hml.processing.demo.entities.Part;
import exercise.hml.processing.demo.repositories.CarRepository;
import exercise.hml.processing.demo.repositories.CustomerRepository;
import exercise.hml.processing.demo.services.CustomerService;
import exercise.hml.processing.demo.util.ValidatorUtil;
import exercise.hml.processing.demo.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String CUSTOMER_PATH =
            "src/main/resources/xmls/customers.xml";

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;
    private final CarRepository carRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ModelMapper modelMapper, ValidatorUtil validatorUtil, XmlParser xmlParser, CarRepository carRepository) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
        this.carRepository = carRepository;
    }
//    private final PartRepository partRepository;

    @Override
    public void seedCustomers() throws JAXBException {

        CustomerImportRoorDto customerImportRoorDto =
                this.xmlParser.parseXml(CustomerImportRoorDto.class, CUSTOMER_PATH);

        for (CustomerImportDto customerImportDto : customerImportRoorDto.getCustomerImportDtos()) {

            Customer customer = this.modelMapper
                    .map(customerImportDto,Customer.class);

            customer.setCars(getRandomListOfCars());

            System.out.println();

            this.customerRepository.saveAndFlush(customer);

        }

    }

    private Set<Car> getRandomListOfCars() {

        List<Car> cars = this.carRepository.findAll();
        Set<Car> randomList = new HashSet<>();

        Random rnd = new Random();

        int numberOfCars = rnd.nextInt(3);

        for (int i = 0; i < numberOfCars; i++) {

            int index = rnd.nextInt(cars.size());

            if (!randomList.contains(cars.get(index))){
                randomList.add(cars.get(index));
            }

        }
        return randomList;
    }

    @Override
    public void getAllCustomersOrderByBirthDateAndOldestDriver() throws JAXBException {

        Set<Customer> customers =
                this.customerRepository.findAllOrderByBirthDateAndOldestDriver();


        Set<CustomerExportDto> customerExportDtos =
                new LinkedHashSet<>();

        for (Customer customer : customers) {

            CustomerExportDto exportDtoCustomer =
                    this.modelMapper.map(customer, CustomerExportDto.class);
            customerExportDtos.add(exportDtoCustomer);

        }

        CustomersExportRootDto customersExportRootDto =
                new CustomersExportRootDto();

        customersExportRootDto.setCustomerExportDtos(customerExportDtos);

        this.xmlParser.exportHml(customersExportRootDto,CustomersExportRootDto.class,
                "src/main/resources/exports/ordered-customers.xml");

    }

    @Override
    public void getTotalSalesByCustomers() throws JAXBException {

        Set<Customer> allAtLestOneCar =
                this.customerRepository.getAllAtLestOneCar();

        Set<CustomerExportCarsDto> customerExportCarsDtoSet =
                new LinkedHashSet<>();


        for (Customer customer : allAtLestOneCar) {

            CustomerExportCarsDto customerDto = new CustomerExportCarsDto();

            customerDto.setFullName(customer.getName());
            customerDto.setBoughtCars(customer.getCars().size());

            double spentMoney = getSpentMoney(customer.getCars());
            customerDto.setSpentMoney(spentMoney);

            customerExportCarsDtoSet.add(customerDto);

        }


        List<CustomerExportCarsDto> customerExportCarsDtoList
                = customerExportCarsDtoSet.stream()
                .sorted((a,b)->{

                    double result = b.getSpentMoney() - a.getSpentMoney();
                    if (result < 0){
                        return -1;
                    }else if (result == 0){
                        return Integer.compare(b.getBoughtCars(),
                                a.getBoughtCars());
                    }else {
                        return 1;
                    }

                  //  return result;
                }).collect(Collectors.toList());


        CustomerExportPartsRootDto customerExportPartsRootDto =
                new CustomerExportPartsRootDto();


        customerExportPartsRootDto.setSet(customerExportCarsDtoList);

        this.xmlParser.exportHml(customerExportPartsRootDto,
                CustomerExportPartsRootDto.class,
                "src/main/resources/exports/customers-total-sales.xml");

    }

    private double getSpentMoney(Set<Car> cars) {

        double sum = 0;
        for (Car car : cars) {
            for (Part part : car.getParts()) {
                sum += part.getPrice().doubleValue();
            }

        }
        return sum;
    }
}
