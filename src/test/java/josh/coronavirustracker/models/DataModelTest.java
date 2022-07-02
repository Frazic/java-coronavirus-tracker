package josh.coronavirustracker.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataModelTest {

    @Test
    void oneDayChangeReturnsPositiveValueWithMoreNewCases() {
        DataModel dataModel = new DataModel();
        dataModel.setPreviousTotalCases("0");
        dataModel.setLatestTotalCases("2");

        int change = dataModel.oneDayChange();

        assertEquals(2, change);
    }

    @Test
    void oneDayChangeReturnsNegativeValueWithLessNewCases() {
        DataModel dataModel = new DataModel();
        dataModel.setPreviousTotalCases("2");
        dataModel.setLatestTotalCases("0");

        int change = dataModel.oneDayChange();

        assertEquals(-2, change);
    }

    @Test
    void oneDayChangeReturnsZeroWithNoNewCases() {
        DataModel dataModel = new DataModel();
        dataModel.setPreviousTotalCases("2");
        dataModel.setLatestTotalCases("2");

        int change = dataModel.oneDayChange();

        assertEquals(0, change);
    }
}