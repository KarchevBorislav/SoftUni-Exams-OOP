package restaurant.models.working;

import restaurant.models.client.Client;
import restaurant.models.waiter.Waiter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class WorkingImpl implements Working {
    @Override
    public void takingOrders(Client client, Collection<Waiter> waiters) {
        Collection<String> clientOrders = client.getClientOrders();
        List<Waiter> waiterToWork = waiters.stream().filter(Waiter::canWork).collect(Collectors.toList());

        waiterToWork.forEach(waiter -> {
            while (waiter.canWork() && clientOrders.iterator().hasNext()) {
                String clientNextOrder = clientOrders.iterator().next();
                waiter.work();
                waiter.takenOrders().getOrdersList().add(clientNextOrder);

              clientOrders.remove(clientNextOrder);

            }

        });


    }

}

