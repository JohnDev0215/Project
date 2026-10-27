package r4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import java.util.ArrayList;

/* JADX INFO: renamed from: r4.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2290c extends Drawable implements InterfaceC2293f, Animatable {
    public final C2289b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f21815m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f21816n;
    public boolean o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f21818q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f21820s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Paint f21821t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Rect f21822u;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f21817p = true;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f21819r = -1;

    public C2290c(C2289b c2289b) {
        A4.h.c(c2289b, "Argument must not be null");
        this.l = c2289b;
    }

    public final void a() {
        A4.h.a("You cannot start a recycled Drawable. Ensure thatyou clear any references to the Drawable when clearing the corresponding request.", !this.o);
        C2294g c2294g = this.l.f21814a;
        if (c2294g.f21829a.l.c == 1) {
            invalidateSelf();
            return;
        }
        if (this.f21815m) {
            return;
        }
        this.f21815m = true;
        if (c2294g.f21836j) {
            throw new IllegalStateException("Cannot subscribe to a cleared frame loader");
        }
        ArrayList arrayList = c2294g.c;
        if (arrayList.contains(this)) {
            throw new IllegalStateException("Cannot subscribe twice in a row");
        }
        boolean zIsEmpty = arrayList.isEmpty();
        arrayList.add(this);
        if (zIsEmpty && !c2294g.f21832f) {
            c2294g.f21832f = true;
            c2294g.f21836j = false;
            c2294g.a();
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        if (this.o) {
            return;
        }
        if (this.f21820s) {
            int intrinsicWidth = getIntrinsicWidth();
            int intrinsicHeight = getIntrinsicHeight();
            Rect bounds = getBounds();
            if (this.f21822u == null) {
                this.f21822u = new Rect();
            }
            Gravity.apply(119, intrinsicWidth, intrinsicHeight, bounds, this.f21822u);
            this.f21820s = false;
        }
        C2294g c2294g = this.l.f21814a;
        C2292e c2292e = c2294g.f21835i;
        Bitmap bitmap = c2292e != null ? c2292e.f21828r : c2294g.l;
        if (this.f21822u == null) {
            this.f21822u = new Rect();
        }
        Rect rect = this.f21822u;
        if (this.f21821t == null) {
            this.f21821t = new Paint(2);
        }
        canvas.drawBitmap(bitmap, (Rect) null, rect, this.f21821t);
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.l;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.l.f21814a.f21840p;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.l.f21814a.o;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return this.f21815m;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f21820s = true;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        if (this.f21821t == null) {
            this.f21821t = new Paint(2);
        }
        this.f21821t.setAlpha(i7);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        if (this.f21821t == null) {
            this.f21821t = new Paint(2);
        }
        this.f21821t.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z6, boolean z10) {
        A4.h.a("Cannot change the visibility of a recycled resource. Ensure that you unset the Drawable from your View before changing the View's visibility.", !this.o);
        this.f21817p = z6;
        if (!z6) {
            this.f21815m = false;
            C2294g c2294g = this.l.f21814a;
            ArrayList arrayList = c2294g.c;
            arrayList.remove(this);
            if (arrayList.isEmpty()) {
                c2294g.f21832f = false;
            }
        } else if (this.f21816n) {
            a();
        }
        return super.setVisible(z6, z10);
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        this.f21816n = true;
        this.f21818q = 0;
        if (this.f21817p) {
            a();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        this.f21816n = false;
        this.f21815m = false;
        C2294g c2294g = this.l.f21814a;
        ArrayList arrayList = c2294g.c;
        arrayList.remove(this);
        if (arrayList.isEmpty()) {
            c2294g.f21832f = false;
        }
    }
}
