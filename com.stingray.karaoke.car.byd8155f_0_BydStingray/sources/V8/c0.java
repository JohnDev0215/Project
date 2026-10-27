package V8;

import U8.EnumC0810a;
import h8.AbstractC1387d;
import n7.AbstractC1998m;
import o7.C2050b;
import q7.C2195i;

/* JADX INFO: loaded from: classes.dex */
public final class c0 implements W {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f9610a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f9611b;

    public c0(long j3, long j10) {
        this.f9610a = j3;
        this.f9611b = j10;
        if (j3 < 0) {
            throw new IllegalArgumentException(("stopTimeout(" + j3 + " ms) cannot be negative").toString());
        }
        if (j10 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("replayExpiration(" + j10 + " ms) cannot be negative").toString());
    }

    @Override // V8.W
    public final InterfaceC0817f a(W8.E e10) {
        a0 a0Var = new a0(this, null);
        int i7 = AbstractC0836z.f9667a;
        C0832v c0832v = new C0832v(new W8.n(a0Var, e10, C2195i.l, -2, EnumC0810a.l), 1, new b0(2, null));
        return c0832v instanceof d0 ? c0832v : new C0816e(c0832v, AbstractC0828q.f9645a);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof c0) {
            c0 c0Var = (c0) obj;
            if (this.f9610a == c0Var.f9610a && this.f9611b == c0Var.f9611b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f9611b) + (Long.hashCode(this.f9610a) * 31);
    }

    public final String toString() {
        C2050b c2050b = new C2050b(2);
        long j3 = this.f9610a;
        if (j3 > 0) {
            c2050b.add("stopTimeout=" + j3 + "ms");
        }
        long j10 = this.f9611b;
        if (j10 < Long.MAX_VALUE) {
            c2050b.add("replayExpiration=" + j10 + "ms");
        }
        return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("SharingStarted.WhileSubscribed("), AbstractC1998m.f0(AbstractC1387d.a(c2050b), null, null, null, null, 63), ')');
    }
}
