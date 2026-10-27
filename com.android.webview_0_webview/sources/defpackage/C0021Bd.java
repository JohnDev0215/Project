package defpackage;

import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: renamed from: Bd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0021Bd extends AbstractC0326Sd {
    public final /* synthetic */ ConnectionResult b;
    public final /* synthetic */ C0039Cd c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0021Bd(C0039Cd c0039Cd, InterfaceC0290Qd interfaceC0290Qd, ConnectionResult connectionResult) {
        super(interfaceC0290Qd);
        this.c = c0039Cd;
        this.b = connectionResult;
    }

    @Override // defpackage.AbstractC0326Sd
    public final void a() {
        this.c.u.n(this.b);
    }
}
