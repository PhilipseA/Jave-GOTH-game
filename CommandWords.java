import java.util.HashMap;

/**
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Adra Philipse
 * @version 2008.03.30
 */

public class CommandWords
{
    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) 
        {
            if(command != CommandWord.UNKNOWN) 
            {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) 
        {
            return command;
        } else {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * Returns a String with all the commands
     */
    public String getCommandList() 
    {
        String returnString="";

        for(String command : validCommands.keySet()) 
        {
            returnString += command + " ";

        }

        return returnString;
    }    
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

}
