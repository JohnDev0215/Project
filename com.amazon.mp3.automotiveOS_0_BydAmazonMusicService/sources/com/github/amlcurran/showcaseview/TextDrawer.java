package com.github.amlcurran.showcaseview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import android.text.style.TextAppearanceSpan;

/* JADX INFO: loaded from: classes6.dex */
class TextDrawer {
    private final float actionBarOffset;
    private final Context context;
    private int forcedTextPosition;
    private boolean hasRecalculated;
    private float[] mBestTextPosition;
    private final float padding;
    private Layout.Alignment textAlignment;
    private DynamicLayout textLayout;
    private final TextPaint textPaint;
    private MetricAffectingSpan textSpan;
    private SpannableString textString;
    private Layout.Alignment titleAlignment;
    private DynamicLayout titleLayout;
    private final TextPaint titlePaint;
    private MetricAffectingSpan titleSpan;
    private SpannableString titleString;

    public TextDrawer(Resources resources, Context context) {
        Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
        this.textAlignment = alignment;
        this.titleAlignment = alignment;
        this.mBestTextPosition = new float[3];
        this.forcedTextPosition = -1;
        this.padding = resources.getDimension(R$dimen.text_padding);
        this.actionBarOffset = resources.getDimension(R$dimen.action_bar_offset);
        this.context = context;
        TextPaint textPaint = new TextPaint();
        this.titlePaint = textPaint;
        textPaint.setAntiAlias(true);
        TextPaint textPaint2 = new TextPaint();
        this.textPaint = textPaint2;
        textPaint2.setAntiAlias(true);
    }

    public void draw(Canvas canvas) {
        if (shouldDrawText()) {
            float[] bestTextPosition = getBestTextPosition();
            int iMax = Math.max(0, (int) this.mBestTextPosition[2]);
            if (!TextUtils.isEmpty(this.titleString)) {
                canvas.save();
                if (this.hasRecalculated) {
                    this.titleLayout = new DynamicLayout(this.titleString, this.titlePaint, iMax, this.titleAlignment, 1.0f, 1.0f, true);
                }
                if (this.titleLayout != null) {
                    canvas.translate(bestTextPosition[0], bestTextPosition[1]);
                    this.titleLayout.draw(canvas);
                    canvas.restore();
                }
            }
            if (!TextUtils.isEmpty(this.textString)) {
                canvas.save();
                if (this.hasRecalculated) {
                    this.textLayout = new DynamicLayout(this.textString, this.textPaint, iMax, this.textAlignment, 1.2f, 1.0f, true);
                }
                float height = this.titleLayout != null ? r2.getHeight() : 0.0f;
                if (this.textLayout != null) {
                    canvas.translate(bestTextPosition[0], bestTextPosition[1] + height);
                    this.textLayout.draw(canvas);
                    canvas.restore();
                }
            }
        }
        this.hasRecalculated = false;
    }

    public void setContentText(CharSequence charSequence) {
        if (charSequence != null) {
            SpannableString spannableString = new SpannableString(charSequence);
            spannableString.setSpan(this.textSpan, 0, spannableString.length(), 0);
            this.textString = spannableString;
            this.hasRecalculated = true;
        }
    }

    public void setContentTitle(CharSequence charSequence) {
        if (charSequence != null) {
            SpannableString spannableString = new SpannableString(charSequence);
            spannableString.setSpan(this.titleSpan, 0, spannableString.length(), 0);
            this.titleString = spannableString;
            this.hasRecalculated = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void calculateTextPosition(int r11, int r12, boolean r13, android.graphics.Rect r14) {
        /*
            r10 = this;
            r0 = 4
            int[] r1 = new int[r0]
            int r2 = r14.left
            int r2 = r2 * r12
            r3 = 0
            r1[r3] = r2
            int r2 = r14.top
            int r2 = r2 * r11
            r4 = 1
            r1[r4] = r2
            int r2 = r14.right
            int r2 = r11 - r2
            int r2 = r2 * r12
            r5 = 2
            r1[r5] = r2
            int r2 = r14.bottom
            int r2 = r12 - r2
            int r2 = r2 * r11
            r6 = 3
            r1[r6] = r2
            r7 = r3
            r2 = r4
        L21:
            if (r2 >= r0) goto L2d
            r8 = r1[r2]
            r9 = r1[r7]
            if (r8 <= r9) goto L2a
            r7 = r2
        L2a:
            int r2 = r2 + 1
            goto L21
        L2d:
            int r1 = r10.forcedTextPosition
            r2 = -1
            if (r1 == r2) goto L33
            r7 = r1
        L33:
            r1 = 1073741824(0x40000000, float:2.0)
            if (r7 == 0) goto L75
            if (r7 == r4) goto L64
            if (r7 == r5) goto L50
            if (r7 == r6) goto L3e
            goto L84
        L3e:
            float[] r2 = r10.mBestTextPosition
            float r8 = r10.padding
            r2[r3] = r8
            int r14 = r14.bottom
            float r14 = (float) r14
            float r14 = r14 + r8
            r2[r4] = r14
            float r14 = (float) r11
            float r8 = r8 * r1
            float r14 = r14 - r8
            r2[r5] = r14
            goto L84
        L50:
            float[] r2 = r10.mBestTextPosition
            int r14 = r14.right
            float r8 = (float) r14
            float r9 = r10.padding
            float r8 = r8 + r9
            r2[r3] = r8
            r2[r4] = r9
            int r14 = r11 - r14
            float r14 = (float) r14
            float r9 = r9 * r1
            float r14 = r14 - r9
            r2[r5] = r14
            goto L84
        L64:
            float[] r14 = r10.mBestTextPosition
            float r2 = r10.padding
            r14[r3] = r2
            float r8 = r10.actionBarOffset
            float r8 = r8 + r2
            r14[r4] = r8
            float r8 = (float) r11
            float r2 = r2 * r1
            float r8 = r8 - r2
            r14[r5] = r8
            goto L84
        L75:
            float[] r2 = r10.mBestTextPosition
            float r8 = r10.padding
            r2[r3] = r8
            r2[r4] = r8
            int r14 = r14.left
            float r14 = (float) r14
            float r8 = r8 * r1
            float r14 = r14 - r8
            r2[r5] = r14
        L84:
            if (r13 == 0) goto La8
            if (r7 == 0) goto L9e
            if (r7 == r4) goto L8f
            if (r7 == r5) goto L9e
            if (r7 == r6) goto L8f
            goto Lb6
        L8f:
            float[] r12 = r10.mBestTextPosition
            r13 = r12[r5]
            float r13 = r13 / r1
            r12[r5] = r13
            r13 = r12[r3]
            int r11 = r11 / r0
            float r11 = (float) r11
            float r13 = r13 + r11
            r12[r3] = r13
            goto Lb6
        L9e:
            float[] r11 = r10.mBestTextPosition
            r13 = r11[r4]
            int r12 = r12 / r0
            float r12 = (float) r12
            float r13 = r13 + r12
            r11[r4] = r13
            goto Lb6
        La8:
            if (r7 == 0) goto Lad
            if (r7 == r5) goto Lad
            goto Lb6
        Lad:
            float[] r11 = r10.mBestTextPosition
            r12 = r11[r4]
            float r13 = r10.actionBarOffset
            float r12 = r12 + r13
            r11[r4] = r12
        Lb6:
            r10.hasRecalculated = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.amlcurran.showcaseview.TextDrawer.calculateTextPosition(int, int, boolean, android.graphics.Rect):void");
    }

    public void setTitleStyling(int i) {
        this.titleSpan = new TextAppearanceSpan(this.context, i);
        setContentTitle(this.titleString);
    }

    public void setDetailStyling(int i) {
        this.textSpan = new TextAppearanceSpan(this.context, i);
        setContentText(this.textString);
    }

    public float[] getBestTextPosition() {
        return this.mBestTextPosition;
    }

    public boolean shouldDrawText() {
        return (TextUtils.isEmpty(this.titleString) && TextUtils.isEmpty(this.textString)) ? false : true;
    }

    public void setContentPaint(TextPaint textPaint) {
        this.textPaint.set(textPaint);
        SpannableString spannableString = this.textString;
        if (spannableString != null) {
            spannableString.removeSpan(this.textSpan);
        }
        this.textSpan = new NoOpSpan();
        setContentText(this.textString);
    }

    public void setTitlePaint(TextPaint textPaint) {
        this.titlePaint.set(textPaint);
        SpannableString spannableString = this.titleString;
        if (spannableString != null) {
            spannableString.removeSpan(this.titleSpan);
        }
        this.titleSpan = new NoOpSpan();
        setContentTitle(this.titleString);
    }

    public void setDetailTextAlignment(Layout.Alignment alignment) {
        this.textAlignment = alignment;
    }

    public void setTitleTextAlignment(Layout.Alignment alignment) {
        this.titleAlignment = alignment;
    }

    public void forceTextPosition(int i) {
        if (i > 3 || i < -1) {
            throw new IllegalArgumentException("ShowcaseView text was forced with an invalid position");
        }
        this.forcedTextPosition = i;
    }

    private static class NoOpSpan extends MetricAffectingSpan {
        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
        }

        @Override // android.text.style.MetricAffectingSpan
        public void updateMeasureState(TextPaint textPaint) {
        }

        private NoOpSpan() {
        }
    }
}
