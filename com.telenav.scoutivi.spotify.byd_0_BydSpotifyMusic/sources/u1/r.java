package u1;

import com.bumptech.glide.i;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import u1.n;

/* JADX INFO: compiled from: MultiModelLoaderFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final c f9629e = new c();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final n<Object, Object> f9630f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<b<?, ?>> f9631a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f9632b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Set<b<?, ?>> f9633c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final h0.e<List<Throwable>> f9634d;

    /* JADX INFO: compiled from: MultiModelLoaderFactory.java */
    private static class a implements n<Object, Object> {
        a() {
        }

        @Override // u1.n
        public boolean a(Object obj) {
            return false;
        }

        @Override // u1.n
        public n.a<Object> b(Object obj, int i7, int i8, n1.i iVar) {
            return null;
        }
    }

    /* JADX INFO: compiled from: MultiModelLoaderFactory.java */
    private static class b<Model, Data> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<Model> f9635a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Class<Data> f9636b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final o<? extends Model, ? extends Data> f9637c;

        public b(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
            this.f9635a = cls;
            this.f9636b = cls2;
            this.f9637c = oVar;
        }

        public boolean a(Class<?> cls) {
            return this.f9635a.isAssignableFrom(cls);
        }

        public boolean b(Class<?> cls, Class<?> cls2) {
            return a(cls) && this.f9636b.isAssignableFrom(cls2);
        }
    }

    /* JADX INFO: compiled from: MultiModelLoaderFactory.java */
    static class c {
        c() {
        }

        public <Model, Data> q<Model, Data> a(List<n<Model, Data>> list, h0.e<List<Throwable>> eVar) {
            return new q<>(list, eVar);
        }
    }

    public r(h0.e<List<Throwable>> eVar) {
        this(eVar, f9629e);
    }

    private <Model, Data> void a(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar, boolean z6) {
        b<?, ?> bVar = new b<>(cls, cls2, oVar);
        List<b<?, ?>> list = this.f9631a;
        list.add(z6 ? list.size() : 0, bVar);
    }

    private <Model, Data> n<Model, Data> e(b<?, ?> bVar) {
        return (n) k2.j.d(bVar.f9637c.c(this));
    }

    private static <Model, Data> n<Model, Data> f() {
        return (n<Model, Data>) f9630f;
    }

    private <Model, Data> o<Model, Data> h(b<?, ?> bVar) {
        return (o<Model, Data>) bVar.f9637c;
    }

    synchronized <Model, Data> void b(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        a(cls, cls2, oVar, true);
    }

    synchronized <Model> List<n<Model, ?>> c(Class<Model> cls) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (b<?, ?> bVar : this.f9631a) {
                if (!this.f9633c.contains(bVar) && bVar.a(cls)) {
                    this.f9633c.add(bVar);
                    arrayList.add(e(bVar));
                    this.f9633c.remove(bVar);
                }
            }
        } catch (Throwable th) {
            this.f9633c.clear();
            throw th;
        }
        return arrayList;
    }

    public synchronized <Model, Data> n<Model, Data> d(Class<Model> cls, Class<Data> cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z6 = false;
            for (b<?, ?> bVar : this.f9631a) {
                if (this.f9633c.contains(bVar)) {
                    z6 = true;
                } else if (bVar.b(cls, cls2)) {
                    this.f9633c.add(bVar);
                    arrayList.add(e(bVar));
                    this.f9633c.remove(bVar);
                }
            }
            if (arrayList.size() > 1) {
                return this.f9632b.a(arrayList, this.f9634d);
            }
            if (arrayList.size() == 1) {
                return (n) arrayList.get(0);
            }
            if (!z6) {
                throw new i.c(cls, cls2);
            }
            return f();
        } catch (Throwable th) {
            this.f9633c.clear();
            throw th;
        }
    }

    synchronized List<Class<?>> g(Class<?> cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (b<?, ?> bVar : this.f9631a) {
            if (!arrayList.contains(bVar.f9636b) && bVar.a(cls)) {
                arrayList.add(bVar.f9636b);
            }
        }
        return arrayList;
    }

    synchronized <Model, Data> List<o<? extends Model, ? extends Data>> i(Class<Model> cls, Class<Data> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<b<?, ?>> it = this.f9631a.iterator();
        while (it.hasNext()) {
            b<?, ?> next = it.next();
            if (next.b(cls, cls2)) {
                it.remove();
                arrayList.add(h(next));
            }
        }
        return arrayList;
    }

    synchronized <Model, Data> List<o<? extends Model, ? extends Data>> j(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        List<o<? extends Model, ? extends Data>> listI;
        listI = i(cls, cls2);
        b(cls, cls2, oVar);
        return listI;
    }

    r(h0.e<List<Throwable>> eVar, c cVar) {
        this.f9631a = new ArrayList();
        this.f9633c = new HashSet();
        this.f9634d = eVar;
        this.f9632b = cVar;
    }
}
