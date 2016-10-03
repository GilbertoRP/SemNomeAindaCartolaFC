package com.SemNomeAindaCartolaFC.Athletes;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by gilberto on 02/10/16.
 */
public class QuickSortAthletes implements SortAthletesAlgorithm{


    @Override
    public Athlete[] sort(Athlete[] athletes) {

        List<Athlete> athleteList = Arrays.asList(athletes);
        List<Athlete> sortedList = quickSort(athleteList);
        return sortedList.toArray(new Athlete[sortedList.size()]);
    }

    private ArrayList<Athlete> quickSort(List<Athlete> athletes) {

        if (athletes.size() <= 0) return new ArrayList<>();
        else {
            Integer pivotIndex = athletes.size() / 2;
            Athlete pivot = athletes.get(pivotIndex);
            List<Athlete> lower = getLessThan(pivotIndex, athletes);
            List<Athlete> greater = getGreaterOrEqualThan(pivotIndex, athletes);
            return concatenate(
                quickSort(lower),
                pivot,
                quickSort(greater)
            );
        }
    }

    private ArrayList<Athlete> getLessThan(Integer pivotIndex, List<Athlete> athletes) {
        ArrayList<Athlete> lowers = new ArrayList<>();

        Athlete pivot = athletes.get(pivotIndex);

        for(int i = 0; i < athletes.size(); i++) {

            Athlete athlete = athletes.get(i);
            if (athlete.orderingKey < pivot.orderingKey)
                lowers.add(athlete);
        }

        return lowers;
    }

    private ArrayList<Athlete> getGreaterOrEqualThan(Integer pivotIndex, List<Athlete> athletes) {
        ArrayList<Athlete> greaters = new ArrayList<>();

        Athlete pivot = athletes.get(pivotIndex);

        for(int i = 0; i < athletes.size(); i++) {

            Athlete athlete = athletes.get(i);
            if (athlete.orderingKey >= pivot.orderingKey && pivotIndex != i)
                greaters.add(athlete);
        }

        return greaters;
    }

    private ArrayList<Athlete> concatenate(List<Athlete> lowers, Athlete pivot, List<Athlete> greaters) {
        ArrayList<Athlete> athletesConcat = new ArrayList<>();


        for(int i = 0; i < lowers.size(); i++) {
            Athlete athlete = lowers.get(i);
            athletesConcat.add(athlete);
        }

        athletesConcat.add(pivot);

        for(int i = 0; i < greaters.size(); i++) {
            Athlete athlete = greaters.get(i);
            athletesConcat.add(athlete);
        }

        return athletesConcat;
    }
}

