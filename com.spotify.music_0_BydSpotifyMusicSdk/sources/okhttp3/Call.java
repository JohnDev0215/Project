package okhttp3;

import okhttp3.internal.connection.RealCall;

/* JADX INFO: loaded from: classes.dex */
public interface Call extends Cloneable {

    public interface Factory {
        RealCall d(Request request);
    }

    void J(Callback callback);

    Response b();

    Request c();

    void cancel();

    boolean i();
}
