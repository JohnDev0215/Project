package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import org.chromium.components.browser_ui.widget.R$color;
import org.chromium.components.browser_ui.widget.R$dimen;

/* JADX INFO: compiled from: PulseDrawable.java */
/* JADX INFO: loaded from: classes2.dex */
public final class p72 extends Drawable implements Animatable {
    public final b e;
    public final Paint f;
    public final Rect g;
    public final Rect h;
    public final Rect i;
    public g j;
    public boolean k;
    public boolean l;
    public long m;
    public final f n;

    /* JADX INFO: compiled from: PulseDrawable.java */
    public class a implements c {
        public final /* synthetic */ int a;

        public a(int i) {
            this.a = i;
        }

        @Override // p72.c
        public final float a(Rect rect) {
            return Math.min(this.a, Math.min(rect.width(), rect.height()) / 2.0f);
        }

        @Override // p72.c
        public final float b(Rect rect) {
            return Math.min(this.a * 1.2f, Math.min(rect.width(), rect.height()) / 2.0f);
        }
    }

    /* JADX INFO: compiled from: PulseDrawable.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            p72 p72Var = p72.this;
            p72Var.getClass();
            long jUptimeMillis = SystemClock.uptimeMillis();
            long j = p72Var.m;
            long j2 = p72Var.j.c;
            if ((j - j2) / 2500 == (jUptimeMillis - j2) / 2500 || p72Var.n.a()) {
                g gVar = p72Var.j;
                gVar.d = gVar.e.getInterpolation(((jUptimeMillis - gVar.c) % 2500) / 2500.0f);
                g gVar2 = p72Var.j;
                gVar2.f.a(p72Var, gVar2.d);
                p72Var.m = jUptimeMillis;
            } else {
                p72Var.stop();
            }
            if (p72Var.l) {
                p72Var.scheduleSelf(p72Var.e, SystemClock.uptimeMillis() + 16);
            }
        }
    }

    /* JADX INFO: compiled from: PulseDrawable.java */
    public interface c {
        float a(Rect rect);

        float b(Rect rect);
    }

    /* JADX INFO: compiled from: PulseDrawable.java */
    public static class d implements f {
        @Override // p72.f
        public final boolean a() {
            return true;
        }
    }

    /* JADX INFO: compiled from: PulseDrawable.java */
    public interface e {
        void a(p72 p72Var, float f);

        void b(p72 p72Var, Paint paint, Canvas canvas, float f);
    }

    /* JADX INFO: compiled from: PulseDrawable.java */
    public interface f {
        boolean a();
    }

    public p72(Context context, Interpolator interpolator, e eVar, f fVar) {
        this(new g(interpolator, eVar), fVar);
        e(context, false);
    }

    public static p72 b(Context context, f fVar) {
        a aVar = new a(context.getResources().getDimensionPixelSize(R$dimen.iph_pulse_baseline_radius));
        if (fVar == null) {
            fVar = new d();
        }
        return c(context, aVar, fVar);
    }

    public static p72 c(Context context, c cVar, f fVar) {
        n72 n72Var = new n72(cVar);
        PathInterpolator pathInterpolator = new PathInterpolator(0.8f, 0.0f, 0.6f, 1.0f);
        if (fVar == null) {
            fVar = new d();
        }
        p72 p72Var = new p72(context, pathInterpolator, n72Var, fVar);
        p72Var.setAlpha(76);
        return p72Var;
    }

    public final void d(int i, int i2, int i3, int i4) {
        this.g.set(i, i2, i3, i4);
        Rect rect = this.h;
        if (rect.isEmpty()) {
            return;
        }
        setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Paint paint = this.f;
        paint.setColor(this.j.a);
        g gVar = this.j;
        gVar.f.b(this, paint, canvas, gVar.d);
    }

    public final void e(Context context, boolean z) {
        int color = z ? context.getColor(R$color.default_icon_color_blue_light) : ii2.c(context);
        if (this.j.b == color) {
            return;
        }
        int alpha = getAlpha();
        g gVar = this.j;
        gVar.a = color;
        gVar.b = color;
        setAlpha(alpha);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.j.a >>> 24;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.j;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return this.l;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        if (!this.k && super.mutate() == this) {
            this.j = new g(this.j);
            this.k = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        g gVar = this.j;
        int i2 = gVar.b;
        int i3 = ((((i2 >>> 24) * (i + (i >> 7))) >> 8) << 24) | ((i2 << 8) >>> 8);
        if (gVar.a != i3) {
            gVar.a = i3;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setBounds(int i, int i2, int i3, int i4) {
        this.h.set(i, i2, i3, i4);
        Rect rect = this.g;
        int i5 = i + rect.left;
        int i6 = i2 + rect.top;
        int i7 = i3 - rect.right;
        int i8 = i4 - rect.bottom;
        Rect rect2 = this.i;
        rect2.set(i5, i6, i7, i8);
        super.setBounds(rect2.left, rect2.top, rect2.right, rect2.bottom);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!z) {
            stop();
        } else if (visible || z2) {
            start();
        }
        return visible;
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        boolean z = this.l;
        b bVar = this.e;
        if (z) {
            unscheduleSelf(bVar);
            scheduleSelf(bVar, SystemClock.uptimeMillis() + 16);
            return;
        }
        this.l = true;
        g gVar = this.j;
        if (gVar.c == 0) {
            gVar.c = SystemClock.uptimeMillis();
            this.m = this.j.c;
        }
        bVar.run();
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        this.l = false;
        this.j.c = 0L;
        unscheduleSelf(this.e);
    }

    /* JADX INFO: compiled from: PulseDrawable.java */
    public static final class g extends Drawable.ConstantState {
        public int a;
        public int b;
        public long c;
        public float d;
        public final Interpolator e;
        public final e f;

        public g(Interpolator interpolator, e eVar) {
            this.e = new q72(interpolator);
            this.f = eVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            return new p72(this, new d());
        }

        public g(g gVar) {
            this.a = gVar.a;
            this.b = gVar.b;
            this.c = gVar.c;
            this.e = gVar.e;
            this.f = gVar.f;
        }
    }

    public p72(g gVar, f fVar) {
        this.e = new b();
        this.f = new Paint(1);
        this.g = new Rect();
        this.h = new Rect();
        this.i = new Rect();
        this.j = gVar;
        this.n = fVar;
    }
}
