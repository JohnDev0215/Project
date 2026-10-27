package com.amazon.mls.config.internal.core.util;

import com.amazon.mls.config.internal.core.logcat.LogcatProxy;
import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class SafeStreamCloser {
    private SafeStreamCloser() {
    }

    public static void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            LogcatProxy.log("Failed to close stream.", e);
        }
    }
}
