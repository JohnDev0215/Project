package com.amplifyframework.hub;

import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;

/* JADX INFO: loaded from: classes5.dex */
public final class HubException extends AmplifyException {
    private static final long serialVersionUID = 1;

    public HubException(@NonNull String str, Throwable th, @NonNull String str2) {
        super(str, th, str2);
    }

    public HubException(@NonNull String str, @NonNull String str2) {
        super(str, str2);
    }
}
