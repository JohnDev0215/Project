package androidx.compose.foundation;

import A.F;
import A7.m;
import E0.AbstractC0156f;
import E0.V;
import L0.u;
import android.view.View;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import y.h0;
import y.i0;
import y.o0;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/MagnifierElement;", "LE0/V;", "Ly/h0;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MagnifierElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f11416a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC2782k f11417b;
    public final InterfaceC2782k c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f11418e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f11419f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f11420g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f11421h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f11422i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final o0 f11423j;

    public MagnifierElement(F f10, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, float f11, boolean z6, long j3, float f12, float f13, boolean z10, o0 o0Var) {
        this.f11416a = f10;
        this.f11417b = interfaceC2782k;
        this.c = interfaceC2782k2;
        this.d = f11;
        this.f11418e = z6;
        this.f11419f = j3;
        this.f11420g = f12;
        this.f11421h = f13;
        this.f11422i = z10;
        this.f11423j = o0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MagnifierElement)) {
            return false;
        }
        MagnifierElement magnifierElement = (MagnifierElement) obj;
        return this.f11416a == magnifierElement.f11416a && this.f11417b == magnifierElement.f11417b && this.d == magnifierElement.d && this.f11418e == magnifierElement.f11418e && this.f11419f == magnifierElement.f11419f && a1.e.a(this.f11420g, magnifierElement.f11420g) && a1.e.a(this.f11421h, magnifierElement.f11421h) && this.f11422i == magnifierElement.f11422i && this.c == magnifierElement.c && this.f11423j.equals(magnifierElement.f11423j);
    }

    public final int hashCode() {
        int iHashCode = this.f11416a.hashCode() * 31;
        InterfaceC2782k interfaceC2782k = this.f11417b;
        int iD = AbstractC1347d.d(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.c(AbstractC1347d.d(AbstractC1347d.b((iHashCode + (interfaceC2782k != null ? interfaceC2782k.hashCode() : 0)) * 31, 31, this.d), 31, this.f11418e), 31, this.f11419f), 31, this.f11420g), 31, this.f11421h), 31, this.f11422i);
        InterfaceC2782k interfaceC2782k2 = this.c;
        return this.f11423j.hashCode() + ((iD + (interfaceC2782k2 != null ? interfaceC2782k2.hashCode() : 0)) * 31);
    }

    @Override // E0.V
    public final n i() {
        o0 o0Var = this.f11423j;
        return new h0(this.f11416a, this.f11417b, this.c, this.d, this.f11418e, this.f11419f, this.f11420g, this.f11421h, this.f11422i, o0Var);
    }

    @Override // E0.V
    public final void l(n nVar) {
        h0 h0Var = (h0) nVar;
        float f10 = h0Var.B;
        long j3 = h0Var.f23907D;
        float f11 = h0Var.f23908E;
        boolean z6 = h0Var.f23906C;
        float f12 = h0Var.f23909F;
        boolean z10 = h0Var.f23910G;
        o0 o0Var = h0Var.f23911H;
        View view = h0Var.f23912I;
        a1.b bVar = h0Var.f23913J;
        h0Var.f23920y = this.f11416a;
        h0Var.f23921z = this.f11417b;
        float f13 = this.d;
        h0Var.B = f13;
        boolean z11 = this.f11418e;
        h0Var.f23906C = z11;
        long j10 = this.f11419f;
        h0Var.f23907D = j10;
        float f14 = this.f11420g;
        h0Var.f23908E = f14;
        float f15 = this.f11421h;
        h0Var.f23909F = f15;
        boolean z12 = this.f11422i;
        h0Var.f23910G = z12;
        h0Var.f23905A = this.c;
        o0 o0Var2 = this.f11423j;
        h0Var.f23911H = o0Var2;
        View viewV = AbstractC0156f.v(h0Var);
        a1.b bVar2 = AbstractC0156f.t(h0Var).f1464C;
        if (h0Var.f23914K != null) {
            u uVar = i0.f23924a;
            if ((!Float.isNaN(f13) || !Float.isNaN(f10)) && f13 != f10) {
                o0Var2.getClass();
            }
            if (j10 != j3 || !a1.e.a(f14, f11) || !a1.e.a(f15, f12) || z11 != z6 || z12 != z10 || !o0Var2.equals(o0Var) || !viewV.equals(view) || !m.a(bVar2, bVar)) {
                h0Var.I0();
            }
        }
        h0Var.J0();
    }
}
