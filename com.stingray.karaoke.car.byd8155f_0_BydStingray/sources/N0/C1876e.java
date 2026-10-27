package n0;

import androidx.compose.ui.platform.AndroidComposeView;
import q0.C2164c;
import q0.C2165d;

/* JADX INFO: renamed from: n0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1876e implements InterfaceC1897z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AndroidComposeView f19429a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f19430b = new Object();

    public C1876e(AndroidComposeView androidComposeView) {
        this.f19429a = androidComposeView;
    }

    @Override // n0.InterfaceC1897z
    public final C2164c a() {
        C2164c c2164c;
        synchronized (this.f19430b) {
            AbstractC1875d.a(this.f19429a);
            c2164c = new C2164c(new C2165d());
        }
        return c2164c;
    }

    @Override // n0.InterfaceC1897z
    public final void b(C2164c c2164c) {
        synchronized (this.f19430b) {
            if (!c2164c.f21133r) {
                c2164c.f21133r = true;
                c2164c.b();
            }
        }
    }
}
