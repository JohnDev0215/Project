package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/* JADX INFO: loaded from: classes7.dex */
public class TrueFileFilter implements IOFileFilter, Serializable {
    public static final IOFileFilter INSTANCE;
    private static final String TO_STRING = Boolean.TRUE.toString();
    public static final IOFileFilter TRUE;

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        return true;
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return true;
    }

    static {
        TrueFileFilter trueFileFilter = new TrueFileFilter();
        TRUE = trueFileFilter;
        INSTANCE = trueFileFilter;
    }

    protected TrueFileFilter() {
    }

    @Override // org.apache.commons.io.file.PathFilter
    public FileVisitResult accept(Path path, BasicFileAttributes basicFileAttributes) {
        return FileVisitResult.CONTINUE;
    }

    public String toString() {
        return TO_STRING;
    }
}
