package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Xt {
    public final Runnable a;
    public final Handler c;
    public final String d;
    public final String e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final Queue b = new ArrayDeque();
    public Tt i = new Ut(null);

    public Xt(Handler handler, Runnable runnable, String str, String str2, boolean z, boolean z2, boolean z3, Rt rt) {
        this.c = handler;
        this.a = runnable;
        this.d = str;
        this.e = str2;
        this.f = z;
        this.g = z2;
        this.h = z3;
    }

    public static void a(Context context, String str, String str2) {
        try {
            context.getPackageManager().getServiceInfo(new ComponentName(str, str2 + "0"), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            throw new RuntimeException("Illegal meta data value: the child service doesn't exist");
        }
    }

    public static Xt b(Context context, Handler handler, Runnable runnable, String str, String str2, String str3, boolean z, boolean z2, boolean z3) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            int i = applicationInfo.metaData != null ? applicationInfo.metaData.getInt(str3, -1) : -1;
            if (i < 0) {
                throw new RuntimeException("Illegal meta data value for number of child services");
            }
            a(context, str, str2);
            return new Vt(handler, runnable, str, str2, z, z2, z3, i, null);
        } catch (PackageManager.NameNotFoundException unused) {
            throw new RuntimeException("Could not get application info.");
        }
    }

    public abstract C1406qu c(Context context, Bundle bundle, InterfaceC1351pu interfaceC1351pu);

    public abstract void d(C1406qu c1406qu);

    public abstract int e();
}
