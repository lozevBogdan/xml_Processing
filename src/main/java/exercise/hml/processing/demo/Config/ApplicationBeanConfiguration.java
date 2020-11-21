package exercise.hml.processing.demo.Config;

import com.google.gson.*;
import exercise.hml.processing.demo.util.ValidatorUtil;
import exercise.hml.processing.demo.util.ValidatorUtilImpl;
import exercise.hml.processing.demo.util.XmlParser;
import exercise.hml.processing.demo.util.XmlParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @Bean
    public Gson gson() {
        return new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type typeOfT,
                                                     JsonDeserializationContext context) throws JsonParseException {
                        return LocalDateTime
                                .parse(json.getAsString(), DateTimeFormatter
                                        .ofPattern("yyyy-MM-dd'T'HH:mm:ss")); }})
            .create();

    }

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }


    @Bean
    public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
    @Bean
    public ValidatorUtil validatorUtil(){

        return new ValidatorUtilImpl(validator());
    }




}
