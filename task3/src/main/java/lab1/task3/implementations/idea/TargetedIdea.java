package lab1.task3.implementations.idea;

import lab1.task3.abstractions.ITargetable;
import lab1.task3.enums.IdeaContent;

public class TargetedIdea extends Idea {

    private final ITargetable target;

    public TargetedIdea(final IdeaContent content, final ITargetable target) {
        super(content);
        this.target = target;
    }

    public ITargetable getTarget() {
        return target;
    }

    @Override
    public String getTargetName() {
        return super.getTargetName() + ", task3.Implementations.Idea.Idea target: [" + this.target.getTargetName() + "]";
    }
}
