package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Commandline;

/* JADX INFO: loaded from: classes.dex */
public class Patch extends Task {
    private File directory;
    private File originalFile;
    private boolean havePatchfile = false;
    private Commandline cmd = new Commandline();

    public void setOriginalfile(File file) {
        this.originalFile = file;
    }

    public void setDestfile(File file) {
        if (file != null) {
            this.cmd.createArgument().setValue("-o");
            this.cmd.createArgument().setFile(file);
        }
    }

    public void setPatchfile(File file) {
        if (!file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("patchfile ");
            stringBuffer.append(file);
            stringBuffer.append(" doesn't exist");
            throw new BuildException(stringBuffer.toString(), getLocation());
        }
        this.cmd.createArgument().setValue("-i");
        this.cmd.createArgument().setFile(file);
        this.havePatchfile = true;
    }

    public void setBackups(boolean backups) {
        if (backups) {
            this.cmd.createArgument().setValue("-b");
        }
    }

    public void setIgnorewhitespace(boolean ignore) {
        if (ignore) {
            this.cmd.createArgument().setValue("-l");
        }
    }

    public void setStrip(int num) throws BuildException {
        if (num < 0) {
            throw new BuildException("strip has to be >= 0", getLocation());
        }
        Commandline.Argument argumentCreateArgument = this.cmd.createArgument();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("-p");
        stringBuffer.append(num);
        argumentCreateArgument.setValue(stringBuffer.toString());
    }

    public void setQuiet(boolean q) {
        if (q) {
            this.cmd.createArgument().setValue("-s");
        }
    }

    public void setReverse(boolean r) {
        if (r) {
            this.cmd.createArgument().setValue("-R");
        }
    }

    public void setDir(File directory) {
        this.directory = directory;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws Throwable {
        if (!this.havePatchfile) {
            throw new BuildException("patchfile argument is required", getLocation());
        }
        Commandline toExecute = (Commandline) this.cmd.clone();
        toExecute.setExecutable("patch");
        if (this.originalFile != null) {
            toExecute.createArgument().setFile(this.originalFile);
        }
        Execute exe = new Execute(new LogStreamHandler(this, 2, 1), null);
        exe.setCommandline(toExecute.getCommandline());
        File file = this.directory;
        if (file != null) {
            if (file.exists() && this.directory.isDirectory()) {
                exe.setWorkingDirectory(this.directory);
            } else {
                if (!this.directory.isDirectory()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(this.directory);
                    stringBuffer.append(" is not a directory.");
                    throw new BuildException(stringBuffer.toString(), getLocation());
                }
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("directory ");
                stringBuffer2.append(this.directory);
                stringBuffer2.append(" doesn't exist");
                throw new BuildException(stringBuffer2.toString(), getLocation());
            }
        } else {
            exe.setWorkingDirectory(getProject().getBaseDir());
        }
        log(toExecute.describeCommand(), 3);
        try {
            exe.execute();
        } catch (IOException e) {
            throw new BuildException(e, getLocation());
        }
    }
}
