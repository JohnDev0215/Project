package com.bugsnag.android.repackaged.dslplatform.json;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class SerializationException extends RuntimeException {
    public SerializationException(@Nullable String str) {
        super(str);
    }

    public SerializationException(@Nullable Throwable th) {
        super(th);
    }

    public SerializationException(@Nullable String str, @Nullable Throwable th) {
        super(str, th);
    }
}
