package android.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.CharacterStyle;
import android.text.style.MetricAffectingSpan;
import android.text.style.ReplacementSpan;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class TextLine {
    private static final boolean DEBUG = false;
    private static final char TAB_CHAR = '\t';
    private static final int TAB_INCREMENT = 20;
    private static final TextLine[] sCached = new TextLine[3];
    private float mAddedWidthForJustify;
    private char[] mChars;
    private boolean mCharsValid;
    private PrecomputedText mComputed;
    private int mDir;
    private Layout.Directions mDirections;
    private int mEllipsisEnd;
    private int mEllipsisStart;
    private boolean mHasTabs;
    private boolean mIsJustifying;
    private int mLen;
    private TextPaint mPaint;
    private Spanned mSpanned;
    private int mStart;
    private Layout.TabStops mTabs;
    private CharSequence mText;
    private final TextPaint mWorkPaint = new TextPaint();
    private final TextPaint mActivePaint = new TextPaint();
    private final SpanSet<MetricAffectingSpan> mMetricAffectingSpanSpanSet = new SpanSet<>(MetricAffectingSpan.class);
    private final SpanSet<CharacterStyle> mCharacterStyleSpanSet = new SpanSet<>(CharacterStyle.class);
    private final SpanSet<ReplacementSpan> mReplacementSpanSpanSet = new SpanSet<>(ReplacementSpan.class);
    private final DecorationInfo mDecorationInfo = new DecorationInfo();
    private final ArrayList<DecorationInfo> mDecorations = new ArrayList<>();

    private int adjustStartHyphenEdit(int i, int i2) {
        if (i > 0) {
            return 0;
        }
        return i2;
    }

    public static boolean isLineEndSpace(char c) {
        return c == ' ' || c == '\t' || c == 5760 || (8192 <= c && c <= 8202 && c != 8199) || c == 8287 || c == 12288;
    }

    private boolean isStretchableWhitespace(int i) {
        return i == 32;
    }

    public static TextLine obtain() {
        TextLine[] textLineArr;
        TextLine[] textLineArr2 = sCached;
        synchronized (textLineArr2) {
            int length = textLineArr2.length;
            do {
                length--;
                if (length >= 0) {
                    textLineArr = sCached;
                } else {
                    return new TextLine();
                }
            } while (textLineArr[length] == null);
            TextLine textLine = textLineArr[length];
            textLineArr[length] = null;
            return textLine;
        }
    }

    public static TextLine recycle(TextLine textLine) {
        textLine.mText = null;
        textLine.mPaint = null;
        textLine.mDirections = null;
        textLine.mSpanned = null;
        textLine.mTabs = null;
        textLine.mChars = null;
        textLine.mComputed = null;
        textLine.mMetricAffectingSpanSpanSet.recycle();
        textLine.mCharacterStyleSpanSet.recycle();
        textLine.mReplacementSpanSpanSet.recycle();
        synchronized (sCached) {
            int i = 0;
            while (true) {
                TextLine[] textLineArr = sCached;
                if (i >= textLineArr.length) {
                    break;
                }
                if (textLineArr[i] == null) {
                    textLineArr[i] = textLine;
                    break;
                }
                i++;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void set(android.text.TextPaint r2, java.lang.CharSequence r3, int r4, int r5, int r6, android.text.Layout.Directions r7, boolean r8, android.text.Layout.TabStops r9, int r10, int r11) {
        /*
            r1 = this;
            r1.mPaint = r2
            r1.mText = r3
            r1.mStart = r4
            int r0 = r5 - r4
            r1.mLen = r0
            r1.mDir = r6
            r1.mDirections = r7
            if (r7 == 0) goto La8
            r1.mHasTabs = r8
            r6 = 0
            r1.mSpanned = r6
            boolean r7 = r3 instanceof android.text.Spanned
            r8 = 0
            if (r7 == 0) goto L2c
            r7 = r3
            android.text.Spanned r7 = (android.text.Spanned) r7
            r1.mSpanned = r7
            android.text.SpanSet<android.text.style.ReplacementSpan> r0 = r1.mReplacementSpanSpanSet
            r0.init(r7, r4, r5)
            android.text.SpanSet<android.text.style.ReplacementSpan> r7 = r1.mReplacementSpanSpanSet
            int r7 = r7.numberOfSpans
            if (r7 <= 0) goto L2c
            r7 = 1
            goto L2d
        L2c:
            r7 = r8
        L2d:
            r1.mComputed = r6
            boolean r0 = r3 instanceof android.text.PrecomputedText
            if (r0 == 0) goto L48
            r0 = r3
            android.text.PrecomputedText r0 = (android.text.PrecomputedText) r0
            r1.mComputed = r0
            android.text.PrecomputedText$Params r0 = r0.getParams()
            android.text.TextPaint r0 = r0.getTextPaint()
            boolean r2 = r0.equalsForTextMeasurement(r2)
            if (r2 != 0) goto L48
            r1.mComputed = r6
        L48:
            r1.mCharsValid = r7
            if (r7 == 0) goto L93
            char[] r2 = r1.mChars
            if (r2 == 0) goto L55
            int r2 = r2.length
            int r6 = r1.mLen
            if (r2 >= r6) goto L5d
        L55:
            int r2 = r1.mLen
            char[] r2 = com.android.internal.util.ArrayUtils.newUnpaddedCharArray(r2)
            r1.mChars = r2
        L5d:
            char[] r2 = r1.mChars
            android.text.TextUtils.getChars(r3, r4, r5, r2, r8)
            if (r7 == 0) goto L93
            char[] r2 = r1.mChars
            r3 = r4
        L67:
            if (r3 >= r5) goto L93
            android.text.SpanSet<android.text.style.ReplacementSpan> r6 = r1.mReplacementSpanSpanSet
            int r6 = r6.getNextTransition(r3, r5)
            android.text.SpanSet<android.text.style.ReplacementSpan> r7 = r1.mReplacementSpanSpanSet
            boolean r7 = r7.hasSpansIntersecting(r3, r6)
            if (r7 == 0) goto L91
            int r3 = r3 - r4
            if (r3 >= r11) goto L7e
            int r7 = r6 - r4
            if (r7 > r10) goto L91
        L7e:
            r7 = 65532(0xfffc, float:9.183E-41)
            r2[r3] = r7
            int r3 = r3 + 1
            int r7 = r6 - r4
        L87:
            if (r3 >= r7) goto L91
            r0 = 65279(0xfeff, float:9.1475E-41)
            r2[r3] = r0
            int r3 = r3 + 1
            goto L87
        L91:
            r3 = r6
            goto L67
        L93:
            r1.mTabs = r9
            r2 = 0
            r1.mAddedWidthForJustify = r2
            r1.mIsJustifying = r8
            if (r10 == r11) goto L9e
            r2 = r10
            goto L9f
        L9e:
            r2 = r8
        L9f:
            r1.mEllipsisStart = r2
            if (r10 == r11) goto La4
            goto La5
        La4:
            r11 = r8
        La5:
            r1.mEllipsisEnd = r11
            return
        La8:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Directions cannot be null"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.TextLine.set(android.text.TextPaint, java.lang.CharSequence, int, int, int, android.text.Layout$Directions, boolean, android.text.Layout$TabStops, int, int):void");
    }

    private char charAt(int i) {
        return this.mCharsValid ? this.mChars[i] : this.mText.charAt(i + this.mStart);
    }

    public void justify(float f) {
        int i = this.mLen;
        while (i > 0 && isLineEndSpace(this.mText.charAt((this.mStart + i) - 1))) {
            i--;
        }
        int iCountStretchableSpaces = countStretchableSpaces(0, i);
        if (iCountStretchableSpaces == 0) {
            return;
        }
        this.mAddedWidthForJustify = (f - Math.abs(measure(i, false, null))) / iCountStretchableSpaces;
        this.mIsJustifying = true;
    }

    void draw(Canvas canvas, float f, int i, int i2, int i3) {
        int i4;
        int runCount = this.mDirections.getRunCount();
        float f2 = 0.0f;
        int i5 = 0;
        while (i5 < runCount) {
            int runStart = this.mDirections.getRunStart(i5);
            if (runStart > this.mLen) {
                return;
            }
            int iMin = Math.min(this.mDirections.getRunLength(i5) + runStart, this.mLen);
            boolean zIsRunRtl = this.mDirections.isRunRtl(i5);
            float fDrawRun = f2;
            int i6 = this.mHasTabs ? runStart : iMin;
            int i7 = runStart;
            while (i6 <= iMin) {
                if (i6 == iMin || charAt(i6) == '\t') {
                    i4 = i6;
                    fDrawRun += drawRun(canvas, i7, i6, zIsRunRtl, f + fDrawRun, i, i2, i3, (i5 == runCount + (-1) && i6 == this.mLen) ? false : true);
                    if (i4 != iMin) {
                        int i8 = this.mDir;
                        fDrawRun = i8 * nextTab(i8 * fDrawRun);
                    }
                    i7 = i4 + 1;
                } else {
                    i4 = i6;
                }
                i6 = i4 + 1;
            }
            i5++;
            f2 = fDrawRun;
        }
    }

    public float metrics(Paint.FontMetricsInt fontMetricsInt) {
        return measure(this.mLen, false, fontMetricsInt);
    }

    public float measure(int i, boolean z, Paint.FontMetricsInt fontMetricsInt) {
        int runStart;
        float fMeasureRun;
        if (i > this.mLen) {
            throw new IndexOutOfBoundsException("offset(" + i + ") should be less than line limit(" + this.mLen + ")");
        }
        int i2 = z ? i - 1 : i;
        float f = 0.0f;
        if (i2 < 0) {
            return 0.0f;
        }
        int i3 = 0;
        while (i3 < this.mDirections.getRunCount() && (runStart = this.mDirections.getRunStart(i3)) <= this.mLen) {
            int iMin = Math.min(this.mDirections.getRunLength(i3) + runStart, this.mLen);
            boolean zIsRunRtl = this.mDirections.isRunRtl(i3);
            float f2 = f;
            int i4 = runStart;
            int i5 = this.mHasTabs ? runStart : iMin;
            while (i5 <= iMin) {
                if (i5 == iMin || charAt(i5) == '\t') {
                    boolean z2 = i2 >= i4 && i2 < i5;
                    boolean z3 = (this.mDir == -1) == zIsRunRtl;
                    if (z2 && z3) {
                        fMeasureRun = measureRun(i4, i, i5, zIsRunRtl, fontMetricsInt);
                    } else {
                        float fMeasureRun2 = measureRun(i4, i5, i5, zIsRunRtl, fontMetricsInt);
                        if (!z3) {
                            fMeasureRun2 = -fMeasureRun2;
                        }
                        f2 += fMeasureRun2;
                        if (z2) {
                            fMeasureRun = measureRun(i4, i, i5, zIsRunRtl, null);
                        } else {
                            if (i5 != iMin) {
                                if (i == i5) {
                                    return f2;
                                }
                                int i6 = this.mDir;
                                float fNextTab = i6 * nextTab(f2 * i6);
                                if (i2 == i5) {
                                    return fNextTab;
                                }
                                f2 = fNextTab;
                            }
                            i4 = i5 + 1;
                        }
                    }
                    return f2 + fMeasureRun;
                }
                i5++;
            }
            i3++;
            f = f2;
        }
        return f;
    }

    public float[] measureAllOffsets(boolean[] zArr, Paint.FontMetricsInt fontMetricsInt) {
        int runStart;
        int i;
        int i2;
        int i3 = this.mLen;
        float[] fArr = new float[i3 + 1];
        boolean z = true;
        int i4 = i3 + 1;
        int[] iArr = new int[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            iArr[i5] = zArr[i5] ? i5 - 1 : i5;
        }
        float f = 0.0f;
        if (iArr[0] < 0) {
            fArr[0] = 0.0f;
        }
        int i6 = 0;
        while (i6 < this.mDirections.getRunCount() && (runStart = this.mDirections.getRunStart(i6)) <= this.mLen) {
            int iMin = Math.min(this.mDirections.getRunLength(i6) + runStart, this.mLen);
            boolean zIsRunRtl = this.mDirections.isRunRtl(i6);
            int i7 = runStart;
            float f2 = f;
            int i8 = this.mHasTabs ? runStart : iMin;
            while (i8 <= iMin) {
                if (i8 == iMin || charAt(i8) == '\t') {
                    boolean z2 = (this.mDir == -1 ? z : false) == zIsRunRtl ? z : false;
                    i = i8;
                    float fMeasureRun = measureRun(i7, i8, i8, zIsRunRtl, fontMetricsInt);
                    if (!z2) {
                        fMeasureRun = -fMeasureRun;
                    }
                    float f3 = f2 + fMeasureRun;
                    if (!z2) {
                        f2 = f3;
                    }
                    Paint.FontMetricsInt fontMetricsInt2 = z2 ? fontMetricsInt : null;
                    int i9 = i7;
                    while (i9 <= i && i9 <= this.mLen) {
                        if (iArr[i9] < i7 || iArr[i9] >= i) {
                            i2 = i9;
                        } else {
                            i2 = i9;
                            fArr[i2] = measureRun(i7, i9, i, zIsRunRtl, fontMetricsInt2) + f2;
                        }
                        i9 = i2 + 1;
                    }
                    if (i != iMin) {
                        if (iArr[i] == i) {
                            fArr[i] = f3;
                        }
                        int i10 = this.mDir;
                        float fNextTab = i10 * nextTab(i10 * f3);
                        int i11 = i + 1;
                        if (iArr[i11] == i) {
                            fArr[i11] = fNextTab;
                        }
                        f2 = fNextTab;
                    } else {
                        f2 = f3;
                    }
                    i7 = i + 1;
                } else {
                    i = i8;
                }
                i8 = i + 1;
                z = true;
            }
            i6++;
            f = f2;
            z = true;
        }
        int i12 = this.mLen;
        if (iArr[i12] == i12) {
            fArr[i12] = f;
        }
        return fArr;
    }

    private float drawRun(Canvas canvas, int i, int i2, boolean z, float f, int i3, int i4, int i5, boolean z2) {
        if ((this.mDir == 1) == z) {
            float f2 = -measureRun(i, i2, i2, z, null);
            handleRun(i, i2, i2, z, canvas, f + f2, i3, i4, i5, null, false);
            return f2;
        }
        return handleRun(i, i2, i2, z, canvas, f, i3, i4, i5, null, z2);
    }

    private float measureRun(int i, int i2, int i3, boolean z, Paint.FontMetricsInt fontMetricsInt) {
        return handleRun(i, i2, i3, z, null, 0.0f, 0, 0, 0, fontMetricsInt, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x012b, code lost:
    
        if (r10 != (-1)) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x012d, code lost:
    
        if (r1 == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0135, code lost:
    
        if (r10 > r9) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0137, code lost:
    
        if (r1 == false) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x013a, code lost:
    
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x013c, code lost:
    
        return r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:?, code lost:
    
        return r22.mLen + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:?, code lost:
    
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x012a, code lost:
    
        r0 = -1;
     */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    int getOffsetToLeftRightOf(int r23, boolean r24) {
        /*
            Method dump skipped, instruction units count: 317
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.TextLine.getOffsetToLeftRightOf(int, boolean):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int getOffsetBeforeAfter(int r9, int r10, int r11, boolean r12, int r13, boolean r14) {
        /*
            r8 = this;
            if (r9 < 0) goto L99
            r9 = 0
            if (r14 == 0) goto L8
            int r0 = r8.mLen
            goto L9
        L8:
            r0 = r9
        L9:
            if (r13 != r0) goto Ld
            goto L99
        Ld:
            android.text.TextPaint r1 = r8.mWorkPaint
            android.text.TextPaint r0 = r8.mPaint
            r1.set(r0)
            boolean r0 = r8.mIsJustifying
            if (r0 == 0) goto L1d
            float r0 = r8.mAddedWidthForJustify
            r1.setWordSpacing(r0)
        L1d:
            android.text.Spanned r0 = r8.mSpanned
            if (r0 != 0) goto L23
        L21:
            r3 = r10
            goto L71
        L23:
            if (r14 == 0) goto L28
            int r0 = r13 + 1
            goto L29
        L28:
            r0 = r13
        L29:
            int r2 = r8.mStart
            int r2 = r2 + r11
        L2c:
            android.text.Spanned r11 = r8.mSpanned
            int r3 = r8.mStart
            int r3 = r3 + r10
            java.lang.Class<android.text.style.MetricAffectingSpan> r4 = android.text.style.MetricAffectingSpan.class
            int r11 = r11.nextSpanTransition(r3, r2, r4)
            int r3 = r8.mStart
            int r11 = r11 - r3
            if (r11 < r0) goto L97
            android.text.Spanned r0 = r8.mSpanned
            int r2 = r3 + r10
            int r3 = r3 + r11
            java.lang.Class<android.text.style.MetricAffectingSpan> r4 = android.text.style.MetricAffectingSpan.class
            java.lang.Object[] r0 = r0.getSpans(r2, r3, r4)
            android.text.style.MetricAffectingSpan[] r0 = (android.text.style.MetricAffectingSpan[]) r0
            android.text.Spanned r2 = r8.mSpanned
            java.lang.Class<android.text.style.MetricAffectingSpan> r3 = android.text.style.MetricAffectingSpan.class
            java.lang.Object[] r0 = android.text.TextUtils.removeEmptySpans(r0, r2, r3)
            android.text.style.MetricAffectingSpan[] r0 = (android.text.style.MetricAffectingSpan[]) r0
            int r2 = r0.length
            if (r2 <= 0) goto L21
            r2 = 0
            r3 = r9
        L58:
            int r4 = r0.length
            if (r3 >= r4) goto L6b
            r4 = r0[r3]
            boolean r5 = r4 instanceof android.text.style.ReplacementSpan
            if (r5 == 0) goto L65
            android.text.style.ReplacementSpan r4 = (android.text.style.ReplacementSpan) r4
            r2 = r4
            goto L68
        L65:
            r4.updateMeasureState(r1)
        L68:
            int r3 = r3 + 1
            goto L58
        L6b:
            if (r2 == 0) goto L21
            if (r14 == 0) goto L70
            r10 = r11
        L70:
            return r10
        L71:
            if (r14 == 0) goto L74
            goto L75
        L74:
            r9 = 2
        L75:
            r7 = r9
            boolean r9 = r8.mCharsValid
            if (r9 == 0) goto L85
            char[] r2 = r8.mChars
            int r4 = r11 - r3
            r5 = r12
            r6 = r13
            int r8 = r1.getTextRunCursor(r2, r3, r4, r5, r6, r7)
            return r8
        L85:
            java.lang.CharSequence r2 = r8.mText
            int r9 = r8.mStart
            int r3 = r3 + r9
            int r4 = r9 + r11
            int r6 = r9 + r13
            r5 = r12
            int r9 = r1.getTextRunCursor(r2, r3, r4, r5, r6, r7)
            int r8 = r8.mStart
        L95:
            int r9 = r9 - r8
            return r9
        L97:
            r10 = r11
            goto L2c
        L99:
            if (r14 == 0) goto La7
            java.lang.CharSequence r9 = r8.mText
            int r10 = r8.mStart
            int r13 = r13 + r10
            int r9 = android.text.TextUtils.getOffsetAfter(r9, r13)
            int r8 = r8.mStart
            goto L95
        La7:
            java.lang.CharSequence r9 = r8.mText
            int r10 = r8.mStart
            int r13 = r13 + r10
            int r9 = android.text.TextUtils.getOffsetBefore(r9, r13)
            int r8 = r8.mStart
            goto L95
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.TextLine.getOffsetBeforeAfter(int, int, int, boolean, int, boolean):int");
    }

    private static void expandMetricsFromPaint(Paint.FontMetricsInt fontMetricsInt, TextPaint textPaint) {
        int i = fontMetricsInt.top;
        int i2 = fontMetricsInt.ascent;
        int i3 = fontMetricsInt.descent;
        int i4 = fontMetricsInt.bottom;
        int i5 = fontMetricsInt.leading;
        textPaint.getFontMetricsInt(fontMetricsInt);
        updateMetrics(fontMetricsInt, i, i2, i3, i4, i5);
    }

    static void updateMetrics(Paint.FontMetricsInt fontMetricsInt, int i, int i2, int i3, int i4, int i5) {
        fontMetricsInt.top = Math.min(fontMetricsInt.top, i);
        fontMetricsInt.ascent = Math.min(fontMetricsInt.ascent, i2);
        fontMetricsInt.descent = Math.max(fontMetricsInt.descent, i3);
        fontMetricsInt.bottom = Math.max(fontMetricsInt.bottom, i4);
        fontMetricsInt.leading = Math.max(fontMetricsInt.leading, i5);
    }

    private static void drawStroke(TextPaint textPaint, Canvas canvas, int i, float f, float f2, float f3, float f4, float f5) {
        float f6 = f5 + textPaint.baselineShift + f;
        int color = textPaint.getColor();
        Paint.Style style = textPaint.getStyle();
        boolean zIsAntiAlias = textPaint.isAntiAlias();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setColor(i);
        canvas.drawRect(f3, f6, f4, f6 + f2, textPaint);
        textPaint.setStyle(style);
        textPaint.setColor(color);
        textPaint.setAntiAlias(zIsAntiAlias);
    }

    private float getRunAdvance(TextPaint textPaint, int i, int i2, int i3, int i4, boolean z, int i5) {
        if (this.mCharsValid) {
            return textPaint.getRunAdvance(this.mChars, i, i2, i3, i4, z, i5);
        }
        int i6 = this.mStart;
        PrecomputedText precomputedText = this.mComputed;
        if (precomputedText == null) {
            return textPaint.getRunAdvance(this.mText, i6 + i, i6 + i2, i6 + i3, i6 + i4, z, i6 + i5);
        }
        return precomputedText.getWidth(i + i6, i6 + i2);
    }

    private float handleText(TextPaint textPaint, int i, int i2, int i3, int i4, boolean z, Canvas canvas, float f, int i5, int i6, int i7, Paint.FontMetricsInt fontMetricsInt, boolean z2, int i8, ArrayList<DecorationInfo> arrayList) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        int i9;
        float f7;
        if (this.mIsJustifying) {
            textPaint.setWordSpacing(this.mAddedWidthForJustify);
        }
        if (fontMetricsInt != null) {
            expandMetricsFromPaint(fontMetricsInt, textPaint);
        }
        float runAdvance = 0.0f;
        if (i2 == i) {
            return 0.0f;
        }
        int size = arrayList == null ? 0 : arrayList.size();
        if (z2 || (canvas != null && (textPaint.bgColor != 0 || size != 0 || z))) {
            runAdvance = getRunAdvance(textPaint, i, i2, i3, i4, z, i8);
        }
        float f8 = runAdvance;
        if (canvas != null) {
            if (z) {
                f4 = f;
                f3 = f - f8;
            } else {
                f3 = f;
                f4 = f + f8;
            }
            if (textPaint.bgColor != 0) {
                int color = textPaint.getColor();
                Paint.Style style = textPaint.getStyle();
                textPaint.setColor(textPaint.bgColor);
                textPaint.setStyle(Paint.Style.FILL);
                canvas.drawRect(f3, i5, f4, i7, textPaint);
                textPaint.setStyle(style);
                textPaint.setColor(color);
            }
            f2 = f8;
            int i10 = size;
            drawTextRun(canvas, textPaint, i, i2, i3, i4, z, f3, i6 + textPaint.baselineShift);
            if (i10 != 0) {
                int i11 = 0;
                while (i11 < i10) {
                    DecorationInfo decorationInfo = arrayList.get(i11);
                    int iMax = Math.max(decorationInfo.start, i);
                    int iMin = Math.min(decorationInfo.end, i8);
                    float runAdvance2 = getRunAdvance(textPaint, i, i2, i3, i4, z, iMax);
                    float runAdvance3 = getRunAdvance(textPaint, i, i2, i3, i4, z, iMin);
                    if (z) {
                        float f9 = f4 - runAdvance2;
                        f5 = f4 - runAdvance3;
                        f6 = f9;
                    } else {
                        f5 = f3 + runAdvance2;
                        f6 = f3 + runAdvance3;
                    }
                    if (decorationInfo.underlineColor != 0) {
                        drawStroke(textPaint, canvas, decorationInfo.underlineColor, textPaint.getUnderlinePosition(), decorationInfo.underlineThickness, f5, f6, i6);
                    }
                    if (decorationInfo.isUnderlineText) {
                        i9 = i10;
                        f7 = 1.0f;
                        drawStroke(textPaint, canvas, textPaint.getColor(), textPaint.getUnderlinePosition(), Math.max(textPaint.getUnderlineThickness(), 1.0f), f5, f6, i6);
                    } else {
                        i9 = i10;
                        f7 = 1.0f;
                    }
                    if (decorationInfo.isStrikeThruText) {
                        drawStroke(textPaint, canvas, textPaint.getColor(), textPaint.getStrikeThruPosition(), Math.max(textPaint.getStrikeThruThickness(), f7), f5, f6, i6);
                    }
                    i11++;
                    i10 = i9;
                }
            }
        } else {
            f2 = f8;
        }
        return z ? -f2 : f2;
    }

    private float handleReplacement(ReplacementSpan replacementSpan, TextPaint textPaint, int i, int i2, boolean z, Canvas canvas, float f, int i3, int i4, int i5, Paint.FontMetricsInt fontMetricsInt, boolean z2) {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        float f2;
        float f3;
        int i11 = this.mStart;
        int i12 = i11 + i;
        int i13 = i11 + i2;
        if (z2 || (canvas != null && z)) {
            boolean z3 = fontMetricsInt != null;
            if (z3) {
                int i14 = fontMetricsInt.top;
                i6 = i14;
                i7 = fontMetricsInt.ascent;
                i8 = fontMetricsInt.descent;
                i9 = fontMetricsInt.bottom;
                i10 = fontMetricsInt.leading;
            } else {
                i6 = 0;
                i7 = 0;
                i8 = 0;
                i9 = 0;
                i10 = 0;
            }
            float size = replacementSpan.getSize(textPaint, this.mText, i12, i13, fontMetricsInt);
            if (z3) {
                f2 = size;
                updateMetrics(fontMetricsInt, i6, i7, i8, i9, i10);
            } else {
                f2 = size;
            }
            f3 = f2;
        } else {
            f3 = 0.0f;
        }
        if (canvas != null) {
            replacementSpan.draw(canvas, this.mText, i12, i13, z ? f - f3 : f, i3, i4, i5, textPaint);
        }
        return z ? -f3 : f3;
    }

    private int adjustEndHyphenEdit(int i, int i2) {
        if (i < this.mLen) {
            return 0;
        }
        return i2;
    }

    private static final class DecorationInfo {
        public int end;
        public boolean isStrikeThruText;
        public boolean isUnderlineText;
        public int start;
        public int underlineColor;
        public float underlineThickness;

        private DecorationInfo() {
            this.start = -1;
            this.end = -1;
        }

        public boolean hasDecoration() {
            return this.isStrikeThruText || this.isUnderlineText || this.underlineColor != 0;
        }

        public DecorationInfo copyInfo() {
            DecorationInfo decorationInfo = new DecorationInfo();
            decorationInfo.isStrikeThruText = this.isStrikeThruText;
            decorationInfo.isUnderlineText = this.isUnderlineText;
            decorationInfo.underlineColor = this.underlineColor;
            decorationInfo.underlineThickness = this.underlineThickness;
            return decorationInfo;
        }
    }

    private void extractDecorationInfo(TextPaint textPaint, DecorationInfo decorationInfo) {
        decorationInfo.isStrikeThruText = textPaint.isStrikeThruText();
        if (decorationInfo.isStrikeThruText) {
            textPaint.setStrikeThruText(false);
        }
        decorationInfo.isUnderlineText = textPaint.isUnderlineText();
        if (decorationInfo.isUnderlineText) {
            textPaint.setUnderlineText(false);
        }
        decorationInfo.underlineColor = textPaint.underlineColor;
        decorationInfo.underlineThickness = textPaint.underlineThickness;
        textPaint.setUnderlineText(0, 0.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x022e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float handleRun(int r31, int r32, int r33, boolean r34, android.graphics.Canvas r35, float r36, int r37, int r38, int r39, android.graphics.Paint.FontMetricsInt r40, boolean r41) {
        /*
            Method dump skipped, instruction units count: 730
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.TextLine.handleRun(int, int, int, boolean, android.graphics.Canvas, float, int, int, int, android.graphics.Paint$FontMetricsInt, boolean):float");
    }

    private void drawTextRun(Canvas canvas, TextPaint textPaint, int i, int i2, int i3, int i4, boolean z, float f, int i5) {
        if (this.mCharsValid) {
            canvas.drawTextRun(this.mChars, i, i2 - i, i3, i4 - i3, f, i5, z, textPaint);
        } else {
            int i6 = this.mStart;
            canvas.drawTextRun(this.mText, i6 + i, i6 + i2, i6 + i3, i6 + i4, f, i5, z, textPaint);
        }
    }

    float nextTab(float f) {
        Layout.TabStops tabStops = this.mTabs;
        if (tabStops != null) {
            return tabStops.nextTab(f);
        }
        return Layout.TabStops.nextDefaultStop(f, 20.0f);
    }

    private int countStretchableSpaces(int i, int i2) {
        int i3 = 0;
        while (i < i2) {
            if (isStretchableWhitespace(this.mCharsValid ? this.mChars[i] : this.mText.charAt(this.mStart + i))) {
                i3++;
            }
            i++;
        }
        return i3;
    }

    private static boolean equalAttributes(TextPaint textPaint, TextPaint textPaint2) {
        return textPaint.getColorFilter() == textPaint2.getColorFilter() && textPaint.getMaskFilter() == textPaint2.getMaskFilter() && textPaint.getShader() == textPaint2.getShader() && textPaint.getTypeface() == textPaint2.getTypeface() && textPaint.getXfermode() == textPaint2.getXfermode() && textPaint.getTextLocales().equals(textPaint2.getTextLocales()) && TextUtils.equals(textPaint.getFontFeatureSettings(), textPaint2.getFontFeatureSettings()) && TextUtils.equals(textPaint.getFontVariationSettings(), textPaint2.getFontVariationSettings()) && textPaint.getShadowLayerRadius() == textPaint2.getShadowLayerRadius() && textPaint.getShadowLayerDx() == textPaint2.getShadowLayerDx() && textPaint.getShadowLayerDy() == textPaint2.getShadowLayerDy() && textPaint.getShadowLayerColor() == textPaint2.getShadowLayerColor() && textPaint.getFlags() == textPaint2.getFlags() && textPaint.getHinting() == textPaint2.getHinting() && textPaint.getStyle() == textPaint2.getStyle() && textPaint.getColor() == textPaint2.getColor() && textPaint.getStrokeWidth() == textPaint2.getStrokeWidth() && textPaint.getStrokeMiter() == textPaint2.getStrokeMiter() && textPaint.getStrokeCap() == textPaint2.getStrokeCap() && textPaint.getStrokeJoin() == textPaint2.getStrokeJoin() && textPaint.getTextAlign() == textPaint2.getTextAlign() && textPaint.isElegantTextHeight() == textPaint2.isElegantTextHeight() && textPaint.getTextSize() == textPaint2.getTextSize() && textPaint.getTextScaleX() == textPaint2.getTextScaleX() && textPaint.getTextSkewX() == textPaint2.getTextSkewX() && textPaint.getLetterSpacing() == textPaint2.getLetterSpacing() && textPaint.getWordSpacing() == textPaint2.getWordSpacing() && textPaint.getStartHyphenEdit() == textPaint2.getStartHyphenEdit() && textPaint.getEndHyphenEdit() == textPaint2.getEndHyphenEdit() && textPaint.bgColor == textPaint2.bgColor && textPaint.baselineShift == textPaint2.baselineShift && textPaint.linkColor == textPaint2.linkColor && textPaint.drawableState == textPaint2.drawableState && textPaint.density == textPaint2.density && textPaint.underlineColor == textPaint2.underlineColor && textPaint.underlineThickness == textPaint2.underlineThickness;
    }
}
