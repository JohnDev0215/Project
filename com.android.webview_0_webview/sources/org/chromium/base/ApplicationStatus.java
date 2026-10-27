package org.chromium.base;

import android.app.Activity;
import defpackage.AbstractC1788xs;
import defpackage.InterfaceC1843ys;
import defpackage.RunnableC1733ws;
import defpackage.Ys;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ApplicationStatus {
    public static InterfaceC1843ys c;
    public static final Map a = Collections.synchronizedMap(new HashMap());
    public static int b = 0;
    public static final Ys d = new Ys();
    public static final Ys e = new Ys();

    static {
        new Ys();
    }

    public static Activity a() {
        return null;
    }

    public static int b(Activity activity) {
        if (activity == null || ((AbstractC1788xs) a.get(activity)) == null) {
            return 6;
        }
        throw null;
    }

    public static int getStateForApplication() {
        synchronized (a) {
        }
        return 0;
    }

    public static boolean hasVisibleActivities() {
        int stateForApplication = getStateForApplication();
        return stateForApplication == 1 || stateForApplication == 2;
    }

    public static void registerThreadSafeNativeApplicationStateListener() {
        ThreadUtils.g(new RunnableC1733ws());
    }
}
