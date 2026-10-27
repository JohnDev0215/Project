package androidx.databinding;

import java.io.Serializable;

/* JADX INFO: compiled from: ObservableField.java */
/* JADX INFO: loaded from: classes.dex */
public class l<T> extends b implements Serializable {
    static final long serialVersionUID = 1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private T f2078g;

    public l(T t6) {
        this.f2078g = t6;
    }

    public T q() {
        return this.f2078g;
    }

    public void r(T t6) {
        if (t6 != this.f2078g) {
            this.f2078g = t6;
            m();
        }
    }

    public l() {
    }
}
