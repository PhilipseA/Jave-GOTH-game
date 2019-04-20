/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author Adra Philipse
 * @version 11.11.2017
 */
public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), 
    LOOK("look"), EQUIP("equip"), BACK("back"), TAKE("take"), 
    ITEMS("items"), DROP("drop"), SETTRAIN("settrain"), USETRAINTICKET("travel"), USE("use"), TALKTO("talkto"), TIME("time");    
       
    private String commandString;
    
    /**
     * Initialise with the corresponding command word.
     * @param commandWord The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
