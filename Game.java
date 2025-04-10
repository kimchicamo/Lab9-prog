import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    //Q20
    private Inventory inventory;
    //Q21,23
    private Stack<Room> roomHistory;
    // Stack to keep track of the visited rooms
    //  
    /**
     * Create the game and initialise its internal map.
     * سازنده کلاس. بازی رو راه‌اندازی می‌کنه و اتاق‌ها رو می‌سازه.          
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
        inventory = new Inventory(); 
        roomHistory = new Stack<>(); 
    }

    /**
     * Create all the rooms and link their exits together.
     * اتاق‌ها رو تعریف و به هم وصل می‌کنه.           
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // initialise room exits Q8
        
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        
        
        theater.setExit("west", outside);
        
        pub.setExit("east",outside);
        
        lab.setExit("north",outside);
        lab.setExit("east", office);
        
        office.setExit("west",lab);

        currentRoom = outside;  // start game outside
        
        outside.addItem(new Item("map"));
        theater.addItem(new Item("projector"));
    }

    /**
     *  Main play routine.  Loops until end of play.
     *  حلقه‌ی اصلی بازی. تا وقتی کاربر «quit» نزده بازی رو ادامه میده و دستورات رو می‌خونه و اجرا می‌کنه.         
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    //Q5
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }
   
    //Q6,5
       //اطلاعات اتاق فعلی رو چاپ می‌کنه.           
    public void printLocationInfo(){
       System.out.println( "You are " + currentRoom.getDescription());
       System.out.println(currentRoom.getExitString());
      System.out.println(currentRoom.getItemDescription());
    }
    

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     * دستوراتی که کاربر وارد می‌کنه رو بررسی و اجرا می‌کنه.          
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        //Q15
        else if (commandWord.equals("look")) {
            System.out.println("Looking Around...");
        }
        //Q20
        else if (commandWord.equals("take")) {
            takeItem(command);
        }
        else if (commandWord.equals("drop")) {
            dropItem(command);
        }
        //Q23
        else if (commandWord.equals("back")) {
            back();
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
        System.out.println(parser.showAllCommands());
    }
    //
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * اگر مسیر معتبر باشه، بازیکن رو به اتاق جدید میبره.          
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        //Q6
        Room nextRoom = currentRoom.getExit(direction);
        //Q5
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            roomHistory.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }
    //Q23
    private void back() {
        if (roomHistory.isEmpty()) {
            System.out.println("You are at the starting point. No room to go back to!");
        } else {
            currentRoom = roomHistory.pop();
            printLocationInfo();
        }
    }
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    //Q20
    private void takeItem(Command command) {
        if (command.hasSecondWord()) {
            String itemName = command.getSecondWord();
            if (currentRoom.hasItem(itemName)) {
                Item item = currentRoom.removeItem(itemName);
                inventory.addItem(item);
                System.out.println("You took the " + itemName + ".");
            } else {
                System.out.println("There is no " + itemName + " here.");
            }
        } else {
            System.out.println("Take what?");
        }
    }
    //Q20
     private void dropItem(Command command) {
        if (command.hasSecondWord()) {
            String itemName = command.getSecondWord();
            if (inventory.hasItem(itemName)) {
                Item item = inventory.removeItem(itemName);
                currentRoom.addItem(item);
                System.out.println("You dropped the " + itemName + ".");
            } else {
                System.out.println("You don't have that item.");
            }
        } else {
            System.out.println("Drop what?");
        }
    }
    //Q21
     private void goBack() {
        if (!roomHistory.isEmpty()) {
            currentRoom = roomHistory.pop(); // Pop the last room from the stack
            printLocationInfo();
        } else {
            System.out.println("You can't go back. This is the starting point.");
        }
    }
}

