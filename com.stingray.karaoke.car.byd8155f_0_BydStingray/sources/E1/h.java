package e1;

import F8.K;
import Q7.InterfaceC0654g;
import Q7.Q;
import e8.C1236a;
import l3.P;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class h extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13784m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f13785n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f13786p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f13787q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f13788r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i7) {
        super(0);
        this.f13784m = i7;
        this.f13785n = obj;
        this.o = obj2;
        this.f13786p = obj3;
        this.f13787q = obj4;
        this.f13788r = obj5;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f13784m) {
            case 0:
                ((x) this.f13785n).j((InterfaceC2772a) this.o, (C1187A) this.f13786p, (String) this.f13787q, (a1.k) this.f13788r);
                return m7.q.f19267a;
            default:
                io.sentry.internal.debugmeta.c cVar = (io.sentry.internal.debugmeta.c) ((P) this.f13785n).o;
                InterfaceC0654g interfaceC0654gN = ((K) this.f13787q).n();
                return cVar.I((Q) this.o, C1236a.a(C1236a.a((C1236a) this.f13786p, 0, false, null, interfaceC0654gN != null ? interfaceC0654gN.j() : null, 31), 0, ((W7.p) this.f13788r).d(), null, null, 59));
        }
    }
}
