package kotlinx.coroutines;

import java.util.Objects;
import k5.e;
import k5.g;

/* JADX INFO: compiled from: CoroutineDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class e0 extends k5.a implements k5.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f7602e = new a(null);

    /* JADX INFO: compiled from: CoroutineDispatcher.kt */
    public static final class a extends k5.b<k5.e, e0> {

        /* JADX INFO: renamed from: kotlinx.coroutines.e0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CoroutineDispatcher.kt */
        static final class C0106a extends kotlin.jvm.internal.m implements r5.l<g.b, e0> {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public static final C0106a f7603e = new C0106a();

            C0106a() {
                super(1);
            }

            @Override // r5.l
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final e0 invoke(g.b bVar) {
                if (!(bVar instanceof e0)) {
                    bVar = null;
                }
                return (e0) bVar;
            }
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        private a() {
            super(k5.e.f7273b, C0106a.f7603e);
        }
    }

    public e0() {
        super(k5.e.f7273b);
    }

    @Override // k5.e
    public final <T> k5.d<T> I(k5.d<? super T> dVar) {
        return new kotlinx.coroutines.internal.h(this, dVar);
    }

    public abstract void a0(k5.g gVar, Runnable runnable);

    public void b0(k5.g gVar, Runnable runnable) {
        a0(gVar, runnable);
    }

    public boolean c0(k5.g gVar) {
        return true;
    }

    @Override // k5.a, k5.g.b, k5.g
    public <E extends g.b> E get(g.c<E> cVar) {
        return (E) e.a.a(this, cVar);
    }

    @Override // k5.a, k5.g
    public k5.g minusKey(g.c<?> cVar) {
        return e.a.b(this, cVar);
    }

    public String toString() {
        return m0.a(this) + '@' + m0.b(this);
    }

    @Override // k5.e
    public void z(k5.d<?> dVar) {
        Objects.requireNonNull(dVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        k<?> kVarN = ((kotlinx.coroutines.internal.h) dVar).n();
        if (kVarN != null) {
            kVarN.s();
        }
    }
}
