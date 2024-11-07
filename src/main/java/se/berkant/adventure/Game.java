package se.berkant.adventure;

import se.berkant.adventure.Resident;
import se.berkant.adventure.Burglar;

import java.util.Scanner;

public class Game {
    private Resident resident;
    private Burglar burglar;
    private boolean running;
    private boolean fryingPanFound;

    public Game() {
        resident = new Resident();
        burglar = new Burglar();
        running = true;
        fryingPanFound = false;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You wake up in the living room, hearing a noise in your house...");

        while (running && resident.isConscious()) {
            System.out.println("Choose a room to enter: kitchen, bedroom, hall, office");

            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "kitchen":
                    enterKitchen();
                    break;
                case "bedroom":
                    enterBedroom();
                    break;
                case "hall":
                    enterHall();
                    break;
                case "office":
                    enterOffice();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

    private void enterKitchen() {
        if (!fryingPanFound) {
            System.out.println("You found a frying pan! Your damage has increased.");
            resident.addDamage(3);
            fryingPanFound = true;
        } else {
            System.out.println("The kitchen is empty. Nothing left to find here.");
        }
    }

    private void enterBedroom() {
        System.out.println("The bedroom is quiet and empty.");
    }

    private void enterHall() {
        System.out.println("A burglar is here! A fight ensues...");
        fight();
    }

    private void enterOffice() {
        if (burglar.isConscious()) {
            System.out.println("You see a phone, but you're too stressed to think of calling...");
        } else {
            System.out.println("You remember to call the police now that the burglar is down. You've won!");
            running = false;
        }
    }

    private void fight() {
        while (resident.isConscious() && burglar.isConscious()) {
            resident.punch(burglar);
            if (burglar.isConscious()) {
                burglar.punch(resident);
            }
        }

        if (resident.isConscious()) {
            System.out.println("You defeated the burglar! You can now go to the office and call the police.");
        } else {
            System.out.println("You were defeated by the burglar. Game over.");
            running = false;
        }
    }
}
