package k3;

import android.text.TextUtils;
import d5.C1077F;
import e2.BinderC1198f;
import h2.AbstractC1346c;
import java.util.List;
import l3.C1740E;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class V implements X, P0, h2.l {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f17864m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f17865n;
    public final /* synthetic */ Object o;

    public /* synthetic */ V(int i7, e2.V v10, e2.V v11) {
        this.l = 4;
        this.f17865n = i7;
        this.f17864m = v10;
        this.o = v11;
    }

    @Override // h2.l
    public void b(Object obj) {
        e2.U u4 = (e2.U) obj;
        int i7 = this.f17865n;
        u4.i(i7);
        u4.t(i7, (e2.V) this.f17864m, (e2.V) this.o);
    }

    @Override // k3.P0
    public void d(C1679y0 c1679y0) {
        Q0 q02 = (Q0) this.f17864m;
        q02.getClass();
        C1740E c1740e = (C1740E) this.o;
        if (TextUtils.isEmpty(c1740e.l)) {
            AbstractC1346c.z("MediaSessionLegacyStub", "onAddQueueItem(): Media ID shouldn't be empty");
            return;
        }
        h5.y yVarJ = q02.f17843f.j(c1679y0, d5.I.B(AbstractC1663q.i(c1740e)));
        B5.u uVar = new B5.u(q02, c1679y0, this.f17865n);
        yVarJ.b(new h5.t(yVarJ, 0, uVar), h5.r.l);
    }

    @Override // k3.X
    public void h(InterfaceC1655m interfaceC1655m, int i7) {
        switch (this.l) {
            case 0:
                C1628b0 c1628b0 = (C1628b0) this.f17864m;
                c1628b0.getClass();
                C1077F c1077fU = d5.I.u();
                int i10 = 0;
                while (true) {
                    List list = (List) this.o;
                    if (i10 >= list.size()) {
                        interfaceC1655m.A0(c1628b0.c, i7, this.f17865n, new BinderC1198f(c1077fU.h()));
                    } else {
                        c1077fU.a(((e2.F) list.get(i10)).d(true));
                        i10++;
                    }
                    break;
                }
                break;
            default:
                C1628b0 c1628b02 = (C1628b0) this.f17864m;
                E1 e12 = c1628b02.l;
                e12.getClass();
                int iR = e12.f17758a.r();
                int i11 = this.f17865n;
                e2.F f10 = (e2.F) this.o;
                BinderC1658n0 binderC1658n0 = c1628b02.c;
                if (iR < 2) {
                    interfaceC1655m.e1(binderC1658n0, i7, i11 + 1, f10.d(true));
                    interfaceC1655m.k0(binderC1658n0, i7, i11);
                } else {
                    interfaceC1655m.F(binderC1658n0, i7, i11, f10.d(true));
                }
                break;
        }
    }

    public /* synthetic */ V(Object obj, int i7, Object obj2, int i10) {
        this.l = i10;
        this.f17864m = obj;
        this.f17865n = i7;
        this.o = obj2;
    }

    public /* synthetic */ V(Q0 q02, C1740E c1740e, int i7) {
        this.l = 3;
        this.f17864m = q02;
        this.o = c1740e;
        this.f17865n = i7;
    }
}
