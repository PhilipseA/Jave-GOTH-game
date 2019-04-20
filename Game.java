import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *  This class is the main class of the "GOTH" application. 
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Adra Philipse
 * @version 10.11.2017
 */

public class Game 
{
    /**
     * Class method to start the game.
     */
    public static void main (String[] args) 
    { 
        Game game = new Game();
        game.play();
    }

    private Parser parser;
    private Player player;
    private Timer timer;
    String playerName;
    private ArrayList<Room> rooms;
    private Scanner reader = new Scanner(System.in);

    /**
     * Constructor of the class game.
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        rooms = new ArrayList<Room>();
        createRooms();
        parser = new Parser();
        timer = new Timer(60, -1, 10);        
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room school, busyStreets, cafe, cityRiver, cityTrainStation, home, 
        countrySide, adjacentCityCentre, cinema, gouvermentHall, judgeRoom,
        theLongMountainPath, nearForest, forestHouse, forest, yourRoom, curiousHouse, curiousRoom, curiousBasement;

        school = new Room("(School) The school which I attend together with my freind. It is after school time.");
        busyStreets = new Room("(Busy streets) The streets are filled with people going home or to work.");
        cafe = new Room("(The Café) The café I visit with my freind regulary. Most people in my neighbourhood gather here.");
        cityRiver = new Room("(City's river) The calming city river, I come here from time to time to think.");
        cityTrainStation = new Room("(City's train station) This train station is the busiest in town, maybe I could stop by home.");
        home = new Room("(Home) Home is where the hearth is. It doesn't seem my parents are home yet.");
        countrySide = new Room("(The country side) The countryside is a beautiful calm environment.");
        adjacentCityCentre = new Room("(Nagiso-cho) This is the city next to ours, I'd say the population here is more dense.");
        cinema = new Room("(Nagiso-cho Cinema) I frequented this cinema with my parents when I was younger.");
        gouvermentHall = new Room("(Nagiso-cho gouverment hall) The gouverment hall. This is where the criminals get trialled.");
        judgeRoom = new Room("(Judges room) This is where it all ends, the final judgement.");
        theLongMountainPath = new Room("(The long mountain path) The long mountain path leads up into the forest.");
        nearForest = new Room("(Near forest area) The area around the thick forest. This is where the flashy lady usually walks by.");
        forestHouse = new Room("(Forest house) This is where the old man lives. He has a shed full of working tools.");
        forest = new Room("(Forest) A beautiful forest.");
        yourRoom = new Room("(Your room) All my personal belongings are here.");
        curiousHouse = new Room("(Curious house) Navigating through the place you find pictures of past victims on the wall.");
        curiousRoom = new Room("(Curious room) This was a mistake! Curiousity killed me....");
        curiousBasement = new Room("(Curious basement) The air is heavy in here...");

        rooms.add(school);
        rooms.add(busyStreets);
        rooms.add(cafe);
        rooms.add(cityRiver);
        rooms.add(cityTrainStation);
        rooms.add(home);
        rooms.add(countrySide);
        rooms.add(adjacentCityCentre);
        rooms.add(cinema);
        rooms.add(gouvermentHall);
        rooms.add(judgeRoom);
        rooms.add(theLongMountainPath);
        rooms.add(nearForest);
        rooms.add(forestHouse);
        rooms.add(forest);
        rooms.add(yourRoom);
        rooms.add(curiousHouse);
        rooms.add(curiousRoom);
        rooms.add(curiousBasement);

        curiousRoom.setTrap(true);
        cityTrainStation.setTravelLocation(true);

        school.setExits("south", busyStreets);
        school.addCharacter("Freind", "Have you seen the news about the serial killer?!");

        busyStreets.setExits("north", school);
        busyStreets.setExits("west", theLongMountainPath);
        busyStreets.setExits("east", cafe);
        busyStreets.setExits("south", cityRiver);
        busyStreets.addCharacter("Loan-Shark", "Hey are you interested in a business oppurtunity? (I should just better ignore this person).");

        cafe.setExits("west", busyStreets);
        cafe.addItem("Sketchbook", "This seems to contain the next strike areas of the murderer..", 5);
        cafe.addCharacter("Sketchy-student", "No... I know nothing... (The student seems rather scared).");
        cafe.addCharacter("Cafe-manager", "You want your usual cup of coffee?");

        theLongMountainPath.setExits("west", nearForest);
        theLongMountainPath.setExits("east", busyStreets);
        theLongMountainPath.addItem("Torn-dress-cloth", "This looks like a piece of women's clothing... Maybe itś form the killer?", 1);
        theLongMountainPath.getItem("Torn-dress-cloth").setUse("showcloth");
        theLongMountainPath.getItem("Torn-dress-cloth").setRoomLock(judgeRoom);

        countrySide.setExits("west", adjacentCityCentre);
        countrySide.setExits("north", cityTrainStation);
        countrySide.setExits("south", curiousHouse);
        countrySide.lockExit("south", true); 
        countrySide.addCharacter("Farmer", "Hey, there! You here to visit my son who works at the café?");

        curiousHouse.setExits("north", countrySide);
        curiousHouse.setExits("up", curiousRoom);
        curiousHouse.setExits("down", curiousBasement);

        curiousRoom.setExits("down", curiousHouse);
        curiousRoom.addCharacter("Killer", "...");

        curiousBasement.setExits("up", curiousHouse);
        curiousBasement.addItem("Inconclusive-evidence", "I should show this to the judge!", 5);
        curiousBasement.getItem("Inconclusive-evidence").setUse("showtojudge");
        curiousBasement.getItem("Inconclusive-evidence").setRoomLock(judgeRoom);

        nearForest.setExits("east", theLongMountainPath);
        nearForest.setExits("north", forest);
        nearForest.setExits("south", forestHouse);
        nearForest.addItem("rustyKey", "I wonder where this key is from..", 2);
        nearForest.getItem("rustyKey").setUse("opencurioushouse");
        nearForest.getItem("rustyKey").setRoomLock(countrySide);
        nearForest.addCharacter("Forest-Guard", "Yes, Can I help you?");
        nearForest.addCharacter("Flashy-Lady", "A serial murder? That's terrible! Oh, I do hope that person leaves me and my children alone.. (the woman seems rather jumpy).");

        forest.setExits("south", nearForest);
        forest.addItem("Corpse", "Oh-no, This seem to be the recent victim of the murderer. Her hand was severed just like the others.", 670);
        forest.getItem("Corpse").setLootable(false);
        forest.addItem("Weird-Tool", "I wonder if this is something that can point out who the murderer is...", 3);
        forest.getItem("Weird-Tool").setUse("showtoguard");
        forest.getItem("Weird-Tool").setRoomLock(nearForest);

        forestHouse.setExits("north", nearForest);
        forestHouse.addCharacter("Old-man", "You kids should just leave this business to the police! Go away. (The man seems very aggressive).");
        forestHouse.addItem("Bloody-axe", "Is that... human blood?!", 5);
        forestHouse.getItem("Bloody-axe").setUse("showaxe");
        forestHouse.getItem("Bloody-axe").setRoomLock(judgeRoom);

        cityRiver.setExits("north", busyStreets);
        cityRiver.setExits("south", cityTrainStation);
        cityRiver.addCharacter("Fisherman", "After one of the women got killed around here, I barely see any students riding by here. What has the world come to?");
        forestHouse.addItem("Student-pass", "This seems to be the student pass of that sketchy student. But why is it here?...", 1);
        forestHouse.getItem("Student-pass").setUse("showpass");
        forestHouse.getItem("Student-pass").setRoomLock(judgeRoom);
        
        cityTrainStation.setExits("north", cityRiver);
        cityTrainStation.setExits("east", home);
        cityTrainStation.setExits("south", countrySide);
        cityTrainStation.addCharacter("Conductor", "If you get a train ticket you can travel back to a previous visited location.");
        cityTrainStation.addItem("Trainticket", "A train ticket to use the train.", 1);
        cityTrainStation.getItem("Trainticket").setUse("beamer");

        home.setExits("west", cityTrainStation);
        home.setExits("up", yourRoom);

        yourRoom.setExits("down", home);
        yourRoom.addItem("backpack", "This is a nice traveling bag", 10);
        yourRoom.getItem("backpack").setEquipable(true);
        yourRoom.getItem("backpack").setUse("backpack");

        adjacentCityCentre.setExits("north", cinema);
        adjacentCityCentre.setExits("east", countrySide);
        adjacentCityCentre.setExits("south", gouvermentHall);
        adjacentCityCentre.addCharacter("Jun", "Hey man...  I really like Aki... but I really don't what to do... Should I tell her? (He shouldn't ask me for advice...)");

        cinema.setExits("south", adjacentCityCentre);
        cinema.addCharacter("Aki", "Hey there! How are you? I'm fine by the way! I'm going to watch a movie.");

        gouvermentHall.setExits("north", adjacentCityCentre);
        gouvermentHall.setExits("south", judgeRoom);
        gouvermentHall.addCharacter("Police-Officer", "If you have some `evidence` you say you have, than USE it in court.");

        judgeRoom.setExits("north", gouvermentHall);
        judgeRoom.addCharacter("Judge", "What are you doing in my courtroom?");

        player = new Player(school);

    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        boolean finished = false;
        while (! finished) 
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("\nThank you "+playerName+" for playing GOTH.  Good bye.");
    }

    /**
     * Prints the Welcome messeges
     */
    private void printWelcome()
    {
        System.out.println("What is your name?");
        playerName = reader.nextLine();        
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("      HGOTH         OTHGOT   THGOTHGOTHGO HGOTH   HGOTHG   ");
        System.out.println("     OTHGOTHGOT    HGOTHGOT  OTHGOTHGOTHG THGOT   THGOT    ");
        System.out.println("    HGOT  OTHG    OTHG  HGOT GOT  OTH  TH  THG     THG     ");
        System.out.println("   OTHG    OTH    GOT    HGO      GOT      OTH     OTH     ");
        System.out.println("   GOT           THGO    THGO     HGO      GOT     GOT     ");
        System.out.println("   HGO           OTHG    OTHG     TH       HGOTHGOTHGO     ");
        System.out.println("   THG   GOTHGO   OTH    GOT      OTH      THGOT G THG     ");
        System.out.println("    TH    GOTHG   GOT    HGO      GOT      OTH     OTH     ");
        System.out.println("    OTH   HGOT    HGOT  OTHG      HGO      GOT     GOT     ");
        System.out.println("     OTHGOTHG      HGOTHGOT     GOTHGOT   THGOT   THGOT    ");
        System.out.println("      OTHGOTHG      HGOTHG      HGOTHGO   OTHGO   OTHGO    ");
        System.out.println("-----------------------------------------------------------");
        System.out.println("\nWelcome " + playerName + " to the game GOTH.");
        System.out.println("\nA serial murderer is on the loose in your hometown.");
        System.out.println("So far he has taken the lives of 3 women, displaying them as a form of art in open spaces.");
        System.out.println("You decide to catch the killer with the evidence you find.");
        System.out.println("\nInspired by a Japanese horror novel written by Otsuichi");
        System.out.println("\nThe murderer will strike again in "+timer+" minutes, you need to find the murderer before that happens.");
        System.out.println("\nType '" + CommandWord.HELP + "' if you need help.");
        System.out.println("\n-----------------------------------------------------------");
        System.out.println(player.getCurrentRoom().getLongDescription());

    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        boolean updateTimer = true;
        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) 
        {
            System.out.println("\nI don't know what you mean...");
            updateTimer = false;
            return false;
        }

        if (commandWord == CommandWord.HELP)
        {
            updateTimer = false;
            printHelp();
        }
        else if (commandWord == CommandWord.GO)
        {
            goRoom(command);
        }
        else if (commandWord == CommandWord.LOOK)
        {
            look();
        }
        else if (commandWord == CommandWord.TAKE)
        {
            take(command);
        }
        else if (commandWord == CommandWord.DROP)
        {
            drop(command);
        }
        else if (commandWord == CommandWord.USE)
        {
            use(command);
        }
        else if (commandWord == CommandWord.TALKTO)
        {
            talk(command);  
        }
        else if (commandWord == CommandWord.EQUIP)
        {
            equip(command);
        }
        else if (commandWord == CommandWord.ITEMS)
        {
            showInventory();
        }
        else if (commandWord == CommandWord.BACK)
        {
            back();
        }
        else if (commandWord == CommandWord.SETTRAIN)
        {
            useTrain();
        }
        else if (commandWord == CommandWord.USETRAINTICKET)
        {
            use(new Command(commandWord, "Trainticket"));   
        }
        else if (commandWord == CommandWord.TIME)
        {
            System.out.println("\nThe murderer strike again in "+timer+"s.");
        }
        else if (commandWord == CommandWord.QUIT)
        {
            updateTimer = false;
            wantToQuit = quit(command);
        }

        if (updateTimer) 
        {
            timer.updateTimer();
            if (timer.hasExpired()) 
            {
                System.out.println("\nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
                System.out.println("Time's up... another victim has fallen pray to this maniac");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                wantToQuit = true;
            } else if (timer.isLow()) {
                System.out.println("\nI need to hurry up...");
                System.out.println("\nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
                System.out.println("The murderer strikes again in "+timer+" minutes.");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            }
        }
        return wantToQuit;
    }

    /**
     * Print out some help information.
     * Some basic information, number of rounds left to play and valid commands.
     */
    private void printHelp() 
    {
        System.out.println("\nYou don't know what the next step is... You are lost.");
        System.out.println("\nYour command words are:");
        System.out.println(parser.getAllCommands());
    }

    /**
     * ---------------------------------------------------------------------------
     *                              Command methods
     * ---------------------------------------------------------------------------
     */    

    /**
     * Sets the train for a location to return to. (variation of the beamer method).
     * If the player has a train ticket he can set the train to an location.
     */
    private void useTrain() {
        if (!player.checkInventory("Trainticket")) 
        {
            System.out.println("You dont have the Trainticket!");
            return;
        }
        player.setTravelRoom(player.getCurrentRoom());
        System.out.println("You can return to this location with the train.");        
    }

    /**
     * Uses an item in the users inventory.
     * This method is for the command use.
     * @param useableItem The item to be used
     */
    private void use(Command useableItem) 
    {
        if(!useableItem.hasSecondWord()) {
            System.out.println("Use what exactly?");
            return;
        }

        String item = useableItem.getSecondWord();

        if (!player.checkInventory(item)) 
        {
            System.out.println("I don't have that item..");
            return;
        }

        if (player.getItem(item).getUse()!=null)
        {
            if (player.getItem(item).getRoomLock()== player.getCurrentRoom())
            {
                if (player.getItem(item).getUse().equals("opencurioushouse"))
                {
                    player.getCurrentRoom().lockExit("south", false);
                    System.out.println("The curious house door opens with a shrieking sound...");
                    return;
                }
                else if (player.getItem(item).getUse().equals("showtoguard"))
                {
                    player.getCurrentRoom().getCharacter("Forest-Guard").addItemToInventory(player.getItem(item));
                    player.removeItemFromInventory(item);
                    player.getCurrentRoom().getCharacter("Forest-Guard");
                    System.out.println("Forest-Guard: That looks like a tool for a coffee maker.");
                    return;
                }
                else if (player.getItem(item).getUse().equals("showtojudge"))
                {
                    player.getCurrentRoom().getCharacter("Judge").addItemToInventory(player.getItem(item));
                    player.removeItemFromInventory(item);
                    player.getCurrentRoom().getCharacter("Judge");
                    System.out.println("\nJudge: With this evidence in hand, we can finally catch the killer.");
                    System.out.println("\nOfcourse... the sketchbook was found in the Café and he could meet all his victims there.. He was the murderer...");
                    System.out.println("\nCongratulations you caught the murderer!");
                    System.out.println("\nThank you "+playerName+" for playing GOTH.  Good bye.");
                    try        
                    {
                        Thread.sleep(8000);
                    } 
                    catch(InterruptedException ex) 
                    {
                        Thread.currentThread().interrupt();
                    }
                    System.exit(0);                  

                }
                else if (player.getItem(item).getUse().equals("showcloth"))
                {
                    player.getCurrentRoom().getCharacter("Judge").addItemToInventory(player.getItem(item));
                    player.removeItemFromInventory(item);
                    player.getCurrentRoom().getCharacter("Judge");
                    System.out.println("\nJudge: You say you found this near the area that two other women got killed?");
                    System.out.println("\nThe flashy Lady seemed to kidnap children because she lost her own, I did catch a criminal but not the serial murderer.");
                    System.out.println("\nAlas, you caught a criminal but not the right one, better luck next time.");
                    System.out.println("\nThank you "+playerName+" for playing GOTH.  Good bye.");
                    try        
                    {
                        Thread.sleep(8000);
                    } 
                    catch(InterruptedException ex) 
                    {
                        Thread.currentThread().interrupt();
                    }
                    System.exit(0);                  

                }
                else if (player.getItem(item).getUse().equals("showaxe"))
                {
                    player.getCurrentRoom().getCharacter("Judge").addItemToInventory(player.getItem(item));
                    player.removeItemFromInventory(item);
                    player.getCurrentRoom().getCharacter("Judge");
                    System.out.println("\nJudge: What are you doing with that axe!? POLICE, POLICE!");
                    System.out.println("\nAfter a long explanation on how I got the axe, I seemed to have wronged the old man. It wasn't blood but paint...");
                    System.out.println("\nAlas, you have wronged an innocent person and got yourself caught. You lost.");
                    System.out.println("\nThank you "+playerName+" for playing GOTH.  Good bye.");
                    try        
                    {
                        Thread.sleep(8000);
                    } 
                    catch(InterruptedException ex) 
                    {
                        Thread.currentThread().interrupt();
                    }
                    System.exit(0);                  

                } 
                else if (player.getItem(item).getUse().equals("showpass"))
                {
                    player.getCurrentRoom().getCharacter("Judge").addItemToInventory(player.getItem(item));
                    player.removeItemFromInventory(item);
                    player.getCurrentRoom().getCharacter("Judge");
                    System.out.println("\nJudge: You say you found this pass near one of the areas a victim got killed?");
                    System.out.println("\nThe student had mental problems because of seclusion, but he was not the murderer.");
                    System.out.println("\nAlas, you have wronged an innocent person. You lost.");
                    System.out.println("\nThank you "+playerName+" for playing GOTH.  Good bye.");
                    try        
                    {
                        Thread.sleep(8000);
                    } 
                    catch(InterruptedException ex) 
                    {
                        Thread.currentThread().interrupt();
                    }
                    System.exit(0);                  

                } 
            }
            else if (player.getItem(item).getUse().equals("beamer")) 
            {
                if (player.getTravelRoom()==null) 
                {
                    System.out.println("You cannot travel with the train at this moment, you need a train ticket.");
                    return;
                }
                player.useTrain();
                System.out.println("You used the " + item + ".");
                System.out.println(player.getCurrentRoom().getLongDescription());
                return;

            }    
        }

        System.out.println("Nothing happens....");

    }

    /**
     * A method to talk to a character and get it's dialogue.
     * @param command character to interact with.
     */
    private void talk(Command interactableCharacter) 
    {
        if(!interactableCharacter.hasSecondWord()) 
        {
            System.out.println("Talk to who?");
            return;
        }

        String character = interactableCharacter.getSecondWord();

        Room currentRoom = player.getCurrentRoom();
        Character characterToTalkTo = currentRoom.getCharacter(character);

        if (characterToTalkTo==null) 
        {
            System.out.println("There is no such person.");
            return;
        } else {
            System.out.println(characterToTalkTo.getDialogue());
        }
    }

    /**
     * Method to pick up an item from a room.
     * @param command Item to pick up.
     */
    private void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }

        String item = command.getSecondWord();

        Room currentRoom = player.getCurrentRoom();
        Item itemToBePicked = currentRoom.getItem(item);

        if (itemToBePicked==null) 
        {
            System.out.println("I don't see such an item around here...");
            return;
        }

        if (!currentRoom.isItemLootable(itemToBePicked)) {
            System.out.println("I shouldn't take this with me...");
            return;
        }

        if (player.getCarriedWeight() + itemToBePicked.getWeight() > player.getMaxWeight())
        {
            System.out.println("I can't carry any more than this, maybe I should drop something...");
            return;
        }

        currentRoom.removeItem(item);
        player.addItemToInventory(itemToBePicked);

        System.out.println("You picked up the "+ item);
    }

    /**
     * Removes an item from the players inventory, places the object in the current room.
     * @param command The item thats going to be removed.
     */
    private void drop(Command command) 
    {
        if(!command.hasSecondWord()) 
        {
            System.out.println("Drop what?");
            return;
        } 

        String item = command.getSecondWord();
        Room currentRoom = player.getCurrentRoom();

        if (!player.checkInventory(item)) 
        {
            System.out.println("There is no such item in my inventory...");
            return;
        }

        currentRoom.addItem(player.getItem(item)); 
        player.removeItemFromInventory(item);               
        System.out.println("You dropped " + item);    
    }

    /**
     * Shows everything in the players inventory.
     */
    private void showInventory() 
    {
        System.out.println(player.getInventoryDescription());
    }

    /**
     * Equip an item in the players inventory.
     * @param command item to be equiped.
     */
    private void equip(Command command) 
    {
        if(!command.hasSecondWord()) 
        {
            System.out.println("Equip what?");
            return;
        }

        String item = command.getSecondWord();

        if (!player.checkInventory(item)) 
        {
            System.out.println("You don't have this item in my inventory...");
            return;
        }

        if (!player.getItem(item).isEquipable()){
            System.out.println("I can't equip this...");
            return;
        }

        if (player.getItem(item).getUse()!=null)
        {
            if (player.getItem(item).getUse().equals("backpack"))
            {
                player.extraInventorySpace(20);
                player.removeItemFromInventory(item);
                System.out.println("You carry the bag on you back.");

                return;
            }
        }

        System.out.println("\nNow I can carry more weight.");

    }

    /**
     * This method returns the location once again.
     */
    private void look() 
    {
        System.out.println("\nYou look around once again...");
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Returns the player back one room.
     */
    private void back() 
    {    
        if (player.returnPreviousRoom())
        {         
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
        else
        {
            System.out.println("You cant go back from here...");
        }
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * @param coomand The chossen exit 
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) 
        {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room roomToLeave = player.getCurrentRoom();
        Room nextRoom = roomToLeave.getExit(direction);

        if (nextRoom == null) 
        {
            System.out.println("There is no path that way...");
        }
        else if (roomToLeave.isLocked(direction))
        {
            System.out.println("\nThere is a curious house south, but the door is locked... I can't go in...");
        } else {
            player.move(nextRoom);

            if (player.getCurrentRoom().equals(rooms.get(17)))
            {            
                System.out.println(player.getCurrentRoom().getLongDescription());
                System.out.println("\nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
                System.out.println("You encountered the murderer! You quickly run away.");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                player.trap(rooms.get(0));
            }

            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @param Command the Quit command.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

}
