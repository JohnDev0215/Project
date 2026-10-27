package com.bumptech.glide.load.p.h;

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
import com.bumptech.glide.load.m;
import com.bumptech.glide.load.p.h.g;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c extends Drawable implements g.b, Animatable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f2393b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2394c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2395d;
    private boolean e;
    private boolean f;
    private int g;
    private int h;
    private boolean i;
    private Paint j;
    private Rect k;
    private List<a.o.a.a.b> l;

    static final class a extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final g f2396a;

        a(g gVar) {
            this.f2396a = gVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new c(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return newDrawable();
        }
    }

    public c(Context context, com.bumptech.glide.n.a aVar, m<Bitmap> mVar, int i, int i2, Bitmap bitmap) {
        this(new a(new g(com.bumptech.glide.c.c(context), aVar, i, i2, mVar, bitmap)));
    }

    c(a aVar) {
        this.f = true;
        this.h = -1;
        this.f2393b = (a) com.bumptech.glide.t.j.d(aVar);
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
        if (this.k == null) {
            this.k = new Rect();
        }
        return this.k;
    }

    private Paint h() {
        if (this.j == null) {
            this.j = new Paint(2);
        }
        return this.j;
    }

    private void j() {
        List<a.o.a.a.b> list = this.l;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                this.l.get(i).a(this);
            }
        }
    }

    private void l() {
        this.g = 0;
    }

    private void n() {
        com.bumptech.glide.t.j.a(!this.e, "You cannot start a recycled Drawable. Ensure thatyou clear any references to the Drawable when clearing the corresponding request.");
        if (this.f2393b.f2396a.f() != 1) {
            if (this.f2394c) {
                return;
            }
            this.f2394c = true;
            this.f2393b.f2396a.r(this);
        }
        invalidateSelf();
    }

    private void o() {
        this.f2394c = false;
        this.f2393b.f2396a.s(this);
    }

    @Override // com.bumptech.glide.load.p.h.g.b
    public void a() {
        if (b() == null) {
            stop();
            invalidateSelf();
            return;
        }
        invalidateSelf();
        if (g() == f() - 1) {
            this.g++;
        }
        int i = this.h;
        if (i == -1 || this.g < i) {
            return;
        }
        j();
        stop();
    }

    public ByteBuffer c() {
        return this.f2393b.f2396a.b();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.e) {
            return;
        }
        if (this.i) {
            Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), d());
            this.i = false;
        }
        canvas.drawBitmap(this.f2393b.f2396a.c(), (Rect) null, d(), h());
    }

    public Bitmap e() {
        return this.f2393b.f2396a.e();
    }

    public int f() {
        return this.f2393b.f2396a.f();
    }

    public int g() {
        return this.f2393b.f2396a.d();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f2393b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f2393b.f2396a.h();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f2393b.f2396a.k();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public int i() {
        return this.f2393b.f2396a.j();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f2394c;
    }

    public void k() {
        this.e = true;
        this.f2393b.f2396a.a();
    }

    public void m(m<Bitmap> mVar, Bitmap bitmap) {
        this.f2393b.f2396a.o(mVar, bitmap);
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.i = true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        h().setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        h().setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        com.bumptech.glide.t.j.a(!this.e, "Cannot change the visibility of a recycled resource. Ensure that you unset the Drawable from your View before changing the View's visibility.");
        this.f = z;
        if (!z) {
            o();
        } else if (this.f2395d) {
            n();
        }
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.f2395d = true;
        l();
        if (this.f) {
            n();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f2395d = false;
        o();
    }
}
