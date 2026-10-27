package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;

/* JADX INFO: renamed from: Ce, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0040Ce implements InterfaceC0724ee {
    public final Context a;
    public final C0165Jd b;
    public final C0272Pd c;
    public final C0272Pd d;
    public final Map e;
    public Bundle h;
    public final Lock l;
    public final Set f = Collections.newSetFromMap(new WeakHashMap());
    public ConnectionResult i = null;
    public ConnectionResult j = null;
    public boolean k = false;
    public int m = 0;
    public final InterfaceC1882zc g = null;

    public C0040Ce(Context context, C0165Jd c0165Jd, Lock lock, Looper looper, AbstractC1278oc abstractC1278oc, Map map, Map map2, C0381Ve c0381Ve, AbstractC1552tc abstractC1552tc, InterfaceC1882zc interfaceC1882zc, ArrayList arrayList, ArrayList arrayList2, Map map3, Map map4) {
        this.a = context;
        this.b = c0165Jd;
        this.l = lock;
        this.c = new C0272Pd(context, c0165Jd, lock, looper, abstractC1278oc, map2, null, map4, null, arrayList2, new C0076Ee(this, null));
        this.d = new C0272Pd(context, this.b, lock, looper, abstractC1278oc, map, c0381Ve, map3, abstractC1552tc, arrayList, new C0058De(this, null));
        C0971j1 c0971j1 = new C0971j1();
        Iterator it = map2.keySet().iterator();
        while (it.hasNext()) {
            c0971j1.put((AbstractC1662vc) it.next(), this.c);
        }
        Iterator it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            c0971j1.put((AbstractC1662vc) it2.next(), this.d);
        }
        this.e = Collections.unmodifiableMap(c0971j1);
    }

    public static void d(C0040Ce c0040Ce) {
        ConnectionResult connectionResult;
        if (!f(c0040Ce.i)) {
            if (c0040Ce.i != null && f(c0040Ce.j)) {
                c0040Ce.d.disconnect();
                c0040Ce.c(c0040Ce.i);
                return;
            }
            ConnectionResult connectionResult2 = c0040Ce.i;
            if (connectionResult2 == null || (connectionResult = c0040Ce.j) == null) {
                return;
            }
            if (c0040Ce.d.l < c0040Ce.c.l) {
                connectionResult2 = connectionResult;
            }
            c0040Ce.c(connectionResult2);
            return;
        }
        if (f(c0040Ce.j) || c0040Ce.i()) {
            int i = c0040Ce.m;
            if (i == 1) {
                c0040Ce.h();
            } else if (i != 2) {
                Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
            } else {
                c0040Ce.b.c(c0040Ce.h);
                c0040Ce.h();
            }
            c0040Ce.m = 0;
            return;
        }
        ConnectionResult connectionResult3 = c0040Ce.j;
        if (connectionResult3 != null) {
            if (c0040Ce.m == 1) {
                c0040Ce.h();
            } else {
                c0040Ce.c(connectionResult3);
                c0040Ce.c.disconnect();
            }
        }
    }

    public static boolean f(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.f();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0021  */
    @Override // defpackage.InterfaceC0724ee
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a() {
        /*
            r2 = this;
            java.util.concurrent.locks.Lock r0 = r2.l
            r0.lock()
            Pd r0 = r2.c     // Catch: java.lang.Throwable -> L28
            Qd r0 = r0.k     // Catch: java.lang.Throwable -> L28
            boolean r0 = r0 instanceof defpackage.C1718wd     // Catch: java.lang.Throwable -> L28
            r1 = 1
            if (r0 == 0) goto L21
            Pd r0 = r2.d     // Catch: java.lang.Throwable -> L28
            Qd r0 = r0.k     // Catch: java.lang.Throwable -> L28
            boolean r0 = r0 instanceof defpackage.C1718wd     // Catch: java.lang.Throwable -> L28
            if (r0 != 0) goto L22
            boolean r0 = r2.i()     // Catch: java.lang.Throwable -> L28
            if (r0 != 0) goto L22
            int r0 = r2.m     // Catch: java.lang.Throwable -> L28
            if (r0 != r1) goto L21
            goto L22
        L21:
            r1 = 0
        L22:
            java.util.concurrent.locks.Lock r0 = r2.l
            r0.unlock()
            return r1
        L28:
            r0 = move-exception
            java.util.concurrent.locks.Lock r1 = r2.l
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0040Ce.a():boolean");
    }

    @Override // defpackage.InterfaceC0724ee
    public final AbstractC0343Tc b(AbstractC0343Tc abstractC0343Tc) {
        AbstractC1662vc abstractC1662vc = abstractC0343Tc.p;
        Cif.b(this.e.containsKey(abstractC1662vc), "GoogleApiClient is not configured to use the API required for this call.");
        if (!((C0272Pd) this.e.get(abstractC1662vc)).equals(this.d)) {
            return this.c.b(abstractC0343Tc);
        }
        if (!i()) {
            return this.d.b(abstractC0343Tc);
        }
        if (this.g == null) {
            abstractC0343Tc.k(new Status(1, 4, null, null));
            return abstractC0343Tc;
        }
        System.identityHashCode(this.b);
        Objects.requireNonNull((BaseGmsClient) this.g);
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public final void c(ConnectionResult connectionResult) {
        int i = this.m;
        if (i == 1) {
            h();
        } else if (i != 2) {
            Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
        } else {
            this.b.a(connectionResult);
            h();
        }
        this.m = 0;
    }

    @Override // defpackage.InterfaceC0724ee
    public final void disconnect() {
        this.j = null;
        this.i = null;
        this.m = 0;
        this.c.disconnect();
        this.d.disconnect();
        h();
    }

    @Override // defpackage.InterfaceC0724ee
    public final void e() {
        this.m = 2;
        this.k = false;
        this.j = null;
        this.i = null;
        this.c.k.e();
        this.d.k.e();
    }

    @Override // defpackage.InterfaceC0724ee
    public final void g(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("authClient").println(":");
        this.d.g(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append((CharSequence) str).append("anonClient").println(":");
        this.c.g(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    public final void h() {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            ((InterfaceC1058kd) it.next()).a();
        }
        this.f.clear();
    }

    public final boolean i() {
        ConnectionResult connectionResult = this.j;
        return connectionResult != null && connectionResult.t == 4;
    }
}
