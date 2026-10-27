package f2;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: EncoderRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<C0074a<?>> f6336a = new ArrayList();

    /* JADX INFO: renamed from: f2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: EncoderRegistry.java */
    private static final class C0074a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<T> f6337a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final n1.d<T> f6338b;

        C0074a(Class<T> cls, n1.d<T> dVar) {
            this.f6337a = cls;
            this.f6338b = dVar;
        }

        boolean a(Class<?> cls) {
            return this.f6337a.isAssignableFrom(cls);
        }
    }

    public synchronized <T> void a(Class<T> cls, n1.d<T> dVar) {
        this.f6336a.add(new C0074a<>(cls, dVar));
    }

    public synchronized <T> n1.d<T> b(Class<T> cls) {
        for (C0074a<?> c0074a : this.f6336a) {
            if (c0074a.a(cls)) {
                return (n1.d<T>) c0074a.f6338b;
            }
        }
        return null;
    }
}
