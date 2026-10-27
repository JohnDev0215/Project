package com.android.car.developeroptions.flashlight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.search.Indexable$SearchIndexProvider;

/* JADX INFO: loaded from: classes.dex */
public class FlashlightHandleActivity extends Activity {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.flashlight.FlashlightHandleActivity.1
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().getBooleanExtra("fallback_to_homepage", false)) {
            startActivity(new Intent("android.settings.SETTINGS"));
        }
        finish();
    }
}
