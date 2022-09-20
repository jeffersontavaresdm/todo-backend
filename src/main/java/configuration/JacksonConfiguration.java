package configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component(value = "configuration")
public class JacksonConfiguration {

  @Bean
  @Qualifier("customObjectMapper")
  public ObjectMapper springObjectMapper() {
    return JsonMapper
      .builder()
      .findAndAddModules()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .build();
  }

  @Bean("defaultObjectMapper")
  @Primary
  public ObjectMapper objectMapper() {
    return JsonMapper
      .builder()
      .findAndAddModules()
      .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
      .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
      .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
      .build();
  }
}