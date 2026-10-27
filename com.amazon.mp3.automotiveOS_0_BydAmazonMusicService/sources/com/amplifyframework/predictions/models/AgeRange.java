package com.amplifyframework.predictions.models;

/* JADX INFO: loaded from: classes5.dex */
public final class AgeRange {
    private final int high;
    private final int low;

    public AgeRange(int i, int i2) {
        if (i2 < i) {
            throw new IllegalArgumentException("Low cannot be higher than High.");
        }
        this.low = i;
        this.high = i2;
    }

    public int getLow() {
        return this.low;
    }

    public int getHigh() {
        return this.high;
    }

    public boolean contains(int i) {
        return i >= this.low && i <= this.high;
    }
}
