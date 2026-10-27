package com.facebook.react.bridge;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes6.dex */
public class NativeArgumentsParseException extends JSApplicationCausedNativeException {
    public NativeArgumentsParseException(String str) {
        super(str);
    }

    public NativeArgumentsParseException(@Nullable String str, @Nullable Throwable th) {
        super(str, th);
    }
}
