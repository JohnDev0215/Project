package T6;

import B8.t;
import S6.k;
import java.util.Iterator;
import n3.z;
import w9.u;

/* JADX INFO: loaded from: classes.dex */
public final class b implements S6.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c f8746a;

    public b(c cVar) {
        this.f8746a = cVar;
    }

    @Override // S6.f
    public final void a(t tVar, z zVar) {
        String str = ((u) zVar).f23384g;
        ((k) tVar.f809p).l.append(str);
        c cVar = this.f8746a;
        if (cVar.f8747a.isEmpty()) {
            return;
        }
        tVar.f0();
        str.getClass();
        Iterator it = cVar.f8747a.iterator();
        if (it.hasNext()) {
            it.next().getClass();
            throw new ClassCastException();
        }
    }
}
