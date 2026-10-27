package defpackage;

import android.app.Activity;
import java.util.Objects;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;

/* JADX INFO: compiled from: CustomTabActivityTabFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class z20 {
    public final Activity a;
    public final h40 b;
    public final qr c;
    public final p61<g3> d;
    public final p61<o30> e;
    public final oj f;
    public final gz2 g;
    public final nw2<y13> h;
    public final nw2<CompositorViewHolder> i;
    public final p61<f9> j;
    public k50 k;

    public z20(Activity activity, h40 h40Var, qr qrVar, p61<g3> p61Var, p61<o30> p61Var2, oj ojVar, p61<f9> p61Var3, gz2 gz2Var, nw2<y13> nw2Var, nw2<CompositorViewHolder> nw2Var2) {
        this.a = activity;
        this.b = h40Var;
        this.c = qrVar;
        this.d = p61Var;
        this.e = p61Var2;
        this.f = ojVar;
        this.j = p61Var3;
        this.g = gz2Var;
        this.h = nw2Var;
        this.i = nw2Var2;
    }

    public final or a(boolean z) {
        Activity activity = this.a;
        g3 g3Var = this.d.get();
        p61<o30> p61Var = this.e;
        Objects.requireNonNull(p61Var);
        return new or(activity, g3Var, new yq2(8, p61Var), z, null, h9.a, this.h, this.i);
    }

    public final void b() {
        k50 k50Var = this.k;
        p61<g3> p61Var = this.d;
        Objects.requireNonNull(p61Var);
        hs hsVar = new hs(2, p61Var);
        gz2 gz2Var = this.g;
        qr qrVar = this.c;
        f9 f9Var = this.j.get();
        k50Var.getClass();
        k50Var.b = new d23(hsVar, gz2Var, qrVar, new zm1() { // from class: j50
            @Override // defpackage.nw2
            public final Integer get() {
                return 1;
            }
        }, f9Var, false, 1);
        k50Var.c = this.b;
        k50Var.a = new z23(k50Var.c, k50Var.b, gz2Var);
        k50Var.a.f.a(new u13(k50Var));
        k50Var.d = true;
    }

    public final a23 c() {
        if (this.k == null) {
            this.k = new k50();
        }
        if (this.k.b == null) {
            b();
        }
        return this.k.b;
    }
}
