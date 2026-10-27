package org.chromium.chrome.browser.tab;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.display.DisplayAndroidManager;

/* JADX INFO: loaded from: classes2.dex */
public class TabUtils {
    public static Rect a(Context context) {
        Rect rect = new Rect();
        Point point = new Point();
        DisplayAndroidManager.b(context).getSize(point);
        Resources resources = context.getResources();
        try {
            point.y -= resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
        } catch (Resources.NotFoundException unused) {
        }
        rect.set(0, resources.getDimensionPixelSize(org.chromium.chrome.R$dimen.custom_tabs_control_container_height), point.x, point.y);
        return rect;
    }

    public static Activity b(Tab tab) {
        WindowAndroid windowAndroidV;
        WebContents webContentsC = tab != null ? tab.c() : null;
        if (webContentsC == null || webContentsC.isDestroyed() || (windowAndroidV = webContentsC.V()) == null) {
            return null;
        }
        return (Activity) windowAndroidV.k().get();
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static float c(android.content.Context r2) {
        /*
            android.content.res.Resources r0 = r2.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.orientation
            r1 = 1
            if (r0 == r1) goto L22
            vj1 r0 = defpackage.vj1.h
            android.app.Activity r2 = (android.app.Activity) r2
            r0.getClass()
            boolean r2 = defpackage.vj1.o(r2)
            if (r2 == 0) goto L1b
            goto L22
        L1b:
            rb0 r2 = defpackage.oj2.f
            double r0 = r2.c()
            goto L28
        L22:
            rb0 r2 = defpackage.oj2.e
            double r0 = r2.c()
        L28:
            float r2 = (float) r0
            r0 = 1056964608(0x3f000000, float:0.5)
            r1 = 1073741824(0x40000000, float:2.0)
            float r2 = defpackage.a8.o(r2, r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tab.TabUtils.c(android.content.Context):float");
    }

    public static void d(int i, Tab tab, boolean z, boolean z2) {
        tab.c().m().s(i, z, !tab.isNativePage());
        if (z2) {
            int i2 = z ? 2 : 1;
            if (N.MJSt3Ocq(Profile.b(tab.c()), 73) == z) {
                i2 = 0;
            }
            CriticalPersistedTabData.m(tab).r(i2);
        }
    }

    @CalledByNative
    public static boolean isHardwareKeyboardAvailable(Tab tab) {
        int i = tab.getContext().getResources().getConfiguration().keyboard;
        return i == 2 || i == 3;
    }
}
