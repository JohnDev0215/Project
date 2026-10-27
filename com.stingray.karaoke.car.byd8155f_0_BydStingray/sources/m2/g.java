package m2;

import e2.C1208p;
import e2.G;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class g extends i2.d {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C1208p f18954n;
    public final c o = new c();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ByteBuffer f18955p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f18956q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f18957r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ByteBuffer f18958s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f18959t;

    static {
        G.a("media3.decoder");
    }

    public g(int i7) {
        this.f18959t = i7;
    }

    public void f() {
        this.f15673m = 0;
        ByteBuffer byteBuffer = this.f18955p;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
        ByteBuffer byteBuffer2 = this.f18958s;
        if (byteBuffer2 != null) {
            byteBuffer2.clear();
        }
        this.f18956q = false;
    }

    public final ByteBuffer i(int i7) {
        int i10 = this.f18959t;
        if (i10 == 1) {
            return ByteBuffer.allocate(i7);
        }
        if (i10 == 2) {
            return ByteBuffer.allocateDirect(i7);
        }
        ByteBuffer byteBuffer = this.f18955p;
        throw new f("Buffer too small (" + (byteBuffer == null ? 0 : byteBuffer.capacity()) + " < " + i7 + ")");
    }

    public final void j(int i7) {
        ByteBuffer byteBuffer = this.f18955p;
        if (byteBuffer == null) {
            this.f18955p = i(i7);
            return;
        }
        int iCapacity = byteBuffer.capacity();
        int iPosition = byteBuffer.position();
        int i10 = i7 + iPosition;
        if (iCapacity >= i10) {
            this.f18955p = byteBuffer;
            return;
        }
        ByteBuffer byteBufferI = i(i10);
        byteBufferI.order(byteBuffer.order());
        if (iPosition > 0) {
            byteBuffer.flip();
            byteBufferI.put(byteBuffer);
        }
        this.f18955p = byteBufferI;
    }

    public final void k() {
        ByteBuffer byteBuffer = this.f18955p;
        if (byteBuffer != null) {
            byteBuffer.flip();
        }
        ByteBuffer byteBuffer2 = this.f18958s;
        if (byteBuffer2 != null) {
            byteBuffer2.flip();
        }
    }
}
