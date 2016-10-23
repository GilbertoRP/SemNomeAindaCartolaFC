package com.SemNomeAindaCartolaFC;

import com.SemNomeAindaCartolaFC.Athletes.*;

import java.io.PrintWriter;

public class SemNomeAindaCartolaFCMain {

	static public void main(String[] args) throws Exception {

		PrintWriter output = new PrintWriter("resultados_SemNomeAinda.txt", "UTF-8");
		AthleteFactory factory = new AthleteFactory("data/25-09-2016.json");
		AthletesContainer container = new AthletesContainer();
		container.setTimeFilePrinter(output);


		int maxAthletes = 100000;
		for(int upToAthletes = 100; upToAthletes <= maxAthletes; upToAthletes *= 10) {

			Athlete[] athletes = factory.createAthletesDataUpTo(upToAthletes);

			container.setAthletes(athletes);
			container.setAthletes(QuickSortSorting(container));
			container.setAthletes(ClassifyWithAlgorithm("QSRM", container));
			container.setAthletes(HeapSortSorting(container));
			container.setAthletes(ClassifyWithAlgorithm("HPST", container));
			container.setAthletes(RadixMSDSorting(container));
			container.setAthletes(ClassifyWithAlgorithm("RMSD", container));


		}

		output.close();
	}

	public static Athlete[] QuickSortSorting(AthletesContainer container) {
		return container.sortWith(new QuickSortAthletes());
	}

	public static Athlete[] HeapSortSorting(AthletesContainer container) {
		return container.sortWith(new HeapSortAthletes());
	}

	public static Athlete[] RadixMSDSorting(AthletesContainer container) {
		return container.sortWith(new RadixMSDSortAthletes());
	}

	public static Athlete[] ClassifyWithAlgorithm(String algorithmName, AthletesContainer container) {
		ClassifyAthletesAlgorithm classifyAlgorithm = new ClassifyByPosition();
		classifyAlgorithm.setName(algorithmName);
		return container.classifyByProperty(classifyAlgorithm);
	}
}
