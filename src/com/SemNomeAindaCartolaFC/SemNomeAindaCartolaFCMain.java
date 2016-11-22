package com.SemNomeAindaCartolaFC;

import com.SemNomeAindaCartolaFC.Athletes.*;
import com.SemNomeAindaCartolaFC.DB.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SemNomeAindaCartolaFCMain {

	static boolean isRunning = true;
	static String ATHLETESDBFILE = "athletes";
	static String POSITIONSDBFILE = "positions";
	static String CLUBSDBFILE = "clubs";
	static String STATUSDBFILE = "statuses";

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
				println("Digite o caminho do arquivo JSON: ");

				if (reader.hasNextLine()) {
					String input = reader.next();
					generateAthletesFile(input);
				}

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

		DAO<Athlete> athletesDao = new DAO<>(ATHLETESDBFILE);
		Integer maxAthletes = athletesDao.getCurrent().size();

		DAO<Position> positionsDao = new DAO<>(POSITIONSDBFILE);
		DAO<Club> clubsDao = new DAO<>(CLUBSDBFILE);
		DAO<Status> statusesDao = new DAO<>(STATUSDBFILE);

		while(!valid){


			println("1 - ordenados por custo x benefício");
			println("2 - custo x benefício e classificados por posição");
			println("3 - custo x benefício e classificados por clube");
			println("4 - custo x benefício e classificados por status");
			println("5 - definir custo máximo");
			println("6 - definir quantidade máxima de jogadores para imprimir");
			println("123 - Sair desse menu");

			Integer menu1Access = reader.nextInt();

			valid = true;

			switch (menu1Access) {

				case 1: {
					printAthletesFormated(athletesDao.getCurrent(), maxCost, maxAthletes);
					valid = false;
					break;
				}

				case 2: {
					printPossiblePositions();
					println("Deseja imprimir os jogadores de qual posição?");
					String positionName = reader.next();
					List<Athlete> selectedByPosition = selectAthletesByPosition(athletesDao.getCurrent(), positionsDao.getCurrent(), positionName);

					printAthletesFormated(selectedByPosition, maxCost, maxAthletes);
					valid = false;
					break;
				}

				case 3: {
					printPossibleClubs();
					println("Deseja imprimir os jogadores de qual clube?");
					String clubName = reader.next();
					List<Athlete> selectedByClub = selectAthletesByClub(athletesDao.getCurrent(), clubsDao.getCurrent(), clubName);

					printAthletesFormated(selectedByClub, maxCost, maxAthletes);
					valid = false;
					break;
				}

				case 4: {
					printPossibleStatuses();
					println("Deseja imprimir os jogadores que estão com qual status?");
					String statusName = reader.next();
					List<Athlete> selectedByStatus = selectAthletesByStatus(athletesDao.getCurrent(), statusesDao.getCurrent(), statusName);

					printAthletesFormated(selectedByStatus, maxCost, maxAthletes);
					valid = false;
					break;
				}

				case 5: {
					println("Qual o custo máximo para seleção?");
					maxCost = reader.nextDouble();
					valid = false;
					break;
				}

				case 6: {
					println("Qual a quantidade máxima de jogadores para exibir?");
					maxAthletes = reader.nextInt();
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

	public static void printAthletesFormated(List<Athlete> athletes, Double maxCost, Integer printLimit) throws Exception {
		println(Athlete.toColumnFields());
		boolean shouldPrintAll = true;
		if (maxCost > 0) shouldPrintAll = false;

		for(int i = 0; i < athletes.size() && i < printLimit; i++ ) {

			if (!shouldPrintAll) {
				if (athletes.get(i).price <= maxCost)
					println(athletes.get(i).toFormatedField());
			}
			else println(athletes.get(i).toFormatedField());
		}
	}

	public static void printPossiblePositions() {
		DAO<Position> pdb = new DAO<>(POSITIONSDBFILE);
		try {
			List<Position> positions = pdb.getCurrent();
			println("Posições disponíveis:");
			for (Position p : positions) {
				println(p.id + " - " + p.name);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void printPossibleClubs() {
		DAO<Club> clubsDao = new DAO<>(CLUBSDBFILE);

		List<Club> clubs = null;
		try {
			clubs = clubsDao.getCurrent();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		println("Clubes disponíveis para consulta:");
		for(Club c : clubs) {
			println(c.id + " - " + c.name);
		}
	}

	public static void printPossibleStatuses() {
		DAO<Status> statusesDao = new DAO<>(STATUSDBFILE);
		List<Status> statuses = null;
		try {
			statuses = statusesDao.getCurrent();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		println("Status disponíveis para filtro:");
		for(Status s : statuses) {
			println(s.id + " - " + s.name);
		}
	}

	public static List<Athlete> selectAthletesByPosition(List<Athlete> athletes, List<Position> positions, String positionName) {

		List<Athlete> resulting = new ArrayList<>();

		boolean byName = positionName != null && positionName != "";
		int positionId = 0;
		for(int i = 0; i < positions.size(); i++) {
			Position p = positions.get(i);
			if (p.name.equalsIgnoreCase(positionName)) {
				println(p.id + " - " + p.name );
				positionId = p.id;
				break;
			}
		}
		if (byName) {

			for(int i = 0; i < athletes.size(); i++) {
				Athlete athlete = athletes.get(i);
				if (athlete.position_id == positionId) {
					resulting.add(athlete);
				}
			}

		}

		return resulting;
	}

	private static List<Athlete> selectAthletesByClub(List<Athlete> athletes, List<Club> clubs, String clubName) {

		ArrayList<Athlete> resulting = new ArrayList<>();

		boolean byName = clubName != null && clubName != "";

		int clubId = 0;

		for(Club c : clubs) {
			if (c.name.equalsIgnoreCase(clubName)) {
				clubId = c.id;
				break;
			}
		}


		if (byName) {

			for(Athlete athlete: athletes) {

				if (athlete.club_id == clubId) {
					resulting.add(athlete);
				}
			}
		}

		return resulting;
	}

	private static List<Athlete> selectAthletesByStatus(List<Athlete> athletes, List<Status> statuses, String statusName) {

		ArrayList<Athlete> resulting = new ArrayList<>();

		boolean byName = statusName != null && statusName != "";

		int statusId = 0;

		for(Status s : statuses) {
			if (s.name.equalsIgnoreCase(statusName)) {
				statusId = s.id;
				break;
			}
		}

		if (byName ) {

			for(Athlete athlete: athletes) {

				if (athlete.status_id == statusId) {
					resulting.add(athlete);
				}
			}
		}

		return resulting;
	}

	public static void generateAthletesFile(String path) throws Exception {

		AthleteFactory factory = new AthleteFactory(path);
		AthletesContainer container = new AthletesContainer();

		Athlete[] athletes = factory.getAllAthletes();
		DAO<Athlete> t = new DAO<>(ATHLETESDBFILE);
		container.setAthletes(athletes);
		athletes = QuickSortSorting(container);
		t.setAll(athletes);


		PositionFactory positionFactory = new PositionFactory(path);
		Position[] positions = positionFactory.getAllPositions();
		DAO<Position> ps = new DAO<>(POSITIONSDBFILE);
		ps.setAll(positions);


		ClubFactory clubFactory = new ClubFactory(path);
		Club[] clubs = clubFactory.getAllClubs();
		DAO<Club> cs = new DAO<>(CLUBSDBFILE);
		cs.setAll(clubs);

		StatusFactory statusFactory = new StatusFactory(path);
		Status[] statuses = statusFactory.getAllStatus();
		DAO<Status> ss = new DAO<>(STATUSDBFILE);
		ss.setAll(statuses);
	}

	public static Athlete[] QuickSortSorting(AthletesContainer container) {
		return container.sortWith(new QuickSortAthletes());
	}

	public static Athlete[] ClassifyByPosition(String algorithmName, AthletesContainer container) {
		ClassifyAthletesAlgorithm classifyAlgorithm = new ClassifyByPosition();
		classifyAlgorithm.setName(algorithmName);
		return container.classifyByProperty(classifyAlgorithm);
	}

	public static void println(Object o) {
		System.out.println(o);
	}
}
