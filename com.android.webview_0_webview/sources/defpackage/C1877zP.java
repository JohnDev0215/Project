package defpackage;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX INFO: renamed from: zP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1877zP extends AbstractC1651vJ {
    public static final PI[] f;
    public static final PI g;
    public String b;
    public String c;
    public short d;
    public C1378qK e;

    static {
        PI[] piArr = {new PI(40, 0)};
        f = piArr;
        g = piArr[0];
    }

    public C1877zP(int i) {
        super(40, i);
    }

    public static C1877zP c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            C1877zP c1877zP = new C1877zP(ri.c(f).b);
            c1877zP.b = ri.u(8, false);
            c1877zP.c = ri.u(16, false);
            ri.w(24, 2);
            c1877zP.d = ri.a.a.getShort(ri.b + 24);
            c1877zP.e = C1378qK.c(ri.s(32, true));
            return c1877zP;
        } finally {
            ri.a();
        }
    }

    public static C1877zP d(ByteBuffer byteBuffer) {
        return c(new RI(new C0989jJ(byteBuffer, new ArrayList())));
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(g);
        wiU.e(this.b, 8, false);
        wiU.e(this.c, 16, false);
        wiU.b.b.putShort(wiU.a + 24, this.d);
        wiU.i(this.e, 32, true);
    }
}
