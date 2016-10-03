package com.SemNomeAindaCartolaFC;

import com.SemNomeAindaCartolaFC.Athletes.*;

import java.io.PrintWriter;

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
		PrintWriter output = new PrintWriter("resultados_SemNomeAinda.txt", "UTF-8");
		AthleteFactory factory = new AthleteFactory("data/25-09-2016.json");

		int maxAthletes = 100000;
		for(int upToAthletes = 100; upToAthletes <= maxAthletes; upToAthletes *= 10) {

			Athlete[] athletes = factory.createAthletesDataUpTo(upToAthletes);

			//QUICKSORT
			{
				AthletesContainer container = new AthletesContainer();
				container.setTimeFilePrinter(output);
				container.addAthletes(athletes);

				container.sortWith(new QuickSortAthletes());
				ClassifyAthletesAlgorithm classifyAlgorithm = new ClassifyByPosition();
				classifyAlgorithm.setName("QSRM");
				container.classifyByProperty(classifyAlgorithm);
			}

			// HEAPSORT
			{
				AthletesContainer container = new AthletesContainer();
				container.setTimeFilePrinter(output);
				container.addAthletes(athletes);

				container.sortWith(new HeapSortAthletes());
				ClassifyAthletesAlgorithm classifyAlgorithm = new ClassifyByPosition();
				classifyAlgorithm.setName("HPST");
				container.classifyByProperty(classifyAlgorithm);
			}

			// RADIXMSDSORT
			{
				AthletesContainer container = new AthletesContainer();
				container.setTimeFilePrinter(output);
				container.addAthletes(athletes);

				container.sortWith(new RadixMSDSortAthletes());
				ClassifyAthletesAlgorithm classifyAlgorithm = new ClassifyByPosition();
				classifyAlgorithm.setName("RMSD");
				container.classifyByProperty(classifyAlgorithm);
			}
		}

		output.close();
	}
}
