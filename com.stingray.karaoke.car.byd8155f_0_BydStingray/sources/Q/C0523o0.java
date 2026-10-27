package Q;

import A.InterfaceC0007c0;
import S8.AbstractC0727y;
import T.C0730a0;
import T.C0732b0;
import T.C0735d;
import T.C0738e0;
import r7.EnumC2303a;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: Q.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0523o0 implements InterfaceC0007c0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final y.n0 f6002A;
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public InterfaceC2772a f6003m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final G7.a f6004n;
    public final C0730a0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public InterfaceC2782k f6005p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final float[] f6006q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C0732b0 f6007r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f6008s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0730a0 f6009t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C0730a0 f6010u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final C0738e0 f6011v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final K7.K f6012w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final C0730a0 f6013x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final C0730a0 f6014y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final C0521n0 f6015z;

    public C0523o0(float f10, int i7, InterfaceC2772a interfaceC2772a, G7.a aVar) {
        float[] fArr;
        this.l = i7;
        this.f6003m = interfaceC2772a;
        this.f6004n = aVar;
        this.o = C0735d.L(f10);
        if (i7 == 0) {
            fArr = new float[0];
        } else {
            int i10 = i7 + 2;
            float[] fArr2 = new float[i10];
            for (int i11 = 0; i11 < i10; i11++) {
                fArr2[i11] = i11 / (i7 + 1);
            }
            fArr = fArr2;
        }
        this.f6006q = fArr;
        this.f6007r = C0735d.M(0);
        this.f6009t = C0735d.L(0.0f);
        this.f6010u = C0735d.L(0.0f);
        this.f6011v = C0735d.O(Boolean.FALSE, T.S.f8224q);
        this.f6012w = new K7.K(this, 12);
        G7.a aVar2 = this.f6004n;
        float f11 = aVar2.f2710a;
        float f12 = aVar2.f2711b - f11;
        this.f6013x = C0735d.L(AbstractC2673a.O(0.0f, 0.0f, AbstractC2673a.u(f12 == 0.0f ? 0.0f : (f10 - f11) / f12, 0.0f, 1.0f)));
        this.f6014y = C0735d.L(0.0f);
        this.f6015z = new C0521n0(this, 0);
        this.f6002A = new y.n0();
    }

    @Override // A.InterfaceC0007c0
    public final Object a(A.Y y10, A.U u4) {
        Object objH = AbstractC0727y.h(new C0519m0(this, y10, null), u4);
        return objH == EnumC2303a.l ? objH : m7.q.f19267a;
    }

    public final void b(float f10) {
        float fI = this.f6007r.i();
        C0730a0 c0730a0 = this.f6010u;
        float f11 = 2;
        float fMax = Math.max(fI - (c0730a0.i() / f11), 0.0f);
        float fMin = Math.min(c0730a0.i() / f11, fMax);
        C0730a0 c0730a02 = this.f6013x;
        float fI2 = c0730a02.i() + f10;
        C0730a0 c0730a03 = this.f6014y;
        c0730a02.j(c0730a03.i() + fI2);
        c0730a03.j(0.0f);
        float fE = AbstractC0517l0.e(c0730a02.i(), fMin, fMax, this.f6006q);
        G7.a aVar = this.f6004n;
        float f12 = fMax - fMin;
        float fO = AbstractC2673a.O(aVar.f2710a, aVar.f2711b, AbstractC2673a.u(f12 == 0.0f ? 0.0f : (fE - fMin) / f12, 0.0f, 1.0f));
        if (fO == this.o.i()) {
            return;
        }
        InterfaceC2782k interfaceC2782k = this.f6005p;
        if (interfaceC2782k != null) {
            interfaceC2782k.b(Float.valueOf(fO));
        } else {
            d(fO);
        }
    }

    public final float c() {
        G7.a aVar = this.f6004n;
        float f10 = aVar.f2710a;
        float fI = this.o.i();
        float f11 = aVar.f2710a;
        float f12 = aVar.f2711b;
        float fU = AbstractC2673a.u(fI, f11, f12);
        float f13 = f12 - f10;
        return AbstractC2673a.u(f13 == 0.0f ? 0.0f : (fU - f10) / f13, 0.0f, 1.0f);
    }

    public final void d(float f10) {
        G7.a aVar = this.f6004n;
        float f11 = aVar.f2710a;
        float f12 = aVar.f2711b;
        this.o.j(AbstractC0517l0.e(AbstractC2673a.u(f10, f11, f12), aVar.f2710a, f12, this.f6006q));
    }
}
