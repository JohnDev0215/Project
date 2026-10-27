package k5;

import k5.g;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: ContinuationInterceptor.kt */
/* JADX INFO: loaded from: classes.dex */
public interface e extends g.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f7273b = b.f7274a;

    /* JADX INFO: compiled from: ContinuationInterceptor.kt */
    public static final class a {
        public static <E extends g.b> E a(e eVar, g.c<E> key) {
            l.e(key, "key");
            if (!(key instanceof k5.b)) {
                if (e.f7273b == key) {
                    return eVar;
                }
                return null;
            }
            k5.b bVar = (k5.b) key;
            if (!bVar.a(eVar.getKey())) {
                return null;
            }
            E e7 = (E) bVar.b(eVar);
            if (e7 instanceof g.b) {
                return e7;
            }
            return null;
        }

        public static g b(e eVar, g.c<?> key) {
            l.e(key, "key");
            if (!(key instanceof k5.b)) {
                return e.f7273b == key ? h.f7276e : eVar;
            }
            k5.b bVar = (k5.b) key;
            return (!bVar.a(eVar.getKey()) || bVar.b(eVar) == null) ? eVar : h.f7276e;
        }
    }

    /* JADX INFO: compiled from: ContinuationInterceptor.kt */
    public static final class b implements g.c<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ b f7274a = new b();

        private b() {
        }
    }

    <T> d<T> I(d<? super T> dVar);

    void z(d<?> dVar);
}
