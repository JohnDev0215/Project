package android.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextUtils;
import android.text.method.TextKeyListener;
import android.text.style.AlignmentSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineBackgroundSpan;
import android.text.style.ParagraphStyle;
import android.text.style.ReplacementSpan;
import android.text.style.TabStopSpan;
import com.android.internal.util.ArrayUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Layout {
    public static final int BREAK_STRATEGY_BALANCED = 2;
    public static final int BREAK_STRATEGY_HIGH_QUALITY = 1;
    public static final int BREAK_STRATEGY_SIMPLE = 0;
    public static final float DEFAULT_LINESPACING_ADDITION = 0.0f;
    public static final float DEFAULT_LINESPACING_MULTIPLIER = 1.0f;
    public static final int DIR_LEFT_TO_RIGHT = 1;
    static final int DIR_REQUEST_DEFAULT_LTR = 2;
    static final int DIR_REQUEST_DEFAULT_RTL = -2;
    static final int DIR_REQUEST_LTR = 1;
    static final int DIR_REQUEST_RTL = -1;
    public static final int DIR_RIGHT_TO_LEFT = -1;
    public static final int HYPHENATION_FREQUENCY_FULL = 2;
    public static final int HYPHENATION_FREQUENCY_NONE = 0;
    public static final int HYPHENATION_FREQUENCY_NORMAL = 1;
    public static final int JUSTIFICATION_MODE_INTER_WORD = 1;
    public static final int JUSTIFICATION_MODE_NONE = 0;
    static final int RUN_LEVEL_MASK = 63;
    static final int RUN_LEVEL_SHIFT = 26;
    static final int RUN_RTL_FLAG = 67108864;
    private static final float TAB_INCREMENT = 20.0f;
    public static final int TEXT_SELECTION_LAYOUT_LEFT_TO_RIGHT = 1;
    public static final int TEXT_SELECTION_LAYOUT_RIGHT_TO_LEFT = 0;
    private Alignment mAlignment;
    private int mJustificationMode;
    private SpanSet<LineBackgroundSpan> mLineBackgroundSpans;
    private TextPaint mPaint;
    private float mSpacingAdd;
    private float mSpacingMult;
    private boolean mSpannedText;
    private CharSequence mText;
    private TextDirectionHeuristic mTextDir;
    private int mWidth;
    private TextPaint mWorkPaint;
    private static final ParagraphStyle[] NO_PARA_SPANS = (ParagraphStyle[]) ArrayUtils.emptyArray(ParagraphStyle.class);
    private static final Rect sTempRect = new Rect();
    static final int RUN_LENGTH_MASK = 67108863;
    public static final Directions DIRS_ALL_LEFT_TO_RIGHT = new Directions(new int[]{0, RUN_LENGTH_MASK});
    public static final Directions DIRS_ALL_RIGHT_TO_LEFT = new Directions(new int[]{0, 134217727});

    public enum Alignment {
        ALIGN_NORMAL,
        ALIGN_OPPOSITE,
        ALIGN_CENTER,
        ALIGN_LEFT,
        ALIGN_RIGHT
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BreakStrategy {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HyphenationFrequency {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface JustificationMode {
    }

    @FunctionalInterface
    public interface SelectionRectangleConsumer {
        void accept(float f, float f2, float f3, float f4, int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TextSelectionLayout {
    }

    public abstract int getBottomPadding();

    public abstract int getEllipsisCount(int i);

    public abstract int getEllipsisStart(int i);

    public int getEndHyphenEdit(int i) {
        return 0;
    }

    public int getIndentAdjust(int i, Alignment alignment) {
        return 0;
    }

    public abstract boolean getLineContainsTab(int i);

    public abstract int getLineCount();

    public abstract int getLineDescent(int i);

    public abstract Directions getLineDirections(int i);

    public int getLineExtra(int i) {
        return 0;
    }

    public abstract int getLineStart(int i);

    public abstract int getLineTop(int i);

    public abstract int getParagraphDirection(int i);

    public int getStartHyphenEdit(int i) {
        return 0;
    }

    public abstract int getTopPadding();

    public static float getDesiredWidth(CharSequence charSequence, TextPaint textPaint) {
        return getDesiredWidth(charSequence, 0, charSequence.length(), textPaint);
    }

    public static float getDesiredWidth(CharSequence charSequence, int i, int i2, TextPaint textPaint) {
        return getDesiredWidth(charSequence, i, i2, textPaint, TextDirectionHeuristics.FIRSTSTRONG_LTR);
    }

    public static float getDesiredWidth(CharSequence charSequence, int i, int i2, TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic) {
        return getDesiredWidthWithLimit(charSequence, i, i2, textPaint, textDirectionHeuristic, Float.MAX_VALUE);
    }

    public static float getDesiredWidthWithLimit(CharSequence charSequence, int i, int i2, TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, float f) throws Throwable {
        float f2 = 0.0f;
        while (i <= i2) {
            int iIndexOf = TextUtils.indexOf(charSequence, '\n', i, i2);
            if (iIndexOf < 0) {
                iIndexOf = i2;
            }
            float fMeasurePara = measurePara(textPaint, charSequence, i, iIndexOf, textDirectionHeuristic);
            if (fMeasurePara > f) {
                return f;
            }
            if (fMeasurePara > f2) {
                f2 = fMeasurePara;
            }
            i = iIndexOf + 1;
        }
        return f2;
    }

    protected Layout(CharSequence charSequence, TextPaint textPaint, int i, Alignment alignment, float f, float f2) {
        this(charSequence, textPaint, i, alignment, TextDirectionHeuristics.FIRSTSTRONG_LTR, f, f2);
    }

    protected Layout(CharSequence charSequence, TextPaint textPaint, int i, Alignment alignment, TextDirectionHeuristic textDirectionHeuristic, float f, float f2) {
        this.mWorkPaint = new TextPaint();
        this.mAlignment = Alignment.ALIGN_NORMAL;
        if (i < 0) {
            throw new IllegalArgumentException("Layout: " + i + " < 0");
        }
        if (textPaint != null) {
            textPaint.bgColor = 0;
            textPaint.baselineShift = 0;
        }
        this.mText = charSequence;
        this.mPaint = textPaint;
        this.mWidth = i;
        this.mAlignment = alignment;
        this.mSpacingMult = f;
        this.mSpacingAdd = f2;
        this.mSpannedText = charSequence instanceof Spanned;
        this.mTextDir = textDirectionHeuristic;
    }

    protected void setJustificationMode(int i) {
        this.mJustificationMode = i;
    }

    void replaceWith(CharSequence charSequence, TextPaint textPaint, int i, Alignment alignment, float f, float f2) {
        if (i < 0) {
            throw new IllegalArgumentException("Layout: " + i + " < 0");
        }
        this.mText = charSequence;
        this.mPaint = textPaint;
        this.mWidth = i;
        this.mAlignment = alignment;
        this.mSpacingMult = f;
        this.mSpacingAdd = f2;
        this.mSpannedText = charSequence instanceof Spanned;
    }

    public void draw(Canvas canvas) {
        draw(canvas, null, null, 0);
    }

    public void draw(Canvas canvas, Path path, Paint paint, int i) {
        long lineRangeForDraw = getLineRangeForDraw(canvas);
        int iUnpackRangeStartFromLong = TextUtils.unpackRangeStartFromLong(lineRangeForDraw);
        int iUnpackRangeEndFromLong = TextUtils.unpackRangeEndFromLong(lineRangeForDraw);
        if (iUnpackRangeEndFromLong < 0) {
            return;
        }
        drawBackground(canvas, path, paint, i, iUnpackRangeStartFromLong, iUnpackRangeEndFromLong);
        drawText(canvas, iUnpackRangeStartFromLong, iUnpackRangeEndFromLong);
    }

    private boolean isJustificationRequired(int i) {
        int lineEnd;
        return (this.mJustificationMode == 0 || (lineEnd = getLineEnd(i)) >= this.mText.length() || this.mText.charAt(lineEnd - 1) == '\n') ? false : true;
    }

    private float getJustifyWidth(int i) {
        int indentAdjust;
        int indentAdjust2;
        Alignment alignment = this.mAlignment;
        int leadingMargin = this.mWidth;
        int paragraphDirection = getParagraphDirection(i);
        ParagraphStyle[] paragraphStyleArr = NO_PARA_SPANS;
        int i2 = 0;
        if (this.mSpannedText) {
            Spanned spanned = (Spanned) this.mText;
            int lineStart = getLineStart(i);
            boolean z = lineStart == 0 || this.mText.charAt(lineStart + (-1)) == '\n';
            if (z) {
                paragraphStyleArr = (ParagraphStyle[]) getParagraphSpans(spanned, lineStart, spanned.nextSpanTransition(lineStart, this.mText.length(), ParagraphStyle.class), ParagraphStyle.class);
                int length = paragraphStyleArr.length - 1;
                while (true) {
                    if (length < 0) {
                        break;
                    }
                    if (paragraphStyleArr[length] instanceof AlignmentSpan) {
                        alignment = ((AlignmentSpan) paragraphStyleArr[length]).getAlignment();
                        break;
                    }
                    length--;
                }
            }
            int length2 = paragraphStyleArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length2) {
                    break;
                }
                if (paragraphStyleArr[i3] instanceof LeadingMarginSpan.LeadingMarginSpan2) {
                    if (i < getLineForOffset(spanned.getSpanStart(paragraphStyleArr[i3])) + ((LeadingMarginSpan.LeadingMarginSpan2) paragraphStyleArr[i3]).getLeadingMarginLineCount()) {
                        z = true;
                        break;
                    }
                }
                i3++;
            }
            int leadingMargin2 = 0;
            while (i2 < length2) {
                if (paragraphStyleArr[i2] instanceof LeadingMarginSpan) {
                    LeadingMarginSpan leadingMarginSpan = (LeadingMarginSpan) paragraphStyleArr[i2];
                    if (paragraphDirection == -1) {
                        leadingMargin -= leadingMarginSpan.getLeadingMargin(z);
                    } else {
                        leadingMargin2 += leadingMarginSpan.getLeadingMargin(z);
                    }
                }
                i2++;
            }
            i2 = leadingMargin2;
        }
        if (alignment == Alignment.ALIGN_LEFT) {
            alignment = paragraphDirection == 1 ? Alignment.ALIGN_NORMAL : Alignment.ALIGN_OPPOSITE;
        } else if (alignment == Alignment.ALIGN_RIGHT) {
            alignment = paragraphDirection == 1 ? Alignment.ALIGN_OPPOSITE : Alignment.ALIGN_NORMAL;
        }
        if (alignment == Alignment.ALIGN_NORMAL) {
            if (paragraphDirection == 1) {
                indentAdjust = getIndentAdjust(i, Alignment.ALIGN_LEFT);
            } else {
                indentAdjust2 = getIndentAdjust(i, Alignment.ALIGN_RIGHT);
                indentAdjust = -indentAdjust2;
            }
        } else if (alignment != Alignment.ALIGN_OPPOSITE) {
            indentAdjust = getIndentAdjust(i, Alignment.ALIGN_CENTER);
        } else if (paragraphDirection == 1) {
            indentAdjust2 = getIndentAdjust(i, Alignment.ALIGN_RIGHT);
            indentAdjust = -indentAdjust2;
        } else {
            indentAdjust = getIndentAdjust(i, Alignment.ALIGN_LEFT);
        }
        return (leadingMargin - i2) - indentAdjust;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x00ef A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void drawText(android.graphics.Canvas r42, int r43, int r44) {
        /*
            Method dump skipped, instruction units count: 764
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.Layout.drawText(android.graphics.Canvas, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x009f A[LOOP:1: B:32:0x009d->B:33:0x009f, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void drawBackground(android.graphics.Canvas r32, android.graphics.Path r33, android.graphics.Paint r34, int r35, int r36, int r37) {
        /*
            Method dump skipped, instruction units count: 279
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.Layout.drawBackground(android.graphics.Canvas, android.graphics.Path, android.graphics.Paint, int, int, int):void");
    }

    public long getLineRangeForDraw(Canvas canvas) {
        synchronized (sTempRect) {
            if (!canvas.getClipBounds(sTempRect)) {
                return TextUtils.packRangeInLong(0, -1);
            }
            int i = sTempRect.top;
            int i2 = sTempRect.bottom;
            int iMax = Math.max(i, 0);
            int iMin = Math.min(getLineTop(getLineCount()), i2);
            if (iMax >= iMin) {
                return TextUtils.packRangeInLong(0, -1);
            }
            return TextUtils.packRangeInLong(getLineForVertical(iMax), getLineForVertical(iMin));
        }
    }

    private int getLineStartPos(int i, int i2, int i3) {
        int indentAdjust;
        int indentAdjust2;
        Alignment paragraphAlignment = getParagraphAlignment(i);
        int paragraphDirection = getParagraphDirection(i);
        if (paragraphAlignment == Alignment.ALIGN_LEFT) {
            paragraphAlignment = paragraphDirection == 1 ? Alignment.ALIGN_NORMAL : Alignment.ALIGN_OPPOSITE;
        } else if (paragraphAlignment == Alignment.ALIGN_RIGHT) {
            paragraphAlignment = paragraphDirection == 1 ? Alignment.ALIGN_OPPOSITE : Alignment.ALIGN_NORMAL;
        }
        if (paragraphAlignment == Alignment.ALIGN_NORMAL) {
            if (paragraphDirection == 1) {
                indentAdjust = getIndentAdjust(i, Alignment.ALIGN_LEFT);
                return i2 + indentAdjust;
            }
            indentAdjust2 = getIndentAdjust(i, Alignment.ALIGN_RIGHT);
            return i3 + indentAdjust2;
        }
        TabStops tabStops = null;
        if (this.mSpannedText && getLineContainsTab(i)) {
            Spanned spanned = (Spanned) this.mText;
            int lineStart = getLineStart(i);
            TabStopSpan[] tabStopSpanArr = (TabStopSpan[]) getParagraphSpans(spanned, lineStart, spanned.nextSpanTransition(lineStart, spanned.length(), TabStopSpan.class), TabStopSpan.class);
            if (tabStopSpanArr.length > 0) {
                tabStops = new TabStops(TAB_INCREMENT, tabStopSpanArr);
            }
        }
        int lineExtent = (int) getLineExtent(i, tabStops, false);
        if (paragraphAlignment != Alignment.ALIGN_OPPOSITE) {
            return ((i2 + i3) - (lineExtent & (-2))) >> (getIndentAdjust(i, Alignment.ALIGN_CENTER) + 1);
        }
        if (paragraphDirection == 1) {
            i3 -= lineExtent;
            indentAdjust2 = getIndentAdjust(i, Alignment.ALIGN_RIGHT);
            return i3 + indentAdjust2;
        }
        i2 -= lineExtent;
        indentAdjust = getIndentAdjust(i, Alignment.ALIGN_LEFT);
        return i2 + indentAdjust;
    }

    public final CharSequence getText() {
        return this.mText;
    }

    public final TextPaint getPaint() {
        return this.mPaint;
    }

    public final int getWidth() {
        return this.mWidth;
    }

    public int getEllipsizedWidth() {
        return this.mWidth;
    }

    public final void increaseWidthTo(int i) {
        if (i < this.mWidth) {
            throw new RuntimeException("attempted to reduce Layout width");
        }
        this.mWidth = i;
    }

    public int getHeight() {
        return getLineTop(getLineCount());
    }

    public int getHeight(boolean z) {
        return getHeight();
    }

    public final Alignment getAlignment() {
        return this.mAlignment;
    }

    public final float getSpacingMultiplier() {
        return this.mSpacingMult;
    }

    public final float getSpacingAdd() {
        return this.mSpacingAdd;
    }

    public final TextDirectionHeuristic getTextDirectionHeuristic() {
        return this.mTextDir;
    }

    public int getLineBounds(int i, Rect rect) {
        if (rect != null) {
            rect.left = 0;
            rect.top = getLineTop(i);
            rect.right = this.mWidth;
            rect.bottom = getLineTop(i + 1);
        }
        return getLineBaseline(i);
    }

    public boolean isLevelBoundary(int i) {
        int lineForOffset = getLineForOffset(i);
        Directions lineDirections = getLineDirections(lineForOffset);
        if (lineDirections == DIRS_ALL_LEFT_TO_RIGHT || lineDirections == DIRS_ALL_RIGHT_TO_LEFT) {
            return false;
        }
        int[] iArr = lineDirections.mDirections;
        int lineStart = getLineStart(lineForOffset);
        int lineEnd = getLineEnd(lineForOffset);
        if (i == lineStart || i == lineEnd) {
            return ((iArr[(i == lineStart ? 0 : iArr.length + (-2)) + 1] >>> 26) & 63) != (getParagraphDirection(lineForOffset) == 1 ? 0 : 1);
        }
        int i2 = i - lineStart;
        for (int i3 = 0; i3 < iArr.length; i3 += 2) {
            if (i2 == iArr[i3]) {
                return true;
            }
        }
        return false;
    }

    public boolean isRtlCharAt(int i) {
        int lineForOffset = getLineForOffset(i);
        Directions lineDirections = getLineDirections(lineForOffset);
        if (lineDirections == DIRS_ALL_LEFT_TO_RIGHT) {
            return false;
        }
        if (lineDirections == DIRS_ALL_RIGHT_TO_LEFT) {
            return true;
        }
        int[] iArr = lineDirections.mDirections;
        int lineStart = getLineStart(lineForOffset);
        for (int i2 = 0; i2 < iArr.length; i2 += 2) {
            int i3 = iArr[i2] + lineStart;
            int i4 = i2 + 1;
            int i5 = (iArr[i4] & RUN_LENGTH_MASK) + i3;
            if (i >= i3 && i < i5) {
                return (((iArr[i4] >>> 26) & 63) & 1) != 0;
            }
        }
        return false;
    }

    public long getRunRange(int i) {
        int lineForOffset = getLineForOffset(i);
        Directions lineDirections = getLineDirections(lineForOffset);
        if (lineDirections == DIRS_ALL_LEFT_TO_RIGHT || lineDirections == DIRS_ALL_RIGHT_TO_LEFT) {
            return TextUtils.packRangeInLong(0, getLineEnd(lineForOffset));
        }
        int[] iArr = lineDirections.mDirections;
        int lineStart = getLineStart(lineForOffset);
        for (int i2 = 0; i2 < iArr.length; i2 += 2) {
            int i3 = iArr[i2] + lineStart;
            int i4 = (iArr[i2 + 1] & RUN_LENGTH_MASK) + i3;
            if (i >= i3 && i < i4) {
                return TextUtils.packRangeInLong(i3, i4);
            }
        }
        return TextUtils.packRangeInLong(0, getLineEnd(lineForOffset));
    }

    public boolean primaryIsTrailingPrevious(int i) {
        int i2;
        int i3;
        int lineForOffset = getLineForOffset(i);
        int lineStart = getLineStart(lineForOffset);
        int lineEnd = getLineEnd(lineForOffset);
        int[] iArr = getLineDirections(lineForOffset).mDirections;
        int i4 = 0;
        while (true) {
            i2 = -1;
            if (i4 >= iArr.length) {
                i3 = -1;
                break;
            }
            int i5 = iArr[i4] + lineStart;
            int i6 = i4 + 1;
            int i7 = (iArr[i6] & RUN_LENGTH_MASK) + i5;
            if (i7 > lineEnd) {
                i7 = lineEnd;
            }
            if (i < i5 || i >= i7) {
                i4 += 2;
            } else {
                if (i > i5) {
                    return false;
                }
                i3 = (iArr[i6] >>> 26) & 63;
            }
        }
        if (i3 == -1) {
            i3 = getParagraphDirection(lineForOffset) == 1 ? 0 : 1;
        }
        if (i != lineStart) {
            int i8 = i - 1;
            int i9 = 0;
            while (true) {
                if (i9 >= iArr.length) {
                    break;
                }
                int i10 = iArr[i9] + lineStart;
                int i11 = i9 + 1;
                int i12 = (iArr[i11] & RUN_LENGTH_MASK) + i10;
                if (i12 > lineEnd) {
                    i12 = lineEnd;
                }
                if (i8 >= i10 && i8 < i12) {
                    i2 = (iArr[i11] >>> 26) & 63;
                    break;
                }
                i9 += 2;
            }
        } else {
            i2 = getParagraphDirection(lineForOffset) == 1 ? 0 : 1;
        }
        return i2 < i3;
    }

    public boolean[] primaryIsTrailingPreviousAllLineOffsets(int i) {
        byte b;
        int lineStart = getLineStart(i);
        int lineEnd = getLineEnd(i);
        int[] iArr = getLineDirections(i).mDirections;
        int i2 = (lineEnd - lineStart) + 1;
        boolean[] zArr = new boolean[i2];
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < iArr.length; i3 += 2) {
            int i4 = iArr[i3] + lineStart;
            int i5 = i3 + 1;
            int i6 = (iArr[i5] & RUN_LENGTH_MASK) + i4;
            if (i6 > lineEnd) {
                i6 = lineEnd;
            }
            if (i6 != i4) {
                bArr[(i6 - lineStart) - 1] = (byte) ((iArr[i5] >>> 26) & 63);
            }
        }
        for (int i7 = 0; i7 < iArr.length; i7 += 2) {
            int i8 = iArr[i7] + lineStart;
            byte b2 = (byte) ((iArr[i7 + 1] >>> 26) & 63);
            int i9 = i8 - lineStart;
            if (i8 == lineStart) {
                b = getParagraphDirection(i) == 1 ? (byte) 0 : (byte) 1;
            } else {
                b = bArr[i9 - 1];
            }
            zArr[i9] = b2 > b;
        }
        return zArr;
    }

    public float getPrimaryHorizontal(int i) {
        return getPrimaryHorizontal(i, false);
    }

    public float getPrimaryHorizontal(int i, boolean z) {
        return getHorizontal(i, primaryIsTrailingPrevious(i), z);
    }

    public float getSecondaryHorizontal(int i) {
        return getSecondaryHorizontal(i, false);
    }

    public float getSecondaryHorizontal(int i, boolean z) {
        return getHorizontal(i, !primaryIsTrailingPrevious(i), z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getHorizontal(int i, boolean z) {
        return z ? getPrimaryHorizontal(i) : getSecondaryHorizontal(i);
    }

    private float getHorizontal(int i, boolean z, boolean z2) {
        return getHorizontal(i, z, getLineForOffset(i), z2);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float getHorizontal(int r17, boolean r18, int r19, boolean r20) {
        /*
            r16 = this;
            r0 = r16
            r1 = r19
            int r13 = r0.getLineStart(r1)
            int r6 = r0.getLineEnd(r1)
            int r7 = r0.getParagraphDirection(r1)
            boolean r9 = r0.getLineContainsTab(r1)
            android.text.Layout$Directions r8 = r0.getLineDirections(r1)
            r14 = 0
            if (r9 == 0) goto L37
            java.lang.CharSequence r2 = r0.mText
            boolean r3 = r2 instanceof android.text.Spanned
            if (r3 == 0) goto L37
            android.text.Spanned r2 = (android.text.Spanned) r2
            java.lang.Class<android.text.style.TabStopSpan> r3 = android.text.style.TabStopSpan.class
            java.lang.Object[] r2 = getParagraphSpans(r2, r13, r6, r3)
            android.text.style.TabStopSpan[] r2 = (android.text.style.TabStopSpan[]) r2
            int r3 = r2.length
            if (r3 <= 0) goto L37
            android.text.Layout$TabStops r3 = new android.text.Layout$TabStops
            r4 = 1101004800(0x41a00000, float:20.0)
            r3.<init>(r4, r2)
            r10 = r3
            goto L38
        L37:
            r10 = r14
        L38:
            android.text.TextLine r15 = android.text.TextLine.obtain()
            android.text.TextPaint r3 = r0.mPaint
            java.lang.CharSequence r4 = r0.mText
            int r11 = r0.getEllipsisStart(r1)
            int r2 = r0.getEllipsisStart(r1)
            int r5 = r0.getEllipsisCount(r1)
            int r12 = r2 + r5
            r2 = r15
            r5 = r13
            r2.set(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            int r2 = r17 - r13
            r3 = r18
            float r2 = r15.measure(r2, r3, r14)
            android.text.TextLine.recycle(r15)
            if (r20 == 0) goto L68
            int r3 = r0.mWidth
            float r4 = (float) r3
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 <= 0) goto L68
            float r2 = (float) r3
        L68:
            int r3 = r0.getParagraphLeft(r1)
            int r4 = r0.getParagraphRight(r1)
            int r1 = r0.getLineStartPos(r1, r3, r4)
            float r1 = (float) r1
            float r1 = r1 + r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.Layout.getHorizontal(int, boolean, int, boolean):float");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float[] getLineHorizontals(int r17, boolean r18, boolean r19) {
        /*
            r16 = this;
            r0 = r16
            int r12 = r16.getLineStart(r17)
            int r13 = r16.getLineEnd(r17)
            int r6 = r16.getParagraphDirection(r17)
            boolean r8 = r16.getLineContainsTab(r17)
            android.text.Layout$Directions r7 = r16.getLineDirections(r17)
            r14 = 0
            if (r8 == 0) goto L35
            java.lang.CharSequence r1 = r0.mText
            boolean r2 = r1 instanceof android.text.Spanned
            if (r2 == 0) goto L35
            android.text.Spanned r1 = (android.text.Spanned) r1
            java.lang.Class<android.text.style.TabStopSpan> r2 = android.text.style.TabStopSpan.class
            java.lang.Object[] r1 = getParagraphSpans(r1, r12, r13, r2)
            android.text.style.TabStopSpan[] r1 = (android.text.style.TabStopSpan[]) r1
            int r2 = r1.length
            if (r2 <= 0) goto L35
            android.text.Layout$TabStops r2 = new android.text.Layout$TabStops
            r3 = 1101004800(0x41a00000, float:20.0)
            r2.<init>(r3, r1)
            r9 = r2
            goto L36
        L35:
            r9 = r14
        L36:
            android.text.TextLine r15 = android.text.TextLine.obtain()
            android.text.TextPaint r2 = r0.mPaint
            java.lang.CharSequence r3 = r0.mText
            int r10 = r16.getEllipsisStart(r17)
            int r1 = r16.getEllipsisStart(r17)
            int r4 = r16.getEllipsisCount(r17)
            int r11 = r1 + r4
            r1 = r15
            r4 = r12
            r5 = r13
            r1.set(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            boolean[] r1 = r16.primaryIsTrailingPreviousAllLineOffsets(r17)
            r2 = 0
            if (r19 != 0) goto L66
            r3 = r2
        L5a:
            int r4 = r1.length
            if (r3 >= r4) goto L66
            boolean r4 = r1[r3]
            r4 = r4 ^ 1
            r1[r3] = r4
            int r3 = r3 + 1
            goto L5a
        L66:
            float[] r1 = r15.measureAllOffsets(r1, r14)
            android.text.TextLine.recycle(r15)
            if (r18 == 0) goto L82
            r3 = r2
        L70:
            int r4 = r1.length
            if (r3 >= r4) goto L82
            r4 = r1[r3]
            int r5 = r0.mWidth
            float r6 = (float) r5
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 <= 0) goto L7f
            float r4 = (float) r5
            r1[r3] = r4
        L7f:
            int r3 = r3 + 1
            goto L70
        L82:
            int r3 = r16.getParagraphLeft(r17)
            int r4 = r16.getParagraphRight(r17)
            r5 = r17
            int r3 = r0.getLineStartPos(r5, r3, r4)
            int r13 = r13 - r12
            int r13 = r13 + 1
            float[] r4 = new float[r13]
        L95:
            if (r2 >= r13) goto La0
            float r5 = (float) r3
            r6 = r1[r2]
            float r5 = r5 + r6
            r4[r2] = r5
            int r2 = r2 + 1
            goto L95
        La0:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.Layout.getLineHorizontals(int, boolean, boolean):float[]");
    }

    public float getLineLeft(int i) {
        Alignment alignment;
        int paragraphDirection = getParagraphDirection(i);
        Alignment paragraphAlignment = getParagraphAlignment(i);
        if (paragraphAlignment == null) {
            paragraphAlignment = Alignment.ALIGN_CENTER;
        }
        int i2 = AnonymousClass1.$SwitchMap$android$text$Layout$Alignment[paragraphAlignment.ordinal()];
        if (i2 == 1) {
            alignment = paragraphDirection == -1 ? Alignment.ALIGN_RIGHT : Alignment.ALIGN_LEFT;
        } else if (i2 == 2) {
            alignment = paragraphDirection == -1 ? Alignment.ALIGN_LEFT : Alignment.ALIGN_RIGHT;
        } else if (i2 == 3) {
            alignment = Alignment.ALIGN_CENTER;
        } else if (i2 == 4) {
            alignment = Alignment.ALIGN_RIGHT;
        } else {
            alignment = Alignment.ALIGN_LEFT;
        }
        int i3 = AnonymousClass1.$SwitchMap$android$text$Layout$Alignment[alignment.ordinal()];
        if (i3 == 3) {
            return (float) Math.floor(getParagraphLeft(i) + ((this.mWidth - getLineMax(i)) / 2.0f));
        }
        if (i3 != 4) {
            return 0.0f;
        }
        return this.mWidth - getLineMax(i);
    }

    /* JADX INFO: renamed from: android.text.Layout$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$text$Layout$Alignment;

        static {
            int[] iArr = new int[Alignment.values().length];
            $SwitchMap$android$text$Layout$Alignment = iArr;
            try {
                iArr[Alignment.ALIGN_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Alignment.ALIGN_OPPOSITE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Alignment.ALIGN_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Alignment.ALIGN_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Alignment.ALIGN_LEFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public float getLineRight(int i) {
        Alignment alignment;
        int paragraphDirection = getParagraphDirection(i);
        Alignment paragraphAlignment = getParagraphAlignment(i);
        if (paragraphAlignment == null) {
            paragraphAlignment = Alignment.ALIGN_CENTER;
        }
        int i2 = AnonymousClass1.$SwitchMap$android$text$Layout$Alignment[paragraphAlignment.ordinal()];
        if (i2 == 1) {
            alignment = paragraphDirection == -1 ? Alignment.ALIGN_RIGHT : Alignment.ALIGN_LEFT;
        } else if (i2 == 2) {
            alignment = paragraphDirection == -1 ? Alignment.ALIGN_LEFT : Alignment.ALIGN_RIGHT;
        } else if (i2 == 3) {
            alignment = Alignment.ALIGN_CENTER;
        } else if (i2 == 4) {
            alignment = Alignment.ALIGN_RIGHT;
        } else {
            alignment = Alignment.ALIGN_LEFT;
        }
        int i3 = AnonymousClass1.$SwitchMap$android$text$Layout$Alignment[alignment.ordinal()];
        if (i3 == 3) {
            return (float) Math.ceil(getParagraphRight(i) - ((this.mWidth - getLineMax(i)) / 2.0f));
        }
        if (i3 == 4) {
            return this.mWidth;
        }
        return getLineMax(i);
    }

    public float getLineMax(int i) {
        float paragraphLeadingMargin = getParagraphLeadingMargin(i);
        float lineExtent = getLineExtent(i, false);
        if (lineExtent < 0.0f) {
            lineExtent = -lineExtent;
        }
        return paragraphLeadingMargin + lineExtent;
    }

    public float getLineWidth(int i) {
        float paragraphLeadingMargin = getParagraphLeadingMargin(i);
        float lineExtent = getLineExtent(i, true);
        if (lineExtent < 0.0f) {
            lineExtent = -lineExtent;
        }
        return paragraphLeadingMargin + lineExtent;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float getLineExtent(int r13, boolean r14) {
        /*
            r12 = this;
            int r3 = r12.getLineStart(r13)
            if (r14 == 0) goto Lb
            int r14 = r12.getLineEnd(r13)
            goto Lf
        Lb:
            int r14 = r12.getLineVisibleEnd(r13)
        Lf:
            r4 = r14
            boolean r7 = r12.getLineContainsTab(r13)
            r14 = 0
            if (r7 == 0) goto L33
            java.lang.CharSequence r0 = r12.mText
            boolean r1 = r0 instanceof android.text.Spanned
            if (r1 == 0) goto L33
            android.text.Spanned r0 = (android.text.Spanned) r0
            java.lang.Class<android.text.style.TabStopSpan> r1 = android.text.style.TabStopSpan.class
            java.lang.Object[] r0 = getParagraphSpans(r0, r3, r4, r1)
            android.text.style.TabStopSpan[] r0 = (android.text.style.TabStopSpan[]) r0
            int r1 = r0.length
            if (r1 <= 0) goto L33
            android.text.Layout$TabStops r1 = new android.text.Layout$TabStops
            r2 = 1101004800(0x41a00000, float:20.0)
            r1.<init>(r2, r0)
            r8 = r1
            goto L34
        L33:
            r8 = r14
        L34:
            android.text.Layout$Directions r6 = r12.getLineDirections(r13)
            if (r6 != 0) goto L3c
            r13 = 0
            return r13
        L3c:
            int r5 = r12.getParagraphDirection(r13)
            android.text.TextLine r11 = android.text.TextLine.obtain()
            android.text.TextPaint r1 = r12.mWorkPaint
            android.text.TextPaint r0 = r12.mPaint
            r1.set(r0)
            int r0 = r12.getStartHyphenEdit(r13)
            r1.setStartHyphenEdit(r0)
            int r0 = r12.getEndHyphenEdit(r13)
            r1.setEndHyphenEdit(r0)
            java.lang.CharSequence r2 = r12.mText
            int r9 = r12.getEllipsisStart(r13)
            int r0 = r12.getEllipsisStart(r13)
            int r10 = r12.getEllipsisCount(r13)
            int r10 = r10 + r0
            r0 = r11
            r0.set(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            boolean r0 = r12.isJustificationRequired(r13)
            if (r0 == 0) goto L79
            float r13 = r12.getJustifyWidth(r13)
            r11.justify(r13)
        L79:
            float r13 = r11.metrics(r14)
            android.text.TextLine.recycle(r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.Layout.getLineExtent(int, boolean):float");
    }

    private float getLineExtent(int i, TabStops tabStops, boolean z) {
        int lineStart = getLineStart(i);
        int lineEnd = z ? getLineEnd(i) : getLineVisibleEnd(i);
        boolean lineContainsTab = getLineContainsTab(i);
        Directions lineDirections = getLineDirections(i);
        int paragraphDirection = getParagraphDirection(i);
        TextLine textLineObtain = TextLine.obtain();
        TextPaint textPaint = this.mWorkPaint;
        textPaint.set(this.mPaint);
        textPaint.setStartHyphenEdit(getStartHyphenEdit(i));
        textPaint.setEndHyphenEdit(getEndHyphenEdit(i));
        textLineObtain.set(textPaint, this.mText, lineStart, lineEnd, paragraphDirection, lineDirections, lineContainsTab, tabStops, getEllipsisStart(i), getEllipsisStart(i) + getEllipsisCount(i));
        if (isJustificationRequired(i)) {
            textLineObtain.justify(getJustifyWidth(i));
        }
        float fMetrics = textLineObtain.metrics(null);
        TextLine.recycle(textLineObtain);
        return fMetrics;
    }

    public int getLineForVertical(int i) {
        int lineCount = getLineCount();
        int i2 = -1;
        while (lineCount - i2 > 1) {
            int i3 = (lineCount + i2) / 2;
            if (getLineTop(i3) > i) {
                lineCount = i3;
            } else {
                i2 = i3;
            }
        }
        if (i2 < 0) {
            return 0;
        }
        return i2;
    }

    public int getLineForOffset(int i) {
        int lineCount = getLineCount();
        int i2 = -1;
        while (lineCount - i2 > 1) {
            int i3 = (lineCount + i2) / 2;
            if (getLineStart(i3) > i) {
                lineCount = i3;
            } else {
                i2 = i3;
            }
        }
        if (i2 < 0) {
            return 0;
        }
        return i2;
    }

    public int getOffsetForHorizontal(int i, float f) {
        return getOffsetForHorizontal(i, f, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getOffsetForHorizontal(int r19, float r20, boolean r21) {
        /*
            Method dump skipped, instruction units count: 297
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.Layout.getOffsetForHorizontal(int, float, boolean):int");
    }

    private class HorizontalMeasurementProvider {
        private float[] mHorizontals;
        private final int mLine;
        private int mLineStartOffset;
        private final boolean mPrimary;

        HorizontalMeasurementProvider(int i, boolean z) {
            this.mLine = i;
            this.mPrimary = z;
            init();
        }

        private void init() {
            if (Layout.this.getLineDirections(this.mLine) == Layout.DIRS_ALL_LEFT_TO_RIGHT) {
                return;
            }
            this.mHorizontals = Layout.this.getLineHorizontals(this.mLine, false, this.mPrimary);
            this.mLineStartOffset = Layout.this.getLineStart(this.mLine);
        }

        float get(int i) {
            int i2 = i - this.mLineStartOffset;
            float[] fArr = this.mHorizontals;
            if (fArr == null || i2 < 0 || i2 >= fArr.length) {
                return Layout.this.getHorizontal(i, this.mPrimary);
            }
            return fArr[i2];
        }
    }

    public final int getLineEnd(int i) {
        return getLineStart(i + 1);
    }

    public int getLineVisibleEnd(int i) {
        return getLineVisibleEnd(i, getLineStart(i), getLineStart(i + 1));
    }

    private int getLineVisibleEnd(int i, int i2, int i3) {
        CharSequence charSequence = this.mText;
        if (i == getLineCount() - 1) {
            return i3;
        }
        while (i3 > i2) {
            int i4 = i3 - 1;
            char cCharAt = charSequence.charAt(i4);
            if (cCharAt == '\n') {
                return i4;
            }
            if (!TextLine.isLineEndSpace(cCharAt)) {
                break;
            }
            i3--;
        }
        return i3;
    }

    public final int getLineBottom(int i) {
        return getLineTop(i + 1);
    }

    public final int getLineBottomWithoutSpacing(int i) {
        return getLineTop(i + 1) - getLineExtra(i);
    }

    public final int getLineBaseline(int i) {
        return getLineTop(i + 1) - getLineDescent(i);
    }

    public final int getLineAscent(int i) {
        return getLineTop(i) - (getLineTop(i + 1) - getLineDescent(i));
    }

    public int getOffsetToLeftOf(int i) {
        return getOffsetToLeftRightOf(i, true);
    }

    public int getOffsetToRightOf(int i) {
        return getOffsetToLeftRightOf(i, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0051 A[PHI: r4 r5
  0x0051: PHI (r4v1 int) = (r4v0 int), (r4v4 int) binds: [B:20:0x003b, B:22:0x0049] A[DONT_GENERATE, DONT_INLINE]
  0x0051: PHI (r5v1 int) = (r5v0 int), (r5v3 int) binds: [B:20:0x003b, B:22:0x0049] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int getOffsetToLeftRightOf(int r17, boolean r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            int r3 = r16.getLineForOffset(r17)
            int r4 = r0.getLineStart(r3)
            int r5 = r0.getLineEnd(r3)
            int r6 = r0.getParagraphDirection(r3)
            r7 = -1
            r8 = 0
            r9 = 1
            if (r6 != r7) goto L1d
            r7 = r9
            goto L1e
        L1d:
            r7 = r8
        L1e:
            if (r2 != r7) goto L22
            r7 = r9
            goto L23
        L22:
            r7 = r8
        L23:
            if (r7 == 0) goto L32
            if (r1 != r5) goto L3b
            int r7 = r16.getLineCount()
            int r7 = r7 - r9
            if (r3 >= r7) goto L31
            int r3 = r3 + 1
            goto L38
        L31:
            return r1
        L32:
            if (r1 != r4) goto L3b
            if (r3 <= 0) goto L3a
            int r3 = r3 + (-1)
        L38:
            r8 = r9
            goto L3b
        L3a:
            return r1
        L3b:
            if (r8 == 0) goto L51
            int r4 = r0.getLineStart(r3)
            int r5 = r0.getLineEnd(r3)
            int r7 = r0.getParagraphDirection(r3)
            if (r7 == r6) goto L51
            r2 = r2 ^ 1
            r15 = r4
            r8 = r5
            r9 = r7
            goto L54
        L51:
            r15 = r4
            r8 = r5
            r9 = r6
        L54:
            android.text.Layout$Directions r10 = r0.getLineDirections(r3)
            android.text.TextLine r14 = android.text.TextLine.obtain()
            android.text.TextPaint r5 = r0.mPaint
            java.lang.CharSequence r6 = r0.mText
            r11 = 0
            r12 = 0
            int r13 = r0.getEllipsisStart(r3)
            int r4 = r0.getEllipsisStart(r3)
            int r3 = r0.getEllipsisCount(r3)
            int r3 = r3 + r4
            r4 = r14
            r7 = r15
            r0 = r14
            r14 = r3
            r4.set(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            int r1 = r1 - r15
            int r1 = r0.getOffsetToLeftRightOf(r1, r2)
            int r15 = r15 + r1
            android.text.TextLine.recycle(r0)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.Layout.getOffsetToLeftRightOf(int, boolean):int");
    }

    private int getOffsetAtStartOf(int i) {
        char cCharAt;
        if (i == 0) {
            return 0;
        }
        CharSequence charSequence = this.mText;
        char cCharAt2 = charSequence.charAt(i);
        if (cCharAt2 >= 56320 && cCharAt2 <= 57343 && (cCharAt = charSequence.charAt(i - 1)) >= 55296 && cCharAt <= 56319) {
            i--;
        }
        if (this.mSpannedText) {
            Spanned spanned = (Spanned) charSequence;
            ReplacementSpan[] replacementSpanArr = (ReplacementSpan[]) spanned.getSpans(i, i, ReplacementSpan.class);
            for (int i2 = 0; i2 < replacementSpanArr.length; i2++) {
                int spanStart = spanned.getSpanStart(replacementSpanArr[i2]);
                int spanEnd = spanned.getSpanEnd(replacementSpanArr[i2]);
                if (spanStart < i && spanEnd > i) {
                    i = spanStart;
                }
            }
        }
        return i;
    }

    public boolean shouldClampCursor(int i) {
        int i2 = AnonymousClass1.$SwitchMap$android$text$Layout$Alignment[getParagraphAlignment(i).ordinal()];
        return i2 != 1 ? i2 == 5 : getParagraphDirection(i) > 0;
    }

    public void getCursorPath(int i, Path path, CharSequence charSequence) {
        int i2;
        path.reset();
        int lineForOffset = getLineForOffset(i);
        int lineTop = getLineTop(lineForOffset);
        int lineBottomWithoutSpacing = getLineBottomWithoutSpacing(lineForOffset);
        float primaryHorizontal = getPrimaryHorizontal(i, shouldClampCursor(lineForOffset)) - 0.5f;
        int metaState = TextKeyListener.getMetaState(charSequence, 1) | TextKeyListener.getMetaState(charSequence, 2048);
        int metaState2 = TextKeyListener.getMetaState(charSequence, 2);
        if (metaState == 0 && metaState2 == 0) {
            i2 = 0;
        } else {
            i2 = (lineBottomWithoutSpacing - lineTop) >> 2;
            if (metaState2 != 0) {
                lineTop += i2;
            }
            if (metaState != 0) {
                lineBottomWithoutSpacing -= i2;
            }
        }
        if (primaryHorizontal < 0.5f) {
            primaryHorizontal = 0.5f;
        }
        float f = lineTop;
        path.moveTo(primaryHorizontal, f);
        float f2 = lineBottomWithoutSpacing;
        path.lineTo(primaryHorizontal, f2);
        if (metaState == 2) {
            path.moveTo(primaryHorizontal, f2);
            float f3 = i2;
            float f4 = lineBottomWithoutSpacing + i2;
            path.lineTo(primaryHorizontal - f3, f4);
            path.lineTo(primaryHorizontal, f2);
            path.lineTo(f3 + primaryHorizontal, f4);
        } else if (metaState == 1) {
            path.moveTo(primaryHorizontal, f2);
            float f5 = i2;
            float f6 = primaryHorizontal - f5;
            float f7 = lineBottomWithoutSpacing + i2;
            path.lineTo(f6, f7);
            float f8 = f7 - 0.5f;
            path.moveTo(f6, f8);
            float f9 = f5 + primaryHorizontal;
            path.lineTo(f9, f8);
            path.moveTo(f9, f7);
            path.lineTo(primaryHorizontal, f2);
        }
        if (metaState2 == 2) {
            path.moveTo(primaryHorizontal, f);
            float f10 = i2;
            float f11 = lineTop - i2;
            path.lineTo(primaryHorizontal - f10, f11);
            path.lineTo(primaryHorizontal, f);
            path.lineTo(primaryHorizontal + f10, f11);
            return;
        }
        if (metaState2 == 1) {
            path.moveTo(primaryHorizontal, f);
            float f12 = i2;
            float f13 = primaryHorizontal - f12;
            float f14 = lineTop - i2;
            path.lineTo(f13, f14);
            float f15 = 0.5f + f14;
            path.moveTo(f13, f15);
            float f16 = f12 + primaryHorizontal;
            path.lineTo(f16, f15);
            path.moveTo(f16, f14);
            path.lineTo(primaryHorizontal, f);
        }
    }

    private void addSelection(int i, int i2, int i3, int i4, int i5, SelectionRectangleConsumer selectionRectangleConsumer) {
        int iMax;
        int iMin;
        int i6;
        int i7;
        int lineStart = getLineStart(i);
        int lineEnd = getLineEnd(i);
        Directions lineDirections = getLineDirections(i);
        if (lineEnd > lineStart && this.mText.charAt(lineEnd - 1) == '\n') {
            lineEnd--;
        }
        for (int i8 = 0; i8 < lineDirections.mDirections.length; i8 += 2) {
            int i9 = lineDirections.mDirections[i8] + lineStart;
            int i10 = i8 + 1;
            int i11 = (lineDirections.mDirections[i10] & RUN_LENGTH_MASK) + i9;
            if (i11 > lineEnd) {
                i11 = lineEnd;
            }
            if (i2 <= i11 && i3 >= i9 && (iMax = Math.max(i2, i9)) != (iMin = Math.min(i3, i11))) {
                float horizontal = getHorizontal(iMax, false, i, false);
                float horizontal2 = getHorizontal(iMin, true, i, false);
                float fMin = Math.min(horizontal, horizontal2);
                float fMax = Math.max(horizontal, horizontal2);
                if ((lineDirections.mDirections[i10] & 67108864) != 0) {
                    i6 = i4;
                    i7 = 0;
                } else {
                    i6 = i4;
                    i7 = 1;
                }
                selectionRectangleConsumer.accept(fMin, i6, fMax, i5, i7);
            }
        }
    }

    public void getSelectionPath(int i, int i2, final Path path) {
        path.reset();
        getSelection(i, i2, new SelectionRectangleConsumer() { // from class: android.text.-$$Lambda$Layout$MzjK2UE2G8VG0asK8_KWY3gHAmY
            @Override // android.text.Layout.SelectionRectangleConsumer
            public final void accept(float f, float f2, float f3, float f4, int i3) {
                path.addRect(f, f2, f3, f4, Path.Direction.CW);
            }
        });
    }

    public final void getSelection(int i, int i2, SelectionRectangleConsumer selectionRectangleConsumer) {
        int i3;
        int i4;
        float f;
        if (i == i2) {
            return;
        }
        if (i2 < i) {
            i4 = i;
            i3 = i2;
        } else {
            i3 = i;
            i4 = i2;
        }
        int lineForOffset = getLineForOffset(i3);
        int lineForOffset2 = getLineForOffset(i4);
        int lineTop = getLineTop(lineForOffset);
        int lineBottomWithoutSpacing = getLineBottomWithoutSpacing(lineForOffset2);
        if (lineForOffset == lineForOffset2) {
            addSelection(lineForOffset, i3, i4, lineTop, lineBottomWithoutSpacing, selectionRectangleConsumer);
            return;
        }
        float f2 = this.mWidth;
        addSelection(lineForOffset, i3, getLineEnd(lineForOffset), lineTop, getLineBottom(lineForOffset), selectionRectangleConsumer);
        if (getParagraphDirection(lineForOffset) == -1) {
            selectionRectangleConsumer.accept(getLineLeft(lineForOffset), lineTop, 0.0f, getLineBottom(lineForOffset), 0);
            f = f2;
        } else {
            f = f2;
            selectionRectangleConsumer.accept(getLineRight(lineForOffset), lineTop, f2, getLineBottom(lineForOffset), 1);
        }
        while (true) {
            lineForOffset++;
            if (lineForOffset >= lineForOffset2) {
                break;
            }
            int lineTop2 = getLineTop(lineForOffset);
            int lineBottom = getLineBottom(lineForOffset);
            if (getParagraphDirection(lineForOffset) == -1) {
                selectionRectangleConsumer.accept(0.0f, lineTop2, f, lineBottom, 0);
            } else {
                selectionRectangleConsumer.accept(0.0f, lineTop2, f, lineBottom, 1);
            }
        }
        int lineTop3 = getLineTop(lineForOffset2);
        int lineBottomWithoutSpacing2 = getLineBottomWithoutSpacing(lineForOffset2);
        addSelection(lineForOffset2, getLineStart(lineForOffset2), i4, lineTop3, lineBottomWithoutSpacing2, selectionRectangleConsumer);
        if (getParagraphDirection(lineForOffset2) == -1) {
            selectionRectangleConsumer.accept(f, lineTop3, getLineRight(lineForOffset2), lineBottomWithoutSpacing2, 0);
        } else {
            selectionRectangleConsumer.accept(0.0f, lineTop3, getLineLeft(lineForOffset2), lineBottomWithoutSpacing2, 1);
        }
    }

    public final Alignment getParagraphAlignment(int i) {
        AlignmentSpan[] alignmentSpanArr;
        int length;
        Alignment alignment = this.mAlignment;
        return (!this.mSpannedText || (length = (alignmentSpanArr = (AlignmentSpan[]) getParagraphSpans((Spanned) this.mText, getLineStart(i), getLineEnd(i), AlignmentSpan.class)).length) <= 0) ? alignment : alignmentSpanArr[length - 1].getAlignment();
    }

    public final int getParagraphLeft(int i) {
        if (getParagraphDirection(i) == -1 || !this.mSpannedText) {
            return 0;
        }
        return getParagraphLeadingMargin(i);
    }

    public final int getParagraphRight(int i) {
        int i2 = this.mWidth;
        return (getParagraphDirection(i) == 1 || !this.mSpannedText) ? i2 : i2 - getParagraphLeadingMargin(i);
    }

    private int getParagraphLeadingMargin(int i) {
        if (!this.mSpannedText) {
            return 0;
        }
        Spanned spanned = (Spanned) this.mText;
        int lineStart = getLineStart(i);
        LeadingMarginSpan[] leadingMarginSpanArr = (LeadingMarginSpan[]) getParagraphSpans(spanned, lineStart, spanned.nextSpanTransition(lineStart, getLineEnd(i), LeadingMarginSpan.class), LeadingMarginSpan.class);
        if (leadingMarginSpanArr.length == 0) {
            return 0;
        }
        boolean z = lineStart == 0 || spanned.charAt(lineStart - 1) == '\n';
        for (int i2 = 0; i2 < leadingMarginSpanArr.length; i2++) {
            if (leadingMarginSpanArr[i2] instanceof LeadingMarginSpan.LeadingMarginSpan2) {
                z |= i < getLineForOffset(spanned.getSpanStart(leadingMarginSpanArr[i2])) + ((LeadingMarginSpan.LeadingMarginSpan2) leadingMarginSpanArr[i2]).getLeadingMarginLineCount();
            }
        }
        int leadingMargin = 0;
        for (LeadingMarginSpan leadingMarginSpan : leadingMarginSpanArr) {
            leadingMargin += leadingMarginSpan.getLeadingMargin(z);
        }
        return leadingMargin;
    }

    private static float measurePara(TextPaint textPaint, CharSequence charSequence, int i, int i2, TextDirectionHeuristic textDirectionHeuristic) throws Throwable {
        MeasuredParagraph measuredParagraphBuildForBidi;
        int leadingMargin;
        TabStops tabStops;
        boolean z;
        TextLine textLineObtain = TextLine.obtain();
        MeasuredParagraph measuredParagraph = null;
        try {
            measuredParagraphBuildForBidi = MeasuredParagraph.buildForBidi(charSequence, i, i2, textDirectionHeuristic, null);
        } catch (Throwable th) {
            th = th;
        }
        try {
            char[] chars = measuredParagraphBuildForBidi.getChars();
            int length = chars.length;
            Directions directions = measuredParagraphBuildForBidi.getDirections(0, length);
            int paragraphDir = measuredParagraphBuildForBidi.getParagraphDir();
            if (charSequence instanceof Spanned) {
                leadingMargin = 0;
                for (LeadingMarginSpan leadingMarginSpan : (LeadingMarginSpan[]) getParagraphSpans((Spanned) charSequence, i, i2, LeadingMarginSpan.class)) {
                    leadingMargin += leadingMarginSpan.getLeadingMargin(true);
                }
            } else {
                leadingMargin = 0;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    tabStops = null;
                    z = false;
                    break;
                }
                if (chars[i3] != '\t') {
                    i3++;
                } else if (charSequence instanceof Spanned) {
                    Spanned spanned = (Spanned) charSequence;
                    TabStopSpan[] tabStopSpanArr = (TabStopSpan[]) getParagraphSpans(spanned, i, spanned.nextSpanTransition(i, i2, TabStopSpan.class), TabStopSpan.class);
                    z = true;
                    tabStops = tabStopSpanArr.length > 0 ? new TabStops(TAB_INCREMENT, tabStopSpanArr) : null;
                } else {
                    z = true;
                    tabStops = null;
                }
            }
            textLineObtain.set(textPaint, charSequence, i, i2, paragraphDir, directions, z, tabStops, 0, 0);
            float fAbs = leadingMargin + Math.abs(textLineObtain.metrics(null));
            TextLine.recycle(textLineObtain);
            if (measuredParagraphBuildForBidi != null) {
                measuredParagraphBuildForBidi.recycle();
            }
            return fAbs;
        } catch (Throwable th2) {
            th = th2;
            measuredParagraph = measuredParagraphBuildForBidi;
            TextLine.recycle(textLineObtain);
            if (measuredParagraph != null) {
                measuredParagraph.recycle();
            }
            throw th;
        }
    }

    public static class TabStops {
        private float mIncrement;
        private int mNumStops;
        private float[] mStops;

        public static float nextDefaultStop(float f, float f2) {
            return ((int) ((f + f2) / f2)) * f2;
        }

        public TabStops(float f, Object[] objArr) {
            reset(f, objArr);
        }

        void reset(float f, Object[] objArr) {
            this.mIncrement = f;
            int i = 0;
            if (objArr != null) {
                float[] fArr = this.mStops;
                int i2 = 0;
                for (Object obj : objArr) {
                    if (obj instanceof TabStopSpan) {
                        if (fArr == null) {
                            fArr = new float[10];
                        } else if (i2 == fArr.length) {
                            float[] fArr2 = new float[i2 * 2];
                            for (int i3 = 0; i3 < i2; i3++) {
                                fArr2[i3] = fArr[i3];
                            }
                            fArr = fArr2;
                        }
                        fArr[i2] = ((TabStopSpan) r4).getTabStop();
                        i2++;
                    }
                }
                if (i2 > 1) {
                    Arrays.sort(fArr, 0, i2);
                }
                if (fArr != this.mStops) {
                    this.mStops = fArr;
                }
                i = i2;
            }
            this.mNumStops = i;
        }

        float nextTab(float f) {
            int i = this.mNumStops;
            if (i > 0) {
                float[] fArr = this.mStops;
                for (int i2 = 0; i2 < i; i2++) {
                    float f2 = fArr[i2];
                    if (f2 > f) {
                        return f2;
                    }
                }
            }
            return nextDefaultStop(f, this.mIncrement);
        }
    }

    static float nextTab(CharSequence charSequence, int i, int i2, float f, Object[] objArr) {
        boolean z;
        if (charSequence instanceof Spanned) {
            if (objArr == null) {
                objArr = getParagraphSpans((Spanned) charSequence, i, i2, TabStopSpan.class);
                z = true;
            } else {
                z = false;
            }
            float f2 = Float.MAX_VALUE;
            for (int i3 = 0; i3 < objArr.length; i3++) {
                if (z || (objArr[i3] instanceof TabStopSpan)) {
                    float tabStop = ((TabStopSpan) objArr[i3]).getTabStop();
                    if (tabStop < f2 && tabStop > f) {
                        f2 = tabStop;
                    }
                }
            }
            if (f2 != Float.MAX_VALUE) {
                return f2;
            }
        }
        return ((int) ((f + TAB_INCREMENT) / TAB_INCREMENT)) * TAB_INCREMENT;
    }

    protected final boolean isSpanned() {
        return this.mSpannedText;
    }

    static <T> T[] getParagraphSpans(Spanned spanned, int i, int i2, Class<T> cls) {
        if (i == i2 && i > 0) {
            return (T[]) ArrayUtils.emptyArray(cls);
        }
        if (spanned instanceof SpannableStringBuilder) {
            return (T[]) ((SpannableStringBuilder) spanned).getSpans(i, i2, cls, false);
        }
        return (T[]) spanned.getSpans(i, i2, cls);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ellipsize(int i, int i2, int i3, char[] cArr, int i4, TextUtils.TruncateAt truncateAt) {
        int ellipsisCount = getEllipsisCount(i3);
        if (ellipsisCount == 0) {
            return;
        }
        int ellipsisStart = getEllipsisStart(i3);
        int lineStart = getLineStart(i3);
        String ellipsisString = TextUtils.getEllipsisString(truncateAt);
        int length = ellipsisString.length();
        boolean z = ellipsisCount >= length;
        int iMax = Math.max(0, (i - ellipsisStart) - lineStart);
        int iMin = Math.min(ellipsisCount, (i2 - ellipsisStart) - lineStart);
        while (iMax < iMin) {
            cArr[(((iMax + ellipsisStart) + lineStart) + i4) - i] = (!z || iMax >= length) ? (char) 65279 : ellipsisString.charAt(iMax);
            iMax++;
        }
    }

    public static class Directions {
        public int[] mDirections;

        public Directions(int[] iArr) {
            this.mDirections = iArr;
        }

        public int getRunCount() {
            return this.mDirections.length / 2;
        }

        public int getRunStart(int i) {
            return this.mDirections[i * 2];
        }

        public int getRunLength(int i) {
            return this.mDirections[(i * 2) + 1] & Layout.RUN_LENGTH_MASK;
        }

        public boolean isRunRtl(int i) {
            return (this.mDirections[(i * 2) + 1] & 67108864) != 0;
        }
    }

    static class Ellipsizer implements CharSequence, GetChars {
        Layout mLayout;
        TextUtils.TruncateAt mMethod;
        CharSequence mText;
        int mWidth;

        public Ellipsizer(CharSequence charSequence) {
            this.mText = charSequence;
        }

        @Override // java.lang.CharSequence
        public char charAt(int i) {
            char[] cArrObtain = TextUtils.obtain(1);
            getChars(i, i + 1, cArrObtain, 0);
            char c = cArrObtain[0];
            TextUtils.recycle(cArrObtain);
            return c;
        }

        @Override // android.text.GetChars
        public void getChars(int i, int i2, char[] cArr, int i3) {
            int lineForOffset = this.mLayout.getLineForOffset(i2);
            TextUtils.getChars(this.mText, i, i2, cArr, i3);
            for (int lineForOffset2 = this.mLayout.getLineForOffset(i); lineForOffset2 <= lineForOffset; lineForOffset2++) {
                this.mLayout.ellipsize(i, i2, lineForOffset2, cArr, i3, this.mMethod);
            }
        }

        @Override // java.lang.CharSequence
        public int length() {
            return this.mText.length();
        }

        @Override // java.lang.CharSequence
        public CharSequence subSequence(int i, int i2) {
            char[] cArr = new char[i2 - i];
            getChars(i, i2, cArr, 0);
            return new String(cArr);
        }

        @Override // java.lang.CharSequence
        public String toString() {
            char[] cArr = new char[length()];
            getChars(0, length(), cArr, 0);
            return new String(cArr);
        }
    }

    static class SpannedEllipsizer extends Ellipsizer implements Spanned {
        private Spanned mSpanned;

        public SpannedEllipsizer(CharSequence charSequence) {
            super(charSequence);
            this.mSpanned = (Spanned) charSequence;
        }

        @Override // android.text.Spanned
        public <T> T[] getSpans(int i, int i2, Class<T> cls) {
            return (T[]) this.mSpanned.getSpans(i, i2, cls);
        }

        @Override // android.text.Spanned
        public int getSpanStart(Object obj) {
            return this.mSpanned.getSpanStart(obj);
        }

        @Override // android.text.Spanned
        public int getSpanEnd(Object obj) {
            return this.mSpanned.getSpanEnd(obj);
        }

        @Override // android.text.Spanned
        public int getSpanFlags(Object obj) {
            return this.mSpanned.getSpanFlags(obj);
        }

        @Override // android.text.Spanned
        public int nextSpanTransition(int i, int i2, Class cls) {
            return this.mSpanned.nextSpanTransition(i, i2, cls);
        }

        @Override // android.text.Layout.Ellipsizer, java.lang.CharSequence
        public CharSequence subSequence(int i, int i2) {
            char[] cArr = new char[i2 - i];
            getChars(i, i2, cArr, 0);
            SpannableString spannableString = new SpannableString(new String(cArr));
            TextUtils.copySpansFrom(this.mSpanned, i, i2, Object.class, spannableString, 0);
            return spannableString;
        }
    }
}
