package org.apache.tools.ant.taskdefs;

import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.TaskContainer;

/* JADX INFO: loaded from: classes.dex */
public class Sequential extends Task implements TaskContainer {
    private Vector nestedTasks = new Vector();

    @Override // org.apache.tools.ant.TaskContainer
    public void addTask(Task nestedTask) {
        this.nestedTasks.addElement(nestedTask);
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Enumeration e = this.nestedTasks.elements();
        while (e.hasMoreElements()) {
            Task nestedTask = (Task) e.nextElement();
            nestedTask.perform();
        }
    }
}
