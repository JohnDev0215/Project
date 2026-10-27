package org.apache.tools.ant.taskdefs;

import java.io.File;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public abstract class Unpack extends Task {
    protected File dest;
    protected File source;

    protected abstract void extract();

    protected abstract String getDefaultExtension();

    public void setSrc(String src) {
        log("DEPRECATED - The setSrc(String) method has been deprecated. Use setSrc(File) instead.");
        setSrc(getProject().resolveFile(src));
    }

    public void setDest(String dest) {
        log("DEPRECATED - The setDest(String) method has been deprecated. Use setDest(File) instead.");
        setDest(getProject().resolveFile(dest));
    }

    public void setSrc(File src) {
        this.source = src;
    }

    public void setDest(File dest) {
        this.dest = dest;
    }

    private void validate() throws BuildException {
        File file = this.source;
        if (file == null) {
            throw new BuildException("No Src specified", getLocation());
        }
        if (!file.exists()) {
            throw new BuildException("Src doesn't exist", getLocation());
        }
        if (this.source.isDirectory()) {
            throw new BuildException("Cannot expand a directory", getLocation());
        }
        if (this.dest == null) {
            this.dest = new File(this.source.getParent());
        }
        if (this.dest.isDirectory()) {
            String defaultExtension = getDefaultExtension();
            createDestFile(defaultExtension);
        }
    }

    private void createDestFile(String defaultExtension) {
        String sourceName = this.source.getName();
        int len = sourceName.length();
        if (defaultExtension != null && len > defaultExtension.length() && defaultExtension.equalsIgnoreCase(sourceName.substring(len - defaultExtension.length()))) {
            this.dest = new File(this.dest, sourceName.substring(0, len - defaultExtension.length()));
        } else {
            this.dest = new File(this.dest, sourceName);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        File savedDest = this.dest;
        try {
            validate();
            extract();
        } finally {
            this.dest = savedDest;
        }
    }
}
