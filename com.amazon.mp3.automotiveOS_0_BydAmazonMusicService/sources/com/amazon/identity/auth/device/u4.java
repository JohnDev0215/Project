package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class u4 {
    public static final String e = "com.amazon.identity.auth.device.u4";
    public static final long f = TimeUnit.MILLISECONDS.convert(15, TimeUnit.SECONDS);
    public static u4 g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f603a;
    public final u7 b;
    public final CountDownLatch c;
    public final AtomicBoolean d;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                u4.a(u4.this);
            } finally {
                u4.this.c.countDown();
            }
        }
    }

    public u4(Context context) {
        t5 t5VarA = t5.a(context);
        this.f603a = t5VarA;
        this.b = ((v7) t5VarA.getSystemService("dcp_data_storage_factory")).a();
        this.c = new CountDownLatch(1);
        this.d = new AtomicBoolean(false);
    }

    public static synchronized u4 a(Context context) {
        if (g == null) {
            g = new u4(context.getApplicationContext());
        }
        return g;
    }

    public void b() {
        boolean z = true;
        if (this.d.getAndSet(true)) {
            Log.i(ga.a(e), "Common Data has already been initialized");
            return;
        }
        t5 t5Var = this.f603a;
        if (t9.c(t5Var) && !t9.f(t5Var)) {
            ga.a(e);
            z = false;
        }
        if (!z) {
            ga.a(e);
            return;
        }
        ga.a(e);
        va.f637a.execute(new a());
    }

    public final void c() {
        if (!this.d.get()) {
            Log.i(ga.a(e), "Common Info Generator not initialized yet, starting init");
            b();
        }
        try {
            if (this.c.await(f, TimeUnit.MILLISECONDS)) {
                return;
            }
            Log.e(ga.a(e), "We timed out waiting for common info to be generated");
        } catch (InterruptedException e2) {
            Log.e(ga.a(e), "We were interrupted waiting for common info to be generated", e2);
        }
    }

    public String a() {
        c();
        String strB = this.b.b("dcp.only.protected.store", "dcp.only.encrypt.key");
        if (strB == null) {
            String str = e;
            Log.e(ga.a(str), "Cannot generate the token key", new Throwable());
        }
        return strB;
    }

    public static void a(u4 u4Var) {
        int i;
        Integer numValueOf;
        if (t9.c(u4Var.f603a)) {
            t4.a(u4Var.f603a).a();
            Log.i(ga.a(e), String.format("Central model has no race conditions in terms of common info and hence can be generated locally. Generated version %d", 1));
            return;
        }
        String strB = u4Var.b.b("dcp.third.party.device.state", "info.version");
        ga.a(t4.d);
        try {
            i = Integer.parseInt(strB);
        } catch (NumberFormatException unused) {
            i = 0;
        }
        if (i >= 1) {
            String str = e;
            String.format("No need to generate more common info. Our needed version is %d and currently we have version %d", 1, Integer.valueOf(i));
            ga.a(str);
            return;
        }
        String str2 = e;
        String.format("%s now do generateCommonInfo", u4Var.f603a.getPackageName());
        ga.a(str2);
        Iterator it = ((ArrayList) MAPApplicationInformationQueryer.a(u4Var.f603a).c()).iterator();
        while (true) {
            if (it.hasNext()) {
                j5 j5Var = (j5) it.next();
                String str3 = e;
                ga.a(str3, "Calling Package %s to generate common info", j5Var.b);
                try {
                    j5Var.toString();
                    ga.a(str3);
                    numValueOf = Integer.valueOf(j5Var.b());
                    break;
                } catch (RemoteMAPException e2) {
                    Log.w(ga.a(e), "Failed to initialize common info from " + j5Var.b, e2);
                    MAPApplicationInformationQueryer.a(u4Var.f603a).d();
                }
            } else {
                Log.e(ga.a(e), "Cannot find other package to generate common info from.");
                numValueOf = null;
                break;
            }
        }
        if (numValueOf == null || numValueOf.intValue() < 1) {
            String str4 = e;
            Object[] objArr = new Object[2];
            objArr[0] = 1;
            objArr[1] = numValueOf != null ? Integer.toString(numValueOf.intValue()) : "<Not Found>";
            Log.e(ga.a(str4), String.format("The main MAP app cannot generate version high enough to function properly. We need version %d or higher, but got %s.", objArr));
        }
    }
}
