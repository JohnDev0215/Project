package defpackage;

import defpackage.zi3;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import org.chromium.base.c;

/* JADX INFO: compiled from: UnownedUserDataKey.java */
/* JADX INFO: loaded from: classes.dex */
public final class aj3<T extends zi3> {
    public final Class<T> a;
    public final Set<c> b = Collections.newSetFromMap(new WeakHashMap());

    public aj3(Class<T> cls) {
        this.a = cls;
    }

    public final void a(c cVar, T t) {
        Objects.requireNonNull(t);
        cVar.a.getClass();
        cVar.b.getClass();
        if (cVar.d.containsKey(this) && !t.equals(cVar.a(this))) {
            c(cVar);
        }
        cVar.d.put(this, new WeakReference<>(t));
        if (e(cVar) != null) {
            return;
        }
        this.b.add(cVar);
    }

    public final void b(T t) {
        for (c cVar : new ArrayList(this.b)) {
            if (t.equals(cVar.a(this))) {
                d(cVar);
            }
        }
    }

    public final void c(c cVar) {
        for (c cVar2 : new ArrayList(this.b)) {
            if (cVar.equals(cVar2)) {
                d(cVar2);
            }
        }
    }

    public final void d(c cVar) {
        zi3 zi3Var;
        cVar.a.getClass();
        cVar.b.getClass();
        WeakReference<? extends zi3> weakReferenceRemove = cVar.d.remove(this);
        if (weakReferenceRemove != null && (zi3Var = weakReferenceRemove.get()) != null) {
            cVar.c.post(new y70(cVar, 2, zi3Var));
        }
        this.b.remove(cVar);
    }

    public final T e(c cVar) {
        Iterator<c> it = this.b.iterator();
        while (it.hasNext()) {
            if (cVar.equals(it.next())) {
                return (T) cVar.a(this);
            }
        }
        return null;
    }
}
