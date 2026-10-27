package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import k5.g;
import kotlinx.coroutines.CoroutineExceptionHandler;

/* JADX INFO: compiled from: Job.kt */
/* JADX INFO: loaded from: classes.dex */
public interface l1 extends g.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b f7703d = b.f7704a;

    /* JADX INFO: compiled from: Job.kt */
    public static final class a {
        public static /* synthetic */ void b(l1 l1Var, CancellationException cancellationException, int i7, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
            }
            if ((i7 & 1) != 0) {
                cancellationException = null;
            }
            l1Var.cancel(cancellationException);
        }

        public static <R> R c(l1 l1Var, R r7, r5.p<? super R, ? super g.b, ? extends R> pVar) {
            return (R) g.b.a.a(l1Var, r7, pVar);
        }

        public static <E extends g.b> E d(l1 l1Var, g.c<E> cVar) {
            return (E) g.b.a.b(l1Var, cVar);
        }

        public static /* synthetic */ v0 e(l1 l1Var, boolean z6, boolean z7, r5.l lVar, int i7, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: invokeOnCompletion");
            }
            if ((i7 & 1) != 0) {
                z6 = false;
            }
            if ((i7 & 2) != 0) {
                z7 = true;
            }
            return l1Var.invokeOnCompletion(z6, z7, lVar);
        }

        public static k5.g f(l1 l1Var, g.c<?> cVar) {
            return g.b.a.c(l1Var, cVar);
        }

        public static k5.g g(l1 l1Var, k5.g gVar) {
            return g.b.a.d(l1Var, gVar);
        }

        public static l1 h(l1 l1Var, l1 l1Var2) {
            return l1Var2;
        }
    }

    /* JADX INFO: compiled from: Job.kt */
    public static final class b implements g.c<l1> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ b f7704a = new b();

        static {
            CoroutineExceptionHandler.a aVar = CoroutineExceptionHandler.f7512c;
        }

        private b() {
        }
    }

    p attachChild(r rVar);

    void cancel(CancellationException cancellationException);

    CancellationException getCancellationException();

    v0 invokeOnCompletion(boolean z6, boolean z7, r5.l<? super Throwable, g5.r> lVar);

    boolean isActive();

    boolean isCancelled();

    Object join(k5.d<? super g5.r> dVar);

    boolean start();
}
