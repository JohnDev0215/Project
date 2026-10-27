package com.amplifyframework.analytics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface AnalyticsCategoryBehavior {
    void disable();

    void enable();

    void flushEvents();

    void identifyUser(@NonNull String str, @Nullable UserProfile userProfile);

    void recordEvent(@NonNull AnalyticsEventBehavior analyticsEventBehavior);

    void recordEvent(@NonNull String str);

    void registerGlobalProperties(@NonNull AnalyticsProperties analyticsProperties);

    void unregisterGlobalProperties(@NonNull String... strArr);
}
