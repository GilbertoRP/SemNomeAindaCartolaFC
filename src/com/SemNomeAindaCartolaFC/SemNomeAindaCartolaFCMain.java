package com.SemNomeAindaCartolaFC;

import com.SemNomeAindaCartolaFC.Athletes.*;
import com.SemNomeAindaCartolaFC.DB.*;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class SemNomeAindaCartolaFCMain {

	static boolean isRunning = true;
	static String ATHLETESDBFILE = "athletes";

	static public void main(String[] args) throws Exception {

		Scanner reader = new Scanner(System.in);

		while(isRunning) {
			isRunning = outterMenu(reader);
		}
	}

	public static boolean outterMenu(Scanner reader) throws Exception {
		println("1 - Imprimir N jogadores");
		println("2 - Usar JSON para criar arquivo ordenado");
		println("123 - Sair");

		Integer menuOutterAccess = reader.nextInt();

		switch (menuOutterAccess) {

			case 1:{
				innerMenu1(reader);
				break;
			}

			case 2: {
				generateAthletesFile();
				break;
			}

			case 123:
				isRunning = false;
				break;

			default:
				printOpcaoInvalida();
				break;
		}

		return isRunning;
	}

	public static void innerMenu1(Scanner reader) throws Exception {

		boolean valid = false;
		Double maxCost = -1.0;
		println("Qual a quantidade máxima de jogadores para exibir?");
		Integer maxAthletes = reader.nextInt();

		while(!valid){


			println("1 - ordenados por custo x benefício");
			println("2 - custo x benefício e classificados por posição");
			println("3 - custo x benefício e classificados por clube");
			println("4 - custo x benefício e classificados por status");
			println("5 - definir custo máximo");
			println("123 - Sair desse menu");

			Integer menu1Access = reader.nextInt();

			valid = true;

			switch (menu1Access) {

				case 1: {
					DAO<Athlete> athletesDao = new DAO<>(ATHLETESDBFILE);
					printAthletesFormated(athletesDao.getMax(maxAthletes), maxCost);
					break;
				}

				case 5: {
					println("Qual o custo máximo para seleção?");
					maxCost = reader.nextDouble();
					valid = false;
					break;
				}


				case 123: {

					break;
				}

				default:
					printOpcaoInvalida();
					valid = false;
					break;
			}
		}
	}

	public static void printOpcaoInvalida() {
		println("Opção inválida!");
	}

	public static void printAthletesFormated(List<Athlete> athletes, Double maxCost) throws Exception {
		println(Athlete.toColumnFields());
		boolean shouldPrintAll = true;
		if (maxCost > 0) shouldPrintAll = false;

		for(int i = 0; i < athletes.size(); i++ ) {

			if (!shouldPrintAll) {
				if (athletes.get(i).price <= maxCost)
					println(athletes.get(i).toFormatedField());
			}
			else println(athletes.get(i).toFormatedField());
		}
	}

	public static void generateAthletesFile() throws Exception {
		PrintWriter output = new PrintWriter("resultados_SemNomeAinda.txt", "UTF-8");
		AthleteFactory factory = new AthleteFactory("data/25-09-2016.json");
		AthletesContainer container = new AthletesContainer();
		container.setTimeFilePrinter(output);

		Athlete[] athletes = factory.getAllAthletes();
		DAO<Athlete> t = new DAO<>(ATHLETESDBFILE);
		container.setAthletes(athletes);
		athletes = QuickSortSorting(container);
		t.setAll(athletes);
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

	public static void println(Object o) {
		System.out.println(o);
	}
}
