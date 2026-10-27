package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ConnectionInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/* JADX INFO: renamed from: Xc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0415Xc implements InterfaceC0110Gc, InterfaceC0128Hc, InterfaceC1884ze {
    public final InterfaceC1882zc b;
    public final InterfaceC1607uc c;
    public final C0289Qc d;
    public final C1389qd e;
    public final int h;
    public final BinderC1059ke i;
    public boolean j;
    public final /* synthetic */ C0500ad m;
    public final Queue a = new LinkedList();
    public final Set f = new HashSet();
    public final Map g = new HashMap();
    public final List k = new ArrayList();
    public ConnectionResult l = null;

    public C0415Xc(C0500ad c0500ad, AbstractC0092Fc abstractC0092Fc) {
        this.m = c0500ad;
        InterfaceC1882zc interfaceC1882zcD = abstractC0092Fc.d(c0500ad.m.getLooper(), this);
        this.b = interfaceC1882zcD;
        this.c = interfaceC1882zcD;
        this.d = abstractC0092Fc.d;
        this.e = new C1389qd();
        this.h = abstractC0092Fc.f;
        if (this.b.requiresSignIn()) {
            this.i = abstractC0092Fc.e(c0500ad.d, c0500ad.m);
        } else {
            this.i = null;
        }
    }

    public final void a() {
        Cif.c(this.m.m);
        if (((BaseGmsClient) this.b).a() || ((BaseGmsClient) this.b).l()) {
            return;
        }
        try {
            int iA = this.m.f.a(this.m.d, this.b);
            if (iA != 0) {
                h(new ConnectionResult(iA, null), null);
                return;
            }
            C0451Zc c0451Zc = new C0451Zc(this.m, this.b, this.d);
            if (this.b.requiresSignIn()) {
                BinderC1059ke binderC1059ke = this.i;
                Object obj = binderC1059ke.f;
                if (obj != null) {
                    ((BaseGmsClient) obj).disconnect();
                }
                binderC1059ke.e.i = Integer.valueOf(System.identityHashCode(binderC1059ke));
                AbstractC1552tc abstractC1552tc = binderC1059ke.c;
                Context context = binderC1059ke.a;
                Looper looper = binderC1059ke.b.getLooper();
                C0381Ve c0381Ve = binderC1059ke.e;
                binderC1059ke.f = (InterfaceC1395qj) abstractC1552tc.a(context, looper, c0381Ve, c0381Ve.g, binderC1059ke, binderC1059ke);
                binderC1059ke.g = c0451Zc;
                Set set = binderC1059ke.d;
                if (set == null || set.isEmpty()) {
                    binderC1059ke.b.post(new RunnableC1003je(binderC1059ke));
                } else {
                    C0674dj c0674dj = (C0674dj) binderC1059ke.f;
                    c0674dj.c(new C0202Le(c0674dj));
                }
            }
            try {
                ((BaseGmsClient) this.b).c(c0451Zc);
            } catch (SecurityException e) {
                h(new ConnectionResult(10), e);
            }
        } catch (IllegalStateException e2) {
            h(new ConnectionResult(10), e2);
        }
    }

    public final boolean b() {
        return this.b.requiresSignIn();
    }

    @Override // defpackage.InterfaceC1884ze
    public final void c(ConnectionResult connectionResult, C0020Bc c0020Bc, boolean z) {
        if (Looper.myLooper() == this.m.m.getLooper()) {
            h(connectionResult, null);
        } else {
            this.m.m.post(new RunnableC0434Yd(this, connectionResult));
        }
    }

    @Override // defpackage.InterfaceC0397Wc
    public final void d(int i) {
        if (Looper.myLooper() == this.m.m.getLooper()) {
            n();
        } else {
            this.m.m.post(new RunnableC0380Vd(this));
        }
    }

    public final void e() {
        Cif.c(this.m.m);
        Status status = C0500ad.n;
        Cif.c(this.m.m);
        j(status, null, false);
        C1389qd c1389qd = this.e;
        Objects.requireNonNull(c1389qd);
        c1389qd.a(false, C0500ad.n);
        for (C0668dd c0668dd : (C0668dd[]) this.g.keySet().toArray(new C0668dd[this.g.size()])) {
            k(new C1609ue(c0668dd, new C1614uj()));
        }
        u(new ConnectionResult(4));
        if (((BaseGmsClient) this.b).a()) {
            Object obj = this.b;
            C0416Xd c0416Xd = new C0416Xd(this);
            Objects.requireNonNull((BaseGmsClient) obj);
            c0416Xd.a.m.m.post(new RunnableC0452Zd(c0416Xd));
        }
    }

    @Override // defpackage.InterfaceC0397Wc
    public final void f(Bundle bundle) {
        if (Looper.myLooper() == this.m.m.getLooper()) {
            m();
        } else {
            this.m.m.post(new RunnableC0398Wd(this));
        }
    }

    public final Feature g(Feature[] featureArr) {
        if (featureArr != null && featureArr.length != 0) {
            ConnectionInfo connectionInfo = ((BaseGmsClient) this.b).x;
            Feature[] featureArr2 = connectionInfo == null ? null : connectionInfo.t;
            if (featureArr2 == null) {
                featureArr2 = new Feature[0];
            }
            C0971j1 c0971j1 = new C0971j1(featureArr2.length);
            for (Feature feature : featureArr2) {
                c0971j1.put(feature.s, Long.valueOf(feature.b()));
            }
            for (Feature feature2 : featureArr) {
                if (!c0971j1.containsKey(feature2.s) || ((Long) c0971j1.get(feature2.s)).longValue() < feature2.b()) {
                    return feature2;
                }
            }
        }
        return null;
    }

    public final void h(ConnectionResult connectionResult, Exception exc) {
        Object obj;
        Cif.c(this.m.m);
        BinderC1059ke binderC1059ke = this.i;
        if (binderC1059ke != null && (obj = binderC1059ke.f) != null) {
            ((BaseGmsClient) obj).disconnect();
        }
        p();
        this.m.f.a.clear();
        u(connectionResult);
        if (connectionResult.t == 4) {
            Status status = C0500ad.o;
            Cif.c(this.m.m);
            j(status, null, false);
            return;
        }
        if (this.a.isEmpty()) {
            this.l = connectionResult;
            return;
        }
        if (exc != null) {
            Cif.c(this.m.m);
            j(null, exc, false);
            return;
        }
        j(v(connectionResult), null, true);
        if (this.a.isEmpty()) {
            return;
        }
        synchronized (C0500ad.p) {
            Objects.requireNonNull(this.m);
        }
        if (this.m.b(connectionResult, this.h)) {
            return;
        }
        if (connectionResult.t == 18) {
            this.j = true;
        }
        if (!this.j) {
            Status statusV = v(connectionResult);
            Cif.c(this.m.m);
            j(statusV, null, false);
        } else {
            Handler handler = this.m.m;
            Message messageObtain = Message.obtain(handler, 9, this.d);
            Objects.requireNonNull(this.m);
            handler.sendMessageDelayed(messageObtain, 5000L);
        }
    }

    @Override // defpackage.InterfaceC0947id
    public final void i(ConnectionResult connectionResult) {
        h(connectionResult, null);
    }

    public final void j(Status status, Exception exc, boolean z) {
        Cif.c(this.m.m);
        if ((status == null) == (exc == null)) {
            throw new IllegalArgumentException("Status XOR exception should be null");
        }
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            AbstractC0218Md abstractC0218Md = (AbstractC0218Md) it.next();
            if (!z || abstractC0218Md.a == 2) {
                if (status != null) {
                    abstractC0218Md.e(status);
                } else {
                    abstractC0218Md.c(exc);
                }
                it.remove();
            }
        }
    }

    public final void k(AbstractC0218Md abstractC0218Md) {
        Cif.c(this.m.m);
        if (((BaseGmsClient) this.b).a()) {
            if (l(abstractC0218Md)) {
                r();
                return;
            } else {
                this.a.add(abstractC0218Md);
                return;
            }
        }
        this.a.add(abstractC0218Md);
        ConnectionResult connectionResult = this.l;
        if (connectionResult == null || !connectionResult.e()) {
            a();
        } else {
            h(this.l, null);
        }
    }

    public final boolean l(AbstractC0218Md abstractC0218Md) {
        if (!(abstractC0218Md instanceof AbstractC1335pe)) {
            s(abstractC0218Md);
            return true;
        }
        AbstractC1335pe abstractC1335pe = (AbstractC1335pe) abstractC0218Md;
        Feature featureG = g(abstractC1335pe.f(this));
        if (featureG == null) {
            s(abstractC0218Md);
            return true;
        }
        if (!abstractC1335pe.g(this)) {
            abstractC1335pe.c(new C0253Oc(featureG));
            return true;
        }
        C0433Yc c0433Yc = new C0433Yc(this.d, featureG, null);
        int iIndexOf = this.k.indexOf(c0433Yc);
        if (iIndexOf >= 0) {
            C0433Yc c0433Yc2 = (C0433Yc) this.k.get(iIndexOf);
            this.m.m.removeMessages(15, c0433Yc2);
            Handler handler = this.m.m;
            Message messageObtain = Message.obtain(handler, 15, c0433Yc2);
            Objects.requireNonNull(this.m);
            handler.sendMessageDelayed(messageObtain, 5000L);
            return false;
        }
        this.k.add(c0433Yc);
        Handler handler2 = this.m.m;
        Message messageObtain2 = Message.obtain(handler2, 15, c0433Yc);
        Objects.requireNonNull(this.m);
        handler2.sendMessageDelayed(messageObtain2, 5000L);
        Handler handler3 = this.m.m;
        Message messageObtain3 = Message.obtain(handler3, 16, c0433Yc);
        Objects.requireNonNull(this.m);
        handler3.sendMessageDelayed(messageObtain3, 120000L);
        ConnectionResult connectionResult = new ConnectionResult(2, null);
        synchronized (C0500ad.p) {
            Objects.requireNonNull(this.m);
        }
        this.m.b(connectionResult, this.h);
        return false;
    }

    public final void m() {
        p();
        u(ConnectionResult.w);
        q();
        Iterator it = this.g.values().iterator();
        if (it.hasNext()) {
            throw null;
        }
        o();
        r();
    }

    public final void n() {
        p();
        this.j = true;
        this.e.a(true, C1444re.d);
        Handler handler = this.m.m;
        Message messageObtain = Message.obtain(handler, 9, this.d);
        Objects.requireNonNull(this.m);
        handler.sendMessageDelayed(messageObtain, 5000L);
        Handler handler2 = this.m.m;
        Message messageObtain2 = Message.obtain(handler2, 11, this.d);
        Objects.requireNonNull(this.m);
        handler2.sendMessageDelayed(messageObtain2, 120000L);
        this.m.f.a.clear();
        Iterator it = this.g.values().iterator();
        if (it.hasNext()) {
            throw null;
        }
    }

    public final void o() {
        ArrayList arrayList = new ArrayList(this.a);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            AbstractC0218Md abstractC0218Md = (AbstractC0218Md) obj;
            if (!((BaseGmsClient) this.b).a()) {
                return;
            }
            if (l(abstractC0218Md)) {
                this.a.remove(abstractC0218Md);
            }
        }
    }

    public final void p() {
        Cif.c(this.m.m);
        this.l = null;
    }

    public final void q() {
        if (this.j) {
            this.m.m.removeMessages(11, this.d);
            this.m.m.removeMessages(9, this.d);
            this.j = false;
        }
    }

    public final void r() {
        this.m.m.removeMessages(12, this.d);
        Handler handler = this.m.m;
        handler.sendMessageDelayed(handler.obtainMessage(12, this.d), this.m.c);
    }

    public final void s(AbstractC0218Md abstractC0218Md) {
        abstractC0218Md.b(this.e, b());
        try {
            abstractC0218Md.a(this);
        } catch (DeadObjectException unused) {
            d(1);
            this.b.disconnect();
        } catch (Throwable th) {
            throw new IllegalStateException(String.format("Error in GoogleApi implementation for client %s.", this.c.getClass().getName()), th);
        }
    }

    public final boolean t(boolean z) {
        Cif.c(this.m.m);
        if (!((BaseGmsClient) this.b).a() || this.g.size() != 0) {
            return false;
        }
        C1389qd c1389qd = this.e;
        if (!((c1389qd.a.isEmpty() && c1389qd.b.isEmpty()) ? false : true)) {
            this.b.disconnect();
            return true;
        }
        if (z) {
            r();
        }
        return false;
    }

    public final void u(ConnectionResult connectionResult) {
        for (AbstractC1719we abstractC1719we : this.f) {
            String str = null;
            if (AbstractC0893hf.a(connectionResult, ConnectionResult.w)) {
                ((BaseGmsClient) this.b).f();
                str = "com.google.android.gms";
            }
            abstractC1719we.a(this.d, connectionResult, str);
        }
        this.f.clear();
    }

    public final Status v(ConnectionResult connectionResult) {
        String str = this.d.c.c;
        String strValueOf = String.valueOf(connectionResult);
        StringBuilder sb = new StringBuilder(strValueOf.length() + String.valueOf(str).length() + 63);
        sb.append("API: ");
        sb.append(str);
        sb.append(" is not available on this device. Connection failed with: ");
        sb.append(strValueOf);
        return new Status(17, sb.toString());
    }
}
