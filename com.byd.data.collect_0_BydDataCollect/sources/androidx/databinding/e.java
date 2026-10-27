package androidx.databinding;

import java.io.Serializable;

/* JADX INFO: compiled from: ObservableField.java */
/* JADX INFO: loaded from: classes.dex */
public class e<T> extends b implements Serializable {
    static final long serialVersionUID = 1;
    private T mValue;

    public e(T t) {
        this.mValue = t;
    }

    public T a() {
        return this.mValue;
    }

    public e() {
    }
}
