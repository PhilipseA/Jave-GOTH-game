import java.util.*;

/**
 * class Player - The player class, this is where all the players functions and properties are at.
 *
 * @author Adra Philipse
 * @version 10.11.2017
 */
public class Player
{    
    private Room currentRoom;
    private Room travelRoom;
    private Stack<Room> previousRooms;
    private HashMap<String, Item> inventory;
    private double maxWeight;
    private double carriedWeight;

    /**
     * Constructor for objects of class Player
     */
    public Player(Room currentRoom)
    {        
        this.currentRoom = currentRoom;
        travelRoom = null;
        previousRooms = new Stack<Room>();
        inventory = new HashMap<String, Item>();
        maxWeight = 10;
        carriedWeight = 0;
    }

    /**
     * ---------------------------------------------------------------------------
     *                              Accessor methods
     * ---------------------------------------------------------------------------
     */
    
    /**
     * @return The previous room.
     */
    public Room getPreviousRoom() 
    {
        return previousRooms.peek();
    }
    
    /**
     * This is the room for the beamer method.
     * @return The return room for the train
     */
    public Room getTravelRoom() 
    {
        return travelRoom;
    }

    /**
     * Returns the maximum carry weight of the player.
     * @return total weight what the player can carry.
     */
    public double getMaxWeight()
    {
        return maxWeight;
    }

    /**
     * This returns the room where the player is currently in.
     * @return The room that the player is currently in.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Returns an item from the players inventory
     * @param item Item to be returned
     * @return The item from the players inventory
     */
    public Item getItem(String item) 
    {
        return inventory.get(item);
    }

    /**
     * Calculates the total weight of the players inventory
     * @return The weight of the inventory
     */
    public double getCarriedWeight() 
    {
        double totalWeight = 0;
        if(!inventory.isEmpty())
        {
            Set<String> itemNames = inventory.keySet();
            for(String itemName : itemNames)
            {
                totalWeight += inventory.get(itemName).getWeight();
            }
        }
        return totalWeight;
    }
    
    /**
     * Returns an string with information about the inventory
     * @return String with the inventory details.
     */
    public String getInventoryDescription() 
    {
        String inventoryString = "Inventory:";
        if(!inventory.isEmpty())
        {

            Set<String> itemNames = inventory.keySet(); 
            for(String itemName : itemNames)
            {
                inventoryString += "\n  " + inventory.get(itemName).getDescription();
            }

            inventoryString += "\nYou are currently carrying " + getCarriedWeight() + " grams. You can carry a total of " + getMaxWeight() + "grams.";

            return inventoryString;
        } else {
            return inventoryString += "\nThere is nothing in your inventory.";  
        }
    }    

    /**
     * Check if the player has this item.
     * @param Name of the object. 
     * @return True or False.
     */
    public boolean checkInventory(String itemName)
    {
        return inventory.containsKey(itemName);
    }

    /**
     * Return player to the previous room.
     * @return True or False.
     */
    public boolean returnPreviousRoom()
    {
        if (!previousRooms.empty())
        {
            Room previousRoom = previousRooms.peek();
            String exit = currentRoom.getAllExits(previousRoom);            

            if (currentRoom.isLocked(exit))
            {
                System.out.println("The door back here is closed.. I can't go back there.");
                return true;
            }

            currentRoom = previousRooms.pop();          
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * ---------------------------------------------------------------------------
     *                              (Setters) Mutator methods
     * ---------------------------------------------------------------------------
     */
    
    /** 
     * Set the Room for the train (this is my variation on the beamer function).
     * @param The room where the train can return to.
     */
    public void setTravelRoom(Room travelRoom) 
    {
        this.travelRoom = travelRoom;
    }

    /**
     * ---------------------------------------------------------------------------
     *                              Mutator methods
     * ---------------------------------------------------------------------------
     */    
    
    /**
     * Places item in the players inventory.
     * @param The item thats going to be added.
     */
    public void addItemToInventory(Item item) 
    {
        inventory.put(item.getName(), item);
    }
    
    /**
     * Removes an item from the players inventory.
     * @param The item thats going to be removed.
     */
    public void removeItemFromInventory(String item) {
        inventory.remove(item);
    }

    /**
     * Adds extra inventory space for the player (item backpack).
     * @param The amount thats going to be added.
     */
    public void extraInventorySpace(double extraSpace)
    {
        this.maxWeight += extraSpace;
    }    
    
    /**
     * Move to the next room (Also adds the next room to the Stack).
     * @param The next room.
     */
    public void move(Room nextRoom) {   
        previousRooms.push(currentRoom);
        currentRoom = nextRoom;
    }
    
    /**
     * Use the train to travel. (Beamer method)
     */
    public void useTrain() {
        previousRooms.clear();
        currentRoom = travelRoom;
    }

    /**
     * This room is a trap which returns the player to the beginning.
     * @param The trap room.
     */
    public void trap(Room trapRoom)
    {
        currentRoom = trapRoom;
        previousRooms.clear();
    }

}
