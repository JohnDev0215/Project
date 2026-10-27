package com.amazon.coral.retry.sequence;

import com.amazon.coral.retry.util.SequenceIndexValidator;

/* JADX INFO: loaded from: classes2.dex */
public class RandomizedPositiveSequence implements Sequence {
    private final Sequence baseSequence;
    private final double randomizationFactor;

    public RandomizedPositiveSequence(double d, Sequence sequence) {
        if (d < 0.0d || d >= 1.0d) {
            throw new IllegalArgumentException("Randomization factor must be in [0,1).");
        }
        this.baseSequence = sequence;
        this.randomizationFactor = d;
    }

    @Override // com.amazon.coral.retry.sequence.Sequence
    public double get(int i) {
        SequenceIndexValidator.validate(i);
        double d = this.baseSequence.get(i);
        if (d < 0.0d) {
            return d;
        }
        double d2 = this.randomizationFactor;
        if (d2 == 0.0d) {
            return d;
        }
        double d3 = d2 * d;
        double d4 = d - d3;
        return d4 + (Math.random() * ((d + d3) - d4));
    }
}
