package androidx.databinding;

import defpackage.db;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MergedDataBinderMapper extends db {
    public Set<Class<? extends db>> a = new HashSet();
    public List<db> b = new CopyOnWriteArrayList();
    public List<String> c = new CopyOnWriteArrayList();

    public void b(db dbVar) {
        if (this.a.add((Class<? extends db>) dbVar.getClass())) {
            this.b.add(dbVar);
            Iterator<db> it = dbVar.a().iterator();
            while (it.hasNext()) {
                b(it.next());
            }
        }
    }
}
