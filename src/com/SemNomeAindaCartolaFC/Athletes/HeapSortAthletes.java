package com.SemNomeAindaCartolaFC.Athletes;

import java.util.*;

/**
 * Created by gilberto on 02/10/16.
 */
public class HeapSortAthletes implements SortAthletesAlgorithm {
    @Override
    public Athlete[] sort(Athlete[] athletes) {

        Heap heap = new Heap();
        heap.setSource(athletes);

        ArrayList<Athlete> sortedList = new ArrayList<>();

        for(int i = 0; i < athletes.length; i++) {
            Athlete athlete = heap.getHigher();
            sortedList.add(athlete);
        }

        return sortedList.toArray(new Athlete[sortedList.size()]);
    }

    @Override
    public String getName() {
        return "HPST";
    }

    private class Heap {

        private int powersOf2 = 64;
        private ArrayList<Athlete> heap = new ArrayList<>();

        public Heap() {
            heap.ensureCapacity(powersOf2);
        }

        public Athlete getHigher() {

            Athlete higher = heap.get(0);
            heap.remove(0);
            heapify();
            return higher;
        }

        public void setSource(Athlete[] athletes) {
            for(int i = 0; i < athletes.length; i++) {
                add(athletes[i]);
            }
        }

        public void add(Athlete athlete) {

            heap.add(athlete);
            heapify();
        }

        private void heapify() {
            if (heap.size() == powersOf2) {
                powersOf2 = powersOf2 * 2;
                heap.ensureCapacity(powersOf2);
            }

            int lastIndex = heap.size() - 1;

            if (lastIndex >= 0) {
                heapifyRecursively(lastIndex);
            }
        }

        private void heapifyRecursively(int lastIndex) {
            Athlete childAthlete = heap.get(lastIndex);
            int fatherIndex = lastIndex / 2;
            Athlete fatherAthlete = heap.get(fatherIndex);

            if (childAthlete.id != fatherAthlete.id) {
                if (childAthlete.orderingKey >= fatherAthlete.orderingKey) {
                    heap.set(lastIndex, fatherAthlete);
                    heap.set(fatherIndex, childAthlete);
                    heapifyRecursively(fatherIndex);
                }
            }
        }
    }
}
