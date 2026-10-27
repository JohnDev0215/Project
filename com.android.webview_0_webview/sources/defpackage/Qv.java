package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Qv implements InterfaceC1461rv {
    public final LJ a;
    public final InterfaceC1101lJ b;
    public final long c;

    public Qv(LJ lj, InterfaceC1101lJ interfaceC1101lJ, long j) {
        this.a = lj;
        this.b = interfaceC1101lJ;
        this.c = j;
    }

    @Override // defpackage.KI
    public void a(Object obj) {
        Ov ov = new Ov();
        ov.b = (C1213nK) obj;
        this.b.q(ov.b(this.a, new C1045kJ(4, 2, this.c)));
    }
}
