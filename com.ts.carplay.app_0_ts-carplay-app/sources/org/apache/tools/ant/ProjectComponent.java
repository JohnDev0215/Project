package org.apache.tools.ant;

/* JADX INFO: loaded from: classes.dex */
public abstract class ProjectComponent {
    protected Project project;

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return this.project;
    }

    public void log(String msg) {
        log(msg, 2);
    }

    public void log(String msg, int msgLevel) {
        Project project = this.project;
        if (project != null) {
            project.log(msg, msgLevel);
        } else if (msgLevel <= 2) {
            System.err.println(msg);
        }
    }
}
