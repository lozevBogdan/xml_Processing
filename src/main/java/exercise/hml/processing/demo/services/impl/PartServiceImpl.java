package exercise.hml.processing.demo.services.impl;

import exercise.hml.processing.demo.dto.imports.PartImportDto;
import exercise.hml.processing.demo.dto.imports.PartImportRootDto;
import exercise.hml.processing.demo.entities.Part;
import exercise.hml.processing.demo.entities.Supplier;
import exercise.hml.processing.demo.repositories.PartRepository;
import exercise.hml.processing.demo.repositories.SupplierRepository;
import exercise.hml.processing.demo.services.PartService;
import exercise.hml.processing.demo.util.ValidatorUtil;
import exercise.hml.processing.demo.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {

    private final static String
            PATH_PARTS = "src/main/resources/xmls/parts.xml";

    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidatorUtil validatorUtil;
    private final SupplierRepository supplierRepository;

    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper,
                           XmlParser xmlParser, ValidatorUtil validatorUtil, SupplierRepository supplierRepository) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void seedParts() throws JAXBException {

        PartImportRootDto partImportRootDto =
                this.xmlParser.parseXml(PartImportRootDto.class, PATH_PARTS);
        for (PartImportDto partImportDto : partImportRootDto.getPartsDto()) {
            Part part = this.modelMapper.map(partImportDto,Part.class);
            part.setSupplier(gerRandomSupplier());
            this.partRepository.saveAndFlush(part);
        }
    }

    private Supplier gerRandomSupplier() {
        List<Supplier> allSuppliers = this.supplierRepository.findAll();
        Random random = new Random();
        int index = random.nextInt(allSuppliers.size()- 1);
        return allSuppliers.get(index);
    }
}
