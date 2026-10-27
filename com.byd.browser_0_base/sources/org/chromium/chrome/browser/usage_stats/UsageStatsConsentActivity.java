package org.chromium.chrome.browser.usage_stats;

import android.content.ComponentName;
import android.os.Bundle;
import android.text.TextUtils;
import defpackage.dk3;
import defpackage.x10;
import org.chromium.chrome.browser.SynchronousInitializationActivity;

/* JADX INFO: loaded from: classes2.dex */
public class UsageStatsConsentActivity extends SynchronousInitializationActivity {
    public static final /* synthetic */ int I = 0;

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onAttachedToWindow() {
        new dk3(this, TextUtils.equals(getIntent().getAction(), "org.chromium.chrome.browser.usage_stats.action.UNAUTHORIZE"), new x10(18, this)).a();
    }

    @Override // org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ComponentName callingActivity = getCallingActivity();
        if (callingActivity == null || !TextUtils.equals("com.google.android.apps.wellbeing", callingActivity.getPackageName())) {
            finish();
        }
    }
}
