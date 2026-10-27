package p;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public abstract class au {
    public int a;
    public final int b = 100;
    public final int c = Integer.MAX_VALUE;
    public com.google.protobuf.e d;

    public static int b(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long c(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static com.google.protobuf.b f(byte[] bArr, int i, int i2, boolean z) {
        com.google.protobuf.b bVar = new com.google.protobuf.b(bArr, i, i2, z);
        try {
            bVar.j(i2);
            return bVar;
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static au g(InputStream inputStream) {
        if (inputStream != null) {
            return new com.google.protobuf.c(inputStream);
        }
        byte[] bArr = w51.b;
        return f(bArr, 0, bArr.length, false);
    }

    public static au h(ByteBuffer byteBuffer, boolean z) {
        if (byteBuffer.hasArray()) {
            return f(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining(), z);
        }
        if (byteBuffer.isDirect() && dr2.d) {
            return new com.google.protobuf.d(byteBuffer, z);
        }
        int iRemaining = byteBuffer.remaining();
        byte[] bArr = new byte[iRemaining];
        byteBuffer.duplicate().get(bArr);
        return f(bArr, 0, iRemaining, true);
    }

    public static int t(int i, InputStream inputStream) throws IOException {
        if ((i & 128) == 0) {
            return i;
        }
        int i2 = i & 127;
        int i3 = 7;
        while (i3 < 32) {
            int i4 = inputStream.read();
            if (i4 == -1) {
                throw InvalidProtocolBufferException.j();
            }
            i2 |= (i4 & 127) << i3;
            if ((i4 & 128) == 0) {
                return i2;
            }
            i3 += 7;
        }
        while (i3 < 64) {
            int i5 = inputStream.read();
            if (i5 == -1) {
                throw InvalidProtocolBufferException.j();
            }
            if ((i5 & 128) == 0) {
                return i2;
            }
            i3 += 7;
        }
        throw InvalidProtocolBufferException.e();
    }

    public abstract int A();

    public abstract int B();

    public abstract long C();

    public abstract boolean D(int i);

    public abstract void a(int i);

    public abstract int d();

    public abstract boolean e();

    public abstract void i(int i);

    public abstract int j(int i);

    public abstract boolean k();

    public abstract ByteString l();

    public abstract double m();

    public abstract int n();

    public abstract int o();

    public abstract long p();

    public abstract float q();

    public abstract int r();

    public abstract long s();

    public abstract int u();

    public abstract long v();

    public abstract int w();

    public abstract long x();

    public abstract String y();

    public abstract String z();
}
