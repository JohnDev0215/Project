package defpackage;

import android.content.Intent;
import android.os.IBinder;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.base.SplitCompatService;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: compiled from: BluetoothNotificationServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class kf extends SplitCompatService.a {
    public final a b = new a();
    public Cif c;

    /* JADX INFO: compiled from: BluetoothNotificationServiceImpl.java */
    public class a implements jf {
        public a() {
        }

        public final Intent a(int i) {
            return b.a(i, 1);
        }
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final IBinder a() {
        return null;
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final void b() {
        this.c = new Cif(new NotificationManagerProxyImpl(xz.a), this.b);
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final void c() {
        this.c.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004e  */
    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int e(android.content.Intent r11, int r12, int r13) {
        /*
            r10 = this;
            if r0 = r10.c
            jf r1 = r0.a
            if (r11 == 0) goto L79
            android.os.Bundle r2 = r11.getExtras()
            if (r2 != 0) goto Le
            goto L79
        Le:
            java.lang.String r2 = "org.chromium.chrome.browser.app.bluetooth.BLUETOOTH_UPDATE"
            java.lang.String r3 = r11.getAction()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L85
            java.lang.String r2 = "NotificationId"
            r3 = -1
            int r2 = r11.getIntExtra(r2, r3)
            java.lang.String r3 = "NotificationBluetoothType"
            r4 = 0
            int r3 = r11.getIntExtra(r3, r4)
            java.lang.String r5 = "NotificationUrl"
            java.lang.String r5 = r11.getStringExtra(r5)
            java.lang.String r6 = "NotificationIsIncognito"
            boolean r6 = r11.getBooleanExtra(r6, r4)
            android.util.SparseIntArray r7 = r0.d
            int r8 = r7.indexOfKey(r2)
            r9 = 1
            if (r8 < 0) goto L3f
            r8 = r9
            goto L40
        L3f:
            r8 = r4
        L40:
            if (r8 == 0) goto L4e
            int r8 = r7.get(r2)
            if (r8 == r3) goto L4a
            r8 = r9
            goto L4b
        L4a:
            r8 = r4
        L4b:
            if (r8 != 0) goto L4e
            goto L85
        L4e:
            int r8 = r7.indexOfKey(r2)
            if (r8 < 0) goto L55
            r4 = r9
        L55:
            if (r4 == 0) goto L64
            so1 r4 = r0.b
            java.lang.String r8 = "BluetoothNotificationManager"
            r4.d(r2, r8)
            r7.delete(r2)
            r0.f(r2, r9)
        L64:
            if (r3 == 0) goto L69
            r0.b(r2, r3, r6, r5)
        L69:
            int r0 = r7.size()
            if (r0 != 0) goto L85
            kf$a r1 = (kf.a) r1
            kf r0 = defpackage.kf.this
            org.chromium.chrome.browser.base.SplitCompatService r0 = r0.a
            r0.stopSelf(r13)
            goto L85
        L79:
            r0.a()
            kf$a r1 = (kf.a) r1
            kf r0 = defpackage.kf.this
            org.chromium.chrome.browser.base.SplitCompatService r0 = r0.a
            r0.stopSelf()
        L85:
            int r10 = super.e(r11, r12, r13)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kf.e(android.content.Intent, int, int):int");
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final boolean g(Intent intent) {
        this.c.a();
        return super.g(intent);
    }
}
