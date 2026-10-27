package K;

import l0.C1717h;
import l0.C1723n;
import x.C2619H;
import x.C2620I;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class G extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3157m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3158n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f3159p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f3160q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ G(Object obj, Object obj2, Object obj3, Object obj4, int i7) {
        super(0);
        this.f3157m = i7;
        this.f3158n = obj;
        this.o = obj2;
        this.f3159p = obj3;
        this.f3160q = obj4;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        V8.K kI;
        switch (this.f3157m) {
            case 0:
                if (!((C0331b0) this.f3158n).b()) {
                    ((C1723n) this.o).a(C1717h.f18335q);
                }
                int i7 = ((T0.m) this.f3159p).d;
                if (!T0.o.a(i7, 7) && !T0.o.a(i7, 8) && (kI = ((M.f) this.f3160q).i()) != null) {
                    ((V8.S) kI).j(m7.q.f19267a);
                }
                break;
            case 1:
                break;
            case 2:
                ((e1.v) this.f3158n).h((InterfaceC2772a) this.o, (e1.t) this.f3159p, (a1.k) this.f3160q);
                break;
            default:
                C2620I c2620i = (C2620I) this.o;
                Number number = c2620i.l;
                Number number2 = (Number) this.f3158n;
                boolean zEquals = number2.equals(number);
                Number number3 = (Number) this.f3159p;
                if (!zEquals || !number3.equals(c2620i.f23410m)) {
                    c2620i.l = number2;
                    c2620i.f23410m = number3;
                    c2620i.f23412p = new x.p0((C2619H) this.f3160q, c2620i.f23411n, number2, number3, null);
                    c2620i.f23416t.f23436b.setValue(Boolean.TRUE);
                    c2620i.f23413q = false;
                    c2620i.f23414r = true;
                }
                break;
        }
        return m7.q.f19267a;
    }
}
