package org.extra.relinker.a;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.extra.relinker.a.c;

/* JADX INFO: loaded from: classes3.dex */
public class h extends c.AbstractC0107c {
    public h(f fVar, c.b bVar, long j) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(bVar.f3963a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = bVar.f3965c + (j * ((long) bVar.f3967e));
        this.f3969a = fVar.e(byteBufferAllocate, j2);
        this.f3970b = fVar.c(byteBufferAllocate, 8 + j2);
        this.f3971c = fVar.c(byteBufferAllocate, 16 + j2);
        this.f3972d = fVar.c(byteBufferAllocate, j2 + 40);
    }
}
