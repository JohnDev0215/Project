package androidx.compose.material.ripple;

import A7.o;
import C.k;
import C.l;
import C.m;
import E0.AbstractC0156f;
import E0.H;
import E0.InterfaceC0162l;
import E0.InterfaceC0165o;
import E0.InterfaceC0173x;
import P.C;
import P.q;
import P.v;
import S8.AbstractC0727y;
import a1.b;
import g0.n;
import kotlin.Metadata;
import n0.InterfaceC1892u;
import n7.AbstractC1996k;
import u.C2482v;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b \u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/material/ripple/RippleNode;", "Lg0/n;", "LE0/l;", "LE0/o;", "LE0/x;", "Ln0/u;", "color", "Ln0/u;", "material-ripple_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class RippleNode extends n implements InterfaceC0162l, InterfaceC0165o, InterfaceC0173x {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final float f11523A;
    public final o B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public C f11524C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public float f11525D;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f11527F;
    private final InterfaceC1892u color;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final k f11529y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final boolean f11530z;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public long f11526E = 0;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final C2482v f11528G = new C2482v();

    /* JADX WARN: Multi-variable type inference failed */
    public RippleNode(k kVar, boolean z6, float f10, InterfaceC1892u interfaceC1892u, InterfaceC2772a interfaceC2772a) {
        this.f11529y = kVar;
        this.f11530z = z6;
        this.f11523A = f10;
        this.color = interfaceC1892u;
        this.B = (o) interfaceC2772a;
    }

    public abstract void H0(m mVar, long j3, float f10);

    public abstract void I0(H h10);

    public final long J0() {
        return this.color.a();
    }

    public final void K0(C.o oVar) {
        if (oVar instanceof m) {
            H0((m) oVar, this.f11526E, this.f11525D);
        } else if (oVar instanceof C.n) {
            L0(((C.n) oVar).f835a);
        } else if (oVar instanceof l) {
            L0(((l) oVar).f833a);
        }
    }

    public abstract void L0(m mVar);

    @Override // E0.InterfaceC0165o
    public final void a(H h10) {
        h10.a();
        C c = this.f11524C;
        if (c != null) {
            c.j(h10, this.f11525D, this.color.a());
        }
        I0(h10);
    }

    @Override // E0.InterfaceC0173x
    public final void u(long j3) {
        this.f11527F = true;
        b bVar = AbstractC0156f.t(this).f1464C;
        this.f11526E = H7.H.S(j3);
        float f10 = this.f11523A;
        this.f11525D = Float.isNaN(f10) ? q.a(bVar, this.f11530z, this.f11526E) : bVar.y(f10);
        C2482v c2482v = this.f11528G;
        Object[] objArr = c2482v.f22637a;
        int i7 = c2482v.f22638b;
        for (int i10 = 0; i10 < i7; i10++) {
            K0((C.o) objArr[i10]);
        }
        AbstractC1996k.J(c2482v.f22637a, null, 0, c2482v.f22638b);
        c2482v.f22638b = 0;
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }

    @Override // g0.n
    public final void z0() {
        AbstractC0727y.w(v0(), null, null, new v(this, null), 3);
    }
}
