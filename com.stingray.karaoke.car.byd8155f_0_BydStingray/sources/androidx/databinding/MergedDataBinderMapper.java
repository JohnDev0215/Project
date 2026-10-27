package androidx.databinding;

import M1.a;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MergedDataBinderMapper extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashSet f11778a = new HashSet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArrayList f11779b = new CopyOnWriteArrayList();

    public MergedDataBinderMapper() {
        new CopyOnWriteArrayList();
    }

    public final void b(a aVar) {
        if (this.f11778a.add(aVar.getClass())) {
            this.f11779b.add(aVar);
            Iterator it = aVar.a().iterator();
            while (it.hasNext()) {
                b((a) it.next());
            }
        }
    }
}
