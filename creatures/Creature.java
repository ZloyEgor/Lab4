package creatures;

import enums.Incident;
import enums.Mood;
import teller.Teller;

/**
 * Interface of creature from Wonderland
 */
public abstract class Creature {

    /**
     * @param name - name
     * @param mood - mood, state of mind
     * @param incident - incidents, physical condition of the bird
     * @param teller - implementation of Teller Interface
     * @param doesHaveAPrize - Does the creature have a prize?
     * @param rewarmingThreshold - a number showing how long creature needs to run to keep warm in caucus-race
     * @param currentPosition - position of creature in caucus-race
     */
    protected String name;
    protected Mood mood;
    protected Incident incident;
    protected final Teller teller;
    protected boolean doesHaveAPrize = false;
    private int rewarmingThreshold;
    private int currentPosition;

    /**
     * @brief Get the name of the creature
     * @return - creature name
     */
    public Creature(Teller teller){
        this.teller = teller;
    }


    /**
     * @brief get a string describing the new mood of the creature
     * @param mood - new mood of the creature
     * @return - string describing the new mood of the creature
     */
    public String updateMood(Mood mood){
        this.mood = mood;
        return name + this.mood.getDescription();
    }

    /**
     * @brief get a string describing the new Incident of the creature
     * @param incident - new incident of the creature
     * @return - string describing the new incident of the creature
     */
    public String updateIncident(Incident incident) {
        this.incident = incident;
        return name + incident.getDescription();
    }

    /**
     * @brief Taking part in a feast
     */
    public void takePartInFeast(){
        teller.tell(name + " took part in the feast");
        teller.tell(updateMood(Mood.GLAD));
    }

    /**
     * @brief receiving a prize
     * @param creature - creature that gave a prize
     * @param nameOfPrize - what is the name of prize?
     */
    public void receivePrize(Creature creature, String nameOfPrize){
        teller.tell(name + " received a prize(" + nameOfPrize + ") from " + creature.getName());
        doesHaveAPrize = true;
    }

    /**
     * @brief standing in cycle for caucus-race
     * @param newPosition - position in cycle
     */
    public void standInCycle(int newPosition){
        teller.tell(name + " stood in a circle");
        rewarmingThreshold = 7;
        currentPosition = newPosition;
    }

    /**
     * @brief Taking part in caucus race
     */
    public void takePartInCaucusRace(int allowedDistance){

        class RunningExercise{
            private void run(int newPosition){
                rewarmingThreshold -= Math.abs(currentPosition - newPosition);
                currentPosition = newPosition;
                teller.tell(name + " is running in the circle");
            }
            private void stop(){
                rewarmingThreshold += 1;
                teller.tell(name + " is stopped in the circle");
            }
            public void execute(){
                if (Math.random() < 0.7)
                    run((int)(Math.random()*(allowedDistance+1)));
                else
                    stop();
                if(rewarmingThreshold <= 0 && incident == Incident.WET) {
                    incident = Incident.NONE;
                    teller.tell(updateMood(Mood.PANTING));
                    teller.tell(name + " is now warmed and dried!");
                }
            }
        }

        RunningExercise exercise = new RunningExercise();
        exercise.execute();
    }

    /**
     * Creature says an exact phrase
     * @param phrase - String that represent a phrase
     */
    public void sayPhrase(String phrase){
        teller.tell(name + " said: \"" + phrase + "\"");
    }

    /**
     * Creature points at another creature
     * @param creature - creature pointed at
     */
    public void pointAt(Creature creature){
        teller.tell(name + " pointed at " + creature.getName());
    }

    /**
     * @brief Listening to the story of another creature
     */
    public void listenToTheStory(Creature creature){
        teller.tell(name + " is listening to a tale of " + creature.getName());
        teller.tell(updateMood(Mood.GLAD));
    }
    public String getName(){
        return name;
    }
}