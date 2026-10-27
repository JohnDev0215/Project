package defpackage;

/* JADX INFO: renamed from: rI, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1430rI extends AbstractC0543bJ implements InterfaceC1266oI, InterfaceC0710eJ {
    public C1430rI(LJ lj, InterfaceC1157mJ interfaceC1157mJ) {
        super(lj, interfaceC1157mJ);
    }

    @Override // defpackage.InterfaceC1266oI
    public void b(C0934iJ c0934iJ, InterfaceC0765fI interfaceC0765fI, C1211nI c1211nI) {
        C1376qI c1376qI = new C1376qI();
        c1376qI.b = c0934iJ;
        c1376qI.c = interfaceC0765fI;
        c1376qI.d = c1211nI;
        C0487aJ c0487aJ = this.s;
        c0487aJ.t.q(c1376qI.b(c0487aJ.s, new C1045kJ(0)));
    }

    @Override // defpackage.InterfaceC0710eJ
    public InterfaceC0655dJ w() {
        return this.s;
    }
}
