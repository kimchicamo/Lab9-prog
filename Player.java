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

    public Player(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        this.inventory = new Inventory();
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

    public void addItem(Item item) {
        inventory.addItem(item);
    }

    public Item removeItem(String itemName) {
        return inventory.removeItem(itemName);
    }

    public boolean hasItem(String itemName) {
        return inventory.hasItem(itemName);
    }

    public String getInventoryList() {
        return inventory.getItemsList();
    }
}