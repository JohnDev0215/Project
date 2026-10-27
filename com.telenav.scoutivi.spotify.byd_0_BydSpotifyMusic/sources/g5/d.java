package g5;

import java.io.Serializable;

/* JADX INFO: compiled from: Lazy.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d<T> implements f<T>, Serializable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final T f6554e;

    public d(T t6) {
        this.f6554e = t6;
    }

    @Override // g5.f
    public T getValue() {
        return this.f6554e;
    }

    public String toString() {
        return String.valueOf(getValue());
    }
}
