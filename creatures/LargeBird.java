package creatures;

import enums.Incident;
import enums.Mood;
import teller.Teller;

/**
 * Class of Large Birds from Wonderland
 */
public class LargeBird extends Bird {

    /**
     * @param teller - implementation of Teller Interface
     * @brief By default, the name of the bird is chosen randomly from the proposed array of names
     */

    public LargeBird(Teller teller) {
        super(teller);
        nameArray = new String[]{"Goose", "Eagle", "Duck", "Flamingo", "Macaw", "Owl", "Magpie", "Raven"};
        name = nameArray[(int) (Math.random() * nameArray.length)];
    }

    @Override
    public void takePartInFeast() {
        teller.tell(getName() + " took part in the feast");
        incident = Math.random() <= 0.75 ? Incident.COMPLAINING : Incident.SQUEAL;
        teller.tell(updateMood(Mood.OUTRAGED));
        teller.tell(updateIncident(incident));
    }

    @Override
    public int hashCode() {
        int result = 13;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof LargeBird)){
            return false;
        }
        LargeBird bird = (LargeBird) obj;

        return (bird.mood == mood) && (getName().matches(bird.getName())) && (incident == bird.incident);
    }
}
