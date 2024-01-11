package com.cars.CarLink.Services.Client;

import com.cars.CarLink.Exceptions.NotFoundException;
import com.cars.CarLink.Model.Client;
import com.cars.CarLink.Model.ClientDto;
import com.cars.CarLink.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client findById(Long id) throws NotFoundException {
        return clientRepository.findById(id).orElseThrow(()-> new NotFoundException("Client not found. Client Id: "+id));
    }

    @Override
    public Client findByEmail(String email) throws NotFoundException {
        return clientRepository.findByEmail(email).orElseThrow(()->new NotFoundException("Client not found. Email: "+email));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(ClientDto clientDto) {
        Client client = new Client(clientDto.getEmail(),
                clientDto.getName(),
                clientDto.getStreetAddress(),
                clientDto.getCity(), clientDto.getCountry()
                ,clientDto.getPhoneNumber(), clientDto.getAbout());
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long id, ClientDto clientDto) {
        return null;
    }

    @Override
    public void deleteClient(Long id) throws NotFoundException {
        Client client =this.findById(id);
        clientRepository.delete(client);
    }

    @Override
    public Boolean existsByEmail(String email){
        return clientRepository.existsByEmail(email);
    }
}
