package restaurant.core;

import restaurant.models.client.Client;
import restaurant.models.client.ClientImpl;
import restaurant.models.waiter.FullTimeWaiter;
import restaurant.models.waiter.HalfTimeWaiter;
import restaurant.models.waiter.Waiter;
import restaurant.models.working.WorkingImpl;
import restaurant.repositories.ClientRepository;
import restaurant.repositories.Repository;
import restaurant.repositories.WaiterRepository;


import java.util.List;
import java.util.stream.Collectors;


import static restaurant.common.ConstantMessages.*;
import static restaurant.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Client> clientRepository;
    private Repository<Waiter> waiterRepository;

    private int count;

    public ControllerImpl() {
        this.clientRepository = new ClientRepository();
        this.waiterRepository = new WaiterRepository();
        this.count = 0;
    }

    @Override
    public String addWaiter(String type, String waiterName) {
        Waiter waiter;
        switch (type) {
            case "FullTimeWaiter":
                waiter = new FullTimeWaiter(waiterName);
                break;
            case "HalfTimeWaiter":
                waiter = new HalfTimeWaiter(waiterName);

                break;
            default:
                throw new IllegalArgumentException(WAITER_INVALID_TYPE);
        }

        this.waiterRepository.add(waiter);


        return String.format(WAITER_ADDED, type, waiterName);
    }

    @Override
    public String addClient(String clientName, String... orders) {
        Client client = new ClientImpl(clientName);
        for (String order : orders) {
            client.getClientOrders().add(order);

        }
        this.clientRepository.add(client);


        return String.format(CLIENT_ADDED, clientName);
    }

    @Override
    public String removeWaiter(String waiterName) {
        Waiter waiter = this.waiterRepository.byName(waiterName);

        if (waiter == null) {
            throw new IllegalArgumentException(String.format(WAITER_DOES_NOT_EXIST, waiterName));
        }
        this.waiterRepository.remove(waiter);
        return String.format(WAITER_REMOVE, waiterName);
    }

    @Override
    public String removeClient(String clientName) {
        Client client = this.clientRepository.byName(clientName);
        if (client == null) {
            throw new IllegalArgumentException(String.format(CLIENT_DOES_NOT_EXIST, clientName));
        }
        this.clientRepository.remove(client);


        return String.format(CLIENT_REMOVE, clientName);
    }

    @Override
    public String startWorking(String clientName) {
        Client client = this.clientRepository.byName(clientName);

        List<Waiter> waiterList = this.waiterRepository.getCollection().stream().filter(Waiter::canWork).collect(Collectors.toList());
        if (waiterList.isEmpty()) {
            throw new IllegalArgumentException(THERE_ARE_NO_WAITERS);
        }
        WorkingImpl working = new WorkingImpl();
        working.takingOrders(client, waiterList);
        this.count++;


        return String.format(ORDERS_SERVING, clientName);
    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        if (this.count == 0) {
            builder.append("None");
        } else {
            builder.append(String.format(FINAL_CLIENTS_COUNT, this.count)).append(System.lineSeparator());
        }
        builder.append(FINAL_WAITERS_STATISTICS).append(System.lineSeparator());
        for (Waiter waiter : this.waiterRepository.getCollection()) {
            builder.append(String.format(FINAL_WAITER_NAME, waiter.getName())).append(System.lineSeparator());
            builder.append(String.format(FINAL_WAITER_EFFICIENCY, waiter.getEfficiency())).append(System.lineSeparator());
            if (waiter.takenOrders().getOrdersList().size() == 0) {
                builder.append("Taken orders: None").append(System.lineSeparator());
            }
            builder.append(String.format(FINAL_WAITER_ORDERS, waiter.takenOrders().getOrdersList().stream().collect(Collectors.joining(", ")))).append(System.lineSeparator());


        }
        return builder.toString().trim();
    }
}