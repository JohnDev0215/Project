package org.chromium.chrome.browser.customtabs.features;

import J.N;
import android.os.SystemClock;
import defpackage.oj2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: loaded from: classes.dex */
public class TabInteractionRecorder {
    public final long a;

    public TabInteractionRecorder(long j) {
        this.a = j;
    }

    @CalledByNative
    public static TabInteractionRecorder create(long j) {
        if (j == 0) {
            return null;
        }
        return new TabInteractionRecorder(j);
    }

    public final void a() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        long j = this.a;
        boolean z = N.Mmu6DTJh(j) || N.MAViEDX6(j);
        boolean zMmu6DTJh = N.Mmu6DTJh(j);
        boolean zMeaNEX9L = N.MeaNEX9L(j);
        boolean zMAViEDX6 = N.MAViEDX6(j);
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        sharedPreferencesManager.n(jUptimeMillis, "Chrome.CustomTabs.LastCloseTimestamp");
        sharedPreferencesManager.l("Chrome.CustomTabs.LastCloseTabInteraction", z);
        oj2.O("CustomTabs.HadInteractionOnClose.Form", zMmu6DTJh);
        oj2.O("CustomTabs.HadInteractionOnClose.FormStillActive", zMeaNEX9L);
        oj2.O("CustomTabs.HadInteractionOnClose.Navigation", zMAViEDX6);
    }

    public TabInteractionRecorder() {
        this(1L);
    }
}
