package dio.challenge.cepApi.service;

import dio.challenge.cepApi.model.Client;

public interface ClientService {

	Iterable<Client> findAll();

	Client findById(Long id);

	void create(Client client);

	void update(Long id, Client client);

	void delete(Long id);

}
