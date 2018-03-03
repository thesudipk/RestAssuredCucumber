package com.kundu.testauto.report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class CucumberHtmlReports {

	public static void main(String... args) {

		try {

			List jsonfiles = new ArrayList();
			File folder = new File(args[0].trim());
			File[] listoffiles = folder.listFiles();

			for (File file : listoffiles) {
				if (file.isFile()) {
					jsonfiles.add(file.getPath());
				}

			}

			Configuration conf = new Configuration(new File(args[1]), "My project");

			ReportBuilder reportbuilder = new ReportBuilder(jsonfiles, conf);
			reportbuilder.generateReports();
			System.out.println("Report generation successful. Check in the C:\\Workspace\\RestAssured\\com.kundu.testauto\\target\\report\\cucumber-html-reports folder");

		} catch (Exception e) {

		}
	}
}
