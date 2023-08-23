package org.brillo;

import java.time.LocalTime;

public class Concurrent {
    public static void main(String[] args) {
        int totalPancakes = 0;
        int totalEaten = 0;

        long startTime = System.currentTimeMillis();
        LocalTime start = LocalTime.now();
        LocalTime end = start.minusSeconds(30);


        while (start.isAfter(end)) {
            int madeByShopkeeper = Math.min(12, 30 - totalPancakes);

            int user1 = Math.min(5, madeByShopkeeper);
            int user2 = Math.min(5, madeByShopkeeper - user1);
            int user3 = Math.min(5, madeByShopkeeper - user1 - user2);

            totalPancakes += madeByShopkeeper;
            totalEaten += user1 + user2 + user3;

            System.out.println("Starting time: " + startTime);
            System.out.println("Ending time: " + System.currentTimeMillis());
            System.out.println("Pancakes made by shopkeeper: " + madeByShopkeeper);
            System.out.println("Pancakes eaten by users: " + (user1 + user2 + user3));

            if (totalPancakes >= 15) {
                System.out.println("Shopkeeper met the needs of all users");
            } else {
                System.out.println("Shopkeeper could not meet the needs of all users");
                System.out.println("Unmet pancake orders: " + (15 - totalPancakes));
            }
            System.out.println("Pancakes wasted: " + Math.max(0, totalPancakes - 15));
            System.out.println("*********");
        }
    }
}
