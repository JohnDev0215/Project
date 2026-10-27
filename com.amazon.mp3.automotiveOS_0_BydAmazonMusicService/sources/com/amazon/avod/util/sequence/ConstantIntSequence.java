package com.amazon.avod.util.sequence;

/* JADX INFO: loaded from: classes2.dex */
public class ConstantIntSequence extends AbstractIntSequence {
    @Override // com.amazon.avod.util.sequence.AbstractIntSequence
    protected int computeNextValue(int i) {
        return i;
    }

    public ConstantIntSequence(int i) {
        super(i, i);
    }
}
