package androidx.databinding;

import java.io.Serializable;
import q0.b;
import q0.e;

/* JADX INFO: loaded from: classes.dex */
public class ObservableField<T> extends b implements Serializable {
    public static final long serialVersionUID = 1;
    private T mValue;

    public ObservableField(T t3) {
        this.mValue = t3;
    }

    public T get() {
        return this.mValue;
    }

    public void set(T t3) {
        if (t3 != this.mValue) {
            this.mValue = t3;
            notifyChange();
        }
    }

    public ObservableField() {
    }

    public ObservableField(e... eVarArr) {
        super(eVarArr);
    }
}
