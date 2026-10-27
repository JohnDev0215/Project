package com.bumptech.glide.load.i;

import com.bumptech.glide.load.i.e;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: DataRewinderRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final e.a<?> f2925a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<Class<?>, e.a<?>> f2926b = new HashMap();

    /* JADX INFO: compiled from: DataRewinderRegistry.java */
    class a implements e.a<Object> {
        a() {
        }

        @Override // com.bumptech.glide.load.i.e.a
        public Class<Object> a() {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override // com.bumptech.glide.load.i.e.a
        public e<Object> b(Object obj) {
            return new b(obj);
        }
    }

    /* JADX INFO: compiled from: DataRewinderRegistry.java */
    private static final class b implements e<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Object f2927a;

        b(Object obj) {
            this.f2927a = obj;
        }

        @Override // com.bumptech.glide.load.i.e
        public Object a() {
            return this.f2927a;
        }

        @Override // com.bumptech.glide.load.i.e
        public void b() {
        }
    }

    public synchronized <T> e<T> a(T t) {
        e.a<?> aVar;
        com.bumptech.glide.m.j.d(t);
        aVar = this.f2926b.get(t.getClass());
        if (aVar == null) {
            Iterator<e.a<?>> it = this.f2926b.values().iterator();
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
            aVar = f2925a;
        }
        return (e<T>) aVar.b(t);
    }

    public synchronized void b(e.a<?> aVar) {
        this.f2926b.put(aVar.a(), aVar);
    }
}
