package events;

import creatures.*;
import enums.Mood;
import exceptions.KeyCharactersNotFoundException;
import teller.Teller;
import java.util.ArrayList;

/**
 * Tale of Mouse in wonderland
 */
public class TaleOfMouse implements Event{
    /**
     * @param alice - Alice
     * @param dodo - Dodo
     * @param mouse - Mouse
     * @param teller - implementation of Teller Interface
     * @param participants - ArrayList of all participants
     * @param audience - ArrayList of all listeners of story
     */
    private Alice alice;
    private Dodo dodo;
    private Mouse mouse;
    private final Teller teller;
    private ArrayList<Creature> participants;
    private ArrayList<Creature> audience;

    /**
     * @brief constructor
     * @param teller - implementation of Teller Interface
     * @param needToInitializeParticipants - do we need to initialize participants?
     */
    public TaleOfMouse(Teller teller, boolean needToInitializeParticipants) {
        this.teller = teller;
        if(needToInitializeParticipants)
            initializeParticipants(teller);
    }

    @Override
    public String getInformationAboutEvent() {
        return "Tale of Mouse in Wonderland";
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
        audience = new ArrayList<>(participants);
        audience.remove(mouse);
    }

    @Override
    public void performEvent(String introduction) {
        try {
            teller.tell(introduction);
            for (Creature creature : audience) {
                teller.tell(creature.getName() + " sat near Mouse and started asking for a story");
            }
            alice.sayPhrase("You promised to tell me your history, you know, why it is you hate--C and D");
            teller.tell(alice.updateMood(Mood.WARY));

            mouse.sayPhrase("Mine is a long and a sad tale!");
            teller.tell(alice.isInterested(true));
            mouse.sigh();
            alice.sayPhrase("It is a long tail, certainly, but why do you call it sad?");
            for (Creature creature : audience) {
                creature.listenToTheStory(mouse);
            }
            mouse.sayPhrase("You are not attending, Alice! What are you thinking of?");
            teller.tell(mouse.updateMood(Mood.RESENTMENT));
            alice.sayPhrase("I beg your pardon, you had got to the fifth bend, I think?");
        }
        catch (NullPointerException e){
            throw new KeyCharactersNotFoundException("Key character is not initialized!", e);
        }
    }

    @Override
    public ArrayList<Creature> getParticipants() {
        return participants;
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
        audience = new ArrayList<>(participants);
        audience.remove(mouse);
    }

    @Override
    public int hashCode() {
        return 16 * alice.hashCode() * dodo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof TaleOfMouse)){
            return false;
        }
        TaleOfMouse taleOfMouse = (TaleOfMouse) obj;

        return taleOfMouse.participants.equals(this.participants);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
