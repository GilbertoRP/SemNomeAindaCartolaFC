package com.SemNomeAindaCartolaFC.Athletes;


import java.util.*;

public class ClassifyByClub implements ClassifyAthletesAlgorithm{



    private String name;

    @Override
    public Athlete[] classify(Athlete[] athletes) {
        HashMap<Integer, ArrayList<Athlete>> clubMap = new HashMap<>();

        for(int i = 0; i < athletes.length; i++) {

            Athlete athlete = athletes[i];

            ArrayList<Athlete> athletesListOfTheClub = clubMap.get(athlete.club_id);
            if (athletesListOfTheClub == null) {
                athletesListOfTheClub = new ArrayList<>();
                clubMap.put(athlete.club_id, athletesListOfTheClub);
            }

            athletesListOfTheClub.add(athlete);
        }

        List<Athlete> responseAthletes = new ArrayList<>();

        for (Map.Entry<Integer, ArrayList<Athlete>> clubAthletes : clubMap.entrySet()) {

            for(int i = 0; i <clubAthletes.getValue().size(); i++ ){

                Athlete currentAthlete = clubAthletes.getValue().get(i);

                responseAthletes.add(currentAthlete);
            }
        }

        return responseAthletes.toArray(new Athlete[responseAthletes.size()]);
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
