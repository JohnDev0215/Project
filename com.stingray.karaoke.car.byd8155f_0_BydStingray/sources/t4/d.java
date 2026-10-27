package t4;

import A4.q;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class d implements InterfaceC2435a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e f22460a;

    public d(e eVar) {
        this.f22460a = eVar;
    }

    @Override // t4.InterfaceC2435a
    public final void a(boolean z6) {
        ArrayList arrayList;
        q.a();
        synchronized (this.f22460a) {
            arrayList = new ArrayList((HashSet) this.f22460a.o);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((InterfaceC2435a) it.next()).a(z6);
        }
    }
}
