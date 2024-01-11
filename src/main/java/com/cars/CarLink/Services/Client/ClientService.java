package com.cars.CarLink.Services.Client;

import com.cars.CarLink.Exceptions.NotFoundException;
import com.cars.CarLink.Model.Client;
import com.cars.CarLink.Model.ClientDto;

import java.util.List;


public interface ClientService {
    Client findById(Long id) throws NotFoundException;
    Client findByEmail(String email) throws NotFoundException;
    List<Client> getAllClients();
    Client createClient(ClientDto clientDto);
    Client updateClient(Long id, ClientDto clientDto);
    void deleteClient(Long id) throws NotFoundException;

    Boolean existsByEmail(String email);
}
