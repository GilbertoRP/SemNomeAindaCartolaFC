package com.SemNomeAindaCartolaFC.Athletes;

import java.util.ArrayList;

/**
 * Created by gilberto on 02/10/16.
 */
public class ClassifyByPosition implements ClassifyAthletesAlgorithm {

    String name;

    @Override
    public Athlete[] classify(Athlete[] athletes) {

        ArrayList<Athlete>[] positions = new ArrayList[7];
        for(int i = 1; i <= 6; i++) {
            positions[i] = new ArrayList();
        }

        for(int i = 0; i < athletes.length; i++) {

            Athlete athlete = athletes[i];
            ArrayList<Athlete> position = positions[athlete.position_id];
            position.add(athlete);
        }

        ArrayList<Athlete> ordered = new ArrayList<>();

        for(int i = 1; i <= 6; i++) {

            ArrayList<Athlete> currentList = positions[i];
            for(int j = 0; j < currentList.size(); j++) {
                Athlete currentAthlete = currentList.get(j);
                ordered.add(currentAthlete);
            }
        }

        return ordered.toArray(new Athlete[ordered.size()]);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
