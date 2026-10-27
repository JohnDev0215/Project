package org.apache.tools.ant.taskdefs.compilers;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Path;

/* JADX INFO: loaded from: classes.dex */
public class Gcj extends DefaultCompilerAdapter {
    private static final String[] CONFLICT_WITH_DASH_C = {"-o", "--main=", "-D", "-fjni", "-L"};

    @Override // org.apache.tools.ant.taskdefs.compilers.CompilerAdapter
    public boolean execute() throws BuildException {
        this.attributes.log("Using gcj compiler", 3);
        Commandline cmd = setupGCJCommand();
        int firstFileName = cmd.size();
        logAndAddFilesToCompile(cmd);
        return executeExternalCompile(cmd.getCommandline(), firstFileName) == 0;
    }

    protected Commandline setupGCJCommand() {
        Commandline cmd = new Commandline();
        Path classpath = new Path(this.project);
        if (this.bootclasspath != null) {
            classpath.append(this.bootclasspath);
        }
        classpath.addExtdirs(this.extdirs);
        classpath.append(getCompileClasspath());
        if (this.compileSourcepath != null) {
            classpath.append(this.compileSourcepath);
        } else {
            classpath.append(this.src);
        }
        String exec = getJavac().getExecutable();
        cmd.setExecutable(exec == null ? "gcj" : exec);
        if (this.destDir != null) {
            cmd.createArgument().setValue("-d");
            cmd.createArgument().setFile(this.destDir);
            if (!this.destDir.exists() && !this.destDir.mkdirs()) {
                throw new BuildException("Can't make output directories. Maybe permission is wrong. ");
            }
        }
        cmd.createArgument().setValue("-classpath");
        cmd.createArgument().setPath(classpath);
        if (this.encoding != null) {
            Commandline.Argument argumentCreateArgument = cmd.createArgument();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("--encoding=");
            stringBuffer.append(this.encoding);
            argumentCreateArgument.setValue(stringBuffer.toString());
        }
        if (this.debug) {
            cmd.createArgument().setValue("-g1");
        }
        if (this.optimize) {
            cmd.createArgument().setValue("-O");
        }
        if (!isNativeBuild()) {
            cmd.createArgument().setValue("-C");
        }
        addCurrentCompilerArgs(cmd);
        return cmd;
    }

    public boolean isNativeBuild() {
        boolean nativeBuild = false;
        String[] additionalArguments = getJavac().getCurrentCompilerArgs();
        for (int argsLength = 0; !nativeBuild && argsLength < additionalArguments.length; argsLength++) {
            int conflictLength = 0;
            while (!nativeBuild) {
                String[] strArr = CONFLICT_WITH_DASH_C;
                if (conflictLength >= strArr.length) {
                    break;
                }
                nativeBuild = additionalArguments[argsLength].startsWith(strArr[conflictLength]);
                conflictLength++;
            }
        }
        return nativeBuild;
    }
}
