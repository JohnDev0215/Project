package retrofit2;

import okhttp3.Request;

/* JADX INFO: loaded from: classes6.dex */
public interface Call<T> extends Cloneable {
    void cancel();

    /* JADX INFO: renamed from: clone */
    Call<T> mo4548clone();

    void enqueue(Callback<T> callback);

    boolean isCanceled();

    Request request();
}
