package com.android.car.developeroptions.support;

import android.app.Activity;
import android.os.Bundle;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.overlay.SupportFeatureProvider;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.search.Indexable$SearchIndexProvider;

/* JADX INFO: loaded from: classes.dex */
public class SupportDashboardActivity extends Activity {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.support.SupportDashboardActivity.1
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SupportFeatureProvider supportFeatureProvider = FeatureFactory.getFactory(this).getSupportFeatureProvider(this);
        if (supportFeatureProvider != null) {
            supportFeatureProvider.startSupport(this);
            finish();
        }
    }
}
