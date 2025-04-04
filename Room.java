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
public class Room 
{
    //Q6
    private String description;
    private HashMap<String,Room> exits;
    
   /**
      * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }
    //Q6//Q7//Q8
   public Room getExit(String direction){
     Room nextRoom = exits.get(direction);
     return nextRoom;
   }
   //Q11
   public String getLongDescription(){
        return "You are" + description + ".\n"+ getExitString();
   }
   
      /** * Return a description of the room’s exits, 
    * for example, "Exits: north west". 
     * @return A description of the available exits. 
    */ 
   //Q10
    public String getExitString(){
        String exitString = "Exits: ";
        for(String direction : exits.keySet()){
           exitString += (direction + "");
        }
        return exitString;
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
    
     
     // Q8
   public void setExit(String direction, Room neighbor) {
       exits.put(direction, neighbor);
   }

   /**
     * @return The description of the room.
     */
    public String getDescription()
   {
        return description;
   }

}
