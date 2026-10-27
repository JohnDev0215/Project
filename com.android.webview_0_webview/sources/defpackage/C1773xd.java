package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

/* JADX INFO: renamed from: xd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1773xd implements InterfaceC0290Qd {
    public final C0272Pd a;
    public final Lock b;
    public final Context c;
    public final AbstractC1278oc d;
    public ConnectionResult e;
    public int f;
    public int h;
    public InterfaceC1395qj k;
    public boolean l;
    public boolean m;
    public boolean n;
    public InterfaceC0502af o;
    public boolean p;
    public boolean q;
    public final C0381Ve r;
    public final Map s;
    public final AbstractC1552tc t;
    public int g = 0;
    public final Bundle i = new Bundle();
    public final Set j = new HashSet();
    public ArrayList u = new ArrayList();

    public C1773xd(C0272Pd c0272Pd, C0381Ve c0381Ve, Map map, AbstractC1278oc abstractC1278oc, AbstractC1552tc abstractC1552tc, Lock lock, Context context) {
        this.a = c0272Pd;
        this.r = c0381Ve;
        this.s = map;
        this.d = abstractC1278oc;
        this.t = abstractC1552tc;
        this.b = lock;
        this.c = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(com.google.android.gms.common.ConnectionResult r5, defpackage.C0020Bc r6, boolean r7) {
        /*
            r4 = this;
            tc r0 = r6.a
            java.util.Objects.requireNonNull(r0)
            r0 = 2147483647(0x7fffffff, float:NaN)
            r1 = 0
            r2 = 1
            if (r7 == 0) goto L22
            boolean r7 = r5.e()
            if (r7 == 0) goto L14
        L12:
            r7 = 1
            goto L20
        L14:
            oc r7 = r4.d
            int r3 = r5.t
            android.content.Intent r7 = r7.a(r3)
            if (r7 == 0) goto L1f
            goto L12
        L1f:
            r7 = 0
        L20:
            if (r7 == 0) goto L2b
        L22:
            com.google.android.gms.common.ConnectionResult r7 = r4.e
            if (r7 == 0) goto L2a
            int r7 = r4.f
            if (r0 >= r7) goto L2b
        L2a:
            r1 = 1
        L2b:
            if (r1 == 0) goto L31
            r4.e = r5
            r4.f = r0
        L31:
            Pd r7 = r4.a
            java.util.Map r7 = r7.g
            vc r6 = r6.a()
            r7.put(r6, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1773xd.a(com.google.android.gms.common.ConnectionResult, Bc, boolean):void");
    }

    @Override // defpackage.InterfaceC0290Qd
    public final AbstractC0343Tc b(AbstractC0343Tc abstractC0343Tc) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void c(ConnectionResult connectionResult, C0020Bc c0020Bc, boolean z) {
        if (o(1)) {
            a(connectionResult, c0020Bc, z);
            if (h()) {
                j();
            }
        }
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void d(int i) {
        n(new ConnectionResult(8, null));
    }

    @Override // defpackage.InterfaceC0290Qd
    public final boolean disconnect() {
        l();
        m(true);
        this.a.h(null);
        return true;
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void e() {
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void f(Bundle bundle) {
        if (o(1)) {
            if (bundle != null) {
                this.i.putAll(bundle);
            }
            if (h()) {
                j();
            }
        }
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void g() {
        this.a.g.clear();
        this.m = false;
        this.e = null;
        this.g = 0;
        this.l = true;
        this.n = false;
        this.p = false;
        HashMap map = new HashMap();
        for (C0020Bc c0020Bc : this.s.keySet()) {
            InterfaceC1882zc interfaceC1882zc = (InterfaceC1882zc) this.a.f.get(c0020Bc.a());
            Objects.requireNonNull(c0020Bc.a);
            boolean zBooleanValue = ((Boolean) this.s.get(c0020Bc)).booleanValue();
            if (interfaceC1882zc.requiresSignIn()) {
                this.m = true;
                if (zBooleanValue) {
                    this.j.add(c0020Bc.a());
                } else {
                    this.l = false;
                }
            }
            map.put(interfaceC1882zc, new C1883zd(this, c0020Bc, zBooleanValue));
        }
        if (this.m) {
            this.r.i = Integer.valueOf(System.identityHashCode(this.a.m));
            C0147Id c0147Id = new C0147Id(this, null);
            AbstractC1552tc abstractC1552tc = this.t;
            Context context = this.c;
            Looper looper = this.a.m.h;
            C0381Ve c0381Ve = this.r;
            this.k = (InterfaceC1395qj) abstractC1552tc.a(context, looper, c0381Ve, c0381Ve.g, c0147Id, c0147Id);
        }
        this.h = this.a.f.size();
        this.u.add(AbstractC0344Td.a.submit(new C0039Cd(this, map)));
    }

    public final boolean h() {
        int i = this.h - 1;
        this.h = i;
        if (i > 0) {
            return false;
        }
        if (i >= 0) {
            ConnectionResult connectionResult = this.e;
            if (connectionResult == null) {
                return true;
            }
            this.a.l = this.f;
            n(connectionResult);
            return false;
        }
        C0165Jd c0165Jd = this.a.m;
        Objects.requireNonNull(c0165Jd);
        StringWriter stringWriter = new StringWriter();
        c0165Jd.k("", null, new PrintWriter(stringWriter), null);
        Log.w("GACConnecting", stringWriter.toString());
        Log.wtf("GACConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
        n(new ConnectionResult(8, null));
        return false;
    }

    public final void i() {
        if (this.h != 0) {
            return;
        }
        if (!this.m || this.n) {
            ArrayList arrayList = new ArrayList();
            this.g = 1;
            this.h = this.a.f.size();
            for (AbstractC1662vc abstractC1662vc : this.a.f.keySet()) {
                if (!this.a.g.containsKey(abstractC1662vc)) {
                    arrayList.add((InterfaceC1882zc) this.a.f.get(abstractC1662vc));
                } else if (h()) {
                    j();
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            this.u.add(AbstractC0344Td.a.submit(new C0057Dd(this, arrayList)));
        }
    }

    public final void j() {
        C0272Pd c0272Pd = this.a;
        c0272Pd.a.lock();
        try {
            c0272Pd.m.o();
            c0272Pd.k = new C1718wd(c0272Pd);
            c0272Pd.k.g();
            c0272Pd.b.signalAll();
            c0272Pd.a.unlock();
            AbstractC0344Td.a.execute(new RunnableC0003Ad(this));
            InterfaceC1395qj interfaceC1395qj = this.k;
            if (interfaceC1395qj != null) {
                if (this.p) {
                    InterfaceC0502af interfaceC0502af = this.o;
                    boolean z = this.q;
                    C0674dj c0674dj = (C0674dj) interfaceC1395qj;
                    Objects.requireNonNull(c0674dj);
                    try {
                        InterfaceC0897hj interfaceC0897hj = (InterfaceC0897hj) c0674dj.i();
                        int iIntValue = c0674dj.F.intValue();
                        C1008jj c1008jj = (C1008jj) interfaceC0897hj;
                        Parcel parcelF = c1008jj.f();
                        AbstractC0383Vg.c(parcelF, interfaceC0502af);
                        parcelF.writeInt(iIntValue);
                        parcelF.writeInt(z ? 1 : 0);
                        c1008jj.d(9, parcelF);
                    } catch (RemoteException unused) {
                        Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
                    }
                }
                m(false);
            }
            Iterator it = this.a.g.keySet().iterator();
            while (it.hasNext()) {
                ((InterfaceC1882zc) this.a.f.get((AbstractC1662vc) it.next())).disconnect();
            }
            this.a.n.c(this.i.isEmpty() ? null : this.i);
        } catch (Throwable th) {
            c0272Pd.a.unlock();
            throw th;
        }
    }

    public final void k() {
        this.m = false;
        this.a.m.q = Collections.emptySet();
        for (AbstractC1662vc abstractC1662vc : this.j) {
            if (!this.a.g.containsKey(abstractC1662vc)) {
                this.a.g.put(abstractC1662vc, new ConnectionResult(17, null));
            }
        }
    }

    public final void l() {
        ArrayList arrayList = this.u;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((Future) obj).cancel(true);
        }
        this.u.clear();
    }

    public final void m(boolean z) {
        Object obj = this.k;
        if (obj != null) {
            if (((BaseGmsClient) obj).a() && z) {
                C0674dj c0674dj = (C0674dj) this.k;
                Objects.requireNonNull(c0674dj);
                try {
                    InterfaceC0897hj interfaceC0897hj = (InterfaceC0897hj) c0674dj.i();
                    int iIntValue = c0674dj.F.intValue();
                    C1008jj c1008jj = (C1008jj) interfaceC0897hj;
                    Parcel parcelF = c1008jj.f();
                    parcelF.writeInt(iIntValue);
                    c1008jj.d(7, parcelF);
                } catch (RemoteException unused) {
                    Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
                }
            }
            ((BaseGmsClient) this.k).disconnect();
            if (this.r.h) {
                this.k = null;
            }
            this.o = null;
        }
    }

    public final void n(ConnectionResult connectionResult) {
        l();
        m(!connectionResult.e());
        this.a.h(connectionResult);
        this.a.n.a(connectionResult);
    }

    public final boolean o(int i) {
        if (this.g == i) {
            return true;
        }
        C0165Jd c0165Jd = this.a.m;
        Objects.requireNonNull(c0165Jd);
        StringWriter stringWriter = new StringWriter();
        c0165Jd.k("", null, new PrintWriter(stringWriter), null);
        Log.w("GACConnecting", stringWriter.toString());
        String strValueOf = String.valueOf(this);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 23);
        sb.append("Unexpected callback in ");
        sb.append(strValueOf);
        Log.w("GACConnecting", sb.toString());
        int i2 = this.h;
        StringBuilder sb2 = new StringBuilder(33);
        sb2.append("mRemainingConnections=");
        sb2.append(i2);
        Log.w("GACConnecting", sb2.toString());
        int i3 = this.g;
        String str = "UNKNOWN";
        String str2 = i3 != 0 ? i3 != 1 ? "UNKNOWN" : "STEP_GETTING_REMOTE_SERVICE" : "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
        if (i == 0) {
            str = "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
        } else if (i == 1) {
            str = "STEP_GETTING_REMOTE_SERVICE";
        }
        StringBuilder sb3 = new StringBuilder(str.length() + str2.length() + 70);
        sb3.append("GoogleApiClient connecting is in step ");
        sb3.append(str2);
        sb3.append(" but received callback for step ");
        sb3.append(str);
        Log.e("GACConnecting", sb3.toString(), new Exception());
        n(new ConnectionResult(8, null));
        return false;
    }
}
