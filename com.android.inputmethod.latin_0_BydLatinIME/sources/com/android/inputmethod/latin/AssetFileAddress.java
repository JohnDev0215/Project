package com.android.inputmethod.latin;

import com.android.inputmethod.latin.common.FileUtils;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public final class AssetFileAddress {
    public final String mFilename;
    public final long mLength;
    public final long mOffset;

    public AssetFileAddress(final String filename, final long offset, final long length) {
        this.mFilename = filename;
        this.mOffset = offset;
        this.mLength = length;
    }

    public static AssetFileAddress makeFromFile(final File file) {
        if (file.isFile()) {
            return new AssetFileAddress(file.getAbsolutePath(), 0L, file.length());
        }
        return null;
    }

    public static AssetFileAddress makeFromFileName(final String filename) {
        if (filename == null) {
            return null;
        }
        return makeFromFile(new File(filename));
    }

    public static AssetFileAddress makeFromFileNameAndOffset(final String filename, final long offset, final long length) {
        if (filename != null && new File(filename).isFile()) {
            return new AssetFileAddress(filename, offset, length);
        }
        return null;
    }

    public boolean pointsToPhysicalFile() {
        return 0 == this.mOffset;
    }

    public void deleteUnderlyingFile() {
        FileUtils.deleteRecursively(new File(this.mFilename));
    }

    public String toString() {
        return String.format("%s (offset=%d, length=%d)", this.mFilename, Long.valueOf(this.mOffset), Long.valueOf(this.mLength));
    }
}
