package com.android.car.developeroptions.dashboard.suggestions;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public interface SuggestionFeatureProvider {
    SharedPreferences getSharedPrefs(Context context);

    ComponentName getSuggestionServiceComponent();
}
