package okhttp3.internal.http;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import p.ko;
import p.w02;

/* JADX INFO: loaded from: classes.dex */
public final class RealResponseBody extends ResponseBody {
    public final String k;
    public final long l;
    public final ko m;

    public RealResponseBody(String str, long j, w02 w02Var) {
        this.k = str;
        this.l = j;
        this.m = w02Var;
    }

    @Override // okhttp3.ResponseBody
    public final long b() {
        return this.l;
    }

    @Override // okhttp3.ResponseBody
    public final MediaType c() {
        String str = this.k;
        if (str == null) {
            return null;
        }
        MediaType.d.getClass();
        return MediaType.Companion.b(str);
    }

    @Override // okhttp3.ResponseBody
    public final ko i() {
        return this.m;
    }
}
