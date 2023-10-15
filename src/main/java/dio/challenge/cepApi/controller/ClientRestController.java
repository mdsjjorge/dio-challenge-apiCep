package dio.challenge.cepApi.controller;

import dio.challenge.cepApi.exception.BadRequestException;
import dio.challenge.cepApi.exception.ResourceNotFoundException;
import dio.challenge.cepApi.model.Client;
import dio.challenge.cepApi.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;


/**
 * This {@link RestController} represents our <b>Facade</b>, as it abstracts all
 * the complexity of integrations (H2 Database and ViaCEP API) in one
 * simple and cohesive interface (REST API).
 * 
 * @author falvojr
 * @author <a href="https://github.com/mdsjjorge"> mdsjjorge </a>
 */

@Slf4j
@RestController
@RequestMapping("clients")
public class ClientRestController {

	@Autowired
	private ClientService clientService;

	@GetMapping
	public ResponseEntity <Iterable<?>>  findAll() {
		try {
			return ResponseEntity.ok(clientService.findAll());
		} catch (BadRequestException e) {
			String errorMessage = "Error: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonList(errorMessage));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			Client client = clientService.findById(id);
			return ResponseEntity.ok(client);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found: " + e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Client client) {
		try {
			clientService.create(client);
			return ResponseEntity.ok(client);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Client client) {
		try {
			clientService.update(id, client);
			return ResponseEntity.ok(client);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found: " + e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			clientService.delete(id);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found: " + e.getMessage());
		}

	}
}
