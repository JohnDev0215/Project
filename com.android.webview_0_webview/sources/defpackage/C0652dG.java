package defpackage;

/* JADX INFO: renamed from: dG, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0652dG implements InterfaceC1647vF {
    public final LJ a;
    public final InterfaceC1101lJ b;
    public final long c;

    public C0652dG(LJ lj, InterfaceC1101lJ interfaceC1101lJ, long j) {
        this.a = lj;
        this.b = interfaceC1101lJ;
        this.c = j;
    }

    @Override // defpackage.KI
    public void a(Object obj) {
        C0540bG c0540bG = new C0540bG();
        c0540bG.b = (C1208nF) obj;
        this.b.q(c0540bG.b(this.a, new C1045kJ(1, 2, this.c)));
    }
}
