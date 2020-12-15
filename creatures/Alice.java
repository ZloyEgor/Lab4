package creatures;

import enums.Incident;
import enums.Mood;
import exceptions.BoxOfCandiesIsEmptyException;
import teller.Teller;

/**
 * Class of Alice from Wonderland
 */
public final class Alice extends Creature{
    /**
     * @param doesHaveTheThimble - does Alice have the thimble?
     * @param boxOfCandies - Alice's box of candies
     */
    private boolean doesHaveTheThimble;
    public BoxOfCandies boxOfCandies;

    /**
     * @brief By default, Alice is wet and upset
     * @param teller - implementation of Teller Interface
     */
    public Alice(Teller teller){
        super(teller);
        name = "Alice";
        teller.tell(updateMood(Mood.UPSET));
        teller.tell(updateIncident(Incident.WET));
        doesHaveTheThimble = true;
        boxOfCandies = new BoxOfCandies();
    }

    /**
     * @brief Alice gives thimble to Dodo
     * @param dodo - Dodo, which receives thimble from Alice
     */
    public void giveThimble(Dodo dodo){
        if(doesHaveTheThimble){
            dodo.receiveThimble(this);
            teller.tell(name + " gave thimble to " + dodo.getName());
            doesHaveTheThimble = false;
        }
    }

    /**
     * @brief Alice takes the thimble from Dodo
     * @param dodo - Dodo, from which Alice takes the thimble
     */
    public void receiveThimble(Dodo dodo){
        if(!doesHaveTheThimble) {
            dodo.giveThimble(this);
            doesHaveTheThimble = true;
            doesHaveAPrize = true;
            teller.tell(name + " received thimble from " + dodo.getName());
            teller.tell(updateMood(Mood.SERIOUS));
            teller.tell(name + " bowed with thankfulness");
            teller.tell(updateMood(Mood.GRATEFUL));
        }
    }

    public String isInterested(boolean isInterested){
        return  name + " is " + (isInterested? "" : "not ") + "interested";
    }

    /**
     * @brief Alice gives candies to some Creature
     * @param creature - Creature that will receive candies
     * @param numberOfCandies - amount of candies that will be given to creature
     */
    public void giveCandy(Creature creature, int numberOfCandies){
        try {
            boxOfCandies.takeOutCandy(numberOfCandies);
            teller.tell(name + " gave a candy to " + creature.getName());
            teller.tell(creature.updateMood(Mood.GLAD));
            creature.receivePrize(this, boxOfCandies.nameOfCandies);
        }
        catch (BoxOfCandiesIsEmptyException e){
            teller.tell(name + " has no longer any candies");
            teller.tell(creature.updateMood(Mood.UPSET));
        }
    }

    @Override
    public void listenToTheStory(Creature creature) {
        if (creature instanceof Mouse) {
            teller.tell("While Mouse was telling a tale, " + name + " was thinking about " + creature.getName() + " tail:");
            teller.tell("                    `Fury said to a\n" +
                    "                   mouse, That he\n" +
                    "                 met in the\n" +
                    "               house,\n" +
                    "            \"Let us\n" +
                    "              both go to\n" +
                    "                law:  I will\n" +
                    "                  prosecute\n" +
                    "                    YOU.  --Come,\n" +
                    "                       I'll take no\n" +
                    "                        denial; We\n" +
                    "                     must have a\n" +
                    "                 trial:  For\n" +
                    "              really this\n" +
                    "           morning I've\n" +
                    "          nothing\n" +
                    "         to do.\"\n" +
                    "           Said the\n" +
                    "             mouse to the\n" +
                    "               cur, \"Such\n" +
                    "                 a trial,\n" +
                    "                   dear Sir,\n" +
                    "                         With\n" +
                    "                     no jury\n" +
                    "                  or judge,\n" +
                    "                would be\n" +
                    "              wasting\n" +
                    "             our\n" +
                    "              breath.\"\n" +
                    "               \"I'll be\n" +
                    "                 judge, I'll\n" +
                    "                   be jury,\"\n" +
                    "                         Said\n" +
                    "                    cunning\n" +
                    "                      old Fury:\n" +
                    "                     \"I'll\n" +
                    "                      try the\n" +
                    "                         whole\n" +
                    "                          cause,\n" +
                    "                             and\n" +
                    "                        condemn\n" +
                    "                       you\n" +
                    "                      to\n" +
                    "                       death.\"'");
            teller.tell(updateMood(Mood.THINKING));
        }
    }

    @Override
    public String toString(){
        return  "Alice from Wonderland" +
                "\nHas the thimble: " + doesHaveTheThimble +
                "\nEnums.Mood:" + mood.getDescription();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + mood.getDescription().hashCode();
        result = 31 * result + Integer.hashCode(doesHaveTheThimble ? 12 : 5);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Alice)){
            return false;
        }
        Alice alice = (Alice) obj;
        return (alice.doesHaveTheThimble == doesHaveTheThimble) && (alice.mood == mood);
    }

    /**
     * Class of Alice's Box with candies
     */
    private static class BoxOfCandies {
        /**
         * @param amountOfCandies - amount of candies which contains the box
         * @param nameOfCandies - descriptive name of candies
         */
        private int amountOfCandies = 6;
        public final String nameOfCandies = "Candied fruit";

        /**
         * @brief get candies out of the box
         * @param numberOfCandies - amount of candies carried in the box
         * @throws BoxOfCandiesIsEmptyException - if amount of candies becomes less than 0, method throws BoxOfCandiesIsEmptyException
         */
        public void takeOutCandy(int numberOfCandies) throws BoxOfCandiesIsEmptyException {
            amountOfCandies -= numberOfCandies;
            if(amountOfCandies < 0) throw new BoxOfCandiesIsEmptyException("Error: The number of sweets in the box cannot be negative, current amount: " + amountOfCandies);
        }
    }
}
