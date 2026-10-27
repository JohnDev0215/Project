package defpackage;

/* JADX INFO: renamed from: Gj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0117Gj {
    public final Object a = new Object();
    public InterfaceC0099Fj b;

    public void a() {
        synchronized (this.a) {
            if (this.b != null) {
                this.b.a();
                this.b = null;
            }
        }
    }
}
