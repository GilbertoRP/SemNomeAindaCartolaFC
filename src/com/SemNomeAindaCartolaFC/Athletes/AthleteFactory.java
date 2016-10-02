package com.SemNomeAindaCartolaFC.Athletes;
	
import org.json.*;
import java.util.ArrayList;

public class AthleteFactory {

	private String jsonBasePath = "";
	private AthleteGenerator generator = new AthleteGenerator();

	public AthleteFactory(String jsonBasePath) {
		this.jsonBasePath = jsonBasePath;
	}

	public Athlete[] createAthletesDataUpTo(Integer theMax) throws Exception {

		JSONObject athletesJsonBase = BaseJSONParser.getJSONObjectFromFile(jsonBasePath);

		Athlete[] athletes = getAllAthletesFrom(athletesJsonBase);
		ArrayList<Athlete> athletesRequested = extractMaximumNumberOfAthletes(athletes, theMax);

		if (athletesRequested.size() < theMax) {
			generator.extractDataForRandomSelection(athletes);

			Integer numberOfAthletesMissing = theMax - athletesRequested.size();

			for(int i = 0; i < numberOfAthletesMissing; i++) {
				Athlete genAthlete = generator.generateAthlete();
				athletesRequested.add(genAthlete);
			}
		}

		return (Athlete[]) athletesRequested.toArray();
	}

	private ArrayList<Athlete> extractMaximumNumberOfAthletes(Athlete[] athletes, Integer request) {
		Integer maxNumberToExtract = getMinimun(athletes.length, request);

		ArrayList<Athlete> athletesList = new ArrayList<>();

		for(int i = 0; i < maxNumberToExtract; i++) {
			athletesList.add(athletes[i]);
		}

		return athletesList;
	}

	private Integer getMinimun(Integer left, Integer right) {
		return left > right ? right : left;
	}

	private Athlete[] getAllAthletesFrom(JSONObject baseData) {
		JSONArray atletasArray = baseData.getJSONArray("atletas");
		int atletasArrayLength = atletasArray.length();
		ArrayList<Athlete> allAthletes = new ArrayList<>();

		for (int i = 0; i < atletasArrayLength; i++) {
			JSONObject athleteData = atletasArray.getJSONObject(i);
			Athlete athlete = createAthleteFrom(athleteData);
			allAthletes.add(athlete);
		}

		return (Athlete[])allAthletes.toArray();
	}

	private Athlete createAthleteFrom(JSONObject athleteData) {
		Athlete athlete = new Athlete();
		athlete.name = athleteData.getString("nome");
		athlete.id = athleteData.getInt("atleta_id");
		athlete.nick = athleteData.getString("apelido");
		//athlete.photoURL = athleteData.getString("foto");
		athlete.price = athleteData.getDouble("preco_num");
		athlete.variation = athleteData.getDouble("variacao_num");
		athlete.mean = athleteData.getDouble("media_num");
		athlete.gamesPlayed = athleteData.getInt("jogos_num");
		athlete.position_id = athleteData.getInt("posicao_id");
		athlete.orderingKey = athlete.mean * 3 + athlete.variation * 2 + athlete.price * 1;
		return athlete;
	}
}
