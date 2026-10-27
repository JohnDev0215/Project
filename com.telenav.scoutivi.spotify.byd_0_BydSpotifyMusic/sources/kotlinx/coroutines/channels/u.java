package kotlinx.coroutines.channels;

import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: ConflatedBroadcastChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public final class u<E> implements g<E> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7578e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f7579g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7580h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final kotlinx.coroutines.internal.c0 f7582j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final c<Object> f7583k;
    private volatile Object _state = f7583k;
    private volatile int _updating = 0;
    private volatile Object onCloseHandler = null;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final b f7584l = new b(null);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final a f7581i = new a(null);

    /* JADX INFO: compiled from: ConflatedBroadcastChannel.kt */
    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Throwable f7585a;

        public a(Throwable th) {
            this.f7585a = th;
        }

        public final Throwable a() {
            Throwable th = this.f7585a;
            return th != null ? th : new t("Channel was closed");
        }
    }

    /* JADX INFO: compiled from: ConflatedBroadcastChannel.kt */
    private static final class b {
        private b() {
        }

        public /* synthetic */ b(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: ConflatedBroadcastChannel.kt */
    private static final class c<E> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f7586a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d<E>[] f7587b;

        public c(Object obj, d<E>[] dVarArr) {
            this.f7586a = obj;
            this.f7587b = dVarArr;
        }
    }

    /* JADX INFO: compiled from: ConflatedBroadcastChannel.kt */
    private static final class d<E> extends v<E> implements c0<E> {

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private final u<E> f7588k;

        public d(u<E> uVar) {
            super(null);
            this.f7588k = uVar;
        }

        @Override // kotlinx.coroutines.channels.v, kotlinx.coroutines.channels.a
        protected void H(boolean z6) {
            if (z6) {
                this.f7588k.d(this);
            }
        }

        @Override // kotlinx.coroutines.channels.v, kotlinx.coroutines.channels.c
        public Object q(E e7) {
            return super.q(e7);
        }
    }

    static {
        kotlinx.coroutines.internal.c0 c0Var = new kotlinx.coroutines.internal.c0("UNDEFINED");
        f7582j = c0Var;
        f7583k = new c<>(c0Var, null);
        f7578e = AtomicReferenceFieldUpdater.newUpdater(u.class, Object.class, "_state");
        f7579g = AtomicIntegerFieldUpdater.newUpdater(u.class, "_updating");
        f7580h = AtomicReferenceFieldUpdater.newUpdater(u.class, Object.class, "onCloseHandler");
    }

    private final d<E>[] b(d<E>[] dVarArr, d<E> dVar) {
        if (dVarArr != null) {
            return (d[]) h5.k.l(dVarArr, dVar);
        }
        d<E>[] dVarArr2 = new d[1];
        for (int i7 = 0; i7 < 1; i7++) {
            dVarArr2[i7] = dVar;
        }
        return dVarArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void d(d<E> dVar) {
        Object obj;
        Object obj2;
        d<E>[] dVarArr;
        do {
            obj = this._state;
            if (obj instanceof a) {
                return;
            }
            if (!(obj instanceof c)) {
                throw new IllegalStateException(("Invalid state " + obj).toString());
            }
            obj2 = ((c) obj).f7586a;
            Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E>");
            dVarArr = ((c) obj).f7587b;
            kotlin.jvm.internal.l.c(dVarArr);
        } while (!kotlinx.coroutines.j.a(f7578e, this, obj, new c(obj2, g(dVarArr, dVar))));
    }

    private final void e(Throwable th) {
        kotlinx.coroutines.internal.c0 c0Var;
        Object obj = this.onCloseHandler;
        if (obj == null || obj == (c0Var = kotlinx.coroutines.channels.b.f7546f) || !kotlinx.coroutines.j.a(f7580h, this, obj, c0Var)) {
            return;
        }
        ((r5.l) kotlin.jvm.internal.v.b(obj, 1)).invoke(th);
    }

    private final a f(E e7) {
        Object obj;
        if (!f7579g.compareAndSet(this, 0, 1)) {
            return null;
        }
        do {
            try {
                obj = this._state;
                if (obj instanceof a) {
                    return (a) obj;
                }
                if (!(obj instanceof c)) {
                    throw new IllegalStateException(("Invalid state " + obj).toString());
                }
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E>");
                }
            } finally {
                this._updating = 0;
            }
        } while (!kotlinx.coroutines.j.a(f7578e, this, obj, new c(e7, ((c) obj).f7587b)));
        d<E>[] dVarArr = ((c) obj).f7587b;
        if (dVarArr != null) {
            for (d<E> dVar : dVarArr) {
                dVar.q(e7);
            }
        }
        return null;
    }

    private final d<E>[] g(d<E>[] dVarArr, d<E> dVar) {
        int length = dVarArr.length;
        int iS = h5.l.s(dVarArr, dVar);
        if (length == 1) {
            return null;
        }
        d<E>[] dVarArr2 = new d[length - 1];
        h5.k.f(dVarArr, dVarArr2, 0, 0, iS, 6, null);
        h5.k.f(dVarArr, dVarArr2, iS, iS + 1, 0, 8, null);
        return dVarArr2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.channels.g
    public c0<E> c() {
        Object obj;
        Object obj2;
        d dVar = new d(this);
        do {
            obj = this._state;
            if (obj instanceof a) {
                dVar.close(((a) obj).f7585a);
                return dVar;
            }
            if (!(obj instanceof c)) {
                throw new IllegalStateException(("Invalid state " + obj).toString());
            }
            c cVar = (c) obj;
            Object obj3 = cVar.f7586a;
            if (obj3 != f7582j) {
                dVar.q(obj3);
            }
            obj2 = cVar.f7586a;
            Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E>");
        } while (!kotlinx.coroutines.j.a(f7578e, this, obj, new c(obj2, b(((c) obj).f7587b, dVar))));
        return dVar;
    }

    @Override // kotlinx.coroutines.channels.g
    public void cancel(CancellationException cancellationException) {
        close(cancellationException);
    }

    @Override // kotlinx.coroutines.channels.g0
    public boolean close(Throwable th) {
        Object obj;
        int i7;
        do {
            obj = this._state;
            if (obj instanceof a) {
                return false;
            }
            if (!(obj instanceof c)) {
                throw new IllegalStateException(("Invalid state " + obj).toString());
            }
        } while (!kotlinx.coroutines.j.a(f7578e, this, obj, th == null ? f7581i : new a(th)));
        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.ConflatedBroadcastChannel.State<E>");
        d<E>[] dVarArr = ((c) obj).f7587b;
        if (dVarArr != null) {
            for (d<E> dVar : dVarArr) {
                dVar.close(th);
            }
        }
        e(th);
        return true;
    }

    @Override // kotlinx.coroutines.channels.g0
    public void invokeOnClose(r5.l<? super Throwable, g5.r> lVar) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f7580h;
        if (kotlinx.coroutines.j.a(atomicReferenceFieldUpdater, this, null, lVar)) {
            Object obj = this._state;
            if ((obj instanceof a) && kotlinx.coroutines.j.a(atomicReferenceFieldUpdater, this, lVar, kotlinx.coroutines.channels.b.f7546f)) {
                lVar.invoke(((a) obj).f7585a);
                return;
            }
            return;
        }
        Object obj2 = this.onCloseHandler;
        if (obj2 == kotlinx.coroutines.channels.b.f7546f) {
            throw new IllegalStateException("Another handler was already registered and successfully invoked");
        }
        throw new IllegalStateException("Another handler was already registered: " + obj2);
    }

    @Override // kotlinx.coroutines.channels.g0
    public boolean isClosedForSend() {
        return this._state instanceof a;
    }

    @Override // kotlinx.coroutines.channels.g0
    public Object send(E e7, k5.d<? super g5.r> dVar) throws Throwable {
        a aVarF = f(e7);
        if (aVarF == null) {
            return aVarF == l5.d.d() ? aVarF : g5.r.f6563a;
        }
        throw aVarF.a();
    }
}
