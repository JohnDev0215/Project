package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: FontRequestWorker.java */
/* JADX INFO: loaded from: classes.dex */
public final class uo0 {
    public static final ua1<String, Typeface> a = new ua1<>(16);
    public static final ThreadPoolExecutor b;
    public static final Object c;
    public static final wm2<String, ArrayList<wx<a>>> d;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new gb2());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        b = threadPoolExecutor;
        c = new Object();
        d = new wm2<>();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static uo0.a a(java.lang.String r6, android.content.Context r7, defpackage.po0 r8, int r9) {
        /*
            ua1<java.lang.String, android.graphics.Typeface> r0 = defpackage.uo0.a
            java.lang.Object r1 = r0.b(r6)
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            if (r1 == 0) goto L10
            uo0$a r6 = new uo0$a
            r6.<init>(r1)
            return r6
        L10:
            wo0 r8 = defpackage.oo0.a(r7, r8)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L59
            r1 = 1
            r2 = -3
            xo0[] r3 = r8.b
            int r8 = r8.a
            if (r8 == 0) goto L21
            if (r8 == r1) goto L1f
            goto L34
        L1f:
            r8 = -2
            goto L3c
        L21:
            if (r3 == 0) goto L3b
            int r8 = r3.length
            if (r8 != 0) goto L27
            goto L3b
        L27:
            int r8 = r3.length
            r1 = 0
            r4 = r1
        L2a:
            if (r4 >= r8) goto L3b
            r5 = r3[r4]
            int r5 = r5.e
            if (r5 == 0) goto L38
            if (r5 >= 0) goto L36
        L34:
            r8 = r2
            goto L3c
        L36:
            r8 = r5
            goto L3c
        L38:
            int r4 = r4 + 1
            goto L2a
        L3b:
            r8 = r1
        L3c:
            if (r8 == 0) goto L44
            uo0$a r6 = new uo0$a
            r6.<init>(r8)
            return r6
        L44:
            android.graphics.Typeface r7 = defpackage.gh3.a(r7, r3, r9)
            if (r7 == 0) goto L53
            r0.c(r6, r7)
            uo0$a r6 = new uo0$a
            r6.<init>(r7)
            return r6
        L53:
            uo0$a r6 = new uo0$a
            r6.<init>(r2)
            return r6
        L59:
            uo0$a r6 = new uo0$a
            r7 = -1
            r6.<init>(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uo0.a(java.lang.String, android.content.Context, po0, int):uo0$a");
    }

    public static Typeface b(Context context, po0 po0Var, int i, hb2 hb2Var, rl rlVar) {
        String str = po0Var.f + "-" + i;
        Typeface typefaceB = a.b(str);
        if (typefaceB != null) {
            rlVar.b.post(new pl(rlVar.a, typefaceB));
            return typefaceB;
        }
        ro0 ro0Var = new ro0(rlVar);
        synchronized (c) {
            wm2<String, ArrayList<wx<a>>> wm2Var = d;
            ArrayList<wx<a>> arrayList = wm2Var.get(str);
            if (arrayList != null) {
                arrayList.add(ro0Var);
                return null;
            }
            ArrayList<wx<a>> arrayList2 = new ArrayList<>();
            arrayList2.add(ro0Var);
            wm2Var.put(str, arrayList2);
            so0 so0Var = new so0(str, context, po0Var, i);
            Executor executor = hb2Var;
            if (hb2Var == null) {
                executor = b;
            }
            executor.execute(new ib2(Looper.myLooper() == null ? new Handler(Looper.getMainLooper()) : new Handler(), so0Var, new to0(str)));
            return null;
        }
    }

    /* JADX INFO: compiled from: FontRequestWorker.java */
    public static final class a {
        public final Typeface a;
        public final int b;

        public a(int i) {
            this.a = null;
            this.b = i;
        }

        @SuppressLint({"WrongConstant"})
        public a(Typeface typeface) {
            this.a = typeface;
            this.b = 0;
        }
    }
}
