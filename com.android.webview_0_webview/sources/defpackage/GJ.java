package defpackage;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class GJ extends AbstractC1706wJ {
    public DJ b;
    public C1761xJ c;
    public C1871zJ d;
    public AJ e;

    public static final GJ b(RI ri, int i) {
        PI piJ = ri.j(i);
        if (piJ.a == 0) {
            return null;
        }
        GJ gj = new GJ();
        int i2 = piJ.b;
        if (i2 == 0) {
            gj.b = DJ.c(ri.s(i + 8, false));
            gj.a = 0;
        } else if (i2 == 1) {
            gj.c = C1761xJ.c(ri.s(i + 8, false));
            gj.a = 1;
        } else if (i2 == 2) {
            gj.d = C1871zJ.c(ri.s(i + 8, false));
            gj.a = 2;
        } else if (i2 == 3) {
            gj.e = AJ.c(ri.s(i + 8, false));
            gj.a = 3;
        }
        return gj;
    }

    @Override // defpackage.AbstractC1706wJ
    public final void a(WI wi, int i) {
        wi.c(16, i);
        wi.c(this.a, i + 4);
        int i2 = this.a;
        if (i2 == 0) {
            wi.i(this.b, i + 8, false);
            return;
        }
        if (i2 == 1) {
            wi.i(this.c, i + 8, false);
        } else if (i2 == 2) {
            wi.i(this.d, i + 8, false);
        } else {
            if (i2 != 3) {
                return;
            }
            wi.i(this.e, i + 8, false);
        }
    }
}
