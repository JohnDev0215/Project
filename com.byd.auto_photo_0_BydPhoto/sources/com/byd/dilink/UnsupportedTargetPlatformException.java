package com.byd.dilink;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class UnsupportedTargetPlatformException extends RuntimeException {
    public UnsupportedTargetPlatformException(String str) {
        super(str);
    }

    public UnsupportedTargetPlatformException(String str, Throwable th) {
        super(str, th);
    }

    public UnsupportedTargetPlatformException(Throwable th) {
        super(th);
    }
}
