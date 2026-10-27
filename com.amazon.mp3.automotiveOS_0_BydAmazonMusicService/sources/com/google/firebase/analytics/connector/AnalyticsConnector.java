package com.google.firebase.analytics.connector;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-measurement-connector@@19.0.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface AnalyticsConnector {

    /* JADX INFO: compiled from: com.google.firebase:firebase-measurement-connector@@19.0.0 */
    @KeepForSdk
    public static class ConditionalUserProperty {

        @KeepForSdk
        public long creationTimestamp;

        @NonNull
        @KeepForSdk
        public String name;

        @NonNull
        @KeepForSdk
        public String origin;

        @KeepForSdk
        public long timeToLive;

        @Nullable
        @KeepForSdk
        public String triggerEventName;

        @KeepForSdk
        public long triggerTimeout;

        @Nullable
        @KeepForSdk
        public Object value;
    }

    @KeepForSdk
    void clearConditionalUserProperty(@NonNull @Size(max = 24, min = 1) String str, @Nullable String str2, @Nullable Bundle bundle);

    @NonNull
    @KeepForSdk
    @WorkerThread
    List<ConditionalUserProperty> getConditionalUserProperties(@NonNull String str, @Nullable @Size(max = 23, min = 1) String str2);

    @KeepForSdk
    @WorkerThread
    int getMaxUserProperties(@NonNull @Size(min = 1) String str);

    @NonNull
    @KeepForSdk
    @WorkerThread
    Map<String, Object> getUserProperties(boolean z);

    @KeepForSdk
    void logEvent(@NonNull String str, @NonNull String str2, @Nullable Bundle bundle);

    @KeepForSdk
    void setConditionalUserProperty(@NonNull ConditionalUserProperty conditionalUserProperty);

    @KeepForSdk
    void setUserProperty(@NonNull String str, @NonNull String str2, @NonNull Object obj);
}
