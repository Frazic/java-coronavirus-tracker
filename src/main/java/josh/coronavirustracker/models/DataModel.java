package josh.coronavirustracker.models;

public class DataModel {

    private String state;
    private String country;
    private int latestTotalCases;
    private int previousTotalCases;

    public int oneDayChange() {
        return latestTotalCases - previousTotalCases;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(String latestTotalCases) {
        this.latestTotalCases = Integer.parseInt(latestTotalCases);
    }

    public int getPreviousTotalCases() {
        return previousTotalCases;
    }

    public void setPreviousTotalCases(String previousTotalCases) {
        this.previousTotalCases = Integer.parseInt(previousTotalCases);
    }

    @Override
    public String toString() {
        return "{" +
                "Country:'" + country + '\'' +
                ",State:'" + state + '\'' +
                ",Cases:" + latestTotalCases + '\'' +
                ",Change:" + this.oneDayChange() + '\'' +
                '}';
    }
}
