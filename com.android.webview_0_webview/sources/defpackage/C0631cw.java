package defpackage;

/* JADX INFO: renamed from: cw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0631cw implements Vv {
    public final LJ a;
    public final InterfaceC1101lJ b;
    public final long c;

    public C0631cw(LJ lj, InterfaceC1101lJ interfaceC1101lJ, long j) {
        this.a = lj;
        this.b = interfaceC1101lJ;
        this.c = j;
    }

    @Override // defpackage.KI
    public void a(Object obj) {
        C0519aw c0519aw = new C0519aw();
        c0519aw.b = (C1378qK) obj;
        this.b.q(c0519aw.b(this.a, new C1045kJ(0, 2, this.c)));
    }
}
