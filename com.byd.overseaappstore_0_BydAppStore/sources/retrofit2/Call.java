package retrofit2;

import java.io.IOException;
import spi.dt;
import spi.im;

/* JADX INFO: loaded from: classes2.dex */
public interface Call<T> extends Cloneable {
    void cancel();

    Call<T> clone();

    void enqueue(Callback<T> callback);

    Response<T> execute() throws IOException;

    boolean isCanceled();

    boolean isExecuted();

    im request();

    dt timeout();
}
