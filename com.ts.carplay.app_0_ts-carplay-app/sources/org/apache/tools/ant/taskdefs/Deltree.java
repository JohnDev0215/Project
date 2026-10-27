package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public class Deltree extends Task {
    private File dir;

    public void setDir(File dir) {
        this.dir = dir;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        log("DEPRECATED - The deltree task is deprecated.  Use delete instead.");
        File file = this.dir;
        if (file == null) {
            throw new BuildException("dir attribute must be set!", getLocation());
        }
        if (file.exists()) {
            if (!this.dir.isDirectory()) {
                if (!this.dir.delete()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unable to delete directory ");
                    stringBuffer.append(this.dir.getAbsolutePath());
                    throw new BuildException(stringBuffer.toString(), getLocation());
                }
                return;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Deleting: ");
            stringBuffer2.append(this.dir.getAbsolutePath());
            log(stringBuffer2.toString());
            try {
                removeDir(this.dir);
            } catch (IOException e) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Unable to delete ");
                stringBuffer3.append(this.dir.getAbsolutePath());
                String msg = stringBuffer3.toString();
                throw new BuildException(msg, getLocation());
            }
        }
    }

    private void removeDir(File dir) throws IOException {
        String[] list = dir.list();
        for (String s : list) {
            File f = new File(dir, s);
            if (f.isDirectory()) {
                removeDir(f);
            } else if (!f.delete()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unable to delete file ");
                stringBuffer.append(f.getAbsolutePath());
                throw new BuildException(stringBuffer.toString());
            }
        }
        if (!dir.delete()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Unable to delete directory ");
            stringBuffer2.append(dir.getAbsolutePath());
            throw new BuildException(stringBuffer2.toString());
        }
    }
}
