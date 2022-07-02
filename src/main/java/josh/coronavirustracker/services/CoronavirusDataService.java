package josh.coronavirustracker.services;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import josh.coronavirustracker.models.DataModel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CoronavirusDataService {

    private static final String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<DataModel> dataList = new ArrayList<>();

    // PostConstruct tells spring to run this on application startup
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *") // Run every day at 1am
    public void getData() throws IOException, InterruptedException, CsvException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(DATA_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse CSV
        List<DataModel> newData = new ArrayList<>();
        StringReader stringReader = new StringReader(response.body());
        CSVReader csvReader = new CSVReaderBuilder(stringReader)
                .withSkipLines(1)
                .build();
        List<String[]> data = csvReader.readAll();
        for (String[] row : data) {
            DataModel dataModel = new DataModel();
            dataModel.setState(row[0]);
            dataModel.setCountry(row[1]);
            dataModel.setLatestTotalCases(row[row.length -1]);
            dataModel.setPreviousTotalCases(row[row.length -2]);

            System.out.println(dataModel);

            newData.add(dataModel);
        }
        this.dataList = newData;
    }

    public List<DataModel> getDataList() {
        return dataList;
    }
}
