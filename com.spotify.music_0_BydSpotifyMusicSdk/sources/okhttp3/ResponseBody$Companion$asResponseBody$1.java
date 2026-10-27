package okhttp3;

import p.fo;
import p.ko;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseBody$Companion$asResponseBody$1 extends ResponseBody {
    public final /* synthetic */ MediaType k;
    public final /* synthetic */ long l;
    public final /* synthetic */ ko m;

    public ResponseBody$Companion$asResponseBody$1(MediaType mediaType, long j, fo foVar) {
        this.k = mediaType;
        this.l = j;
        this.m = foVar;
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
        return this.m;
    }
}
