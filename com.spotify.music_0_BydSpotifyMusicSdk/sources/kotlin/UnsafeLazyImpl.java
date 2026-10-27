package kotlin;

import java.io.Serializable;
import p.py0;
import p.v03;
import p.v81;
import p.zb0;

/* JADX INFO: loaded from: classes.dex */
public final class UnsafeLazyImpl<T> implements v81, Serializable {
    private Object _value = zb0.q;
    private py0 initializer;

    public UnsafeLazyImpl(py0 py0Var) {
        this.initializer = py0Var;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    @Override // p.v81
    public final Object getValue() {
        if (this._value == zb0.q) {
            py0 py0Var = this.initializer;
            v03.h(py0Var);
            this._value = py0Var.invoke();
            this.initializer = null;
        }
        return this._value;
    }

    @Override // p.v81
    public final boolean isInitialized() {
        return this._value != zb0.q;
    }

    public final String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
