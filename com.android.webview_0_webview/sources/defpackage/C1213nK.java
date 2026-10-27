package defpackage;

/* JADX INFO: renamed from: nK, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1213nK extends AbstractC1706wJ {
    public byte[] b;
    public C1268oK c;
    public boolean d;

    public static final C1213nK b(RI ri, int i) {
        PI piJ = ri.j(i);
        if (piJ.a == 0) {
            return null;
        }
        C1213nK c1213nK = new C1213nK();
        int i2 = piJ.b;
        if (i2 == 0) {
            c1213nK.b = ri.e(i + 8, 0, -1);
            c1213nK.a = 0;
        } else if (i2 == 1) {
            c1213nK.c = C1268oK.c(ri.s(i + 8, false));
            c1213nK.a = 1;
        } else if (i2 == 2) {
            c1213nK.d = ri.d(i + 8, 0);
            c1213nK.a = 2;
        }
        return c1213nK;
    }

    @Override // defpackage.AbstractC1706wJ
    public final void a(WI wi, int i) {
        wi.c(16, i);
        wi.c(this.a, i + 4);
        int i2 = this.a;
        if (i2 == 0) {
            wi.m(this.b, i + 8, 0, -1);
        } else if (i2 == 1) {
            wi.i(this.c, i + 8, false);
        } else {
            if (i2 != 2) {
                return;
            }
            wi.l(this.d, i + 8, 0);
        }
    }
}
