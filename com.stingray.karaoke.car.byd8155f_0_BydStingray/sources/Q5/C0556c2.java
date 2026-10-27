package Q5;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.c2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0556c2 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6730m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Integer f6731n;
    public final /* synthetic */ Integer o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6732p;

    public /* synthetic */ C0556c2(g0.o oVar, Integer num, Integer num2, int i7, int i10) {
        this.l = i10;
        this.f6730m = oVar;
        this.f6731n = num;
        this.o = num2;
        this.f6732p = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = this.l;
        C0761q c0761q = (C0761q) obj;
        ((Integer) obj2).getClass();
        switch (i7) {
            case 0:
                int iZ = C0735d.Z(this.f6732p | 1);
                l9.d.u(this.f6730m, this.f6731n, this.o, c0761q, iZ);
                break;
            default:
                int iZ2 = C0735d.Z(this.f6732p | 1);
                l9.d.v(this.f6730m, this.f6731n, this.o, c0761q, iZ2);
                break;
        }
        return m7.q.f19267a;
    }
}
