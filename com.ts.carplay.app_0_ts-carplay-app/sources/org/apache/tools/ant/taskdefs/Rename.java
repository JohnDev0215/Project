package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class Rename extends Task {
    private File dest;
    private boolean replace = true;
    private File src;

    public void setSrc(File src) {
        this.src = src;
    }

    public void setDest(File dest) {
        this.dest = dest;
    }

    public void setReplace(String replace) {
        this.replace = Project.toBoolean(replace);
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws Throwable {
        log("DEPRECATED - The rename task is deprecated.  Use move instead.");
        File file = this.dest;
        if (file == null) {
            throw new BuildException("dest attribute is required", getLocation());
        }
        if (this.src == null) {
            throw new BuildException("src attribute is required", getLocation());
        }
        if (!this.replace && file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.dest);
            stringBuffer.append(" already exists.");
            throw new BuildException(stringBuffer.toString());
        }
        try {
            FileUtils.newFileUtils().rename(this.src, this.dest);
        } catch (IOException e) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Unable to rename ");
            stringBuffer2.append(this.src);
            stringBuffer2.append(" to ");
            stringBuffer2.append(this.dest);
            throw new BuildException(stringBuffer2.toString(), e, getLocation());
        }
    }
}
