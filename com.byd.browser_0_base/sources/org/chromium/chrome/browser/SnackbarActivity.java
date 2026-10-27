package org.chromium.chrome.browser;

import android.R;
import android.os.Bundle;
import android.view.ViewGroup;
import defpackage.wp2;

/* JADX INFO: loaded from: classes.dex */
public abstract class SnackbarActivity extends SynchronousInitializationActivity implements wp2.c {
    public wp2 I;

    @Override // org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.I = new wp2(this, (ViewGroup) findViewById(R.id.content), null);
    }

    @Override // wp2.c
    public final wp2 q0() {
        return this.I;
    }
}
