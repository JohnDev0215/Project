package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.app.NotificationCompat$Builder;
import defpackage.kp1;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: compiled from: SharingNotificationUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rl2 {
    public static void a(int i, String str, int i2, o8 o8Var, o8 o8Var2, o8 o8Var3, o8 o8Var4, String str2, String str3, int i3, int i4, int i5, boolean z) {
        Bitmap bitmapDecodeResource;
        Context context = xz.a;
        Resources resources = context.getResources();
        qq qqVarD = n3.D("sharing", new o8(i, i2, str));
        qqVarD.k(str2);
        qqVarD.j(str3);
        lo1 lo1Var = new lo1();
        lo1Var.d = NotificationCompat$Builder.b(str3);
        NotificationCompat$Builder notificationCompat$Builder = qqVarD.a;
        if (notificationCompat$Builder.k != lo1Var) {
            notificationCompat$Builder.k = lo1Var;
            lo1Var.f(notificationCompat$Builder);
        }
        notificationCompat$Builder.u = context.getColor(i5);
        notificationCompat$Builder.p = str;
        notificationCompat$Builder.i = 1;
        qqVarD.p(i3);
        qqVarD.h(true);
        qqVarD.l(-1);
        if (o8Var != null) {
            if (!z || Build.VERSION.SDK_INT < 31) {
                qqVarD.c(o8Var);
            } else {
                notificationCompat$Builder.g = (PendingIntent) o8Var.c;
            }
        }
        if (o8Var2 != null) {
            qqVarD.a(o8Var2);
        }
        if (o8Var3 != null) {
            qqVarD.b(R$drawable.ic_checkmark_24dp, resources.getString(R$string.submit), o8Var3, 26);
        }
        if (o8Var4 != null) {
            qqVarD.b(R$drawable.ic_cancel_circle, resources.getString(R$string.cancel), o8Var4, 27);
        }
        if (i4 != 0 && (bitmapDecodeResource = BitmapFactory.decodeResource(resources, i4)) != null) {
            qqVarD.m(bitmapDecodeResource);
        }
        lp1 lp1VarF = qqVarD.f();
        new NotificationManagerProxyImpl(context).c(lp1VarF);
        kp1.a.a.b(i, lp1VarF.a);
    }
}
