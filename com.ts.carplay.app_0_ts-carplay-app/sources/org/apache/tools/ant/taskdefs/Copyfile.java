package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public class Copyfile extends Task {
    private File destFile;
    private boolean filtering = false;
    private boolean forceOverwrite = false;
    private File srcFile;

    public void setSrc(File src) {
        this.srcFile = src;
    }

    public void setForceoverwrite(boolean force) {
        this.forceOverwrite = force;
    }

    public void setDest(File dest) {
        this.destFile = dest;
    }

    public void setFiltering(String filter) {
        this.filtering = Project.toBoolean(filter);
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws Throwable {
        log("DEPRECATED - The copyfile task is deprecated.  Use copy instead.");
        File file = this.srcFile;
        if (file == null) {
            throw new BuildException("The src attribute must be present.", getLocation());
        }
        if (!file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("src ");
            stringBuffer.append(this.srcFile.toString());
            stringBuffer.append(" does not exist.");
            throw new BuildException(stringBuffer.toString(), getLocation());
        }
        File file2 = this.destFile;
        if (file2 == null) {
            throw new BuildException("The dest attribute must be present.", getLocation());
        }
        if (this.srcFile.equals(file2)) {
            log("Warning: src == dest", 1);
        }
        if (this.forceOverwrite || this.srcFile.lastModified() > this.destFile.lastModified()) {
            try {
                getProject().copyFile(this.srcFile, this.destFile, this.filtering, this.forceOverwrite);
            } catch (IOException ioe) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Error copying file: ");
                stringBuffer2.append(this.srcFile.getAbsolutePath());
                stringBuffer2.append(" due to ");
                stringBuffer2.append(ioe.getMessage());
                String msg = stringBuffer2.toString();
                throw new BuildException(msg);
            }
        }
    }
}
