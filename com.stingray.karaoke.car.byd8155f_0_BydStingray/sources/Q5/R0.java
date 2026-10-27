package Q5;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class R0 implements InterfaceC2785n {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6518m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6519n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f6520p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f6521q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f6522r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f6523s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f6524t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Object f6525u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ Object f6526v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ Object f6527w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ m7.c f6528x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ m7.c f6529y;

    public /* synthetic */ R0(g0.o oVar, String str, String str2, String str3, boolean z6, boolean z10, boolean z11, boolean z12, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, boolean z13, int i7, int i10) {
        this.f6518m = oVar;
        this.f6525u = str;
        this.f6526v = str2;
        this.f6527w = str3;
        this.f6519n = z6;
        this.o = z10;
        this.f6520p = z11;
        this.f6521q = z12;
        this.f6528x = interfaceC2772a;
        this.f6529y = interfaceC2772a2;
        this.f6522r = z13;
        this.f6523s = i7;
        this.f6524t = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f6523s | 1);
                int iZ2 = C0735d.Z(this.f6524t);
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.f6529y;
                boolean z6 = this.f6522r;
                w0.c.w(this.f6518m, (String) this.f6525u, (String) this.f6526v, (String) this.f6527w, this.f6519n, this.o, this.f6520p, this.f6521q, (InterfaceC2772a) this.f6528x, interfaceC2772a, z6, (C0761q) obj, iZ, iZ2);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ3 = C0735d.Z(this.f6523s | 1);
                int iZ4 = C0735d.Z(this.f6524t);
                boolean z10 = this.f6522r;
                InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f6529y;
                w0.c.f(this.f6518m, this.f6519n, this.o, (InterfaceC2782k) this.f6525u, this.f6520p, (InterfaceC2782k) this.f6526v, this.f6521q, (Boolean) this.f6527w, (InterfaceC2782k) this.f6528x, z10, interfaceC2782k, (C0761q) obj, iZ3, iZ4);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ R0(g0.o oVar, boolean z6, boolean z10, InterfaceC2782k interfaceC2782k, boolean z11, InterfaceC2782k interfaceC2782k2, boolean z12, Boolean bool, InterfaceC2782k interfaceC2782k3, boolean z13, InterfaceC2782k interfaceC2782k4, int i7, int i10) {
        this.f6518m = oVar;
        this.f6519n = z6;
        this.o = z10;
        this.f6525u = interfaceC2782k;
        this.f6520p = z11;
        this.f6526v = interfaceC2782k2;
        this.f6521q = z12;
        this.f6527w = bool;
        this.f6528x = interfaceC2782k3;
        this.f6522r = z13;
        this.f6529y = interfaceC2782k4;
        this.f6523s = i7;
        this.f6524t = i10;
    }
}
