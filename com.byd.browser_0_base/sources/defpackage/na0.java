package defpackage;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: DiscardableReferencePool.java */
/* JADX INFO: loaded from: classes.dex */
public final class na0 {
    public final Set<a<?>> a = Collections.newSetFromMap(new WeakHashMap());

    /* JADX INFO: compiled from: DiscardableReferencePool.java */
    public static class a<T> {
        public T a;

        public a() {
            throw null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public a(Object obj) {
            this.a = obj;
        }
    }

    public final <T> a<T> a(T t) {
        a<T> aVar = new a<>(t);
        this.a.add(aVar);
        return aVar;
    }
}
