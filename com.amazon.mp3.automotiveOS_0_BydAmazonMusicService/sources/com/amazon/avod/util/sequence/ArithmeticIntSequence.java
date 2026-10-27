package com.amazon.avod.util.sequence;

/* JADX INFO: loaded from: classes2.dex */
public class ArithmeticIntSequence extends AbstractIntSequence {
    private final int mIncrement;

    public ArithmeticIntSequence(int i, int i2, int i3) {
        super(i, i3);
        this.mIncrement = i2;
    }

    @Override // com.amazon.avod.util.sequence.AbstractIntSequence
    protected int computeNextValue(int i) {
        return i + this.mIncrement;
    }
}
