package creatures;

import enums.Incident;
import enums.Mood;
import teller.Teller;

/**
 * Abstract class of wonderland birds
 */
public abstract class Bird extends Creature{

    /**
     * @param nameArray - array of allowed names
     */
    protected String[] nameArray;

    /**
     * @brief Birds are serious by default and have no incident.
     */
    public Bird(Teller teller){
        super(teller);
        mood = Mood.SERIOUS;
        incident = Incident.NONE;
    }

    /**
     * @brief The method sets a new name for the bird, which is different from the unwantedName
     * @param unwantedName - name that should be changed
     */
    public void setDifferentName(String unwantedName){
        do{
            name = nameArray[(int) (Math.random() * nameArray.length)];
        } while(name.matches(unwantedName));
    }

    @Override
    public String toString() {
        return  "Bird from Wonderland" +
                "\nName: " + getName() +
                "\nType: " + this.getClass().getName() +
                "\nEnums.Mood: " + mood.getDescription() +
                "\nWhat incident happened: " + incident.getDescription();
    }
}