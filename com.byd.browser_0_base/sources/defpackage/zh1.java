package defpackage;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: MockPersistedTabData.java */
/* JADX INFO: loaded from: classes2.dex */
public class zh1 extends l02 {
    public static final /* synthetic */ int o = 0;
    public int n;

    @Override // defpackage.l02
    public final boolean b(ByteBuffer byteBuffer) {
        this.n = byteBuffer.getInt();
        return true;
    }

    @Override // defpackage.l02
    public final vi2<ByteBuffer> g() {
        ByteBuffer byteBufferPutInt = ByteBuffer.allocate(4).putInt(this.n);
        byteBufferPutInt.rewind();
        return new c01(20, byteBufferPutInt);
    }

    @Override // defpackage.l02
    public final String i() {
        return "MockCritical";
    }
}
