package com.amazon.avod.util.sequence;

/* JADX INFO: loaded from: classes2.dex */
public class ExponentialSequence extends AbstractIntSequence {
    private final double mMultiplier;

    public ExponentialSequence(int i, double d, int i2) {
        super(i, i2);
        this.mMultiplier = d;
    }

    @Override // com.amazon.avod.util.sequence.AbstractIntSequence
    protected int computeNextValue(int i) {
        return (int) (((double) i) * this.mMultiplier);
    }
}
