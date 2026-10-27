package org.chromium.chrome.browser.price_tracking;

import android.content.Context;
import android.text.TextUtils;
import defpackage.e42;
import defpackage.g20;
import defpackage.n3;
import defpackage.u32;
import defpackage.xz;
import java.util.Locale;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: loaded from: classes2.dex */
public class PriceTrackingNotificationBridge {
    public final a a;
    public final u32 b;

    public PriceTrackingNotificationBridge(a aVar, PriceDropNotificationManagerImpl priceDropNotificationManagerImpl) {
        this.a = aVar;
        this.b = priceDropNotificationManagerImpl;
    }

    public static String a(e42 e42Var) {
        String strT = e42Var.T();
        long jS = e42Var.S();
        if (TextUtils.isEmpty(strT)) {
            return null;
        }
        g20 g20Var = new g20(strT, Locale.getDefault());
        String strN = n3.N(jS, g20Var);
        g20Var.a();
        return strN;
    }

    @CalledByNative
    public static PriceTrackingNotificationBridge create(long j) {
        Context context = xz.a;
        return new PriceTrackingNotificationBridge(new a(context, new NotificationManagerProxyImpl(context)), n3.B());
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x020c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01c0 A[SYNTHETIC] */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void showNotification(byte[] r23) {
        /*
            Method dump skipped, instruction units count: 598
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.price_tracking.PriceTrackingNotificationBridge.showNotification(byte[]):void");
    }
}
