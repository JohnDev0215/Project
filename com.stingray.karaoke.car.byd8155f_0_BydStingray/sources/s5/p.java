package S5;

import T.C0735d;
import T.C0761q;
import g1.AbstractC1274a;
import n6.C1966G;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p implements InterfaceC2785n {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final /* synthetic */ int f8022A;
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f8023m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f8024n;
    public final /* synthetic */ String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ String f8025p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C1966G f8026q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f8027r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ String f8028s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8029t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8030u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ boolean f8031v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8032w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f8033x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f8034y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final /* synthetic */ int f8035z;

    public /* synthetic */ p(g0.o oVar, String str, String str2, String str3, C1966G c1966g, boolean z6, String str4, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, boolean z10, InterfaceC2772a interfaceC2772a3, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, int i7, int i10, int i11) {
        this.l = i11;
        this.f8023m = oVar;
        this.f8024n = str;
        this.o = str2;
        this.f8025p = str3;
        this.f8026q = c1966g;
        this.f8027r = z6;
        this.f8028s = str4;
        this.f8029t = interfaceC2772a;
        this.f8030u = interfaceC2772a2;
        this.f8031v = z10;
        this.f8032w = interfaceC2772a3;
        this.f8033x = interfaceC2782k;
        this.f8034y = interfaceC2782k2;
        this.f8035z = i7;
        this.f8022A = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f8035z | 1);
                int iZ2 = C0735d.Z(this.f8022A);
                String str = this.f8024n;
                String str2 = this.o;
                C1966G c1966g = this.f8026q;
                InterfaceC2782k interfaceC2782k = this.f8033x;
                InterfaceC2782k interfaceC2782k2 = this.f8034y;
                AbstractC2673a.j(this.f8023m, str, str2, this.f8025p, c1966g, this.f8027r, this.f8028s, this.f8029t, this.f8030u, this.f8031v, this.f8032w, interfaceC2782k, interfaceC2782k2, (C0761q) obj, iZ, iZ2);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ3 = C0735d.Z(this.f8035z | 1);
                int iZ4 = C0735d.Z(this.f8022A);
                String str3 = this.f8024n;
                String str4 = this.o;
                C1966G c1966g2 = this.f8026q;
                InterfaceC2782k interfaceC2782k3 = this.f8033x;
                InterfaceC2782k interfaceC2782k4 = this.f8034y;
                AbstractC1274a.o(this.f8023m, str3, str4, this.f8025p, c1966g2, this.f8027r, this.f8028s, this.f8029t, this.f8030u, this.f8031v, this.f8032w, interfaceC2782k3, interfaceC2782k4, (C0761q) obj, iZ3, iZ4);
                break;
        }
        return m7.q.f19267a;
    }
}
