package org.apache.tools.ant.taskdefs.compilers;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Commandline;

/* JADX INFO: loaded from: classes.dex */
public class Sj extends DefaultCompilerAdapter {
    @Override // org.apache.tools.ant.taskdefs.compilers.CompilerAdapter
    public boolean execute() throws BuildException {
        this.attributes.log("Using symantec java compiler", 3);
        Commandline cmd = setupJavacCommand();
        String exec = getJavac().getExecutable();
        cmd.setExecutable(exec == null ? "sj" : exec);
        int firstFileName = cmd.size() - this.compileList.length;
        return executeExternalCompile(cmd.getCommandline(), firstFileName) == 0;
    }

    @Override // org.apache.tools.ant.taskdefs.compilers.DefaultCompilerAdapter
    protected String getNoDebugArgument() {
        return null;
    }
}
