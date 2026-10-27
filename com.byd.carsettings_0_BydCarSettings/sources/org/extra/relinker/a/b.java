package org.extra.relinker.a;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.extra.relinker.a.c;

/* JADX INFO: loaded from: classes3.dex */
public class b extends c.a {
    public b(f fVar, c.b bVar, long j, int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(bVar.f3963a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = j + ((long) (i * 16));
        this.f3961a = fVar.c(byteBufferAllocate, j2);
        this.f3962b = fVar.c(byteBufferAllocate, j2 + 8);
    }
}
