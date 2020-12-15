import events.Awarding;
import events.CaucusRace;
import events.Feast;
import events.TaleOfMouse;
import teller.StoryTeller;

/**
 * Main class of the program
 */
public class Main {
    /**
     * main method which contains execution of program
     */
    public static void main(String[] args){
        StoryTeller storyTeller = new StoryTeller();

        CaucusRace caucusRace = new CaucusRace(storyTeller, true);
        caucusRace.performEvent("\t@The Caucus-race has started!@");

        Awarding awarding = new Awarding(storyTeller, false);
        awarding.setParticipants(caucusRace.getParticipants());
        awarding.performEvent("\t@The ceremony of awarding has started!@");

        Feast feast = new Feast(storyTeller, false);
        feast.setParticipants(awarding.getParticipants());
        feast.performEvent("\t@The feast has started!@");

        TaleOfMouse taleOfMouse = new TaleOfMouse(storyTeller, false);
        taleOfMouse.setParticipants(feast.getParticipants());
        taleOfMouse.performEvent("\t@Tale of Mouse has started!@");

    }

    @Override
    public String toString() {
        return "Main class of program";
    }
}
