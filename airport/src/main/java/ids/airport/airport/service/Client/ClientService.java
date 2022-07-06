package ids.airport.airport.service.Client;

import ids.airport.airport.model.Client;

import java.util.List;

public interface ClientService {
    Client findById(long clientId);

    List<Client> findAll();

    void deleteClient(long clientId);

    Client updateClient(Client client);

    Client createClient (Client client);
}
