package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: ChildConnectionAllocator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class gn {
    public final Runnable a;
    public final Handler c;
    public final String d;
    public final String e;
    public final String f;
    public final boolean i;
    public final ArrayDeque b = new ArrayDeque();
    public b j = new c();
    public final boolean g = false;
    public final boolean h = false;

    /* JADX INFO: compiled from: ChildConnectionAllocator.java */
    public static class a extends gn {
        public final e k;
        public final e l;

        public a(Handler handler, gg3 gg3Var, String str) {
            super(handler, gg3Var, str, "org.chromium.content.app.SandboxedProcessService", null, false);
            this.k = new e(handler, gg3Var, str, "org.chromium.content.app.SandboxedProcessService0", null);
            this.l = new e(handler, gg3Var, str, "org.chromium.content.app.SandboxedProcessService1", null);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
        @Override // defpackage.gn
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final defpackage.qn b(android.content.Context r6, android.os.Bundle r7, defpackage.en r8) {
            /*
                r5 = this;
                gn$e r0 = r5.k
                qn r1 = r0.d(r6, r7)
                r2 = 0
                if (r1 != 0) goto La
                goto L24
            La:
                boolean r3 = r0.i
                java.lang.String r4 = "ChildProcessConnection.tryStart"
                org.chromium.base.TraceEvent.q(r4, r2)     // Catch: java.lang.Throwable -> L35
                boolean r3 = r1.c(r3)     // Catch: java.lang.Throwable -> L35
                if (r3 != 0) goto L1c
                org.chromium.base.TraceEvent.C(r4)
                r3 = 0
                goto L22
            L1c:
                r1.g = r8     // Catch: java.lang.Throwable -> L35
                org.chromium.base.TraceEvent.C(r4)
                r3 = 1
            L22:
                if (r3 != 0) goto L26
            L24:
                r1 = r2
                goto L2b
            L26:
                q8<qn> r0 = r0.k
                r0.add(r1)
            L2b:
                if (r1 == 0) goto L2e
                goto L34
            L2e:
                gn$e r5 = r5.l
                qn r1 = r5.b(r6, r7, r8)
            L34:
                return r1
            L35:
                r5 = move-exception
                org.chromium.base.TraceEvent.C(r4)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: gn.a.b(android.content.Context, android.os.Bundle, en):qn");
        }

        @Override // defpackage.gn
        public final void c(qn qnVar) {
            e eVar = this.k;
            if (eVar.k.contains(qnVar)) {
                eVar.c(qnVar);
                return;
            }
            e eVar2 = this.l;
            if (eVar2.k.contains(qnVar)) {
                eVar2.c(qnVar);
            }
        }
    }

    /* JADX INFO: compiled from: ChildConnectionAllocator.java */
    public interface b {
    }

    /* JADX INFO: compiled from: ChildConnectionAllocator.java */
    public static class c implements b {
    }

    /* JADX INFO: compiled from: ChildConnectionAllocator.java */
    public static class d extends gn {
        public final qn[] k;
        public final ArrayList<Integer> l;

        public d(Handler handler, gg3 gg3Var, String str, String str2, boolean z, int i) {
            super(handler, gg3Var, str, str2, null, z);
            this.k = new qn[i];
            this.l = new ArrayList<>(i);
            for (int i2 = 0; i2 < i; i2++) {
                this.l.add(Integer.valueOf(i2));
            }
        }

        @Override // defpackage.gn
        public final qn b(Context context, Bundle bundle, en enVar) {
            ArrayList<Integer> arrayList = this.l;
            if (arrayList.isEmpty()) {
                Log.w("cr_ChildConnAllocator", "Ran out of services to allocate.");
                return null;
            }
            int iIntValue = arrayList.remove(0).intValue();
            ComponentName componentName = new ComponentName(this.d, this.e + iIntValue);
            b bVar = this.j;
            boolean z = this.g;
            boolean z2 = this.h;
            ((c) bVar).getClass();
            qn qnVar = new qn(context, componentName, null, z, z2, bundle, null);
            this.k[iIntValue] = qnVar;
            qnVar.k(this.i, enVar);
            return qnVar;
        }

        @Override // defpackage.gn
        public final void c(qn qnVar) {
            qn[] qnVarArr = this.k;
            int iIndexOf = Arrays.asList(qnVarArr).indexOf(qnVar);
            if (iIndexOf == -1) {
                Log.e("cr_ChildConnAllocator", "Unable to find connection to free.");
            } else {
                qnVarArr[iIndexOf] = null;
                this.l.add(Integer.valueOf(iIndexOf));
            }
        }
    }

    /* JADX INFO: compiled from: ChildConnectionAllocator.java */
    public static class e extends gn {
        public final q8<qn> k;
        public int l;

        public e(Handler handler, gg3 gg3Var, String str, String str2, String str3) {
            super(handler, gg3Var, str, str2, str3, false);
            this.k = new q8<>(0);
        }

        @Override // defpackage.gn
        public final qn b(Context context, Bundle bundle, en enVar) {
            qn qnVarD = d(context, bundle);
            if (qnVarD == null) {
                return null;
            }
            this.k.add(qnVarD);
            qnVarD.k(this.i, enVar);
            return qnVarD;
        }

        @Override // defpackage.gn
        public final void c(qn qnVar) {
            this.k.remove(qnVar);
        }

        public final qn d(Context context, Bundle bundle) {
            if (this.k.g >= 5) {
                Log.w("cr_ChildConnAllocator", "Ran out of UIDs to allocate.");
                return null;
            }
            String str = this.d;
            ComponentName componentName = new ComponentName(str, this.e);
            String str2 = this.f;
            ComponentName componentName2 = str2 != null ? new ComponentName(str, str2) : null;
            String string = Integer.toString(this.l);
            this.l++;
            b bVar = this.j;
            boolean z = this.g;
            boolean z2 = this.h;
            ((c) bVar).getClass();
            return new qn(context, componentName, componentName2, z, z2, bundle, string);
        }
    }

    public gn(Handler handler, gg3 gg3Var, String str, String str2, String str3, boolean z) {
        this.c = handler;
        this.a = gg3Var;
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.i = z;
    }

    public static void a(Context context, String str, String str2) {
        if (Build.VERSION.SDK_INT >= 30 && !str.equals(context.getPackageName())) {
            try {
                Class.forName("android.webkit.WebViewUpdateService").getDeclaredMethod("getCurrentWebViewPackageName", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception e2) {
                Log.w("cr_ChildConnAllocator", "workAroundWebViewPackageVisibility failed", e2);
            }
        }
        try {
            context.getPackageManager().getServiceInfo(new ComponentName(str, str2.concat("0")), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            throw new uv("Illegal meta data value: the child service doesn't exist");
        }
    }

    public abstract qn b(Context context, Bundle bundle, en enVar);

    public abstract void c(qn qnVar);
}
