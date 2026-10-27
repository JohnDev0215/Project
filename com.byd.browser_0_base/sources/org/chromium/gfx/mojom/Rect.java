package org.chromium.gfx.mojom;

import defpackage.e70;
import defpackage.hv2;
import defpackage.qh0;
import defpackage.t50;

/* JADX INFO: loaded from: classes3.dex */
public final class Rect extends hv2 {
    public static final t50[] f;
    public static final t50 g;
    public int b;
    public int c;
    public int d;
    public int e;

    static {
        t50 t50Var = new t50(24, 0);
        f = new t50[]{t50Var};
        g = t50Var;
    }

    public Rect(int i) {
        super(24);
    }

    public static Rect d(e70 e70Var) {
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            Rect rect = new Rect(e70Var.c(f).b);
            rect.b = e70Var.p(8);
            rect.c = e70Var.p(12);
            rect.d = e70Var.p(16);
            rect.e = e70Var.p(20);
            return rect;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(g);
        qh0VarT.b(this.b, 8);
        qh0VarT.b(this.c, 12);
        qh0VarT.b(this.d, 16);
        qh0VarT.b(this.e, 20);
    }

    public Rect() {
        this(0);
    }
}
