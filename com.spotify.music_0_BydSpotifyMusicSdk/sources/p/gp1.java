package p;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes.dex */
public final class gp1 extends ResponseBody {
    public final ResponseBody k;
    public final w02 l;
    public IOException m;

    public gp1(ResponseBody responseBody) {
        this.k = responseBody;
        this.l = v03.d(new zl(this, responseBody.i()));
    }

    @Override // okhttp3.ResponseBody
    public final long b() {
        return this.k.b();
    }

    @Override // okhttp3.ResponseBody
    public final MediaType c() {
        return this.k.c();
    }

    @Override // okhttp3.ResponseBody, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.k.close();
    }

    @Override // okhttp3.ResponseBody
    public final ko i() {
        return this.l;
    }
}
