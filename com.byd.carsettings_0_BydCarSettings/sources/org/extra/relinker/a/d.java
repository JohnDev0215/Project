package org.extra.relinker.a;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.extra.relinker.a.c;

/* JADX INFO: loaded from: classes3.dex */
public class d extends c.b {
    private final f j;

    public d(boolean z, f fVar) {
        this.f3963a = z;
        this.j = fVar;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.f3964b = fVar.b(byteBufferAllocate, 16L);
        this.f3965c = fVar.e(byteBufferAllocate, 28L);
        this.f3966d = fVar.e(byteBufferAllocate, 32L);
        this.f3967e = fVar.b(byteBufferAllocate, 42L);
        this.f3968f = fVar.b(byteBufferAllocate, 44L);
        this.g = fVar.b(byteBufferAllocate, 46L);
        this.h = fVar.b(byteBufferAllocate, 48L);
        this.i = fVar.b(byteBufferAllocate, 50L);
    }

    @Override // org.extra.relinker.a.c.b
    public c.d a(int i) {
        return new i(this.j, this, i);
    }

    @Override // org.extra.relinker.a.c.b
    public c.AbstractC0107c a(long j) {
        return new g(this.j, this, j);
    }

    @Override // org.extra.relinker.a.c.b
    public c.a a(long j, int i) {
        return new a(this.j, this, j, i);
    }
}
