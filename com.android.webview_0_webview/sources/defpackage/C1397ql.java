package defpackage;

import java.util.Objects;

/* JADX INFO: renamed from: ql, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1397ql implements InterfaceC0192Km {
    public final AbstractC1342pl a;

    public C1397ql(AbstractC1342pl abstractC1342pl) {
        AbstractC0119Gl.b(abstractC1342pl, "output");
        this.a = abstractC1342pl;
        abstractC1342pl.a = this;
    }

    public void a(int i, double d) {
        AbstractC1342pl abstractC1342pl = this.a;
        Objects.requireNonNull(abstractC1342pl);
        abstractC1342pl.K(i, Double.doubleToRawLongBits(d));
    }

    public void b(int i, float f) {
        AbstractC1342pl abstractC1342pl = this.a;
        Objects.requireNonNull(abstractC1342pl);
        abstractC1342pl.I(i, Float.floatToRawIntBits(f));
    }

    public void c(int i, Object obj, InterfaceC1178mm interfaceC1178mm) {
        AbstractC1342pl abstractC1342pl = this.a;
        abstractC1342pl.Q(i, 3);
        interfaceC1178mm.g((InterfaceC0509am) obj, abstractC1342pl.a);
        abstractC1342pl.Q(i, 4);
    }

    public void d(int i, Object obj, InterfaceC1178mm interfaceC1178mm) {
        this.a.O(i, (InterfaceC0509am) obj, interfaceC1178mm);
    }

    public void e(int i, int i2) {
        this.a.R(i, AbstractC1342pl.C(i2));
    }

    public void f(int i, long j) {
        this.a.T(i, AbstractC1342pl.D(j));
    }
}
