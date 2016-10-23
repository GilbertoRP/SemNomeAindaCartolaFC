package com.SemNomeAindaCartolaFC.Athletes;

import java.io.*;
import java.util.*;

public class AthletesContainer {

	private PrintWriter writer = null;
	
	private ArrayList<Athlete> athletes = new ArrayList<Athlete>();

	public Athlete[] getAthletes() {
		return athletes.toArray(new Athlete[athletes.size()]).clone();
	}
	
	public void setTimeFilePrinter(PrintWriter writer) {
		this.writer = writer;
	}

	public void setAthletes(Athlete[] athletes) {
		this.athletes.clear();
		this.athletes.addAll(Arrays.asList(athletes));
	}
	
	public Athlete[] sortWith(SortAthletesAlgorithm algorithm) {

		long startTime = System.currentTimeMillis();
		Athlete[] sortedAthletes = algorithm.sort(this.getAthletes());
		long estimatedTime = System.currentTimeMillis() - startTime;

		String algorithmName = algorithm.getName();
		int amountOfAthletes = sortedAthletes.length;
		String format = "%s, numerico, %d, %d";

		String result = String.format(format, algorithmName, amountOfAthletes, estimatedTime);
		writer.println(result);

		return sortedAthletes;
	}
	
	public Athlete[] classifyByProperty(ClassifyAthletesAlgorithm algorithm) {

		long startTime = System.currentTimeMillis();
		Athlete[] classifiedAthletes = algorithm.classify(this.getAthletes());
		long estimatedTime = System.currentTimeMillis() - startTime;

		String algorithmName = algorithm.getName();
		int amountOfAthletes = classifiedAthletes.length;
		String format = "%s, categorico, %d, %d";

		String result = String.format(format, algorithmName, amountOfAthletes, estimatedTime);
		writer.println(result);

		return classifiedAthletes;
	}

}
