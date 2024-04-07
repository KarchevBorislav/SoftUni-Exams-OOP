package restaurant.models.waiter;


import restaurant.models.orders.TakenOrders;
import restaurant.models.orders.TakenOrdersImpl;

import static restaurant.common.ExceptionMessages.*;

public abstract class BaseWaiter implements Waiter {
    private String name;
    private int efficiency;
    private TakenOrders takenOrders;

    protected BaseWaiter(String name, int efficiency) {
        setName(name);
        setEfficiency(efficiency);
        this.takenOrders = new TakenOrdersImpl();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(WAITER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setEfficiency(int efficiency) {
        if (efficiency < 0) {
            throw new IllegalArgumentException(WAITER_EFFICIENCY_LESS_THAN_ZERO);
        }
        this.efficiency = efficiency;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEfficiency() {
        return this.efficiency;
    }

    @Override
    public boolean canWork() {
        return this.efficiency > 0;
    }

    @Override
    public TakenOrders takenOrders() {
        return takenOrders;
    }

    @Override
    public abstract void work();
}
