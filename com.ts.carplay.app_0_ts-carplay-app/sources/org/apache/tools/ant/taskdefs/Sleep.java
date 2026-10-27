package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public class Sleep extends Task {
    private boolean failOnError = true;
    private int seconds = 0;
    private int hours = 0;
    private int minutes = 0;
    private int milliseconds = 0;

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public void doSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }

    public void setFailOnError(boolean failOnError) {
        this.failOnError = failOnError;
    }

    private long getSleepTime() {
        return (((((((long) this.hours) * 60) + ((long) this.minutes)) * 60) + ((long) this.seconds)) * 1000) + ((long) this.milliseconds);
    }

    public void validate() throws BuildException {
        if (getSleepTime() < 0) {
            throw new BuildException("Negative sleep periods are not supported");
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        try {
            validate();
            long sleepTime = getSleepTime();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("sleeping for ");
            stringBuffer.append(sleepTime);
            stringBuffer.append(" milliseconds");
            log(stringBuffer.toString(), 3);
            doSleep(sleepTime);
        } catch (Exception e) {
            if (this.failOnError) {
                throw new BuildException(e);
            }
            String text = e.toString();
            log(text, 0);
        }
    }
}
