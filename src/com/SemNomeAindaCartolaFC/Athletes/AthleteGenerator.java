package com.SemNomeAindaCartolaFC.Athletes;

import java.util.*;

public class AthleteGenerator {

    private Double minMean = 0.0;
    private Double maxMean = 0.0;
    private Double minVariation = 0.0;
    private Double maxVariation = 0.0;
    private Double minPrice = 0.0;
    private Double maxPrice = 0.0;
    private Integer lastId = 0;
    private Integer maxGamesPlayed = 0;

    private Random rand = new Random();

    private ArrayList<String> firstNames = new ArrayList<>();
    private ArrayList<String> lastNames = new ArrayList<>();

    public Athlete generateAthlete() {
        Athlete genAthlete = new Athlete();
        
        ArrayList<Athlete> sepbyposition1 = new ArrayList<>(); 
        ArrayList<Athlete> sepbyposition2 = new ArrayList<>(); 
        ArrayList<Athlete> sepbyposition3 = new ArrayList<>(); 
        ArrayList<Athlete> sepbyposition4 = new ArrayList<>(); 
        ArrayList<Athlete> sepbyposition5 = new ArrayList<>(); 
        ArrayList<Athlete> sepbyposition6 = new ArrayList<>(); 
        
        ArrayList<Athlete> sepbyclub = new ArrayList<>();
        ArrayList<Athlete> newvet = new ArrayList<>();

        String name = String.format("%s %s", getRandomFirstName(), getRandomLastName());
        Double genMean = genDoubleBetween(minMean, maxMean);
        Double genVariation = genDoubleBetween(minVariation, maxVariation);
        Double genPrice = genDoubleBetween(minPrice, maxPrice);
        lastId = lastId + 1;
        Integer genGamesPlayed = genIntegerUpTo(maxGamesPlayed);
        Integer genPositionId = genIntegerUpTo(6) + 1;

        genAthlete.id = lastId;
        genAthlete.name = name;
        genAthlete.nick = name;
        genAthlete.mean = genMean;
        genAthlete.variation = genVariation;
        genAthlete.price = genPrice;
        genAthlete.gamesPlayed = genGamesPlayed;
        genAthlete.position_id = genPositionId;
        genAthlete.orderingKey = genAthlete.mean * 3 + genAthlete.variation * 2 + genAthlete.price * 1;
        
        newvet.add(genAthlete) ;
        
        switch (genAthlete.position_id){
            case 1: sepbyposition1.add(genAthlete); break;
            case 2: sepbyposition2.add(genAthlete); break;
            case 3: sepbyposition3.add(genAthlete); break;
            case 4: sepbyposition4.add(genAthlete); break;
            case 5: sepbyposition5.add(genAthlete); break;
            case 6: sepbyposition6.add(genAthlete); break;
        }
        
        //criar switch case para club_id
        
        
        
        return genAthlete;
    }

    public void extractDataForRandomSelection(Athlete[] athletesBase) {

        try {

            for (Athlete athlete : athletesBase) {
                tryAddFistAndLastNamesForRandomSelectableList(athlete);
                trySetMinMean(athlete);
                trySetMaxMean(athlete);
                trySetMinVariation(athlete);
                trySetMaxVariation(athlete);
                trySetMinPrice(athlete);
                trySetMaxPrice(athlete);
                trySetLastId(athlete);
                trySetMaxGamesPlayed(athlete);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getRandomFirstName() {
        Integer index = genIntegerUpTo(firstNames.size());
        return firstNames.get(index);
    }

    private String getRandomLastName() {
        Integer index = genIntegerUpTo(lastNames.size());
        return lastNames.get(index);
    }

    private Double genDoubleBetween(Double min, Double max) {
        return (rand.nextDouble() % (max)) + min;
    }

    private Integer genIntegerUpTo(Integer max) {
        return rand.nextInt(max);
    }

    private void tryAddFistAndLastNamesForRandomSelectableList(Athlete athlete) {
        if (athlete.name.length() > 0 && athlete.name.contains(" ")) {
            String[] namesSplit = athlete.name.split(" ", 2);
            String firstName = namesSplit[0];
            String lastName = namesSplit[1];

            firstNames.add(firstName);
            lastNames.add(lastName);
        }
        else {
            System.out.println("Athlete " + athlete.id.toString() + " doesn't have name");
        }
    }

    private void trySetMinMean(Athlete athlete) {
        if (athlete.mean < this.minMean) {
            this.minMean = athlete.mean;
        }
    }

    private void trySetMaxMean(Athlete athlete) {
        if (athlete.mean > this.maxMean) {
            this.maxMean = athlete.mean;
        }
    }

    private void trySetMinVariation(Athlete athlete) {
        if (athlete.variation < this.minVariation) {
            this.minVariation = athlete.variation;
        }
    }

    private void trySetMaxVariation(Athlete athlete) {
        if (athlete.variation > this.maxVariation) {
            this.maxVariation = athlete.variation;
        }
    }

    private void trySetMinPrice(Athlete athlete) {
        if (athlete.price < this.minPrice) {
            this.minPrice = athlete.price;
        }
    }

    private void trySetMaxPrice(Athlete athlete) {
        if (athlete.price > this.maxPrice) {
            this.maxPrice = athlete.price;
        }
    }

    private void trySetLastId(Athlete athlete) {
        if (athlete.id > this.lastId) {
            this.lastId = athlete.id;
        }
    }

    private void trySetMaxGamesPlayed(Athlete athlete) {
        if (athlete.gamesPlayed > this.maxGamesPlayed) {
            this.maxGamesPlayed = athlete.gamesPlayed;
        }
    }

}
