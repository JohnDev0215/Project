package com.android.car.developeroptions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.android.car.developeroptions.EncryptionInterstitial;

/* JADX INFO: loaded from: classes.dex */
public class SetupEncryptionInterstitial extends EncryptionInterstitial {

    public static class SetupEncryptionInterstitialFragment extends EncryptionInterstitial.EncryptionInterstitialFragment {
    }

    public static Intent createStartIntent(Context context, int i, boolean z, Intent intent) {
        Intent intentCreateStartIntent = EncryptionInterstitial.createStartIntent(context, i, z, intent);
        intentCreateStartIntent.setClass(context, SetupEncryptionInterstitial.class);
        intentCreateStartIntent.putExtra("extra_prefs_show_button_bar", false).putExtra(":settings:show_fragment_title_resid", -1);
        return intentCreateStartIntent;
    }

    @Override // com.android.car.developeroptions.EncryptionInterstitial, com.android.car.developeroptions.SettingsActivity, android.app.Activity
    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", SetupEncryptionInterstitialFragment.class.getName());
        return intent;
    }

    @Override // com.android.car.developeroptions.EncryptionInterstitial, com.android.car.developeroptions.SettingsActivity
    protected boolean isValidFragment(String str) {
        return SetupEncryptionInterstitialFragment.class.getName().equals(str);
    }

    @Override // com.android.car.developeroptions.EncryptionInterstitial, com.android.car.developeroptions.SettingsActivity, com.android.car.developeroptions.core.SettingsBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((LinearLayout) findViewById(R.id.content_parent)).setFitsSystemWindows(false);
    }
}
