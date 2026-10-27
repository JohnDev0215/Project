package a.k;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class l<T> extends b implements Serializable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public T f925b;

    public void j(T t) {
        if (t != this.f925b) {
            this.f925b = t;
            h();
        }
    }
}
