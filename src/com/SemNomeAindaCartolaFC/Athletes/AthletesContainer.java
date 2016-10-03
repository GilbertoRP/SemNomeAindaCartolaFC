package com.SemNomeAindaCartolaFC.Athletes;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AthletesContainer {
	//TODO: Add save file formatter to save results.
	private String savePath = null;
	private PrintWriter writer = null;
	
	private ArrayList<Athlete> athletes = new ArrayList<Athlete>();

	public Athlete[] getAthletes() {
		return athletes.toArray(new Athlete[athletes.size()]).clone();
	}
	
	public void setTimeFilePrinter(PrintWriter writer) {
		this.writer = writer;
	}

	public void addAthletes(Athlete[] athletes) {
		this.athletes.clear();
		for(int i = 0; i < athletes.length; i++) {
			this.addAthlete(athletes[i]);
		}
	}

	public void addAthlete(Athlete athlete) {
		athletes.add(athlete);
	}
	
	public AthletesContainer sortWith(SortAthletesAlgorithm sortAlgorithm) {

		long startTime = System.currentTimeMillis();

		Athlete[] sortedAthletes = sortAlgorithm.sort(this.getAthletes());
		this.replaceAllCurrentAthletesWith(sortedAthletes);

		long estimatedTime = System.currentTimeMillis() - startTime;
		String result = String.format("%s, numerico, %d, %d", sortAlgorithm.getName(), sortedAthletes.length, estimatedTime);
		writer.println(result);
		return this;
	}
	
	public AthletesContainer classifyByProperty(ClassifyAthletesAlgorithm classifyAlgorithm) {

		long startTime = System.currentTimeMillis();

		Athlete[] classifiedAthletes = classifyAlgorithm.classify(this.getAthletes());
		this.replaceAllCurrentAthletesWith(classifiedAthletes);

		long estimatedTime = System.currentTimeMillis() - startTime;
		String result = String.format("%s, categorico, %d, %d", classifyAlgorithm.getName(), classifiedAthletes.length, estimatedTime);
		writer.println(result);
		return this;
	}
	
	private void replaceAllCurrentAthletesWith(Athlete[] newAthletes) {
		for(int i = 0; i < newAthletes.length; i++) {
			Athlete athleteAtIthIndex = newAthletes[i];
			this.athletes.set(i, athleteAtIthIndex);
		}
	}
}
