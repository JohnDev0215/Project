package androidx.compose.ui.semantics;

import E0.V;
import L0.d;
import g0.n;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/semantics/EmptySemanticsElement;", "LE0/V;", "LL0/d;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class EmptySemanticsElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f11694a;

    public EmptySemanticsElement(d dVar) {
        this.f11694a = dVar;
    }

    public final boolean equals(Object obj) {
        return obj == this;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // E0.V
    public final n i() {
        return this.f11694a;
    }

    @Override // E0.V
    public final /* bridge */ /* synthetic */ void l(n nVar) {
    }
}
