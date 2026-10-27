package com.android.inputmethod.dictionarypack;

import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
final class AssetFileAddress {
    public final String mFilename;
    public final long mLength;
    public final long mOffset;

    public AssetFileAddress(final String filename, final long offset, final long length) {
        this.mFilename = filename;
        this.mOffset = offset;
        this.mLength = length;
    }

    public static AssetFileAddress makeFromFileName(final String filename) {
        if (filename == null) {
            return null;
        }
        File file = new File(filename);
        if (file.isFile()) {
            return new AssetFileAddress(filename, 0L, file.length());
        }
        return null;
    }

    public static AssetFileAddress makeFromFileNameAndOffset(final String filename, final long offset, final long length) {
        if (filename != null && new File(filename).isFile()) {
            return new AssetFileAddress(filename, offset, length);
        }
        return null;
    }
}
