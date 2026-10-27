package p;

import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class c70 implements e21 {
    public final long a;
    public final /* synthetic */ ByteString b;

    public c70(ByteString byteString) {
        this.b = byteString;
        this.a = byteString.e();
    }

    @Override // p.e21
    public final void a(io ioVar) {
        ioVar.a0(this.b);
    }

    @Override // p.e21
    public final String b() {
        return "application/json";
    }

    @Override // p.e21
    public final long c() {
        return this.a;
    }
}
