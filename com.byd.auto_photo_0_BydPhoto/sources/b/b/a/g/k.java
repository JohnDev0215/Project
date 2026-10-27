package b.b.a.g;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes2.dex */
public class k extends e {
    public k(i iVar, d dVar, long j) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(dVar.f616a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = dVar.f618c + (j * ((long) dVar.e));
        this.f619a = iVar.r(byteBufferAllocate, j2);
        this.f620b = iVar.p(byteBufferAllocate, 8 + j2);
        this.f621c = iVar.p(byteBufferAllocate, 16 + j2);
        this.d = iVar.p(byteBufferAllocate, j2 + 40);
    }
}
