package defpackage;

import com.google.android.gms.common.ConnectionResult;
import java.util.Set;

/* JADX INFO: renamed from: Zc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0451Zc implements InterfaceC1115le, InterfaceC0184Ke {
    public final InterfaceC1882zc a;
    public final C0289Qc b;
    public InterfaceC0502af c = null;
    public Set d = null;
    public boolean e = false;
    public final /* synthetic */ C0500ad f;

    public C0451Zc(C0500ad c0500ad, InterfaceC1882zc interfaceC1882zc, C0289Qc c0289Qc) {
        this.f = c0500ad;
        this.a = interfaceC1882zc;
        this.b = c0289Qc;
    }

    @Override // defpackage.InterfaceC0184Ke
    public final void a(ConnectionResult connectionResult) {
        this.f.m.post(new RunnableC0501ae(this, connectionResult));
    }

    public final void b(ConnectionResult connectionResult) {
        C0415Xc c0415Xc = (C0415Xc) this.f.i.get(this.b);
        Cif.c(c0415Xc.m.m);
        c0415Xc.b.disconnect();
        c0415Xc.h(connectionResult, null);
    }
}
