package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: Atomic.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class d<T> extends w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7625a = AtomicReferenceFieldUpdater.newUpdater(d.class, Object.class, "_consensus");
    private volatile Object _consensus = c.f7622a;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.internal.w
    public d<?> a() {
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.internal.w
    public final Object c(Object obj) {
        Object objE = this._consensus;
        if (objE == c.f7622a) {
            objE = e(i(obj));
        }
        d(obj, objE);
        return objE;
    }

    public abstract void d(T t6, Object obj);

    public final Object e(Object obj) {
        Object obj2 = this._consensus;
        Object obj3 = c.f7622a;
        return obj2 != obj3 ? obj2 : kotlinx.coroutines.j.a(f7625a, this, obj3, obj) ? obj : this._consensus;
    }

    public final Object f() {
        return this._consensus;
    }

    public long g() {
        return 0L;
    }

    public final boolean h() {
        return this._consensus != c.f7622a;
    }

    public abstract Object i(T t6);
}
