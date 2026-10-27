package org.chromium.gfx.mojom;

import defpackage.AbstractC1651vJ;
import defpackage.PI;
import defpackage.RI;
import defpackage.WI;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Rect extends AbstractC1651vJ {
    public static final PI[] f;
    public static final PI g;
    public int b;
    public int c;
    public int d;
    public int e;

    static {
        PI[] piArr = {new PI(24, 0)};
        f = piArr;
        g = piArr[0];
    }

    public Rect(int i) {
        super(24, i);
    }

    public static Rect c(RI ri) {
        if (ri == null) {
            return null;
        }
        ri.b();
        try {
            Rect rect = new Rect(ri.c(f).b);
            rect.b = ri.m(8);
            rect.c = ri.m(12);
            rect.d = ri.m(16);
            rect.e = ri.m(20);
            return rect;
        } finally {
            ri.a();
        }
    }

    @Override // defpackage.AbstractC1651vJ
    public final void a(WI wi) {
        WI wiU = wi.u(g);
        wiU.c(this.b, 8);
        wiU.c(this.c, 12);
        wiU.c(this.d, 16);
        wiU.c(this.e, 20);
    }
}
