package com.android.systemui.shadow;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class ShadowLayout extends LinearLayout {
    private static final String TAG = "ShadowLayout";
    private int bgColor;
    private float blurRadius;
    int bottom;
    private boolean hasEffect;
    int left;
    private Paint locationPaint;
    private float mHeightMode;
    private Paint mPaint;
    private float mWidthMode;
    int right;
    private Shadow shadow;
    private int shadowColor;
    private float shadowRadius;
    private int shadowType;
    int top;
    private float xOffset;
    private float yOffset;
    public static final float SHADOW_DEFAULT_RADIUS = DimenUtil.dp2px(5.0f);
    public static final float SHADOW_MAX_OFFSET = DimenUtil.dp2px(20.0f);
    public static final float SHADOW_MAX_BLUR = DimenUtil.dp2px(20.0f);
    public static final float SHADOW_DEFAULT_BLUR_RADIUS = DimenUtil.dp2px(5.0f);

    public ShadowLayout(Context context) {
        super(context, null);
        this.shadowColor = Color.parseColor("#333333");
        this.shadowRadius = 0.0f;
        this.blurRadius = SHADOW_DEFAULT_BLUR_RADIUS;
        this.xOffset = DimenUtil.dp2px(10.0f);
        this.yOffset = DimenUtil.dp2px(10.0f);
        this.bgColor = -1;
        this.hasEffect = false;
        this.left = 0;
        this.right = 0;
        this.top = 0;
        this.bottom = 0;
        this.shadow = new ShadowConfig(this);
        this.mPaint = new Paint();
        this.locationPaint = new Paint();
    }

    public ShadowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.shadowColor = Color.parseColor("#333333");
        this.shadowRadius = 0.0f;
        float f = SHADOW_DEFAULT_BLUR_RADIUS;
        this.blurRadius = f;
        this.xOffset = DimenUtil.dp2px(10.0f);
        this.yOffset = DimenUtil.dp2px(10.0f);
        this.bgColor = -1;
        this.hasEffect = false;
        this.left = 0;
        this.right = 0;
        this.top = 0;
        this.bottom = 0;
        this.shadow = new ShadowConfig(this);
        this.mPaint = new Paint();
        this.locationPaint = new Paint();
        setLayerType(1, null);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.ShadowLayout);
        this.shadowColor = typedArrayObtainStyledAttributes.getColor(3, -16776961);
        this.blurRadius = typedArrayObtainStyledAttributes.getDimension(1, f);
        this.shadowRadius = typedArrayObtainStyledAttributes.getDimension(4, 0.0f);
        this.hasEffect = typedArrayObtainStyledAttributes.getBoolean(2, false);
        this.xOffset = typedArrayObtainStyledAttributes.getDimension(5, DimenUtil.dp2px(10.0f));
        this.yOffset = typedArrayObtainStyledAttributes.getDimension(6, DimenUtil.dp2px(10.0f));
        this.bgColor = typedArrayObtainStyledAttributes.getColor(0, -1);
        typedArrayObtainStyledAttributes.recycle();
        float f2 = this.shadowRadius;
        if (f2 < 0.0f) {
            this.shadowRadius = -f2;
        }
        float f3 = this.blurRadius;
        if (f3 < 0.0f) {
            this.blurRadius = -f3;
        }
        this.blurRadius = Math.min(SHADOW_MAX_BLUR, this.blurRadius);
        float fAbs = Math.abs(this.xOffset);
        float f4 = SHADOW_MAX_OFFSET;
        if (fAbs > f4) {
            float f5 = this.xOffset;
            this.xOffset = (f5 / Math.abs(f5)) * f4;
        }
        if (Math.abs(this.yOffset) > f4) {
            float f6 = this.yOffset;
            this.yOffset = (f6 / Math.abs(f6)) * f4;
        }
        setBackgroundColor(Color.parseColor("#00ffffff"));
        init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init() {
        float f = this.xOffset;
        if (f > 0.0f) {
            this.right = (int) (this.blurRadius + Math.abs(f));
        } else if (f == 0.0f) {
            float f2 = this.blurRadius;
            this.left = (int) f2;
            this.right = (int) f2;
        } else {
            this.left = (int) (this.blurRadius + Math.abs(f));
        }
        float f3 = this.yOffset;
        if (f3 > 0.0f) {
            this.bottom = (int) (this.blurRadius + Math.abs(f3));
        } else if (f3 == 0.0f) {
            float f4 = this.blurRadius;
            this.top = (int) f4;
            this.bottom = (int) f4;
        } else {
            this.top = (int) (this.blurRadius + Math.abs(f3));
        }
        setPadding(this.left, this.top, this.right, this.bottom);
    }

    public Shadow getShadowConfig() {
        return this.shadow;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void drawBackground(Canvas canvas) {
        float f;
        float f2;
        float f3;
        float f4;
        this.mWidthMode = getMeasuredWidth();
        float measuredHeight = getMeasuredHeight();
        this.mHeightMode = measuredHeight;
        if (this.xOffset == 0.0f) {
            f = this.right;
            f2 = this.mWidthMode - this.blurRadius;
        } else {
            float f5 = this.right;
            float f6 = this.blurRadius;
            f = f5 + f6;
            f2 = (this.mWidthMode - this.left) - f6;
        }
        if (this.yOffset == 0.0f) {
            f4 = this.bottom;
            f3 = this.blurRadius;
        } else {
            float f7 = this.bottom;
            f3 = this.blurRadius;
            f4 = f7 + f3;
            measuredHeight -= this.top;
        }
        float f8 = measuredHeight - f3;
        if (this.blurRadius > 0.0f) {
            this.mPaint.setMaskFilter(new BlurMaskFilter(this.blurRadius, BlurMaskFilter.Blur.NORMAL));
        }
        this.mPaint.setColor(this.shadowColor);
        this.mPaint.setAntiAlias(true);
        RectF rectF = new RectF(f, f4, f2, f8);
        RectF rectF2 = new RectF(this.left, this.top, this.mWidthMode - this.right, this.mHeightMode - this.bottom);
        float f9 = this.shadowRadius;
        if (f9 == 0.0f) {
            canvas.drawRect(rectF, this.mPaint);
        } else {
            canvas.drawRoundRect(rectF, f9, f9, this.mPaint);
        }
        this.locationPaint.setColor(this.bgColor);
        this.locationPaint.setAntiAlias(true);
        float f10 = this.shadowRadius;
        if (f10 == 0.0f) {
            canvas.drawRect(rectF2, this.locationPaint);
        } else {
            canvas.drawRoundRect(rectF2, f10, f10, this.locationPaint);
        }
    }

    class ShadowConfig implements Shadow {
        private ShadowLayout shadow;

        private ShadowConfig(ShadowLayout shadow) {
            this.shadow = shadow;
        }

        @Override // com.android.systemui.shadow.Shadow
        public Shadow setShadowRadius(float radius) {
            return setShadowRadius(1, radius);
        }

        @Override // com.android.systemui.shadow.Shadow
        public Shadow setShadowRadius(int unit, float radius) {
            Resources resources;
            Context context = ShadowLayout.this.getContext();
            if (context == null) {
                resources = Resources.getSystem();
            } else {
                resources = context.getResources();
            }
            this.shadow.shadowRadius = Math.abs(TypedValue.applyDimension(unit, radius, resources.getDisplayMetrics()));
            return this;
        }

        @Override // com.android.systemui.shadow.Shadow
        public Shadow setShadowColor(int color) {
            this.shadow.shadowColor = color;
            return this;
        }

        @Override // com.android.systemui.shadow.Shadow
        public Shadow setShadowColorRes(int colorRes) {
            ShadowLayout shadowLayout = this.shadow;
            shadowLayout.shadowColor = shadowLayout.getResources().getColor(colorRes);
            return this;
        }

        @Override // com.android.systemui.shadow.Shadow
        public Shadow setBlurRadius(float radius) {
            return setBlurRadius(1, radius);
        }

        @Override // com.android.systemui.shadow.Shadow
        public Shadow setBlurRadius(int unit, float radius) {
            Resources resources;
            Context context = ShadowLayout.this.getContext();
            if (context == null) {
                resources = Resources.getSystem();
            } else {
                resources = context.getResources();
            }
            this.shadow.blurRadius = Math.min(ShadowLayout.SHADOW_MAX_BLUR, Math.abs(TypedValue.applyDimension(unit, radius, resources.getDisplayMetrics())));
            return this;
        }

        @Override // com.android.systemui.shadow.Shadow
        public Shadow setXOffset(float offset) {
            return setXOffset(1, offset);
        }

        @Override // com.android.systemui.shadow.Shadow
        public Shadow setXOffset(int unit, float offset) {
            Resources resources;
            Context context = ShadowLayout.this.getContext();
            if (context == null) {
                resources = Resources.getSystem();
            } else {
                resources = context.getResources();
            }
            float fApplyDimension = TypedValue.applyDimension(unit, offset, resources.getDisplayMetrics());
            if (Math.abs(fApplyDimension) > ShadowLayout.SHADOW_MAX_OFFSET) {
                fApplyDimension = (fApplyDimension / Math.abs(fApplyDimension)) * ShadowLayout.SHADOW_MAX_OFFSET;
            }
            this.shadow.xOffset = fApplyDimension;
            return this;
        }

        @Override // com.android.systemui.shadow.Shadow
        public Shadow setYOffset(float offset) {
            return setYOffset(1, offset);
        }

        @Override // com.android.systemui.shadow.Shadow
        public Shadow setYOffset(int unit, float offset) {
            Resources resources;
            Context context = ShadowLayout.this.getContext();
            if (context == null) {
                resources = Resources.getSystem();
            } else {
                resources = context.getResources();
            }
            float fApplyDimension = TypedValue.applyDimension(unit, offset, resources.getDisplayMetrics());
            if (Math.abs(fApplyDimension) > ShadowLayout.SHADOW_MAX_OFFSET) {
                fApplyDimension = (fApplyDimension / Math.abs(fApplyDimension)) * ShadowLayout.SHADOW_MAX_OFFSET;
            }
            this.shadow.yOffset = fApplyDimension;
            return this;
        }

        @Override // com.android.systemui.shadow.Shadow
        public void commit() {
            this.shadow.init();
            this.shadow.requestLayout();
            this.shadow.postInvalidate();
        }
    }
}
