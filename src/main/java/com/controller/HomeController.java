package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.models.LocationStats;
import com.services.CoronaVirusDataService;

@Controller
public class HomeController {
	@Autowired
	CoronaVirusDataService service;

	@RequestMapping("/")
	public String home( Model model) {
		
		List<LocationStats>  allstats= service.getAllStats();
		int totalcases=allstats.stream().mapToInt(stat->stat.getLatestTotalcases()).sum();
		int totalnewcases=allstats.stream().mapToInt(stat->stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationstats",allstats);
		model.addAttribute("totalreportedcases",totalcases);
		model.addAttribute("totalnewcases",totalnewcases);
		
		
		return "home";
	}
}
