package androidx.compose.ui.input.pointer;

import A7.z;
import E0.AbstractC0156f;
import E0.V;
import G.g0;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import y0.C2723a;
import y0.C2733k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerHoverIconModifierElement;", "LE0/V;", "Ly0/k;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class PointerHoverIconModifierElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2723a f11608a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f11609b;

    public PointerHoverIconModifierElement(C2723a c2723a, boolean z6) {
        this.f11608a = c2723a;
        this.f11609b = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PointerHoverIconModifierElement)) {
            return false;
        }
        PointerHoverIconModifierElement pointerHoverIconModifierElement = (PointerHoverIconModifierElement) obj;
        return this.f11608a.equals(pointerHoverIconModifierElement.f11608a) && this.f11609b == pointerHoverIconModifierElement.f11609b;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f11609b) + (this.f11608a.f24049b * 31);
    }

    @Override // E0.V
    public final n i() {
        C2723a c2723a = this.f11608a;
        C2733k c2733k = new C2733k();
        c2733k.f24070y = c2723a;
        c2733k.f24071z = this.f11609b;
        return c2733k;
    }

    @Override // E0.V
    public final void l(n nVar) {
        C2733k c2733k = (C2733k) nVar;
        C2723a c2723a = c2733k.f24070y;
        C2723a c2723a2 = this.f11608a;
        if (!c2723a.equals(c2723a2)) {
            c2733k.f24070y = c2723a2;
            if (c2733k.f24069A) {
                c2733k.I0();
            }
        }
        boolean z6 = c2733k.f24071z;
        boolean z10 = this.f11609b;
        if (z6 != z10) {
            c2733k.f24071z = z10;
            if (z10) {
                if (c2733k.f24069A) {
                    c2733k.H0();
                    return;
                }
                return;
            }
            boolean z11 = c2733k.f24069A;
            if (z11 && z11) {
                if (!z10) {
                    z zVar = new z();
                    AbstractC0156f.x(c2733k, new g0(zVar, 3));
                    C2733k c2733k2 = (C2733k) zVar.l;
                    if (c2733k2 != null) {
                        c2733k = c2733k2;
                    }
                }
                c2733k.H0();
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PointerHoverIconModifierElement(icon=");
        sb.append(this.f11608a);
        sb.append(", overrideDescendants=");
        return AbstractC1347d.i(sb, this.f11609b, ')');
    }
}
