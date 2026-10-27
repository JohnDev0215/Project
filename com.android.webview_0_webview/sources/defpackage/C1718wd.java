package defpackage;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: renamed from: wd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1718wd implements InterfaceC0290Qd {
    public final C0272Pd a;
    public boolean b = false;

    public C1718wd(C0272Pd c0272Pd) {
        this.a = c0272Pd;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.InterfaceC0290Qd
    public final AbstractC0343Tc b(AbstractC0343Tc abstractC0343Tc) {
        try {
            C1444re c1444re = this.a.m.y;
            c1444re.a.add(abstractC0343Tc);
            abstractC0343Tc.g.set(c1444re.b);
            C0165Jd c0165Jd = this.a.m;
            InterfaceC1882zc interfaceC1882zc = (InterfaceC1882zc) c0165Jd.p.get(abstractC0343Tc.p);
            Cif.g(interfaceC1882zc, "Appropriate Api was not requested.");
            if (((BaseGmsClient) interfaceC1882zc).a() || !this.a.g.containsKey(abstractC0343Tc.p)) {
                abstractC0343Tc.j(interfaceC1882zc);
            } else {
                abstractC0343Tc.k(new Status(17));
            }
        } catch (DeadObjectException unused) {
            C0272Pd c0272Pd = this.a;
            c0272Pd.e.sendMessage(c0272Pd.e.obtainMessage(1, new C1663vd(this, this)));
        }
        return abstractC0343Tc;
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void c(ConnectionResult connectionResult, C0020Bc c0020Bc, boolean z) {
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void d(int i) {
        this.a.h(null);
        this.a.n.b(i, false);
    }

    @Override // defpackage.InterfaceC0290Qd
    public final boolean disconnect() {
        if (this.b) {
            return false;
        }
        C0165Jd c0165Jd = this.a.m;
        c0165Jd.b.lock();
        c0165Jd.b.unlock();
        this.a.h(null);
        return true;
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void e() {
        if (this.b) {
            this.b = false;
            C0272Pd c0272Pd = this.a;
            c0272Pd.e.sendMessage(c0272Pd.e.obtainMessage(1, new C1828yd(this, this)));
        }
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void f(Bundle bundle) {
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void g() {
    }
}
