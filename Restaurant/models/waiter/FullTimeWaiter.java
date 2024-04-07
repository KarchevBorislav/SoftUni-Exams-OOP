package restaurant.models.waiter;


public class FullTimeWaiter extends BaseWaiter {
    private static final int FULL_TIME_WAITER_INITIAL_EFFICIENCY = 8;

    public FullTimeWaiter(String name) {
        super(name, FULL_TIME_WAITER_INITIAL_EFFICIENCY);
    }

    @Override
    public void work() {
        super.setEfficiency(Math.max(super.getEfficiency() - 1, 0));

    }
}
