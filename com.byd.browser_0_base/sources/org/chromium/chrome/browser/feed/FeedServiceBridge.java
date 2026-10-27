package org.chromium.chrome.browser.feed;

import J.N;
import android.content.Context;
import android.util.DisplayMetrics;
import defpackage.a8;
import defpackage.ar;
import defpackage.jl0;
import defpackage.oj2;
import defpackage.sw2;
import defpackage.ul0;
import defpackage.xz;
import defpackage.zb2;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.app.feed.FeedServiceDependencyProviderFactoryImpl;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;

/* JADX INFO: loaded from: classes.dex */
public final class FeedServiceBridge {
    public static a8 a;

    @CalledByNative
    public static void clearAll() {
        ul0 ul0VarB = ul0.b();
        if (ul0VarB.c == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (sw2 sw2Var : ul0VarB.c) {
            if (sw2Var.isActive()) {
                arrayList.add(sw2Var);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((sw2) it.next()).d();
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((sw2) it2.next()).b();
        }
    }

    @CalledByNative
    public static double[] getDisplayMetrics() {
        DisplayMetrics displayMetrics = xz.a.getResources().getDisplayMetrics();
        return new double[]{displayMetrics.density, displayMetrics.widthPixels, displayMetrics.heightPixels};
    }

    @CalledByNative
    public static String getLanguageTag() {
        return xz.a.getResources().getConfiguration().getLocales().get(0).toLanguageTag();
    }

    @CalledByNative
    public static int getTabGroupEnabledState() {
        jl0 jl0Var;
        if (a == null && (jl0Var = (jl0) FeedServiceDependencyProviderFactoryImpl.class.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0])) != null) {
            a = jl0Var.a();
        }
        a.getClass();
        Context context = xz.a;
        if (!zb2.d(context) && oj2.D(context)) {
            return CachedFeatureFlags.b(ar.d.b("TabGridLayoutAndroid:enable_tab_group_auto_creation"), true) ? 1 : 2;
        }
        return 0;
    }

    public static class UnreadContentObserver {
        public long a = N.MrqoIpvG(this, true);

        @CalledByNative
        public void hasUnreadContentChanged(boolean z) {
        }
    }

    @CalledByNative
    public static void prefetchImage(String str) {
    }
}
