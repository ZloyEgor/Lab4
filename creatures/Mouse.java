package creatures;

import enums.Incident;
import enums.Mood;
import teller.Teller;

public class Mouse extends Creature{

    public Mouse(Teller teller) {
        super(teller);
        name = "Mouse";
        teller.tell(updateMood(Mood.UPSET));
        teller.tell(updateIncident(Incident.WET));
    }

    public void sigh(){
        teller.tell(name + " is sighing");
        teller.tell(updateMood(Mood.UPSET));
    }


}
