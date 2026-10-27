package k5;

import k5.e;
import kotlin.jvm.internal.l;
import kotlin.jvm.internal.m;
import r5.p;

/* JADX INFO: compiled from: CoroutineContext.kt */
/* JADX INFO: loaded from: classes.dex */
public interface g {

    /* JADX INFO: compiled from: CoroutineContext.kt */
    public static final class a {

        /* JADX INFO: renamed from: k5.g$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CoroutineContext.kt */
        static final class C0101a extends m implements p<g, b, g> {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public static final C0101a f7275e = new C0101a();

            C0101a() {
                super(2);
            }

            @Override // r5.p
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final g invoke(g acc, b element) {
                k5.c cVar;
                l.e(acc, "acc");
                l.e(element, "element");
                g gVarMinusKey = acc.minusKey(element.getKey());
                h hVar = h.f7276e;
                if (gVarMinusKey == hVar) {
                    return element;
                }
                e.b bVar = e.f7273b;
                e eVar = (e) gVarMinusKey.get(bVar);
                if (eVar == null) {
                    cVar = new k5.c(gVarMinusKey, element);
                } else {
                    g gVarMinusKey2 = gVarMinusKey.minusKey(bVar);
                    if (gVarMinusKey2 == hVar) {
                        return new k5.c(element, eVar);
                    }
                    cVar = new k5.c(new k5.c(gVarMinusKey2, element), eVar);
                }
                return cVar;
            }
        }

        public static g a(g gVar, g context) {
            l.e(context, "context");
            return context == h.f7276e ? gVar : (g) context.fold(gVar, C0101a.f7275e);
        }
    }

    /* JADX INFO: compiled from: CoroutineContext.kt */
    public interface b extends g {

        /* JADX INFO: compiled from: CoroutineContext.kt */
        public static final class a {
            public static <R> R a(b bVar, R r7, p<? super R, ? super b, ? extends R> operation) {
                l.e(operation, "operation");
                return operation.invoke(r7, bVar);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static <E extends b> E b(b bVar, c<E> key) {
                l.e(key, "key");
                if (l.a(bVar.getKey(), key)) {
                    return bVar;
                }
                return null;
            }

            public static g c(b bVar, c<?> key) {
                l.e(key, "key");
                return l.a(bVar.getKey(), key) ? h.f7276e : bVar;
            }

            public static g d(b bVar, g context) {
                l.e(context, "context");
                return a.a(bVar, context);
            }
        }

        @Override // k5.g
        <E extends b> E get(c<E> cVar);

        c<?> getKey();
    }

    /* JADX INFO: compiled from: CoroutineContext.kt */
    public interface c<E extends b> {
    }

    <R> R fold(R r7, p<? super R, ? super b, ? extends R> pVar);

    <E extends b> E get(c<E> cVar);

    g minusKey(c<?> cVar);

    g plus(g gVar);
}
