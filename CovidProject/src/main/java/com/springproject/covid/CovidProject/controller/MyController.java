package com.springproject.covid.CovidProject.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springproject.covid.CovidProject.model.Location;
import com.springproject.covid.CovidProject.service.COVIDData;

@Controller
public class MyController {

	@Autowired
	COVIDData covidService;

	@RequestMapping("/")
	public String homeMethod(Model model) {
		List<Location> locList = covidService.getLocationList();
		LocalDate today = LocalDate.now( ZoneId.of("Asia/Kolkata" ) ) ;
		model.addAttribute("currentDate",today.toString());
		int totalCases = locList.stream().mapToInt(stat -> stat.getLatestcase()).sum();
		int prevCases = locList.stream().mapToInt(stat -> stat.getVarCases()).sum();
		model.addAttribute("locList", locList);
		model.addAttribute("totalReportedCase", totalCases);
		model.addAttribute("totalChangeCase", prevCases);
		return "home";
	}

}
