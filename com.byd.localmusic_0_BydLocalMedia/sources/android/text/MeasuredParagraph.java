package android.text;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.text.MeasuredText;
import android.text.AutoGrowArray;
import android.text.Layout;
import android.text.style.MetricAffectingSpan;
import android.text.style.ReplacementSpan;
import android.util.Pools;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class MeasuredParagraph {
    private static final char OBJECT_REPLACEMENT_CHARACTER = 65532;
    private static final Pools.SynchronizedPool<MeasuredParagraph> sPool = new Pools.SynchronizedPool<>(1);
    private Paint.FontMetricsInt mCachedFm;
    private char[] mCopiedBuffer;
    private boolean mLtrWithoutBidi;
    private MeasuredText mMeasuredText;
    private int mParaDir;
    private Spanned mSpanned;
    private int mTextLength;
    private int mTextStart;
    private float mWholeWidth;
    private AutoGrowArray.ByteArray mLevels = new AutoGrowArray.ByteArray();
    private AutoGrowArray.FloatArray mWidths = new AutoGrowArray.FloatArray();
    private AutoGrowArray.IntArray mSpanEndCache = new AutoGrowArray.IntArray(4);
    private AutoGrowArray.IntArray mFontMetrics = new AutoGrowArray.IntArray(16);
    private TextPaint mCachedPaint = new TextPaint();

    private MeasuredParagraph() {
    }

    private static MeasuredParagraph obtain() {
        MeasuredParagraph measuredParagraphAcquire = sPool.acquire();
        return measuredParagraphAcquire != null ? measuredParagraphAcquire : new MeasuredParagraph();
    }

    public void recycle() {
        release();
        sPool.release(this);
    }

    public void release() {
        reset();
        this.mLevels.clearWithReleasingLargeArray();
        this.mWidths.clearWithReleasingLargeArray();
        this.mFontMetrics.clearWithReleasingLargeArray();
        this.mSpanEndCache.clearWithReleasingLargeArray();
    }

    private void reset() {
        this.mSpanned = null;
        this.mCopiedBuffer = null;
        this.mWholeWidth = 0.0f;
        this.mLevels.clear();
        this.mWidths.clear();
        this.mFontMetrics.clear();
        this.mSpanEndCache.clear();
        this.mMeasuredText = null;
    }

    public int getTextLength() {
        return this.mTextLength;
    }

    public char[] getChars() {
        return this.mCopiedBuffer;
    }

    public int getParagraphDir() {
        return this.mParaDir;
    }

    public Layout.Directions getDirections(int i, int i2) {
        if (this.mLtrWithoutBidi) {
            return Layout.DIRS_ALL_LEFT_TO_RIGHT;
        }
        return AndroidBidi.directions(this.mParaDir, this.mLevels.getRawArray(), i, this.mCopiedBuffer, i, i2 - i);
    }

    public float getWholeWidth() {
        return this.mWholeWidth;
    }

    public AutoGrowArray.FloatArray getWidths() {
        return this.mWidths;
    }

    public AutoGrowArray.IntArray getSpanEndCache() {
        return this.mSpanEndCache;
    }

    public AutoGrowArray.IntArray getFontMetrics() {
        return this.mFontMetrics;
    }

    public MeasuredText getMeasuredText() {
        return this.mMeasuredText;
    }

    public float getWidth(int i, int i2) {
        MeasuredText measuredText = this.mMeasuredText;
        if (measuredText == null) {
            float[] rawArray = this.mWidths.getRawArray();
            float f = 0.0f;
            while (i < i2) {
                f += rawArray[i];
                i++;
            }
            return f;
        }
        return measuredText.getWidth(i, i2);
    }

    public void getBounds(int i, int i2, Rect rect) {
        this.mMeasuredText.getBounds(i, i2, rect);
    }

    public float getCharWidthAt(int i) {
        return this.mMeasuredText.getCharWidthAt(i);
    }

    public static MeasuredParagraph buildForBidi(CharSequence charSequence, int i, int i2, TextDirectionHeuristic textDirectionHeuristic, MeasuredParagraph measuredParagraph) {
        if (measuredParagraph == null) {
            measuredParagraph = obtain();
        }
        measuredParagraph.resetAndAnalyzeBidi(charSequence, i, i2, textDirectionHeuristic);
        return measuredParagraph;
    }

    public static MeasuredParagraph buildForMeasurement(TextPaint textPaint, CharSequence charSequence, int i, int i2, TextDirectionHeuristic textDirectionHeuristic, MeasuredParagraph measuredParagraph) {
        if (measuredParagraph == null) {
            measuredParagraph = obtain();
        }
        measuredParagraph.resetAndAnalyzeBidi(charSequence, i, i2, textDirectionHeuristic);
        measuredParagraph.mWidths.resize(measuredParagraph.mTextLength);
        if (measuredParagraph.mTextLength == 0) {
            return measuredParagraph;
        }
        if (measuredParagraph.mSpanned != null) {
            while (true) {
                int i3 = i;
                if (i3 >= i2) {
                    break;
                }
                i = measuredParagraph.mSpanned.nextSpanTransition(i3, i2, MetricAffectingSpan.class);
                measuredParagraph.applyMetricsAffectingSpan(textPaint, (MetricAffectingSpan[]) TextUtils.removeEmptySpans((MetricAffectingSpan[]) measuredParagraph.mSpanned.getSpans(i3, i, MetricAffectingSpan.class), measuredParagraph.mSpanned, MetricAffectingSpan.class), i3, i, null);
            }
        } else {
            measuredParagraph.applyMetricsAffectingSpan(textPaint, null, i, i2, null);
        }
        return measuredParagraph;
    }

    public static MeasuredParagraph buildForStaticLayout(TextPaint textPaint, CharSequence charSequence, int i, int i2, TextDirectionHeuristic textDirectionHeuristic, boolean z, boolean z2, MeasuredParagraph measuredParagraph, MeasuredParagraph measuredParagraph2) {
        MeasuredText.Builder builder;
        if (measuredParagraph2 == null) {
            measuredParagraph2 = obtain();
        }
        measuredParagraph2.resetAndAnalyzeBidi(charSequence, i, i2, textDirectionHeuristic);
        if (measuredParagraph == null) {
            builder = new MeasuredText.Builder(measuredParagraph2.mCopiedBuffer).setComputeHyphenation(z).setComputeLayout(z2);
        } else {
            builder = new MeasuredText.Builder(measuredParagraph.mMeasuredText);
        }
        if (measuredParagraph2.mTextLength == 0) {
            measuredParagraph2.mMeasuredText = builder.build();
        } else {
            if (measuredParagraph2.mSpanned != null) {
                while (true) {
                    int i3 = i;
                    if (i3 >= i2) {
                        break;
                    }
                    i = measuredParagraph2.mSpanned.nextSpanTransition(i3, i2, MetricAffectingSpan.class);
                    measuredParagraph2.applyMetricsAffectingSpan(textPaint, (MetricAffectingSpan[]) TextUtils.removeEmptySpans((MetricAffectingSpan[]) measuredParagraph2.mSpanned.getSpans(i3, i, MetricAffectingSpan.class), measuredParagraph2.mSpanned, MetricAffectingSpan.class), i3, i, builder);
                    measuredParagraph2.mSpanEndCache.append(i);
                }
            } else {
                measuredParagraph2.applyMetricsAffectingSpan(textPaint, null, i, i2, builder);
                measuredParagraph2.mSpanEndCache.append(i2);
            }
            measuredParagraph2.mMeasuredText = builder.build();
        }
        return measuredParagraph2;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void resetAndAnalyzeBidi(java.lang.CharSequence r6, int r7, int r8, android.text.TextDirectionHeuristic r9) {
        /*
            r5 = this;
            r5.reset()
            boolean r0 = r6 instanceof android.text.Spanned
            if (r0 == 0) goto Lb
            r0 = r6
            android.text.Spanned r0 = (android.text.Spanned) r0
            goto Lc
        Lb:
            r0 = 0
        Lc:
            r5.mSpanned = r0
            r5.mTextStart = r7
            int r0 = r8 - r7
            r5.mTextLength = r0
            char[] r1 = r5.mCopiedBuffer
            if (r1 == 0) goto L1b
            int r1 = r1.length
            if (r1 == r0) goto L21
        L1b:
            int r0 = r5.mTextLength
            char[] r0 = new char[r0]
            r5.mCopiedBuffer = r0
        L21:
            char[] r0 = r5.mCopiedBuffer
            r1 = 0
            android.text.TextUtils.getChars(r6, r7, r8, r0, r1)
            android.text.Spanned r6 = r5.mSpanned
            if (r6 == 0) goto L5c
            java.lang.Class<android.text.style.ReplacementSpan> r0 = android.text.style.ReplacementSpan.class
            java.lang.Object[] r6 = r6.getSpans(r7, r8, r0)
            android.text.style.ReplacementSpan[] r6 = (android.text.style.ReplacementSpan[]) r6
            r8 = r1
        L34:
            int r0 = r6.length
            if (r8 >= r0) goto L5c
            android.text.Spanned r0 = r5.mSpanned
            r2 = r6[r8]
            int r0 = r0.getSpanStart(r2)
            int r0 = r0 - r7
            android.text.Spanned r2 = r5.mSpanned
            r3 = r6[r8]
            int r2 = r2.getSpanEnd(r3)
            int r2 = r2 - r7
            if (r0 >= 0) goto L4c
            r0 = r1
        L4c:
            int r3 = r5.mTextLength
            if (r2 <= r3) goto L51
            r2 = r3
        L51:
            char[] r3 = r5.mCopiedBuffer
            r4 = 65532(0xfffc, float:9.183E-41)
            java.util.Arrays.fill(r3, r0, r2, r4)
            int r8 = r8 + 1
            goto L34
        L5c:
            android.text.TextDirectionHeuristic r6 = android.text.TextDirectionHeuristics.LTR
            r7 = 1
            if (r9 == r6) goto L69
            android.text.TextDirectionHeuristic r6 = android.text.TextDirectionHeuristics.FIRSTSTRONG_LTR
            if (r9 == r6) goto L69
            android.text.TextDirectionHeuristic r6 = android.text.TextDirectionHeuristics.ANYRTL_LTR
            if (r9 != r6) goto L7d
        L69:
            char[] r6 = r5.mCopiedBuffer
            int r8 = r5.mTextLength
            boolean r6 = android.text.TextUtils.doesNotNeedBidi(r6, r1, r8)
            if (r6 == 0) goto L7d
            android.text.AutoGrowArray$ByteArray r6 = r5.mLevels
            r6.clear()
            r5.mParaDir = r7
            r5.mLtrWithoutBidi = r7
            goto Lb7
        L7d:
            android.text.TextDirectionHeuristic r6 = android.text.TextDirectionHeuristics.LTR
            r8 = -1
            if (r9 != r6) goto L83
            goto La0
        L83:
            android.text.TextDirectionHeuristic r6 = android.text.TextDirectionHeuristics.RTL
            if (r9 != r6) goto L89
        L87:
            r7 = r8
            goto La0
        L89:
            android.text.TextDirectionHeuristic r6 = android.text.TextDirectionHeuristics.FIRSTSTRONG_LTR
            if (r9 != r6) goto L8f
            r7 = 2
            goto La0
        L8f:
            android.text.TextDirectionHeuristic r6 = android.text.TextDirectionHeuristics.FIRSTSTRONG_RTL
            if (r9 != r6) goto L95
            r7 = -2
            goto La0
        L95:
            char[] r6 = r5.mCopiedBuffer
            int r0 = r5.mTextLength
            boolean r6 = r9.isRtl(r6, r1, r0)
            if (r6 == 0) goto La0
            goto L87
        La0:
            android.text.AutoGrowArray$ByteArray r6 = r5.mLevels
            int r8 = r5.mTextLength
            r6.resize(r8)
            char[] r6 = r5.mCopiedBuffer
            android.text.AutoGrowArray$ByteArray r8 = r5.mLevels
            byte[] r8 = r8.getRawArray()
            int r6 = android.text.AndroidBidi.bidi(r7, r6, r8)
            r5.mParaDir = r6
            r5.mLtrWithoutBidi = r1
        Lb7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.MeasuredParagraph.resetAndAnalyzeBidi(java.lang.CharSequence, int, int, android.text.TextDirectionHeuristic):void");
    }

    private void applyReplacementRun(ReplacementSpan replacementSpan, int i, int i2, MeasuredText.Builder builder) {
        TextPaint textPaint = this.mCachedPaint;
        Spanned spanned = this.mSpanned;
        int i3 = this.mTextStart;
        float size = replacementSpan.getSize(textPaint, spanned, i + i3, i2 + i3, this.mCachedFm);
        if (builder == null) {
            this.mWidths.set(i, size);
            int i4 = i + 1;
            if (i2 > i4) {
                Arrays.fill(this.mWidths.getRawArray(), i4, i2, 0.0f);
            }
            this.mWholeWidth += size;
            return;
        }
        builder.appendReplacementRun(this.mCachedPaint, i2 - i, size);
    }

    private void applyStyleRun(int i, int i2, MeasuredText.Builder builder) {
        if (this.mLtrWithoutBidi) {
            if (builder == null) {
                int i3 = i2 - i;
                this.mWholeWidth += this.mCachedPaint.getTextRunAdvances(this.mCopiedBuffer, i, i3, i, i3, false, this.mWidths.getRawArray(), i);
                return;
            } else {
                builder.appendStyleRun(this.mCachedPaint, i2 - i, false);
                return;
            }
        }
        byte b = this.mLevels.get(i);
        int i4 = i + 1;
        int i5 = i;
        while (true) {
            if (i4 == i2 || this.mLevels.get(i4) != b) {
                boolean z = (b & 1) != 0;
                if (builder == null) {
                    int i6 = i4 - i5;
                    this.mWholeWidth += this.mCachedPaint.getTextRunAdvances(this.mCopiedBuffer, i5, i6, i5, i6, z, this.mWidths.getRawArray(), i5);
                } else {
                    builder.appendStyleRun(this.mCachedPaint, i4 - i5, z);
                }
                if (i4 == i2) {
                    return;
                }
                b = this.mLevels.get(i4);
                i5 = i4;
            }
            i4++;
        }
    }

    private void applyMetricsAffectingSpan(TextPaint textPaint, MetricAffectingSpan[] metricAffectingSpanArr, int i, int i2, MeasuredText.Builder builder) {
        this.mCachedPaint.set(textPaint);
        this.mCachedPaint.baselineShift = 0;
        boolean z = builder != null;
        if (z && this.mCachedFm == null) {
            this.mCachedFm = new Paint.FontMetricsInt();
        }
        ReplacementSpan replacementSpan = null;
        if (metricAffectingSpanArr != null) {
            for (MetricAffectingSpan metricAffectingSpan : metricAffectingSpanArr) {
                if (metricAffectingSpan instanceof ReplacementSpan) {
                    replacementSpan = (ReplacementSpan) metricAffectingSpan;
                } else {
                    metricAffectingSpan.updateMeasureState(this.mCachedPaint);
                }
            }
        }
        int i3 = this.mTextStart;
        int i4 = i - i3;
        int i5 = i2 - i3;
        if (builder != null) {
            this.mCachedPaint.getFontMetricsInt(this.mCachedFm);
        }
        if (replacementSpan != null) {
            applyReplacementRun(replacementSpan, i4, i5, builder);
        } else {
            applyStyleRun(i4, i5, builder);
        }
        if (z) {
            if (this.mCachedPaint.baselineShift < 0) {
                this.mCachedFm.ascent += this.mCachedPaint.baselineShift;
                this.mCachedFm.top += this.mCachedPaint.baselineShift;
            } else {
                this.mCachedFm.descent += this.mCachedPaint.baselineShift;
                this.mCachedFm.bottom += this.mCachedPaint.baselineShift;
            }
            this.mFontMetrics.append(this.mCachedFm.top);
            this.mFontMetrics.append(this.mCachedFm.bottom);
            this.mFontMetrics.append(this.mCachedFm.ascent);
            this.mFontMetrics.append(this.mCachedFm.descent);
        }
    }

    int breakText(int i, boolean z, float f) {
        float[] rawArray = this.mWidths.getRawArray();
        if (z) {
            int i2 = 0;
            while (i2 < i) {
                f -= rawArray[i2];
                if (f < 0.0f) {
                    break;
                }
                i2++;
            }
            while (i2 > 0 && this.mCopiedBuffer[i2 - 1] == ' ') {
                i2--;
            }
            return i2;
        }
        int i3 = i - 1;
        int i4 = i3;
        while (i4 >= 0) {
            f -= rawArray[i4];
            if (f < 0.0f) {
                break;
            }
            i4--;
        }
        while (i4 < i3) {
            int i5 = i4 + 1;
            if (this.mCopiedBuffer[i5] != ' ' && rawArray[i5] != 0.0f) {
                break;
            }
            i4 = i5;
        }
        return (i - i4) - 1;
    }

    float measure(int i, int i2) {
        float[] rawArray = this.mWidths.getRawArray();
        float f = 0.0f;
        while (i < i2) {
            f += rawArray[i];
            i++;
        }
        return f;
    }

    public int getMemoryUsage() {
        return this.mMeasuredText.getMemoryUsage();
    }
}
