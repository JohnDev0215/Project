package defpackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: compiled from: FlatBufferBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public final class nn0 {
    public ByteBuffer a;
    public int b;
    public int c;
    public int[] d;
    public int e;
    public boolean f;
    public int g;
    public int[] h;
    public int i;
    public int j;
    public final a k;
    public final rk3 l;

    /* JADX INFO: compiled from: FlatBufferBuilder.java */
    public static abstract class a {
    }

    /* JADX INFO: compiled from: FlatBufferBuilder.java */
    public static final class b extends a {
        public static final b a = new b();
    }

    public nn0() {
        b bVar = b.a;
        if (rk3.a == null) {
            rk3.a = new tk3();
        }
        tk3 tk3Var = rk3.a;
        this.c = 1;
        this.d = null;
        this.e = 0;
        this.f = false;
        this.h = new int[16];
        this.i = 0;
        this.j = 0;
        this.k = bVar;
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(1024).order(ByteOrder.LITTLE_ENDIAN);
        this.a = byteBufferOrder;
        this.l = tk3Var;
        this.b = byteBufferOrder.capacity();
    }

    public final void a(int i, int i2) {
        if (i2 != 0) {
            e(4, 0);
            ByteBuffer byteBuffer = this.a;
            int i3 = this.b - 4;
            this.b = i3;
            byteBuffer.putInt(i3, i2);
            this.d[i] = d();
        }
    }

    public final void b(short s) {
        e(2, 0);
        ByteBuffer byteBuffer = this.a;
        int i = this.b - 2;
        this.b = i;
        byteBuffer.putShort(i, s);
    }

    public final int c(String str) {
        rk3 rk3Var = this.l;
        int iC = rk3Var.c(str);
        e(1, 0);
        ByteBuffer byteBuffer = this.a;
        int i = this.b - 1;
        this.b = i;
        byteBuffer.put(i, (byte) 0);
        if (this.f) {
            throw new AssertionError("FlatBuffers: object serialization must not be nested.");
        }
        this.j = iC;
        int i2 = iC * 1;
        e(4, i2);
        e(1, i2);
        this.f = true;
        ByteBuffer byteBuffer2 = this.a;
        int i3 = this.b - iC;
        this.b = i3;
        rk3Var.b(str, this.a);
        if (!this.f) {
            throw new AssertionError("FlatBuffers: endVector called without startVector");
        }
        this.f = false;
        int i4 = this.j;
        ByteBuffer byteBuffer3 = this.a;
        int i5 = this.b - 4;
        this.b = i5;
        byteBuffer3.putInt(i5, i4);
        return d();
    }

    public final int d() {
        return this.a.capacity() - this.b;
    }

    public final void e(int i, int i2) {
        int i3;
        if (i > this.c) {
            this.c = i;
        }
        int i4 = ((~((this.a.capacity() - this.b) + i2)) + 1) & (i - 1);
        while (this.b < i4 + i + i2) {
            int iCapacity = this.a.capacity();
            ByteBuffer byteBuffer = this.a;
            int iCapacity2 = byteBuffer.capacity();
            if (iCapacity2 == 0) {
                i3 = 1024;
            } else {
                i3 = 2147483639;
                if (iCapacity2 == 2147483639) {
                    throw new AssertionError("FlatBuffers: cannot grow buffer beyond 2 gigabytes.");
                }
                if (((-1073741824) & iCapacity2) == 0) {
                    i3 = iCapacity2 << 1;
                }
            }
            ((b) this.k).getClass();
            ByteBuffer byteBufferOrder = ByteBuffer.allocate(i3).order(ByteOrder.LITTLE_ENDIAN);
            byteBufferOrder.put(byteBuffer);
            this.a = byteBufferOrder;
            this.b = (byteBufferOrder.capacity() - iCapacity) + this.b;
        }
        for (int i5 = 0; i5 < i4; i5++) {
            ByteBuffer byteBuffer2 = this.a;
            int i6 = this.b - 1;
            this.b = i6;
            byteBuffer2.put(i6, (byte) 0);
        }
    }
}
