package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class TempFile extends Task {
    private String prefix;
    private String property;
    private File destDir = null;
    private String suffix = "";

    public void setProperty(String property) {
        this.property = property;
    }

    public void setDestDir(File destDir) {
        this.destDir = destDir;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        String str = this.property;
        if (str == null || str.length() == 0) {
            throw new BuildException("no property specified");
        }
        if (this.destDir == null) {
            this.destDir = getProject().resolveFile(".");
        }
        FileUtils utils = FileUtils.newFileUtils();
        File tfile = utils.createTempFile(this.prefix, this.suffix, this.destDir);
        getProject().setNewProperty(this.property, tfile.toString());
    }
}
