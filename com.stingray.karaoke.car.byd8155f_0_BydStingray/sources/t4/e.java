package t4;

import A.L;
import A4.i;
import A4.j;
import A7.m;
import E5.z;
import F8.K;
import J3.f;
import Q7.InterfaceC0649b;
import Q7.InterfaceC0654g;
import Q7.Q;
import S8.AbstractC0727y;
import S8.k0;
import U8.EnumC0810a;
import U8.l;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Trace;
import android.util.Log;
import androidx.lifecycle.EnumC0981t;
import e2.S;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h3.C1350a;
import h5.s;
import h6.n;
import h8.AbstractC1386c;
import i.g;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CancellationException;
import k3.A0;
import k3.C0;
import k3.C1671u0;
import k3.C1679y0;
import k3.C1681z0;
import k3.I0;
import k3.Q0;
import k3.RunnableC1665r0;
import k3.RunnableC1667s0;
import k3.V;
import k3.n1;
import l5.C1778f;
import n2.RunnableC1901a;
import n2.SurfaceHolderCallbackC1920u;
import n3.C1947l;
import r.C2257p;
import r8.C2309b;
import t.C2420d;
import t.C2422f;
import u.AbstractC2456D;
import u.C2484x;
import v1.C2537d;
import x3.AbstractC2673a;
import y0.C2738p;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class e implements j, s, G8.c {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static volatile e f22461p;
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f22462m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f22463n;
    public final Object o;

    public /* synthetic */ e(int i7, Object obj, Object obj2, boolean z6) {
        this.l = i7;
        this.f22462m = z6;
        this.f22463n = obj;
        this.o = obj2;
    }

    public static final void b(e eVar) {
        ((C2484x) eVar.f22463n).a();
        int i7 = 0;
        eVar.f22462m = false;
        V.d dVar = (V.d) eVar.o;
        int i10 = dVar.f9279n;
        if (i10 > 0) {
            Object[] objArr = dVar.l;
            do {
                ((InterfaceC2772a) objArr[i7]).a();
                i7++;
            } while (i7 < i10);
        }
        dVar.g();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void c(t4.e r15) {
        /*
            java.lang.Object r0 = r15.f22463n
            u.x r0 = (u.C2484x) r0
            java.lang.Object[] r1 = r0.f22644b
            long[] r2 = r0.f22643a
            int r3 = r2.length
            int r3 = r3 + (-2)
            r4 = 0
            if (r3 < 0) goto L63
            r5 = r4
        Lf:
            r6 = r2[r5]
            long r8 = ~r6
            r10 = 7
            long r8 = r8 << r10
            long r8 = r8 & r6
            r10 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r10
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L5e
            int r8 = r5 - r3
            int r8 = ~r8
            int r8 = r8 >>> 31
            r9 = 8
            int r8 = 8 - r8
            r10 = r4
        L29:
            if (r10 >= r8) goto L5c
            r11 = 255(0xff, double:1.26E-321)
            long r11 = r11 & r6
            r13 = 128(0x80, double:6.3E-322)
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 >= 0) goto L58
            int r11 = r5 << 3
            int r11 = r11 + r10
            r11 = r1[r11]
            l0.r r11 = (l0.C1727r) r11
            r11.getClass()
            t4.e r12 = l0.AbstractC1713d.E(r11)
            java.lang.Object r12 = r12.f22463n
            u.x r12 = (u.C2484x) r12
            java.lang.Object r12 = r12.e(r11)
            l0.q r12 = (l0.EnumC1726q) r12
            if (r12 == 0) goto L51
            r11.f18353A = r12
            goto L58
        L51:
            java.lang.String r15 = "committing a node that was not updated in the current transaction"
            l9.d.i0(r15)
            r15 = 0
            throw r15
        L58:
            long r6 = r6 >> r9
            int r10 = r10 + 1
            goto L29
        L5c:
            if (r8 != r9) goto L63
        L5e:
            if (r5 == r3) goto L63
            int r5 = r5 + 1
            goto Lf
        L63:
            r0.a()
            r15.f22462m = r4
            java.lang.Object r15 = r15.o
            V.d r15 = (V.d) r15
            r15.g()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: t4.e.c(t4.e):void");
    }

    private final void j(Throwable th) {
    }

    @Override // G8.c
    public boolean a(K k10, K k11) {
        InterfaceC0649b interfaceC0649b = (InterfaceC0649b) this.f22463n;
        m.f(interfaceC0649b, "$a");
        InterfaceC0649b interfaceC0649b2 = (InterfaceC0649b) this.o;
        m.f(interfaceC0649b2, "$b");
        m.f(k10, "c1");
        m.f(k11, "c2");
        if (k10.equals(k11)) {
            return true;
        }
        InterfaceC0654g interfaceC0654gN = k10.n();
        InterfaceC0654g interfaceC0654gN2 = k11.n();
        if (!(interfaceC0654gN instanceof Q) || !(interfaceC0654gN2 instanceof Q)) {
            return false;
        }
        return C2309b.l.d((Q) interfaceC0654gN, (Q) interfaceC0654gN2, this.f22462m, new L(interfaceC0649b, 18, interfaceC0649b2));
    }

    public boolean d(long j3) {
        Object obj;
        ArrayList arrayList = (ArrayList) ((C1778f) this.o).f18638m;
        int size = arrayList.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                obj = null;
                break;
            }
            obj = arrayList.get(i7);
            if (C2738p.a(((y0.s) obj).f24089a, j3)) {
                break;
            }
            i7++;
        }
        y0.s sVar = (y0.s) obj;
        if (sVar != null) {
            return sVar.f24094h;
        }
        return false;
    }

    public void e() throws IllegalAccessException, InvocationTargetException {
        ((l) this.f22463n).o(new CancellationException("onBack cancelled"), true);
        ((k0) this.o).g(null);
    }

    public int f() {
        z zVar = (z) this.o;
        int i7 = zVar.f1841b;
        int i10 = zVar.c;
        if (i7 < i10) {
            return 2;
        }
        return i7 > i10 ? 1 : 3;
    }

    public void g() {
        if (this.f22462m || ((HashSet) this.o).isEmpty()) {
            return;
        }
        A2.c cVar = (A2.c) this.f22463n;
        i iVar = (i) cVar.d;
        boolean z6 = false;
        cVar.f311b = ((ConnectivityManager) iVar.get()).getActiveNetwork() != null;
        try {
            ((ConnectivityManager) iVar.get()).registerDefaultNetworkCallback((f) cVar.f312e);
            z6 = true;
        } catch (RuntimeException e10) {
            if (Log.isLoggable("ConnectivityMonitor", 5)) {
                n.u("ConnectivityMonitor", "Failed to register callback", e10);
            }
        }
        this.f22462m = z6;
    }

    @Override // A4.j
    public Object get() {
        if (this.f22462m) {
            throw new IllegalStateException("Recursive Registry initialization! In your AppGlideModule and LibraryGlideModules, Make sure you're using the provided Registry rather calling glide.getRegistry()!");
        }
        h6.e.f("Glide registry");
        this.f22462m = true;
        try {
            return l9.d.M((com.bumptech.glide.b) this.f22463n, (ArrayList) this.o);
        } finally {
            this.f22462m = false;
            Trace.endSection();
        }
    }

    @Override // h5.s
    public void h(Throwable th) {
        switch (this.l) {
            case 5:
                if (!this.f22462m) {
                    AbstractC1346c.z("NotificationProvider", "Failed to load bitmap: " + th.getMessage());
                }
                break;
            case 6:
                if (th instanceof UnsupportedOperationException) {
                    AbstractC1346c.A("MediaSessionImpl", "UnsupportedOperationException: Make sure to implement MediaSession.Callback.onPlaybackResumption() if you add a media button receiver to your manifest or if you implement the recent media item contract with your MediaLibraryService.", th);
                } else {
                    AbstractC1346c.q("MediaSessionImpl", "Failure calling MediaSession.Callback.onPlaybackResumption(): " + th.getMessage(), th);
                }
                I0 i02 = (I0) this.o;
                AbstractC1343A.A(i02.f17811t);
                if (this.f22462m) {
                    i02.o((C1679y0) this.f22463n);
                }
                break;
        }
    }

    @Override // h5.s
    public void i(Object obj) {
        switch (this.l) {
            case 5:
                Bitmap bitmap = (Bitmap) obj;
                if (!this.f22462m) {
                    C2537d c2537d = (C2537d) this.f22463n;
                    c2537d.c(bitmap);
                    e7.f fVar = new e7.f(c2537d.b());
                    V v10 = (V) this.o;
                    C1671u0 c1671u0 = (C1671u0) v10.f17864m;
                    c1671u0.f18126e.execute(new RunnableC1667s0(v10.f17865n, 0, c1671u0, (A0) v10.o, fVar));
                }
                break;
            case 6:
                I0 i02 = (I0) this.o;
                i02.getClass();
                C1679y0 c1679y0 = (C1679y0) this.f22463n;
                i02.f17815x = c1679y0;
                n1.i(i02.f17811t, (C1681z0) obj);
                AbstractC1343A.A(i02.f17811t);
                if (this.f22462m) {
                    i02.o(c1679y0);
                }
                i02.f17815x = null;
                break;
            default:
                I0 i03 = ((Q0) this.o).f17843f;
                Handler handler = i03.l;
                C1679y0 c1679y02 = (C1679y0) this.f22463n;
                AbstractC1343A.K(handler, new C0(i03, c1679y02, new RunnableC1665r0(this, (C1681z0) obj, this.f22462m, c1679y02)));
                break;
        }
    }

    public void k() {
        t3.e eVar = (t3.e) this.f22463n;
        A9.b bVarG = eVar.g();
        if (bVarG.O0() != EnumC0981t.f12005m) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        bVarG.M0(new t3.b(eVar, 0));
        C2257p c2257p = (C2257p) this.o;
        c2257p.getClass();
        if (c2257p.f21542a) {
            throw new IllegalStateException("SavedStateRegistry was already attached.");
        }
        bVarG.M0(new C1947l(c2257p, 1));
        c2257p.f21542a = true;
        this.f22462m = true;
    }

    public void l(Bundle bundle) {
        if (!this.f22462m) {
            k();
        }
        A9.b bVarG = ((t3.e) this.f22463n).g();
        if (bVarG.O0().compareTo(EnumC0981t.o) >= 0) {
            throw new IllegalStateException(("performRestore cannot be called when owner is " + bVarG.O0()).toString());
        }
        C2257p c2257p = (C2257p) this.o;
        if (!c2257p.f21542a) {
            throw new IllegalStateException("You must call performAttach() before calling performRestore(Bundle).");
        }
        if (c2257p.f21543b) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        c2257p.f21544e = bundle != null ? bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key") : null;
        c2257p.f21543b = true;
    }

    public void m(Bundle bundle) {
        m.f(bundle, "outBundle");
        C2257p c2257p = (C2257p) this.o;
        c2257p.getClass();
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = (Bundle) c2257p.f21544e;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        C2422f c2422f = (C2422f) c2257p.d;
        c2422f.getClass();
        C2420d c2420d = new C2420d(c2422f);
        c2422f.f22424n.put(c2420d, Boolean.FALSE);
        while (c2420d.hasNext()) {
            Map.Entry entry = (Map.Entry) c2420d.next();
            bundle2.putBundle((String) entry.getKey(), ((t3.d) entry.getValue()).a());
        }
        if (bundle2.isEmpty()) {
            return;
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    public void n() {
        if (this.f22462m) {
            ((Context) this.f22463n).unregisterReceiver((RunnableC1901a) this.o);
            this.f22462m = false;
        }
    }

    public String toString() {
        switch (this.l) {
            case 1:
                StringBuilder sb = new StringBuilder("SingleSelectionLayout(isStartHandle=");
                sb.append(this.f22462m);
                sb.append(", crossed=");
                int iF = f();
                sb.append(iF != 1 ? iF != 2 ? iF != 3 ? "null" : "COLLAPSED" : "NOT_CROSSED" : "CROSSED");
                sb.append(", info=\n\t");
                sb.append((z) this.o);
                sb.append(')');
                return sb.toString();
            case 12:
                return super.toString() + "{numRequests=" + ((Set) this.f22463n).size() + ", isPaused=" + this.f22462m + "}";
            default:
                return super.toString();
        }
    }

    public /* synthetic */ e(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f22463n = obj;
        this.o = obj2;
    }

    public e(t3.e eVar) {
        this.l = 11;
        this.f22463n = eVar;
        this.o = new C2257p();
    }

    public e(int i7) {
        this.l = i7;
        switch (i7) {
            case 12:
                this.f22463n = Collections.newSetFromMap(new WeakHashMap());
                this.o = new HashSet();
                break;
            default:
                long[] jArr = AbstractC2456D.f22584a;
                this.f22463n = new C2484x();
                this.o = new V.d(new InterfaceC2772a[16]);
                break;
        }
    }

    public e(Context context, Handler handler, SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u) {
        this.l = 9;
        this.f22463n = context.getApplicationContext();
        this.o = new RunnableC1901a(this, handler, surfaceHolderCallbackC1920u);
    }

    public e(Context context, int i7) {
        this.l = i7;
        switch (i7) {
            case 4:
                this.f22463n = context;
                this.o = new C1350a(15);
                break;
            default:
                this.o = new HashSet();
                this.f22463n = new A2.c(new i(new P6.a(context, 13, false)), new d(this));
                break;
        }
    }

    public e(com.bumptech.glide.b bVar, ArrayList arrayList, AbstractC1386c abstractC1386c) {
        this.l = 2;
        this.f22463n = bVar;
        this.o = arrayList;
    }

    public e(X8.c cVar, boolean z6, InterfaceC2785n interfaceC2785n, g gVar) {
        this.l = 3;
        this.f22462m = z6;
        this.f22463n = AbstractC2673a.a(-2, 4, EnumC0810a.l);
        this.o = AbstractC0727y.w(cVar, null, null, new i.f(gVar, interfaceC2785n, this, null), 3);
    }

    public e(Q0 q02, C1679y0 c1679y0, boolean z6) {
        this.l = 7;
        this.o = q02;
        this.f22463n = c1679y0;
        this.f22462m = z6;
    }

    public e(I0 i02, C1679y0 c1679y0, boolean z6, S s6) {
        this.l = 6;
        this.o = i02;
        this.f22463n = c1679y0;
        this.f22462m = z6;
    }
}
