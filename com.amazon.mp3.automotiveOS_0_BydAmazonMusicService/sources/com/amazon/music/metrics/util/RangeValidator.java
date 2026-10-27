package com.amazon.music.metrics.util;

/* JADX INFO: loaded from: classes4.dex */
public class RangeValidator {
    private boolean validateBounds(long j, long j2) {
        return j2 >= j;
    }

    public boolean withinRange(long j, long j2, long j3) {
        validateBoundsAndThrow(j2, j3);
        return j >= j2 && j <= j3;
    }

    private void validateBoundsAndThrow(long j, long j2) {
        if (!validateBounds(j, j2)) {
            throw new IllegalArgumentException("Upper Bound must be greater than or equal to Lower Bound");
        }
    }
}
