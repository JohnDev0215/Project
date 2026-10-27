package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class EM implements InterfaceC1654vM {
    public final LJ a;
    public final InterfaceC1101lJ b;
    public final long c;

    public EM(LJ lj, InterfaceC1101lJ interfaceC1101lJ, long j) {
        this.a = lj;
        this.b = interfaceC1101lJ;
        this.c = j;
    }

    @Override // defpackage.KI
    public void a(Object obj) {
        CM cm = new CM();
        cm.b = (C1764xM[]) obj;
        this.b.q(cm.b(this.a, new C1045kJ(0, 2, this.c)));
    }
}
