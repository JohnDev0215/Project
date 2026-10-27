package Q5;

import T.C0735d;
import T.C0761q;
import n6.C1972e;
import o6.C2048a;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Y2 implements InterfaceC2785n {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6661m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6662n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6663p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f6664q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f6665r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f6666s;

    public /* synthetic */ Y2(g0.o oVar, String str, String str2, boolean z6, boolean z10, InterfaceC2772a interfaceC2772a, int i7) {
        this.f6661m = oVar;
        this.f6664q = str;
        this.f6665r = str2;
        this.f6662n = z6;
        this.o = z10;
        this.f6666s = interfaceC2772a;
        this.f6663p = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f6663p | 1);
                boolean z6 = this.o;
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.f6666s;
                com.bumptech.glide.c.b(this.f6661m, (String) this.f6664q, (String) this.f6665r, this.f6662n, z6, interfaceC2772a, (C0761q) obj, iZ);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f6663p | 1);
                C2048a c2048a = (C2048a) this.f6666s;
                boolean z10 = this.f6662n;
                boolean z11 = this.o;
                h6.m.c(this.f6661m, (n6.J) this.f6664q, (C1972e) this.f6665r, c2048a, z10, z11, (C0761q) obj, iZ2);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ Y2(g0.o oVar, n6.J j3, C1972e c1972e, C2048a c2048a, boolean z6, boolean z10, int i7) {
        this.f6661m = oVar;
        this.f6664q = j3;
        this.f6665r = c1972e;
        this.f6666s = c2048a;
        this.f6662n = z6;
        this.o = z10;
        this.f6663p = i7;
    }
}
