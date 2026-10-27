package com.amazon.podcast.featureGates;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class FeatureGatePreferences {
    private final Context context;

    public FeatureGatePreferences(final Context context) {
        this.context = context;
    }

    public final void writeFeatureGatePreferences(final Map<String, Boolean> featureGates) {
        SharedPreferences.Editor editorEdit = getFeatureGateSharedPreferences().edit();
        editorEdit.clear();
        for (Map.Entry<String, Boolean> entry : featureGates.entrySet()) {
            editorEdit.putBoolean(entry.getKey(), entry.getValue().booleanValue());
        }
        editorEdit.apply();
    }

    public final boolean isRedhoodPlusUXEnabled() {
        return getFeatureGateSharedPreferences().getBoolean("REDHOOD_PLUS_UX", true);
    }

    public final boolean isRedhoodPlusMetricsEnabled() {
        return getFeatureGateSharedPreferences().getBoolean("REDHOOD_PLUS_METRICS", true);
    }

    public final boolean isNewDetailPageLayoutsFeatureEnabled() {
        return getFeatureGateSharedPreferences().getBoolean("NEW_DETAIL_PAGE_LAYOUTS", true);
    }

    public final boolean isAccentOutlineUpsellBadgeEnabled() {
        return getFeatureGateSharedPreferences().getBoolean("ACCENT_OUTLINE_UPSELL_BADGE", false);
    }

    public final boolean isBitesEnabled() {
        return getFeatureGateSharedPreferences().getBoolean("BITES", false);
    }

    public final void clearData() {
        getFeatureGateSharedPreferences().edit().clear().apply();
    }

    private SharedPreferences getFeatureGateSharedPreferences() {
        return this.context.getSharedPreferences("PodcastFeatureGates", 0);
    }
}
