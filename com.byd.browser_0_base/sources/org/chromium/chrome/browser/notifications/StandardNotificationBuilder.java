package org.chromium.chrome.browser.notifications;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import androidx.core.app.NotificationCompat$Builder;
import defpackage.lo1;
import defpackage.lp1;
import defpackage.n3;
import defpackage.o8;
import defpackage.qq;
import java.util.Iterator;
import org.chromium.chrome.browser.notifications.a;

/* JADX INFO: loaded from: classes2.dex */
public class StandardNotificationBuilder extends a {
    public final Context w;

    public StandardNotificationBuilder(Context context) {
        super(context.getResources());
        this.w = context;
    }

    @Override // org.chromium.chrome.browser.notifications.a
    public final lp1 c(o8 o8Var) {
        qq qqVarD = n3.D(this.g, o8Var);
        qqVarD.k(this.d);
        qqVarD.j(this.e);
        qqVarD.r(this.f);
        CharSequence charSequence = this.h;
        NotificationCompat$Builder notificationCompat$Builder = qqVarD.a;
        notificationCompat$Builder.A.tickerText = NotificationCompat$Builder.b(charSequence);
        Bitmap bitmap = this.i;
        if (bitmap != null) {
            qqVarD.i(bitmap, this.e);
        } else {
            CharSequence charSequence2 = this.e;
            lo1 lo1Var = new lo1();
            lo1Var.d = NotificationCompat$Builder.b(charSequence2);
            if (notificationCompat$Builder.k != lo1Var) {
                notificationCompat$Builder.k = lo1Var;
                lo1Var.f(notificationCompat$Builder);
            }
        }
        Bitmap bitmapB = this.v;
        CharSequence charSequence3 = this.f;
        if (bitmapB != null && bitmapB.getWidth() != 0) {
            int width = bitmapB.getWidth();
            int i = this.b;
            int i2 = this.a;
            if (width > i2 || bitmapB.getHeight() > i) {
                bitmapB = Bitmap.createScaledBitmap(bitmapB, i2, i, false);
            }
        } else if (charSequence3 != null) {
            bitmapB = this.c.b(charSequence3.toString(), true);
        } else {
            bitmapB = null;
        }
        qqVarD.m(bitmapB);
        int i3 = this.j;
        Bitmap bitmap2 = this.k;
        if (bitmap2 != null) {
            qqVarD.q(Icon.createWithBitmap(bitmap2));
        } else {
            qqVarD.p(i3);
        }
        qqVarD.c(this.m);
        qqVarD.a(this.n);
        Iterator it = this.o.iterator();
        while (it.hasNext()) {
            a.a(qqVarD, (a.C0095a) it.next());
        }
        a.C0095a c0095a = this.p;
        if (c0095a != null) {
            a.a(qqVarD, c0095a);
        }
        notificationCompat$Builder.i = 0;
        qqVarD.l(this.q);
        long[] jArr = this.r;
        if (jArr != null) {
            notificationCompat$Builder.A.vibrate = jArr;
        }
        notificationCompat$Builder.B = this.s;
        notificationCompat$Builder.A.when = this.t;
        notificationCompat$Builder.j = true;
        notificationCompat$Builder.c(8, !this.u);
        CharSequence charSequence4 = this.f;
        if (charSequence4 != null) {
            notificationCompat$Builder.p = "Web:" + ((Object) charSequence4);
        }
        qq qqVarD2 = n3.D(this.g, null);
        qqVarD2.j(this.w.getString(R$string.notification_hidden_text));
        qqVarD2.p(R$drawable.ic_chrome);
        qqVarD2.r(this.f);
        Bitmap bitmap3 = this.k;
        if (bitmap3 != null) {
            qqVarD2.q(Icon.createWithBitmap(bitmap3.copy(bitmap3.getConfig(), true)));
        }
        notificationCompat$Builder.w = qqVarD2.e();
        return qqVarD.f();
    }
}
