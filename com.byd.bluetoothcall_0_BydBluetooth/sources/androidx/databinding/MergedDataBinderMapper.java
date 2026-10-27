package androidx.databinding;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MergedDataBinderMapper extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Set<Class<? extends d>> f1306a = new HashSet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<d> f1307b = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<String> f1308c = new CopyOnWriteArrayList();

    public void b(d dVar) {
        if (this.f1306a.add((Class<? extends d>) dVar.getClass())) {
            this.f1307b.add(dVar);
            Iterator<d> it = dVar.a().iterator();
            while (it.hasNext()) {
                b(it.next());
            }
        }
    }
}
