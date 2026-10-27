package p;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/* JADX INFO: loaded from: classes.dex */
public final class m32 extends RequestBody {
    public final RequestBody b;
    public final MediaType c;

    public m32(RequestBody requestBody, MediaType mediaType) {
        this.b = requestBody;
        this.c = mediaType;
    }

    @Override // okhttp3.RequestBody
    public final long a() {
        return this.b.a();
    }

    @Override // okhttp3.RequestBody
    public final MediaType b() {
        return this.c;
    }

    @Override // okhttp3.RequestBody
    public final void c(io ioVar) {
        this.b.c(ioVar);
    }
}
