package n2;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: ShimmerDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ValueAnimator.AnimatorUpdateListener f8535a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Paint f8536b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Rect f8537c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Matrix f8538d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ValueAnimator f8539e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b f8540f;

    /* JADX INFO: compiled from: ShimmerDrawable.java */
    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            c.this.invalidateSelf();
        }
    }

    public c() {
        Paint paint = new Paint();
        this.f8536b = paint;
        this.f8537c = new Rect();
        this.f8538d = new Matrix();
        paint.setAntiAlias(true);
    }

    private float c(float f7, float f8, float f9) {
        return f7 + ((f8 - f7) * f9);
    }

    private void g() {
        b bVar;
        Shader radialGradient;
        Rect bounds = getBounds();
        int iWidth = bounds.width();
        int iHeight = bounds.height();
        if (iWidth == 0 || iHeight == 0 || (bVar = this.f8540f) == null) {
            return;
        }
        int iD = bVar.d(iWidth);
        int iA = this.f8540f.a(iHeight);
        b bVar2 = this.f8540f;
        boolean z6 = true;
        if (bVar2.f8519g != 1) {
            int i7 = bVar2.f8516d;
            if (i7 != 1 && i7 != 3) {
                z6 = false;
            }
            if (z6) {
                iD = 0;
            }
            if (!z6) {
                iA = 0;
            }
            float f7 = iA;
            b bVar3 = this.f8540f;
            radialGradient = new LinearGradient(0.0f, 0.0f, iD, f7, bVar3.f8514b, bVar3.f8513a, Shader.TileMode.CLAMP);
        } else {
            float f8 = iA / 2.0f;
            float fMax = (float) (((double) Math.max(iD, iA)) / Math.sqrt(2.0d));
            b bVar4 = this.f8540f;
            radialGradient = new RadialGradient(iD / 2.0f, f8, fMax, bVar4.f8514b, bVar4.f8513a, Shader.TileMode.CLAMP);
        }
        this.f8536b.setShader(radialGradient);
    }

    private void h() {
        boolean zIsStarted;
        if (this.f8540f == null) {
            return;
        }
        ValueAnimator valueAnimator = this.f8539e;
        if (valueAnimator != null) {
            zIsStarted = valueAnimator.isStarted();
            this.f8539e.cancel();
            this.f8539e.removeAllUpdateListeners();
        } else {
            zIsStarted = false;
        }
        b bVar = this.f8540f;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, (bVar.f8533u / bVar.f8532t) + 1.0f);
        this.f8539e = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setRepeatMode(this.f8540f.f8531s);
        this.f8539e.setRepeatCount(this.f8540f.f8530r);
        ValueAnimator valueAnimator2 = this.f8539e;
        b bVar2 = this.f8540f;
        valueAnimator2.setDuration(bVar2.f8532t + bVar2.f8533u);
        this.f8539e.addUpdateListener(this.f8535a);
        if (zIsStarted) {
            this.f8539e.start();
        }
    }

    public boolean a() {
        ValueAnimator valueAnimator = this.f8539e;
        return valueAnimator != null && valueAnimator.isStarted();
    }

    void b() {
        b bVar;
        ValueAnimator valueAnimator = this.f8539e;
        if (valueAnimator == null || valueAnimator.isStarted() || (bVar = this.f8540f) == null || !bVar.f8528p || getCallback() == null) {
            return;
        }
        this.f8539e.start();
    }

    public void d(b bVar) {
        this.f8540f = bVar;
        if (bVar != null) {
            this.f8536b.setXfermode(new PorterDuffXfermode(this.f8540f.f8529q ? PorterDuff.Mode.DST_IN : PorterDuff.Mode.SRC_IN));
        }
        g();
        h();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float fC;
        float fC2;
        if (this.f8540f == null || this.f8536b.getShader() == null) {
            return;
        }
        float fTan = (float) Math.tan(Math.toRadians(this.f8540f.f8526n));
        float fHeight = this.f8537c.height() + (this.f8537c.width() * fTan);
        float fWidth = this.f8537c.width() + (fTan * this.f8537c.height());
        ValueAnimator valueAnimator = this.f8539e;
        float f7 = 0.0f;
        float animatedFraction = valueAnimator != null ? valueAnimator.getAnimatedFraction() : 0.0f;
        int i7 = this.f8540f.f8516d;
        if (i7 != 1) {
            if (i7 == 2) {
                fC2 = c(fWidth, -fWidth, animatedFraction);
            } else if (i7 != 3) {
                fC2 = c(-fWidth, fWidth, animatedFraction);
            } else {
                fC = c(fHeight, -fHeight, animatedFraction);
            }
            f7 = fC2;
            fC = 0.0f;
        } else {
            fC = c(-fHeight, fHeight, animatedFraction);
        }
        this.f8538d.reset();
        this.f8538d.setRotate(this.f8540f.f8526n, this.f8537c.width() / 2.0f, this.f8537c.height() / 2.0f);
        this.f8538d.postTranslate(f7, fC);
        this.f8536b.getShader().setLocalMatrix(this.f8538d);
        canvas.drawRect(this.f8537c, this.f8536b);
    }

    public void e() {
        if (this.f8539e == null || a() || getCallback() == null) {
            return;
        }
        this.f8539e.start();
    }

    public void f() {
        if (this.f8539e == null || !a()) {
            return;
        }
        this.f8539e.cancel();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        b bVar = this.f8540f;
        return (bVar == null || !(bVar.f8527o || bVar.f8529q)) ? -1 : -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f8537c.set(0, 0, rect.width(), rect.height());
        g();
        b();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i7) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
