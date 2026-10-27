package defpackage;

import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: renamed from: Ed, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0075Ed extends AbstractC0326Sd {
    public final /* synthetic */ InterfaceC0184Ke b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0075Ed(InterfaceC0290Qd interfaceC0290Qd, InterfaceC0184Ke interfaceC0184Ke) {
        super(interfaceC0290Qd);
        this.b = interfaceC0184Ke;
    }

    @Override // defpackage.AbstractC0326Sd
    public final void a() {
        this.b.a(new ConnectionResult(16, null));
    }
}
