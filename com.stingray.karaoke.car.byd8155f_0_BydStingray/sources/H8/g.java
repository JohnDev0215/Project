package H8;

import A7.m;
import F8.K;
import Q7.InterfaceC0654g;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import n7.u;

/* JADX INFO: loaded from: classes.dex */
public final class g implements K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f2864a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String[] f2865b;
    public final String c;

    public g(h hVar, String... strArr) {
        m.f(hVar, "kind");
        m.f(strArr, "formatParams");
        this.f2864a = hVar;
        this.f2865b = strArr;
        Object[] objArrCopyOf = Arrays.copyOf(strArr, strArr.length);
        this.c = String.format("[Error type: %s]", Arrays.copyOf(new Object[]{String.format(hVar.l, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length))}, 1));
    }

    @Override // F8.K
    public final N7.i l() {
        N7.e eVar = N7.e.f4791f;
        return N7.e.f4791f;
    }

    @Override // F8.K
    public final boolean m() {
        return false;
    }

    @Override // F8.K
    public final InterfaceC0654g n() {
        i.f2895a.getClass();
        return i.c;
    }

    @Override // F8.K
    public final Collection o() {
        return u.l;
    }

    @Override // F8.K
    public final List p() {
        return u.l;
    }

    public final String toString() {
        return this.c;
    }
}
