import java.util.ArrayList;

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
//Q28
public class Player {
    private String name;
    private Room currentRoom;
    private Inventory inventory;
    private int maxWeight;
    private int currentWeight;


     public Player(String name, Room startingRoom, int maxWeight) {
        this.name = name;
        this.currentRoom = startingRoom;
        this.inventory = new Inventory();
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public Inventory getInventory() {
        return inventory;
    }
    //Q31
    public boolean canCarry(Item item) {
        return currentWeight + item.getWeight() <= maxWeight;
    }
    //31
    public void addItem(Item item) {
         if (canCarry(item)) {
            inventory.addItem(item);
            currentWeight += item.getWeight();
        } else {
            System.out.println("This item is too heavy! You can't carry it.");
        }
    }
    //31
    public Item removeItem(String itemName) {
        Item removedItem = inventory.removeItem(itemName);
        if (removedItem != null) {
            currentWeight -= removedItem.getWeight();
        }
        return removedItem;
    }

    public boolean hasItem(String itemName) {
        return inventory.hasItem(itemName);
    }

    public String getInventoryList() {
        return inventory.getItemsList() + " (Total weight: " + currentWeight + "/" + maxWeight + ")";
    }
}