package com.SemNomeAindaCartolaFC.Athletes;
	
import org.json.*;

public class AthleteFactory {
	
	public Athlete createAthleteFrom(JSONObject athleteData) {
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
