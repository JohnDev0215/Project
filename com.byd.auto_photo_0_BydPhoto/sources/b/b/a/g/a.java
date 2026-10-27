package b.b.a.g;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes2.dex */
public class a extends c {
    public a(i iVar, d dVar, long j, int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(dVar.f616a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = j + ((long) (i * 8));
        this.f614a = iVar.r(byteBufferAllocate, j2);
        this.f615b = iVar.r(byteBufferAllocate, j2 + 4);
    }
}
