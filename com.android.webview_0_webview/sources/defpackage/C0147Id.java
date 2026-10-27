package defpackage;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: renamed from: Id, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0147Id implements InterfaceC0110Gc, InterfaceC0128Hc {
    public final /* synthetic */ C1773xd a;

    public C0147Id(C1773xd c1773xd, RunnableC0003Ad runnableC0003Ad) {
        this.a = c1773xd;
    }

    @Override // defpackage.InterfaceC0397Wc
    public final void d(int i) {
    }

    @Override // defpackage.InterfaceC0397Wc
    public final void f(Bundle bundle) {
        C1773xd c1773xd = this.a;
        if (!c1773xd.r.h) {
            ((C0674dj) c1773xd.k).v(new BinderC0111Gd(this.a));
            return;
        }
        c1773xd.b.lock();
        try {
            if (this.a.k == null) {
                return;
            }
            ((C0674dj) this.a.k).v(new BinderC0111Gd(this.a));
        } finally {
            this.a.b.unlock();
        }
    }

    @Override // defpackage.InterfaceC0947id
    public final void i(ConnectionResult connectionResult) {
        this.a.b.lock();
        try {
            if (this.a.l && !connectionResult.e()) {
                this.a.k();
                this.a.i();
            } else {
                this.a.n(connectionResult);
            }
        } finally {
            this.a.b.unlock();
        }
    }
}
