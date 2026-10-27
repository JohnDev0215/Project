package defpackage;

import org.chromium.base.Callback;

/* JADX INFO: renamed from: xO, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1766xO implements InterfaceC1656vO {
    public final C1546tO a;
    public Callback b;

    public C1766xO(C1546tO c1546tO, Callback callback) {
        this.a = c1546tO;
        this.b = callback;
    }

    @Override // defpackage.InterfaceC1656vO
    public void b(JO jo, int i) {
        Callback callback = this.b;
        this.b = null;
        callback.a(Integer.valueOf(i));
    }

    @Override // defpackage.InterfaceC1656vO
    public void d(JO jo, int i) {
        if (i == 0) {
            this.a.b(jo, 1);
        } else {
            this.a.b(jo, 2);
        }
    }
}
