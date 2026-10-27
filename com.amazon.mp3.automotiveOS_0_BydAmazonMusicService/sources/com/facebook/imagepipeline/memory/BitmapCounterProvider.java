package com.facebook.imagepipeline.memory;

/* JADX INFO: loaded from: classes5.dex */
public class BitmapCounterProvider {
    private static volatile BitmapCounter sBitmapCounter;
    public static final int MAX_BITMAP_TOTAL_SIZE = getMaxSizeHardCap();
    private static int sMaxBitmapCount = 384;

    private static int getMaxSizeHardCap() {
        int iMin = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (iMin > 16777216) {
            return (iMin / 4) * 3;
        }
        return iMin / 2;
    }

    public static BitmapCounter get() {
        if (sBitmapCounter == null) {
            synchronized (BitmapCounterProvider.class) {
                if (sBitmapCounter == null) {
                    sBitmapCounter = new BitmapCounter(sMaxBitmapCount, MAX_BITMAP_TOTAL_SIZE);
                }
            }
        }
        return sBitmapCounter;
    }
}
