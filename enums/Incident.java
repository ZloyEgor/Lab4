package enums;
/**
 * List of incidents, minor incidents
 */
public  enum Incident {

    SQUEAK(" squeaks shrilly"),
    SQUEAL(" squeals loudly"),
    COMPLAINING(" grumble because didn't have time to taste sweets"){},
    CHOKE(" ate quickly and choked"),
    WET(" is wet and may get cold"),
    NONE(" is fine");

    /**
     * @param description - description of the incident
     */
    private final String description;

    /**
     * @brief - constructor
     * @param description - a string that contains a description of the mood
     */
    Incident(String description){
        this.description = description;
    }

    /**
     * @brief - description getter
     * @return - description
     */
    public String getDescription(){
        return description;
    }

    @Override
    public String toString() {
        return "Enum of Incidents";
    }
}