package Q5;

import T.C0735d;
import T.C0761q;
import g1.AbstractC1274a;
import java.io.Serializable;
import java.util.ArrayList;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0565f implements InterfaceC2785n {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6779m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f6780n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6781p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Serializable f6782q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f6783r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f6784s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f6785t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Object f6786u;

    public /* synthetic */ C0565f(g0.o oVar, String str, String str2, T0.o oVar2, InterfaceC2782k interfaceC2782k, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, boolean z6, int i7) {
        this.f6779m = oVar;
        this.f6782q = str;
        this.f6783r = str2;
        this.f6784s = oVar2;
        this.f6785t = interfaceC2782k;
        this.f6780n = interfaceC2772a;
        this.f6786u = interfaceC2772a2;
        this.o = z6;
        this.f6781p = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f6781p | 1);
                ArrayList arrayList = (ArrayList) this.f6782q;
                Integer num = (Integer) this.f6785t;
                D.L l = (D.L) this.f6786u;
                AbstractC1274a.c(this.f6779m, arrayList, this.o, this.f6780n, (InterfaceC2785n) this.f6783r, (b6.g) this.f6784s, num, l, (C0761q) obj, iZ);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f6781p | 1);
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.f6786u;
                boolean z6 = this.o;
                P8.n.g(this.f6779m, (String) this.f6782q, (String) this.f6783r, (T0.o) this.f6784s, (InterfaceC2782k) this.f6785t, this.f6780n, interfaceC2772a, z6, (C0761q) obj, iZ2);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ C0565f(g0.o oVar, ArrayList arrayList, boolean z6, InterfaceC2772a interfaceC2772a, InterfaceC2785n interfaceC2785n, b6.g gVar, Integer num, D.L l, int i7) {
        this.f6779m = oVar;
        this.f6782q = arrayList;
        this.o = z6;
        this.f6780n = interfaceC2772a;
        this.f6783r = interfaceC2785n;
        this.f6784s = gVar;
        this.f6785t = num;
        this.f6786u = l;
        this.f6781p = i7;
    }
}
