package dio.challenge.cepApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The following modules were selected:
 * - Spring Data JPA
 * - Spring Web
 * - H2 Database
 * - OpenFeign
 * - lombok
 *
 * @author falvojr
*  @author <a href="https://github.com/mdsjjorge"> mdsjjorge </a>
 */

@EnableFeignClients
@SpringBootApplication
public class CepApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CepApiApplication.class, args);
	}

}
