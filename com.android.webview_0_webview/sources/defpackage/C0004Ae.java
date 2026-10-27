package defpackage;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: renamed from: Ae, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0004Ae implements InterfaceC0110Gc, InterfaceC0128Hc {
    public final C0020Bc a;
    public final boolean b;
    public InterfaceC1884ze c;

    public C0004Ae(C0020Bc c0020Bc, boolean z) {
        this.a = c0020Bc;
        this.b = z;
    }

    public final void a() {
        Cif.g(this.c, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
    }

    @Override // defpackage.InterfaceC0397Wc
    public final void d(int i) {
        a();
        this.c.d(i);
    }

    @Override // defpackage.InterfaceC0397Wc
    public final void f(Bundle bundle) {
        a();
        this.c.f(bundle);
    }

    @Override // defpackage.InterfaceC0947id
    public final void i(ConnectionResult connectionResult) {
        a();
        this.c.c(connectionResult, this.a, this.b);
    }
}
