package org.apache.tools.ant.util;

/* JADX INFO: loaded from: classes.dex */
public class MergingMapper implements FileNameMapper {
    protected String[] mergedFile = null;

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setFrom(String from) {
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setTo(String to) {
        this.mergedFile = new String[]{to};
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public String[] mapFileName(String sourceFileName) {
        return this.mergedFile;
    }
}
