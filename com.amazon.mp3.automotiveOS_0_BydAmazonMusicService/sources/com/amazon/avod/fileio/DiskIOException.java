package com.amazon.avod.fileio;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class DiskIOException extends IOException {
    public DiskIOException(String str) {
        super(str);
    }

    public DiskIOException(String str, Throwable th) {
        super(str, th);
    }
}
