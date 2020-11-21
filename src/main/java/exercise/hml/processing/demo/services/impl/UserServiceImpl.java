package exercise.hml.processing.demo.services.impl;

import exercise.hml.processing.demo.dto.exports.*;
import exercise.hml.processing.demo.dto.imports.UserImportDto;
import exercise.hml.processing.demo.dto.imports.UserImportRootDto;
import exercise.hml.processing.demo.entities.Product;
import exercise.hml.processing.demo.entities.User;
import exercise.hml.processing.demo.repositories.UserRepository;
import exercise.hml.processing.demo.services.UserService;
import exercise.hml.processing.demo.util.ValidatorUtil;
import exercise.hml.processing.demo.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private static final String USERS_PATH = "src/main/resources/xmls/users.xml";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil, XmlParser xmlParser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedUsers() throws JAXBException {

        UserImportRootDto userImportRootDto = this.xmlParser
                .parseXml(UserImportRootDto.class, USERS_PATH);


        for (UserImportDto dtoUser : userImportRootDto.getUsers()) {

            if (this.validatorUtil.isValid(dtoUser)){
                User user = this.modelMapper.map(dtoUser,User.class);

                this.userRepository.saveAndFlush(user);
            }


        }


    }

    @Override
    public void getAllSuccessfullySoldProducts() throws JAXBException {

        List<User> userWithSoldProducts =
                this.userRepository.getSuccessfullySoldProducts();

        List<User> usersWithProductsWithBuyer = new LinkedList<>();


        UserExportRootDto userExportRootDto = new UserExportRootDto();

        Set<UserExportDto> userExportDtoSet = new LinkedHashSet<>();


        for (User user : userWithSoldProducts) {

            List<ProductExportUserDto> productsDto = new LinkedList<>();

            Set<Product> soldProducts = new HashSet<>(user.getSold());

            for (Product product : soldProducts) {
                if (product.getBuyer() != null){


                  ProductExportUserDto productExportUserDto =
                          this.modelMapper.map(product, ProductExportUserDto.class);
                    productsDto.add(productExportUserDto);

                }
            }

            SoldProductDto soldProductDto = new SoldProductDto();
            soldProductDto.setProducts(productsDto);
            UserExportDto userExportDto = new UserExportDto();
            userExportDto.setFirstName(user.getFirstName());
            userExportDto.setLastName(user.getLastName());
            userExportDto.setProducts(soldProductDto);
            userExportDtoSet.add(userExportDto);

        }

        userExportRootDto.setUserExportDtos(userExportDtoSet);

        this.xmlParser.exportHml(userExportRootDto,
                UserExportRootDto.class,
                "src/main/resources/exports/users-sold-products.xml");


    }

    @Override
    public void getAllBySoldProductsSizeAndLastName() throws JAXBException {

        List<User> userList =
                this.userRepository.getAllAtLeastOneProductOrderByProductsAndLastName();

        List<SoldProductExporUserDto> soldProductExporUserDtoSet =
                new LinkedList<>();

        UserRootDtoInfo userRootDtoInfo = new UserRootDtoInfo();

        List<UserExportProductDto> userInfoes = new LinkedList<>();

        for (User user : userList) {

            List<ProductNameAndPriceExportDto> productNameAndPriceExportDtos =
                    new LinkedList<>();

            for (Product product : user.getSold()) {

                ProductNameAndPriceExportDto productNameAndPriceExportDto =
                        new ProductNameAndPriceExportDto();

                    //    this.modelMapper.map(product,ProductNameAndPriceExportDto.class);

                productNameAndPriceExportDto.setName(product.getName());
                productNameAndPriceExportDto.setPrice(product.getPrice());

                productNameAndPriceExportDtos.add(productNameAndPriceExportDto);

            }

            SoldProductExporUserDto soldProd = new SoldProductExporUserDto();

            soldProd.setCount(productNameAndPriceExportDtos.size());
            soldProd.setPriceExportDtoList(productNameAndPriceExportDtos);

            soldProductExporUserDtoSet.add(soldProd);

            UserExportProductDto userDtoIfo = this.modelMapper.map(user,
                    UserExportProductDto.class);

            userDtoIfo.setList(soldProductExporUserDtoSet);

            userInfoes.add(userDtoIfo);

        }
        userRootDtoInfo.setCount(userInfoes.size());
        userRootDtoInfo.setUsers(userInfoes);

            this.xmlParser.exportHml(userRootDtoInfo,UserRootDtoInfo.class,
                    "src/main/resources/exports/users-and-products.xml");

    }
}
