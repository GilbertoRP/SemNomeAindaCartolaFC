package com.SemNomeAindaCartolaFC;

import com.SemNomeAindaCartolaFC.Athletes.*;

public class SemNomeAindaCartolaFCMain {

	static public void main(String[] args) throws Exception {

		/*
		// How to use methods and classes...
		AthleteFactory factory = new AthleteFactory("path/to/athltesJsonBase.json");
		Athlete[] athletes = factory.createAthletesDataUpTo(1000);
		AthletesContainer container = new AthletesContainer();
		container.addAthletes(athletes);
		container.sort(new QuickSortAthletes);
		// now the container has sorted all athletes...
		* */

		AthleteFactory factory = new AthleteFactory("/home/gilberto/Projects/SemNomeAindaCartolaFC/data/25-09-2016.json");
		Athlete[] athletes = factory.createAthletesDataUpTo(1000);
		AthletesContainer container = new AthletesContainer();
		container.addAthletes(athletes);
		container.sortWith(new QuickSortAthletes());

		Athlete[] orderedAthletes = container.getAthletes();

		System.out.printf("%-40s%s\n", "Nome", "Chave de Ordenação");
		for(int i = 0; i < orderedAthletes.length; i++) {
			Athlete athlete = orderedAthletes[i];
			System.out.printf("%-40s%6f\n", athlete.name, athlete.orderingKey);
		}
	}
}
