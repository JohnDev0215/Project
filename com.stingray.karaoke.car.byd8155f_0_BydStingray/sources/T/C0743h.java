package T;

import S8.C0712i;
import h8.AbstractC1386c;
import java.util.ArrayList;
import q7.InterfaceC2189c;
import q7.InterfaceC2192f;
import q7.InterfaceC2193g;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: T.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0743h implements T {
    public final K7.K l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Throwable f8257n;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f8256m = new Object();
    public ArrayList o = new ArrayList();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList f8258p = new ArrayList();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C0737e f8259q = new C0737e(0);

    public C0743h(K7.K k10) {
        this.l = k10;
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2192f F(InterfaceC2193g interfaceC2193g) {
        return h6.q.r(this, interfaceC2193g);
    }

    public final void a(long j3) {
        Object objF;
        synchronized (this.f8256m) {
            try {
                ArrayList arrayList = this.o;
                this.o = this.f8258p;
                this.f8258p = arrayList;
                this.f8259q.set(0);
                int size = arrayList.size();
                for (int i7 = 0; i7 < size; i7++) {
                    C0739f c0739f = (C0739f) arrayList.get(i7);
                    c0739f.getClass();
                    try {
                        objF = c0739f.f8245a.b(Long.valueOf(j3));
                    } catch (Throwable th) {
                        objF = AbstractC1386c.f(th);
                    }
                    c0739f.f8246b.resumeWith(objF);
                }
                arrayList.clear();
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Override // q7.InterfaceC2194h
    public final Object j(Object obj, InterfaceC2785n interfaceC2785n) {
        return interfaceC2785n.invoke(obj, this);
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2194h k(InterfaceC2193g interfaceC2193g) {
        return h6.q.w(this, interfaceC2193g);
    }

    @Override // T.T
    public final Object o(InterfaceC2782k interfaceC2782k, InterfaceC2189c interfaceC2189c) {
        int i7 = 0;
        C0712i c0712i = new C0712i(1, h6.e.k(interfaceC2189c));
        c0712i.s();
        C0739f c0739f = new C0739f(c0712i, interfaceC2782k);
        synchronized (this.f8256m) {
            Throwable th = this.f8257n;
            if (th != null) {
                c0712i.resumeWith(AbstractC1386c.f(th));
            } else {
                boolean zIsEmpty = this.o.isEmpty();
                this.o.add(c0739f);
                if (zIsEmpty) {
                    this.f8259q.set(1);
                }
                c0712i.u(new C0741g(this, i7, c0739f));
                if (zIsEmpty) {
                    try {
                        this.l.a();
                    } catch (Throwable th2) {
                        synchronized (this.f8256m) {
                            try {
                                if (this.f8257n == null) {
                                    this.f8257n = th2;
                                    ArrayList arrayList = this.o;
                                    int size = arrayList.size();
                                    for (int i10 = 0; i10 < size; i10++) {
                                        ((C0739f) arrayList.get(i10)).f8246b.resumeWith(AbstractC1386c.f(th2));
                                    }
                                    this.o.clear();
                                    this.f8259q.set(0);
                                }
                            } catch (Throwable th3) {
                                throw th3;
                            }
                        }
                    }
                }
            }
        }
        Object objQ = c0712i.q();
        EnumC2303a enumC2303a = EnumC2303a.l;
        return objQ;
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2194h v(InterfaceC2194h interfaceC2194h) {
        return h6.q.x(this, interfaceC2194h);
    }
}
