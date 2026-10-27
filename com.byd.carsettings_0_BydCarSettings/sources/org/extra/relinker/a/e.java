package org.extra.relinker.a;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.extra.relinker.a.c;

/* JADX INFO: loaded from: classes3.dex */
public class e extends c.b {
    private final f j;

    public e(boolean z, f fVar) {
        this.f3963a = z;
        this.j = fVar;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.f3964b = fVar.b(byteBufferAllocate, 16L);
        this.f3965c = fVar.c(byteBufferAllocate, 32L);
        this.f3966d = fVar.c(byteBufferAllocate, 40L);
        this.f3967e = fVar.b(byteBufferAllocate, 54L);
        this.f3968f = fVar.b(byteBufferAllocate, 56L);
        this.g = fVar.b(byteBufferAllocate, 58L);
        this.h = fVar.b(byteBufferAllocate, 60L);
        this.i = fVar.b(byteBufferAllocate, 62L);
    }

    @Override // org.extra.relinker.a.c.b
    public c.d a(int i) {
        return new j(this.j, this, i);
    }

    @Override // org.extra.relinker.a.c.b
    public c.AbstractC0107c a(long j) {
        return new h(this.j, this, j);
    }

    @Override // org.extra.relinker.a.c.b
    public c.a a(long j, int i) {
        return new b(this.j, this, j, i);
    }
}
