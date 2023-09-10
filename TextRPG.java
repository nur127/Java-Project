package Project;

import java.util.Random;
import java.util.Scanner;

class Player {
    String name;
    int health;
    int damage;
    int gold;

    public Player(String name) {
        this.name = name;
        health = 100;
        damage = 20;
        gold = 0;
    }

    void takeDamage(int damage) {
        health -= damage;
    }

    boolean isAlive() {
        return health > 0;
    }
}

public class TextRPG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Text RPG!");
        System.out.print("Enter your character's name: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);

        while (player.isAlive()) {
            System.out.println("\n" + playerName + ", you are in a room.");
            System.out.println("Options:");
            System.out.println("1. Explore");
            System.out.println("2. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    exploreRoom(player);
                    break;
                case 2:
                    endGame(player);
                    return;
                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");
            }
        }

        System.out.println("Game over! " + playerName + " has been defeated.");
    }

    static void exploreRoom(Player player) {
        Random random = new Random();
        int monsterHealth = random.nextInt(50) + 50; // Random monster health between 50 and 100
        int monsterDamage = random.nextInt(10) + 10; // Random monster damage between 10 and 20

        System.out.println("A monster appears! It has " + monsterHealth + " health.");
        while (player.isAlive() && monsterHealth > 0) {
            System.out.println("\nOptions:");
            System.out.println("1. Attack");
            System.out.println("2. Run");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    int playerDamage = player.damage;
                    int monsterDamageTaken = playerDamage;
                    int playerDamageTaken = monsterDamage;

                    System.out.println(player.name + " attacks the monster for " + playerDamage + " damage.");
                    System.out.println("The monster attacks " + player.name + " for " + monsterDamage + " damage.");

                    monsterHealth -= playerDamageTaken;
                    player.takeDamage(playerDamageTaken);

                    if (monsterHealth <= 0) {
                        System.out.println("You defeated the monster!");
                        player.gold += 10;
                        System.out.println("You gained 10 gold.");
                    }

                    break;
                case 2:
                    System.out.println(player.name + " tries to run away.");
                    if (random.nextDouble() < 0.5) {
                        System.out.println("You successfully escaped!");
                        return;
                    } else {
                        System.out.println("Escape failed. You must continue fighting.");
                        player.takeDamage(monsterDamage);
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");
            }
        }
    }

    static void endGame(Player player) {
        System.out.println("Thanks for playing, " + player.name + "!");
        System.out.println("Final stats:");
        System.out.println("Health: " + player.health);
        System.out.println("Gold: " + player.gold);
    }
}
