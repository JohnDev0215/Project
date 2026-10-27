package kotlin;

import java.io.Serializable;

/* JADX INFO: compiled from: Lazy.kt */
/* JADX INFO: loaded from: classes.dex */
public final class UnsafeLazyImpl<T> implements a<T>, Serializable {
    private Object _value;
    private kotlin.jvm.b.a<? extends T> initializer;

    public UnsafeLazyImpl(kotlin.jvm.b.a<? extends T> aVar) {
        kotlin.jvm.internal.h.d(aVar, "initializer");
        this.initializer = aVar;
        this._value = j.f4070a;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    @Override // kotlin.a
    public T getValue() {
        if (this._value == j.f4070a) {
            kotlin.jvm.b.a<? extends T> aVar = this.initializer;
            kotlin.jvm.internal.h.b(aVar);
            this._value = aVar.invoke();
            this.initializer = null;
        }
        return (T) this._value;
    }

    public boolean isInitialized() {
        return this._value != j.f4070a;
    }

    public String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
