import java.util.*;

/**
 * class Item - This class makes items for the game.
 *
 * @author Adra Philipse
 * @version 10.11.2017
 */
public class Item
{
    private double weight;
    private String name;
    private String description;
    private boolean lootable;
    private boolean equipable;
    private String use; 
    private Room roomLock;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, double weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        lootable = true;
        use = null;
        roomLock = null;
    }

    /**
     * ---------------------------------------------------------------------------
     *                              Accessor methods
     * ---------------------------------------------------------------------------
     */    

    /**
     * Returns the locked room where the item fits on.
     * @return The locked room.
     */
    public Room getRoomLock() 
    {
        return roomLock;
    }

    /**
     * Returns the use of an item.
     * @return String containing the use.
     */
    public String getUse() {
        return use;
    }
    
    /**
     * @return the weight of the item.
     */
    public double getWeight() 
    {
        return weight;
    }

    /**
     * Returns the name an item.
     * @return String with the name.
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Returns the description of an item.
     * @return String with the description.
     */
    public String getDescription()
    {
        return name + " - " + description + ". grams: " + weight;
    }
    
    /**
     * Check if the item can be equiped.
     * @return True or False.
     */
    public boolean isEquipable() 
    {
        return equipable;
    }
     
    /**
     * Check if the player can pick up the item.
     * @return True or False.
     */
    public boolean isLootable() 
    {
        return lootable;
    }

    /**
     * ---------------------------------------------------------------------------
     *                              (Setters) Mutator methods
     * ---------------------------------------------------------------------------
     */
    
    /**
     * Set a purpose for an item
     * @param effect The effect given to the item
     */
    public void setUse(String usage) 
    {
        use = usage;
    }
    
    /**
     * Set if the item can be equiped.
     * @param True or False
     */
    public void setEquipable(boolean condition) 
    {
        equipable = condition;
    }
    
    /**
     * Set if a player can pick up the item.
     * @param True or False.
     */
    public void setLootable(boolean condition) 
    {
        lootable = condition;
    }
    
    /**
     * Set an item usage for a locked room.
     * @param The locked room where it can be used.
     */
    public void setRoomLock(Room roomLock) 
    {
        this.roomLock = roomLock;
    }             
  
}
