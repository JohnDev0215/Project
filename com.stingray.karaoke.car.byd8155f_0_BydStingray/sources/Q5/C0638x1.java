package Q5;

import T.C0735d;
import T.C0761q;
import java.util.List;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.x1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0638x1 implements InterfaceC2785n {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f7130m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ List f7131n;
    public final /* synthetic */ String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ D.L f7132p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f7133q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f7134r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f7135s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f7136t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Object f7137u;

    public /* synthetic */ C0638x1(g0.o oVar, Integer num, List list, String str, D.L l, InterfaceC2785n interfaceC2785n, InterfaceC2782k interfaceC2782k, E.D d, int i7) {
        this.f7130m = oVar;
        this.f7136t = num;
        this.f7131n = list;
        this.o = str;
        this.f7132p = l;
        this.f7133q = interfaceC2785n;
        this.f7134r = interfaceC2782k;
        this.f7137u = d;
        this.f7135s = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f7135s | 1);
                b6.g gVar = (b6.g) this.f7137u;
                D.L l = this.f7132p;
                com.bumptech.glide.d.j(this.f7130m, this.f7131n, this.o, this.f7133q, this.f7134r, (InterfaceC2785n) this.f7136t, gVar, l, (C0761q) obj, iZ);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f7135s | 1);
                D.L l10 = this.f7132p;
                InterfaceC2782k interfaceC2782k = this.f7134r;
                E.D d = (E.D) this.f7137u;
                C7.a.t(this.f7130m, (Integer) this.f7136t, this.f7131n, this.o, l10, this.f7133q, interfaceC2782k, d, (C0761q) obj, iZ2);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ C0638x1(g0.o oVar, List list, String str, InterfaceC2785n interfaceC2785n, InterfaceC2782k interfaceC2782k, InterfaceC2785n interfaceC2785n2, b6.g gVar, D.L l, int i7) {
        this.f7130m = oVar;
        this.f7131n = list;
        this.o = str;
        this.f7133q = interfaceC2785n;
        this.f7134r = interfaceC2782k;
        this.f7136t = interfaceC2785n2;
        this.f7137u = gVar;
        this.f7132p = l;
        this.f7135s = i7;
    }
}
