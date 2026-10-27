package defpackage;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* JADX INFO: renamed from: ve, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1664ve extends AbstractC1335pe {
    public final AbstractC1224nd b;
    public final C1614uj c;
    public final InterfaceC1114ld d;

    public C1664ve(int i, AbstractC1224nd abstractC1224nd, C1614uj c1614uj, InterfaceC1114ld interfaceC1114ld) {
        super(i);
        this.c = c1614uj;
        this.b = abstractC1224nd;
        this.d = interfaceC1114ld;
        if (i == 2 && abstractC1224nd.b) {
            throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        }
    }

    @Override // defpackage.AbstractC0218Md
    public final void a(C0415Xc c0415Xc) throws DeadObjectException {
        try {
            this.b.a(c0415Xc.b, this.c);
        } catch (DeadObjectException e) {
            throw e;
        } catch (RemoteException e2) {
            e(AbstractC0218Md.d(e2));
        } catch (RuntimeException e3) {
            this.c.c(e3);
        }
    }

    @Override // defpackage.AbstractC0218Md
    public final void b(C1389qd c1389qd, boolean z) {
        C1614uj c1614uj = this.c;
        c1389qd.b.put(c1614uj, Boolean.valueOf(z));
        C0027Bj c0027Bj = c1614uj.a;
        C1498sd c1498sd = new C1498sd(c1389qd, c1614uj);
        Objects.requireNonNull(c0027Bj);
        c0027Bj.a(AbstractC1724wj.a, c1498sd);
    }

    @Override // defpackage.AbstractC0218Md
    public final void c(Exception exc) {
        this.c.c(exc);
    }

    @Override // defpackage.AbstractC0218Md
    public final void e(Status status) {
        C1614uj c1614uj = this.c;
        Objects.requireNonNull((C0271Pc) this.d);
        c1614uj.c(status.v != null ? new C0200Lc(status) : new C0038Cc(status));
    }

    @Override // defpackage.AbstractC1335pe
    public final Feature[] f(C0415Xc c0415Xc) {
        return this.b.a;
    }

    @Override // defpackage.AbstractC1335pe
    public final boolean g(C0415Xc c0415Xc) {
        return this.b.b;
    }
}
