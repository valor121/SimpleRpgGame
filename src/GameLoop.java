import java.util.Random;
import java.util.Scanner;

public class GameLoop {

    // create a player object
    Player player1 = new Player("Player", 1, 100, 0, 0, 0, 0, 0, 0);
    // create a Monster object
    Monster monster1 = new Monster("Monster", 1, 100, 10, 0, 0, 0, 0);
    // create a Shop object
    Shop shop = new Shop("The Shop");
    // create a Weapon object
    Weapon weapon = new Weapon();

    // class to run the game.
    GameLoop() {
        firstMenu();
    }

    // This method checks if the user input is an integer or not.
    // It returns a boolean value indicating whether the input is an integer or not.
    public static boolean checkIfInteger(Scanner scan) {
        return scan.hasNextInt();
        // changed from boolean  /robin
        // itIs = scan.hasNextInt();
        // return itIs;
        //Will limit the input to int.
    }

    // This method displays the main menu of the game and prompts the user to enter a choice.
    // Depending on the user's choice, it either starts the game, displays the player's details, or exits the game.
    // If the user enters an invalid choice, the method prompts the user again.
    public void firstMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Game!");
        System.out.println("1> Go adventuring");
        System.out.println("2> Show details about your character");
        System.out.println("3> Exit the Game");

        if (checkIfInteger(scan)) {// Will make sure the input is 'int' and will not crash if user inputs was a letter.
            int input = scan.nextInt();

            if (input == 1) {
                System.out.println("You see nothing but swaying grass all around you... \nEnter your name to Continue:");
                String name = scan.next();
                player1.setName(name);

                System.out.println("You are now " + player1.getName() + "!");
                updateGameBoard();
                battle(player1, monster1);

            } else if (input == 2) {
                System.out.println("Name: " + player1.getName());
                System.out.println("Level: " + player1.getLevel());
                System.out.println("HP: " + player1.getHP());
                System.out.println("Attack: " + player1.getAttack());
                System.out.println("Defend: " + player1.getDefend());
                System.out.println("Gold: " + player1.getGold());
                System.out.println("XP: " + player1.getXP());
                firstMenu();
            } else if (input == 3) {
                System.out.println("Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid input!");
                firstMenu();
            }
        } else {
            System.out.println("Invalid input");
            firstMenu();
        }
    }

    //This method displays the game board and prompts the user to move the player.
    // The game board is represented by a 2D array of characters.
    //The user can move the player by pressing 'w', 'a', 's', or 'd' keys.
    // If the player encounters the monster, the battle() method is called.
    public void updateGameBoard() {

        System.out.println("Press W/S/D/A to move or E to exit!");

        Random rand = new Random();
        //random monster spawn
        int monsterX = rand.nextInt(1,7);
        int monsterY = rand.nextInt(1,11);
        /*
        *----------- Y
        |
        |
        |
        X
        */
        int playerX = 5;
        int playerY = 5;


        while (true) {

            char[][] gameBoard = {
                    {'*', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '*'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                    {'*', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '*'}};

            for (int x = 0; x < gameBoard.length; x++) {
                for (int y = 0; y < gameBoard[x].length; y++) {
                    if (x == playerX && y == playerY) {
                        System.out.print('P');
                    } else if (x == monsterX && y == monsterY) {
                        System.out.print('M');
                    } else {
                        System.out.print(gameBoard[x][y]);
                    }
                }
                System.out.println();
            }
            Scanner scan = new Scanner(System.in);
            String input = scan.next();

            //regex to check for w,a,s,d,e inputs
            if (input.equalsIgnoreCase("w")) {
                playerX--;
            } else if (input.equalsIgnoreCase("s")) {
                playerX++;
            } else if (input.equalsIgnoreCase("a")) {
                playerY--;
            } else if (input.equalsIgnoreCase("d")) {
                playerY++;
            } else if (input.equalsIgnoreCase("e")) {
                System.out.println("Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid input!");
            }
            if (playerX == monsterX && playerY == monsterY) { //if player reaches monster
                System.out.println("You have encountered a monster!");
                battle(player1, monster1);
                updateGameBoard();
            }

            if (playerX == 0 || playerX == 8 || playerY == 0 || playerY == 13) { //if player reaches edge of map
                System.out.println("You have reached the edge of the map!");
                updateGameBoard();
            }
        }
    }

    // prints out a list of available weapons for the player to choose from.
    // Then, it calls the choseWeapon() method on the weapon object,
    // which prompts the player to input the number of the weapon they want to use and prints out the name and damage of the weapon they chose.
    private void choseWeapon(Player player1) {

        weapon.displayWeapons();
        weapon.choseWeapon();
        player1.setAttack(weapon.getDamage());
    }

    // This method represents the battle between the player and the monster.
    // It first displays the details of the player and the monster.
    // Then, it prompts the user to choose an action, such as attacking or using an item.
    // Depending on the user's choice, it either attacks the monster or uses an item.
    // If the player defeats the monster, the player earns gold and XP.
    // If the player dies, the game ends.
    public void battle(Player player1, Monster monster1) {
        if (player1.getHP() >= 0 && monster1.getHP() >= 0) {
            System.out.println("You are fighting a " + monster1.getName());
            System.out.println("Your HP: " + player1.getHP());
            System.out.println("Monster HP: " + monster1.getHP());
            System.out.println("What do you want to do?");
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            System.out.println("3. Shop");

            Scanner scan = new Scanner(System.in);

            if (checkIfInteger(scan)) { // Will make sure the input is int and will not crash if user inputs was a letter.

                int choice = scan.nextInt();
                while (choice == 1) {
                    choseWeapon(player1);

                    if (player1.getAttack() > monster1.getDefend()) {
                        monster1.setHP(monster1.getHP() - player1.getAttack());
                        player1.setGold(player1.getGold() + player1.getAttack() - 10);
                        if (monster1.getHP() < 0) {
                            break;
                        }
                        System.out.println("You hit the monster for " + player1.getAttack() + " damage!");
                        System.out.println("The monster is at " + monster1.getHP() + " HP!");
                        System.out.println("You got " + player1.getGold() + " Gold");
                        break;
                    }
                }
                if (monster1.getHP() <= 0) {
                    System.out.println("You killed the monster!");
                    System.out.println("You got " + player1.getGold() + " Gold");
                    player1.setGold(player1.getGold() + 5);
                    monster1.setName("Bigger monster");
                    monster1.setHP(300);
                    monster1.setAttack(50);
                    monster1.setLevel(2);
                    player1.setLevel(2);
                    System.out.println("Now your fighting " + monster1.getName());
                }
                while (choice == 2) {
                    if (monster1.getAttack() >= player1.getDefend()) {
                        player1.setHP(player1.getHP() - monster1.getAttack());
                        if (player1.getHP() < 0) {
                            break;
                        }
                        System.out.println("The monster hit you for " + monster1.getAttack() + " damage!");
                        System.out.println("You are at " + player1.getHP() + " HP!");
                        break;
                    }
                }
                if (player1.getHP() <= 0) {
                    System.out.println("You died!");
                    System.out.println("Game Over!");
                    System.exit(0);
                }
                if (choice == 3) {
                    shop.displayItems(player1);
                    shop.purchaseItem(player1);
                    manageItem(shop, weapon);
                }
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    // method that manages a player's interactions with a shop, where they can purchase items or weapons.
    public void manageItem(Shop shop, Weapon weapon) {
        int choice = shop.getItemNumber();

        if (choice == 0) {
            if (player1.getHP() + 20 > 100) {
                player1.resetHP();
                System.out.println("Your HP is " + player1.getHP());
            } else {
                player1.setHP(player1.getHP() + 20);
                System.out.println("Your HP is " + player1.getHP());
            }
        }
        if (choice == 1) {
            weapon.addWeapon("Pistol", 35);
        }
        if (choice == 2) {
            weapon.addWeapon("Katana", 40);
        }
        if (choice == 3) {
            player1.resetHP();
            System.out.println("Your HP is " + player1.getHP());
        }
        if (choice == 4) {
            if (player1.getHP() + 20 > 100) {
                player1.resetHP();
                System.out.println("Your HP is " + player1.getHP());
            } else {
                player1.setHP(player1.getHP() + 5);
                System.out.println("Your HP is " + player1.getHP());
            }
        }
    }
}
