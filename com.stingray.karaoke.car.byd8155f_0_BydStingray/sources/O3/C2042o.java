package o3;

import V8.f0;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import n3.C1933H;
import n3.C1945j;
import n3.N;
import n3.O;
import n7.AbstractC1998m;
import n7.AbstractC1999n;

/* JADX INFO: renamed from: o3.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
@N("dialog")
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lo3/o;", "Ln3/O;", "Lo3/n;", "<init>", "()V", "navigation-compose_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class C2042o extends O {
    @Override // n3.O
    public final n3.y a() {
        b0.b bVar = AbstractC2032e.f20459a;
        return new C2041n(this);
    }

    @Override // n3.O
    public final void d(List list, C1933H c1933h) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            b().f((C1945j) it.next());
        }
    }

    @Override // n3.O
    public final void e(C1945j c1945j, boolean z6) {
        b().e(c1945j, z6);
        int iC0 = AbstractC1998m.c0((Iterable) ((f0) b().f19881f.l).getValue(), c1945j);
        int i7 = 0;
        for (Object obj : (Iterable) ((f0) b().f19881f.l).getValue()) {
            int i10 = i7 + 1;
            if (i7 < 0) {
                AbstractC1999n.I();
                throw null;
            }
            C1945j c1945j2 = (C1945j) obj;
            if (i7 > iC0) {
                b().b(c1945j2);
            }
            i7 = i10;
        }
    }
}
