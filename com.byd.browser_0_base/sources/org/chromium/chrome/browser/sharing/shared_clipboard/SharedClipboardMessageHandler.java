package org.chromium.chrome.browser.sharing.shared_clipboard;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat$Builder;
import defpackage.b80;
import defpackage.kp1;
import defpackage.lp1;
import defpackage.n3;
import defpackage.o8;
import defpackage.q11;
import defpackage.qq;
import defpackage.rl2;
import defpackage.sp;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sharing.SharingServiceProxy;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: loaded from: classes2.dex */
public class SharedClipboardMessageHandler {

    public static final class TryAgainReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            new NotificationManagerProxyImpl(xz.a).d(11, "SharedClipboard");
            String strR = q11.r(intent, "SharedClipboard.EXTRA_DEVICE_GUID");
            String strR2 = q11.r(intent, "SharedClipboard.EXTRA_DEVICE_CLIENT_NAME");
            String strR3 = q11.r(intent, "android.intent.extra.TEXT");
            if (TextUtils.isEmpty(strR) || TextUtils.isEmpty(strR2) || TextUtils.isEmpty(strR3)) {
                return;
            }
            Context context2 = xz.a;
            String string = context2.getResources().getString(R$string.sharing_sending_notification_title, strR2);
            qq qqVarD = n3.D("sharing", new o8(18, 11, "SharedClipboard"));
            qqVarD.k(string);
            NotificationCompat$Builder notificationCompat$Builder = qqVarD.a;
            notificationCompat$Builder.p = "SharedClipboard";
            notificationCompat$Builder.u = context2.getColor(R$color.default_icon_color_accent1_baseline);
            notificationCompat$Builder.i = 1;
            qqVarD.p(R$drawable.ic_devices_16dp);
            notificationCompat$Builder.m = 0;
            notificationCompat$Builder.n = 0;
            notificationCompat$Builder.o = true;
            qqVarD.o(true);
            qqVarD.l(-1);
            lp1 lp1VarF = qqVarD.f();
            new NotificationManagerProxyImpl(context2).c(lp1VarF);
            kp1.a.a.b(18, lp1VarF.a);
            sp.a().d(false);
            if (SharingServiceProxy.a == null) {
                if (SharingServiceProxy.b == 0) {
                    N.MI$va2Pq(Profile.d());
                }
                SharingServiceProxy.a = new SharingServiceProxy();
            }
            b80 b80Var = new b80(strR, 5, strR3, strR2);
            long j = SharingServiceProxy.b;
            if (j == 0) {
                b80Var.run();
            } else {
                N.MBEvP57R(j, b80Var);
            }
        }
    }

    @CalledByNative
    public static void showNotification(String str) {
        Context context = xz.a;
        o8 o8VarB = o8.b(context, 0, new Intent(context, (Class<?>) TapReceiver.class), 134217728, false);
        Resources resources = context.getResources();
        rl2.a(18, "SharedClipboard", 10, o8VarB, null, null, null, TextUtils.isEmpty(str) ? resources.getString(R$string.shared_clipboard_notification_title_unknown_device) : resources.getString(R$string.shared_clipboard_notification_title, str), resources.getString(R$string.shared_clipboard_notification_text), R$drawable.ic_devices_16dp, R$drawable.shared_clipboard_40dp, R$color.default_icon_color_accent1_baseline, false);
    }

    public static final class TapReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
        }
    }
}
