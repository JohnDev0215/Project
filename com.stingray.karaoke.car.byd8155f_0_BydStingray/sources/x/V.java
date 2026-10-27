package x;

import n7.AbstractC1996k;
import u.C2482v;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class V extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23460m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2634f0 f23461n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ V(C2634f0 c2634f0, int i7) {
        super(1);
        this.f23460m = i7;
        this.f23461n = c2634f0;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f23460m) {
            case 0:
                long jLongValue = ((Number) obj).longValue();
                C2634f0 c2634f0 = this.f23461n;
                long j3 = jLongValue - c2634f0.f23528x;
                c2634f0.f23528x = jLongValue;
                long jY = C7.a.Y(j3 / ((double) c2634f0.B));
                C2482v c2482v = c2634f0.f23529y;
                int i7 = c2482v.f22638b;
                int i10 = 0;
                if (i7 != 0) {
                    Object[] objArr = c2482v.f22637a;
                    for (int i11 = 0; i11 < i7; i11++) {
                        U u4 = (U) objArr[i11];
                        C2634f0.b1(c2634f0, u4, jY);
                        u4.c = true;
                    }
                    x0 x0Var = c2634f0.f23521q;
                    if (x0Var != null) {
                        x0Var.p();
                    }
                    int i12 = c2482v.f22638b;
                    Object[] objArr2 = c2482v.f22637a;
                    G7.d dVarB0 = AbstractC2673a.b0(0, i12);
                    int i13 = dVarB0.l;
                    int i14 = dVarB0.f2712m;
                    if (i13 <= i14) {
                        while (true) {
                            objArr2[i13 - i10] = objArr2[i13];
                            if (((U) objArr2[i13]).c) {
                                i10++;
                            }
                            if (i13 != i14) {
                                i13++;
                            }
                        }
                    }
                    AbstractC1996k.J(objArr2, null, i12 - i10, i12);
                    c2482v.f22638b -= i10;
                }
                U u10 = c2634f0.f23530z;
                if (u10 != null) {
                    u10.f23458g = c2634f0.f23522r;
                    C2634f0.b1(c2634f0, u10, jY);
                    c2634f0.j1(u10.d);
                    if (u10.d == 1.0f) {
                        c2634f0.f23530z = null;
                    }
                    c2634f0.i1();
                }
                break;
            default:
                this.f23461n.f23528x = ((Number) obj).longValue();
                break;
        }
        return m7.q.f19267a;
    }
}
