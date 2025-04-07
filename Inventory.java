import java.util.HashMap;
/**
 * Write a description of class Inventory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Inventory {
    private HashMap<String, Item> items;
    //Q20,22
    public Inventory() {
        items = new HashMap<>();
    }

    // Add an item to the inventory
    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    // Remove an item from the inventory by its name
    public Item removeItem(String itemName) {
        return items.remove(itemName);
    }

    // Check if the inventory contains an item
    public boolean hasItem(String itemName) {
        return items.containsKey(itemName);
    }

    // Get a list of all items in the inventory
    public String getItemsList() {
        if (items.isEmpty()) {
            return "Your inventory is empty.";
        }
        String itemList = "Items in your inventory: ";
        for (String itemName : items.keySet()) {
            itemList += itemName + " ";
        }
        return itemList.trim();
    }
}