package ids.airport.airport.service.Client;

import ids.airport.airport.exception.ResourceNotFoundException;
import ids.airport.airport.model.Client;
import ids.airport.airport.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client findById(long clientId) {
        Optional<Client> clientDb = this.clientRepository.findById(clientId);
        if(clientDb.isPresent()){
            return clientDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + clientId);
        }
    }

    @Override
    public List<Client> findAll() {
        return (List<Client>) this.clientRepository.findAll();
    }

    @Override
    public void deleteClient(long clientId) {
        Optional<Client> clientDb = this.clientRepository.findById(clientId);

    }

    @Override
    public Client updateClient(Client client) {
        Optional<Client> clientDb = this.clientRepository.findById(client.getId());
        if(clientDb.isPresent()){
            Client clientUpdate = clientDb.get();
            clientUpdate.setId(client.getId());
            clientUpdate.setFirstname(client.getFirstname());
            clientUpdate.setSurname(client.getSurname());
            clientUpdate.setLanguage(client.getLanguage());
            clientUpdate.setCountry(client.getCountry());
            clientUpdate.setAirport(client.getAirport());

            clientRepository.save(clientUpdate);
            return clientUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + client.getId());
        }
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }
}
