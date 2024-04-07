package restaurant.repositories;

import restaurant.models.client.Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ClientRepository implements Repository<Client> {

    private Collection<Client> clients;

    public ClientRepository() {
        this.clients = new ArrayList<>();
    }

    @Override
    public Collection<Client> getCollection() {
        return Collections.unmodifiableCollection(clients);
    }

    @Override
    public void add(Client client) {
        this.clients.add(client);
    }

    @Override
    public boolean remove(Client client) {
        return this.clients.remove(client);
    }

    @Override
    public Client byName(String name) {
        return this.clients.stream().filter(client -> client.getName().equals(name)).findFirst().orElse(null);
    }
}
