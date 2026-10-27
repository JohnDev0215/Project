package com.google.android.exoplayer2.ui;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.util.EdgeTypeSpan;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
final class SubtitlePainter {
    private boolean applyEmbeddedFontSizes;
    private boolean applyEmbeddedStyles;
    private int backgroundColor;
    private Rect bitmapRect;
    private float bottomPaddingFraction;
    private final float cornerRadius;
    private Bitmap cueBitmap;
    private float cueBitmapHeight;
    private float cueLine;
    private int cueLineAnchor;
    private int cueLineType;
    private float cuePosition;
    private int cuePositionAnchor;
    LinkedList<CueRowLayout> cueRowLayouts;
    private float cueSize;
    private Layout.Alignment cueTextAlignment;
    private final CaptionStyleCompat defaultStyle;
    private int edgeColor;
    private int edgeType;
    private float effectiveFontScale;
    private int foregroundColor;
    private int maxWindowColumnCount;
    private int maxWindowRowCount;
    private final float outlineWidth;
    private final Paint paint;
    private int parentBottom;
    private int parentLeft;
    private int parentRight;
    private int parentTop;
    private final float shadowOffset;
    private final float shadowRadius;
    private final float spacingAdd;
    private final float spacingMult;
    private int textLeft;
    private int textPaddingX;
    private final TextPaint textPaint;
    private List<Pair<Integer, CharSequence>> textRows;
    private float textSizePx;
    private int textTop;
    private StaticLayout widestCharLayout;
    private int winHeight;
    private int winWidth;
    private int windowBorderColor;
    private int windowBorderType;
    private int windowColor;
    private final RectF lineBounds = new RectF();
    private float oldTextSizePx = 0.0f;

    private static class CueRowLayout {
        public StaticLayout textLayout;
        public int textStartOffset;

        public CueRowLayout(StaticLayout staticLayout, int i) {
            this.textLayout = staticLayout;
            this.textStartOffset = i;
        }
    }

    public SubtitlePainter(Context context, CaptionStyleCompat captionStyleCompat) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, new int[]{R.attr.lineSpacingExtra, R.attr.lineSpacingMultiplier}, 0, 0);
        this.spacingAdd = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.spacingMult = typedArrayObtainStyledAttributes.getFloat(1, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        float fRound = Math.round((context.getResources().getDisplayMetrics().densityDpi * 2.0f) / 160.0f);
        this.cornerRadius = fRound;
        this.outlineWidth = fRound;
        this.shadowRadius = fRound;
        this.shadowOffset = fRound;
        TextPaint textPaint = new TextPaint();
        this.textPaint = textPaint;
        textPaint.setAntiAlias(true);
        textPaint.setSubpixelText(true);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        this.defaultStyle = captionStyleCompat;
    }

    private <T> void removeStyleSpans(Class<T> cls, SpannableStringBuilder spannableStringBuilder) {
        for (Object obj : spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), cls)) {
            spannableStringBuilder.removeSpan(obj);
        }
    }

    private float getMaxEmbeddedFontScale(CharSequence charSequence) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        RelativeSizeSpan[] relativeSizeSpanArr = (RelativeSizeSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), RelativeSizeSpan.class);
        if (relativeSizeSpanArr.length <= 0) {
            return 1.0f;
        }
        float fMax = 0.0f;
        for (RelativeSizeSpan relativeSizeSpan : relativeSizeSpanArr) {
            fMax = Math.max(fMax, relativeSizeSpan.getSizeChange());
        }
        return fMax;
    }

    private void maybeRemoveEmbeddedStyling(CaptionStyleCompat captionStyleCompat, SpannableStringBuilder spannableStringBuilder) {
        if (captionStyleCompat.foregroundColor != this.defaultStyle.foregroundColor) {
            removeStyleSpans(ForegroundColorSpan.class, spannableStringBuilder);
        }
        if (captionStyleCompat.backgroundColor != this.defaultStyle.backgroundColor) {
            removeStyleSpans(BackgroundColorSpan.class, spannableStringBuilder);
        }
        if (captionStyleCompat.typeface != this.defaultStyle.typeface) {
            removeStyleSpans(TypefaceSpan.class, spannableStringBuilder);
        }
        if (captionStyleCompat.edgeType != 0) {
            removeStyleSpans(EdgeTypeSpan.class, spannableStringBuilder);
        }
    }

    private float getEffectiveFontScale(CharSequence charSequence, float f, SpannableStringBuilder spannableStringBuilder) {
        if (this.applyEmbeddedFontSizes || spannableStringBuilder == null) {
            float maxEmbeddedFontScale = getMaxEmbeddedFontScale(charSequence);
            return (Math.abs(f - 1.0f) <= 0.001f || f <= maxEmbeddedFontScale) ? maxEmbeddedFontScale : f;
        }
        if (Math.abs(f - 1.0f) > 0.001f) {
            removeStyleSpans(RelativeSizeSpan.class, spannableStringBuilder);
            removeStyleSpans(AbsoluteSizeSpan.class, spannableStringBuilder);
            return 1.0f;
        }
        return getMaxEmbeddedFontScale(charSequence);
    }

    private float preProcessCueText(CharSequence charSequence, List<Pair<Integer, CharSequence>> list, CaptionStyleCompat captionStyleCompat, float f, int i) {
        SpannableStringBuilder spannableStringBuilder = (this.applyEmbeddedFontSizes && this.applyEmbeddedStyles) ? null : new SpannableStringBuilder(charSequence);
        if (!this.applyEmbeddedStyles && spannableStringBuilder != null) {
            maybeRemoveEmbeddedStyling(captionStyleCompat, spannableStringBuilder);
        }
        float effectiveFontScale = getEffectiveFontScale(charSequence, f, spannableStringBuilder);
        if (spannableStringBuilder != null) {
            list.add(new Pair<>(Integer.valueOf(i), spannableStringBuilder));
        }
        return effectiveFontScale;
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(com.google.android.exoplayer2.text.Cue r27, boolean r28, boolean r29, com.google.android.exoplayer2.text.CaptionStyleCompat r30, float r31, float r32, float r33, android.graphics.Canvas r34, int r35, int r36, int r37, int r38) {
        /*
            Method dump skipped, instruction units count: 688
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.SubtitlePainter.draw(com.google.android.exoplayer2.text.Cue, boolean, boolean, com.google.android.exoplayer2.text.CaptionStyleCompat, float, float, float, android.graphics.Canvas, int, int, int, int):void");
    }

    private void setupTextLayout() {
        Layout.Alignment alignment;
        int iMax;
        int iMin;
        int i;
        int iRound;
        int i2;
        Layout.Alignment alignment2;
        int width;
        int i3 = this.parentRight - this.parentLeft;
        int i4 = this.parentBottom - this.parentTop;
        this.textPaint.setTextSize(this.textSizePx);
        float f = this.textSizePx;
        int i5 = (int) ((0.125f * f) + 0.5f);
        int i6 = i5 * 2;
        int i7 = i3 - i6;
        float f2 = this.cueSize;
        if (f2 != Float.MIN_VALUE) {
            i7 = (int) (i7 * f2);
        }
        if (i7 <= 0) {
            Log.w("SubtitlePainter", "Skipped drawing subtitle cue (insufficient space)");
            return;
        }
        Layout.Alignment alignment3 = this.cueTextAlignment;
        if (alignment3 == null) {
            alignment3 = Layout.Alignment.ALIGN_CENTER;
        }
        Layout.Alignment alignment4 = alignment3;
        if (this.widestCharLayout == null || Math.abs(this.oldTextSizePx - f) > 0.001f) {
            alignment = alignment4;
            this.widestCharLayout = new StaticLayout("M", this.textPaint, i7, Layout.Alignment.ALIGN_NORMAL, this.spacingMult, this.spacingAdd, true);
            this.oldTextSizePx = this.textSizePx;
        } else {
            alignment = alignment4;
        }
        float lineMax = this.widestCharLayout.getLineMax(0) * this.effectiveFontScale;
        float height = this.widestCharLayout.getHeight() * this.effectiveFontScale;
        int i8 = (int) (this.maxWindowColumnCount * lineMax);
        this.winWidth = i8;
        if ((this.cueSize != Float.MIN_VALUE && i8 < i7) || i8 > i7) {
            this.winWidth = i7;
        }
        int i9 = this.winWidth;
        if (i9 != 0) {
            this.winWidth = i9 + i6;
        }
        this.winHeight = (int) (height * this.maxWindowRowCount);
        this.cueRowLayouts = new LinkedList<>();
        Iterator<Pair<Integer, CharSequence>> it = this.textRows.iterator();
        int iMax2 = 0;
        int height2 = 0;
        while (it.hasNext()) {
            StaticLayout staticLayout = new StaticLayout((CharSequence) it.next().second, this.textPaint, i7, alignment, this.spacingMult, this.spacingAdd, true);
            height2 += staticLayout.getHeight();
            int lineCount = staticLayout.getLineCount();
            iMax2 = iMax2;
            for (int i10 = 0; i10 < lineCount; i10++) {
                iMax2 = Math.max((int) Math.ceil(staticLayout.getLineWidth(i10)), iMax2);
            }
        }
        int i11 = iMax2;
        if (this.cueSize == Float.MIN_VALUE || i11 >= i7) {
            i7 = i11;
        }
        int i12 = i7 + i6;
        float f3 = this.cuePosition;
        if (f3 != Float.MIN_VALUE) {
            int iRound2 = Math.round(i3 * f3);
            int i13 = this.parentLeft;
            int i14 = iRound2 + i13;
            int i15 = this.cuePositionAnchor;
            if (i15 == 2) {
                i14 -= i12;
            } else if (i15 == 1) {
                i14 = ((i14 * 2) - i12) / 2;
            }
            iMax = Math.max(i14, i13);
            iMin = Math.min(i12 + iMax, this.parentRight);
        } else {
            iMax = (i3 - i12) / 2;
            iMin = iMax + i12;
        }
        int i16 = iMin - iMax;
        if (i16 <= 0) {
            Log.w("SubtitlePainter", "Skipped drawing subtitle cue (invalid horizontal positioning)");
            return;
        }
        for (Pair<Integer, CharSequence> pair : this.textRows) {
            int iRound3 = Math.round(((Integer) pair.first).intValue() * lineMax);
            StaticLayout staticLayout2 = new StaticLayout((CharSequence) pair.second, this.textPaint, i16, alignment, this.spacingMult, this.spacingAdd, true);
            if (this.cuePosition != Float.MIN_VALUE) {
                alignment2 = alignment;
                if (alignment2 != Layout.Alignment.ALIGN_NORMAL && (width = this.winWidth - staticLayout2.getWidth()) > 0) {
                    iRound3 = alignment2 == Layout.Alignment.ALIGN_OPPOSITE ? width : width / 2;
                }
            } else {
                alignment2 = alignment;
            }
            this.cueRowLayouts.add(new CueRowLayout(staticLayout2, iRound3));
            alignment = alignment2;
        }
        float f4 = this.cueLine;
        if (f4 != Float.MIN_VALUE) {
            if (this.cueLineType == 0) {
                iRound = Math.round(i4 * f4);
                i2 = this.parentTop;
            } else {
                StaticLayout staticLayout3 = this.cueRowLayouts.get(0).textLayout;
                int lineBottom = staticLayout3.getLineBottom(0) - staticLayout3.getLineTop(0);
                float f5 = this.cueLine;
                if (f5 >= 0.0f) {
                    iRound = Math.round(f5 * lineBottom);
                    i2 = this.parentTop;
                } else {
                    iRound = Math.round((f5 + 1.0f) * lineBottom);
                    i2 = this.parentBottom;
                }
            }
            i = iRound + i2;
            int i17 = this.cueLineAnchor;
            if (i17 == 2) {
                i -= height2;
            } else if (i17 == 1) {
                i = ((i * 2) - height2) / 2;
            }
            int i18 = i + height2;
            int i19 = this.parentBottom;
            if (i18 > i19) {
                i = i19 - height2;
            } else {
                int i20 = this.parentTop;
                if (i < i20) {
                    i = i20;
                }
            }
        } else {
            i = (this.parentBottom - height2) - ((int) (i4 * this.bottomPaddingFraction));
        }
        this.textLeft = iMax;
        this.textTop = i;
        this.textPaddingX = i5;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setupBitmapLayout() {
        /*
            r7 = this;
            int r0 = r7.parentRight
            int r1 = r7.parentLeft
            int r0 = r0 - r1
            int r2 = r7.parentBottom
            int r3 = r7.parentTop
            int r2 = r2 - r3
            float r1 = (float) r1
            float r0 = (float) r0
            float r4 = r7.cuePosition
            float r4 = r4 * r0
            float r1 = r1 + r4
            float r3 = (float) r3
            float r2 = (float) r2
            float r4 = r7.cueLine
            float r4 = r4 * r2
            float r3 = r3 + r4
            float r4 = r7.cueSize
            float r0 = r0 * r4
            int r0 = java.lang.Math.round(r0)
            float r4 = r7.cueBitmapHeight
            r5 = 1
            int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r5 == 0) goto L2a
            float r2 = r2 * r4
            int r2 = java.lang.Math.round(r2)
            goto L3f
        L2a:
            float r2 = (float) r0
            android.graphics.Bitmap r4 = r7.cueBitmap
            int r4 = r4.getHeight()
            float r4 = (float) r4
            android.graphics.Bitmap r5 = r7.cueBitmap
            int r5 = r5.getWidth()
            float r5 = (float) r5
            float r4 = r4 / r5
            float r2 = r2 * r4
            int r2 = java.lang.Math.round(r2)
        L3f:
            int r4 = r7.cueLineAnchor
            r5 = 1
            r6 = 2
            if (r4 != r6) goto L48
            float r4 = (float) r0
        L46:
            float r1 = r1 - r4
            goto L4e
        L48:
            if (r4 != r5) goto L4e
            int r4 = r0 / 2
            float r4 = (float) r4
            goto L46
        L4e:
            int r1 = java.lang.Math.round(r1)
            int r4 = r7.cuePositionAnchor
            if (r4 != r6) goto L59
            float r4 = (float) r2
        L57:
            float r3 = r3 - r4
            goto L5f
        L59:
            if (r4 != r5) goto L5f
            int r4 = r2 / 2
            float r4 = (float) r4
            goto L57
        L5f:
            int r3 = java.lang.Math.round(r3)
            android.graphics.Rect r4 = new android.graphics.Rect
            int r0 = r0 + r1
            int r2 = r2 + r3
            r4.<init>(r1, r3, r0, r2)
            r7.bitmapRect = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.SubtitlePainter.setupBitmapLayout():void");
    }

    private void drawLayout(Canvas canvas, boolean z) {
        if (z) {
            drawTextLayout(canvas);
        } else {
            drawBitmapLayout(canvas);
        }
    }

    private void drawTextLayout(Canvas canvas) {
        LinkedList<CueRowLayout> linkedList = this.cueRowLayouts;
        if (linkedList == null || linkedList.size() == 0) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(this.textLeft, this.textTop);
        if (Color.alpha(this.windowColor) > 0 && this.winWidth != 0) {
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setColor(this.windowColor);
            int i = this.textPaddingX;
            canvas.drawRect(-i, 0.0f, this.winWidth + i, this.winHeight, this.paint);
        }
        if (this.windowBorderType != 0 && Color.alpha(this.windowBorderColor) > 0 && this.winWidth != 0) {
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setColor(this.windowBorderColor);
            int i2 = this.textPaddingX;
            canvas.drawRect(-i2, 0.0f, this.winWidth + i2, this.winHeight, this.paint);
        }
        canvas.restoreToCount(iSave);
        Iterator<CueRowLayout> it = this.cueRowLayouts.iterator();
        int height = 0;
        while (it.hasNext()) {
            StaticLayout staticLayout = it.next().textLayout;
            if (staticLayout.getText().length() == 0) {
                height += staticLayout.getHeight();
            } else {
                int iSave2 = canvas.save();
                canvas.translate(r3.textStartOffset + this.textLeft, this.textTop + height);
                height += staticLayout.getHeight();
                if (Color.alpha(this.backgroundColor) > 0) {
                    this.paint.setStyle(Paint.Style.FILL);
                    this.paint.setColor(this.backgroundColor);
                    float lineTop = staticLayout.getLineTop(0);
                    int lineCount = staticLayout.getLineCount();
                    int i3 = 0;
                    while (i3 < lineCount) {
                        this.lineBounds.left = staticLayout.getLineLeft(i3);
                        this.lineBounds.right = staticLayout.getLineRight(i3);
                        RectF rectF = this.lineBounds;
                        rectF.top = lineTop;
                        rectF.bottom = staticLayout.getLineBottom(i3);
                        RectF rectF2 = this.lineBounds;
                        float f = rectF2.bottom;
                        float f2 = rectF2.left;
                        float f3 = rectF2.right;
                        if (f2 != f3) {
                            int i4 = this.textPaddingX;
                            rectF2.left = f2 - i4;
                            rectF2.right = f3 + i4;
                            float f4 = this.cornerRadius;
                            canvas.drawRoundRect(rectF2, f4, f4, this.paint);
                        }
                        i3++;
                        lineTop = f;
                    }
                }
                int i5 = this.edgeType;
                if (i5 == 1) {
                    this.textPaint.setStrokeJoin(Paint.Join.ROUND);
                    this.textPaint.setStrokeWidth(this.outlineWidth);
                    this.textPaint.setColor(this.edgeColor);
                    this.textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                    staticLayout.draw(canvas);
                } else if (i5 == 2) {
                    TextPaint textPaint = this.textPaint;
                    float f5 = this.shadowRadius;
                    float f6 = this.shadowOffset;
                    textPaint.setShadowLayer(f5, f6, f6, this.edgeColor);
                } else if (i5 == 3 || i5 == 4) {
                    boolean z = i5 == 3;
                    int i6 = z ? -1 : this.edgeColor;
                    int i7 = z ? this.edgeColor : -1;
                    float f7 = this.shadowRadius / 2.0f;
                    this.textPaint.setColor(this.foregroundColor);
                    this.textPaint.setStyle(Paint.Style.FILL);
                    float f8 = -f7;
                    this.textPaint.setShadowLayer(this.shadowRadius, f8, f8, i6);
                    staticLayout.draw(canvas);
                    this.textPaint.setShadowLayer(this.shadowRadius, f7, f7, i7);
                }
                this.textPaint.setColor(this.foregroundColor);
                this.textPaint.setStyle(Paint.Style.FILL);
                staticLayout.draw(canvas);
                this.textPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
                canvas.restoreToCount(iSave2);
            }
        }
    }

    private void drawBitmapLayout(Canvas canvas) {
        canvas.drawBitmap(this.cueBitmap, (Rect) null, this.bitmapRect, (Paint) null);
    }

    private static boolean areCharSequencesEqual(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence == charSequence2 || (charSequence != null && charSequence.equals(charSequence2));
    }
}
