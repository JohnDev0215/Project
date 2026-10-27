package defpackage;

/* JADX INFO: renamed from: hJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0878hJ {
    public static boolean a(LJ lj, AbstractC0599cJ abstractC0599cJ, C1541tJ c1541tJ, InterfaceC1101lJ interfaceC1101lJ) {
        FJ fjC = FJ.c(c1541tJ.b());
        JJ jj = new JJ();
        IJ ij = new IJ();
        jj.b = ij;
        if (fjC.b.a == 0) {
            CJ cj = new CJ();
            ij.a = 0;
            ij.b = cj;
            jj.b.b.b = abstractC0599cJ.g();
        } else {
            jj.b = null;
        }
        return interfaceC1101lJ.q(jj.b(lj, new C1045kJ(-1, 2, c1541tJ.d.d)));
    }

    public static boolean b(AbstractC0599cJ abstractC0599cJ, C1541tJ c1541tJ) {
        GJ gj = HJ.c(c1541tJ.b()).b;
        return gj.a == 0 && gj.b.b <= abstractC0599cJ.g();
    }
}
