import java.util.ArrayList;
import java.util.Scanner;

//class manages the items available for purchase in the game and allows the player to buy them using their in-game gold.
public class Shop {
    private final ArrayList<Item> items;
    private final String shopName;
    private int itemNumber;

    //constructor method for the Shop class, which takes a shopName parameter and initializes an empty ArrayList for the items in the shop.
    public Shop(String shopName) {
        this.shopName = shopName;
        this.items = new ArrayList<>();
        addItem(new Item("Bananer +20 HP", 10));
        addItem(new Item("Pistol", 30));
        addItem(new Item("Katana", 50));
        addItem(new Item("Shield full HP", 60));
        addItem(new Item("Kram +5 HP", 0));
    }

    //This method returns the index of the item that the player has selected to purchase.
    public int getItemNumber() {
        return itemNumber;
    }

    //This method adds a new item to the items list.
    public void addItem(Item item) {
        items.add(item);
    }

    //This method displays all the available items in the shop, along with their prices and the player's current gold.
    public void displayItems(Player player1) {
        System.out.println("Welcome to " + shopName + "!");
        System.out.println("Your gold: " + player1.getGold());
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + 1 + ". " + items.get(i).getName() + " - " + items.get(i).getPrice() + " gold");
        }
    }

    //This method allows the player to purchase an item from the shop.
    // It prompts the player to enter the number of the item they want to purchase, checks if they have enough gold to make the purchase,
    // and deducts the price of the item from their gold if they do.
    // It also sets the itemNumber variable to the index of the item they purchased.
    public void purchaseItem(Player player1) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of the item you want to purchase or 0 to exit");
        if (GameLoop.checkIfInteger(scanner)) {// Will make sure the input is 'int' and will not crash if user inputs was a letter.
            int choice = scanner.nextInt();
            if (choice == 0) {
                System.out.println("Exiting shop");
            } else if (choice > 0 && choice <= items.size()) {
                Item selectedItem = items.get(choice - 1);
                if (player1.getGold() >= selectedItem.getPrice()) {
                    player1.setGold(player1.getGold() - selectedItem.getPrice());
                    System.out.println("You purchased " + selectedItem.getName() + " for " + selectedItem.getPrice() + " gold.");
                    itemNumber = choice - 1;
                } else {
                    System.out.println("You don't have enough gold to purchase this item.");
                }
            } else {
                System.out.println("Invalid choice. Exiting shop...");
            }
        } else {
            System.out.println("invalid input. Exiting shop...");
        }
    }
}

