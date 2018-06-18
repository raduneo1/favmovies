package movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class FavoriteMovies1Application {

	public static void main(String[] args) {
		SpringApplication.run(FavoriteMovies1Application.class, args);
	}
	
//	@Bean
//	public FilterRegistrationBean corsFilter() {
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    CorsConfiguration config = new CorsConfiguration();
//	    config.setAllowCredentials(true);
//	    config.addAllowedOrigin("http://localhost:3000");
//	    config.addAllowedHeader("*");
//	    config.addAllowedMethod("*");
//	    source.registerCorsConfiguration("/**", config);
//	    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//	    bean.setOrder(0);
//	    return bean;
//	}
//	
}
