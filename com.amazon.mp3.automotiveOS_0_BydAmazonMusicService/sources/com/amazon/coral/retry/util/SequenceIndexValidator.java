package com.amazon.coral.retry.util;

/* JADX INFO: loaded from: classes2.dex */
public final class SequenceIndexValidator {
    private SequenceIndexValidator() {
    }

    public static final void validate(int i) {
        if (i < 1) {
            throw new IllegalArgumentException(String.format("Sequences are indexed from 1, index '%d' given.", Integer.valueOf(i)));
        }
    }
}
