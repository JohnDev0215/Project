package org.apache.tools.ant.helper;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Executor;
import org.apache.tools.ant.Project;

/* JADX INFO: loaded from: classes.dex */
public class SingleCheckExecutor implements Executor {
    @Override // org.apache.tools.ant.Executor
    public void executeTargets(Project project, String[] targetNames) throws BuildException {
        project.executeSortedTargets(project.topoSort(targetNames, project.getTargets(), false));
    }

    @Override // org.apache.tools.ant.Executor
    public Executor getSubProjectExecutor() {
        return this;
    }
}
