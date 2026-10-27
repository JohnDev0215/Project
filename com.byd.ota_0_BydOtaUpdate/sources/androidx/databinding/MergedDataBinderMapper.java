package androidx.databinding;

import b.i.d;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MergedDataBinderMapper extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Set<Class<? extends d>> f471a = new HashSet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<d> f472b = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<String> f473c = new CopyOnWriteArrayList();

    public void b(d dVar) {
        if (this.f471a.add((Class<? extends d>) dVar.getClass())) {
            this.f472b.add(dVar);
            Iterator<d> it = dVar.a().iterator();
            while (it.hasNext()) {
                b(it.next());
            }
        }
    }
}
