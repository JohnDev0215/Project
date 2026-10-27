package defpackage;

import android.content.Intent;
import android.os.IBinder;
import java.util.ArrayList;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.base.SplitCompatService;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: compiled from: UsbNotificationServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class kk3 extends SplitCompatService.a {
    public final a b = new a();
    public ik3 c;

    /* JADX INFO: compiled from: UsbNotificationServiceImpl.java */
    public class a implements jk3 {
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
        this.c = new ik3(new NotificationManagerProxyImpl(xz.a), this.b);
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final void c() {
        this.c.a();
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final int e(Intent intent, int i, int i2) {
        ik3 ik3Var = this.c;
        jk3 jk3Var = ik3Var.a;
        if (intent == null || intent.getExtras() == null) {
            ik3Var.a();
            kk3.this.a.stopSelf();
        } else if ("org.chromium.chrome.browser.app.usb.USB_UPDATE".equals(intent.getAction())) {
            int intExtra = intent.getIntExtra("NotificationId", -1);
            boolean booleanExtra = intent.getBooleanExtra("NotificationIsConnected", false);
            String stringExtra = intent.getStringExtra("NotificationUrl");
            boolean booleanExtra2 = intent.getBooleanExtra("NotificationIsIncognito", false);
            ArrayList arrayList = ik3Var.d;
            if (arrayList.contains(Integer.valueOf(intExtra))) {
                ik3Var.b.d(intExtra, "UsbNotificationManager");
                arrayList.remove(arrayList.indexOf(Integer.valueOf(intExtra)));
                ik3Var.c(intExtra, true);
            }
            if (booleanExtra) {
                ik3Var.b(stringExtra, intExtra, booleanExtra2);
            }
            if (arrayList.size() == 0) {
                kk3.this.a.stopSelf(i2);
            }
        }
        return super.e(intent, i, i2);
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final boolean g(Intent intent) {
        this.c.a();
        return super.g(intent);
    }
}
