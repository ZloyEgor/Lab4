package teller;

/**
 * Implementation of Teller Interface that prints text to the console
 */
public class StoryTeller implements Teller {

    @Override
    public void tell(String text) {
        System.out.println(text);
    }
}
