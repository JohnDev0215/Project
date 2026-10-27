package com.amazon.alexa.voice.core.internal.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes2.dex */
public final class IOUtils {
    private IOUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void waitUntilNotified(Object obj, long j) throws InterruptedIOException {
        Preconditions.notNull(obj, "monitor == null");
        try {
            long jNanoTime = System.nanoTime();
            obj.wait(j);
            if (j > 0 && System.nanoTime() - jNanoTime >= j * 1000000) {
                throw new InterruptedIOException("timeout");
            }
        } catch (InterruptedException unused) {
            throw new InterruptedIOException("interrupted");
        }
    }
}
