package com.amplifyframework.analytics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.core.category.Category;
import com.amplifyframework.core.category.CategoryType;

/* JADX INFO: loaded from: classes5.dex */
public final class AnalyticsCategory extends Category<AnalyticsPlugin<?>> implements AnalyticsCategoryBehavior {
    private boolean enabled = true;

    @Override // com.amplifyframework.core.category.CategoryTypeable
    @NonNull
    public CategoryType getCategoryType() {
        return CategoryType.ANALYTICS;
    }

    @Override // com.amplifyframework.analytics.AnalyticsCategoryBehavior
    public void identifyUser(@NonNull String str, @Nullable UserProfile userProfile) {
        if (this.enabled) {
            getSelectedPlugin().identifyUser(str, userProfile);
        }
    }

    @Override // com.amplifyframework.analytics.AnalyticsCategoryBehavior
    public void disable() {
        this.enabled = false;
        getSelectedPlugin().disable();
    }

    @Override // com.amplifyframework.analytics.AnalyticsCategoryBehavior
    public void enable() {
        this.enabled = true;
        getSelectedPlugin().enable();
    }

    @Override // com.amplifyframework.analytics.AnalyticsCategoryBehavior
    public void recordEvent(@NonNull String str) {
        if (this.enabled) {
            getSelectedPlugin().recordEvent(str);
        }
    }

    @Override // com.amplifyframework.analytics.AnalyticsCategoryBehavior
    public void recordEvent(@NonNull AnalyticsEventBehavior analyticsEventBehavior) {
        if (this.enabled) {
            getSelectedPlugin().recordEvent(analyticsEventBehavior);
        }
    }

    @Override // com.amplifyframework.analytics.AnalyticsCategoryBehavior
    public void registerGlobalProperties(@NonNull AnalyticsProperties analyticsProperties) {
        getSelectedPlugin().registerGlobalProperties(analyticsProperties);
    }

    @Override // com.amplifyframework.analytics.AnalyticsCategoryBehavior
    public void unregisterGlobalProperties(@NonNull String... strArr) {
        getSelectedPlugin().unregisterGlobalProperties(strArr);
    }

    @Override // com.amplifyframework.analytics.AnalyticsCategoryBehavior
    public void flushEvents() {
        if (this.enabled) {
            getSelectedPlugin().flushEvents();
        }
    }
}
