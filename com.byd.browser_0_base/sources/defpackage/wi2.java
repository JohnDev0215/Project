package defpackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/* JADX INFO: compiled from: ServiceMessage.java */
/* JADX INFO: loaded from: classes3.dex */
public final class wi2 extends gq0 {
    public final yg1 d;
    public gq0 e;

    public wi2(gq0 gq0Var, yg1 yg1Var) {
        super((ByteBuffer) gq0Var.a, (List) gq0Var.b);
        this.d = yg1Var;
    }

    public final gq0 f() {
        if (this.e == null) {
            ByteBuffer byteBufferSlice = ((ByteBuffer) ((ByteBuffer) this.a).position(this.d.a.a)).slice();
            byteBufferSlice.order(ByteOrder.LITTLE_ENDIAN);
            this.e = new gq0(byteBufferSlice, (List) this.b);
        }
        return this.e;
    }

    @Override // defpackage.gq0
    public final wi2 a() {
        return this;
    }
}
