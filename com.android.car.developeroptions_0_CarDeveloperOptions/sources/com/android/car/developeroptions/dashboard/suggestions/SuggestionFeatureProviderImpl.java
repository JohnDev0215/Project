package com.android.car.developeroptions.dashboard.suggestions;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import com.android.car.developeroptions.overlay.FeatureFactory;

/* JADX INFO: loaded from: classes.dex */
public class SuggestionFeatureProviderImpl implements SuggestionFeatureProvider {
    @Override // com.android.car.developeroptions.dashboard.suggestions.SuggestionFeatureProvider
    public ComponentName getSuggestionServiceComponent() {
        return new ComponentName("com.android.car.developeroptions.intelligence", "com.android.car.developeroptions.intelligence.suggestions.SuggestionService");
    }

    @Override // com.android.car.developeroptions.dashboard.suggestions.SuggestionFeatureProvider
    public SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences("suggestions", 0);
    }

    public SuggestionFeatureProviderImpl(Context context) {
        FeatureFactory.getFactory(context.getApplicationContext()).getMetricsFeatureProvider();
    }
}
