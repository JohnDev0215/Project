package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Fv implements InterfaceC1352pv {
    public final LJ a;
    public final InterfaceC1101lJ b;
    public final long c;

    public Fv(LJ lj, InterfaceC1101lJ interfaceC1101lJ, long j) {
        this.a = lj;
        this.b = interfaceC1101lJ;
        this.c = j;
    }

    public void a(Object obj, Object obj2) {
        Dv dv = new Dv();
        dv.b = ((Long) obj).longValue();
        dv.c = (C1323pK) obj2;
        this.b.q(dv.b(this.a, new C1045kJ(5, 2, this.c)));
    }
}
