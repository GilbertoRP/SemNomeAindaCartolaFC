package com.SemNomeAindaCartolaFC;

import com.SemNomeAindaCartolaFC.Athletes.*;

public class SemNomeAindaCartolaFCMain {

	static public void main(String[] args) {
		
		Athlete a = new Athlete();
		a.name = "Gilberto Ribeiro";
		a.id = 123123;
		a.gamesPlayed = 10;
		
		
		System.out.println("Hello World! " + a.toString());
	}
}
