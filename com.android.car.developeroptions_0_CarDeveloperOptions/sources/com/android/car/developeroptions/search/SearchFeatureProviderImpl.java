package com.android.car.developeroptions.search;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.settingslib.search.SearchIndexableResources;
import com.android.settingslib.search.SearchIndexableResourcesMobile;

/* JADX INFO: loaded from: classes.dex */
public class SearchFeatureProviderImpl implements SearchFeatureProvider {
    private SearchIndexableResources mSearchIndexableResources;

    @Override // com.android.car.developeroptions.search.SearchFeatureProvider
    public SearchIndexableResources getSearchIndexableResources() {
        if (this.mSearchIndexableResources == null) {
            this.mSearchIndexableResources = new SearchIndexableResourcesMobile();
        }
        return this.mSearchIndexableResources;
    }

    @Override // com.android.car.developeroptions.search.SearchFeatureProvider
    public Intent buildSearchIntent(Context context, int i) {
        return new Intent("android.settings.APP_SEARCH_SETTINGS").setPackage(getSettingsIntelligencePkgName(context)).putExtra("android.intent.extra.REFERRER", buildReferrer(context, i));
    }

    private static Uri buildReferrer(Context context, int i) {
        return new Uri.Builder().scheme("android-app").authority(context.getPackageName()).path(String.valueOf(i)).build();
    }
}
