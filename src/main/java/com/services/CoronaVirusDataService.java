package com.services;

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
import com.models.LocationStats;

@Service
public class CoronaVirusDataService  {
	
	
	private String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<LocationStats> allStats=new ArrayList<>();
	
	public List<LocationStats> getAllStats() {
		return allStats;
	}

	public void setAllStats(List<LocationStats> allStats) {
		this.allStats = allStats;
	}
	@PostConstruct
	@Scheduled(cron ="* * 1 * * *")
	public void fetchvirusdata() throws IOException,InterruptedException {
		List< LocationStats> newstats=new ArrayList();
		HttpClient client=HttpClient.newHttpClient();
		
		HttpRequest request= HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
		HttpResponse<String> httpResponse= client.send(request,HttpResponse.BodyHandlers.ofString());
		StringReader csvbodyreader= new StringReader(httpResponse.body());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvbodyreader);
		for (CSVRecord record : records) {
			LocationStats locationStats =new LocationStats();
		    locationStats.setState(record.get("Province/State"));
		    locationStats.setCountry(record.get("Country/Region"));
		    int  latestcase=Integer.parseInt(record.get(record.size()-1));
		    int  prevdaycase=Integer.parseInt(record.get(record.size()-2));
		    locationStats.setDiffFromPrevDay(latestcase-prevdaycase);
		    locationStats.setLatestTotalcases(latestcase);
	
		    newstats.add(locationStats);
		}
		        this.allStats=newstats;
	  }
}

