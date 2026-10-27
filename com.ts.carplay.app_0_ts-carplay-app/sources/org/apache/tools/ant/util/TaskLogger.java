package org.apache.tools.ant.util;

import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public final class TaskLogger {
    private Task m_task;

    public TaskLogger(Task task) {
        this.m_task = task;
    }

    public void info(String message) {
        this.m_task.log(message, 2);
    }

    public void error(String message) {
        this.m_task.log(message, 0);
    }

    public void warning(String message) {
        this.m_task.log(message, 1);
    }

    public void verbose(String message) {
        this.m_task.log(message, 3);
    }

    public void debug(String message) {
        this.m_task.log(message, 4);
    }
}
