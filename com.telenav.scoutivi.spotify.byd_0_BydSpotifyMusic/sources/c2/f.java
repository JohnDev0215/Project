package c2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: TranscoderRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<a<?, ?>> f3581a = new ArrayList();

    /* JADX INFO: compiled from: TranscoderRegistry.java */
    private static final class a<Z, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<Z> f3582a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Class<R> f3583b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final e<Z, R> f3584c;

        a(Class<Z> cls, Class<R> cls2, e<Z, R> eVar) {
            this.f3582a = cls;
            this.f3583b = cls2;
            this.f3584c = eVar;
        }

        public boolean a(Class<?> cls, Class<?> cls2) {
            return this.f3582a.isAssignableFrom(cls) && cls2.isAssignableFrom(this.f3583b);
        }
    }

    public synchronized <Z, R> e<Z, R> a(Class<Z> cls, Class<R> cls2) {
        if (cls2.isAssignableFrom(cls)) {
            return g.b();
        }
        for (a<?, ?> aVar : this.f3581a) {
            if (aVar.a(cls, cls2)) {
                return (e<Z, R>) aVar.f3584c;
            }
        }
        throw new IllegalArgumentException("No transcoder registered to transcode from " + cls + " to " + cls2);
    }

    public synchronized <Z, R> List<Class<R>> b(Class<Z> cls, Class<R> cls2) {
        ArrayList arrayList = new ArrayList();
        if (cls2.isAssignableFrom(cls)) {
            arrayList.add(cls2);
            return arrayList;
        }
        Iterator<a<?, ?>> it = this.f3581a.iterator();
        while (it.hasNext()) {
            if (it.next().a(cls, cls2)) {
                arrayList.add(cls2);
            }
        }
        return arrayList;
    }

    public synchronized <Z, R> void c(Class<Z> cls, Class<R> cls2, e<Z, R> eVar) {
        this.f3581a.add(new a<>(cls, cls2, eVar));
    }
}
