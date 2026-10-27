package androidx.wear.widget.drawer;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import i1.b;

/* JADX INFO: loaded from: classes.dex */
public class PageIndicatorView extends View implements ViewPager.j {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Paint f3404e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Paint f3405g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Paint f3406h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Paint f3407i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f3408j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private float f3409k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private float f3410l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f3411m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f3412n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f3413o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    int f3414p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    int f3415q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f3416r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private float f3417s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private float f3418t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private float f3419u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f3420v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private int f3421w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int f3422x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private int f3423y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    boolean f3424z;

    class a extends j1.a {
        a() {
        }

        @Override // j1.a
        public void a(Animator animator) {
            PageIndicatorView pageIndicatorView = PageIndicatorView.this;
            pageIndicatorView.f3424z = false;
            pageIndicatorView.animate().alpha(0.0f).setListener(null).setStartDelay(PageIndicatorView.this.f3414p).setDuration(PageIndicatorView.this.f3415q).start();
        }
    }

    public PageIndicatorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void d() {
        this.f3424z = true;
        animate().cancel();
        animate().alpha(1.0f).setStartDelay(0L).setDuration(this.f3416r).start();
    }

    private void e() {
        this.f3424z = true;
        animate().cancel();
        animate().alpha(1.0f).setStartDelay(0L).setDuration(this.f3416r).setListener(new a()).start();
    }

    private void f(long j7) {
        this.f3424z = false;
        animate().cancel();
        animate().alpha(0.0f).setStartDelay(j7).setDuration(this.f3415q).start();
    }

    private void g(int i7) {
        this.f3422x = i7;
        invalidate();
    }

    private void h(Paint paint, Paint paint2, float f7, float f8, int i7, int i8) {
        float f9 = f7 + f8;
        paint2.setShader(new RadialGradient(0.0f, 0.0f, f9, new int[]{i8, i8, 0}, new float[]{0.0f, f7 / f9, 1.0f}, Shader.TileMode.CLAMP));
        paint.setColor(i7);
        paint.setStyle(Paint.Style.FILL);
    }

    private void i() {
        h(this.f3404e, this.f3405g, this.f3409k, this.f3419u, this.f3411m, this.f3420v);
        h(this.f3406h, this.f3407i, this.f3410l, this.f3419u, this.f3412n, this.f3420v);
    }

    @Override // androidx.viewpager.widget.ViewPager.j
    public void a(int i7, float f7, int i8) {
        if (this.f3413o && this.f3423y == 1) {
            if (f7 != 0.0f) {
                if (this.f3424z) {
                    return;
                }
                d();
            } else if (this.f3424z) {
                f(0L);
            }
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.j
    public void b(int i7) {
        if (this.f3423y != i7) {
            this.f3423y = i7;
            if (this.f3413o && i7 == 0) {
                if (this.f3424z) {
                    f(this.f3414p);
                } else {
                    e();
                }
            }
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.j
    public void c(int i7) {
        if (i7 != this.f3422x) {
            g(i7);
        }
    }

    public int getDotColor() {
        return this.f3411m;
    }

    public int getDotColorSelected() {
        return this.f3412n;
    }

    public int getDotFadeInDuration() {
        return this.f3416r;
    }

    public int getDotFadeOutDelay() {
        return this.f3414p;
    }

    public int getDotFadeOutDuration() {
        return this.f3415q;
    }

    public boolean getDotFadeWhenIdle() {
        return this.f3413o;
    }

    public float getDotRadius() {
        return this.f3409k;
    }

    public float getDotRadiusSelected() {
        return this.f3410l;
    }

    public int getDotShadowColor() {
        return this.f3420v;
    }

    public float getDotShadowDx() {
        return this.f3417s;
    }

    public float getDotShadowDy() {
        return this.f3418t;
    }

    public float getDotShadowRadius() {
        return this.f3419u;
    }

    public float getDotSpacing() {
        return this.f3408j;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f3421w > 1) {
            canvas.save();
            canvas.translate(getPaddingLeft() + (this.f3408j / 2.0f), getHeight() / 2.0f);
            for (int i7 = 0; i7 < this.f3421w; i7++) {
                if (i7 == this.f3422x) {
                    canvas.drawCircle(this.f3417s, this.f3418t, this.f3410l + this.f3419u, this.f3407i);
                    canvas.drawCircle(0.0f, 0.0f, this.f3410l, this.f3406h);
                } else {
                    canvas.drawCircle(this.f3417s, this.f3418t, this.f3409k + this.f3419u, this.f3405g);
                    canvas.drawCircle(0.0f, 0.0f, this.f3409k, this.f3404e);
                }
                canvas.translate(this.f3408j, 0.0f);
            }
            canvas.restore();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i7, int i8) {
        int iCeil;
        int size = View.MeasureSpec.getMode(i7) == 1073741824 ? View.MeasureSpec.getSize(i7) : (this.f3421w * this.f3408j) + getPaddingLeft() + getPaddingRight();
        if (View.MeasureSpec.getMode(i8) == 1073741824) {
            iCeil = View.MeasureSpec.getSize(i8);
        } else {
            float f7 = this.f3409k;
            float f8 = this.f3419u;
            iCeil = ((int) (((int) Math.ceil(Math.max(f7 + f8, this.f3410l + f8) * 2.0f)) + this.f3418t)) + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(View.resolveSizeAndState(size, i7, 0), View.resolveSizeAndState(iCeil, i8, 0));
    }

    public void setDotColor(int i7) {
        if (this.f3411m != i7) {
            this.f3411m = i7;
            invalidate();
        }
    }

    public void setDotColorSelected(int i7) {
        if (this.f3412n != i7) {
            this.f3412n = i7;
            invalidate();
        }
    }

    public void setDotFadeOutDelay(int i7) {
        this.f3414p = i7;
    }

    public void setDotFadeWhenIdle(boolean z6) {
        this.f3413o = z6;
        if (z6) {
            return;
        }
        d();
    }

    public void setDotRadius(int i7) {
        float f7 = i7;
        if (this.f3409k != f7) {
            this.f3409k = f7;
            i();
            invalidate();
        }
    }

    public void setDotRadiusSelected(int i7) {
        float f7 = i7;
        if (this.f3410l != f7) {
            this.f3410l = f7;
            i();
            invalidate();
        }
    }

    public void setDotShadowColor(int i7) {
        this.f3420v = i7;
        i();
        invalidate();
    }

    public void setDotShadowDx(float f7) {
        this.f3417s = f7;
        invalidate();
    }

    public void setDotShadowDy(float f7) {
        this.f3418t = f7;
        invalidate();
    }

    public void setDotShadowRadius(float f7) {
        if (this.f3419u != f7) {
            this.f3419u = f7;
            i();
            invalidate();
        }
    }

    public void setDotSpacing(int i7) {
        if (this.f3408j != i7) {
            this.f3408j = i7;
            requestLayout();
        }
    }

    public void setPager(ViewPager viewPager) {
        viewPager.b(this);
        viewPager.getAdapter();
        setPagerAdapter(null);
        viewPager.getAdapter();
    }

    public void setPagerAdapter(androidx.viewpager.widget.a aVar) {
    }

    public PageIndicatorView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, b.PageIndicatorView, i7, i1.a.WsPageIndicatorViewStyle);
        this.f3408j = typedArrayObtainStyledAttributes.getDimensionPixelOffset(b.PageIndicatorView_wsPageIndicatorDotSpacing, 0);
        this.f3409k = typedArrayObtainStyledAttributes.getDimension(b.PageIndicatorView_wsPageIndicatorDotRadius, 0.0f);
        this.f3410l = typedArrayObtainStyledAttributes.getDimension(b.PageIndicatorView_wsPageIndicatorDotRadiusSelected, 0.0f);
        this.f3411m = typedArrayObtainStyledAttributes.getColor(b.PageIndicatorView_wsPageIndicatorDotColor, 0);
        this.f3412n = typedArrayObtainStyledAttributes.getColor(b.PageIndicatorView_wsPageIndicatorDotColorSelected, 0);
        this.f3414p = typedArrayObtainStyledAttributes.getInt(b.PageIndicatorView_wsPageIndicatorDotFadeOutDelay, 0);
        this.f3415q = typedArrayObtainStyledAttributes.getInt(b.PageIndicatorView_wsPageIndicatorDotFadeOutDuration, 0);
        this.f3416r = typedArrayObtainStyledAttributes.getInt(b.PageIndicatorView_wsPageIndicatorDotFadeInDuration, 0);
        this.f3413o = typedArrayObtainStyledAttributes.getBoolean(b.PageIndicatorView_wsPageIndicatorDotFadeWhenIdle, false);
        this.f3417s = typedArrayObtainStyledAttributes.getDimension(b.PageIndicatorView_wsPageIndicatorDotShadowDx, 0.0f);
        this.f3418t = typedArrayObtainStyledAttributes.getDimension(b.PageIndicatorView_wsPageIndicatorDotShadowDy, 0.0f);
        this.f3419u = typedArrayObtainStyledAttributes.getDimension(b.PageIndicatorView_wsPageIndicatorDotShadowRadius, 0.0f);
        this.f3420v = typedArrayObtainStyledAttributes.getColor(b.PageIndicatorView_wsPageIndicatorDotShadowColor, 0);
        typedArrayObtainStyledAttributes.recycle();
        Paint paint = new Paint(1);
        this.f3404e = paint;
        paint.setColor(this.f3411m);
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        this.f3406h = paint2;
        paint2.setColor(this.f3412n);
        paint2.setStyle(Paint.Style.FILL);
        this.f3405g = new Paint(1);
        this.f3407i = new Paint(1);
        this.f3423y = 0;
        if (isInEditMode()) {
            this.f3421w = 5;
            this.f3422x = 2;
            this.f3413o = false;
        }
        if (this.f3413o) {
            this.f3424z = false;
            animate().alpha(0.0f).setStartDelay(2000L).setDuration(this.f3415q).start();
        } else {
            animate().cancel();
            setAlpha(1.0f);
        }
        i();
    }
}
