package com.android.inputmethod.latin.settings;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
public final class TestFragmentActivity extends Activity {
    public static final String EXTRA_SHOW_FRAGMENT = "show_fragment";
    public Fragment mFragment;

    @Override // android.app.Activity
    protected void onCreate(final Bundle savedState) {
        super.onCreate(savedState);
        String stringExtra = getIntent().getStringExtra(EXTRA_SHOW_FRAGMENT);
        if (stringExtra == null) {
            throw new IllegalArgumentException("No fragment name specified for testing");
        }
        this.mFragment = Fragment.instantiate(this, stringExtra);
        getFragmentManager().beginTransaction().add(this.mFragment, stringExtra).commit();
    }
}
