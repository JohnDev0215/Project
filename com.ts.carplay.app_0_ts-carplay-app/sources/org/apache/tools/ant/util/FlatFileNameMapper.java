package org.apache.tools.ant.util;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class FlatFileNameMapper implements FileNameMapper {
    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setFrom(String from) {
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setTo(String to) {
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public String[] mapFileName(String sourceFileName) {
        return new String[]{new File(sourceFileName).getName()};
    }
}
