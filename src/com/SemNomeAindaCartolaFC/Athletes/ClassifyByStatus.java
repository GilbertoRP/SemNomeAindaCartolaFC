package com.SemNomeAindaCartolaFC.Athletes;

import java.util.*;

public class ClassifyByStatus implements ClassifyAthletesAlgorithm{


    private static int STATUSCOUNT = 8;
    private String name;

    @Override
    public Athlete[] classify(Athlete[] athletes) {

        List<Athlete>[] matrix = (ArrayList<Athlete>[])new ArrayList[ClassifyByStatus.STATUSCOUNT];
        for(int i = 0; i < athletes.length; i++) {
            Athlete athlete = athletes[i];
            matrix[athlete.status_id].add(athlete);
        }

        int totalAthletes = 0;
        for(int i = 0; i < matrix.length; i++) {
            totalAthletes += matrix[i].size();
        }

        Athlete[] allAthletes = new Athlete[totalAthletes];

        for(int i = 0, index = 0; i < matrix.length; i++) {

            for(int j = 0; j < matrix[i].size(); j++, index++) {

                allAthletes[index] = matrix[i].get(j);
            }
        }

        return allAthletes;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
