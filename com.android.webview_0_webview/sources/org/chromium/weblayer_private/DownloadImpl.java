package org.chromium.weblayer_private;

import J.N;
import android.R;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import defpackage.AbstractBinderC1549tR;
import defpackage.AbstractC1054kS;
import defpackage.C0719eS;
import defpackage.C1659vR;
import defpackage.CQ;
import defpackage.InterfaceC0831gS;
import defpackage.InterfaceC0942iR;
import defpackage.InterfaceC1769xR;
import defpackage.Is;
import defpackage.M1;
import defpackage.Q1;
import defpackage.V1;
import defpackage.XQ;
import defpackage.ZQ;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;
import org.chromium.base.ContentUriUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class DownloadImpl extends AbstractBinderC1549tR {
    public static boolean h = false;
    public static final HashMap i = new HashMap();
    public final String a;
    public final InterfaceC1769xR b;
    public final InterfaceC0942iR c;
    public long d;
    public boolean e;
    public final int f;
    public M1 g;

    public DownloadImpl(String str, InterfaceC1769xR interfaceC1769xR, long j, int i2) {
        this.a = str;
        this.b = interfaceC1769xR;
        this.d = j;
        this.f = i2;
        if (interfaceC1769xR == null) {
            this.c = null;
        } else {
            try {
                this.c = ((C1659vR) interfaceC1769xR).d(this);
            } catch (RemoteException e) {
                throw new ZQ(e);
            }
        }
        N.MCQjxfAX(this.d, this);
    }

    public static void d(Context context, Intent intent, CQ cq) {
        if (!intent.getAction().equals("org.chromium.weblayer.downloads.OPEN")) {
            ProfileImpl profileImplA = cq.a(intent.getStringExtra("org.chromium.weblayer.downloads.NOTIFICATION_PROFILE"));
            if (profileImplA.f) {
                v(intent);
                return;
            } else {
                profileImplA.h.add(intent);
                N.M1$JPkux(profileImplA.b);
                return;
            }
        }
        String stringExtra = intent.getStringExtra("org.chromium.weblayer.downloads.NOTIFICATION_LOCATION");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        String stringExtra2 = intent.getStringExtra("org.chromium.weblayer.downloads.NOTIFICATION_MIME_TYPE");
        Intent intent2 = new Intent("android.intent.action.VIEW");
        if (TextUtils.isEmpty(stringExtra2)) {
            intent2.setData(ContentUriUtils.b(new File(stringExtra)));
        } else {
            intent2.setDataAndType(ContentUriUtils.b(new File(stringExtra)), stringExtra2);
        }
        intent2.addFlags(1);
        intent2.addFlags(2);
        intent2.addFlags(268435456);
        try {
            context.startActivity(intent2);
        } catch (ActivityNotFoundException unused) {
        }
    }

    private void onNativeDestroyed() {
        this.d = 0L;
        i.remove(Integer.valueOf(this.f));
    }

    public static void v(Intent intent) {
        int intExtra = intent.getIntExtra("org.chromium.weblayer.downloads.NOTIFICATION_ID", -1);
        DownloadImpl downloadImpl = (DownloadImpl) i.get(Integer.valueOf(intExtra));
        if (downloadImpl == null) {
            return;
        }
        if (intent.getAction().equals("org.chromium.weblayer.downloads.PAUSE")) {
            AbstractC1054kS.a();
            downloadImpl.w();
            N.Mmh9t8Wp(downloadImpl.d, downloadImpl);
            downloadImpl.x();
            return;
        }
        if (intent.getAction().equals("org.chromium.weblayer.downloads.RESUME")) {
            AbstractC1054kS.a();
            downloadImpl.w();
            N.MBdFHh_o(downloadImpl.d, downloadImpl);
            downloadImpl.x();
            return;
        }
        if (!intent.getAction().equals("org.chromium.weblayer.downloads.CANCEL")) {
            if (intent.getAction().equals("org.chromium.weblayer.downloads.DELETE")) {
                i.remove(Integer.valueOf(intExtra));
            }
        } else {
            AbstractC1054kS.a();
            downloadImpl.w();
            N.M7ycbCea(downloadImpl.d, downloadImpl);
            downloadImpl.x();
        }
    }

    public final Intent c() {
        if (WebLayerFactoryImpl.c() < 83) {
            try {
                return ((C1659vR) this.b).f();
            } catch (RemoteException e) {
                throw new ZQ(e);
            }
        }
        InterfaceC0831gS interfaceC0831gS = XQ.d;
        if (interfaceC0831gS == null) {
            throw new IllegalStateException("WebLayer should have been initialized already.");
        }
        try {
            return ((C0719eS) interfaceC0831gS).c();
        } catch (RemoteException e2) {
            throw new ZQ(e2);
        }
    }

    public String f() {
        AbstractC1054kS.a();
        w();
        return N.MBNW0Bg1(this.d, this);
    }

    public int i() {
        AbstractC1054kS.a();
        w();
        int iMg9vWlA3 = N.Mg9vWlA3(this.d, this);
        if (iMg9vWlA3 == 0) {
            return 0;
        }
        if (iMg9vWlA3 == 1) {
            return 1;
        }
        if (iMg9vWlA3 != 2) {
            return iMg9vWlA3 != 3 ? 4 : 3;
        }
        return 2;
    }

    public long u() {
        AbstractC1054kS.a();
        w();
        return N.MLQnpV49(this.d, this);
    }

    public final void w() {
        if (this.d == 0) {
            throw new IllegalStateException("Using Download after native destroyed");
        }
    }

    public final void x() {
        if (this.g == null) {
            return;
        }
        Context context = Is.a;
        V1 v1 = context == null ? null : new V1(context);
        String strF = f();
        if (!TextUtils.isEmpty(strF)) {
            M1 m1 = this.g;
            String name = new File(strF).getName();
            Objects.requireNonNull(m1);
            m1.d = M1.c(name);
        }
        boolean z = false;
        if (u() > 0) {
            AbstractC1054kS.a();
            w();
            int iMLPJbQwI = (int) ((N.MLPJbQwI(this.d, this) * 100) / u());
            M1 m12 = this.g;
            m12.j = 100;
            m12.k = iMLPJbQwI;
            m12.l = false;
        }
        int i2 = i();
        if (i2 == 3) {
            if (v1 != null) {
                v1.b.cancel(null, this.f);
            }
            this.g = null;
            return;
        }
        this.g.b.clear();
        if (i2 == 1) {
            Intent intentC = c();
            intentC.setAction("org.chromium.weblayer.downloads.OPEN");
            intentC.putExtra("org.chromium.weblayer.downloads.NOTIFICATION_ID", this.f);
            intentC.putExtra("org.chromium.weblayer.downloads.NOTIFICATION_PROFILE", this.a);
            intentC.putExtra("org.chromium.weblayer.downloads.NOTIFICATION_LOCATION", f());
            AbstractC1054kS.a();
            w();
            intentC.putExtra("org.chromium.weblayer.downloads.NOTIFICATION_MIME_TYPE", N.MiYZ62wR(this.d, this));
            PendingIntent broadcast = PendingIntent.getBroadcast(Is.a, this.f, intentC, 0);
            M1 m13 = this.g;
            m13.j = 100;
            m13.k = 100;
            m13.l = false;
            m13.d(2, false);
            m13.t.icon = R.drawable.stat_sys_download_done;
            m13.f = broadcast;
            m13.d(16, true);
        } else if (i2 == 4) {
            M1 m14 = this.g;
            Objects.requireNonNull(m14);
            m14.e = M1.c("Download failed");
            m14.d(2, false);
            m14.t.icon = R.drawable.stat_sys_download_done;
        } else if (i2 == 0) {
            Intent intentC2 = c();
            intentC2.setAction("org.chromium.weblayer.downloads.PAUSE");
            intentC2.putExtra("org.chromium.weblayer.downloads.NOTIFICATION_ID", this.f);
            intentC2.putExtra("org.chromium.weblayer.downloads.NOTIFICATION_PROFILE", this.a);
            PendingIntent broadcast2 = PendingIntent.getBroadcast(Is.a, this.f, intentC2, 0);
            M1 m15 = this.g;
            m15.a(0, "Pause", broadcast2);
            m15.t.icon = R.drawable.stat_sys_download;
        } else if (i2 == 2) {
            Intent intentC3 = c();
            intentC3.setAction("org.chromium.weblayer.downloads.RESUME");
            intentC3.putExtra("org.chromium.weblayer.downloads.NOTIFICATION_ID", this.f);
            intentC3.putExtra("org.chromium.weblayer.downloads.NOTIFICATION_PROFILE", this.a);
            PendingIntent broadcast3 = PendingIntent.getBroadcast(Is.a, this.f, intentC3, 0);
            M1 m16 = this.g;
            m16.a(0, "Resume", broadcast3);
            m16.t.icon = R.drawable.ic_media_pause;
        }
        if (i2 == 0 || i2 == 2) {
            Intent intentC4 = c();
            intentC4.setAction("org.chromium.weblayer.downloads.CANCEL");
            intentC4.putExtra("org.chromium.weblayer.downloads.NOTIFICATION_ID", this.f);
            intentC4.putExtra("org.chromium.weblayer.downloads.NOTIFICATION_PROFILE", this.a);
            this.g.a(0, "Cancel", PendingIntent.getBroadcast(Is.a, this.f, intentC4, 0));
        }
        if (v1 != null) {
            int i3 = this.f;
            Notification notificationB = this.g.b();
            Bundle bundle = notificationB.extras;
            if (bundle != null && bundle.getBoolean("android.support.useSideChannel")) {
                z = true;
            }
            if (!z) {
                v1.b.notify(null, i3, notificationB);
            } else {
                v1.a(new Q1(v1.a.getPackageName(), i3, null, notificationB));
                v1.b.cancel(null, i3);
            }
        }
    }
}
