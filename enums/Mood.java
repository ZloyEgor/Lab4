package enums;
/**
 * Enumeration of moods, states of mind
 */
public enum Mood {
    SERIOUS(" looks quit serious"), GLAD(" looks happy and glad"),
    OUTRAGED(" looks indignant and outraged"), HOLDING_LAUGH(" trying to hold back laugh and look serious"),
    GRATEFUL(" looks really grateful and beholden"), CONFUSED(" looks confused"),
    RESENTMENT(" looks offended"), UPSET(" looks upset"),
    PANTING(" looks out of breath and agitated"), THINKING(" looks very thoughtful"),
    WARY(" looks wary and alert"), REPROVING(" looks reproving");

    /**
     * @param description - mood description
     */
    private final String description;

    /**
     * @brief - constructor
     * @param description - a string that contains a description of the mood
     */
    Mood(String description){
        this.description = description;
    }

    /**
     * @brief - getter of the description
     * @return - description
     */
    public String getDescription(){
        return description;
    }

    @Override
    public String toString() {
        return "Enum of Mood";
    }
}
