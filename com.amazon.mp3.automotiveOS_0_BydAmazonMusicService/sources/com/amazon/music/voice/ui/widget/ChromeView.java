package com.amazon.music.voice.ui.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.FloatRange;
import com.amazon.music.voice.R$styleable;
import com.amazon.music.voice.ui.internal.util.DisplayUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class ChromeView extends View {
    static final Property<ChromeView, Float> RENDER_LEVEL = new Property<ChromeView, Float>(Float.class, "renderLevel") { // from class: com.amazon.music.voice.ui.widget.ChromeView.3
        @Override // android.util.Property
        public Float get(ChromeView chromeView) {
            return Float.valueOf(chromeView.getRenderLevel());
        }

        @Override // android.util.Property
        public void set(ChromeView chromeView, Float f) {
            chromeView.setRenderLevel(f.floatValue());
        }
    };
    private int backgroundColor;
    private int chromeHeight;
    private Drawable chromeMiddleDrawable;
    private Drawable chromeProcessingDrawable1;
    private Drawable chromeProcessingDrawable2;
    private Drawable chromeProcessingDrawable4;
    private Drawable chromeProcessingDrawable5;
    private Interpolator decelerateInterpolator;
    private boolean drawProcessingDrawables;
    private float level;
    private ObjectAnimator listeningAnimator;
    private Paint paint;
    private ValueAnimator processingAnimator;
    private float renderLevel;
    private ValueAnimator responseAnimator;

    public ChromeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.drawProcessingDrawables = false;
        initialize(context, attributeSet);
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.ChromeView, 0, 0);
        try {
            this.chromeHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ChromeView_chromeHeight, DisplayUtils.transformDpiToPx(context, 8));
            this.chromeProcessingDrawable1 = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ChromeView_chrome1);
            this.chromeProcessingDrawable2 = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ChromeView_chrome2);
            this.chromeMiddleDrawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ChromeView_chromeMiddle);
            this.chromeProcessingDrawable4 = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ChromeView_chrome4);
            this.chromeProcessingDrawable5 = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ChromeView_chrome5);
            mutateChromeDrawables();
            hideProcessingChromeDrawables();
            this.backgroundColor = typedArrayObtainStyledAttributes.getColor(R$styleable.ChromeView_colour, -16776961);
            typedArrayObtainStyledAttributes.recycle();
            this.paint = new Paint();
            createListeningAnimator();
            createProcessingAnimator();
            createResponseAnimator();
            this.decelerateInterpolator = new DecelerateInterpolator(1.72f);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void mutateChromeDrawables() {
        Drawable drawable = this.chromeProcessingDrawable1;
        if (drawable != null) {
            drawable.mutate();
        }
        Drawable drawable2 = this.chromeProcessingDrawable2;
        if (drawable2 != null) {
            drawable2.mutate();
        }
        Drawable drawable3 = this.chromeMiddleDrawable;
        if (drawable3 != null) {
            drawable3.mutate();
        }
        Drawable drawable4 = this.chromeProcessingDrawable4;
        if (drawable4 != null) {
            drawable4.mutate();
        }
        Drawable drawable5 = this.chromeProcessingDrawable5;
        if (drawable5 != null) {
            drawable5.mutate();
        }
    }

    public void startResponseAnimation() {
        stopAllAnimations();
        setLevel(1.0f);
        this.responseAnimator.start();
    }

    public void stopResponseAnimation() {
        ValueAnimator valueAnimator = this.responseAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        hideProcessingChromeDrawables();
    }

    private void createResponseAnimator() {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, 255);
        this.responseAnimator = valueAnimatorOfInt;
        valueAnimatorOfInt.setDuration(800L);
        this.responseAnimator.setRepeatCount(-1);
        this.responseAnimator.setRepeatMode(2);
        this.responseAnimator.setInterpolator(new LinearInterpolator());
        this.responseAnimator.addUpdateListener(createResponseAnimatorUpdateListener());
    }

    public ValueAnimator.AnimatorUpdateListener createResponseAnimatorUpdateListener() {
        return new ValueAnimator.AnimatorUpdateListener() { // from class: com.amazon.music.voice.ui.widget.ChromeView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ChromeView.this.chromeMiddleDrawable.setAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
                ChromeView.this.postInvalidateOnAnimation();
            }
        };
    }

    public void startProcessingAnimation() {
        stopAllAnimations();
        setLevel(0.25f);
        this.drawProcessingDrawables = true;
        this.processingAnimator.start();
    }

    public void stopProcessingAnimation() {
        ValueAnimator valueAnimator = this.processingAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        hideProcessingChromeDrawables();
    }

    private void createProcessingAnimator() {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(255, 0);
        this.processingAnimator = valueAnimatorOfInt;
        valueAnimatorOfInt.setDuration(150L);
        this.processingAnimator.setRepeatCount(-1);
        this.processingAnimator.setRepeatMode(2);
        this.processingAnimator.setInterpolator(new LinearInterpolator());
        this.processingAnimator.addUpdateListener(createProcessingAnimatorUpdateListener());
    }

    private ValueAnimator.AnimatorUpdateListener createProcessingAnimatorUpdateListener() {
        return new ValueAnimator.AnimatorUpdateListener() { // from class: com.amazon.music.voice.ui.widget.ChromeView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ChromeView.this.chromeProcessingDrawable1.setAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
                ChromeView.this.chromeMiddleDrawable.setAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
                ChromeView.this.chromeProcessingDrawable5.setAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
                ChromeView.this.chromeProcessingDrawable2.setAlpha(Math.abs(255 - ((Integer) valueAnimator.getAnimatedValue()).intValue()));
                ChromeView.this.chromeProcessingDrawable4.setAlpha(Math.abs(255 - ((Integer) valueAnimator.getAnimatedValue()).intValue()));
                ChromeView.this.postInvalidateOnAnimation();
            }
        };
    }

    private void hideProcessingChromeDrawables() {
        this.drawProcessingDrawables = false;
        resetMiddleChromeDrawable();
        postInvalidateOnAnimation();
    }

    private void resetMiddleChromeDrawable() {
        Drawable drawable = this.chromeMiddleDrawable;
        if (drawable != null) {
            drawable.setAlpha(255);
        }
    }

    public void stopAllAnimations() {
        stopProcessingAnimation();
        stopResponseAnimation();
        stopListeningAnimation();
    }

    public void stopListeningAnimation() {
        ObjectAnimator objectAnimator = this.listeningAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    private void createListeningAnimator() {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        this.listeningAnimator = objectAnimator;
        objectAnimator.setTarget(this);
        this.listeningAnimator.setProperty(RENDER_LEVEL);
        this.listeningAnimator.setInterpolator(this.decelerateInterpolator);
        this.listeningAnimator.setDuration(350L);
    }

    float getRenderLevel() {
        return this.renderLevel;
    }

    void setRenderLevel(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.renderLevel != f) {
            this.renderLevel = f;
            postInvalidateOnAnimation();
        }
    }

    public float getLevel() {
        return this.level;
    }

    public void setLevel(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.level == f) {
            return;
        }
        this.level = f;
        ObjectAnimator objectAnimator = this.listeningAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        this.renderLevel = f;
        postInvalidateOnAnimation();
    }

    public void setLevelAnimated(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.level == f) {
            return;
        }
        this.level = f;
        this.listeningAnimator.setFloatValues(f);
        this.listeningAnimator.start();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        this.paint.setColor(this.backgroundColor);
        float f = width;
        canvas.drawRect(0.0f, this.chromeHeight, f, height, this.paint);
        int i = (int) (f * ((this.renderLevel * 0.75f) + 0.25f));
        int i2 = (width - i) / 2;
        this.chromeMiddleDrawable.setBounds(i2, 0, i + i2, height);
        setProcessingDrawablesBounds();
        drawChromeDrawables(canvas);
    }

    private void setProcessingDrawablesBounds() {
        int width = getWidth();
        int height = getHeight();
        int i = width / 5;
        int i2 = (int) (i * 0.2f);
        this.chromeProcessingDrawable1.setBounds(0, 0, i + i2, height);
        this.chromeProcessingDrawable2.setBounds(i - i2, 0, (i * 2) + i2, height);
        int i3 = (i * 3) - i2;
        int i4 = i * 4;
        this.chromeProcessingDrawable4.setBounds(i3, 0, i4 + i2, height);
        this.chromeProcessingDrawable5.setBounds(i4 - i2, 0, width, height);
    }

    private void drawChromeDrawables(Canvas canvas) {
        if (this.drawProcessingDrawables) {
            this.chromeProcessingDrawable1.draw(canvas);
            this.chromeProcessingDrawable2.draw(canvas);
            this.chromeProcessingDrawable4.draw(canvas);
            this.chromeProcessingDrawable5.draw(canvas);
        }
        this.chromeMiddleDrawable.draw(canvas);
    }
}
