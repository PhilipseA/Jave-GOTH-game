import java.util.*; 

/**
 * class Character - this class is for all the interactible characters in the game
 *
 * @author Adra Philipse
 * @version 10.11.2017
 */
public class Character
{
    private String name;
    private String dialogue;
    private HashMap<String, Item> inventory;
    
    /**
     * Constructor for objects of class Character
     */
    public Character(String name, String dialogue)
    {
    	this.name = name;
    	this.dialogue = dialogue;
    	inventory = new HashMap<String, Item>();    
    }
    
    /**
     * ---------------------------------------------------------------------------
     *                              Accessor methods
     * ---------------------------------------------------------------------------
     */  
    
    /**
     * @return The name of the character
     */
    public String getName() 
    {
        return name;
    }
    
    /**
     * @return The dialogue of the character
     */
    public String getDialogue()
    {
        return name + ": '" + dialogue + "'";
    }
    
    /**
     * Returns an item in the characters inventory.
     * @param the item from the character.
     * @return The item from the characters inventory.
     */
    public Item getItem(String item) 
    {
    	return inventory.get(item);
    }

    /**
     * Checks if the item is in the inventory.
     * @param the item in the inventory.
     * @return True or False.
     */
    public boolean checkIventory(String itemName)
    {
    	return inventory.containsKey(itemName);
    }

    /**
     * ---------------------------------------------------------------------------
     *                              (Setters) Mutator methods
     * ---------------------------------------------------------------------------
     */    

    /**
     * Set a new dialogue for the character.
     * @param the new dialogue line.
     */
    public void setDialogue(String response)
    {
    	this.dialogue = response;
    }

    /**
     * ---------------------------------------------------------------------------
     *                              Mutator methods
     * ---------------------------------------------------------------------------
     */     
    
    /**
     * Add item to character's inventory.
     * @param the item thats going to be added.
     */
    public void addItemToInventory(Item item) 
    {
    	inventory.put(item.getName(), item);
    }
    
    /**
     * Removes the item from a character's inventory.
     * @param the item thats going to be removed.
     */
    public void removeItemFromInventory(String item) 
    {
    	inventory.remove(item);
    }
    
}
