package com.amazon.avod.fileio;

/* JADX INFO: loaded from: classes2.dex */
public class DiskFullIOException extends DiskIOException {
    private final long mBytesAvailable;
    private final long mBytesRequested;

    public DiskFullIOException() {
        this(-1L, -1L, null);
    }

    public DiskFullIOException(String str) {
        this(-1L, -1L, str);
    }

    public DiskFullIOException(long j, long j2, String str) {
        super(str);
        this.mBytesRequested = j;
        this.mBytesAvailable = j2;
    }
}
