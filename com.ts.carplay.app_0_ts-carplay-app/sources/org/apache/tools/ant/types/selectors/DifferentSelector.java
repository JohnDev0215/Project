package org.apache.tools.ant.types.selectors;

import java.io.File;
import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class DifferentSelector extends MappingSelector {
    private FileUtils fileUtils = FileUtils.newFileUtils();
    private boolean ignoreFileTimes = true;
    private boolean ignoreContents = false;

    public void setIgnoreFileTimes(boolean ignoreFileTimes) {
        this.ignoreFileTimes = ignoreFileTimes;
    }

    public void setIgnoreContents(boolean ignoreContents) {
        this.ignoreContents = ignoreContents;
    }

    @Override // org.apache.tools.ant.types.selectors.MappingSelector
    protected boolean selectionTest(File srcfile, File destfile) {
        if (srcfile.exists() != destfile.exists() || srcfile.length() != destfile.length()) {
            return true;
        }
        if (!this.ignoreFileTimes) {
            boolean sameDate = destfile.lastModified() >= srcfile.lastModified() - ((long) this.granularity) && destfile.lastModified() <= srcfile.lastModified() + ((long) this.granularity);
            if (!sameDate) {
                return true;
            }
        }
        boolean sameDate2 = this.ignoreContents;
        if (sameDate2) {
            return false;
        }
        try {
            return !this.fileUtils.contentEquals(srcfile, destfile);
        } catch (IOException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("while comparing ");
            stringBuffer.append(srcfile);
            stringBuffer.append(" and ");
            stringBuffer.append(destfile);
            throw new BuildException(stringBuffer.toString(), e);
        }
    }
}
