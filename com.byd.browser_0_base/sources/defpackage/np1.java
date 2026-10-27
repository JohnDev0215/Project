package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.NotificationCompat$Builder;
import androidx.core.app.a;
import androidx.core.graphics.drawable.IconCompat;
import java.util.Collections;

/* JADX INFO: compiled from: NotificationWrapperCompatBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public class np1 implements mp1 {
    public final NotificationCompat$Builder a;
    public final o8 b;
    public final Context c;

    public np1(Context context, String str, zm zmVar, o8 o8Var) {
        if (str != null) {
            zmVar.a(Collections.emptyList(), Collections.singletonList(str));
        }
        this.a = new NotificationCompat$Builder(context, str);
        this.b = o8Var;
        this.c = context;
    }

    public final np1 d(int i, String str, PendingIntent pendingIntent) {
        NotificationCompat$Builder notificationCompat$Builder = this.a;
        if (i != 0) {
            PorterDuff.Mode mode = IconCompat.k;
            Context context = this.c;
            context.getClass();
            notificationCompat$Builder.b.add(new a.C0006a(IconCompat.b(context.getResources(), context.getPackageName(), i), str, pendingIntent).a());
        } else {
            notificationCompat$Builder.b.add(new a(i == 0 ? null : IconCompat.b(null, "", i), str, pendingIntent, new Bundle(), null, null, true, 0, true, false, false));
        }
        return this;
    }

    public final Notification e() {
        try {
            try {
                Notification notificationA = this.a.a();
                oj2.O("Notifications.Android.Build", true);
                return notificationA;
            } catch (NullPointerException e) {
                Log.e("cr_NotifCompatBuilder", "Failed to build notification.", e);
                oj2.O("Notifications.Android.Build", false);
                return null;
            }
        } catch (Throwable th) {
            oj2.O("Notifications.Android.Build", false);
            throw th;
        }
    }

    public final lp1 f() {
        return new lp1(e(), this.b);
    }

    public final lp1 g(String str) {
        lo1 lo1Var = new lo1(this.a);
        lo1Var.d = NotificationCompat$Builder.b(str);
        NotificationCompat$Builder notificationCompat$Builder = lo1Var.a;
        return new lp1(notificationCompat$Builder != null ? notificationCompat$Builder.a() : null, this.b);
    }

    public final np1 h(boolean z) {
        this.a.c(16, z);
        return this;
    }

    public final np1 i(Bitmap bitmap, CharSequence charSequence) {
        IconCompat iconCompat;
        ko1 ko1Var = new ko1();
        if (bitmap == null) {
            iconCompat = null;
        } else {
            IconCompat iconCompat2 = new IconCompat(1);
            iconCompat2.b = bitmap;
            iconCompat = iconCompat2;
        }
        ko1Var.d = iconCompat;
        ko1Var.b = NotificationCompat$Builder.b(charSequence);
        ko1Var.c = true;
        NotificationCompat$Builder notificationCompat$Builder = this.a;
        if (notificationCompat$Builder.k != ko1Var) {
            notificationCompat$Builder.k = ko1Var;
            ko1Var.f(notificationCompat$Builder);
        }
        return this;
    }

    public final np1 j(CharSequence charSequence) {
        NotificationCompat$Builder notificationCompat$Builder = this.a;
        notificationCompat$Builder.getClass();
        notificationCompat$Builder.f = NotificationCompat$Builder.b(charSequence);
        return this;
    }

    public final np1 k(CharSequence charSequence) {
        NotificationCompat$Builder notificationCompat$Builder = this.a;
        notificationCompat$Builder.getClass();
        notificationCompat$Builder.e = NotificationCompat$Builder.b(charSequence);
        return this;
    }

    public final np1 l(int i) {
        Notification notification = this.a.A;
        notification.defaults = i;
        if ((i & 4) != 0) {
            notification.flags |= 1;
        }
        return this;
    }

    public final np1 m(Bitmap bitmap) {
        IconCompat iconCompat;
        NotificationCompat$Builder notificationCompat$Builder = this.a;
        if (bitmap == null) {
            iconCompat = null;
        } else {
            notificationCompat$Builder.getClass();
            IconCompat iconCompat2 = new IconCompat(1);
            iconCompat2.b = bitmap;
            iconCompat = iconCompat2;
        }
        notificationCompat$Builder.h = iconCompat;
        return this;
    }

    public final np1 n() {
        this.a.r = true;
        return this;
    }

    public final np1 o(boolean z) {
        this.a.c(2, z);
        return this;
    }

    public final np1 p(int i) {
        this.a.A.icon = i;
        return this;
    }

    public final np1 q(Icon icon) {
        IconCompat iconCompatB;
        IconCompat iconCompat;
        Context context = this.c;
        PorterDuff.Mode mode = IconCompat.k;
        icon.getClass();
        int type = icon.getType();
        if (type != 2) {
            if (type == 4) {
                Uri uri = icon.getUri();
                uri.getClass();
                String string = uri.toString();
                string.getClass();
                iconCompat = new IconCompat(4);
                iconCompat.b = string;
            } else if (type != 6) {
                iconCompatB = new IconCompat(-1);
                iconCompatB.b = icon;
            } else {
                Uri uri2 = icon.getUri();
                uri2.getClass();
                String string2 = uri2.toString();
                string2.getClass();
                iconCompat = new IconCompat(6);
                iconCompat.b = string2;
            }
            iconCompatB = iconCompat;
        } else {
            String resPackage = icon.getResPackage();
            try {
                iconCompatB = IconCompat.b(IconCompat.e(context, resPackage), resPackage, icon.getResId());
            } catch (Resources.NotFoundException unused) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        }
        NotificationCompat$Builder notificationCompat$Builder = this.a;
        notificationCompat$Builder.C = iconCompatB.g(notificationCompat$Builder.a);
        return this;
    }

    public final np1 r(CharSequence charSequence) {
        NotificationCompat$Builder notificationCompat$Builder = this.a;
        notificationCompat$Builder.getClass();
        notificationCompat$Builder.l = NotificationCompat$Builder.b(charSequence);
        return this;
    }
}
