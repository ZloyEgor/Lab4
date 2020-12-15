package events;

import creatures.*;
import enums.Mood;
import exceptions.KeyCharactersNotFoundException;
import teller.Teller;

import java.util.ArrayList;

/**
 * Awarding in the Wonderland
 */
public class Awarding implements Event {

    /**
     * @param alice - Alice
     * @param dodo - Dodo
     * @param mouse - Mouse
     * @param teller - implementation of Teller Interface
     * @param participants - ArrayList of all participants
     * @param participantsOfRace - ArrayList of participants of Caucus-race
     */

    private Alice alice;
    private Dodo dodo;
    private Mouse mouse;
    private final Teller teller;
    private ArrayList<Creature> participants;
    private ArrayList<Creature> participantsOfRace;

    /**
     * @brief constructor
     * @param teller - implementation of Teller Interface
     * @param needToInitializeParticipants - do we need to initialize participants?
     */
    public Awarding(Teller teller, boolean needToInitializeParticipants) {
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

        participantsOfRace = new ArrayList<>(participants);
        participantsOfRace.remove(dodo);
        participantsOfRace.remove(alice);
    }

    @Override
    public void performEvent(String introduction) {
        try {
            teller.tell(introduction);
            teller.tell(alice.updateMood(Mood.CONFUSED));

            for (Creature participantOfRace : participantsOfRace) {
                alice.giveCandy(participantOfRace, 1);
            }

            mouse.sayPhrase("But she must have a prize herself, you know");
            teller.tell(mouse.updateMood(Mood.REPROVING));
            teller.tell(dodo.updateMood(Mood.SERIOUS));
            dodo.sayPhrase("Of course, Alice, what else have you got in your pocket?");
            alice.sayPhrase("Only a thimble");
            teller.tell(alice.updateMood(Mood.UPSET));
            dodo.sayPhrase("Hand it over here");
            alice.giveThimble(dodo);
            for (Creature participantOfRace : participantsOfRace) {
                teller.tell(participantOfRace.getName() + " surrounded " + alice.getName());
                teller.tell(participantOfRace.updateMood(Mood.SERIOUS));
            }
            dodo.sayPhrase("We beg your acceptance of this elegant thimble!");
            for (Creature participantOfRace : participantsOfRace) {
                teller.tell(participantOfRace.getName() + " started cheering to Alice");
            }
            teller.tell(alice.updateMood(Mood.HOLDING_LAUGH));
            alice.receiveThimble(dodo);

        }
        catch (NullPointerException e){
            throw new KeyCharactersNotFoundException("Key character is not initialized!", e);
        }
    }

    @Override
    public String getInformationAboutEvent() {
        return "Awarding in the Wonderland";
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
        participantsOfRace = new ArrayList<>(participants);
        participantsOfRace.remove(dodo);
        participantsOfRace.remove(alice);
    }

    @Override
    public ArrayList<Creature> getParticipants() {
        return participants;
    }

    @Override
    public int hashCode() {
        return 13 * alice.hashCode() * dodo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Awarding)){
            return false;
        }
        Awarding awarding = (Awarding) obj;

        return awarding.participants.equals(this.participants);
    }

    @Override
    public String toString() {
        return getInformationAboutEvent();
    }
}
