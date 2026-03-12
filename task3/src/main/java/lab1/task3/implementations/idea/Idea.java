package lab1.task3.implementations.idea;

import lab1.task3.abstractions.ITargetable;
import lab1.task3.enums.IdeaContent;

public class Idea implements ITargetable {
    private final IdeaContent content;

    public Idea(final IdeaContent content) {
        this.content = content;
    }

    public IdeaContent getContent() {
        return content;
    }

    @Override
    public String getTargetName() {
        return "task3.Implementations.Idea.Idea content: [" + this.content + "]";
    }
}
