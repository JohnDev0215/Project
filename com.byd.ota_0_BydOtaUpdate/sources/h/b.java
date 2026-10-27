package h;

import java.io.IOException;

/* JADX INFO: compiled from: Call.java */
/* JADX INFO: loaded from: classes.dex */
public interface b<T> extends Cloneable {
    l<T> c() throws IOException;

    void cancel();

    b<T> clone();

    boolean d();

    void v(d<T> dVar);
}
