package spi;

/* JADX INFO: loaded from: classes.dex */
public final class m2 implements l0<byte[]> {
    @Override // spi.l0
    public final int a() {
        return 1;
    }

    @Override // spi.l0
    public final int b(byte[] bArr) {
        return bArr.length;
    }

    @Override // spi.l0
    public final String getTag() {
        return "ByteArrayPool";
    }

    @Override // spi.l0
    public final byte[] newArray(int i) {
        return new byte[i];
    }
}
