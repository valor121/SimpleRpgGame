import java.util.ArrayList;
import java.util.Scanner;

public class Weapon {
    ArrayList<String> name;
    ArrayList<Integer> damage;
    private int choice;

    public Weapon() {
        damage = new ArrayList<>();
        name = new ArrayList<>();
        addWeapon("Axe", 15);
        addWeapon("Bow", 20);
        addWeapon("Tomato", 25);
        addWeapon("Sword", 30);
    }

    //This method adds a new weapon to the name and damage ArrayLists.
    public void addWeapon(String name, int damage) {
        this.name.add(name);
        this.damage.add(damage);
    }

    //This method returns the damage of the currently selected weapon based on the value of choice.
    public int getDamage() {
        return damage.get(choice - 1);
    }

    //This method displays the list of available weapons by iterating over the name and damage ArrayLists
    // and printing their contents to the console.
    public void displayWeapons() {//Print the list of weapons.
        System.out.println("Available weapons: ");
        for (int i = 0; i < name.size(); i++) {
            System.out.println(i + 1 + ". " + name.get(i) + " - " + damage.get(i) + " damage");
        }
    }

    //This method prompts the user to choose a weapon by entering a number.
    // It uses Scanner to read the input, checks if it's a valid integer, and stores the choice in the choice variable.
    // If the choice is valid, it displays the name and damage of the selected weapon.
    public void choseWeapon() {

        System.out.println("Enter the number of the weapon you want to use!");
        Scanner scanner = new Scanner(System.in);
        if (GameLoop.checkIfInteger(scanner)) { // Will make sure the input is 'int' and will not crash if user inputs was a letter.
            choice = scanner.nextInt();
            if (choice > 0 && choice <= name.size()) {
                System.out.println("You chose " + name.get(choice - 1) + " for " + damage.get(choice - 1) + " damage.");
            } else {
                System.out.println("Invalid choice. Exiting...");
            }
        } else {
            System.out.println("invalid input. Exiting...");
        }
    }
}
