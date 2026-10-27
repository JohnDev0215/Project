package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Q5 {
    public P5 a = null;
    public ArrayList b = new ArrayList();
    public long c = 120;
    public long d = 120;
    public long e = 250;
    public long f = 250;

    public final void a(AbstractC0697e6 abstractC0697e6) {
        P5 p5 = this.a;
        if (p5 == null) {
            return;
        }
        throw null;
    }

    public final void b() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            ((O5) this.b.get(i)).a();
        }
        this.b.clear();
    }

    public abstract void c();

    public abstract boolean d();
}
