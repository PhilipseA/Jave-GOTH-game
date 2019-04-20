/**
 * This class handles in-game timers.
 * timer  - the current value of the timer
 * update - what to change the update time with
 * low    - when to return that the timer is being low
 * 
 * @author  Adra Philipse
 * @version 1.11.2017
 */
 
public class Timer {
    private int timer;
    private int update;
    private int low;
    
    /**
     * Constructor for the class Timer
     */
    public Timer(int defaultTime, int defaultUpdate, int defaultLow) 
    {
        timer = defaultTime;
        update = defaultUpdate;
        low = defaultLow;
    }

    /**
     * ---------------------------------------------------------------------------
     *                              Accessor methods
     * ---------------------------------------------------------------------------
     */     
    
    /**
     * prints out the timer.
     * @return String with the timer.
     */
    public String toString() {
        return Integer.toString(timer);
    }   

    /**
     * Check if the timer is low
     * @return True or False.
     */
    public boolean isLow() 
    {
        if (timer <= low) 
        {
            return true;
        }
        return false;
    }
    
    /**
     * Check if the timer reached zero.
     * @return True or False.
     */
    public boolean hasExpired() 
    {
        if (timer <= 0) 
        {
            return true;
        }
        return false;
    }    
    
    /**
     * ---------------------------------------------------------------------------
     *                              (Setters) Mutator methods
     * ---------------------------------------------------------------------------
     */     
    
    /**
     * Set a new new timer.
     * @param new time.
     */
    public void setTime(int time) 
    {
        timer = time;
    }
    
    /**
     * Set an update for the current timer.
     * @param new time.
     */
    public void setUpdate(int update) 
    {
        this.update = update;
    }
    
    /**
     * Set a low time for the timer.
     * @param time for low time.
     */
    public void setLow(int low) 
    {
        this.low = low;
    } 

    /**
     * ---------------------------------------------------------------------------
     *                              Mutator methods
     * ---------------------------------------------------------------------------
     */ 
    
    /**
     * updates the timer
     */
    public void updateTimer() 
    {
        timer += update;
    }
}
