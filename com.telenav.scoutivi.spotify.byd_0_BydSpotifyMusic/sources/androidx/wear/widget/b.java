package androidx.wear.widget;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.animation.LinearInterpolator;

/* JADX INFO: compiled from: ProgressDrawable.java */
/* JADX INFO: loaded from: classes.dex */
class b extends Drawable {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final Property<b, Integer> f3397f = new a(Integer.class, "level");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final TimeInterpolator f3398g = androidx.wear.widget.a.f3394a;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final RectF f3399a = new RectF();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Paint f3400b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ObjectAnimator f3401c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f3402d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3403e;

    /* JADX INFO: compiled from: ProgressDrawable.java */
    class a extends Property<b, Integer> {
        a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer get(b bVar) {
            return Integer.valueOf(bVar.getLevel());
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(b bVar, Integer num) {
            bVar.setLevel(num.intValue());
            bVar.invalidateSelf();
        }
    }

    b() {
        Paint paint = new Paint();
        this.f3400b = paint;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(this, f3397f, 0, 10000);
        this.f3401c = objectAnimatorOfInt;
        objectAnimatorOfInt.setRepeatCount(-1);
        objectAnimatorOfInt.setRepeatMode(1);
        objectAnimatorOfInt.setDuration(6000L);
        objectAnimatorOfInt.setInterpolator(new LinearInterpolator());
    }

    private static float a(float f7, float f8, float f9) {
        if (f7 != f8) {
            return (f9 - f7) / (f8 - f7);
        }
        return 0.0f;
    }

    public void b(int i7) {
        this.f3403e = i7;
    }

    public void c(float f7) {
        this.f3402d = f7;
    }

    public void d() {
        if (this.f3401c.isStarted()) {
            return;
        }
        this.f3401c.start();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.save();
        this.f3399a.set(getBounds());
        RectF rectF = this.f3399a;
        float f7 = this.f3402d;
        rectF.inset(f7 / 2.0f, f7 / 2.0f);
        this.f3400b.setStrokeWidth(this.f3402d);
        this.f3400b.setColor(this.f3403e);
        int level = getLevel();
        float f8 = (level - ((level / 2000) * 2000)) / 2000.0f;
        boolean z6 = f8 < 0.5f;
        float f9 = 54.0f * f8;
        float fMax = Math.max(1.0f, (z6 ? f3398g.getInterpolation(a(0.0f, 0.5f, f8)) : 1.0f - f3398g.getInterpolation(a(0.5f, 1.0f, f8))) * 306.0f);
        canvas.rotate(((((level * 1.0E-4f) * 2.0f) * 360.0f) - 90.0f) + f9, this.f3399a.centerX(), this.f3399a.centerY());
        canvas.drawArc(this.f3399a, z6 ? 0.0f : 306.0f - fMax, fMax, false, this.f3400b);
        canvas.restore();
    }

    public void e() {
        this.f3401c.cancel();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i7) {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i7) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
