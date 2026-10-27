package androidx.leanback.widget;

/* JADX INFO: loaded from: classes3.dex */
class WindowAlignment {
    public final Axis horizontal;
    private Axis mMainAxis;
    private Axis mSecondAxis;
    private int mOrientation = 0;
    public final Axis vertical = new Axis("vertical");

    WindowAlignment() {
        Axis axis = new Axis("horizontal");
        this.horizontal = axis;
        this.mMainAxis = axis;
        this.mSecondAxis = this.vertical;
    }

    public static class Axis {
        static final int PF_KEYLINE_OVER_HIGH_EDGE = 2;
        static final int PF_KEYLINE_OVER_LOW_EDGE = 1;
        private int mMaxEdge;
        private int mMaxScroll;
        private int mMinEdge;
        private int mMinScroll;
        private int mPaddingMax;
        private int mPaddingMin;
        private boolean mReversedFlow;
        private int mSize;
        private int mPreferredKeyLine = 2;
        private int mWindowAlignment = 3;
        private int mWindowAlignmentOffset = 0;
        private float mWindowAlignmentOffsetPercent = 50.0f;

        public Axis(String name) {
            reset();
        }

        public final int getWindowAlignment() {
            return this.mWindowAlignment;
        }

        public final void setWindowAlignment(int windowAlignment) {
            this.mWindowAlignment = windowAlignment;
        }

        final void setPreferKeylineOverLowEdge(boolean keylineOverLowEdge) {
            int i;
            if (keylineOverLowEdge) {
                i = this.mPreferredKeyLine | 1;
            } else {
                i = this.mPreferredKeyLine & (-2);
            }
            this.mPreferredKeyLine = i;
        }

        final void setPreferKeylineOverHighEdge(boolean keylineOverHighEdge) {
            int i;
            if (keylineOverHighEdge) {
                i = this.mPreferredKeyLine | 2;
            } else {
                i = this.mPreferredKeyLine & (-3);
            }
            this.mPreferredKeyLine = i;
        }

        final boolean isPreferKeylineOverHighEdge() {
            return (this.mPreferredKeyLine & 2) != 0;
        }

        final boolean isPreferKeylineOverLowEdge() {
            return (this.mPreferredKeyLine & 1) != 0;
        }

        public final int getWindowAlignmentOffset() {
            return this.mWindowAlignmentOffset;
        }

        public final void setWindowAlignmentOffset(int offset) {
            this.mWindowAlignmentOffset = offset;
        }

        public final void setWindowAlignmentOffsetPercent(float percent) {
            if ((percent < 0.0f || percent > 100.0f) && percent != -1.0f) {
                throw new IllegalArgumentException();
            }
            this.mWindowAlignmentOffsetPercent = percent;
        }

        public final float getWindowAlignmentOffsetPercent() {
            return this.mWindowAlignmentOffsetPercent;
        }

        public final int getMinScroll() {
            return this.mMinScroll;
        }

        public final void invalidateScrollMin() {
            this.mMinEdge = Integer.MIN_VALUE;
            this.mMinScroll = Integer.MIN_VALUE;
        }

        public final int getMaxScroll() {
            return this.mMaxScroll;
        }

        public final void invalidateScrollMax() {
            this.mMaxEdge = Integer.MAX_VALUE;
            this.mMaxScroll = Integer.MAX_VALUE;
        }

        void reset() {
            this.mMinEdge = Integer.MIN_VALUE;
            this.mMaxEdge = Integer.MAX_VALUE;
        }

        public final boolean isMinUnknown() {
            return this.mMinEdge == Integer.MIN_VALUE;
        }

        public final boolean isMaxUnknown() {
            return this.mMaxEdge == Integer.MAX_VALUE;
        }

        public final void setSize(int size) {
            this.mSize = size;
        }

        public final int getSize() {
            return this.mSize;
        }

        public final void setPadding(int paddingMin, int paddingMax) {
            this.mPaddingMin = paddingMin;
            this.mPaddingMax = paddingMax;
        }

        public final int getPaddingMin() {
            return this.mPaddingMin;
        }

        public final int getPaddingMax() {
            return this.mPaddingMax;
        }

        public final int getClientSize() {
            return (this.mSize - this.mPaddingMin) - this.mPaddingMax;
        }

        final int calculateKeyline() {
            int keyLine;
            int keyLine2;
            if (!this.mReversedFlow) {
                int i = this.mWindowAlignmentOffset;
                if (i >= 0) {
                    keyLine2 = this.mWindowAlignmentOffset;
                } else {
                    keyLine2 = i + this.mSize;
                }
                float f = this.mWindowAlignmentOffsetPercent;
                if (f != -1.0f) {
                    return keyLine2 + ((int) ((this.mSize * f) / 100.0f));
                }
                return keyLine2;
            }
            int keyLine3 = this.mWindowAlignmentOffset;
            if (keyLine3 >= 0) {
                keyLine = this.mSize - keyLine3;
            } else {
                keyLine = -keyLine3;
            }
            float f2 = this.mWindowAlignmentOffsetPercent;
            if (f2 != -1.0f) {
                return keyLine - ((int) ((this.mSize * f2) / 100.0f));
            }
            return keyLine;
        }

        final int calculateScrollToKeyLine(int viewCenterPosition, int keyLine) {
            return viewCenterPosition - keyLine;
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
        
            r6.mMinScroll = r6.mMinEdge - r6.mPaddingMin;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
        
            r6.mMaxScroll = (r6.mMaxEdge - r6.mPaddingMin) - r0;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void updateMinMax(int r7, int r8, int r9, int r10) {
            /*
                Method dump skipped, instruction units count: 231
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.WindowAlignment.Axis.updateMinMax(int, int, int, int):void");
        }

        public final int getScroll(int viewCenter) {
            int size = getSize();
            int keyLine = calculateKeyline();
            boolean isMinUnknown = isMinUnknown();
            boolean isMaxUnknown = isMaxUnknown();
            if (!isMinUnknown) {
                int keyLineToMinEdge = keyLine - this.mPaddingMin;
                if (this.mReversedFlow ? (this.mWindowAlignment & 2) != 0 : (this.mWindowAlignment & 1) != 0) {
                    int i = this.mMinEdge;
                    if (viewCenter - i <= keyLineToMinEdge) {
                        int alignToMin = i - this.mPaddingMin;
                        if (!isMaxUnknown && alignToMin > this.mMaxScroll) {
                            return this.mMaxScroll;
                        }
                        return alignToMin;
                    }
                }
            }
            if (!isMaxUnknown) {
                int keyLineToMaxEdge = (size - keyLine) - this.mPaddingMax;
                if (this.mReversedFlow ? (this.mWindowAlignment & 1) != 0 : (this.mWindowAlignment & 2) != 0) {
                    int i2 = this.mMaxEdge;
                    if (i2 - viewCenter <= keyLineToMaxEdge) {
                        int alignToMax = i2 - (size - this.mPaddingMax);
                        if (!isMinUnknown && alignToMax < this.mMinScroll) {
                            return this.mMinScroll;
                        }
                        return alignToMax;
                    }
                }
            }
            int keyLineToMaxEdge2 = calculateScrollToKeyLine(viewCenter, keyLine);
            return keyLineToMaxEdge2;
        }

        public final void setReversedFlow(boolean reversedFlow) {
            this.mReversedFlow = reversedFlow;
        }

        public String toString() {
            return " min:" + this.mMinEdge + " " + this.mMinScroll + " max:" + this.mMaxEdge + " " + this.mMaxScroll;
        }
    }

    public final Axis mainAxis() {
        return this.mMainAxis;
    }

    public final Axis secondAxis() {
        return this.mSecondAxis;
    }

    public final void setOrientation(int orientation) {
        this.mOrientation = orientation;
        if (orientation == 0) {
            this.mMainAxis = this.horizontal;
            this.mSecondAxis = this.vertical;
        } else {
            this.mMainAxis = this.vertical;
            this.mSecondAxis = this.horizontal;
        }
    }

    public final int getOrientation() {
        return this.mOrientation;
    }

    public final void reset() {
        mainAxis().reset();
    }

    public String toString() {
        return "horizontal=" + this.horizontal + "; vertical=" + this.vertical;
    }
}
