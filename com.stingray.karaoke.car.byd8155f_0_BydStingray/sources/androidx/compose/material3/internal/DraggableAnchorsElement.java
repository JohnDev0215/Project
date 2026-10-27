package androidx.compose.material3.internal;

import A.EnumC0017h0;
import A7.m;
import E0.V;
import R.w;
import R.y;
import g0.n;
import kotlin.Metadata;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0002¨\u0006\u0004"}, d2 = {"Landroidx/compose/material3/internal/DraggableAnchorsElement;", "T", "LE0/V;", "LR/y;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DraggableAnchorsElement<T> extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w f11546a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC2785n f11547b;

    public DraggableAnchorsElement(w wVar, InterfaceC2785n interfaceC2785n) {
        this.f11546a = wVar;
        this.f11547b = interfaceC2785n;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DraggableAnchorsElement)) {
            return false;
        }
        DraggableAnchorsElement draggableAnchorsElement = (DraggableAnchorsElement) obj;
        return m.a(this.f11546a, draggableAnchorsElement.f11546a) && this.f11547b == draggableAnchorsElement.f11547b;
    }

    public final int hashCode() {
        return EnumC0017h0.f181m.hashCode() + ((this.f11547b.hashCode() + (this.f11546a.hashCode() * 31)) * 31);
    }

    @Override // E0.V
    public final n i() {
        y yVar = new y();
        yVar.f7338y = this.f11546a;
        yVar.f7339z = this.f11547b;
        yVar.f7337A = EnumC0017h0.f181m;
        return yVar;
    }

    @Override // E0.V
    public final void l(n nVar) {
        y yVar = (y) nVar;
        yVar.f7338y = this.f11546a;
        yVar.f7339z = this.f11547b;
        yVar.f7337A = EnumC0017h0.f181m;
    }
}
