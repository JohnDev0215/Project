package com.amazon.coral.retry.sequence;

import com.amazon.coral.retry.util.SequenceIndexValidator;

/* JADX INFO: loaded from: classes2.dex */
public class ConstantSequence implements Sequence {
    private final long constant;

    public ConstantSequence(long j) {
        this.constant = j;
    }

    @Override // com.amazon.coral.retry.sequence.Sequence
    public double get(int i) {
        SequenceIndexValidator.validate(i);
        return this.constant;
    }
}
