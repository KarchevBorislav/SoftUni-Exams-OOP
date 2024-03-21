package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.museum.BaseMuseum;
import goldDigger.models.museum.Museum;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OperationImpl implements Operation {

    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        Collection<String> spotExhibits = spot.getExhibits();

        for (Discoverer discoverer : discoverers) {
            while (discoverer.canDig() && spotExhibits.iterator().hasNext()) {
                discoverer.dig();

                String currentExhibit = spotExhibits.iterator().next();
                discoverer.getMuseum().getExhibits().add(currentExhibit);
                spotExhibits.remove(currentExhibit);
            }
        }
    }
}