package androidx.leanback.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.core.view.ViewCompat;
import androidx.leanback.R;

/* JADX INFO: loaded from: classes3.dex */
public class PagingIndicator extends View {
    private static final TimeInterpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final Property<Dot, Float> DOT_ALPHA = new Property<Dot, Float>(Float.class, "alpha") { // from class: androidx.leanback.widget.PagingIndicator.1
        @Override // android.util.Property
        public Float get(Dot dot) {
            return Float.valueOf(dot.getAlpha());
        }

        @Override // android.util.Property
        public void set(Dot dot, Float value) {
            dot.setAlpha(value.floatValue());
        }
    };
    private static final Property<Dot, Float> DOT_DIAMETER = new Property<Dot, Float>(Float.class, "diameter") { // from class: androidx.leanback.widget.PagingIndicator.2
        @Override // android.util.Property
        public Float get(Dot dot) {
            return Float.valueOf(dot.getDiameter());
        }

        @Override // android.util.Property
        public void set(Dot dot, Float value) {
            dot.setDiameter(value.floatValue());
        }
    };
    private static final Property<Dot, Float> DOT_TRANSLATION_X = new Property<Dot, Float>(Float.class, "translation_x") { // from class: androidx.leanback.widget.PagingIndicator.3
        @Override // android.util.Property
        public Float get(Dot dot) {
            return Float.valueOf(dot.getTranslationX());
        }

        @Override // android.util.Property
        public void set(Dot dot, Float value) {
            dot.setTranslationX(value.floatValue());
        }
    };
    private static final long DURATION_ALPHA = 167;
    private static final long DURATION_DIAMETER = 417;
    private static final long DURATION_TRANSLATION_X = 417;
    private final AnimatorSet mAnimator;
    Bitmap mArrow;
    final int mArrowDiameter;
    private final int mArrowGap;
    Paint mArrowPaint;
    final int mArrowRadius;
    final Rect mArrowRect;
    final float mArrowToBgRatio;
    final Paint mBgPaint;
    private int mCurrentPage;
    int mDotCenterY;
    final int mDotDiameter;
    int mDotFgSelectColor;
    private final int mDotGap;
    final int mDotRadius;
    private int[] mDotSelectedNextX;
    private int[] mDotSelectedPrevX;
    private int[] mDotSelectedX;
    private Dot[] mDots;
    final Paint mFgPaint;
    private final AnimatorSet mHideAnimator;
    boolean mIsLtr;
    private int mPageCount;
    private int mPreviousPage;
    private final int mShadowRadius;
    private final AnimatorSet mShowAnimator;

    public PagingIndicator(Context context) {
        this(context, null, 0);
    }

    public PagingIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagingIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mAnimator = new AnimatorSet();
        Resources res = getResources();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PagingIndicator, defStyle, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, R.styleable.PagingIndicator, attrs, typedArray, defStyle, 0);
        int dimensionFromTypedArray = getDimensionFromTypedArray(typedArray, R.styleable.PagingIndicator_lbDotRadius, R.dimen.lb_page_indicator_dot_radius);
        this.mDotRadius = dimensionFromTypedArray;
        this.mDotDiameter = dimensionFromTypedArray * 2;
        int dimensionFromTypedArray2 = getDimensionFromTypedArray(typedArray, R.styleable.PagingIndicator_arrowRadius, R.dimen.lb_page_indicator_arrow_radius);
        this.mArrowRadius = dimensionFromTypedArray2;
        this.mArrowDiameter = dimensionFromTypedArray2 * 2;
        this.mDotGap = getDimensionFromTypedArray(typedArray, R.styleable.PagingIndicator_dotToDotGap, R.dimen.lb_page_indicator_dot_gap);
        this.mArrowGap = getDimensionFromTypedArray(typedArray, R.styleable.PagingIndicator_dotToArrowGap, R.dimen.lb_page_indicator_arrow_gap);
        int dotBgColor = getColorFromTypedArray(typedArray, R.styleable.PagingIndicator_dotBgColor, R.color.lb_page_indicator_dot);
        Paint paint = new Paint(1);
        this.mBgPaint = paint;
        paint.setColor(dotBgColor);
        this.mDotFgSelectColor = getColorFromTypedArray(typedArray, R.styleable.PagingIndicator_arrowBgColor, R.color.lb_page_indicator_arrow_background);
        if (this.mArrowPaint == null && typedArray.hasValue(R.styleable.PagingIndicator_arrowColor)) {
            setArrowColor(typedArray.getColor(R.styleable.PagingIndicator_arrowColor, 0));
        }
        typedArray.recycle();
        this.mIsLtr = res.getConfiguration().getLayoutDirection() == 0;
        int shadowColor = res.getColor(R.color.lb_page_indicator_arrow_shadow);
        this.mShadowRadius = res.getDimensionPixelSize(R.dimen.lb_page_indicator_arrow_shadow_radius);
        this.mFgPaint = new Paint(1);
        int shadowOffset = res.getDimensionPixelSize(R.dimen.lb_page_indicator_arrow_shadow_offset);
        this.mFgPaint.setShadowLayer(this.mShadowRadius, shadowOffset, shadowOffset, shadowColor);
        this.mArrow = loadArrow();
        this.mArrowRect = new Rect(0, 0, this.mArrow.getWidth(), this.mArrow.getHeight());
        this.mArrowToBgRatio = this.mArrow.getWidth() / this.mArrowDiameter;
        AnimatorSet animatorSet = new AnimatorSet();
        this.mShowAnimator = animatorSet;
        animatorSet.playTogether(createDotAlphaAnimator(0.0f, 1.0f), createDotDiameterAnimator(this.mDotRadius * 2, this.mArrowRadius * 2), createDotTranslationXAnimator());
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mHideAnimator = animatorSet2;
        animatorSet2.playTogether(createDotAlphaAnimator(1.0f, 0.0f), createDotDiameterAnimator(this.mArrowRadius * 2, this.mDotRadius * 2), createDotTranslationXAnimator());
        this.mAnimator.playTogether(this.mShowAnimator, this.mHideAnimator);
        setLayerType(1, null);
    }

    private int getDimensionFromTypedArray(TypedArray typedArray, int attr, int defaultId) {
        return typedArray.getDimensionPixelOffset(attr, getResources().getDimensionPixelOffset(defaultId));
    }

    private int getColorFromTypedArray(TypedArray typedArray, int attr, int defaultId) {
        return typedArray.getColor(attr, getResources().getColor(defaultId));
    }

    private Bitmap loadArrow() {
        Bitmap arrow = BitmapFactory.decodeResource(getResources(), R.drawable.lb_ic_nav_arrow);
        if (this.mIsLtr) {
            return arrow;
        }
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(arrow, 0, 0, arrow.getWidth(), arrow.getHeight(), matrix, false);
    }

    public void setArrowColor(int color) {
        if (this.mArrowPaint == null) {
            this.mArrowPaint = new Paint();
        }
        this.mArrowPaint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
    }

    public void setDotBackgroundColor(int color) {
        this.mBgPaint.setColor(color);
    }

    public void setArrowBackgroundColor(int color) {
        this.mDotFgSelectColor = color;
    }

    private Animator createDotAlphaAnimator(float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat((Object) null, DOT_ALPHA, from, to);
        animator.setDuration(DURATION_ALPHA);
        animator.setInterpolator(DECELERATE_INTERPOLATOR);
        return animator;
    }

    private Animator createDotDiameterAnimator(float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat((Object) null, DOT_DIAMETER, from, to);
        animator.setDuration(417L);
        animator.setInterpolator(DECELERATE_INTERPOLATOR);
        return animator;
    }

    private Animator createDotTranslationXAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat((Object) null, DOT_TRANSLATION_X, (-this.mArrowGap) + this.mDotGap, 0.0f);
        animator.setDuration(417L);
        animator.setInterpolator(DECELERATE_INTERPOLATOR);
        return animator;
    }

    public void setPageCount(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("The page count should be a positive integer");
        }
        this.mPageCount = pages;
        this.mDots = new Dot[pages];
        for (int i = 0; i < this.mPageCount; i++) {
            this.mDots[i] = new Dot();
        }
        calculateDotPositions();
        setSelectedPage(0);
    }

    public void onPageSelected(int pageIndex, boolean withAnimation) {
        if (this.mCurrentPage == pageIndex) {
            return;
        }
        if (this.mAnimator.isStarted()) {
            this.mAnimator.end();
        }
        int i = this.mCurrentPage;
        this.mPreviousPage = i;
        if (withAnimation) {
            this.mHideAnimator.setTarget(this.mDots[i]);
            this.mShowAnimator.setTarget(this.mDots[pageIndex]);
            this.mAnimator.start();
        }
        setSelectedPage(pageIndex);
    }

    private void calculateDotPositions() {
        int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = getWidth() - getPaddingRight();
        int requiredWidth = getRequiredWidth();
        int mid = (left + right) / 2;
        int i = this.mPageCount;
        int[] iArr = new int[i];
        this.mDotSelectedX = iArr;
        int[] iArr2 = new int[i];
        this.mDotSelectedPrevX = iArr2;
        int[] iArr3 = new int[i];
        this.mDotSelectedNextX = iArr3;
        if (this.mIsLtr) {
            int startLeft = mid - (requiredWidth / 2);
            int i2 = this.mDotRadius;
            int i3 = this.mDotGap;
            int i4 = this.mArrowGap;
            iArr[0] = ((startLeft + i2) - i3) + i4;
            iArr2[0] = startLeft + i2;
            iArr3[0] = ((i2 + startLeft) - (i3 * 2)) + (i4 * 2);
            for (int i5 = 1; i5 < this.mPageCount; i5++) {
                int[] iArr4 = this.mDotSelectedX;
                int[] iArr5 = this.mDotSelectedPrevX;
                int i6 = iArr5[i5 - 1];
                int i7 = this.mArrowGap;
                iArr4[i5] = i6 + i7;
                iArr5[i5] = iArr5[i5 - 1] + this.mDotGap;
                this.mDotSelectedNextX[i5] = iArr4[i5 - 1] + i7;
            }
        } else {
            int startRight = (requiredWidth / 2) + mid;
            int i8 = this.mDotRadius;
            int i9 = this.mDotGap;
            int i10 = this.mArrowGap;
            iArr[0] = ((startRight - i8) + i9) - i10;
            iArr2[0] = startRight - i8;
            iArr3[0] = ((startRight - i8) + (i9 * 2)) - (i10 * 2);
            for (int i11 = 1; i11 < this.mPageCount; i11++) {
                int[] iArr6 = this.mDotSelectedX;
                int[] iArr7 = this.mDotSelectedPrevX;
                int i12 = iArr7[i11 - 1];
                int i13 = this.mArrowGap;
                iArr6[i11] = i12 - i13;
                iArr7[i11] = iArr7[i11 - 1] - this.mDotGap;
                this.mDotSelectedNextX[i11] = iArr6[i11 - 1] - i13;
            }
        }
        int i14 = this.mArrowRadius;
        this.mDotCenterY = i14 + top;
        adjustDotPosition();
    }

    int getPageCount() {
        return this.mPageCount;
    }

    int[] getDotSelectedX() {
        return this.mDotSelectedX;
    }

    int[] getDotSelectedLeftX() {
        return this.mDotSelectedPrevX;
    }

    int[] getDotSelectedRightX() {
        return this.mDotSelectedNextX;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height;
        int width;
        int desiredHeight = getDesiredHeight();
        int mode = View.MeasureSpec.getMode(heightMeasureSpec);
        if (mode == Integer.MIN_VALUE) {
            int height2 = View.MeasureSpec.getSize(heightMeasureSpec);
            height = Math.min(desiredHeight, height2);
        } else if (mode == 1073741824) {
            height = View.MeasureSpec.getSize(heightMeasureSpec);
        } else {
            height = desiredHeight;
        }
        int desiredWidth = getDesiredWidth();
        int mode2 = View.MeasureSpec.getMode(widthMeasureSpec);
        if (mode2 == Integer.MIN_VALUE) {
            int width2 = View.MeasureSpec.getSize(widthMeasureSpec);
            width = Math.min(desiredWidth, width2);
        } else if (mode2 == 1073741824) {
            width = View.MeasureSpec.getSize(widthMeasureSpec);
        } else {
            width = desiredWidth;
        }
        setMeasuredDimension(width, height);
    }

    @Override // android.view.View
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        setMeasuredDimension(width, height);
        calculateDotPositions();
    }

    private int getDesiredHeight() {
        return getPaddingTop() + this.mArrowDiameter + getPaddingBottom() + this.mShadowRadius;
    }

    private int getRequiredWidth() {
        return (this.mDotRadius * 2) + (this.mArrowGap * 2) + ((this.mPageCount - 3) * this.mDotGap);
    }

    private int getDesiredWidth() {
        return getPaddingLeft() + getRequiredWidth() + getPaddingRight();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < this.mPageCount; i++) {
            this.mDots[i].draw(canvas);
        }
    }

    private void setSelectedPage(int now) {
        if (now == this.mCurrentPage) {
            return;
        }
        this.mCurrentPage = now;
        adjustDotPosition();
    }

    private void adjustDotPosition() {
        int i;
        int i2 = 0;
        while (true) {
            i = this.mCurrentPage;
            if (i2 >= i) {
                break;
            }
            this.mDots[i2].deselect();
            Dot dot = this.mDots[i2];
            if (i2 != this.mPreviousPage) {
                f = 1.0f;
            }
            dot.mDirection = f;
            this.mDots[i2].mCenterX = this.mDotSelectedPrevX[i2];
            i2++;
        }
        this.mDots[i].select();
        Dot[] dotArr = this.mDots;
        int i3 = this.mCurrentPage;
        dotArr[i3].mDirection = this.mPreviousPage >= i3 ? 1.0f : -1.0f;
        this.mDots[this.mCurrentPage].mCenterX = this.mDotSelectedX[r1];
        int i4 = this.mCurrentPage;
        while (true) {
            i4++;
            if (i4 < this.mPageCount) {
                this.mDots[i4].deselect();
                this.mDots[i4].mDirection = 1.0f;
                this.mDots[i4].mCenterX = this.mDotSelectedNextX[i4];
            } else {
                return;
            }
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int layoutDirection) {
        super.onRtlPropertiesChanged(layoutDirection);
        boolean isLtr = layoutDirection == 0;
        if (this.mIsLtr != isLtr) {
            this.mIsLtr = isLtr;
            this.mArrow = loadArrow();
            Dot[] dotArr = this.mDots;
            if (dotArr != null) {
                for (Dot dot : dotArr) {
                    dot.onRtlPropertiesChanged();
                }
            }
            calculateDotPositions();
            invalidate();
        }
    }

    public class Dot {
        static final float LEFT = -1.0f;
        static final float LTR = 1.0f;
        static final float RIGHT = 1.0f;
        static final float RTL = -1.0f;
        float mAlpha;
        float mArrowImageRadius;
        float mCenterX;
        float mDiameter;
        float mDirection = 1.0f;
        int mFgColor;
        float mLayoutDirection;
        float mRadius;
        float mTranslationX;

        public Dot() {
            this.mLayoutDirection = PagingIndicator.this.mIsLtr ? 1.0f : -1.0f;
        }

        void select() {
            this.mTranslationX = 0.0f;
            this.mCenterX = 0.0f;
            this.mDiameter = PagingIndicator.this.mArrowDiameter;
            float f = PagingIndicator.this.mArrowRadius;
            this.mRadius = f;
            this.mArrowImageRadius = f * PagingIndicator.this.mArrowToBgRatio;
            this.mAlpha = 1.0f;
            adjustAlpha();
        }

        void deselect() {
            this.mTranslationX = 0.0f;
            this.mCenterX = 0.0f;
            this.mDiameter = PagingIndicator.this.mDotDiameter;
            float f = PagingIndicator.this.mDotRadius;
            this.mRadius = f;
            this.mArrowImageRadius = f * PagingIndicator.this.mArrowToBgRatio;
            this.mAlpha = 0.0f;
            adjustAlpha();
        }

        public void adjustAlpha() {
            int alpha = Math.round(this.mAlpha * 255.0f);
            int red = Color.red(PagingIndicator.this.mDotFgSelectColor);
            int green = Color.green(PagingIndicator.this.mDotFgSelectColor);
            int blue = Color.blue(PagingIndicator.this.mDotFgSelectColor);
            this.mFgColor = Color.argb(alpha, red, green, blue);
        }

        public float getAlpha() {
            return this.mAlpha;
        }

        public void setAlpha(float alpha) {
            this.mAlpha = alpha;
            adjustAlpha();
            PagingIndicator.this.invalidate();
        }

        public float getTranslationX() {
            return this.mTranslationX;
        }

        public void setTranslationX(float translationX) {
            this.mTranslationX = this.mDirection * translationX * this.mLayoutDirection;
            PagingIndicator.this.invalidate();
        }

        public float getDiameter() {
            return this.mDiameter;
        }

        public void setDiameter(float diameter) {
            this.mDiameter = diameter;
            this.mRadius = diameter / 2.0f;
            this.mArrowImageRadius = (diameter / 2.0f) * PagingIndicator.this.mArrowToBgRatio;
            PagingIndicator.this.invalidate();
        }

        void draw(Canvas canvas) {
            float centerX = this.mCenterX + this.mTranslationX;
            canvas.drawCircle(centerX, PagingIndicator.this.mDotCenterY, this.mRadius, PagingIndicator.this.mBgPaint);
            if (this.mAlpha > 0.0f) {
                PagingIndicator.this.mFgPaint.setColor(this.mFgColor);
                canvas.drawCircle(centerX, PagingIndicator.this.mDotCenterY, this.mRadius, PagingIndicator.this.mFgPaint);
                Bitmap bitmap = PagingIndicator.this.mArrow;
                Rect rect = PagingIndicator.this.mArrowRect;
                int i = (int) (centerX - this.mArrowImageRadius);
                float f = PagingIndicator.this.mDotCenterY;
                float f2 = this.mArrowImageRadius;
                canvas.drawBitmap(bitmap, rect, new Rect(i, (int) (f - f2), (int) (f2 + centerX), (int) (PagingIndicator.this.mDotCenterY + this.mArrowImageRadius)), PagingIndicator.this.mArrowPaint);
            }
        }

        void onRtlPropertiesChanged() {
            this.mLayoutDirection = PagingIndicator.this.mIsLtr ? 1.0f : -1.0f;
        }
    }
}
