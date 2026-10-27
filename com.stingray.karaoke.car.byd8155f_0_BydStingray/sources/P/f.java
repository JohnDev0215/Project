package P;

import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.X;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import h2.AbstractC1347d;
import n0.C1891t;
import y.Z;
import y.a0;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f5225a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f5226b;
    public final X c;

    public f(boolean z6, float f10, X x8) {
        this.f5225a = z6;
        this.f5226b = f10;
        this.c = x8;
    }

    @Override // y.Z
    public final a0 a(C.k kVar, C0761q c0761q) {
        long jA;
        c0761q.Q(988743187);
        w wVar = (w) c0761q.k(y.f5261a);
        X x8 = this.c;
        if (((C1891t) x8.getValue()).f19453a != 16) {
            c0761q.Q(-303571590);
            c0761q.p(false);
            jA = ((C1891t) x8.getValue()).f19453a;
        } else {
            c0761q.Q(-303521246);
            jA = wVar.a(c0761q);
            c0761q.p(false);
        }
        X xR = C0735d.R(new C1891t(jA), c0761q);
        X xR2 = C0735d.R(wVar.b(c0761q), c0761q);
        c0761q.Q(331259447);
        ViewGroup viewGroupB = z.b((View) c0761q.k(AndroidCompositionLocals_androidKt.f11690f));
        boolean zF = c0761q.f(kVar) | c0761q.f(this) | c0761q.f(viewGroupB);
        Object objG = c0761q.G();
        Object obj = C0753m.f8270a;
        if (zF || objG == obj) {
            objG = new C0489a(this.f5225a, this.f5226b, xR, xR2, viewGroupB);
            c0761q.a0(objG);
        }
        C0489a c0489a = (C0489a) objG;
        c0761q.p(false);
        boolean zF2 = c0761q.f(kVar) | c0761q.h(c0489a);
        Object objG2 = c0761q.G();
        if (zF2 || objG2 == obj) {
            objG2 = new g(kVar, c0489a, null);
            c0761q.a0(objG2);
        }
        C0735d.g(c0489a, kVar, (InterfaceC2785n) objG2, c0761q);
        c0761q.p(false);
        return c0489a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return this.f5225a == fVar.f5225a && a1.e.a(this.f5226b, fVar.f5226b) && this.c.equals(fVar.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + AbstractC1347d.b(Boolean.hashCode(this.f5225a) * 31, 31, this.f5226b);
    }
}
