package io.sentry;

/* JADX INFO: loaded from: classes.dex */
public enum I1 {
    LOW(0.8f, 50000),
    MEDIUM(1.0f, 75000),
    HIGH(1.0f, 100000);

    public final int bitRate;
    public final float sizeScale;

    I1(float f10, int i7) {
        this.sizeScale = f10;
        this.bitRate = i7;
    }
}
