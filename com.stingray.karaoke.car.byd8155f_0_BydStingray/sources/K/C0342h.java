package K;

import B2.C0074n;
import T.C0735d;
import T.C0761q;
import n0.AbstractC1887p;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: K.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0342h extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2786o f3269A;
    public final /* synthetic */ int B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final /* synthetic */ int f3270C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final /* synthetic */ Object f3271D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final /* synthetic */ Object f3272E;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3273m = 1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f3274n;
    public final /* synthetic */ g0.o o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ N0.L f3275p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C0074n f3276q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f3277r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ C.k f3278s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ AbstractC1887p f3279t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ boolean f3280u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ int f3281v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ int f3282w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ Z f3283x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ boolean f3284y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final /* synthetic */ boolean f3285z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0342h(T0.y yVar, InterfaceC2782k interfaceC2782k, g0.o oVar, N0.L l, C0074n c0074n, InterfaceC2782k interfaceC2782k2, C.k kVar, AbstractC1887p abstractC1887p, boolean z6, int i7, int i10, T0.m mVar, Z z10, boolean z11, boolean z12, InterfaceC2786o interfaceC2786o, int i11, int i12) {
        super(2);
        this.f3271D = yVar;
        this.f3274n = interfaceC2782k;
        this.o = oVar;
        this.f3275p = l;
        this.f3276q = c0074n;
        this.f3277r = interfaceC2782k2;
        this.f3278s = kVar;
        this.f3279t = abstractC1887p;
        this.f3280u = z6;
        this.f3281v = i7;
        this.f3282w = i10;
        this.f3272E = mVar;
        this.f3283x = z10;
        this.f3284y = z11;
        this.f3285z = z12;
        this.f3269A = interfaceC2786o;
        this.B = i11;
        this.f3270C = i12;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f3273m) {
            case 0:
                ((Number) obj2).intValue();
                int iZ = C0735d.Z(this.B | 1);
                int iZ2 = C0735d.Z(this.f3270C);
                InterfaceC2782k interfaceC2782k = this.f3277r;
                C.k kVar = this.f3278s;
                AbstractC0344i.a((String) this.f3271D, this.f3274n, this.o, this.f3280u, this.f3284y, this.f3275p, (C0329a0) this.f3272E, this.f3283x, this.f3285z, this.f3281v, this.f3282w, this.f3276q, interfaceC2782k, kVar, this.f3279t, this.f3269A, (C0761q) obj, iZ, iZ2);
                break;
            default:
                ((Number) obj2).intValue();
                int iZ3 = C0735d.Z(this.B | 1);
                int iZ4 = C0735d.Z(this.f3270C);
                T0.y yVar = (T0.y) this.f3271D;
                Z z6 = this.f3283x;
                boolean z10 = this.f3284y;
                X.d(yVar, this.f3274n, this.o, this.f3275p, this.f3276q, this.f3277r, this.f3278s, this.f3279t, this.f3280u, this.f3281v, this.f3282w, (T0.m) this.f3272E, z6, z10, this.f3285z, this.f3269A, (C0761q) obj, iZ3, iZ4);
                break;
        }
        return m7.q.f19267a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0342h(String str, InterfaceC2782k interfaceC2782k, g0.o oVar, boolean z6, boolean z10, N0.L l, C0329a0 c0329a0, Z z11, boolean z12, int i7, int i10, C0074n c0074n, InterfaceC2782k interfaceC2782k2, C.k kVar, AbstractC1887p abstractC1887p, InterfaceC2786o interfaceC2786o, int i11, int i12) {
        super(2);
        this.f3271D = str;
        this.f3274n = interfaceC2782k;
        this.o = oVar;
        this.f3280u = z6;
        this.f3284y = z10;
        this.f3275p = l;
        this.f3272E = c0329a0;
        this.f3283x = z11;
        this.f3285z = z12;
        this.f3281v = i7;
        this.f3282w = i10;
        this.f3276q = c0074n;
        this.f3277r = interfaceC2782k2;
        this.f3278s = kVar;
        this.f3279t = abstractC1887p;
        this.f3269A = interfaceC2786o;
        this.B = i11;
        this.f3270C = i12;
    }
}
