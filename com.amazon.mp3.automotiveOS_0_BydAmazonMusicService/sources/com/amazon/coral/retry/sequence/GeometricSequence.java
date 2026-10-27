package com.amazon.coral.retry.sequence;

import com.amazon.coral.retry.util.SequenceIndexValidator;

/* JADX INFO: loaded from: classes2.dex */
public class GeometricSequence implements Sequence {
    private final double commonRatio;
    private final double firstTerm;

    public GeometricSequence(double d, double d2) {
        this.firstTerm = d;
        this.commonRatio = d2;
    }

    @Override // com.amazon.coral.retry.sequence.Sequence
    public double get(int i) {
        SequenceIndexValidator.validate(i);
        return this.firstTerm * Math.pow(this.commonRatio, i - 1);
    }
}
