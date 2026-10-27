package Q5;

import T.C0761q;
import java.util.ArrayList;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.t0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0621t0 implements InterfaceC2785n {
    public final /* synthetic */ Integer l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ArrayList f7067m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f7068n;
    public final /* synthetic */ D.L o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f7069p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ b6.g f7070q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f7071r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Integer f7072s;

    public C0621t0(Integer num, ArrayList arrayList, InterfaceC2772a interfaceC2772a, D.L l, InterfaceC2785n interfaceC2785n, b6.g gVar, boolean z6, Integer num2) {
        this.l = num;
        this.f7067m = arrayList;
        this.f7068n = interfaceC2772a;
        this.o = l;
        this.f7069p = interfaceC2785n;
        this.f7070q = gVar;
        this.f7071r = z6;
        this.f7072s = num2;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            com.bumptech.glide.c.A(g0.l.f14684a, this.l, this.f7067m, this.f7068n, this.o, this.f7069p, this.f7070q, this.f7071r, this.f7072s, c0761q, 6, 0);
        }
        return m7.q.f19267a;
    }
}
