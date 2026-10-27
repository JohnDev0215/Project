package Q;

import T.C0730a0;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: Q.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0505f0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5882m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0523o0 f5883n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0505f0(C0523o0 c0523o0, int i7) {
        super(1);
        this.f5882m = i7;
        this.f5883n = c0523o0;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        int i7;
        switch (this.f5882m) {
            case 0:
                this.f5883n.f6010u.j((int) (((a1.j) obj).f11037a >> 32));
                return m7.q.f19267a;
            case 1:
                float fFloatValue = ((Number) obj).floatValue();
                C0523o0 c0523o0 = this.f5883n;
                G7.a aVar = c0523o0.f6004n;
                float fU = AbstractC2673a.u(fFloatValue, aVar.f2710a, aVar.f2711b);
                boolean z6 = true;
                int i10 = c0523o0.l;
                if (i10 > 0 && (i7 = i10 + 1) >= 0) {
                    float fAbs = fU;
                    float f10 = fAbs;
                    int i11 = 0;
                    while (true) {
                        float fO = AbstractC2673a.O(aVar.f2710a, aVar.f2711b, i11 / i7);
                        float f11 = fO - fU;
                        if (Math.abs(f11) <= fAbs) {
                            fAbs = Math.abs(f11);
                            f10 = fO;
                        }
                        if (i11 != i7) {
                            i11++;
                        } else {
                            fU = f10;
                        }
                    }
                }
                C0730a0 c0730a0 = c0523o0.o;
                if (fU == c0730a0.i()) {
                    z6 = false;
                } else {
                    if (fU != c0730a0.i()) {
                        InterfaceC2782k interfaceC2782k = c0523o0.f6005p;
                        if (interfaceC2782k != null) {
                            interfaceC2782k.b(Float.valueOf(fU));
                        } else {
                            c0523o0.d(fU);
                        }
                    }
                    InterfaceC2772a interfaceC2772a = c0523o0.f6003m;
                    if (interfaceC2772a != null) {
                        interfaceC2772a.a();
                    }
                }
                return Boolean.valueOf(z6);
            default:
                long j3 = ((m0.c) obj).f18880a;
                C0523o0 c0523o02 = this.f5883n;
                c0523o02.b(0.0f);
                c0523o02.f6012w.a();
                return m7.q.f19267a;
        }
    }
}
