package com.android.car.developeroptions;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class SubSettings extends SettingsActivity {
    @Override // com.android.car.developeroptions.core.SettingsBaseActivity, android.app.Activity
    public boolean onNavigateUp() {
        finish();
        return true;
    }

    @Override // com.android.car.developeroptions.SettingsActivity
    protected boolean isValidFragment(String str) {
        Log.d("SubSettings", "Launching fragment " + str);
        return true;
    }
}
