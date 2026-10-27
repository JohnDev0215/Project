package org.extra.relinker.a;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.extra.relinker.a.c;

/* JADX INFO: loaded from: classes3.dex */
public class g extends c.AbstractC0107c {
    public g(f fVar, c.b bVar, long j) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(bVar.f3963a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = bVar.f3965c + (j * ((long) bVar.f3967e));
        this.f3969a = fVar.e(byteBufferAllocate, j2);
        this.f3970b = fVar.e(byteBufferAllocate, 4 + j2);
        this.f3971c = fVar.e(byteBufferAllocate, 8 + j2);
        this.f3972d = fVar.e(byteBufferAllocate, j2 + 20);
    }
}
