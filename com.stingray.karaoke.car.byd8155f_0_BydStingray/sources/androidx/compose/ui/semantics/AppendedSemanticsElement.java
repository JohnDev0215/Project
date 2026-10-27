package androidx.compose.ui.semantics;

import E0.V;
import L0.c;
import g0.m;
import g0.n;
import kotlin.Metadata;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/semantics/AppendedSemanticsElement;", "LE0/V;", "LL0/c;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class AppendedSemanticsElement extends V implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f11692a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC2782k f11693b;

    public AppendedSemanticsElement(InterfaceC2782k interfaceC2782k, boolean z6) {
        this.f11692a = z6;
        this.f11693b = interfaceC2782k;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppendedSemanticsElement)) {
            return false;
        }
        AppendedSemanticsElement appendedSemanticsElement = (AppendedSemanticsElement) obj;
        return this.f11692a == appendedSemanticsElement.f11692a && A7.m.a(this.f11693b, appendedSemanticsElement.f11693b);
    }

    public final int hashCode() {
        return this.f11693b.hashCode() + (Boolean.hashCode(this.f11692a) * 31);
    }

    @Override // E0.V
    public final n i() {
        c cVar = new c();
        cVar.f3879y = this.f11692a;
        cVar.f3880z = this.f11693b;
        return cVar;
    }

    @Override // E0.V
    public final void l(n nVar) {
        c cVar = (c) nVar;
        cVar.f3879y = this.f11692a;
        cVar.f3880z = this.f11693b;
    }

    public final String toString() {
        return "AppendedSemanticsElement(mergeDescendants=" + this.f11692a + ", properties=" + this.f11693b + ')';
    }
}
