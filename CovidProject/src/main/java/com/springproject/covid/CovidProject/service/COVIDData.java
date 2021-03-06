package com.springproject.covid.CovidProject.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.springproject.covid.CovidProject.model.Location;

@Service
public class COVIDData {

	public static String covidDataUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<Location> locationList = new ArrayList<>();

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void covidData() throws IOException, InterruptedException {
		List<Location> newLocation = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(covidDataUrl)).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvReader = new StringReader(response.body());
//		System.out.println(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
		for (CSVRecord record : records) {
			String state = record.get("Province/State");
			Location locationStat = new Location();
			locationStat.setState(record.get("Province/State"));
			locationStat.setCountry(record.get("Country/Region"));
			
			int LatestCases =  (Integer.parseInt(record.get(record.size()-1)));
			int prevCase = (Integer.parseInt(record.get(record.size()-2)));
			locationStat.setLatestcase(LatestCases);
			locationStat.setVarCases(LatestCases-prevCase);
			System.out.println(locationStat);
			newLocation.add(locationStat);
		}
		this.locationList = newLocation;
		
	}

	public List<Location> getLocationList() {
		return locationList;
	}
}
