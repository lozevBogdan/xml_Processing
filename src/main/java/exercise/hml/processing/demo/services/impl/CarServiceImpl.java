package exercise.hml.processing.demo.services.impl;

import exercise.hml.processing.demo.dto.exports.*;
import exercise.hml.processing.demo.dto.imports.CarImportDto;
import exercise.hml.processing.demo.dto.imports.CartImportRootDto;
import exercise.hml.processing.demo.entities.Car;
import exercise.hml.processing.demo.entities.Part;
import exercise.hml.processing.demo.repositories.CarRepository;
import exercise.hml.processing.demo.repositories.PartRepository;
import exercise.hml.processing.demo.services.CarService;
import exercise.hml.processing.demo.util.ValidatorUtil;
import exercise.hml.processing.demo.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.*;

@Service
public class CarServiceImpl implements CarService {

    private static final String CAR_PATH =
            "src/main/resources/xmls/cars.xml";

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;
    private final PartRepository partRepository;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper,
                          ValidatorUtil validatorUtil, XmlParser xmlParser, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
        this.partRepository = partRepository;
    }

    @Override
    public void seedCars() throws JAXBException {

        CartImportRootDto cartImportRootDto =
                this.xmlParser.parseXml(CartImportRootDto.class, CAR_PATH);

        for (CarImportDto carImportDto : cartImportRootDto.getCasDto()) {

            Car car = this.modelMapper.map(carImportDto,Car.class);

            car.setParts(getRandomListOfParts());

            this.carRepository.saveAndFlush(car);

        }

    }

    @Override
    public void getCarsByToyota() throws JAXBException {

        Set<Car> carsByToyota =
                this.carRepository
                        .getAllCarsToyotaOrderByModelAndTravelledDistance();

        Set<CarExportDto> carExportDtoSet = new LinkedHashSet<>();

        for (Car car : carsByToyota) {

            CarExportDto carExportDto = this.modelMapper
                    .map(car, CarExportDto.class);

            carExportDtoSet.add(carExportDto);

        }


        CarExportRootDto carExportRootDto = new CarExportRootDto();

        carExportRootDto.setDtoCarSet(carExportDtoSet);


        this.xmlParser.exportHml(carExportRootDto,CarExportRootDto.class,
                "src/main/resources/exports/toyota-cars.xml");


    }

    @Override
    public void getCarsWithTheirParts() throws JAXBException {

        List<Car> allCars = this.carRepository.findAll();

        Set<CarExportsWithPartsDto> dtoSet = new LinkedHashSet<>();

        for (Car car : allCars) {

            Set<PartsExportDto> partsExportDtos = new LinkedHashSet<>();

            for (Part part : car.getParts()) {

                PartsExportDto partsExport =
                        this.modelMapper.map(part,PartsExportDto.class);

                partsExportDtos.add(partsExport);
            }

            PartsRootDto partsRootDto = new PartsRootDto();
            partsRootDto.setPartsExportDtos(partsExportDtos);

            CarExportsWithPartsDto carExportsWithPartsDto
            = this.modelMapper.map(car,CarExportsWithPartsDto.class);
            carExportsWithPartsDto.setParts(partsRootDto);
            dtoSet.add(carExportsWithPartsDto);

        }

        CarsWithPartsExportRootDto carsExport = new CarsWithPartsExportRootDto();
        carsExport.setSet(dtoSet);

        this.xmlParser.exportHml(carsExport,CarsWithPartsExportRootDto.class,
                "src/main/resources/exports/cars-and-parts.xml");

    }

    private Set<Part> getRandomListOfParts() {

        List<Part> all = this.partRepository.findAll();
        Set<Part> randomList = new LinkedHashSet<>();

        Random random = new Random();

        for (int i = 0; i < 3; i++) {

            int index = random.nextInt(all.size()-1);
            if (!randomList.contains(all.get(index))) {
                randomList.add(all.get(index));
            }

        }

        return randomList;
    }
}
