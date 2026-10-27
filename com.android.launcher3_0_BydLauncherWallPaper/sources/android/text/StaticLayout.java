package android.text;

import android.graphics.Paint;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pools;
import com.android.internal.util.ArrayUtils;

/* JADX INFO: loaded from: classes3.dex */
public class StaticLayout extends Layout {
    private static final char CHAR_NEW_LINE = '\n';
    private static final int COLUMNS_ELLIPSIZE = 7;
    private static final int COLUMNS_NORMAL = 5;
    private static final int DEFAULT_MAX_LINE_HEIGHT = -1;
    private static final int DESCENT = 2;
    private static final int DIR = 0;
    private static final int DIR_SHIFT = 30;
    private static final int ELLIPSIS_COUNT = 6;
    private static final int ELLIPSIS_START = 5;
    private static final int END_HYPHEN_MASK = 7;
    private static final int EXTRA = 3;
    private static final double EXTRA_ROUNDING = 0.5d;
    private static final int HYPHEN = 4;
    private static final int HYPHEN_MASK = 255;
    private static final int START = 0;
    private static final int START_HYPHEN_BITS_SHIFT = 3;
    private static final int START_HYPHEN_MASK = 24;
    private static final int START_MASK = 536870911;
    private static final int TAB = 0;
    private static final float TAB_INCREMENT = 20.0f;
    private static final int TAB_MASK = 536870912;
    static final String TAG = "StaticLayout";
    private static final int TOP = 1;
    private int mBottomPadding;
    private int mColumns;
    private boolean mEllipsized;
    private int mEllipsizedWidth;
    private int[] mLeftIndents;
    private int mLineCount;
    private Layout.Directions[] mLineDirections;
    private int[] mLines;
    private int mMaxLineHeight;
    private int mMaximumVisibleLineCount;
    private int[] mRightIndents;
    private int mTopPadding;

    static int packHyphenEdit(int i, int i2) {
        return (i << 3) | i2;
    }

    static int unpackEndHyphenEdit(int i) {
        return i & 7;
    }

    static int unpackStartHyphenEdit(int i) {
        return (i & 24) >> 3;
    }

    public static final class Builder {
        private static final Pools.SynchronizedPool<Builder> sPool = new Pools.SynchronizedPool<>(3);
        private boolean mAddLastLineLineSpacing;
        private Layout.Alignment mAlignment;
        private int mBreakStrategy;
        private TextUtils.TruncateAt mEllipsize;
        private int mEllipsizedWidth;
        private int mEnd;
        private boolean mFallbackLineSpacing;
        private final Paint.FontMetricsInt mFontMetricsInt = new Paint.FontMetricsInt();
        private int mHyphenationFrequency;
        private boolean mIncludePad;
        private int mJustificationMode;
        private int[] mLeftIndents;
        private int mMaxLines;
        private TextPaint mPaint;
        private int[] mRightIndents;
        private float mSpacingAdd;
        private float mSpacingMult;
        private int mStart;
        private CharSequence mText;
        private TextDirectionHeuristic mTextDir;
        private int mWidth;

        private Builder() {
        }

        public static Builder obtain(CharSequence charSequence, int i, int i2, TextPaint textPaint, int i3) {
            Builder builderAcquire = sPool.acquire();
            if (builderAcquire == null) {
                builderAcquire = new Builder();
            }
            builderAcquire.mText = charSequence;
            builderAcquire.mStart = i;
            builderAcquire.mEnd = i2;
            builderAcquire.mPaint = textPaint;
            builderAcquire.mWidth = i3;
            builderAcquire.mAlignment = Layout.Alignment.ALIGN_NORMAL;
            builderAcquire.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            builderAcquire.mSpacingMult = 1.0f;
            builderAcquire.mSpacingAdd = 0.0f;
            builderAcquire.mIncludePad = true;
            builderAcquire.mFallbackLineSpacing = false;
            builderAcquire.mEllipsizedWidth = i3;
            builderAcquire.mEllipsize = null;
            builderAcquire.mMaxLines = Integer.MAX_VALUE;
            builderAcquire.mBreakStrategy = 0;
            builderAcquire.mHyphenationFrequency = 0;
            builderAcquire.mJustificationMode = 0;
            return builderAcquire;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void recycle(Builder builder) {
            builder.mPaint = null;
            builder.mText = null;
            builder.mLeftIndents = null;
            builder.mRightIndents = null;
            sPool.release(builder);
        }

        void finish() {
            this.mText = null;
            this.mPaint = null;
            this.mLeftIndents = null;
            this.mRightIndents = null;
        }

        public Builder setText(CharSequence charSequence) {
            return setText(charSequence, 0, charSequence.length());
        }

        public Builder setText(CharSequence charSequence, int i, int i2) {
            this.mText = charSequence;
            this.mStart = i;
            this.mEnd = i2;
            return this;
        }

        public Builder setPaint(TextPaint textPaint) {
            this.mPaint = textPaint;
            return this;
        }

        public Builder setWidth(int i) {
            this.mWidth = i;
            if (this.mEllipsize == null) {
                this.mEllipsizedWidth = i;
            }
            return this;
        }

        public Builder setAlignment(Layout.Alignment alignment) {
            this.mAlignment = alignment;
            return this;
        }

        public Builder setTextDirection(TextDirectionHeuristic textDirectionHeuristic) {
            this.mTextDir = textDirectionHeuristic;
            return this;
        }

        public Builder setLineSpacing(float f, float f2) {
            this.mSpacingAdd = f;
            this.mSpacingMult = f2;
            return this;
        }

        public Builder setIncludePad(boolean z) {
            this.mIncludePad = z;
            return this;
        }

        public Builder setUseLineSpacingFromFallbacks(boolean z) {
            this.mFallbackLineSpacing = z;
            return this;
        }

        public Builder setEllipsizedWidth(int i) {
            this.mEllipsizedWidth = i;
            return this;
        }

        public Builder setEllipsize(TextUtils.TruncateAt truncateAt) {
            this.mEllipsize = truncateAt;
            return this;
        }

        public Builder setMaxLines(int i) {
            this.mMaxLines = i;
            return this;
        }

        public Builder setBreakStrategy(int i) {
            this.mBreakStrategy = i;
            return this;
        }

        public Builder setHyphenationFrequency(int i) {
            this.mHyphenationFrequency = i;
            return this;
        }

        public Builder setIndents(int[] iArr, int[] iArr2) {
            this.mLeftIndents = iArr;
            this.mRightIndents = iArr2;
            return this;
        }

        public Builder setJustificationMode(int i) {
            this.mJustificationMode = i;
            return this;
        }

        Builder setAddLastLineLineSpacing(boolean z) {
            this.mAddLastLineLineSpacing = z;
            return this;
        }

        public StaticLayout build() {
            StaticLayout staticLayout = new StaticLayout(this);
            recycle(this);
            return staticLayout;
        }
    }

    @Deprecated
    public StaticLayout(CharSequence charSequence, TextPaint textPaint, int i, Layout.Alignment alignment, float f, float f2, boolean z) {
        this(charSequence, 0, charSequence.length(), textPaint, i, alignment, f, f2, z);
    }

    @Deprecated
    public StaticLayout(CharSequence charSequence, int i, int i2, TextPaint textPaint, int i3, Layout.Alignment alignment, float f, float f2, boolean z) {
        this(charSequence, i, i2, textPaint, i3, alignment, f, f2, z, null, 0);
    }

    @Deprecated
    public StaticLayout(CharSequence charSequence, int i, int i2, TextPaint textPaint, int i3, Layout.Alignment alignment, float f, float f2, boolean z, TextUtils.TruncateAt truncateAt, int i4) {
        this(charSequence, i, i2, textPaint, i3, alignment, TextDirectionHeuristics.FIRSTSTRONG_LTR, f, f2, z, truncateAt, i4, Integer.MAX_VALUE);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @Deprecated
    public StaticLayout(CharSequence charSequence, int i, int i2, TextPaint textPaint, int i3, Layout.Alignment alignment, TextDirectionHeuristic textDirectionHeuristic, float f, float f2, boolean z, TextUtils.TruncateAt truncateAt, int i4, int i5) {
        CharSequence ellipsizer;
        CharSequence charSequence2;
        if (truncateAt == null) {
            charSequence2 = charSequence;
        } else {
            if (charSequence instanceof Spanned) {
                ellipsizer = new Layout.SpannedEllipsizer(charSequence);
            } else {
                ellipsizer = new Layout.Ellipsizer(charSequence);
            }
            charSequence2 = ellipsizer;
        }
        super(charSequence2, textPaint, i3, alignment, textDirectionHeuristic, f, f2);
        this.mMaxLineHeight = -1;
        this.mMaximumVisibleLineCount = Integer.MAX_VALUE;
        Builder maxLines = Builder.obtain(charSequence, i, i2, textPaint, i3).setAlignment(alignment).setTextDirection(textDirectionHeuristic).setLineSpacing(f2, f).setIncludePad(z).setEllipsizedWidth(i4).setEllipsize(truncateAt).setMaxLines(i5);
        if (truncateAt != null) {
            Layout.Ellipsizer ellipsizer2 = (Layout.Ellipsizer) getText();
            ellipsizer2.mLayout = this;
            ellipsizer2.mWidth = i4;
            ellipsizer2.mMethod = truncateAt;
            this.mEllipsizedWidth = i4;
            this.mColumns = 7;
        } else {
            this.mColumns = 5;
            this.mEllipsizedWidth = i3;
        }
        this.mLineDirections = (Layout.Directions[]) ArrayUtils.newUnpaddedArray(Layout.Directions.class, 2);
        this.mLines = ArrayUtils.newUnpaddedIntArray(this.mColumns * 2);
        this.mMaximumVisibleLineCount = i5;
        generate(maxLines, maxLines.mIncludePad, maxLines.mIncludePad);
        Builder.recycle(maxLines);
    }

    StaticLayout(CharSequence charSequence) {
        super(charSequence, null, 0, null, 0.0f, 0.0f);
        this.mMaxLineHeight = -1;
        this.mMaximumVisibleLineCount = Integer.MAX_VALUE;
        this.mColumns = 7;
        this.mLineDirections = (Layout.Directions[]) ArrayUtils.newUnpaddedArray(Layout.Directions.class, 2);
        this.mLines = ArrayUtils.newUnpaddedIntArray(this.mColumns * 2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private StaticLayout(Builder builder) {
        CharSequence ellipsizer;
        if (builder.mEllipsize == null) {
            ellipsizer = builder.mText;
        } else if (builder.mText instanceof Spanned) {
            ellipsizer = new Layout.SpannedEllipsizer(builder.mText);
        } else {
            ellipsizer = new Layout.Ellipsizer(builder.mText);
        }
        super(ellipsizer, builder.mPaint, builder.mWidth, builder.mAlignment, builder.mTextDir, builder.mSpacingMult, builder.mSpacingAdd);
        this.mMaxLineHeight = -1;
        this.mMaximumVisibleLineCount = Integer.MAX_VALUE;
        if (builder.mEllipsize != null) {
            Layout.Ellipsizer ellipsizer2 = (Layout.Ellipsizer) getText();
            ellipsizer2.mLayout = this;
            ellipsizer2.mWidth = builder.mEllipsizedWidth;
            ellipsizer2.mMethod = builder.mEllipsize;
            this.mEllipsizedWidth = builder.mEllipsizedWidth;
            this.mColumns = 7;
        } else {
            this.mColumns = 5;
            this.mEllipsizedWidth = builder.mWidth;
        }
        this.mLineDirections = (Layout.Directions[]) ArrayUtils.newUnpaddedArray(Layout.Directions.class, 2);
        this.mLines = ArrayUtils.newUnpaddedIntArray(this.mColumns * 2);
        this.mMaximumVisibleLineCount = builder.mMaxLines;
        this.mLeftIndents = builder.mLeftIndents;
        this.mRightIndents = builder.mRightIndents;
        setJustificationMode(builder.mJustificationMode);
        generate(builder, builder.mIncludePad, builder.mIncludePad);
    }

    /* JADX WARN: Removed duplicated region for block: B:175:0x053d  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0529 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:207:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x022b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void generate(android.text.StaticLayout.Builder r74, boolean r75, boolean r76) {
        /*
            Method dump skipped, instruction units count: 1422
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.StaticLayout.generate(android.text.StaticLayout$Builder, boolean, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0127  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int out(java.lang.CharSequence r29, int r30, int r31, int r32, int r33, int r34, int r35, int r36, float r37, float r38, android.text.style.LineHeightSpan[] r39, int[] r40, android.graphics.Paint.FontMetricsInt r41, boolean r42, int r43, boolean r44, android.text.MeasuredParagraph r45, int r46, boolean r47, boolean r48, boolean r49, char[] r50, int r51, android.text.TextUtils.TruncateAt r52, float r53, float r54, android.text.TextPaint r55, boolean r56) {
        /*
            Method dump skipped, instruction units count: 491
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.StaticLayout.out(java.lang.CharSequence, int, int, int, int, int, int, int, float, float, android.text.style.LineHeightSpan[], int[], android.graphics.Paint$FontMetricsInt, boolean, int, boolean, android.text.MeasuredParagraph, int, boolean, boolean, boolean, char[], int, android.text.TextUtils$TruncateAt, float, float, android.text.TextPaint, boolean):int");
    }

    private void calculateEllipsis(int i, int i2, MeasuredParagraph measuredParagraph, int i3, float f, TextUtils.TruncateAt truncateAt, int i4, float f2, TextPaint textPaint, boolean z) {
        int i5;
        float totalInsets = f - getTotalInsets(i4);
        int i6 = 0;
        if (f2 <= totalInsets && !z) {
            int[] iArr = this.mLines;
            int i7 = this.mColumns;
            iArr[(i7 * i4) + 5] = 0;
            iArr[(i7 * i4) + 6] = 0;
            return;
        }
        float fMeasureText = textPaint.measureText(TextUtils.getEllipsisString(truncateAt));
        int i8 = i2 - i;
        float charWidthAt = 0.0f;
        if (truncateAt == TextUtils.TruncateAt.START) {
            if (this.mMaximumVisibleLineCount == 1) {
                int i9 = i8;
                float charWidthAt2 = 0.0f;
                while (true) {
                    if (i9 <= 0) {
                        break;
                    }
                    charWidthAt2 += measuredParagraph.getCharWidthAt(((i9 - 1) + i) - i3);
                    if (charWidthAt2 + fMeasureText > totalInsets) {
                        while (i9 < i8 && measuredParagraph.getCharWidthAt((i9 + i) - i3) == 0.0f) {
                            i9++;
                        }
                    } else {
                        i9--;
                    }
                }
                i5 = i9;
            } else {
                if (Log.isLoggable(TAG, 5)) {
                    Log.w(TAG, "Start Ellipsis only supported with one line");
                }
                i5 = 0;
            }
        } else if (truncateAt == TextUtils.TruncateAt.END || truncateAt == TextUtils.TruncateAt.MARQUEE || truncateAt == TextUtils.TruncateAt.END_SMALL) {
            while (i6 < i8) {
                charWidthAt += measuredParagraph.getCharWidthAt((i6 + i) - i3);
                if (charWidthAt + fMeasureText > totalInsets) {
                    break;
                } else {
                    i6++;
                }
            }
            i5 = i8 - i6;
            if (z && i5 == 0 && i8 > 0) {
                i6 = i8 - 1;
                i5 = 1;
            }
        } else if (this.mMaximumVisibleLineCount == 1) {
            float f3 = totalInsets - fMeasureText;
            float f4 = f3 / 2.0f;
            int i10 = i8;
            float f5 = 0.0f;
            while (true) {
                if (i10 <= 0) {
                    break;
                }
                float charWidthAt3 = measuredParagraph.getCharWidthAt(((i10 - 1) + i) - i3) + f5;
                if (charWidthAt3 > f4) {
                    while (i10 < i8 && measuredParagraph.getCharWidthAt((i10 + i) - i3) == 0.0f) {
                        i10++;
                    }
                } else {
                    i10--;
                    f5 = charWidthAt3;
                }
            }
            float f6 = f3 - f5;
            while (i6 < i10) {
                charWidthAt += measuredParagraph.getCharWidthAt((i6 + i) - i3);
                if (charWidthAt > f6) {
                    break;
                } else {
                    i6++;
                }
            }
            i5 = i10 - i6;
        } else {
            if (Log.isLoggable(TAG, 5)) {
                Log.w(TAG, "Middle Ellipsis only supported with one line");
            }
            i5 = 0;
        }
        this.mEllipsized = true;
        int[] iArr2 = this.mLines;
        int i11 = this.mColumns;
        iArr2[(i11 * i4) + 5] = i6;
        iArr2[(i11 * i4) + 6] = i5;
    }

    private float getTotalInsets(int i) {
        int[] iArr = this.mLeftIndents;
        int i2 = iArr != null ? iArr[Math.min(i, iArr.length - 1)] : 0;
        int[] iArr2 = this.mRightIndents;
        if (iArr2 != null) {
            i2 += iArr2[Math.min(i, iArr2.length - 1)];
        }
        return i2;
    }

    @Override // android.text.Layout
    public int getLineForVertical(int i) {
        int i2 = this.mLineCount;
        int[] iArr = this.mLines;
        int i3 = -1;
        while (i2 - i3 > 1) {
            int i4 = (i2 + i3) >> 1;
            if (iArr[(this.mColumns * i4) + 1] > i) {
                i2 = i4;
            } else {
                i3 = i4;
            }
        }
        if (i3 < 0) {
            return 0;
        }
        return i3;
    }

    @Override // android.text.Layout
    public int getLineCount() {
        return this.mLineCount;
    }

    @Override // android.text.Layout
    public int getLineTop(int i) {
        return this.mLines[(this.mColumns * i) + 1];
    }

    @Override // android.text.Layout
    public int getLineExtra(int i) {
        return this.mLines[(this.mColumns * i) + 3];
    }

    @Override // android.text.Layout
    public int getLineDescent(int i) {
        return this.mLines[(this.mColumns * i) + 2];
    }

    @Override // android.text.Layout
    public int getLineStart(int i) {
        return this.mLines[(this.mColumns * i) + 0] & 536870911;
    }

    @Override // android.text.Layout
    public int getParagraphDirection(int i) {
        return this.mLines[(this.mColumns * i) + 0] >> 30;
    }

    @Override // android.text.Layout
    public boolean getLineContainsTab(int i) {
        return (this.mLines[(this.mColumns * i) + 0] & 536870912) != 0;
    }

    @Override // android.text.Layout
    public final Layout.Directions getLineDirections(int i) {
        if (i > getLineCount()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mLineDirections[i];
    }

    @Override // android.text.Layout
    public int getTopPadding() {
        return this.mTopPadding;
    }

    @Override // android.text.Layout
    public int getBottomPadding() {
        return this.mBottomPadding;
    }

    @Override // android.text.Layout
    public int getStartHyphenEdit(int i) {
        return unpackStartHyphenEdit(this.mLines[(this.mColumns * i) + 4] & 255);
    }

    @Override // android.text.Layout
    public int getEndHyphenEdit(int i) {
        return unpackEndHyphenEdit(this.mLines[(this.mColumns * i) + 4] & 255);
    }

    @Override // android.text.Layout
    public int getIndentAdjust(int i, Layout.Alignment alignment) {
        if (alignment == Layout.Alignment.ALIGN_LEFT) {
            int[] iArr = this.mLeftIndents;
            if (iArr == null) {
                return 0;
            }
            return iArr[Math.min(i, iArr.length - 1)];
        }
        if (alignment == Layout.Alignment.ALIGN_RIGHT) {
            int[] iArr2 = this.mRightIndents;
            if (iArr2 == null) {
                return 0;
            }
            return -iArr2[Math.min(i, iArr2.length - 1)];
        }
        if (alignment == Layout.Alignment.ALIGN_CENTER) {
            int[] iArr3 = this.mLeftIndents;
            int i2 = iArr3 != null ? iArr3[Math.min(i, iArr3.length - 1)] : 0;
            int[] iArr4 = this.mRightIndents;
            return (i2 - (iArr4 != null ? iArr4[Math.min(i, iArr4.length - 1)] : 0)) >> 1;
        }
        throw new AssertionError("unhandled alignment " + alignment);
    }

    @Override // android.text.Layout
    public int getEllipsisCount(int i) {
        int i2 = this.mColumns;
        if (i2 < 7) {
            return 0;
        }
        return this.mLines[(i2 * i) + 6];
    }

    @Override // android.text.Layout
    public int getEllipsisStart(int i) {
        int i2 = this.mColumns;
        if (i2 < 7) {
            return 0;
        }
        return this.mLines[(i2 * i) + 5];
    }

    @Override // android.text.Layout
    public int getEllipsizedWidth() {
        return this.mEllipsizedWidth;
    }

    @Override // android.text.Layout
    public int getHeight(boolean z) {
        int i;
        if (z && this.mLineCount > this.mMaximumVisibleLineCount && this.mMaxLineHeight == -1 && Log.isLoggable(TAG, 5)) {
            Log.w(TAG, "maxLineHeight should not be -1.  maxLines:" + this.mMaximumVisibleLineCount + " lineCount:" + this.mLineCount);
        }
        return (!z || this.mLineCount <= this.mMaximumVisibleLineCount || (i = this.mMaxLineHeight) == -1) ? super.getHeight() : i;
    }

    static class LineBreaks {
        private static final int INITIAL_SIZE = 16;
        public int[] breaks = new int[16];
        public float[] widths = new float[16];
        public float[] ascents = new float[16];
        public float[] descents = new float[16];
        public int[] flags = new int[16];

        LineBreaks() {
        }
    }
}
