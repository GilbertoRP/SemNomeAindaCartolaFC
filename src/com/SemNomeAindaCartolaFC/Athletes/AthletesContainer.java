package com.SemNomeAindaCartolaFC.Athletes;

import java.util.ArrayList;

public class AthletesContainer {
	//TODO: Add save file formatter to save results.
	private String savePath;
	
	private ArrayList<Athlete> athletes = new ArrayList<Athlete>();

	public Athlete[] getAthletes() {
		return (Athlete[]) this.athletes.toArray().clone();
	}
	
	public void setTimeSavePath(String savePath) {
		this.savePath = savePath;
	}

	public void addAthletes(Athlete[] athletes) {
		for(int i = 0; i < athletes.length; i++) {
			this.addAthlete(athletes[i]);
		}
	}

	public void addAthlete(Athlete athlete) {
		athletes.add(athlete);
	}
	
	public AthletesContainer sortWith(SortAthletesAlgorithm sortAlgorithm) {
		Athlete[] sortedAthletes = sortAlgorithm.sort(this.getAthletes());
		this.replaceAllCurrentAthletesWith(sortedAthletes);		
		return this;
	}
	
	public AthletesContainer classifyByProperty(ClassifyAthletesAlgorithm classifyAlgorithm) {
		Athlete[] classifiedAthletes = classifyAlgorithm.classify(this.getAthletes());
		this.replaceAllCurrentAthletesWith(classifiedAthletes);
		return this;
	}
	
	private void replaceAllCurrentAthletesWith(Athlete[] newAthletes) {
		for(int i = 0; i < newAthletes.length; i++) {
			Athlete athleteAtIthIndex = newAthletes[i];
			this.athletes.set(i, athleteAtIthIndex);
		}
	}
}
