package movies;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;


public class SpringDataRestCustomization extends RepositoryRestConfigurerAdapter {

 @Override
 public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.getCorsRegistry().addMapping("/**")
            .allowedOrigins("http://localhost:3000/")
            .allowCredentials(true)
            .allowedMethods("POST, GET, PUT, OPTIONS, DELETE")
            .maxAge(3600)
            .allowedHeaders("Access-Control-Allow-Origin, Authorization, Origin, X-Requested-With, Content-Type, Key, Accept");
  }
 
 //       response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
// response.setHeader("Access-Control-Allow-Credentials", "true");
// response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
// response.setHeader("Access-Control-Max-Age", "3600");
// response.setHeader("Access-Control-Allow-Headers", "Authorization, Origin, X-Requested-With, Content-Type, Key, Accept");

}