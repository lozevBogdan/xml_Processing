package exercise.hml.processing.demo.services.impl;

import exercise.hml.processing.demo.dto.exports.ProductExportDto;
import exercise.hml.processing.demo.dto.exports.ProductExportRootDto;
import exercise.hml.processing.demo.dto.imports.ProductImportDto;
import exercise.hml.processing.demo.dto.imports.ProductImportRootDto;
import exercise.hml.processing.demo.entities.Category;
import exercise.hml.processing.demo.entities.Product;
import exercise.hml.processing.demo.entities.User;
import exercise.hml.processing.demo.repositories.CategoryRepository;
import exercise.hml.processing.demo.repositories.ProductRepository;
import exercise.hml.processing.demo.repositories.UserRepository;
import exercise.hml.processing.demo.services.ProductService;
import exercise.hml.processing.demo.util.ValidatorUtil;
import exercise.hml.processing.demo.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCTS_PATH = "src/main/resources/xmls/products.xml";


    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;



    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil, XmlParser xmlParser, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedProducts() throws JAXBException {

        ProductImportRootDto productDto =
                this.xmlParser.parseXml(ProductImportRootDto.class, PRODUCTS_PATH);

        int index = 0;
        for (ProductImportDto productImportDto : productDto.getProductImportDtos()) {


            if(this.validatorUtil.isValid(productDto)){

                Product product = this.modelMapper
                        .map(productImportDto,Product.class);

                if (index % 3 == 0) {
                    product.setBuyer(getRandomUser());
                }
                product.setSeller(getRandomUser());

                product.setCategories(getRandomSetOfCategories());

                this.productRepository.saveAndFlush(product);

            }
            index++;
        }

    }

    @Override
    public void getProductInRange500And1000() {

        Set<Product> productSet = this.productRepository.getAllProductInRange500And1000();

        Set<ProductExportDto> importDtoSet = new HashSet<>();

        for (Product product : productSet) {


            ProductExportDto importDto = this.modelMapper
                    .map(product, ProductExportDto.class);

            importDto.setSeller(product.getSeller().getFirstName() + " " +
                    product.getSeller().getLastName());

            importDtoSet.add(importDto);

        }

        ProductExportRootDto productImportRootDto = new ProductExportRootDto();
        productImportRootDto.setProducts(importDtoSet);

        try {

            this.xmlParser.exportHml(productImportRootDto,
                    ProductExportRootDto.class,"src/main/resources/exports/products-in-range.xml");

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private Set<Category> getRandomSetOfCategories() {

        List<Category> allCategories = this.categoryRepository.findAll();

        Set<Category> randomSet = new HashSet<>();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {

            int index = random.nextInt(allCategories.size()-1);
            Category category = allCategories.get(index);
            if (!randomSet.contains(category)){
                randomSet.add(category);
            }
        }
        return randomSet;
    }

    private User getRandomUser() {

        List<User> allUsers = this.userRepository.findAll();


        Random rnd = new Random();
         int index = rnd.nextInt(allUsers.size() - 1);

         return allUsers.get(index);

    }
}
