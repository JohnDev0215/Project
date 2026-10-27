package kotlin;

import java.io.Serializable;

/* JADX INFO: compiled from: LazyJVM.kt */
/* JADX INFO: loaded from: classes.dex */
final class SynchronizedLazyImpl<T> implements c<T>, Serializable {
    private volatile Object _value;
    private kotlin.jvm.b.a<? extends T> initializer;
    private final Object lock;

    public SynchronizedLazyImpl(kotlin.jvm.b.a<? extends T> aVar, Object obj) {
        kotlin.jvm.internal.i.c(aVar, "initializer");
        this.initializer = aVar;
        this._value = i.f5108a;
        this.lock = obj == null ? this : obj;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    @Override // kotlin.c
    public T getValue() {
        T tInvoke;
        T t = (T) this._value;
        i iVar = i.f5108a;
        if (t != iVar) {
            return t;
        }
        synchronized (this.lock) {
            tInvoke = (T) this._value;
            if (tInvoke == iVar) {
                kotlin.jvm.b.a<? extends T> aVar = this.initializer;
                if (aVar == null) {
                    kotlin.jvm.internal.i.g();
                }
                tInvoke = aVar.invoke();
                this._value = tInvoke;
                this.initializer = null;
            }
        }
        return tInvoke;
    }

    public boolean isInitialized() {
        return this._value != i.f5108a;
    }

    public String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }

    public /* synthetic */ SynchronizedLazyImpl(kotlin.jvm.b.a aVar, Object obj, int i, kotlin.jvm.internal.f fVar) {
        this(aVar, (i & 2) != 0 ? null : obj);
    }
}
