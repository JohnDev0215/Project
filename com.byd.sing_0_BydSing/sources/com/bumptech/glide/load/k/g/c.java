package com.bumptech.glide.load.k.g;

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
import com.bumptech.glide.load.k.g.g;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: compiled from: GifDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends Drawable implements g.b, Animatable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f3072b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3073c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3074d;
    private boolean e;
    private boolean f;
    private int g;
    private int h;
    private boolean i;
    private Paint j;
    private Rect k;
    private List<c.o.a.a.b> l;

    /* JADX INFO: compiled from: GifDrawable.java */
    static final class a extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final g f3075a;

        a(g gVar) {
            this.f3075a = gVar;
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

    public c(Context context, com.bumptech.glide.i.a aVar, com.bumptech.glide.load.h<Bitmap> hVar, int i, int i2, Bitmap bitmap) {
        this(new a(new g(com.bumptech.glide.b.c(context), aVar, i, i2, hVar, bitmap)));
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
        List<c.o.a.a.b> list = this.l;
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
        com.bumptech.glide.m.j.a(!this.e, "You cannot start a recycled Drawable. Ensure thatyou clear any references to the Drawable when clearing the corresponding request.");
        if (this.f3072b.f3075a.f() == 1) {
            invalidateSelf();
        } else {
            if (this.f3073c) {
                return;
            }
            this.f3073c = true;
            this.f3072b.f3075a.r(this);
            invalidateSelf();
        }
    }

    private void o() {
        this.f3073c = false;
        this.f3072b.f3075a.s(this);
    }

    @Override // com.bumptech.glide.load.k.g.g.b
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
        return this.f3072b.f3075a.b();
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
        canvas.drawBitmap(this.f3072b.f3075a.c(), (Rect) null, d(), h());
    }

    public Bitmap e() {
        return this.f3072b.f3075a.e();
    }

    public int f() {
        return this.f3072b.f3075a.f();
    }

    public int g() {
        return this.f3072b.f3075a.d();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f3072b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f3072b.f3075a.h();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f3072b.f3075a.k();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public int i() {
        return this.f3072b.f3075a.j();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f3073c;
    }

    public void k() {
        this.e = true;
        this.f3072b.f3075a.a();
    }

    public void m(com.bumptech.glide.load.h<Bitmap> hVar, Bitmap bitmap) {
        this.f3072b.f3075a.o(hVar, bitmap);
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
        com.bumptech.glide.m.j.a(!this.e, "Cannot change the visibility of a recycled resource. Ensure that you unset the Drawable from your View before changing the View's visibility.");
        this.f = z;
        if (!z) {
            o();
        } else if (this.f3074d) {
            n();
        }
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.f3074d = true;
        l();
        if (this.f) {
            n();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f3074d = false;
        o();
    }

    c(a aVar) {
        this.f = true;
        this.h = -1;
        this.f3072b = (a) com.bumptech.glide.m.j.d(aVar);
    }
}
