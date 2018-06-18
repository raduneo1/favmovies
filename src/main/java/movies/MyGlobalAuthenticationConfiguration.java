package movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

@Configuration
@EnableGlobalAuthentication
public class MyGlobalAuthenticationConfiguration {

       @Autowired
       public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
               auth
               .inMemoryAuthentication()
               .withUser("radu").password("radu").roles("USER");
       }
}