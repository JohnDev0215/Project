package defpackage;

import android.os.IInterface;

/* JADX INFO: renamed from: Pi, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0277Pi implements InterfaceC0241Ni {
    public final /* synthetic */ AbstractC0259Oi a;

    public C0277Pi(AbstractC0259Oi abstractC0259Oi) {
        this.a = abstractC0259Oi;
    }

    public final void a() {
        if (!this.a.a()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final /* synthetic */ IInterface b() {
        return (InterfaceC0080Ei) this.a.i();
    }
}
