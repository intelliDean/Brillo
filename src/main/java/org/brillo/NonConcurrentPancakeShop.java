package org.brillo;

import java.time.LocalTime;
import java.util.Random;

public class NonConcurrentPancakeShop {
    public static void main(String[] args) {
        int totalOrders = 10;
        int maxPancakesPerUser = 5;
        int maxPancakesPerShopkeeper = 12;

        Random random = new Random();
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = startTime.plusSeconds(30);
        for (int order = 1; order <= totalOrders; order++) {

            int shopkeeperPancakes = random.nextInt(maxPancakesPerShopkeeper + 1);
            int user1Pancakes = random.nextInt(maxPancakesPerUser + 1);
            int user2Pancakes = random.nextInt(maxPancakesPerUser + 1);
            int user3Pancakes = random.nextInt(maxPancakesPerUser + 1);

            int totalPancakesEaten = user1Pancakes + user2Pancakes + user3Pancakes;

            boolean shopkeeperMetNeeds = totalPancakesEaten <= shopkeeperPancakes;
            int wastedPancakes = 0;
            if (shopkeeperPancakes >= totalPancakesEaten) {
                wastedPancakes = shopkeeperPancakes - totalPancakesEaten;
            }
            int unmetOrders = totalPancakesEaten > shopkeeperPancakes
                    ? totalPancakesEaten - shopkeeperPancakes
                    : 0;
            String needsMet = shopkeeperMetNeeds ? "Ye, He did" : "No, He did not";
            LocalTime temp = endTime;

            System.out.printf("""
                            Time phase: %s%n
                            Start Time: %s%n
                            End Time: %s%n
                            Total Pancakes Made by Shopkeeper: %d%n
                            Total Pancakes Requested by Users: %d%n
                            Was Shopkeeper Able To Meet Users' Needs?: %s%n
                            Wasted Pancakes: %d%n
                            Unmet Orders: %d
                            --------------------------------------------------------------------------
                            """, order, startTime, endTime, shopkeeperPancakes,
                    totalPancakesEaten, needsMet, wastedPancakes, unmetOrders
            );
            startTime = temp;
            endTime = startTime.plusSeconds(30);
        }
    }
}



