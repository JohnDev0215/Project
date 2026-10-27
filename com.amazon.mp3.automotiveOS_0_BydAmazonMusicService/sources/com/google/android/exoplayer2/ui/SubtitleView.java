package com.google.android.exoplayer2.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.CaptioningManager;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextRenderer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public final class SubtitleView extends View implements TextRenderer.Output {
    private boolean applyEmbeddedFontSizes;
    private boolean applyEmbeddedStyles;
    private float bottomPaddingFraction;
    private List<Cue> cues;
    private float fontScale;
    private final List<SubtitlePainter> painters;
    private CaptionStyleCompat style;
    private float textSize;
    private int textSizeType;

    public SubtitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.fontScale = 1.0f;
        this.painters = new ArrayList();
        this.textSizeType = 0;
        this.textSize = 0.0533f;
        this.applyEmbeddedStyles = false;
        this.applyEmbeddedFontSizes = false;
        this.style = CaptionStyleCompat.DEFAULT;
        this.bottomPaddingFraction = 0.08f;
    }

    @Override // com.google.android.exoplayer2.text.TextRenderer.Output
    public void onCues(List<Cue> list) {
        setCues(list);
    }

    public void setCues(List<Cue> list) {
        if (this.cues == list) {
            return;
        }
        this.cues = list;
        int size = list == null ? 0 : list.size();
        while (this.painters.size() < size) {
            this.painters.add(new SubtitlePainter(getContext(), CaptionStyleCompat.DEFAULT));
        }
        invalidate();
    }

    public void setUserFontScale(float f) {
        this.fontScale = f;
    }

    public void setFractionalTextSize(float f) {
        setFractionalTextSize(f, false);
    }

    public void setFractionalTextSize(float f, boolean z) {
        setTextSize(z ? 1 : 0, f);
    }

    private void setTextSize(int i, float f) {
        if (this.textSizeType == i && this.textSize == f) {
            return;
        }
        this.textSizeType = i;
        this.textSize = f;
        invalidate();
    }

    public void setApplyEmbeddedStyles(boolean z) {
        if (this.applyEmbeddedStyles == z && this.applyEmbeddedFontSizes == z) {
            return;
        }
        this.applyEmbeddedStyles = z;
        this.applyEmbeddedFontSizes = z;
        invalidate();
    }

    public void setApplyEmbeddedFontSizes(boolean z) {
        if (this.applyEmbeddedFontSizes == z) {
            return;
        }
        this.applyEmbeddedFontSizes = z;
        invalidate();
    }

    public void setStyle(CaptionStyleCompat captionStyleCompat) {
        if (this.style == captionStyleCompat) {
            return;
        }
        this.style = captionStyleCompat;
        invalidate();
    }

    public void setBottomPaddingFraction(float f) {
        if (this.bottomPaddingFraction == f) {
            return;
        }
        this.bottomPaddingFraction = f;
        invalidate();
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas canvas) {
        List<Cue> list = this.cues;
        int size = list == null ? 0 : list.size();
        int top = getTop();
        int bottom = getBottom();
        int left = getLeft() + getPaddingLeft();
        int paddingTop = getPaddingTop() + top;
        int right = getRight() - getPaddingRight();
        int paddingBottom = bottom - getPaddingBottom();
        if (paddingBottom <= paddingTop || right <= left) {
            return;
        }
        int i = right - left;
        int i2 = paddingBottom - paddingTop;
        if (size != 0 && !this.cues.get(0).isWideAspectRatio && i / i2 > 1.5555556f) {
            int i3 = (i2 * 4) / 3;
            int i4 = (i - i3) / 2;
            left += i4;
            right -= i4;
            i = i3;
        }
        if (size > 0 && this.cues.get(0).isSafeOffsetAdjustmentNeeded) {
            int i5 = (int) ((i * 0.2f) / 2.0f);
            int i6 = (int) ((i2 * 0.2f) / 2.0f);
            left += i5;
            paddingTop += i6;
            right -= i5;
            paddingBottom -= i6;
        }
        int i7 = this.textSizeType;
        float f = i7 == 2 ? this.textSize : (i7 == 0 ? paddingBottom - paddingTop : bottom - top) * this.textSize;
        if (f <= 0.0f) {
            return;
        }
        for (int i8 = 0; i8 < size; i8++) {
            this.painters.get(i8).draw(this.cues.get(i8), this.applyEmbeddedStyles, this.applyEmbeddedFontSizes, this.style, this.fontScale, f, this.bottomPaddingFraction, canvas, left, paddingTop, right, paddingBottom);
        }
    }

    @TargetApi(19)
    private float getUserCaptionFontScaleV19() {
        return ((CaptioningManager) getContext().getSystemService("captioning")).getFontScale();
    }

    @TargetApi(19)
    private CaptionStyleCompat getUserCaptionStyleV19() {
        return CaptionStyleCompat.createFromCaptionStyle(((CaptioningManager) getContext().getSystemService("captioning")).getUserStyle());
    }
}
