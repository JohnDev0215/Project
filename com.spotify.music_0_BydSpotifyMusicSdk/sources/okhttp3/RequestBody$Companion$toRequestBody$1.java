package okhttp3;

import okio.ByteString;
import p.io;

/* JADX INFO: loaded from: classes.dex */
public final class RequestBody$Companion$toRequestBody$1 extends RequestBody {
    public final /* synthetic */ MediaType b;
    public final /* synthetic */ ByteString c;

    public RequestBody$Companion$toRequestBody$1(MediaType mediaType, ByteString byteString) {
        this.b = mediaType;
        this.c = byteString;
    }

    @Override // okhttp3.RequestBody
    public final long a() {
        return this.c.e();
    }

    @Override // okhttp3.RequestBody
    public final MediaType b() {
        return this.b;
    }

    @Override // okhttp3.RequestBody
    public final void c(io ioVar) {
        ioVar.a0(this.c);
    }
}
