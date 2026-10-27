package com.android.inputmethod.latin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class FileTransforms {
    public static OutputStream getCryptedStream(OutputStream out) {
        return out;
    }

    public static InputStream getDecryptedStream(InputStream in) {
        return in;
    }

    public static InputStream getUncompressedStream(InputStream in) throws IOException {
        return new GZIPInputStream(in);
    }
}
