package retrofit2;

import okhttp3.Request;

/* JADX INFO: compiled from: Call.java */
/* JADX INFO: loaded from: classes.dex */
public interface d<T> extends Cloneable {
    void cancel();

    /* JADX INFO: renamed from: clone */
    d<T> mo167clone();

    void e(f<T> fVar);

    boolean isCanceled();

    Request request();
}
