package p;

import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class jo1 {
    public final int a;
    public final Object b;
    public final Object c;
    public final Object d;
    public final String e;
    public final x41 f;
    public final String g;
    public final Serializable h;
    public final Object i;

    public jo1(String str, String str2, String str3, String str4, e31 e31Var, String str5, String str6, String str7, int i) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = e31Var;
        this.g = str5;
        this.h = str6;
        this.i = str7;
        this.a = i;
    }

    public jo1(xn1 xn1Var) {
        ArrayList arrayList;
        this.h = new ArrayList();
        Bundle bundle = new Bundle();
        this.i = bundle;
        this.d = xn1Var;
        Context context = xn1Var.a;
        this.b = context;
        int i = Build.VERSION.SDK_INT;
        String str = xn1Var.j;
        Notification.Builder builderA = ho1.a(context, str);
        this.c = builderA;
        Notification notification = xn1Var.l;
        builderA.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(xn1Var.e).setContentText(null).setContentInfo(null).setContentIntent(null).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & 128) != 0).setNumber(0).setProgress(0, 0, false);
        fo1.b(builderA, null);
        ao1.b(ao1.d(ao1.c(builderA, null), false), xn1Var.f);
        Iterator it = xn1Var.b.iterator();
        if (it.hasNext()) {
            u4.z(it.next());
            throw null;
        }
        Bundle bundle2 = xn1Var.i;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        this.e = null;
        this.f = null;
        bo1.a(builderA, xn1Var.g);
        do1.i(builderA, false);
        do1.g(builderA, null);
        do1.j(builderA, null);
        do1.h(builderA, false);
        this.a = 0;
        eo1.b(builderA, null);
        eo1.c(builderA, 0);
        eo1.f(builderA, 0);
        eo1.d(builderA, null);
        eo1.e(builderA, notification.sound, notification.audioAttributes);
        ArrayList arrayList2 = xn1Var.c;
        ArrayList arrayList3 = xn1Var.m;
        if (i < 28) {
            if (arrayList2 == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList(arrayList2.size());
                Iterator it2 = arrayList2.iterator();
                if (it2.hasNext()) {
                    u4.z(it2.next());
                    throw null;
                }
            }
            if (arrayList != null) {
                if (arrayList3 == null) {
                    arrayList3 = arrayList;
                } else {
                    qb qbVar = new qb(arrayList3.size() + arrayList.size());
                    qbVar.addAll(arrayList);
                    qbVar.addAll(arrayList3);
                    arrayList3 = new ArrayList(qbVar);
                }
            }
        }
        if (arrayList3 != null && !arrayList3.isEmpty()) {
            Iterator it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                eo1.a((Notification.Builder) this.c, (String) it3.next());
            }
        }
        this.g = null;
        ArrayList arrayList4 = xn1Var.d;
        if (arrayList4.size() > 0) {
            if (xn1Var.i == null) {
                xn1Var.i = new Bundle();
            }
            Bundle bundle3 = xn1Var.i.getBundle("android.car.EXTENSIONS");
            bundle3 = bundle3 == null ? new Bundle() : bundle3;
            Bundle bundle4 = new Bundle(bundle3);
            Bundle bundle5 = new Bundle();
            if (arrayList4.size() > 0) {
                Integer.toString(0);
                u4.z(arrayList4.get(0));
                Object obj = ko1.a;
                new Bundle();
                throw null;
            }
            bundle3.putBundle("invisible_actions", bundle5);
            bundle4.putBundle("invisible_actions", bundle5);
            if (xn1Var.i == null) {
                xn1Var.i = new Bundle();
            }
            xn1Var.i.putBundle("android.car.EXTENSIONS", bundle3);
            ((Bundle) this.i).putBundle("android.car.EXTENSIONS", bundle4);
        }
        int i2 = Build.VERSION.SDK_INT;
        co1.a((Notification.Builder) this.c, xn1Var.i);
        go1.e((Notification.Builder) this.c, null);
        ho1.b((Notification.Builder) this.c, 0);
        ho1.e((Notification.Builder) this.c, null);
        ho1.f((Notification.Builder) this.c, null);
        ho1.g((Notification.Builder) this.c, 0L);
        ho1.d((Notification.Builder) this.c, 0);
        if (!TextUtils.isEmpty(str)) {
            ((Notification.Builder) this.c).setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
        if (i2 >= 28) {
            Iterator it4 = arrayList2.iterator();
            if (it4.hasNext()) {
                u4.z(it4.next());
                throw null;
            }
        }
        if (i2 >= 29) {
            io1.a((Notification.Builder) this.c, xn1Var.k);
            io1.b((Notification.Builder) this.c, null);
        }
    }
}
