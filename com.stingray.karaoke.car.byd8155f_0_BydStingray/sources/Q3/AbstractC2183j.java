package q3;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import f1.AbstractC1252g;
import f1.C1253h;

/* JADX INFO: renamed from: q3.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2183j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C1253h f21249a = new C1253h();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f21250b = new Object();
    public static m6.b c = null;

    public static long a(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        return Build.VERSION.SDK_INT >= 33 ? AbstractC2181h.a(packageManager, context).lastUpdateTime : packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    public static m6.b b() {
        m6.b bVar = new m6.b();
        c = bVar;
        C1253h c1253h = f21249a;
        c1253h.getClass();
        if (AbstractC1252g.f14541q.J(c1253h, null, bVar)) {
            AbstractC1252g.c(c1253h);
        }
        return c;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0047 A[Catch: all -> 0x0012, TryCatch #2 {, blocks: (B:9:0x000c, B:11:0x0010, B:16:0x0019, B:22:0x002e, B:34:0x0041, B:36:0x0047, B:37:0x004a, B:39:0x004c, B:45:0x006f, B:51:0x0092, B:52:0x0096, B:54:0x00a7, B:62:0x00b5, B:64:0x00bb, B:79:0x00d7, B:82:0x00dd, B:86:0x00e7, B:88:0x00f0, B:91:0x00f9, B:92:0x00fc, B:90:0x00f6, B:56:0x00ac, B:57:0x00af, B:94:0x00fe, B:95:0x0101, B:32:0x003f, B:31:0x003c), top: B:104:0x000c, inners: #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x004c A[Catch: all -> 0x0012, TryCatch #2 {, blocks: (B:9:0x000c, B:11:0x0010, B:16:0x0019, B:22:0x002e, B:34:0x0041, B:36:0x0047, B:37:0x004a, B:39:0x004c, B:45:0x006f, B:51:0x0092, B:52:0x0096, B:54:0x00a7, B:62:0x00b5, B:64:0x00bb, B:79:0x00d7, B:82:0x00dd, B:86:0x00e7, B:88:0x00f0, B:91:0x00f9, B:92:0x00fc, B:90:0x00f6, B:56:0x00ac, B:57:0x00af, B:94:0x00fe, B:95:0x0101, B:32:0x003f, B:31:0x003c), top: B:104:0x000c, inners: #5, #6 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c(android.content.Context r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: q3.AbstractC2183j.c(android.content.Context, boolean):void");
    }
}
