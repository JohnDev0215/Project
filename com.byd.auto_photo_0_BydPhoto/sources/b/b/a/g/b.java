package b.b.a.g;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes2.dex */
public class b extends c {
    public b(i iVar, d dVar, long j, int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(dVar.f616a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = j + ((long) (i * 16));
        this.f614a = iVar.p(byteBufferAllocate, j2);
        this.f615b = iVar.p(byteBufferAllocate, j2 + 8);
    }
}
