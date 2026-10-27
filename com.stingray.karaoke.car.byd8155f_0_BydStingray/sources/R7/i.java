package R7;

import P8.q;
import Q7.F;
import g8.C1312b;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.t;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public final class i implements h {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f7536m;

    public /* synthetic */ i(int i7, List list) {
        this.l = i7;
        this.f7536m = list;
    }

    @Override // R7.h
    public final b e(C2058c c2058c) {
        switch (this.l) {
            case 0:
                return P8.n.v(this, c2058c);
            case 1:
                A7.m.f(c2058c, "fqName");
                return (b) P8.m.b0(P8.m.h0(AbstractC1998m.S((List) this.f7536m), new F(c2058c, 1)));
            default:
                A7.m.f(c2058c, "fqName");
                if (c2058c.equals((C2058c) this.f7536m)) {
                    return C1312b.f14998a;
                }
                return null;
        }
    }

    @Override // R7.h
    public final boolean isEmpty() {
        switch (this.l) {
            case 0:
                return ((List) this.f7536m).isEmpty();
            case 1:
                List list = (List) this.f7536m;
                if (list != null && list.isEmpty()) {
                    return true;
                }
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (!((h) it.next()).isEmpty()) {
                        return false;
                    }
                }
                return true;
            default:
                return false;
        }
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        switch (this.l) {
            case 0:
                return ((List) this.f7536m).iterator();
            case 1:
                return new P8.f(new P8.h(AbstractC1998m.S((List) this.f7536m), k.f7539m, q.f5483t));
            default:
                return t.l;
        }
    }

    @Override // R7.h
    public final boolean k(C2058c c2058c) {
        switch (this.l) {
            case 1:
                A7.m.f(c2058c, "fqName");
                Iterator it = ((Iterable) AbstractC1998m.S((List) this.f7536m).f5481b).iterator();
                while (it.hasNext()) {
                    if (((h) it.next()).k(c2058c)) {
                        break;
                    }
                }
                break;
        }
        return P8.n.A(this, c2058c);
    }

    public String toString() {
        switch (this.l) {
            case 0:
                return ((List) this.f7536m).toString();
            default:
                return super.toString();
        }
    }

    public i(h[] hVarArr) {
        this.l = 1;
        this.f7536m = AbstractC1996k.b0(hVarArr);
    }

    public i(C2058c c2058c) {
        this.l = 2;
        A7.m.f(c2058c, "fqNameToMatch");
        this.f7536m = c2058c;
    }
}
