package org.apache.tools.ant.taskdefs.condition;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class FilesMatch implements Condition {
    private File file1;
    private File file2;
    private FileUtils fu = FileUtils.newFileUtils();

    public void setFile1(File file1) {
        this.file1 = file1;
    }

    public void setFile2(File file2) {
        this.file2 = file2;
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        File file;
        File file2 = this.file1;
        if (file2 == null || (file = this.file2) == null) {
            throw new BuildException("both file1 and file2 are required in filesmatch");
        }
        try {
            boolean matches = this.fu.contentEquals(file2, file);
            return matches;
        } catch (IOException ioe) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("when comparing files: ");
            stringBuffer.append(ioe.getMessage());
            throw new BuildException(stringBuffer.toString(), ioe);
        }
    }
}
