package defpackage;

/* JADX INFO: renamed from: iF, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0930iF implements InterfaceC0595cF {
    public final LJ a;
    public final InterfaceC1101lJ b;
    public final long c;

    public C0930iF(LJ lj, InterfaceC1101lJ interfaceC1101lJ, long j) {
        this.a = lj;
        this.b = interfaceC1101lJ;
        this.c = j;
    }

    @Override // defpackage.KI
    public void a(Object obj) {
        C0818gF c0818gF = new C0818gF();
        c0818gF.b = (C1153mF) obj;
        this.b.q(c0818gF.b(this.a, new C1045kJ(0, 2, this.c)));
    }
}
