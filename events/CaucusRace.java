package events;

import creatures.*;
import enums.Incident;
import enums.Mood;
import exceptions.KeyCharactersNotFoundException;
import teller.Teller;

import java.util.ArrayList;

public class CaucusRace implements Event{

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
    public CaucusRace(Teller teller, boolean needToInitializeParticipants) {
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
        participants.add(new Creature(teller) {
            {
                name = "Crab";
                teller.tell(updateMood(Mood.UPSET));
                teller.tell(updateIncident(Incident.WET));
            }
        });
        participants.add(mouse);
        participants.add(dodo);
        participants.add(alice);

        if (participants.get(1).hashCode() == participants.get(2).hashCode()){
            ((Bird)participants.get(1)).setDifferentName(participants.get(2).getName());
        }
        if (participants.get(3).hashCode() == participants.get(4).hashCode()){
            ((Bird)participants.get(3)).setDifferentName(participants.get(4).getName());
        }
        for(Creature bird: participants){
            if(!(bird instanceof Dodo) && (bird instanceof Bird)) {
                teller.tell(bird.updateMood(Mood.UPSET));
                teller.tell(bird.updateIncident(Incident.WET));
            }
        }
    }

    @Override
    public void performEvent(String introduction) {
        try {
            dodo.updateMood(Mood.RESENTMENT);
            dodo.sayPhrase("The best thing to get us dry would be a Caucus-race.");
            teller.tell("<prolonged silence>");
            alice.sayPhrase("What is a Caucus-race?");
            teller.tell(alice.isInterested(false));

            dodo.sayPhrase("The best way to explain it is to do it.");
            dodo.updateMood(Mood.GLAD);

            teller.tell(introduction);
            ArrayList<Creature> participantsOfRace = new ArrayList<>(participants);
            participantsOfRace.remove(dodo);

            dodo.arrangeCaucusRace(participantsOfRace);

            for (Creature participant : participantsOfRace) {
                teller.tell(participant.getName() + " surrounded " + dodo.getName());
                participant.sayPhrase("But who has won?!");
            }

            dodo.sayPhrase("Everybody has won, and all must have prizes.");

            for (Creature participant : participantsOfRace) {
                participant.sayPhrase("But who is to give the prizes?");
            }

            teller.tell(dodo.updateMood(Mood.THINKING));
            dodo.sayPhrase("Why, she, of course");
            dodo.pointAt(alice);
            participantsOfRace.remove(alice);

            for (Creature participant : participantsOfRace) {
                teller.tell(participant.getName() + " surrounded " + alice.getName());
                participant.sayPhrase("Prizes! Prizes!");
            }
        }
        catch (NullPointerException e){
            throw new KeyCharactersNotFoundException("Key character is not initialized!", e);
        }

    }

    @Override
    public String getInformationAboutEvent() {
        return "Caucus-race in the Wonderland";
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
    public int hashCode() {
        return 15 * alice.hashCode() * dodo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof CaucusRace)){
            return false;
        }
        CaucusRace caucusRace = (CaucusRace) obj;

        return caucusRace.participants.equals(this.participants);
    }

    @Override
    public String toString() {
        return getInformationAboutEvent();
    }
}