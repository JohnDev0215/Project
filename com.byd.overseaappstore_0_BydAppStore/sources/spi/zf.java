package spi;

/* JADX INFO: loaded from: classes.dex */
public final class zf implements l0<int[]> {
    @Override // spi.l0
    public final int a() {
        return 4;
    }

    @Override // spi.l0
    public final int b(int[] iArr) {
        return iArr.length;
    }

    @Override // spi.l0
    public final String getTag() {
        return "IntegerArrayPool";
    }

    @Override // spi.l0
    public final int[] newArray(int i) {
        return new int[i];
    }
}
