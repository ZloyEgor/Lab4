package events;

import creatures.Creature;
import teller.Teller;

import java.util.ArrayList;

/**
 * Interface of the Wonderland event
 */
public interface Event {
    /**
     * @brief Get information about an event
     * @return - event description
     */
    String getInformationAboutEvent();

    /**
     * @brief Initializing event participants
     */
    void initializeParticipants(Teller teller);

    /**
     * @brief Executing an event
     * @param introduction - String describing the event
     */
    void performEvent(String introduction);

    /**
     * @brief method what returns ArrayList of participants
     * @return - ArrayList of participants
     */
    ArrayList<Creature> getParticipants();

    /**
     * Setting participants from outside
     * @param participants - ArrayList of creatures that should be participants of Event
     */
    void setParticipants(ArrayList<Creature> participants);
}