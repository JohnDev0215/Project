package p;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/* JADX INFO: loaded from: classes.dex */
public final class z60 extends RequestBody {
    public final /* synthetic */ e21 b;

    public z60(e21 e21Var) {
        this.b = e21Var;
    }

    @Override // okhttp3.RequestBody
    public final long a() {
        return this.b.c();
    }

    @Override // okhttp3.RequestBody
    public final MediaType b() {
        MediaType.Companion companion = MediaType.d;
        String strB = this.b.b();
        companion.getClass();
        return MediaType.Companion.a(strB);
    }

    @Override // okhttp3.RequestBody
    public final void c(io ioVar) {
        this.b.a(ioVar);
    }
}
