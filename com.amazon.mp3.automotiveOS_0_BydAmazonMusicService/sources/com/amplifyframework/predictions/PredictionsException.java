package com.amplifyframework.predictions;

import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;

/* JADX INFO: loaded from: classes5.dex */
public final class PredictionsException extends AmplifyException {
    private static final long serialVersionUID = 6843042852631940595L;

    public PredictionsException(@NonNull String str, @NonNull Throwable th, @NonNull String str2) {
        super(str, th, str2);
    }

    public PredictionsException(@NonNull String str, @NonNull String str2) {
        super(str, str2);
    }
}
