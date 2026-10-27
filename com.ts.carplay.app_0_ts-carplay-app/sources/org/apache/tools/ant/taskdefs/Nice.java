package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public class Nice extends Task {
    private String currentPriority;
    private Integer newPriority;

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Thread self = Thread.currentThread();
        int priority = self.getPriority();
        if (this.currentPriority != null) {
            String current = Integer.toString(priority);
            getProject().setNewProperty(this.currentPriority, current);
        }
        Integer num = this.newPriority;
        if (num != null && priority != num.intValue()) {
            try {
                self.setPriority(this.newPriority.intValue());
            } catch (IllegalArgumentException iae) {
                throw new BuildException("Priority out of range", iae);
            } catch (SecurityException e) {
                log("Unable to set new priority -a security manager is in the way", 1);
            }
        }
    }

    public void setCurrentPriority(String currentPriority) {
        this.currentPriority = currentPriority;
    }

    public void setNewPriority(int newPriority) {
        if (newPriority < 1 || newPriority > 10) {
            throw new BuildException("The thread priority is out of the range 1-10");
        }
        this.newPriority = new Integer(newPriority);
    }
}
