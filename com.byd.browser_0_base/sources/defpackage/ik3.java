package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import defpackage.kk3;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.chromium.chrome.browser.app.usb.UsbNotificationService;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.usb.R;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: UsbNotificationManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ik3 {
    public final jk3 a;
    public final so1 b;
    public final ArrayList d = new ArrayList();
    public final SharedPreferencesManager c = SharedPreferencesManager.getInstance();

    public ik3(NotificationManagerProxyImpl notificationManagerProxyImpl, kk3.a aVar) {
        this.a = aVar;
        this.b = notificationManagerProxyImpl;
    }

    public static void d(Context context, int i, WebContents webContents, GURL gurl, boolean z) {
        boolean zContains = false;
        boolean zMrjKaAvk = webContents == null ? false : N.MrjKaAvk(webContents);
        if (zMrjKaAvk) {
            zContains = true;
        } else {
            Set<String> setI = SharedPreferencesManager.getInstance().i("Chrome.USB.NotificationIds", null);
            if (setI != null && !setI.isEmpty()) {
                zContains = setI.contains(String.valueOf(i));
            }
        }
        if (zContains) {
            Intent intent = new Intent(context, (Class<?>) UsbNotificationService.class);
            intent.setAction("org.chromium.chrome.browser.app.usb.USB_UPDATE");
            intent.putExtra("NotificationId", i);
            intent.putExtra("NotificationUrl", gurl.j());
            intent.putExtra("NotificationIsConnected", zMrjKaAvk);
            intent.putExtra("NotificationIsIncognito", z);
            context.startService(intent);
        }
    }

    public final void a() {
        SharedPreferencesManager sharedPreferencesManager = this.c;
        Set<String> setI = sharedPreferencesManager.i("Chrome.USB.NotificationIds", null);
        if (setI == null) {
            return;
        }
        Iterator<String> it = setI.iterator();
        while (it.hasNext()) {
            this.b.d(Integer.parseInt(it.next()), "UsbNotificationManager");
        }
        sharedPreferencesManager.removeKey("Chrome.USB.NotificationIds");
    }

    public final void b(String str, int i, boolean z) {
        String string;
        Context contextC = xz.c();
        qq qqVarD = n3.D("usb", new o8(36, i, "UsbNotificationManager"));
        o8 o8VarA = o8.a(contextC, i, ((kk3.a) this.a).a(i), 0);
        qqVarD.h(false);
        qqVarD.o(true);
        qqVarD.n();
        qqVarD.c(o8VarA);
        qq qqVar = qqVarD;
        qqVar.p(R.drawable.gm_filled_usb_24);
        qqVar.k(contextC.getString(R.string.connected_to_usb_device_notification_title));
        if (z) {
            string = contextC.getString(R.string.usb_notification_content_text_incognito);
            qqVarD.r(contextC.getString(R.string.notification_incognito_tab));
        } else {
            string = contextC.getString(R.string.usb_notification_content_text, oj2.j(1, new GURL(str)));
        }
        qqVarD.j(string);
        lp1 lp1VarG = qqVarD.g(string);
        this.b.c(lp1VarG);
        this.d.add(Integer.valueOf(i));
        c(i, false);
        kp1.a().b(36, lp1VarG.a());
    }

    public final void c(int i, boolean z) {
        HashSet hashSet = new HashSet();
        SharedPreferencesManager sharedPreferencesManager = this.c;
        HashSet hashSet2 = new HashSet(sharedPreferencesManager.i("Chrome.USB.NotificationIds", hashSet));
        if (z && !hashSet2.isEmpty() && hashSet2.contains(String.valueOf(i))) {
            hashSet2.remove(String.valueOf(i));
        } else if (!z) {
            hashSet2.add(String.valueOf(i));
        }
        sharedPreferencesManager.p("Chrome.USB.NotificationIds", hashSet2);
    }
}
