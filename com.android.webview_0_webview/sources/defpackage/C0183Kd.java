package defpackage;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: renamed from: Kd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0183Kd implements InterfaceC0290Qd {
    public final C0272Pd a;

    public C0183Kd(C0272Pd c0272Pd) {
        this.a = c0272Pd;
    }

    @Override // defpackage.InterfaceC0290Qd
    public final AbstractC0343Tc b(AbstractC0343Tc abstractC0343Tc) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void c(ConnectionResult connectionResult, C0020Bc c0020Bc, boolean z) {
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void d(int i) {
    }

    @Override // defpackage.InterfaceC0290Qd
    public final boolean disconnect() {
        return true;
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void e() {
        C0272Pd c0272Pd = this.a;
        c0272Pd.a.lock();
        try {
            c0272Pd.k = new C1773xd(c0272Pd, c0272Pd.h, c0272Pd.i, c0272Pd.d, c0272Pd.j, c0272Pd.a, c0272Pd.c);
            c0272Pd.k.g();
            c0272Pd.b.signalAll();
        } finally {
            c0272Pd.a.unlock();
        }
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void f(Bundle bundle) {
    }

    @Override // defpackage.InterfaceC0290Qd
    public final void g() {
        Iterator it = this.a.f.values().iterator();
        while (it.hasNext()) {
            ((InterfaceC1882zc) it.next()).disconnect();
        }
        this.a.m.q = Collections.emptySet();
    }
}
