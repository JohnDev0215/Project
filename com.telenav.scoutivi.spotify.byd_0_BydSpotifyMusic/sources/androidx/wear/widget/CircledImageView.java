package androidx.wear.widget;

import android.R;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.View;
import i0.u;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class CircledImageView extends View {
    private static final ArgbEvaluator K = new ArgbEvaluator();
    private boolean A;
    private boolean B;
    private long C;
    private float D;
    private float E;
    private Integer F;
    private Integer G;
    int H;
    private final ValueAnimator.AnimatorUpdateListener I;
    private ValueAnimator J;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final RectF f3362e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Paint f3363g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final c f3364h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final float f3365i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final androidx.wear.widget.b f3366j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final Rect f3367k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final Drawable.Callback f3368l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private ColorStateList f3369m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Drawable f3370n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private float f3371o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private float f3372p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private float f3373q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private float f3374r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private float f3375s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f3376t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private Paint.Cap f3377u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private float f3378v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private boolean f3379w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private float f3380x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private boolean f3381y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private boolean f3382z;

    class a implements Drawable.Callback {
        a() {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            CircledImageView.this.invalidate();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j7) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        }
    }

    class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            CircledImageView circledImageView = CircledImageView.this;
            if (iIntValue != circledImageView.H) {
                circledImageView.H = iIntValue;
                circledImageView.invalidate();
            }
        }
    }

    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int[] f3385a = {-16777216, 0};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final float[] f3386b = {0.6f, 1.0f};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final RectF f3387c = new RectF();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final float f3388d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Paint f3389e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private float f3390f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        float f3391g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private float f3392h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private float f3393i;

        c(float f7, float f8, float f9, float f10) {
            Paint paint = new Paint();
            this.f3389e = paint;
            this.f3388d = f7;
            this.f3391g = f8;
            this.f3392h = f9;
            this.f3393i = f10;
            this.f3390f = f9 + f10 + (f7 * f8);
            paint.setColor(-16777216);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            f();
        }

        private void f() {
            float f7 = this.f3392h + this.f3393i + (this.f3388d * this.f3391g);
            this.f3390f = f7;
            if (f7 > 0.0f) {
                this.f3389e.setShader(new RadialGradient(this.f3387c.centerX(), this.f3387c.centerY(), this.f3390f, this.f3385a, this.f3386b, Shader.TileMode.MIRROR));
            }
        }

        void a(Canvas canvas, float f7) {
            if (this.f3388d <= 0.0f || this.f3391g <= 0.0f) {
                return;
            }
            this.f3389e.setAlpha(Math.round(r0.getAlpha() * f7));
            canvas.drawCircle(this.f3387c.centerX(), this.f3387c.centerY(), this.f3390f, this.f3389e);
        }

        void b(int i7, int i8, int i9, int i10) {
            this.f3387c.set(i7, i8, i9, i10);
            f();
        }

        void c(float f7) {
            this.f3393i = f7;
            f();
        }

        void d(float f7) {
            this.f3392h = f7;
            f();
        }

        void e(float f7) {
            this.f3391g = f7;
            f();
        }
    }

    public CircledImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void a() {
        int colorForState = this.f3369m.getColorForState(getDrawableState(), this.f3369m.getDefaultColor());
        if (this.C <= 0) {
            if (colorForState != this.H) {
                this.H = colorForState;
                invalidate();
                return;
            }
            return;
        }
        ValueAnimator valueAnimator = this.J;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        } else {
            this.J = new ValueAnimator();
        }
        this.J.setIntValues(this.H, colorForState);
        this.J.setEvaluator(K);
        this.J.setDuration(this.C);
        this.J.addUpdateListener(this.I);
        this.J.start();
    }

    public void b(boolean z6) {
        this.f3382z = z6;
        androidx.wear.widget.b bVar = this.f3366j;
        if (bVar != null) {
            if (z6 && this.A && this.B) {
                bVar.d();
            } else {
                bVar.e();
            }
        }
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        a();
    }

    public ColorStateList getCircleColorStateList() {
        return this.f3369m;
    }

    public float getCircleRadius() {
        float fMax = this.f3371o;
        if (fMax <= 0.0f && this.f3372p > 0.0f) {
            fMax = Math.max(getMeasuredHeight(), getMeasuredWidth()) * this.f3372p;
        }
        return fMax - this.f3375s;
    }

    public float getCircleRadiusPercent() {
        return this.f3372p;
    }

    public float getCircleRadiusPressed() {
        float fMax = this.f3373q;
        if (fMax <= 0.0f && this.f3374r > 0.0f) {
            fMax = Math.max(getMeasuredHeight(), getMeasuredWidth()) * this.f3374r;
        }
        return fMax - this.f3375s;
    }

    public float getCircleRadiusPressedPercent() {
        return this.f3374r;
    }

    public long getColorChangeAnimationDuration() {
        return this.C;
    }

    public int getDefaultCircleColor() {
        return this.f3369m.getDefaultColor();
    }

    public Drawable getImageDrawable() {
        return this.f3370n;
    }

    public float getInitialCircleRadius() {
        return this.f3365i;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        float circleRadiusPressed = this.f3381y ? getCircleRadiusPressed() : getCircleRadius();
        this.f3364h.a(canvas, getAlpha());
        if (this.f3378v > 0.0f) {
            this.f3362e.set(paddingLeft, paddingTop, getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            RectF rectF = this.f3362e;
            rectF.set(rectF.centerX() - circleRadiusPressed, this.f3362e.centerY() - circleRadiusPressed, this.f3362e.centerX() + circleRadiusPressed, this.f3362e.centerY() + circleRadiusPressed);
            this.f3363g.setColor(this.f3376t);
            this.f3363g.setAlpha(Math.round(r3.getAlpha() * getAlpha()));
            this.f3363g.setStyle(Paint.Style.STROKE);
            this.f3363g.setStrokeWidth(this.f3378v);
            this.f3363g.setStrokeCap(this.f3377u);
            if (this.f3382z) {
                this.f3362e.roundOut(this.f3367k);
                this.f3366j.setBounds(this.f3367k);
                this.f3366j.b(this.f3376t);
                this.f3366j.c(this.f3378v);
                this.f3366j.draw(canvas);
            } else {
                canvas.drawArc(this.f3362e, -90.0f, this.f3380x * 360.0f, false, this.f3363g);
            }
        }
        if (!this.f3379w) {
            this.f3362e.set(paddingLeft, paddingTop, getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            this.f3363g.setColor(this.H);
            this.f3363g.setAlpha(Math.round(r0.getAlpha() * getAlpha()));
            this.f3363g.setStyle(Paint.Style.FILL);
            canvas.drawCircle(this.f3362e.centerX(), this.f3362e.centerY(), circleRadiusPressed, this.f3363g);
        }
        Drawable drawable = this.f3370n;
        if (drawable != null) {
            drawable.setAlpha(Math.round(getAlpha() * 255.0f));
            Integer num = this.F;
            if (num != null) {
                this.f3370n.setTint(num.intValue());
            }
            this.f3370n.draw(canvas);
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        Drawable drawable = this.f3370n;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = this.f3370n.getIntrinsicHeight();
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            float f7 = this.D;
            if (f7 <= 0.0f) {
                f7 = 1.0f;
            }
            float f8 = intrinsicWidth;
            float f9 = intrinsicHeight;
            float fMin = Math.min(1.0f, Math.min(f8 != 0.0f ? (measuredWidth * f7) / f8 : 1.0f, f9 != 0.0f ? (f7 * measuredHeight) / f9 : 1.0f));
            int iRound = Math.round(f8 * fMin);
            int iRound2 = Math.round(fMin * f9);
            int iRound3 = ((measuredWidth - iRound) / 2) + Math.round(this.E * iRound);
            int i11 = (measuredHeight - iRound2) / 2;
            this.f3370n.setBounds(iRound3, i11, iRound + iRound3, iRound2 + i11);
        }
        super.onLayout(z6, i7, i8, i9, i10);
    }

    @Override // android.view.View
    protected void onMeasure(int i7, int i8) {
        float circleRadius = getCircleRadius() + this.f3378v;
        c cVar = this.f3364h;
        float f7 = (circleRadius + (cVar.f3388d * cVar.f3391g)) * 2.0f;
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        int mode2 = View.MeasureSpec.getMode(i8);
        int size2 = View.MeasureSpec.getSize(i8);
        if (mode != 1073741824) {
            size = mode == Integer.MIN_VALUE ? (int) Math.min(f7, size) : (int) f7;
        }
        if (mode2 != 1073741824) {
            size2 = mode2 == Integer.MIN_VALUE ? (int) Math.min(f7, size2) : (int) f7;
        }
        Integer num = this.G;
        if (num != null) {
            int iIntValue = num.intValue();
            if (iIntValue == 1) {
                size = size2;
            } else if (iIntValue == 2) {
                size2 = size;
            }
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(size2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
    }

    @Override // android.view.View
    protected boolean onSetAlpha(int i7) {
        return true;
    }

    @Override // android.view.View
    public void onSizeChanged(int i7, int i8, int i9, int i10) {
        if (i7 == i9 && i8 == i10) {
            return;
        }
        this.f3364h.b(getPaddingLeft(), getPaddingTop(), i7 - getPaddingRight(), i8 - getPaddingBottom());
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i7) {
        super.onVisibilityChanged(view, i7);
        this.A = i7 == 0;
        b(this.f3382z);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i7) {
        super.onWindowVisibilityChanged(i7);
        this.B = i7 == 0;
        b(this.f3382z);
    }

    public void setCircleBorderCap(Paint.Cap cap) {
        if (cap != this.f3377u) {
            this.f3377u = cap;
            invalidate();
        }
    }

    public void setCircleBorderColor(int i7) {
        this.f3376t = i7;
    }

    public void setCircleBorderWidth(float f7) {
        if (f7 != this.f3378v) {
            this.f3378v = f7;
            this.f3364h.c(f7);
            invalidate();
        }
    }

    public void setCircleColor(int i7) {
        setCircleColorStateList(ColorStateList.valueOf(i7));
    }

    public void setCircleColorStateList(ColorStateList colorStateList) {
        if (Objects.equals(colorStateList, this.f3369m)) {
            return;
        }
        this.f3369m = colorStateList;
        a();
        invalidate();
    }

    public void setCircleHidden(boolean z6) {
        if (z6 != this.f3379w) {
            this.f3379w = z6;
            invalidate();
        }
    }

    public void setCircleRadius(float f7) {
        if (f7 != this.f3371o) {
            this.f3371o = f7;
            this.f3364h.d(this.f3381y ? getCircleRadiusPressed() : getCircleRadius());
            invalidate();
        }
    }

    public void setCircleRadiusPercent(float f7) {
        if (f7 != this.f3372p) {
            this.f3372p = f7;
            this.f3364h.d(this.f3381y ? getCircleRadiusPressed() : getCircleRadius());
            invalidate();
        }
    }

    public void setCircleRadiusPressed(float f7) {
        if (f7 != this.f3373q) {
            this.f3373q = f7;
            invalidate();
        }
    }

    public void setCircleRadiusPressedPercent(float f7) {
        if (f7 != this.f3374r) {
            this.f3374r = f7;
            this.f3364h.d(this.f3381y ? getCircleRadiusPressed() : getCircleRadius());
            invalidate();
        }
    }

    public void setColorChangeAnimationDuration(long j7) {
        this.C = j7;
    }

    public void setImageCirclePercentage(float f7) {
        float fMax = Math.max(0.0f, Math.min(1.0f, f7));
        if (fMax != this.D) {
            this.D = fMax;
            invalidate();
        }
    }

    public void setImageDrawable(Drawable drawable) {
        Drawable drawable2 = this.f3370n;
        if (drawable != drawable2) {
            this.f3370n = drawable;
            if (drawable != null && drawable.getConstantState() != null) {
                this.f3370n = this.f3370n.getConstantState().newDrawable(getResources(), getContext().getTheme()).mutate();
            }
            if (drawable != null && drawable2 != null && drawable2.getIntrinsicHeight() == drawable.getIntrinsicHeight() && drawable2.getIntrinsicWidth() == drawable.getIntrinsicWidth()) {
                this.f3370n.setBounds(drawable2.getBounds());
            } else {
                requestLayout();
            }
            invalidate();
        }
    }

    public void setImageHorizontalOffcenterPercentage(float f7) {
        if (f7 != this.E) {
            this.E = f7;
            invalidate();
        }
    }

    public void setImageResource(int i7) {
        setImageDrawable(i7 == 0 ? null : getContext().getDrawable(i7));
    }

    public void setImageTint(int i7) {
        Integer num = this.F;
        if (num == null || i7 != num.intValue()) {
            this.F = Integer.valueOf(i7);
            invalidate();
        }
    }

    @Override // android.view.View
    public void setPadding(int i7, int i8, int i9, int i10) {
        if (i7 != getPaddingLeft() || i8 != getPaddingTop() || i9 != getPaddingRight() || i10 != getPaddingBottom()) {
            this.f3364h.b(i7, i8, getWidth() - i9, getHeight() - i10);
        }
        super.setPadding(i7, i8, i9, i10);
    }

    @Override // android.view.View
    public void setPressed(boolean z6) {
        super.setPressed(z6);
        if (z6 != this.f3381y) {
            this.f3381y = z6;
            this.f3364h.d(z6 ? getCircleRadiusPressed() : getCircleRadius());
            invalidate();
        }
    }

    public void setProgress(float f7) {
        if (f7 != this.f3380x) {
            this.f3380x = f7;
            invalidate();
        }
    }

    public void setShadowVisibility(float f7) {
        c cVar = this.f3364h;
        if (f7 != cVar.f3391g) {
            cVar.e(f7);
            invalidate();
        }
    }

    public CircledImageView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f3367k = new Rect();
        a aVar = new a();
        this.f3368l = aVar;
        this.f3379w = false;
        this.f3380x = 1.0f;
        this.f3381y = false;
        this.C = 0L;
        this.D = 1.0f;
        this.E = 0.0f;
        this.I = new b();
        Context context2 = getContext();
        int[] iArr = i1.b.CircledImageView;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr);
        u.i0(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(i1.b.CircledImageView_android_src);
        this.f3370n = drawable;
        if (drawable != null && drawable.getConstantState() != null) {
            Drawable drawableNewDrawable = this.f3370n.getConstantState().newDrawable(context.getResources(), context.getTheme());
            this.f3370n = drawableNewDrawable;
            this.f3370n = drawableNewDrawable.mutate();
        }
        ColorStateList colorStateList = typedArrayObtainStyledAttributes.getColorStateList(i1.b.CircledImageView_background_color);
        this.f3369m = colorStateList;
        if (colorStateList == null) {
            this.f3369m = ColorStateList.valueOf(context.getColor(R.color.darker_gray));
        }
        float dimension = typedArrayObtainStyledAttributes.getDimension(i1.b.CircledImageView_background_radius, 0.0f);
        this.f3371o = dimension;
        this.f3365i = dimension;
        this.f3373q = typedArrayObtainStyledAttributes.getDimension(i1.b.CircledImageView_background_radius_pressed, dimension);
        this.f3376t = typedArrayObtainStyledAttributes.getColor(i1.b.CircledImageView_background_border_color, -16777216);
        this.f3377u = Paint.Cap.values()[typedArrayObtainStyledAttributes.getInt(i1.b.CircledImageView_background_border_cap, 0)];
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(i1.b.CircledImageView_background_border_width, 0.0f);
        this.f3378v = dimension2;
        if (dimension2 > 0.0f) {
            this.f3375s += dimension2 / 2.0f;
        }
        float dimension3 = typedArrayObtainStyledAttributes.getDimension(i1.b.CircledImageView_img_padding, 0.0f);
        if (dimension3 > 0.0f) {
            this.f3375s += dimension3;
        }
        this.D = typedArrayObtainStyledAttributes.getFloat(i1.b.CircledImageView_img_circle_percentage, 0.0f);
        this.E = typedArrayObtainStyledAttributes.getFloat(i1.b.CircledImageView_img_horizontal_offset_percentage, 0.0f);
        int i8 = i1.b.CircledImageView_img_tint;
        if (typedArrayObtainStyledAttributes.hasValue(i8)) {
            this.F = Integer.valueOf(typedArrayObtainStyledAttributes.getColor(i8, 0));
        }
        int i9 = i1.b.CircledImageView_clip_dimen;
        if (typedArrayObtainStyledAttributes.hasValue(i9)) {
            this.G = Integer.valueOf(typedArrayObtainStyledAttributes.getInt(i9, 0));
        }
        float fraction = typedArrayObtainStyledAttributes.getFraction(i1.b.CircledImageView_background_radius_percent, 1, 1, 0.0f);
        this.f3372p = fraction;
        this.f3374r = typedArrayObtainStyledAttributes.getFraction(i1.b.CircledImageView_background_radius_pressed_percent, 1, 1, fraction);
        float dimension4 = typedArrayObtainStyledAttributes.getDimension(i1.b.CircledImageView_background_shadow_width, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        this.f3362e = new RectF();
        Paint paint = new Paint();
        this.f3363g = paint;
        paint.setAntiAlias(true);
        this.f3364h = new c(dimension4, 0.0f, getCircleRadius(), this.f3378v);
        androidx.wear.widget.b bVar = new androidx.wear.widget.b();
        this.f3366j = bVar;
        bVar.setCallback(aVar);
        setWillNotDraw(false);
        a();
    }
}
