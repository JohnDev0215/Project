package b2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import b2.g;
import java.nio.ByteBuffer;
import java.util.List;
import n1.m;

/* JADX INFO: compiled from: GifDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends Drawable implements g.b, Animatable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final a f3484e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f3485g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f3486h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f3487i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f3488j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f3489k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f3490l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f3491m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Paint f3492n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private Rect f3493o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private List<f1.a> f3494p;

    /* JADX INFO: compiled from: GifDrawable.java */
    static final class a extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final g f3495a;

        a(g gVar) {
            this.f3495a = gVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return newDrawable();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new c(this);
        }
    }

    public c(Context context, l1.a aVar, m<Bitmap> mVar, int i7, int i8, Bitmap bitmap) {
        this(new a(new g(com.bumptech.glide.c.c(context), aVar, i7, i8, mVar, bitmap)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Drawable.Callback b() {
        Drawable.Callback callback = getCallback();
        while (callback instanceof Drawable) {
            callback = ((Drawable) callback).getCallback();
        }
        return callback;
    }

    private Rect d() {
        if (this.f3493o == null) {
            this.f3493o = new Rect();
        }
        return this.f3493o;
    }

    private Paint h() {
        if (this.f3492n == null) {
            this.f3492n = new Paint(2);
        }
        return this.f3492n;
    }

    private void j() {
        List<f1.a> list = this.f3494p;
        if (list != null) {
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                this.f3494p.get(i7).a(this);
            }
        }
    }

    private void l() {
        this.f3489k = 0;
    }

    private void n() {
        k2.j.a(!this.f3487i, "You cannot start a recycled Drawable. Ensure thatyou clear any references to the Drawable when clearing the corresponding request.");
        if (this.f3484e.f3495a.f() == 1) {
            invalidateSelf();
        } else {
            if (this.f3485g) {
                return;
            }
            this.f3485g = true;
            this.f3484e.f3495a.r(this);
            invalidateSelf();
        }
    }

    private void o() {
        this.f3485g = false;
        this.f3484e.f3495a.s(this);
    }

    @Override // b2.g.b
    public void a() {
        if (b() == null) {
            stop();
            invalidateSelf();
            return;
        }
        invalidateSelf();
        if (g() == f() - 1) {
            this.f3489k++;
        }
        int i7 = this.f3490l;
        if (i7 == -1 || this.f3489k < i7) {
            return;
        }
        j();
        stop();
    }

    public ByteBuffer c() {
        return this.f3484e.f3495a.b();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f3487i) {
            return;
        }
        if (this.f3491m) {
            Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), d());
            this.f3491m = false;
        }
        canvas.drawBitmap(this.f3484e.f3495a.c(), (Rect) null, d(), h());
    }

    public Bitmap e() {
        return this.f3484e.f3495a.e();
    }

    public int f() {
        return this.f3484e.f3495a.f();
    }

    public int g() {
        return this.f3484e.f3495a.d();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f3484e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f3484e.f3495a.h();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f3484e.f3495a.k();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public int i() {
        return this.f3484e.f3495a.j();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f3485g;
    }

    public void k() {
        this.f3487i = true;
        this.f3484e.f3495a.a();
    }

    public void m(m<Bitmap> mVar, Bitmap bitmap) {
        this.f3484e.f3495a.o(mVar, bitmap);
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f3491m = true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i7) {
        h().setAlpha(i7);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        h().setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z6, boolean z7) {
        k2.j.a(!this.f3487i, "Cannot change the visibility of a recycled resource. Ensure that you unset the Drawable from your View before changing the View's visibility.");
        this.f3488j = z6;
        if (!z6) {
            o();
        } else if (this.f3486h) {
            n();
        }
        return super.setVisible(z6, z7);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.f3486h = true;
        l();
        if (this.f3488j) {
            n();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f3486h = false;
        o();
    }

    c(a aVar) {
        this.f3488j = true;
        this.f3490l = -1;
        this.f3484e = (a) k2.j.d(aVar);
    }
}
