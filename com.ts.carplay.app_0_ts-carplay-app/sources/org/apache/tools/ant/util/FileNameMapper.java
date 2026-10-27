package org.apache.tools.ant.util;

/* JADX INFO: loaded from: classes.dex */
public interface FileNameMapper {
    String[] mapFileName(String str);

    void setFrom(String str);

    void setTo(String str);
}
