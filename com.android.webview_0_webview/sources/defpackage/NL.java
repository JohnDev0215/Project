package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NL implements GL {
    public final LJ a;
    public final InterfaceC1101lJ b;
    public final long c;

    public NL(LJ lj, InterfaceC1101lJ interfaceC1101lJ, long j) {
        this.a = lj;
        this.b = interfaceC1101lJ;
        this.c = j;
    }

    @Override // defpackage.KI
    public void a(Object obj) {
        LL ll = new LL();
        ll.b = (int[]) obj;
        this.b.q(ll.b(this.a, new C1045kJ(1, 2, this.c)));
    }
}
