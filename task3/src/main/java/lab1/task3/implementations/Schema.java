package lab1.task3.implementations;

import lab1.task3.abstractions.IMaterialized;
import lab1.task3.abstractions.ITargetable;
import lab1.task3.enums.Material;
import lab1.task3.enums.State;

import java.util.Objects;

public class Schema implements IMaterialized {
    private State currentState;
    private ITargetable lastManipulatedObject;

    private String message;

    public Schema() {
        setCurrentState(State.DEFAULT);
    }

    public State getCurrentState() {
        return this.currentState;
    }

    private void setCurrentState(final State currentState) {
        this.currentState = currentState;
        switch (this.currentState) {
            case DOUBT -> message = "Is it worth it?";
            case DEFAULT -> message = ". . .";
            case DISGUST -> message = "It's disgusting!";
            case FUN -> message = "Hahaha!";
            case BORED -> message = "Meh...";
        }
    }

    public State chirp() {
        if (Objects.requireNonNull(this.currentState) == State.DEFAULT) {
            setCurrentState(State.DISGUST);
        }
        System.out.printf("[%s], being in state of [%s], chirped.\n", this.getTargetName(), this.currentState);
        return this.currentState;
    }

    public void manipulate(final ITargetable target) {
        this.lastManipulatedObject = target;
        System.out.printf("[%s], manipulated the [%s].\n", this.getTargetName(), this.lastManipulatedObject.getTargetName());
    }

    public State click() {
        if (Objects.requireNonNull(this.currentState) == State.DISGUST) {
            setCurrentState(State.DOUBT);
        }
        System.out.printf("[%s] clicked.\n", this.getTargetName());
        return this.currentState;
    }

    public void speak() {
        System.out.printf("[%s]: \"%s\"\n", this.getTargetName(), this.message);
    }

    public State compareMaterials(final Material first, final Material second) {
        if (Objects.requireNonNull(this.currentState) == State.DOUBT) {
            setCurrentState(State.FUN);
        }
        System.out.printf("[%s] compared: [%s] vs [%s]. Result: %s.\n", this.getTargetName(), first, second,
                first == second ? "Equals" : "Not equals");
        return this.currentState;
    }

    public State checkHydrogen() {
        if (Objects.requireNonNull(this.currentState) == State.FUN) {
            setCurrentState(State.BORED);
        }
        System.out.printf("[%s] checked the hydrogen level.\n", this.getTargetName());
        return this.currentState;
    }

    public State turnOff() {
        setCurrentState(State.DEFAULT);
        this.lastManipulatedObject = null;
        return this.currentState;
    }

    @Override
    public String getTargetName() {
        return "The schema[" + hashCode() + "]";
    }

    @Override
    public Material getMaterial() {
        return Material.MIXED;
    }
}
