package com.SemNomeAindaCartolaFC.Athletes;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by gilberto on 02/10/16.
 */
public class RadixMSDSortAthletes implements SortAthletesAlgorithm {

    @Override
    public Athlete[] sort(Athlete[] athletes) {

        ArrayList<Athlete>[] positions = new ArrayList[10];

        for(int i = 0; i <= 9; i++) {
            positions[i] = new ArrayList();
        }

        ArrayList<Athlete> athletesList = new ArrayList<>();

        athletesList.addAll(Arrays.asList(athletes));


        int maxIteration = 10;

        for(int i = 0; i < maxIteration; i++) {

            for(int j = 0; j < athletesList.size(); j++) {

                Athlete athlete = athletesList.get(j);

                String unpadded = String.format("%6f", athletes[0].orderingKey);
                String padded = "000000000000000".substring(unpadded.length()) + unpadded;

                char charAnalysed = padded.charAt(i);
                if (charAnalysed == '.' || charAnalysed == '-') continue;
                int bucketIndex = Integer.parseInt("" + charAnalysed);
                ArrayList<Athlete> selectionPosition = positions[bucketIndex];

                selectionPosition.add(athlete);
            }

            ArrayList<Athlete> intermediaryArray = new ArrayList<>();

            for(int bucketIndex = 0; bucketIndex <= 9; bucketIndex++) {

                ArrayList<Athlete> bucket = positions[bucketIndex];

                for(int j = 0; j < bucket.size(); j++) {
                    Athlete selected = bucket.get(j);
                    intermediaryArray.add(selected);
                }
            }

            for(int posIndex = 0; posIndex <= 9; posIndex++) {
                positions[posIndex].clear();
            }

            athletesList.clear();
            athletesList.addAll(intermediaryArray);
        }

        return athletesList.toArray(new Athlete[athletesList.size()]);
    }

    @Override
    public String getName() {
        return "RMSD";
    }


}
