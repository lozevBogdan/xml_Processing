package exercise.hml.processing.demo.util;

import javax.xml.bind.JAXBException;

public interface XmlParser  {

    <O> O parseXml(Class<O> objectClass, String filePath) throws JAXBException;

    <O> void  exportHml(O object,Class<O> objectClass, String filePath) throws JAXBException;

}
