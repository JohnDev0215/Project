package androidx.compose.material3;

import A7.m;
import C.k;
import E0.AbstractC0156f;
import E0.V;
import Q.g1;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import x.AbstractC2631e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/material3/ThumbElement;", "LE0/V;", "LQ/g1;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class ThumbElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f11535a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f11536b;

    public ThumbElement(k kVar, boolean z6) {
        this.f11535a = kVar;
        this.f11536b = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ThumbElement)) {
            return false;
        }
        ThumbElement thumbElement = (ThumbElement) obj;
        return m.a(this.f11535a, thumbElement.f11535a) && this.f11536b == thumbElement.f11536b;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f11536b) + (this.f11535a.hashCode() * 31);
    }

    @Override // E0.V
    public final n i() {
        g1 g1Var = new g1();
        g1Var.f5898y = this.f11535a;
        g1Var.f5899z = this.f11536b;
        g1Var.f5896D = Float.NaN;
        g1Var.f5897E = Float.NaN;
        return g1Var;
    }

    @Override // E0.V
    public final void l(n nVar) {
        g1 g1Var = (g1) nVar;
        g1Var.f5898y = this.f11535a;
        boolean z6 = g1Var.f5899z;
        boolean z10 = this.f11536b;
        if (z6 != z10) {
            AbstractC0156f.n(g1Var);
        }
        g1Var.f5899z = z10;
        if (g1Var.f5895C == null && !Float.isNaN(g1Var.f5897E)) {
            g1Var.f5895C = AbstractC2631e.a(g1Var.f5897E);
        }
        if (g1Var.B != null || Float.isNaN(g1Var.f5896D)) {
            return;
        }
        g1Var.B = AbstractC2631e.a(g1Var.f5896D);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ThumbElement(interactionSource=");
        sb.append(this.f11535a);
        sb.append(", checked=");
        return AbstractC1347d.i(sb, this.f11536b, ')');
    }
}
