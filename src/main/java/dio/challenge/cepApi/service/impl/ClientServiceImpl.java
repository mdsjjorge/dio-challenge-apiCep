package dio.challenge.cepApi.service.impl;

import java.util.Optional;

import dio.challenge.cepApi.model.Address;
import dio.challenge.cepApi.model.Client;
import dio.challenge.cepApi.repository.AddressRepository;
import dio.challenge.cepApi.repository.ClientRepository;
import dio.challenge.cepApi.service.ClientService;
import dio.challenge.cepApi.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implementation of <b>Strategy</b> {@link dio.challenge.cepApi.service.ClientService}, which can be
 * injected by Spring (via {@link Autowired}). Therefore, as this class is a
 * {@link Service}, it will be treated as a <b>Singleton</b>.
 * 
 * @author falvojr
 * @author <a href="https://github.com/mdsjjorge"> mdsjjorge </a>
 */
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ViaCepService viaCepService;


	@Override
	public Iterable<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	public Client findById(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		return client.get();
	}

	@Override
	public void create(Client client) {
		this.saveClientWithCep(client);
	}

	@Override
	public void update(Long id, Client client) {
		Optional<Client> clientBd = clientRepository.findById(id);
		if (clientBd.isPresent()) {
			this.saveClientWithCep(client);
		}
	}

	@Override
	public void delete(Long id) {
		clientRepository.deleteById(id);
	}

	private void saveClientWithCep(Client client) {

		String cep = client.getAddress().getCep();
		Address address = addressRepository.findById(cep)
				.orElseGet(	() -> {
					Address newAddress = viaCepService.findCep(cep);
					addressRepository.save(newAddress);
					return newAddress;
				});

		client.setAddress(address);
		clientRepository.save(client);
	}

}
