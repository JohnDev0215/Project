package com.amazon.avod.util.sequence;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractIntSequence {
    private int mCurrentValue;
    private final int mMaxValue;
    private final int mStartValue;

    protected abstract int computeNextValue(int i);

    public AbstractIntSequence(int i, int i2) {
        this.mStartValue = i;
        this.mMaxValue = i2;
        this.mCurrentValue = i;
    }

    public int getMaxValue() {
        return this.mMaxValue;
    }

    public synchronized int getNextValue() {
        int i;
        i = this.mCurrentValue;
        try {
            int iComputeNextValue = computeNextValue(i);
            int i2 = this.mMaxValue;
            if (iComputeNextValue > i2 || iComputeNextValue <= 0) {
                iComputeNextValue = i2;
            }
            this.mCurrentValue = iComputeNextValue;
        } catch (ArithmeticException unused) {
            this.mCurrentValue = this.mMaxValue;
        }
        return i;
    }

    public synchronized void reset() {
        this.mCurrentValue = this.mStartValue;
    }
}
