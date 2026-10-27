package b.b.a.g;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes2.dex */
public class g extends d {
    private final i j;

    public g(boolean z, i iVar) {
        this.f616a = z;
        this.j = iVar;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.f617b = iVar.o(byteBufferAllocate, 16L);
        this.f618c = iVar.r(byteBufferAllocate, 28L);
        this.d = iVar.r(byteBufferAllocate, 32L);
        this.e = iVar.o(byteBufferAllocate, 42L);
        this.f = iVar.o(byteBufferAllocate, 44L);
        this.g = iVar.o(byteBufferAllocate, 46L);
        this.h = iVar.o(byteBufferAllocate, 48L);
        this.i = iVar.o(byteBufferAllocate, 50L);
    }

    @Override // b.b.a.g.d
    public c a(long j, int i) {
        return new a(this.j, this, j, i);
    }

    @Override // b.b.a.g.d
    public e b(long j) {
        return new j(this.j, this, j);
    }

    @Override // b.b.a.g.d
    public f c(int i) {
        return new l(this.j, this, i);
    }
}
