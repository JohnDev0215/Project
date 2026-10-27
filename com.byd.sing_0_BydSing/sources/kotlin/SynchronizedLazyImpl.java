package kotlin;

import java.io.Serializable;

/* JADX INFO: compiled from: LazyJVM.kt */
/* JADX INFO: loaded from: classes.dex */
final class SynchronizedLazyImpl<T> implements a<T>, Serializable {
    private volatile Object _value;
    private kotlin.jvm.b.a<? extends T> initializer;
    private final Object lock;

    public SynchronizedLazyImpl(kotlin.jvm.b.a<? extends T> aVar, Object obj) {
        kotlin.jvm.internal.h.d(aVar, "initializer");
        this.initializer = aVar;
        this._value = j.f4070a;
        this.lock = obj == null ? this : obj;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    @Override // kotlin.a
    public T getValue() {
        T tInvoke;
        T t = (T) this._value;
        j jVar = j.f4070a;
        if (t != jVar) {
            return t;
        }
        synchronized (this.lock) {
            tInvoke = (T) this._value;
            if (tInvoke == jVar) {
                kotlin.jvm.b.a<? extends T> aVar = this.initializer;
                kotlin.jvm.internal.h.b(aVar);
                tInvoke = aVar.invoke();
                this._value = tInvoke;
                this.initializer = null;
            }
        }
        return tInvoke;
    }

    public boolean isInitialized() {
        return this._value != j.f4070a;
    }

    public String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }

    public /* synthetic */ SynchronizedLazyImpl(kotlin.jvm.b.a aVar, Object obj, int i, kotlin.jvm.internal.f fVar) {
        this(aVar, (i & 2) != 0 ? null : obj);
    }
}
