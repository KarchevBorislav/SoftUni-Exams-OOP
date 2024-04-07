package restaurant.models.waiter;


public class HalfTimeWaiter extends BaseWaiter {
    private static final int HALF_TIME_WAITER_INITIAL_EFFICIENCY = 4;

    public HalfTimeWaiter(String name) {
        super(name, HALF_TIME_WAITER_INITIAL_EFFICIENCY);
    }

    @Override
    public void work() {
        super.setEfficiency(Math.max(super.getEfficiency() - 2,0));

    }
}
