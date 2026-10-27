package com.android.systemui.util;

/* JADX INFO: loaded from: classes.dex */
public class SystemUIException extends RuntimeException {
    public SystemUIException() {
    }

    public SystemUIException(String message) {
        super(message);
    }

    public SystemUIException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemUIException(Throwable cause) {
        super(cause);
    }

    public SystemUIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
