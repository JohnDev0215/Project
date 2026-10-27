package defpackage;

import android.content.Context;
import android.util.SparseIntArray;
import defpackage.kf;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.chromium.chrome.browser.bluetooth.R;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;
import org.chromium.url.GURL;

/* JADX INFO: renamed from: if, reason: invalid class name */
/* JADX INFO: compiled from: BluetoothNotificationManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class Cif {
    public final jf a;
    public final so1 b;
    public final SparseIntArray d = new SparseIntArray();
    public final SharedPreferencesManager c = SharedPreferencesManager.getInstance();

    public Cif(NotificationManagerProxyImpl notificationManagerProxyImpl, kf.a aVar) {
        this.a = aVar;
        this.b = notificationManagerProxyImpl;
    }

    public static int c(int i) {
        if (i == 1) {
            return R.drawable.ic_bluetooth_connected;
        }
        if (i == 2) {
            return R.drawable.gm_filled_bluetooth_searching_24;
        }
        return 0;
    }

    public static String d(int i) {
        return xz.c().getString(i == 1 ? R.string.connected_to_bluetooth_device_notification_title : i == 2 ? R.string.scanning_for_bluetooth_devices_notification_title : 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0015  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void e(android.content.Context r4, int r5, org.chromium.content_public.browser.WebContents r6, org.chromium.url.GURL r7, boolean r8) {
        /*
            r0 = 1
            r1 = 0
            if (r6 != 0) goto L5
            goto L15
        L5:
            boolean r2 = J.N.MkAMKLD2(r6)
            if (r2 == 0) goto Ld
            r6 = 2
            goto L16
        Ld:
            boolean r6 = J.N.MkukzSD4(r6)
            if (r6 == 0) goto L15
            r6 = r0
            goto L16
        L15:
            r6 = r1
        L16:
            java.lang.String r2 = "WebBluetoothNewPermissionsBackend"
            boolean r2 = J.N.Mudil8Bg(r2)
            if (r2 != 0) goto L1f
            goto L3f
        L1f:
            if (r6 == 0) goto L22
            goto L40
        L22:
            org.chromium.chrome.browser.preferences.SharedPreferencesManager r0 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()
            java.lang.String r2 = "Chrome.Bluetooth.NotificationIds"
            r3 = 0
            java.util.Set r0 = r0.i(r2, r3)
            if (r0 == 0) goto L3f
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L36
            goto L3f
        L36:
            java.lang.String r1 = java.lang.String.valueOf(r5)
            boolean r0 = r0.contains(r1)
            goto L40
        L3f:
            r0 = r1
        L40:
            if (r0 != 0) goto L43
            return
        L43:
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<org.chromium.chrome.browser.app.bluetooth.BluetoothNotificationService> r1 = org.chromium.chrome.browser.app.bluetooth.BluetoothNotificationService.class
            r0.<init>(r4, r1)
            java.lang.String r1 = "org.chromium.chrome.browser.app.bluetooth.BLUETOOTH_UPDATE"
            r0.setAction(r1)
            java.lang.String r1 = "NotificationId"
            r0.putExtra(r1, r5)
            java.lang.String r5 = "NotificationUrl"
            java.lang.String r7 = r7.j()
            r0.putExtra(r5, r7)
            java.lang.String r5 = "NotificationBluetoothType"
            r0.putExtra(r5, r6)
            java.lang.String r5 = "NotificationIsIncognito"
            r0.putExtra(r5, r8)
            r4.startService(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Cif.e(android.content.Context, int, org.chromium.content_public.browser.WebContents, org.chromium.url.GURL, boolean):void");
    }

    public final void a() {
        SharedPreferencesManager sharedPreferencesManager = this.c;
        Set<String> setI = sharedPreferencesManager.i("Chrome.Bluetooth.NotificationIds", null);
        if (setI == null) {
            return;
        }
        Iterator<String> it = setI.iterator();
        while (it.hasNext()) {
            this.b.d(Integer.parseInt(it.next()), "BluetoothNotificationManager");
        }
        sharedPreferencesManager.removeKey("Chrome.Bluetooth.NotificationIds");
    }

    public final void b(int i, int i2, boolean z, String str) {
        String string;
        Context contextC = xz.c();
        qq qqVarD = n3.D("bluetooth", new o8(35, i, "BluetoothNotificationManager"));
        o8 o8VarA = o8.a(contextC, i, ((kf.a) this.a).a(i), 0);
        qqVarD.h(false);
        qqVarD.o(true);
        qqVarD.n();
        qqVarD.c(o8VarA);
        qq qqVar = qqVarD;
        qqVar.p(c(i2));
        qqVar.k(d(i2));
        if (z) {
            string = contextC.getString(R.string.bluetooth_notification_content_text_incognito);
            qqVarD.r(contextC.getString(R.string.notification_incognito_tab));
        } else {
            string = contextC.getString(R.string.bluetooth_notification_content_text, oj2.j(1, new GURL(str)));
        }
        qqVarD.j(string);
        lp1 lp1VarG = qqVarD.g(string);
        this.b.c(lp1VarG);
        this.d.put(i, i2);
        f(i, false);
        kp1.a().b(35, lp1VarG.a());
    }

    public final void f(int i, boolean z) {
        HashSet hashSet = new HashSet();
        SharedPreferencesManager sharedPreferencesManager = this.c;
        HashSet hashSet2 = new HashSet(sharedPreferencesManager.i("Chrome.Bluetooth.NotificationIds", hashSet));
        if (z && !hashSet2.isEmpty() && hashSet2.contains(String.valueOf(i))) {
            hashSet2.remove(String.valueOf(i));
        } else if (!z) {
            hashSet2.add(String.valueOf(i));
        }
        sharedPreferencesManager.p("Chrome.Bluetooth.NotificationIds", hashSet2);
    }
}
