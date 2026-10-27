package com.android.inputmethod.dictionarypack;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.android.inputmethod.latin.utils.FragmentUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class DictionarySettingsActivity extends PreferenceActivity {
    private static final String DEFAULT_FRAGMENT = DictionarySettingsFragment.class.getName();

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // android.app.Activity
    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":android:show_fragment", DEFAULT_FRAGMENT);
        intent.putExtra(":android:no_headers", true);
        return intent;
    }

    @Override // android.preference.PreferenceActivity
    public boolean isValidFragment(String fragmentName) {
        return FragmentUtils.isValidFragment(fragmentName);
    }
}
