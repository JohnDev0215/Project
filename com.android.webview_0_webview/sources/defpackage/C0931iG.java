package defpackage;

/* JADX INFO: renamed from: iG, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0931iG implements InterfaceC1702wF {
    public final LJ a;
    public final InterfaceC1101lJ b;
    public final long c;

    public C0931iG(LJ lj, InterfaceC1101lJ interfaceC1101lJ, long j) {
        this.a = lj;
        this.b = interfaceC1101lJ;
        this.c = j;
    }

    @Override // defpackage.KI
    public void a(Object obj) {
        C0819gG c0819gG = new C0819gG();
        c0819gG.b = (C1208nF) obj;
        this.b.q(c0819gG.b(this.a, new C1045kJ(3, 2, this.c)));
    }
}
