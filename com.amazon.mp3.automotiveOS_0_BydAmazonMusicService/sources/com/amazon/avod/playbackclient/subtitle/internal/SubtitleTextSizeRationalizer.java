package com.amazon.avod.playbackclient.subtitle.internal;

import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleTextSizeRationalizer {
    private static final IntegerRange VALID_RANGE = IntegerRange.from(50, 200);

    public int rationalize(int i) {
        IntegerRange integerRange = VALID_RANGE;
        if (integerRange.contains(i)) {
            return i;
        }
        if (i < integerRange.getBegin()) {
            return integerRange.getBegin();
        }
        return integerRange.getEnd();
    }

    public static final class IntegerRange {
        private int mBegin;
        private int mEnd;

        public static IntegerRange from(int i, int i2) {
            return new IntegerRange(i, i2);
        }

        private IntegerRange(int i, int i2) {
            this.mBegin = i;
            this.mEnd = i2;
        }

        public int getBegin() {
            return this.mBegin;
        }

        public int getEnd() {
            return this.mEnd;
        }

        public boolean contains(int i) {
            return i >= this.mBegin && i <= this.mEnd;
        }

        public String toString() {
            return String.format(Locale.US, "[%d,%d]", Integer.valueOf(this.mBegin), Integer.valueOf(this.mEnd));
        }
    }
}
