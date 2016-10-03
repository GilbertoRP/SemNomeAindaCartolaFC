package com.SemNomeAindaCartolaFC.Athletes;

/**
 * Created by gilberto on 02/10/16.
 */
public class RadixMSDSortAthletes implements SortAthletesAlgorithm {
    @Override
    public Athlete[] sort(Athlete[] athletes) {

        String.format("%6f", athletes[0].orderingKey);
        return new Athlete[0];
    }

    @Override
    public String getName() {
        return "RMSD";
    }


}
