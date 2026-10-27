package R;

import T.C0738e0;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: R.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0687o extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7311m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ w f7312n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0687o(w wVar, int i7) {
        super(0);
        this.f7311m = i7;
        this.f7312n = wVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        Object objB;
        switch (this.f7311m) {
            case 0:
                return this.f7312n.d();
            case 1:
                w wVar = this.f7312n;
                return new m7.i(wVar.d(), wVar.f7330g.getValue());
            case 2:
                w wVar2 = this.f7312n;
                Object value = wVar2.f7334k.getValue();
                if (value != null) {
                    return value;
                }
                float fI = wVar2.f7332i.i();
                boolean zIsNaN = Float.isNaN(fI);
                C0738e0 c0738e0 = wVar2.f7329f;
                if (zIsNaN) {
                    return c0738e0.getValue();
                }
                Object value2 = c0738e0.getValue();
                E eD = wVar2.d();
                float fC = eD.c(value2);
                if (fC != fI && !Float.isNaN(fC) && (fC >= fI ? (objB = eD.b(fI, false)) != null : (objB = eD.b(fI, true)) != null)) {
                    value2 = objB;
                }
                return value2;
            case 3:
                w wVar3 = this.f7312n;
                float fC2 = wVar3.d().c(wVar3.f7329f.getValue());
                float fC3 = wVar3.d().c(wVar3.f7331h.getValue()) - fC2;
                float fAbs = Math.abs(fC3);
                float f10 = 1.0f;
                if (!Float.isNaN(fAbs) && fAbs > 1.0E-6f) {
                    float fE = (wVar3.e() - fC2) / fC3;
                    if (fE < 1.0E-6f) {
                        f10 = 0.0f;
                    } else if (fE <= 0.999999f) {
                        f10 = fE;
                    }
                }
                return Float.valueOf(f10);
            default:
                w wVar4 = this.f7312n;
                Object value3 = wVar4.f7334k.getValue();
                if (value3 != null) {
                    return value3;
                }
                float fI2 = wVar4.f7332i.i();
                boolean zIsNaN2 = Float.isNaN(fI2);
                C0738e0 c0738e02 = wVar4.f7329f;
                return !zIsNaN2 ? wVar4.c(fI2, 0.0f, c0738e02.getValue()) : c0738e02.getValue();
        }
    }
}
