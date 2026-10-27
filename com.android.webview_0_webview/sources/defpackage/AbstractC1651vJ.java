package defpackage;

/* JADX INFO: renamed from: vJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1651vJ {
    public final int a;

    public AbstractC1651vJ(int i, int i2) {
        this.a = i;
    }

    public abstract void a(WI wi);

    public C1541tJ b(LJ lj, C1045kJ c1045kJ) {
        WI wi = new WI(lj, this.a + c1045kJ.a.a);
        wi.f(c1045kJ.a);
        wi.c(0, 8);
        wi.c(c1045kJ.b, 12);
        wi.c(c1045kJ.c, 16);
        if (C1045kJ.b(c1045kJ.c)) {
            wi.d(c1045kJ.d, 24);
        }
        a(wi);
        return new C1541tJ(wi.v(), c1045kJ);
    }
}
