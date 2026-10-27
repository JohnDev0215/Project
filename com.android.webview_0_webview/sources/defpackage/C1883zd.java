package defpackage;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: zd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1883zd implements InterfaceC0184Ke {
    public final WeakReference a;
    public final C0020Bc b;
    public final boolean c;

    public C1883zd(C1773xd c1773xd, C0020Bc c0020Bc, boolean z) {
        this.a = new WeakReference(c1773xd);
        this.b = c0020Bc;
        this.c = z;
    }

    @Override // defpackage.InterfaceC0184Ke
    public final void a(ConnectionResult connectionResult) {
        C1773xd c1773xd = (C1773xd) this.a.get();
        if (c1773xd == null) {
            return;
        }
        Cif.i(Looper.myLooper() == c1773xd.a.m.h, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
        c1773xd.b.lock();
        try {
            if (c1773xd.o(0)) {
                if (!connectionResult.f()) {
                    c1773xd.a(connectionResult, this.b, this.c);
                }
                if (c1773xd.h()) {
                    c1773xd.i();
                }
            }
        } finally {
            c1773xd.b.unlock();
        }
    }
}
