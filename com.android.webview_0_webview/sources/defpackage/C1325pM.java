package defpackage;

/* JADX INFO: renamed from: pM, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1325pM implements InterfaceC0602cM {
    public final LJ a;
    public final InterfaceC1101lJ b;
    public final long c;

    public C1325pM(LJ lj, InterfaceC1101lJ interfaceC1101lJ, long j) {
        this.a = lj;
        this.b = interfaceC1101lJ;
        this.c = j;
    }

    @Override // defpackage.KI
    public void a(Object obj) {
        C1215nM c1215nM = new C1215nM();
        c1215nM.b = (C1048kM[]) obj;
        this.b.q(c1215nM.b(this.a, new C1045kJ(0, 2, this.c)));
    }
}
