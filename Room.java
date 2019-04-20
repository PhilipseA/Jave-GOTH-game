import java.util.*;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "GOTH" application. 
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Adra Philipse
 * @version 10.11.2017
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Boolean> roomLocks;
    private HashMap<String, Item> items;
    private HashMap<String, Character> characters;
    private boolean travel;
    private boolean trap;

    /**
     * Constructor for the class room.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashMap<String, Item>();
        roomLocks = new HashMap<String, Boolean>();
        characters = new HashMap<String, Character>();
    }

    /**
     * ---------------------------------------------------------------------------
     *                              Accessor methods
     * ---------------------------------------------------------------------------
     */ 
    
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Returns the character name.
     * @param the name were looking for.
     * @return The character's name
     */
    public Character getCharacter(String name) 
    {
        return characters.get(name);
    }

    /**
     * Return the travel location.
     * @return True or False.
     */
    public boolean getTravelLocation()
    {
        return travel;
    }

    /**
     * Return the trap location.
     * @return True of False.
     */
    public boolean getTrap()
    {
        return trap;
    }

    /**
     * Returns an item the exits is the room
     * @param itemName The name of the item that should be returned
     * @return The item!
     */
    public Item getItem(String itemName) 
    {
        return items.get(itemName);
    }

    /**
     * Return the description of a room.
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Return the name of a exit that is connected with a given neighbour
     * @param neighbour the neigbour to be looked for
     * @return the name of the exit that connects this room with the neighbour, 
     * null if no conenction is found
     */
    public String getAllExits(Room neighbour) 
    {
        Set<String> exitKey = exits.keySet(); 
        for(String exitName : exitKey)
        {
            if (exits.get(exitName) == neighbour)
            {
                return exitName;
            }
        }
        return null;
    }

    /**
     * Return a description of the room's exist,
     * for example, "Exits: north west".
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit: keys) 
        {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * Return a long description of this room, of the form:
     *  You are in the kitchen.
     *  Exits: north west
     *  
     * @return A description of the room, including exits.
    */
    public String getLongDescription()
    {   
        String returnString;
        
        returnString = "Location :" + description + "."; 
        returnString +="\n" + getItemDescription();
        returnString +="\n\n"+ getCharacterDescription();
        returnString +="\n\n"+ getExitString();
        returnString +="\n\n"+"---------------------------------";
        return returnString;
    }
    
    /**
     * Returns a String with all the items in the room.
     * @return A string with all items and their description.
     */
    public String getItemDescription()
    {
        String itemString = "\nItems:";
        if(!items.isEmpty())
        {
            Set<String> itemNames = items.keySet(); 
            for(String itemName : itemNames)
            {
                itemString += "\n  " + getItem(itemName).getDescription();
            }
            return itemString;
        } else {
            return itemString + "\n No items...";  
        }
    }
    
    /**
     * Returns a String with all the characters in the room.
     * @return A string all characters.
     */
    public String getCharacterDescription()
    {
        String charactersString = "People:";
        if(!characters.isEmpty())
        {           
            Set<String> characterNames = characters.keySet(); 
            for(String characterName : characterNames)
            {
                charactersString += "\n  " + getCharacter(characterName).getName();
            }
            return charactersString;
        } else {
            return charactersString + "\n There are no people here...."; 
        }
    }
    
    /**
     * Checks if the player can loot this item.
     * @param the item to check.
     * @return True of False.
     */
    public boolean isItemLootable(Item item)
    {
        return item.isLootable();
    }    
    
    /**
     * Return if an exit is locked.
     * @param the direction.
     * @return True or False.
     */
    public boolean isLocked(String direction) {
        if (direction!=null)
        {
            return roomLocks.get(direction).booleanValue();
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
     * Set the current room as a travel location for the train.
     * @param True or False.
     */
    public void setTravelLocation(boolean condition)
    {
        travel = condition;
    }

    /**
     * Set the current room as a trap.
     * @param True or False.
     */
    public void setTrap(boolean condition)
    {
        trap = condition;
    }
     
    /**
     * Define the exits from this room.
     * to another room or is null (no exit there).
     * @param direction The direction of the exit.
     * @param neighbor The room in the fiven diretion.
     */
    public void setExits(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
        roomLocks.put(direction, false);
    }

    /**
     * ---------------------------------------------------------------------------
     *                              Mutator methods
     * ---------------------------------------------------------------------------
     */  

    /**
     * Locks or unlocks an exit.
     * @param exitś direction.
     * @param True locked, False unlocked.
     */
    public void lockExit(String direction, boolean condition) 
    {
        roomLocks.put(direction, condition);
    }

    /**
     * Adds an item to the room. (This function is needed if the item is already made and added to a room, and the player drops it).
     * @param item to be added.
     */
    public void addItem(Item item) 
    {
        items.put(item.getName(), item);
    }

    /**
     * Adds a character to a room.
     * @param the character's name.
     * @param the character's dialogue
     */
    public void addCharacter(String name, String dialogue) 
    {
        characters.put(name, new Character(name, dialogue));
    }

    /**
     * Adds an item to the room.
     * @param name of the item.
     * @param description of the item.
     * @param weight of the room.
     */
    public void addItem(String name, String description, double weight)
    {
        items.put(name, new Item(name, description, weight));
    }
    
    /**
     * Removes an item from the room.
     * @param the item to be removed.
     */
    public void removeItem(String item) 
    {
        items.remove(item);
    }
}
