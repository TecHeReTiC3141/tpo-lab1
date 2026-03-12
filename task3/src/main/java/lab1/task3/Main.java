package lab1.task3;

import lab1.task3.abstractions.INamed;
import lab1.task3.enums.IdeaContent;
import lab1.task3.enums.Material;
import lab1.task3.enums.State;
import lab1.task3.implementations.Door;
import lab1.task3.implementations.Robot;
import lab1.task3.implementations.idea.TargetedIdea;

public class Main {
    public static void main(final String[] args) {
        final Robot marvin = new Robot("Marvin", 2);
        final State watchindState = marvin.watch((INamed) () -> "She");
        final State finalState = marvin.useSchemas(new TargetedIdea(IdeaContent.PHYSICAL_ABUSE, new Door(Material.WOOD)));
        final boolean isSpasmed = marvin.turn();
        System.out.printf("Program result: Marvin's watching state was [%s], while the final state (after schemas work) is [%s], and he is [%s].\n", watchindState, finalState, isSpasmed ? "spasmed" : "not spasmed");
    }
}
