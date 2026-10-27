package androidx.databinding;

import java.io.Serializable;
import u0.b;
import u0.e;

/* JADX INFO: loaded from: classes.dex */
public class ObservableField<T> extends b implements Serializable {
    public static final long serialVersionUID = 1;
    private T mValue;

    public ObservableField(T t7) {
        this.mValue = t7;
    }

    public T get() {
        return this.mValue;
    }

    public void set(T t7) {
        if (t7 != this.mValue) {
            this.mValue = t7;
            notifyChange();
        }
    }

    public ObservableField() {
    }

    public ObservableField(e... eVarArr) {
        super(eVarArr);
    }
}
