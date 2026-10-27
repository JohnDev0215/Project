package com.android.inputmethod.latin.setup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
public final class SetupActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        intent.setClass(this, SetupWizardActivity.class);
        intent.addFlags(335544320);
        startActivity(intent);
        if (isFinishing()) {
            return;
        }
        finish();
    }
}
