package exercise.hml.processing.demo.services.impl;

import exercise.hml.processing.demo.dto.exports.SupplierExportDto;
import exercise.hml.processing.demo.dto.exports.SupplierExportRootDto;
import exercise.hml.processing.demo.dto.imports.SupplierImportDto;
import exercise.hml.processing.demo.dto.imports.SupplierImportRootDto;
import exercise.hml.processing.demo.entities.Customer;
import exercise.hml.processing.demo.entities.Supplier;
import exercise.hml.processing.demo.repositories.SupplierRepository;
import exercise.hml.processing.demo.services.SupplierService;
import exercise.hml.processing.demo.util.ValidatorUtil;
import exercise.hml.processing.demo.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class SupplierServiceImpl implements SupplierService {


    private final static String
            PATH_SUPPLIERS = "src/main/resources/xmls/suppliers.xml";

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidatorUtil validatorUtil) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
    }


    @Override
    public void seedSuppliers() throws JAXBException {

        SupplierImportRootDto supplierImportRootDto =
                this.xmlParser.parseXml(SupplierImportRootDto.class, PATH_SUPPLIERS);

        for (SupplierImportDto supplierImportDto :
                supplierImportRootDto.getSupplierImportDtoList()) {

            if (this.validatorUtil.isValid(supplierImportDto)){
                Supplier supplier = this.modelMapper
                        .map(supplierImportDto,Supplier.class);

                this.supplierRepository.saveAndFlush(supplier);
            }

        }

    }

    @Override
    public void getLocalSuppliers() throws JAXBException {

        Set<Supplier> localSuppliers =
                this.supplierRepository.getLocalSuppliers();

        Set<SupplierExportDto> exportDtoSet = new LinkedHashSet<>();

        SupplierExportRootDto supplierExportRootDto =
                new SupplierExportRootDto();


        for (Supplier localSupplier : localSuppliers) {

            SupplierExportDto exportDto =
                    this.modelMapper.map(localSupplier, SupplierExportDto.class);

            exportDto.setPartCount(localSupplier.getParts().size());
            exportDtoSet.add(exportDto);

        }

        supplierExportRootDto.setExportDtoSet(exportDtoSet);

        this.xmlParser.exportHml(supplierExportRootDto,SupplierExportRootDto.class,
                "src/main/resources/exports/local-suppliers.xml");


    }
}
