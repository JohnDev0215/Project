package org.extra.relinker.a;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.extra.relinker.a.c;

/* JADX INFO: loaded from: classes3.dex */
public class a extends c.a {
    public a(f fVar, c.b bVar, long j, int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(bVar.f3963a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = j + ((long) (i * 8));
        this.f3961a = fVar.e(byteBufferAllocate, j2);
        this.f3962b = fVar.e(byteBufferAllocate, j2 + 4);
    }
}
