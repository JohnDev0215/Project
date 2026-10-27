package org.apache.tools.ant;

import java.util.EventObject;

/* JADX INFO: loaded from: classes.dex */
public class BuildEvent extends EventObject {
    private Throwable exception;
    private String message;
    private int priority;
    private Project project;
    private Target target;
    private Task task;

    public BuildEvent(Project project) {
        super(project);
        this.priority = 3;
        this.project = project;
        this.target = null;
        this.task = null;
    }

    public BuildEvent(Target target) {
        super(target);
        this.priority = 3;
        this.project = target.getProject();
        this.target = target;
        this.task = null;
    }

    public BuildEvent(Task task) {
        super(task);
        this.priority = 3;
        this.project = task.getProject();
        this.target = task.getOwningTarget();
        this.task = task;
    }

    public void setMessage(String message, int priority) {
        this.message = message;
        this.priority = priority;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public Project getProject() {
        return this.project;
    }

    public Target getTarget() {
        return this.target;
    }

    public Task getTask() {
        return this.task;
    }

    public String getMessage() {
        return this.message;
    }

    public int getPriority() {
        return this.priority;
    }

    public Throwable getException() {
        return this.exception;
    }
}
