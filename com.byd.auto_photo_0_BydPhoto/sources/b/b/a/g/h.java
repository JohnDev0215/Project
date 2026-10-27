package b.b.a.g;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes2.dex */
public class h extends d {
    private final i j;

    public h(boolean z, i iVar) {
        this.f616a = z;
        this.j = iVar;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.f617b = iVar.o(byteBufferAllocate, 16L);
        this.f618c = iVar.p(byteBufferAllocate, 32L);
        this.d = iVar.p(byteBufferAllocate, 40L);
        this.e = iVar.o(byteBufferAllocate, 54L);
        this.f = iVar.o(byteBufferAllocate, 56L);
        this.g = iVar.o(byteBufferAllocate, 58L);
        this.h = iVar.o(byteBufferAllocate, 60L);
        this.i = iVar.o(byteBufferAllocate, 62L);
    }

    @Override // b.b.a.g.d
    public c a(long j, int i) {
        return new b(this.j, this, j, i);
    }

    @Override // b.b.a.g.d
    public e b(long j) {
        return new k(this.j, this, j);
    }

    @Override // b.b.a.g.d
    public f c(int i) {
        return new m(this.j, this, i);
    }
}
