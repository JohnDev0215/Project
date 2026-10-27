package org.apache.commons.compress.compressors.lz77support;

/* JADX INFO: loaded from: classes4.dex */
public final class Parameters {
    public static final int TRUE_MIN_BACK_REFERENCE_LENGTH = 3;
    private final boolean lazyMatching;
    private final int lazyThreshold;
    private final int maxBackReferenceLength;
    private final int maxCandidates;
    private final int maxLiteralLength;
    private final int maxOffset;
    private final int minBackReferenceLength;
    private final int niceBackReferenceLength;
    private final int windowSize;

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPowerOfTwo(int i) {
        return (i & (i + (-1))) == 0;
    }

    public static Builder builder(int i) {
        return new Builder(i);
    }

    public static class Builder {
        private Boolean lazyMatches;
        private Integer lazyThreshold;
        private int maxBackReferenceLength;
        private Integer maxCandidates;
        private int maxLiteralLength;
        private int maxOffset;
        private int minBackReferenceLength;
        private Integer niceBackReferenceLength;
        private final int windowSize;

        private Builder(int i) {
            if (i < 2 || !Parameters.isPowerOfTwo(i)) {
                throw new IllegalArgumentException("windowSize must be a power of two");
            }
            this.windowSize = i;
            this.minBackReferenceLength = 3;
            int i2 = i - 1;
            this.maxBackReferenceLength = i2;
            this.maxOffset = i2;
            this.maxLiteralLength = i;
        }

        public Builder withMinBackReferenceLength(int i) {
            int iMax = Math.max(3, i);
            this.minBackReferenceLength = iMax;
            if (this.windowSize < iMax) {
                throw new IllegalArgumentException("minBackReferenceLength can't be bigger than windowSize");
            }
            if (this.maxBackReferenceLength < iMax) {
                this.maxBackReferenceLength = iMax;
            }
            return this;
        }

        public Builder withMaxBackReferenceLength(int i) {
            int iMin = this.minBackReferenceLength;
            if (i >= iMin) {
                iMin = Math.min(i, this.windowSize - 1);
            }
            this.maxBackReferenceLength = iMin;
            return this;
        }

        public Builder withMaxOffset(int i) {
            this.maxOffset = i < 1 ? this.windowSize - 1 : Math.min(i, this.windowSize - 1);
            return this;
        }

        public Builder withMaxLiteralLength(int i) {
            int iMin;
            if (i < 1) {
                iMin = this.windowSize;
            } else {
                iMin = Math.min(i, this.windowSize);
            }
            this.maxLiteralLength = iMin;
            return this;
        }

        public Builder withNiceBackReferenceLength(int i) {
            this.niceBackReferenceLength = Integer.valueOf(i);
            return this;
        }

        public Builder withMaxNumberOfCandidates(int i) {
            this.maxCandidates = Integer.valueOf(i);
            return this;
        }

        public Builder withLazyMatching(boolean z) {
            this.lazyMatches = Boolean.valueOf(z);
            return this;
        }

        public Builder withLazyThreshold(int i) {
            this.lazyThreshold = Integer.valueOf(i);
            return this;
        }

        public Builder tunedForSpeed() {
            this.niceBackReferenceLength = Integer.valueOf(Math.max(this.minBackReferenceLength, this.maxBackReferenceLength / 8));
            this.maxCandidates = Integer.valueOf(Math.max(32, this.windowSize / 1024));
            this.lazyMatches = false;
            this.lazyThreshold = Integer.valueOf(this.minBackReferenceLength);
            return this;
        }

        public Builder tunedForCompressionRatio() {
            Integer numValueOf = Integer.valueOf(this.maxBackReferenceLength);
            this.lazyThreshold = numValueOf;
            this.niceBackReferenceLength = numValueOf;
            this.maxCandidates = Integer.valueOf(Math.max(32, this.windowSize / 16));
            this.lazyMatches = true;
            return this;
        }

        public Parameters build() {
            int iIntValue;
            int i;
            Integer num = this.niceBackReferenceLength;
            int iIntValue2 = num != null ? num.intValue() : Math.max(this.minBackReferenceLength, this.maxBackReferenceLength / 2);
            Integer num2 = this.maxCandidates;
            int iIntValue3 = num2 != null ? num2.intValue() : Math.max(256, this.windowSize / 128);
            Boolean bool = this.lazyMatches;
            boolean z = bool == null || bool.booleanValue();
            if (z) {
                Integer num3 = this.lazyThreshold;
                if (num3 == null) {
                    i = iIntValue2;
                    return new Parameters(this.windowSize, this.minBackReferenceLength, this.maxBackReferenceLength, this.maxOffset, this.maxLiteralLength, iIntValue2, iIntValue3, z, i);
                }
                iIntValue = num3.intValue();
            } else {
                iIntValue = this.minBackReferenceLength;
            }
            i = iIntValue;
            return new Parameters(this.windowSize, this.minBackReferenceLength, this.maxBackReferenceLength, this.maxOffset, this.maxLiteralLength, iIntValue2, iIntValue3, z, i);
        }
    }

    private Parameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, int i8) {
        this.windowSize = i;
        this.minBackReferenceLength = i2;
        this.maxBackReferenceLength = i3;
        this.maxOffset = i4;
        this.maxLiteralLength = i5;
        this.niceBackReferenceLength = i6;
        this.maxCandidates = i7;
        this.lazyMatching = z;
        this.lazyThreshold = i8;
    }

    public int getWindowSize() {
        return this.windowSize;
    }

    public int getMinBackReferenceLength() {
        return this.minBackReferenceLength;
    }

    public int getMaxBackReferenceLength() {
        return this.maxBackReferenceLength;
    }

    public int getMaxOffset() {
        return this.maxOffset;
    }

    public int getMaxLiteralLength() {
        return this.maxLiteralLength;
    }

    public int getNiceBackReferenceLength() {
        return this.niceBackReferenceLength;
    }

    public int getMaxCandidates() {
        return this.maxCandidates;
    }

    public boolean getLazyMatching() {
        return this.lazyMatching;
    }

    public int getLazyMatchingThreshold() {
        return this.lazyThreshold;
    }
}
