package h2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class h implements Iterable {
    public final Object l = new Object();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final HashMap f15212m = new HashMap();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Set f15213n = Collections.emptySet();
    public List o = Collections.emptyList();

    public final int a(q2.k kVar) {
        int iIntValue;
        synchronized (this.l) {
            try {
                iIntValue = this.f15212m.containsKey(kVar) ? ((Integer) this.f15212m.get(kVar)).intValue() : 0;
            } catch (Throwable th) {
                throw th;
            }
        }
        return iIntValue;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        Iterator it;
        synchronized (this.l) {
            it = this.o.iterator();
        }
        return it;
    }
}
