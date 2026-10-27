package com.amplifyframework.analytics;

import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;

/* JADX INFO: loaded from: classes5.dex */
public final class AnalyticsException extends AmplifyException {
    private static final long serialVersionUID = 1;

    public AnalyticsException(@NonNull String str, Throwable th, @NonNull String str2) {
        super(str, th, str2);
    }

    public AnalyticsException(@NonNull String str, @NonNull String str2) {
        super(str, str2);
    }
}
