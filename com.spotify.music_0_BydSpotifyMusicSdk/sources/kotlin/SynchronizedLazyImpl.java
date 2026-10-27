package kotlin;

import java.io.Serializable;
import p.py0;
import p.v03;
import p.v81;
import p.zb0;

/* JADX INFO: loaded from: classes.dex */
final class SynchronizedLazyImpl<T> implements v81, Serializable {
    private volatile Object _value;
    private py0 initializer;
    private final Object lock;

    public SynchronizedLazyImpl(py0 py0Var) {
        v03.j(py0Var, "initializer");
        this.initializer = py0Var;
        this._value = zb0.q;
        this.lock = this;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    @Override // p.v81
    public final Object getValue() {
        Object objInvoke;
        Object obj = this._value;
        zb0 zb0Var = zb0.q;
        if (obj != zb0Var) {
            return obj;
        }
        synchronized (this.lock) {
            objInvoke = this._value;
            if (objInvoke == zb0Var) {
                py0 py0Var = this.initializer;
                v03.h(py0Var);
                objInvoke = py0Var.invoke();
                this._value = objInvoke;
                this.initializer = null;
            }
        }
        return objInvoke;
    }

    @Override // p.v81
    public final boolean isInitialized() {
        return this._value != zb0.q;
    }

    public final String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
