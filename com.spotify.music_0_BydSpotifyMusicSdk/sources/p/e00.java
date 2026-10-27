package p;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.internal.connection.RealCall;

/* JADX INFO: loaded from: classes.dex */
public final class e00 implements Callback, ry0 {
    public final Call c;
    public final yp e;

    public e00(Call call, zp zpVar) {
        this.c = call;
        this.e = zpVar;
    }

    @Override // p.ry0
    public final Object invoke(Object obj) {
        try {
            this.c.cancel();
        } catch (Throwable unused) {
        }
        return lq2.a;
    }

    @Override // okhttp3.Callback
    public final void onFailure(Call call, IOException iOException) {
        if (((RealCall) call).x) {
            return;
        }
        ((zp) this.e).k(kotlin.a.b(iOException));
    }

    @Override // okhttp3.Callback
    public final void onResponse(Call call, Response response) {
        ((zp) this.e).k(response);
    }
}
