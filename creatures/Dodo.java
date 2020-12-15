package creatures;

import enums.Incident;
import enums.Mood;
import teller.Teller;
import java.util.ArrayList;

/**
 * Class of Dodo from Wonderland
 */
public final class Dodo extends LargeBird {
    /**
     * @param doesHaveTheThimble - does Dodo have the thimble?
     */
    private boolean doesHaveTheThimble;

    /**
     * @brief By default, Dodo is serious, has no thimble
     * @param teller - implementation of Teller Interface
     */
    public Dodo(Teller teller) {
        super(teller);
        name = "Dodo";
        doesHaveTheThimble = false;
    }

    /**
     * @brief Dodo gives Alice thimble
     * @param alice - Alice which receive the thimble from Dodo
     */
    public void giveThimble(Alice alice){
        if(doesHaveTheThimble) {
            doesHaveTheThimble = false;
            teller.tell(getName() + " gave thimble to " + alice.getName());
        }
    }

    /**
     * Dodo receives thimble from Alice
     * @param alice - Alice, from which Dodo takes the thimble
     */
    public void receiveThimble(Alice alice){
        if(!doesHaveTheThimble){
            doesHaveTheThimble = true;
            teller.tell(getName() + " received thimble from " + alice.getName());
        }
    }

    /**
     * Class of circle drawn by Dodo
     */
    private class DodoCircle{
        /**
         * @param circleDiameter - diameter of circle
         */
        private final int circleDiameter = (int)(Math.random()*10) + 15;

        /**
         * @brief getter of circleDiameter
         * @return value of circleDiameter
         */
        public int getCircleDiameter(){
            return circleDiameter;
        }
    }

    /**
     * @brief Dodo arranges CaucusRace
     * @param participants - participants of CaucusRace
     */
    public void arrangeCaucusRace(ArrayList<Creature> participants){
        DodoCircle circle = new DodoCircle();
        teller.tell(name + " drew a circle on the ground.");
        for(Creature participant: participants)
            participant.standInCycle((int)(Math.random()* circle.getCircleDiameter()));
        sayPhrase("Let's run as you like!");
        while(participants.stream().anyMatch((t -> t.incident != Incident.NONE)))
            for(Creature participant: participants){
                participant.takePartInCaucusRace(circle.getCircleDiameter());
            }
        sayPhrase("The race is over!");
    }

    @Override
    public void takePartInFeast() {
        teller.tell(getName() + " took part in the feast");
        teller.tell(updateMood(Mood.GLAD));

    }

    @Override
    public int hashCode() {
        int result = 19;
        result = 31 * result + name.hashCode();
        result = 31 * result + mood.getDescription().hashCode();
        result = 31 * result + incident.getDescription().hashCode();
        result = 31 * result + Integer.hashCode(doesHaveTheThimble ? 13 : 3);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Dodo)){
            return false;
        }
        Dodo bird = (Dodo) obj;

        return (bird.mood == mood) && (getName().matches(bird.getName())) && (doesHaveTheThimble == bird.doesHaveTheThimble) && (incident == bird.incident);
    }

    @Override
    public String toString() {
        return  "Dodo from Wonderland" +
                "\nType: " + this.getClass().getSuperclass().getName() +
                "\nHas the thimble: " + doesHaveTheThimble +
                "\nEnums.Mood: " + mood.getDescription() +
                "\nWhat incident happened: " + incident.getDescription();
    }
}