import java.util.*;


/**
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Adra Philipse
 * @version 11.11.2017
 */
public class Parser 
{
    private CommandWords commands;  
    private Scanner reader;         
    
    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }
    
    /**
     * @return The next command from the user.
     */
    public Command getCommand() 
    {
        String inputLine;   
        String word1 = null;
        String word2 = null;    
    
        System.out.print("> "); 
    
        inputLine = reader.nextLine();
    
        Scanner tokenizer = new Scanner(inputLine);
    
        if(tokenizer.hasNext()) 
        {
            word1 = tokenizer.next(); 
            if(tokenizer.hasNext()) 
            {
                word2 = tokenizer.next(); 
            }
        }
        if (word1 != null)
        {
            word1 = word1.toLowerCase();
        }
    
        return new Command(commands.getCommandWord(word1), word2);
    }
    
    /**
     * @return string of all commands.
     */
    public String getAllCommands()
    {    
        return commands.getCommandList();
    }
}
