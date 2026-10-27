package androidx.databinding;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MergedDataBinderMapper extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Set<Class<? extends c>> f1261a = new HashSet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<c> f1262b = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<String> f1263c = new CopyOnWriteArrayList();

    public void b(c cVar) {
        if (this.f1261a.add((Class<? extends c>) cVar.getClass())) {
            this.f1262b.add(cVar);
            Iterator<c> it = cVar.a().iterator();
            while (it.hasNext()) {
                b(it.next());
            }
        }
    }
}
