package com.google.android.gms.cast.framework;

import androidx.annotation.NonNull;
import com.google.android.gms.cast.framework.Session;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface SessionManagerListener<T extends Session> {
    void onSessionEnded(@NonNull T t, int i);

    void onSessionEnding(@NonNull T t);

    void onSessionResumeFailed(@NonNull T t, int i);

    void onSessionResumed(@NonNull T t, boolean z);

    void onSessionResuming(@NonNull T t, @NonNull String str);

    void onSessionStartFailed(@NonNull T t, int i);

    void onSessionStarted(@NonNull T t, @NonNull String str);

    void onSessionStarting(@NonNull T t);

    void onSessionSuspended(@NonNull T t, int i);
}
