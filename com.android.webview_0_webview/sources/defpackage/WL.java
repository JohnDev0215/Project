package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WL implements EL {
    public final LJ a;
    public final InterfaceC1101lJ b;
    public final long c;

    public WL(LJ lj, InterfaceC1101lJ interfaceC1101lJ, long j) {
        this.a = lj;
        this.b = interfaceC1101lJ;
        this.c = j;
    }

    @Override // defpackage.KI
    public void a(Object obj) {
        UL ul = new UL();
        ul.b = (RL[]) obj;
        this.b.q(ul.b(this.a, new C1045kJ(0, 2, this.c)));
    }
}
