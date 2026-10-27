package com.amazon.coral.retry.sequence;

import com.amazon.coral.retry.util.SequenceIndexValidator;

/* JADX INFO: loaded from: classes2.dex */
public class LengthBoundedSequence implements Sequence {
    private final Sequence baseSequence;
    private final int cutoffIndex;
    private final double cutoffValue;

    public LengthBoundedSequence(int i, double d, Sequence sequence) {
        this.baseSequence = sequence;
        this.cutoffIndex = i;
        this.cutoffValue = d;
    }

    @Override // com.amazon.coral.retry.sequence.Sequence
    public double get(int i) {
        SequenceIndexValidator.validate(i);
        if (i > this.cutoffIndex) {
            return this.cutoffValue;
        }
        return this.baseSequence.get(i);
    }
}
