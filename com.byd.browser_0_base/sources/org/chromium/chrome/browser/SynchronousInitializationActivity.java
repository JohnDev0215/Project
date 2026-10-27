package org.chromium.chrome.browser;

import android.os.Bundle;
import defpackage.sp;

/* JADX INFO: loaded from: classes.dex */
public abstract class SynchronousInitializationActivity extends ChromeBaseAppCompatActivity {
    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        sp.a().d(false);
        super.onCreate(bundle);
    }
}
