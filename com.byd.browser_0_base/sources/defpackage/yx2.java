package defpackage;

import defpackage.ce2;
import java.io.Serializable;

/* JADX INFO: compiled from: LazyJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public final class yx2<T> implements Serializable {
    public yq0<? extends T> e;
    public volatile Object f = oj2.p;
    public final Object g = this;

    public yx2(ce2.a aVar) {
        this.e = aVar;
    }

    private final Object writeReplace() {
        return new zz0(a());
    }

    public final T a() {
        T tA;
        T t = (T) this.f;
        oj2 oj2Var = oj2.p;
        if (t != oj2Var) {
            return t;
        }
        synchronized (this.g) {
            tA = (T) this.f;
            if (tA == oj2Var) {
                yq0<? extends T> yq0Var = this.e;
                i21.b(yq0Var);
                tA = yq0Var.a();
                this.f = tA;
                this.e = null;
            }
        }
        return tA;
    }

    public final String toString() {
        return this.f != oj2.p ? String.valueOf(a()) : "Lazy value not initialized yet.";
    }
}
