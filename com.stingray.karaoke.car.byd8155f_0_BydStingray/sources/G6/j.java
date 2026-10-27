package G6;

import F.y;
import T.C0735d;
import T.C0761q;
import X5.AbstractC0851c;
import g0.o;
import m7.q;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class j implements InterfaceC2785n {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2704m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f2705n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f2706p;

    public /* synthetic */ j(y yVar, int i7, InterfaceC2772a interfaceC2772a, int i10) {
        this.o = yVar;
        this.f2704m = i7;
        this.f2706p = interfaceC2772a;
        this.f2705n = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        q qVar = q.f19267a;
        Object obj3 = this.o;
        Object obj4 = this.f2706p;
        int i7 = this.f2704m;
        int i10 = this.f2705n;
        int i11 = this.l;
        C0761q c0761q = (C0761q) obj;
        ((Integer) obj2).getClass();
        switch (i11) {
            case 0:
                int iZ = C0735d.Z(i10 | 1);
                b0.b bVar = AbstractC0851c.f10229a;
                w0.c.C((o) obj3, (String) obj4, i7, c0761q, iZ);
                break;
            default:
                y yVar = (y) obj3;
                com.bumptech.glide.c.l(yVar, i7, (InterfaceC2772a) obj4, c0761q, C0735d.Z(i10 | 1));
                break;
        }
        return qVar;
    }

    public /* synthetic */ j(o oVar, String str, int i7, int i10) {
        b0.b bVar = AbstractC0851c.f10229a;
        this.o = oVar;
        this.f2706p = str;
        this.f2704m = i7;
        this.f2705n = i10;
    }
}
