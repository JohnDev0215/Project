package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public class Mkdir extends Task {
    private static final int MKDIR_RETRY_SLEEP_MILLIS = 10;
    private File dir;

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        File file = this.dir;
        if (file == null) {
            throw new BuildException("dir attribute is required", getLocation());
        }
        if (file.isFile()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to create directory as a file already exists with that name: ");
            stringBuffer.append(this.dir.getAbsolutePath());
            throw new BuildException(stringBuffer.toString());
        }
        if (!this.dir.exists()) {
            boolean result = mkdirs(this.dir);
            if (!result) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Directory ");
                stringBuffer2.append(this.dir.getAbsolutePath());
                stringBuffer2.append(" creation was not successful for an unknown reason");
                String msg = stringBuffer2.toString();
                throw new BuildException(msg, getLocation());
            }
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Created dir: ");
            stringBuffer3.append(this.dir.getAbsolutePath());
            log(stringBuffer3.toString());
        }
    }

    public void setDir(File dir) {
        this.dir = dir;
    }

    private boolean mkdirs(File f) {
        if (!f.mkdirs()) {
            try {
                Thread.sleep(10L);
                return f.mkdirs();
            } catch (InterruptedException e) {
                return f.mkdirs();
            }
        }
        return true;
    }
}
