package com.bumptech.glide.load.data;

import com.bumptech.glide.load.data.e;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final e.a<?> f1977a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<Class<?>, e.a<?>> f1978b = new HashMap();

    class a implements e.a<Object> {
        a() {
        }

        @Override // com.bumptech.glide.load.data.e.a
        public Class<Object> a() {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override // com.bumptech.glide.load.data.e.a
        public e<Object> b(Object obj) {
            return new b(obj);
        }
    }

    private static final class b implements e<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Object f1979a;

        b(Object obj) {
            this.f1979a = obj;
        }

        @Override // com.bumptech.glide.load.data.e
        public Object a() {
            return this.f1979a;
        }

        @Override // com.bumptech.glide.load.data.e
        public void b() {
        }
    }

    public synchronized <T> e<T> a(T t) {
        e.a<?> aVar;
        com.bumptech.glide.t.j.d(t);
        aVar = this.f1978b.get(t.getClass());
        if (aVar == null) {
            Iterator<e.a<?>> it = this.f1978b.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                e.a<?> next = it.next();
                if (next.a().isAssignableFrom(t.getClass())) {
                    aVar = next;
                    break;
                }
            }
        }
        if (aVar == null) {
            aVar = f1977a;
        }
        return (e<T>) aVar.b(t);
    }

    public synchronized void b(e.a<?> aVar) {
        this.f1978b.put(aVar.a(), aVar);
    }
}
