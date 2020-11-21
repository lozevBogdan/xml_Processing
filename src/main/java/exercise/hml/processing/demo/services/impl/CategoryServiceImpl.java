package exercise.hml.processing.demo.services.impl;

import exercise.hml.processing.demo.dto.exports.CategoryExportDto;
import exercise.hml.processing.demo.dto.exports.CategoryRootExportDto;
import exercise.hml.processing.demo.dto.imports.CategoryImportDto;
import exercise.hml.processing.demo.dto.imports.CategoryImportRootDto;
import exercise.hml.processing.demo.entities.Category;
import exercise.hml.processing.demo.entities.Product;
import exercise.hml.processing.demo.repositories.CategoryRepository;
import exercise.hml.processing.demo.services.CategoryService;
import exercise.hml.processing.demo.util.ValidatorUtil;
import exercise.hml.processing.demo.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.*;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORY_PATH =
            "src/main/resources/xmls/categories.xml";

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper,
                               ValidatorUtil validatorUtil, XmlParser xmlParser) {

        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
    }


    @Override
    public void seedCategory() throws JAXBException {

        CategoryImportRootDto categoryImportRootDto =
                this.xmlParser.parseXml(CategoryImportRootDto.class, CATEGORY_PATH);

        for (CategoryImportDto categoryDto : categoryImportRootDto.getCategories()) {


            if (this.validatorUtil.isValid(categoryDto)){

                Category category = this.modelMapper.map(categoryDto,Category.class);
                this.categoryRepository.saveAndFlush(category);

            }

        }


    }

    @Override
    public void getAllCategoriesByProductsCount() throws JAXBException {

        Set<Category> categories =
                this.categoryRepository.findAllAndOrderByCountOfProducts();

        Set<CategoryExportDto> categoryExportDtoList = new LinkedHashSet<>();

        for (Category category : categories) {

            CategoryExportDto categoryExportDto = new CategoryExportDto();
            categoryExportDto.setName(category.getName());

            double sum = 0.0;
            for (Product product : category.getProducts()) {
                sum += product.getPrice().doubleValue();
            }

            double size = category.getProducts().size() * 1.0;

            categoryExportDto.setAveragePrice((sum/size));
            categoryExportDto.setTotalRevenue((sum));
            categoryExportDto.setProductCount(category.getProducts().size());

            categoryExportDtoList.add(categoryExportDto);

        }

        CategoryRootExportDto categoryRootExportDto = new CategoryRootExportDto();
        categoryRootExportDto.setCategoryExportDtos(categoryExportDtoList);

        this.xmlParser.exportHml(categoryRootExportDto,
                CategoryRootExportDto.class,
                "src/main/resources/exports/categories-by-products.xml");

    }
}
