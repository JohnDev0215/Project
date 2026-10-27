package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ZF implements InterfaceC1592uF {
    public final LJ a;
    public final InterfaceC1101lJ b;
    public final long c;

    public ZF(LJ lj, InterfaceC1101lJ interfaceC1101lJ, long j) {
        this.a = lj;
        this.b = interfaceC1101lJ;
        this.c = j;
    }

    @Override // defpackage.KI
    public void a(Object obj) {
        XF xf = new XF();
        xf.b = (C1208nF) obj;
        this.b.q(xf.b(this.a, new C1045kJ(4, 2, this.c)));
    }
}
