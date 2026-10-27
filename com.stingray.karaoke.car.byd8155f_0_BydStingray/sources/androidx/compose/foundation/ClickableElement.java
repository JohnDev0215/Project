package androidx.compose.foundation;

import A7.m;
import C.k;
import E0.V;
import L0.g;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import y.C2719y;
import y.e0;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/ClickableElement;", "LE0/V;", "Ly/y;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ClickableElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f11402a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e0 f11403b;
    public final boolean c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g f11404e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final InterfaceC2772a f11405f;

    public ClickableElement(k kVar, e0 e0Var, boolean z6, String str, g gVar, InterfaceC2772a interfaceC2772a) {
        this.f11402a = kVar;
        this.f11403b = e0Var;
        this.c = z6;
        this.d = str;
        this.f11404e = gVar;
        this.f11405f = interfaceC2772a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ClickableElement.class != obj.getClass()) {
            return false;
        }
        ClickableElement clickableElement = (ClickableElement) obj;
        return m.a(this.f11402a, clickableElement.f11402a) && m.a(this.f11403b, clickableElement.f11403b) && this.c == clickableElement.c && m.a(this.d, clickableElement.d) && m.a(this.f11404e, clickableElement.f11404e) && this.f11405f == clickableElement.f11405f;
    }

    public final int hashCode() {
        k kVar = this.f11402a;
        int iHashCode = (kVar != null ? kVar.hashCode() : 0) * 31;
        e0 e0Var = this.f11403b;
        int iD = AbstractC1347d.d((iHashCode + (e0Var != null ? e0Var.hashCode() : 0)) * 31, 31, this.c);
        String str = this.d;
        int iHashCode2 = (iD + (str != null ? str.hashCode() : 0)) * 31;
        g gVar = this.f11404e;
        return this.f11405f.hashCode() + ((iHashCode2 + (gVar != null ? Integer.hashCode(gVar.f3883a) : 0)) * 31);
    }

    @Override // E0.V
    public final n i() {
        return new C2719y(this.f11402a, this.f11403b, this.c, this.d, this.f11404e, this.f11405f);
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((C2719y) nVar).O0(this.f11402a, this.f11403b, this.c, this.d, this.f11404e, this.f11405f);
    }
}
