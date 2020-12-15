package events;

import creatures.*;
import exceptions.KeyCharactersNotFoundException;
import teller.Teller;
import java.util.ArrayList;

/**
 * Feast in wonderland
 */
public class Feast implements Event{
    /**
     * @param alice - Alice
     * @param dodo - Dodo
     * @param mouse - Mouse
     * @param teller - implementation of Teller Interface
     * @param participants - ArrayList of all participants
     */
    private Alice alice;
    private Dodo dodo;
    private Mouse mouse;
    private final Teller teller;
    private ArrayList<Creature> participants;

    /**
     * @brief constructor
     * @param teller - implementation of Teller Interface
     * @param needToInitializeParticipants - do we need to initialize participants?
     */
    public Feast(Teller teller, boolean needToInitializeParticipants) {
        this.teller = teller;
        if(needToInitializeParticipants)
            initializeParticipants(teller);
    }

    @Override
    public void initializeParticipants(Teller teller) {
        alice = new Alice(teller);
        mouse = new Mouse(teller);
        dodo = new Dodo(teller);
        participants = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            if (i < 2)
                participants.add(new LargeBird(teller));
            else
                participants.add(new SmallBird(teller));
        }
        participants.add(alice);
        participants.add(mouse);
        participants.add(dodo);
        if (participants.get(1).hashCode() == participants.get(2).hashCode()){
            ((Bird)participants.get(1)).setDifferentName(participants.get(2).getName());
        }
        if (participants.get(3).hashCode() == participants.get(4).hashCode()){
            ((Bird)participants.get(3)).setDifferentName(participants.get(4).getName());
        }
    }

    @Override
    public void performEvent(String introduction) {
        try {
            teller.tell(introduction);
            for (Creature creature : participants) {
                creature.takePartInFeast();
                if (creature instanceof SmallBird)
                    ((SmallBird) creature).takePartInFeast(alice);

            }
        }
        catch (NullPointerException e){
            throw new KeyCharactersNotFoundException("Key character is not initialized!", e);
        }
    }


    @Override
    public void setParticipants(ArrayList<Creature> participants) {
        this.participants = participants;
        for(Creature creature : participants){
            if (creature instanceof Alice)
                alice = (Alice) creature;
            else if(creature instanceof Dodo)
                dodo = (Dodo) creature;
            else if(creature instanceof Mouse)
                mouse = (Mouse) creature;
        }
    }

    @Override
    public ArrayList<Creature> getParticipants() {
        return participants;
    }

    @Override
    public String getInformationAboutEvent() {
        return "Feast in the Wonderland";
    }

    @Override
    public int hashCode() {
        return 17 * alice.hashCode() * dodo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Feast)){
            return false;
        }
        Feast feast = (Feast) obj;

        return feast.participants.equals(this.participants);
    }

    @Override
    public String toString() {
        return getInformationAboutEvent();
    }
}
