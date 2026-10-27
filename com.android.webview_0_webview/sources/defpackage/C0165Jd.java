package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.Lock;

/* JADX INFO: renamed from: Jd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0165Jd extends AbstractC0146Ic implements InterfaceC0780fe {
    public final Lock b;
    public boolean c;
    public final C1885zf d;
    public final int f;
    public final Context g;
    public final Looper h;
    public volatile boolean j;
    public final HandlerC0254Od m;
    public final C1223nc n;
    public C0557be o;
    public final Map p;
    public final C0381Ve r;
    public final Map s;
    public final AbstractC1552tc t;
    public final ArrayList v;
    public Integer w;
    public final C1444re y;
    public final InterfaceC1830yf z;
    public InterfaceC0724ee e = null;
    public final Queue i = new LinkedList();
    public long k = 120000;
    public long l = 5000;
    public Set q = new HashSet();
    public final C0891hd u = new C0891hd();
    public Set x = null;

    public C0165Jd(Context context, Lock lock, Looper looper, C0381Ve c0381Ve, C1223nc c1223nc, AbstractC1552tc abstractC1552tc, Map map, List list, List list2, Map map2, int i, int i2, ArrayList arrayList) {
        this.w = null;
        C0201Ld c0201Ld = new C0201Ld(this);
        this.z = c0201Ld;
        this.g = context;
        this.b = lock;
        this.c = false;
        this.d = new C1885zf(looper, c0201Ld);
        this.h = looper;
        this.m = new HandlerC0254Od(this, looper);
        this.n = c1223nc;
        this.f = i;
        if (i >= 0) {
            this.w = Integer.valueOf(i2);
        }
        this.s = map;
        this.p = map2;
        this.v = arrayList;
        this.y = new C1444re(map2);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            InterfaceC0110Gc interfaceC0110Gc = (InterfaceC0110Gc) it.next();
            C1885zf c1885zf = this.d;
            Objects.requireNonNull(c1885zf);
            Cif.f(interfaceC0110Gc);
            synchronized (c1885zf.i) {
                if (c1885zf.b.contains(interfaceC0110Gc)) {
                    String strValueOf = String.valueOf(interfaceC0110Gc);
                    StringBuilder sb = new StringBuilder(strValueOf.length() + 62);
                    sb.append("registerConnectionCallbacks(): listener ");
                    sb.append(strValueOf);
                    sb.append(" is already registered");
                    Log.w("GmsClientEvents", sb.toString());
                } else {
                    c1885zf.b.add(interfaceC0110Gc);
                }
            }
            if (c1885zf.a.a()) {
                Handler handler = c1885zf.h;
                handler.sendMessage(handler.obtainMessage(1, interfaceC0110Gc));
            }
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            this.d.a((InterfaceC0128Hc) it2.next());
        }
        this.r = c0381Ve;
        this.t = abstractC1552tc;
    }

    public static int l(Iterable iterable, boolean z) {
        Iterator it = iterable.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (((InterfaceC1882zc) it.next()).requiresSignIn()) {
                z2 = true;
            }
        }
        return z2 ? 1 : 3;
    }

    public static void m(C0165Jd c0165Jd) {
        c0165Jd.b.lock();
        try {
            if (c0165Jd.j) {
                c0165Jd.n();
            }
        } finally {
            c0165Jd.b.unlock();
        }
    }

    public static String q(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNKNOWN" : "SIGN_IN_MODE_NONE" : "SIGN_IN_MODE_OPTIONAL" : "SIGN_IN_MODE_REQUIRED";
    }

    @Override // defpackage.InterfaceC0780fe
    public final void a(ConnectionResult connectionResult) {
        C1223nc c1223nc = this.n;
        Context context = this.g;
        int i = connectionResult.t;
        Objects.requireNonNull(c1223nc);
        if (!AbstractC1388qc.b(context, i)) {
            o();
        }
        if (this.j) {
            return;
        }
        C1885zf c1885zf = this.d;
        Cif.d(c1885zf.h, "onConnectionFailure must only be called on the Handler thread");
        c1885zf.h.removeMessages(1);
        synchronized (c1885zf.i) {
            ArrayList arrayList = new ArrayList(c1885zf.d);
            int i2 = c1885zf.f.get();
            int size = arrayList.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList.get(i3);
                i3++;
                InterfaceC0128Hc interfaceC0128Hc = (InterfaceC0128Hc) obj;
                if (c1885zf.e && c1885zf.f.get() == i2) {
                    if (c1885zf.d.contains(interfaceC0128Hc)) {
                        interfaceC0128Hc.i(connectionResult);
                    }
                }
                break;
            }
        }
        this.d.b();
    }

    @Override // defpackage.InterfaceC0780fe
    public final void b(int i, boolean z) {
        if (i == 1 && !z && !this.j) {
            this.j = true;
            if (this.o == null) {
                try {
                    this.o = this.n.i(this.g.getApplicationContext(), new C0236Nd(this));
                } catch (SecurityException unused) {
                }
            }
            HandlerC0254Od handlerC0254Od = this.m;
            handlerC0254Od.sendMessageDelayed(handlerC0254Od.obtainMessage(1), this.k);
            HandlerC0254Od handlerC0254Od2 = this.m;
            handlerC0254Od2.sendMessageDelayed(handlerC0254Od2.obtainMessage(2), this.l);
        }
        for (BasePendingResult basePendingResult : (BasePendingResult[]) this.y.a.toArray(C1444re.e)) {
            basePendingResult.h(C1444re.d);
        }
        C1885zf c1885zf = this.d;
        Cif.d(c1885zf.h, "onUnintentionalDisconnection must only be called on the Handler thread");
        c1885zf.h.removeMessages(1);
        synchronized (c1885zf.i) {
            c1885zf.g = true;
            ArrayList arrayList = new ArrayList(c1885zf.b);
            int i2 = c1885zf.f.get();
            int size = arrayList.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList.get(i3);
                i3++;
                InterfaceC0110Gc interfaceC0110Gc = (InterfaceC0110Gc) obj;
                if (!c1885zf.e || c1885zf.f.get() != i2) {
                    break;
                } else if (c1885zf.b.contains(interfaceC0110Gc)) {
                    interfaceC0110Gc.d(i);
                }
            }
            c1885zf.c.clear();
            c1885zf.g = false;
        }
        this.d.b();
        if (i == 2) {
            n();
        }
    }

    @Override // defpackage.InterfaceC0780fe
    public final void c(Bundle bundle) {
        while (!this.i.isEmpty()) {
            f((AbstractC0343Tc) this.i.remove());
        }
        C1885zf c1885zf = this.d;
        Cif.d(c1885zf.h, "onConnectionSuccess must only be called on the Handler thread");
        synchronized (c1885zf.i) {
            boolean z = true;
            Cif.h(!c1885zf.g);
            c1885zf.h.removeMessages(1);
            c1885zf.g = true;
            if (c1885zf.c.size() != 0) {
                z = false;
            }
            Cif.h(z);
            ArrayList arrayList = new ArrayList(c1885zf.b);
            int i = c1885zf.f.get();
            int size = arrayList.size();
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                InterfaceC0110Gc interfaceC0110Gc = (InterfaceC0110Gc) obj;
                if (!c1885zf.e || !c1885zf.a.a() || c1885zf.f.get() != i) {
                    break;
                } else if (!c1885zf.c.contains(interfaceC0110Gc)) {
                    interfaceC0110Gc.f(bundle);
                }
            }
            c1885zf.c.clear();
            c1885zf.g = false;
        }
    }

    @Override // defpackage.AbstractC0146Ic
    public final void d() {
        this.b.lock();
        try {
            if (this.f >= 0) {
                Cif.i(this.w != null, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.w == null) {
                this.w = Integer.valueOf(l(this.p.values(), false));
            } else if (this.w.intValue() == 2) {
                throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            j(this.w.intValue());
        } finally {
            this.b.unlock();
        }
    }

    @Override // defpackage.AbstractC0146Ic
    public final void e() {
        this.b.lock();
        try {
            C1444re c1444re = this.y;
            for (BasePendingResult basePendingResult : (BasePendingResult[]) c1444re.a.toArray(C1444re.e)) {
                basePendingResult.g.set(null);
                if (basePendingResult.f()) {
                    c1444re.a.remove(basePendingResult);
                }
            }
            if (this.e != null) {
                this.e.disconnect();
            }
            C0891hd c0891hd = this.u;
            for (C0835gd c0835gd : c0891hd.a) {
                c0835gd.b = null;
                c0835gd.c = null;
            }
            c0891hd.a.clear();
            for (AbstractC0343Tc abstractC0343Tc : this.i) {
                abstractC0343Tc.g.set(null);
                abstractC0343Tc.a();
            }
            this.i.clear();
            if (this.e == null) {
                return;
            }
            o();
            this.d.b();
        } finally {
            this.b.unlock();
        }
    }

    @Override // defpackage.AbstractC0146Ic
    public final AbstractC0343Tc f(AbstractC0343Tc abstractC0343Tc) {
        Cif.b(abstractC0343Tc.p != null, "This task can not be executed (it's probably a Batch or malformed)");
        boolean zContainsKey = this.p.containsKey(abstractC0343Tc.p);
        C0020Bc c0020Bc = abstractC0343Tc.q;
        String str = c0020Bc != null ? c0020Bc.c : "the API";
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 65);
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(str);
        sb.append(" required for this call.");
        Cif.b(zContainsKey, sb.toString());
        this.b.lock();
        try {
            if (this.e == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (!this.j) {
                return this.e.b(abstractC0343Tc);
            }
            this.i.add(abstractC0343Tc);
            while (!this.i.isEmpty()) {
                AbstractC0343Tc abstractC0343Tc2 = (AbstractC0343Tc) this.i.remove();
                this.y.a(abstractC0343Tc2);
                abstractC0343Tc2.k(Status.x);
            }
            return abstractC0343Tc;
        } finally {
            this.b.unlock();
        }
    }

    @Override // defpackage.AbstractC0146Ic
    public final InterfaceC1882zc g(AbstractC1662vc abstractC1662vc) {
        InterfaceC1882zc interfaceC1882zc = (InterfaceC1882zc) this.p.get(abstractC1662vc);
        Cif.g(interfaceC1882zc, "Appropriate Api was not requested.");
        return interfaceC1882zc;
    }

    @Override // defpackage.AbstractC0146Ic
    public final Looper h() {
        return this.h;
    }

    @Override // defpackage.AbstractC0146Ic
    public final boolean i() {
        InterfaceC0724ee interfaceC0724ee = this.e;
        return interfaceC0724ee != null && interfaceC0724ee.a();
    }

    public final void j(int i) {
        this.b.lock();
        boolean z = true;
        if (i != 3 && i != 1 && i != 2) {
            z = false;
        }
        try {
            StringBuilder sb = new StringBuilder(33);
            sb.append("Illegal sign-in mode: ");
            sb.append(i);
            Cif.b(z, sb.toString());
            p(i);
            n();
        } finally {
            this.b.unlock();
        }
    }

    public final void k(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("mContext=").println(this.g);
        printWriter.append((CharSequence) str).append("mResuming=").print(this.j);
        printWriter.append(" mWorkQueue.size()=").print(this.i.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.y.a.size());
        InterfaceC0724ee interfaceC0724ee = this.e;
        if (interfaceC0724ee != null) {
            interfaceC0724ee.g(str, fileDescriptor, printWriter, strArr);
        }
    }

    public final void n() {
        this.d.e = true;
        this.e.e();
    }

    public final boolean o() {
        if (!this.j) {
            return false;
        }
        this.j = false;
        this.m.removeMessages(2);
        this.m.removeMessages(1);
        C0557be c0557be = this.o;
        if (c0557be != null) {
            c0557be.a();
            this.o = null;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void p(int i) {
        Integer num = this.w;
        if (num == null) {
            this.w = Integer.valueOf(i);
        } else if (num.intValue() != i) {
            String strQ = q(i);
            String strQ2 = q(this.w.intValue());
            StringBuilder sb = new StringBuilder(strQ2.length() + strQ.length() + 51);
            sb.append("Cannot use sign-in mode: ");
            sb.append(strQ);
            sb.append(". Mode was already set to ");
            sb.append(strQ2);
            throw new IllegalStateException(sb.toString());
        }
        if (this.e != null) {
            return;
        }
        Iterator it = this.p.values().iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (((InterfaceC1882zc) it.next()).requiresSignIn()) {
                z = true;
            }
        }
        int iIntValue = this.w.intValue();
        if (iIntValue != 1) {
            if (iIntValue == 2 && z) {
                Context context = this.g;
                Lock lock = this.b;
                Looper looper = this.h;
                C1223nc c1223nc = this.n;
                Map map = this.p;
                C0381Ve c0381Ve = this.r;
                Map map2 = this.s;
                AbstractC1552tc abstractC1552tc = this.t;
                ArrayList arrayList = this.v;
                C0971j1 c0971j1 = new C0971j1();
                C0971j1 c0971j12 = new C0971j1();
                for (Map.Entry entry : map.entrySet()) {
                    InterfaceC1882zc interfaceC1882zc = (InterfaceC1882zc) entry.getValue();
                    Objects.requireNonNull((BaseGmsClient) interfaceC1882zc);
                    if (interfaceC1882zc.requiresSignIn()) {
                        c0971j1.put((AbstractC1662vc) entry.getKey(), interfaceC1882zc);
                    } else {
                        c0971j12.put((AbstractC1662vc) entry.getKey(), interfaceC1882zc);
                    }
                }
                Cif.i(!c0971j1.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
                C0971j1 c0971j13 = new C0971j1();
                C0971j1 c0971j14 = new C0971j1();
                for (C0020Bc c0020Bc : map2.keySet()) {
                    Object objA = c0020Bc.a();
                    if (c0971j1.containsKey(objA)) {
                        c0971j13.put(c0020Bc, (Boolean) map2.get(c0020Bc));
                    } else {
                        if (!c0971j12.containsKey(objA)) {
                            throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
                        }
                        c0971j14.put(c0020Bc, (Boolean) map2.get(c0020Bc));
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj = arrayList.get(i2);
                    int i3 = i2 + 1;
                    int i4 = size;
                    C0004Ae c0004Ae = (C0004Ae) obj;
                    if (c0971j13.containsKey(c0004Ae.a)) {
                        arrayList2.add(c0004Ae);
                    } else {
                        if (!c0971j14.containsKey(c0004Ae.a)) {
                            throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
                        }
                        arrayList3.add(c0004Ae);
                    }
                    size = i4;
                    i2 = i3;
                }
                this.e = new C0040Ce(context, this, lock, looper, c1223nc, c0971j1, c0971j12, c0381Ve, abstractC1552tc, null, arrayList2, arrayList3, c0971j13, c0971j14);
                return;
            }
        } else if (!z) {
            throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
        }
        this.e = new C0272Pd(this.g, this, this.b, this.h, this.n, this.p, this.r, this.s, this.t, this.v, this);
    }
}
