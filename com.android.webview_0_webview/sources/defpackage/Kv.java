package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Kv implements InterfaceC1407qv {
    public final LJ a;
    public final InterfaceC1101lJ b;
    public final long c;

    public Kv(LJ lj, InterfaceC1101lJ interfaceC1101lJ, long j) {
        this.a = lj;
        this.b = interfaceC1101lJ;
        this.c = j;
    }

    @Override // defpackage.KI
    public void a(Object obj) {
        Iv iv = new Iv();
        iv.b = (String) obj;
        this.b.q(iv.b(this.a, new C1045kJ(6, 2, this.c)));
    }
}
