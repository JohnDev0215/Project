package u1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ModelLoaderRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final r f9616a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f9617b;

    /* JADX INFO: compiled from: ModelLoaderRegistry.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Map<Class<?>, C0163a<?>> f9618a = new HashMap();

        /* JADX INFO: renamed from: u1.p$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ModelLoaderRegistry.java */
        private static class C0163a<Model> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final List<n<Model, ?>> f9619a;

            public C0163a(List<n<Model, ?>> list) {
                this.f9619a = list;
            }
        }

        a() {
        }

        public void a() {
            this.f9618a.clear();
        }

        public <Model> List<n<Model, ?>> b(Class<Model> cls) {
            C0163a<?> c0163a = this.f9618a.get(cls);
            if (c0163a == null) {
                return null;
            }
            return (List<n<Model, ?>>) c0163a.f9619a;
        }

        public <Model> void c(Class<Model> cls, List<n<Model, ?>> list) {
            if (this.f9618a.put(cls, new C0163a<>(list)) == null) {
                return;
            }
            throw new IllegalStateException("Already cached loaders for model: " + cls);
        }
    }

    public p(h0.e<List<Throwable>> eVar) {
        this(new r(eVar));
    }

    private static <A> Class<A> b(A a7) {
        return (Class<A>) a7.getClass();
    }

    private synchronized <A> List<n<A, ?>> e(Class<A> cls) {
        List<n<A, ?>> listB;
        listB = this.f9617b.b(cls);
        if (listB == null) {
            listB = Collections.unmodifiableList(this.f9616a.c(cls));
            this.f9617b.c(cls, listB);
        }
        return listB;
    }

    private <Model, Data> void g(List<o<? extends Model, ? extends Data>> list) {
        Iterator<o<? extends Model, ? extends Data>> it = list.iterator();
        while (it.hasNext()) {
            it.next().b();
        }
    }

    public synchronized <Model, Data> void a(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        this.f9616a.b(cls, cls2, oVar);
        this.f9617b.a();
    }

    public synchronized List<Class<?>> c(Class<?> cls) {
        return this.f9616a.g(cls);
    }

    public <A> List<n<A, ?>> d(A a7) {
        List<n<A, ?>> listE = e(b(a7));
        int size = listE.size();
        List<n<A, ?>> listEmptyList = Collections.emptyList();
        boolean z6 = true;
        for (int i7 = 0; i7 < size; i7++) {
            n<A, ?> nVar = listE.get(i7);
            if (nVar.a(a7)) {
                if (z6) {
                    listEmptyList = new ArrayList<>(size - i7);
                    z6 = false;
                }
                listEmptyList.add(nVar);
            }
        }
        return listEmptyList;
    }

    public synchronized <Model, Data> void f(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        g(this.f9616a.j(cls, cls2, oVar));
        this.f9617b.a();
    }

    private p(r rVar) {
        this.f9617b = new a();
        this.f9616a = rVar;
    }
}
