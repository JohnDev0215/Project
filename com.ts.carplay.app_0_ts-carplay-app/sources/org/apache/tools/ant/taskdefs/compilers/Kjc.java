package org.apache.tools.ant.taskdefs.compilers;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.ExecuteJava;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Path;

/* JADX INFO: loaded from: classes.dex */
public class Kjc extends DefaultCompilerAdapter {
    @Override // org.apache.tools.ant.taskdefs.compilers.CompilerAdapter
    public boolean execute() throws BuildException {
        this.attributes.log("Using kjc compiler", 3);
        Commandline cmd = setupKjcCommand();
        cmd.setExecutable("at.dms.kjc.Main");
        ExecuteJava ej = new ExecuteJava();
        ej.setJavaCommand(cmd);
        return ej.fork(getJavac()) == 0;
    }

    protected Commandline setupKjcCommand() {
        Commandline cmd = new Commandline();
        Path classpath = getCompileClasspath();
        if (this.deprecation) {
            cmd.createArgument().setValue("-deprecation");
        }
        if (this.destDir != null) {
            cmd.createArgument().setValue("-d");
            cmd.createArgument().setFile(this.destDir);
        }
        cmd.createArgument().setValue("-classpath");
        Path cp = new Path(this.project);
        if (this.bootclasspath != null && this.bootclasspath.size() > 0) {
            cp.append(this.bootclasspath);
        }
        if (this.extdirs != null) {
            cp.addExtdirs(this.extdirs);
        }
        cp.append(classpath);
        if (this.compileSourcepath != null) {
            cp.append(this.compileSourcepath);
        } else {
            cp.append(this.src);
        }
        cmd.createArgument().setPath(cp);
        if (this.encoding != null) {
            cmd.createArgument().setValue("-encoding");
            cmd.createArgument().setValue(this.encoding);
        }
        if (this.debug) {
            cmd.createArgument().setValue("-g");
        }
        if (this.optimize) {
            cmd.createArgument().setValue("-O2");
        }
        if (this.verbose) {
            cmd.createArgument().setValue("-verbose");
        }
        addCurrentCompilerArgs(cmd);
        logAndAddFilesToCompile(cmd);
        return cmd;
    }
}
