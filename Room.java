import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room {
    private String description;
    private HashMap<String, Room> exits;
    private Inventory items;  
    
    public Room(String description) {
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new Inventory();  
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getDescription() {
        return description;
    }

    public String getExitString() {
        String exitString = "Exits: ";
        for (String direction : exits.keySet()) {
            exitString += direction + " ";
        }
        return exitString.trim();
    }

    
    public void addItem(Item item) {
        items.addItem(item);
    }
    //Q31
    public Item removeItem(String itemName) {
        return items.removeItem(itemName);
    }
   
    public String getItemsList() {
        return items.getItemsList();  
    }

    
    public Room getExit(String direction) {
        return exits.get(direction);
    }
}
    
    // /**
     // * Define the exits of this room.  Every direction either leads
     // * to another room or is null (no exit there).
     // * @param north The north exit.
     // * @param east The east east.
     // * @param south The south exit.
     // * @param west The west exit.
     // */
    // public void setExits(Room north, Room east, Room south, Room west) 
    // {
        // if(north != null) {/
            // exits.put("north",north);
        // }
        // if(east != null) {
            // exits.put("east",east);
        // }
        // if(south != null) {
            // exits.put("south",south);
        // }
        // if(west != null) {
            // exits.put("west",west);
        // }
     // }
    
     


   
   

