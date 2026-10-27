package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;

/* JADX INFO: loaded from: classes.dex */
public class Copydir extends MatchingTask {
    private File destDir;
    private File srcDir;
    private boolean filtering = false;
    private boolean flatten = false;
    private boolean forceOverwrite = false;
    private Hashtable filecopyList = new Hashtable();

    public void setSrc(File src) {
        this.srcDir = src;
    }

    public void setDest(File dest) {
        this.destDir = dest;
    }

    public void setFiltering(boolean filter) {
        this.filtering = filter;
    }

    public void setFlatten(boolean flatten) {
        this.flatten = flatten;
    }

    public void setForceoverwrite(boolean force) {
        this.forceOverwrite = force;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        log("DEPRECATED - The copydir task is deprecated.  Use copy instead.");
        File file = this.srcDir;
        if (file == null) {
            throw new BuildException("src attribute must be set!", getLocation());
        }
        if (!file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("srcdir ");
            stringBuffer.append(this.srcDir.toString());
            stringBuffer.append(" does not exist!");
            throw new BuildException(stringBuffer.toString(), getLocation());
        }
        File file2 = this.destDir;
        if (file2 == null) {
            throw new BuildException("The dest attribute must be set.", getLocation());
        }
        if (this.srcDir.equals(file2)) {
            log("Warning: src == dest", 1);
        }
        DirectoryScanner ds = super.getDirectoryScanner(this.srcDir);
        try {
            String[] files = ds.getIncludedFiles();
            scanDir(this.srcDir, this.destDir, files);
            if (this.filecopyList.size() > 0) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Copying ");
                stringBuffer2.append(this.filecopyList.size());
                stringBuffer2.append(" file");
                stringBuffer2.append(this.filecopyList.size() == 1 ? "" : "s");
                stringBuffer2.append(" to ");
                stringBuffer2.append(this.destDir.getAbsolutePath());
                log(stringBuffer2.toString());
                Enumeration e = this.filecopyList.keys();
                while (e.hasMoreElements()) {
                    String fromFile = (String) e.nextElement();
                    String toFile = (String) this.filecopyList.get(fromFile);
                    try {
                        getProject().copyFile(fromFile, toFile, this.filtering, this.forceOverwrite);
                    } catch (IOException ioe) {
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append("Failed to copy ");
                        stringBuffer3.append(fromFile);
                        stringBuffer3.append(" to ");
                        stringBuffer3.append(toFile);
                        stringBuffer3.append(" due to ");
                        stringBuffer3.append(ioe.getMessage());
                        String msg = stringBuffer3.toString();
                        throw new BuildException(msg, ioe, getLocation());
                    }
                }
            }
        } finally {
            this.filecopyList.clear();
        }
    }

    private void scanDir(File from, File to, String[] files) {
        File destFile;
        for (String filename : files) {
            File srcFile = new File(from, filename);
            if (this.flatten) {
                destFile = new File(to, new File(filename).getName());
            } else {
                destFile = new File(to, filename);
            }
            if (this.forceOverwrite || srcFile.lastModified() > destFile.lastModified()) {
                this.filecopyList.put(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
            }
        }
    }
}
