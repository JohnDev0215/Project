package org.apache.tools.ant;

/* JADX INFO: loaded from: classes.dex */
public interface Executor {
    void executeTargets(Project project, String[] strArr) throws BuildException;

    Executor getSubProjectExecutor();
}
