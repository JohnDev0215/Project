package o1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import o1.e;

/* JADX INFO: compiled from: DataRewinderRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final e.a<?> f8647b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<Class<?>, e.a<?>> f8648a = new HashMap();

    /* JADX INFO: compiled from: DataRewinderRegistry.java */
    class a implements e.a<Object> {
        a() {
        }

        @Override // o1.e.a
        public Class<Object> a() {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override // o1.e.a
        public e<Object> b(Object obj) {
            return new b(obj);
        }
    }

    /* JADX INFO: compiled from: DataRewinderRegistry.java */
    private static final class b implements e<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Object f8649a;

        b(Object obj) {
            this.f8649a = obj;
        }

        @Override // o1.e
        public Object a() {
            return this.f8649a;
        }

        @Override // o1.e
        public void b() {
        }
    }

    public synchronized <T> e<T> a(T t6) {
        e.a<?> aVar;
        k2.j.d(t6);
        aVar = this.f8648a.get(t6.getClass());
        if (aVar == null) {
            Iterator<e.a<?>> it = this.f8648a.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                e.a<?> next = it.next();
                if (next.a().isAssignableFrom(t6.getClass())) {
                    aVar = next;
                    break;
                }
            }
        }
        if (aVar == null) {
            aVar = f8647b;
        }
        return (e<T>) aVar.b(t6);
    }

    public synchronized void b(e.a<?> aVar) {
        this.f8648a.put(aVar.a(), aVar);
    }
}
