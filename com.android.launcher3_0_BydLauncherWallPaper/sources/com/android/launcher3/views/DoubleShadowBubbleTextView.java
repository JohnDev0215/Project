package com.android.launcher3.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.R;
import com.android.launcher3.icons.GraphicsUtils;

/* JADX INFO: loaded from: classes4.dex */
public class DoubleShadowBubbleTextView extends BubbleTextView {
    private final ShadowInfo mShadowInfo;

    public DoubleShadowBubbleTextView(Context context) {
        this(context, null);
    }

    public DoubleShadowBubbleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DoubleShadowBubbleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ShadowInfo shadowInfo = new ShadowInfo(context, attrs, defStyle);
        this.mShadowInfo = shadowInfo;
        setShadowLayer(shadowInfo.ambientShadowBlur, 0.0f, 0.0f, shadowInfo.ambientShadowColor);
    }

    @Override // com.android.launcher3.BubbleTextView, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        if (this.mShadowInfo.skipDoubleShadow(this)) {
            super.onDraw(canvas);
            return;
        }
        int iAlpha = Color.alpha(getCurrentTextColor());
        getPaint().setShadowLayer(this.mShadowInfo.ambientShadowBlur, 0.0f, 0.0f, GraphicsUtils.setColorAlphaBound(this.mShadowInfo.ambientShadowColor, iAlpha));
        drawWithoutDot(canvas);
        canvas.save();
        canvas.clipRect(getScrollX(), getScrollY() + getExtendedPaddingTop(), getScrollX() + getWidth(), getScrollY() + getHeight());
        getPaint().setShadowLayer(this.mShadowInfo.keyShadowBlur, 0.0f, this.mShadowInfo.keyShadowOffset, GraphicsUtils.setColorAlphaBound(this.mShadowInfo.keyShadowColor, iAlpha));
        drawWithoutDot(canvas);
        canvas.restore();
        drawDotIfNecessary(canvas);
    }

    public static class ShadowInfo {
        public final float ambientShadowBlur;
        public final int ambientShadowColor;
        public final float keyShadowBlur;
        public final int keyShadowColor;
        public final float keyShadowOffset;

        public ShadowInfo(Context c, AttributeSet attrs, int defStyle) {
            TypedArray typedArrayObtainStyledAttributes = c.obtainStyledAttributes(attrs, R.styleable.ShadowInfo, defStyle, 0);
            this.ambientShadowBlur = typedArrayObtainStyledAttributes.getDimension(0, 0.0f);
            this.ambientShadowColor = typedArrayObtainStyledAttributes.getColor(1, 0);
            this.keyShadowBlur = typedArrayObtainStyledAttributes.getDimension(2, 0.0f);
            this.keyShadowOffset = typedArrayObtainStyledAttributes.getDimension(4, 0.0f);
            this.keyShadowColor = typedArrayObtainStyledAttributes.getColor(3, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean skipDoubleShadow(TextView textView) {
            int iAlpha = Color.alpha(textView.getCurrentTextColor());
            int iAlpha2 = Color.alpha(this.keyShadowColor);
            int iAlpha3 = Color.alpha(this.ambientShadowColor);
            if (iAlpha == 0 || (iAlpha2 == 0 && iAlpha3 == 0)) {
                textView.getPaint().clearShadowLayer();
                return true;
            }
            if (iAlpha3 > 0) {
                textView.getPaint().setShadowLayer(this.ambientShadowBlur, 0.0f, 0.0f, GraphicsUtils.setColorAlphaBound(this.ambientShadowColor, iAlpha));
                return true;
            }
            if (iAlpha2 <= 0) {
                return false;
            }
            textView.getPaint().setShadowLayer(this.keyShadowBlur, 0.0f, this.keyShadowOffset, GraphicsUtils.setColorAlphaBound(this.keyShadowColor, iAlpha));
            return true;
        }
    }
}
