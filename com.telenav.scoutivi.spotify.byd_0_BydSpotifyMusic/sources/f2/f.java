package f2;

import java.util.ArrayList;
import java.util.List;
import n1.l;

/* JADX INFO: compiled from: ResourceEncoderRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<a<?>> f6350a = new ArrayList();

    /* JADX INFO: compiled from: ResourceEncoderRegistry.java */
    private static final class a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<T> f6351a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final l<T> f6352b;

        a(Class<T> cls, l<T> lVar) {
            this.f6351a = cls;
            this.f6352b = lVar;
        }

        boolean a(Class<?> cls) {
            return this.f6351a.isAssignableFrom(cls);
        }
    }

    public synchronized <Z> void a(Class<Z> cls, l<Z> lVar) {
        this.f6350a.add(new a<>(cls, lVar));
    }

    public synchronized <Z> l<Z> b(Class<Z> cls) {
        int size = this.f6350a.size();
        for (int i7 = 0; i7 < size; i7++) {
            a<?> aVar = this.f6350a.get(i7);
            if (aVar.a(cls)) {
                return (l<Z>) aVar.f6352b;
            }
        }
        return null;
    }
}
