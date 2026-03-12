package lab1.task3.abstractions;

public interface INamed extends ITargetable {

    String getName();

    @Override
    default String getTargetName() {
        return getName();
    }
}
