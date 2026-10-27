package com.android.car.developeroptions.search;

import android.content.Context;
import android.content.Intent;
import com.android.car.developeroptions.R;
import com.android.settingslib.search.SearchIndexableResources;

/* JADX INFO: loaded from: classes.dex */
public interface SearchFeatureProvider {
    Intent buildSearchIntent(Context context, int i);

    SearchIndexableResources getSearchIndexableResources();

    default String getSettingsIntelligencePkgName(Context context) {
        return context.getString(R.string.config_settingsintelligence_package_name);
    }
}
