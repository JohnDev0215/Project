package org.chromium.content.browser.accessibility;

import android.os.Handler;
import android.provider.Settings;
import defpackage.C0646dA;
import defpackage.Is;
import defpackage.It;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BrowserAccessibilityState {
    public static void recordAccessibilityHistograms() {
        float f = Settings.Global.getFloat(Is.a.getContentResolver(), "animator_duration_scale", -1.0f);
        It.d("Accessibility.Android.AnimationsEnabled2", f < 0.0f ? 0 : f > 0.0f ? 2 : 1, 3);
    }

    public static void registerAnimatorDurationScaleObserver() {
        Handler handler = new Handler(ThreadUtils.d());
        Is.a.getContentResolver().registerContentObserver(Settings.Global.getUriFor("animator_duration_scale"), false, new C0646dA(handler));
    }
}
