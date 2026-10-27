package p8;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: p8.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2135j extends AbstractC2134i implements u {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C2133h f20982m = C2133h.c;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f20983n;

    public final void f(AbstractC2136k abstractC2136k) {
        z zVar;
        if (!this.f20983n) {
            this.f20982m = this.f20982m.clone();
            this.f20983n = true;
        }
        C2133h c2133h = this.f20982m;
        C2133h c2133h2 = abstractC2136k.l;
        c2133h.getClass();
        int i7 = 0;
        while (true) {
            int size = c2133h2.f20980a.f21003m.size();
            zVar = c2133h2.f20980a;
            if (i7 >= size) {
                break;
            }
            c2133h.g((Map.Entry) zVar.f21003m.get(i7));
            i7++;
        }
        Iterator it = zVar.d().iterator();
        while (it.hasNext()) {
            c2133h.g((Map.Entry) it.next());
        }
    }
}
