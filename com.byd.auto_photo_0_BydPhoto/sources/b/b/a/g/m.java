package b.b.a.g;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes2.dex */
public class m extends f {
    public m(i iVar, d dVar, int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(dVar.f616a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.f622a = iVar.r(byteBufferAllocate, dVar.d + ((long) (i * dVar.g)) + 44);
    }
}
