package Lab2.service;

import Lab2.exception.ConvertException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class JsonConverter<T extends Serializable> implements Converter<T> {
    private Class<T> typeOfClass;

    public JsonConverter(Class<T> typeOfClass) {
        this.typeOfClass = typeOfClass;
    }
    @Override
    public String serializeToString(T obj) throws ConvertException {

        try{
            ObjectMapper mapper = new ObjectMapper(); // клас для преобразования мого об'єкта JSON в String
            String json = mapper.writeValueAsString(obj);//спитати чого НУЛЛ показує
            return json;
        }
        catch (Exception e) {
            throw new ConvertException(e.getMessage());
        }
    }

    @Override
    public T deserializeString(String str) throws ConvertException {
        try{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(str, typeOfClass);
        }
        catch (Exception e) {
            throw new ConvertException(e.getMessage());
        }

    }
}
