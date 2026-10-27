package W8;

import U8.EnumC0810a;
import java.util.Arrays;
import q7.InterfaceC2189c;

/* JADX INFO: renamed from: W8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0842b {
    public AbstractC0844d[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f9908m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9909n;
    public E o;

    public final AbstractC0844d c() {
        AbstractC0844d abstractC0844dD;
        E e10;
        synchronized (this) {
            try {
                AbstractC0844d[] abstractC0844dArrE = this.l;
                if (abstractC0844dArrE == null) {
                    abstractC0844dArrE = e();
                    this.l = abstractC0844dArrE;
                } else if (this.f9908m >= abstractC0844dArrE.length) {
                    Object[] objArrCopyOf = Arrays.copyOf(abstractC0844dArrE, abstractC0844dArrE.length * 2);
                    A7.m.e(objArrCopyOf, "copyOf(...)");
                    this.l = (AbstractC0844d[]) objArrCopyOf;
                    abstractC0844dArrE = (AbstractC0844d[]) objArrCopyOf;
                }
                int i7 = this.f9909n;
                do {
                    abstractC0844dD = abstractC0844dArrE[i7];
                    if (abstractC0844dD == null) {
                        abstractC0844dD = d();
                        abstractC0844dArrE[i7] = abstractC0844dD;
                    }
                    i7++;
                    if (i7 >= abstractC0844dArrE.length) {
                        i7 = 0;
                    }
                } while (!abstractC0844dD.a(this));
                this.f9909n = i7;
                this.f9908m++;
                e10 = this.o;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (e10 != null) {
            e10.x(1);
        }
        return abstractC0844dD;
    }

    public abstract AbstractC0844d d();

    public abstract AbstractC0844d[] e();

    public final void g(AbstractC0844d abstractC0844d) {
        E e10;
        int i7;
        InterfaceC2189c[] interfaceC2189cArrB;
        synchronized (this) {
            try {
                int i10 = this.f9908m - 1;
                this.f9908m = i10;
                e10 = this.o;
                if (i10 == 0) {
                    this.f9909n = 0;
                }
                A7.m.d(abstractC0844d, "null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
                interfaceC2189cArrB = abstractC0844d.b(this);
            } catch (Throwable th) {
                throw th;
            }
        }
        for (InterfaceC2189c interfaceC2189c : interfaceC2189cArrB) {
            if (interfaceC2189c != null) {
                interfaceC2189c.resumeWith(m7.q.f19267a);
            }
        }
        if (e10 != null) {
            e10.x(-1);
        }
    }

    public final E i() {
        E e10;
        synchronized (this) {
            e10 = this.o;
            if (e10 == null) {
                int i7 = this.f9908m;
                e10 = new E(1, Integer.MAX_VALUE, EnumC0810a.f9223m);
                e10.j(Integer.valueOf(i7));
                this.o = e10;
            }
        }
        return e10;
    }
}
