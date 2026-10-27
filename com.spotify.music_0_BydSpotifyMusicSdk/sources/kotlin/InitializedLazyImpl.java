package kotlin;

import java.io.Serializable;
import p.v81;

/* JADX INFO: loaded from: classes.dex */
public final class InitializedLazyImpl<T> implements v81, Serializable {
    private final T value;

    /* JADX WARN: Multi-variable type inference failed */
    public InitializedLazyImpl(Object obj) {
        this.value = obj;
    }

    @Override // p.v81
    public final Object getValue() {
        return this.value;
    }

    @Override // p.v81
    public final boolean isInitialized() {
        throw null;
    }

    public final String toString() {
        return String.valueOf(this.value);
    }
}
