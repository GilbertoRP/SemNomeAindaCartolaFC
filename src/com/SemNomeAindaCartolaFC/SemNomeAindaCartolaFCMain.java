package com.SemNomeAindaCartolaFC;

import com.SemNomeAindaCartolaFC.Athletes.*;

public class SemNomeAindaCartolaFCMain {

	static public void main(String[] args) {
		
		AthleteGenerator generator = new AthleteGenerator();
		generator.setDataAndGetMinMaxForGeneration("/home/gilberto/Projects/SemNomeAindaCartolaFC/data/25-09-2016.json");

		AthletesContainer container = new AthletesContainer();
		Athlete[] generateds = generator.generateAthletesUpTo(10);
		for(int i = 0; i < 10; i++) {
			container.addAthlete(generateds[i]);
		}

	}
}
