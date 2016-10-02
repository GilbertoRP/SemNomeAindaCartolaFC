package com.SemNomeAindaCartolaFC;

import com.SemNomeAindaCartolaFC.Athletes.*;

public class SemNomeAindaCartolaFCMain {

	static public void main(String[] args) {
		
		AthleteGenerator generator = new AthleteGenerator();
		generator.setDataAndGetMinMaxForGeneration("/home/gilberto/Projects/SemNomeAindaCartolaFC/data/25-09-2016.json");
		Athlete generated = generator.generateAthlete();
                
		System.out.println(generated.toString());
	}
}
