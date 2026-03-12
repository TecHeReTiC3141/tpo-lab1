package lab1.task3.implementations;

import lab1.task3.abstractions.IMaterialized;
import lab1.task3.abstractions.INamed;
import lab1.task3.abstractions.ITargetable;
import lab1.task3.enums.IdeaContent;
import lab1.task3.enums.Material;
import lab1.task3.enums.State;
import lab1.task3.implementations.idea.Idea;
import lab1.task3.implementations.idea.TargetedIdea;

public class Robot implements INamed, IMaterialized {

    private final String name;
    private final Schema[] logicalSchemas;
    private State currentState;

    public Robot(final String name, final int numOfSchemas) {
        this.name = name;
        this.currentState = State.DEFAULT;
        this.logicalSchemas = new Schema[numOfSchemas];
        for (int i = 0; i < numOfSchemas; i++) {
            logicalSchemas[i] = new Schema();
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    public State getCurrentState() {
        return currentState;
    }

    public State watch(final ITargetable target) {
        if ("She".equals(target.getTargetName())) {
            this.currentState = State.COLD_CONTEMPT;
        }
        System.out.printf("[%s], being in state of [%s], watched after [%s].\n", this.getTargetName(), this.currentState, target.getTargetName());
        return this.currentState;
    }

    public State useSchemas(final Idea idea) {
        if (this.logicalSchemas.length == 0) {
            return this.currentState;
        }
        if (this.currentState != State.COLD_CONTEMPT) {
            return this.currentState;
        }
        if (idea.getContent() == IdeaContent.PHYSICAL_ABUSE) {
            for (final Schema schema : this.logicalSchemas) {
                schema.chirp();
            }
            for (final Schema schema : this.logicalSchemas) {
                schema.manipulate(idea);
            }
            for (final Schema schema : this.logicalSchemas) {
                schema.click();
            }
            for (final Schema schema : this.logicalSchemas) {
                schema.speak();
            }
            if (!(idea instanceof TargetedIdea && ((TargetedIdea) idea).getTarget() instanceof IMaterialized)) {
                this.currentState = this.logicalSchemas[0].getCurrentState();
                return this.currentState;
            }
            final HumanBrain brain = new HumanBrain();
            for (final Schema schema : this.logicalSchemas) {
                schema.compareMaterials(((IMaterialized) ((TargetedIdea) idea).getTarget()).getMaterial(), brain.getMaterial());
            }
            for (final Schema schema : this.logicalSchemas) {
                schema.checkHydrogen();
            }
            for (final Schema schema : this.logicalSchemas) {
                schema.turnOff();
            }

            this.currentState = State.DESPAIR;
            return this.currentState;
        }
        return this.currentState;
    }

    public boolean turn() {
        if (this.currentState == State.DESPAIR) {
            System.out.printf("[%s], being in state of [%s], turned with a spasm.\n", this.getTargetName(), this.currentState);
            return true;
        }
        System.out.printf("[%s], being in state of [%s], turned.\n", this.getTargetName(), this.currentState);
        return false;

    }

    @Override
    public Material getMaterial() {
        return Material.MIXED;
    }
}
