package p;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes.dex */
public final class hp1 extends ResponseBody {
    public final MediaType k;
    public final long l;

    public hp1(MediaType mediaType, long j) {
        this.k = mediaType;
        this.l = j;
    }

    @Override // okhttp3.ResponseBody
    public final long b() {
        return this.l;
    }

    @Override // okhttp3.ResponseBody
    public final MediaType c() {
        return this.k;
    }

    @Override // okhttp3.ResponseBody
    public final ko i() {
        throw new IllegalStateException("Cannot read raw response body of a converted body.");
    }
}
