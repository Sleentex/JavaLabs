package Lab2.service;

import Lab2.exception.ConvertException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.Serializable;

public class XmlConverter<T extends Serializable> implements Converter<T> {
    private Class<T> typeOfClass;

    public XmlConverter(Class<T> typeOfClass) {
        this.typeOfClass = typeOfClass;
    }
    @Override
    public String serializeToString(T obj) throws ConvertException {
        try{
            XmlMapper xmlMapper = new XmlMapper(); //клас для преобразования мого об'єкта XML в String
            String xml = xmlMapper.writeValueAsString(obj);
            return xml;
        }
        catch (Exception e) {
            throw new ConvertException(e.getMessage());
        }
    }

    @Override
    public T deserializeString(String str) throws ConvertException {
        try{
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(str, typeOfClass);
        }
        catch (Exception e) {
            throw new ConvertException(e.getMessage());
        }
    }
}
