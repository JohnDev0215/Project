package p;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public abstract class z0 extends zu {
    public final ByteBuffer i = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

    public abstract void H0(byte[] bArr, int i);

    @Override // p.zu
    public final zu x0(byte[] bArr) {
        bArr.getClass();
        H0(bArr, bArr.length);
        return this;
    }

    @Override // p.zu
    public final zu y0(byte[] bArr, int i) {
        ci1.m(0, i, bArr.length);
        H0(bArr, i);
        return this;
    }

    @Override // p.zu
    public final zu z0(char c) {
        ByteBuffer byteBuffer = this.i;
        byteBuffer.putChar(c);
        try {
            H0(byteBuffer.array(), 2);
            return this;
        } finally {
            byteBuffer.clear();
        }
    }
}
