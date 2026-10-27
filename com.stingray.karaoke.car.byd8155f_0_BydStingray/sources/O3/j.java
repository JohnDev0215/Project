package O3;

import a4.AbstractC0922c;
import a4.AbstractC0925f;
import a4.AbstractC0927h;
import a4.ChoreographerFrameCallbackC0924e;
import a4.ThreadFactoryC0923d;
import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.view.Choreographer;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class j extends Drawable implements Drawable.Callback, Animatable {

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public static final List f5094X = Arrays.asList("reduced motion", "reduced_motion", "reduced-motion", "reducedmotion");

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public static final ThreadPoolExecutor f5095Y = new ThreadPoolExecutor(0, 2, 35, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactoryC0923d());

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f5096A;
    public boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f5097C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final Matrix f5098D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public Bitmap f5099E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public Canvas f5100F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public Rect f5101G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public RectF f5102H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public P3.a f5103I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public Rect f5104J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public Rect f5105K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public RectF f5106L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public RectF f5107M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public Matrix f5108N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final float[] f5109O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public Matrix f5110P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public boolean f5111Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final Semaphore f5112R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final A2.l f5113S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public float f5114T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public int f5115U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public int f5116V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public int f5117W;
    public a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ChoreographerFrameCallbackC0924e f5118m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f5119n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f5120p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public T3.a f5121q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public N0.q f5122r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Map f5123s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final l f5124t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f5125u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f5126v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public X3.c f5127w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f5128x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f5129y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f5130z;

    public j() {
        ChoreographerFrameCallbackC0924e choreographerFrameCallbackC0924e = new ChoreographerFrameCallbackC0924e();
        this.f5118m = choreographerFrameCallbackC0924e;
        this.f5119n = true;
        this.o = false;
        this.f5115U = 1;
        this.f5120p = new ArrayList();
        this.f5124t = new l(0);
        this.f5125u = false;
        this.f5126v = true;
        this.f5128x = 255;
        this.B = false;
        this.f5116V = 1;
        this.f5097C = false;
        this.f5098D = new Matrix();
        this.f5109O = new float[9];
        this.f5111Q = false;
        f fVar = new f(this, 0);
        this.f5112R = new Semaphore(1);
        this.f5113S = new A2.l(this, 4);
        this.f5114T = -3.4028235E38f;
        choreographerFrameCallbackC0924e.addUpdateListener(fVar);
    }

    public static void d(Rect rect, RectF rectF) {
        rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
    }

    public final boolean a(Context context) {
        if (this.f5119n) {
            if (context != null) {
                Matrix matrix = AbstractC0927h.f11132a;
                if (Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f) != 0.0f) {
                }
            }
            return true;
        }
        return false;
    }

    public final void b() {
        a aVar = this.l;
        if (aVar == null) {
            return;
        }
        io.sentry.internal.debugmeta.c cVar = Y3.q.f10693a;
        Rect rect = aVar.f5077k;
        X3.c cVar2 = new X3.c(this, new X3.e(Collections.emptyList(), aVar, "__container", -1L, 1, -1L, null, Collections.emptyList(), new V3.d(), 0, 0, 0, 0.0f, 0.0f, rect.width(), rect.height(), null, null, Collections.emptyList(), 1, null, false, null, null, 1), aVar.f5076j, aVar);
        this.f5127w = cVar2;
        if (this.f5129y) {
            cVar2.l(true);
        }
        this.f5127w.f10027L = this.f5126v;
    }

    public final void c() {
        a aVar = this.l;
        if (aVar == null) {
            return;
        }
        int i7 = this.f5116V;
        int i10 = aVar.o;
        int iC = AbstractC2641j.c(i7);
        boolean z6 = false;
        if (iC != 1 && (iC == 2 || i10 > 4)) {
            z6 = true;
        }
        this.f5097C = z6;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        X3.c cVar = this.f5127w;
        if (cVar == null) {
            return;
        }
        int i7 = this.f5117W;
        if (i7 == 0) {
            i7 = 1;
        }
        boolean z6 = i7 == 2;
        ThreadPoolExecutor threadPoolExecutor = f5095Y;
        Semaphore semaphore = this.f5112R;
        A2.l lVar = this.f5113S;
        ChoreographerFrameCallbackC0924e choreographerFrameCallbackC0924e = this.f5118m;
        if (z6) {
            try {
                semaphore.acquire();
            } catch (InterruptedException unused) {
                if (!z6) {
                    return;
                }
                semaphore.release();
                if (cVar.f10026K == choreographerFrameCallbackC0924e.a()) {
                    return;
                }
            } catch (Throwable th) {
                if (z6) {
                    semaphore.release();
                    if (cVar.f10026K != choreographerFrameCallbackC0924e.a()) {
                        threadPoolExecutor.execute(lVar);
                    }
                }
                throw th;
            }
        }
        if (z6 && m()) {
            l(choreographerFrameCallbackC0924e.a());
        }
        if (this.o) {
            try {
                if (this.f5097C) {
                    i(canvas, cVar);
                } else {
                    e(canvas);
                }
            } catch (Throwable unused2) {
                AbstractC0922c.f11094a.getClass();
            }
        } else if (this.f5097C) {
            i(canvas, cVar);
        } else {
            e(canvas);
        }
        this.f5111Q = false;
        if (z6) {
            semaphore.release();
            if (cVar.f10026K == choreographerFrameCallbackC0924e.a()) {
                return;
            }
            threadPoolExecutor.execute(lVar);
        }
    }

    public final void e(Canvas canvas) {
        X3.c cVar = this.f5127w;
        a aVar = this.l;
        if (cVar == null || aVar == null) {
            return;
        }
        Matrix matrix = this.f5098D;
        matrix.reset();
        if (!getBounds().isEmpty()) {
            matrix.preTranslate(r3.left, r3.top);
            matrix.preScale(r3.width() / aVar.f5077k.width(), r3.height() / aVar.f5077k.height());
        }
        cVar.f(canvas, matrix, this.f5128x, null);
    }

    public final Context f() {
        Drawable.Callback callback = getCallback();
        if (callback != null && (callback instanceof View)) {
            return ((View) callback).getContext();
        }
        return null;
    }

    public final U3.f g() {
        U3.f fVar = null;
        for (String str : f5094X) {
            a aVar = this.l;
            int size = aVar.f5073g.size();
            for (int i7 = 0; i7 < size; i7++) {
                U3.f fVar2 = (U3.f) aVar.f5073g.get(i7);
                String str2 = fVar2.f9014a;
                if (str2.equalsIgnoreCase(str) || (str2.endsWith("\r") && str2.substring(0, str2.length() - 1).equalsIgnoreCase(str))) {
                    fVar = fVar2;
                    break;
                }
            }
            fVar = null;
            if (fVar != null) {
                break;
            }
        }
        return fVar;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.f5128x;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        a aVar = this.l;
        if (aVar == null) {
            return -1;
        }
        return aVar.f5077k.height();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        a aVar = this.l;
        if (aVar == null) {
            return -1;
        }
        return aVar.f5077k.width();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    public final void h() {
        if (this.f5127w == null) {
            this.f5120p.add(new e(this, 1));
            return;
        }
        c();
        boolean zA = a(f());
        ChoreographerFrameCallbackC0924e choreographerFrameCallbackC0924e = this.f5118m;
        if (zA || choreographerFrameCallbackC0924e.getRepeatCount() == 0) {
            if (isVisible()) {
                choreographerFrameCallbackC0924e.f11107x = true;
                boolean zD = choreographerFrameCallbackC0924e.d();
                Iterator it = choreographerFrameCallbackC0924e.f11097m.iterator();
                while (it.hasNext()) {
                    ((Animator.AnimatorListener) it.next()).onAnimationStart(choreographerFrameCallbackC0924e, zD);
                }
                choreographerFrameCallbackC0924e.h((int) (choreographerFrameCallbackC0924e.d() ? choreographerFrameCallbackC0924e.b() : choreographerFrameCallbackC0924e.c()));
                choreographerFrameCallbackC0924e.f11100q = 0L;
                choreographerFrameCallbackC0924e.f11103t = 0;
                if (choreographerFrameCallbackC0924e.f11107x) {
                    choreographerFrameCallbackC0924e.g(false);
                    Choreographer.getInstance().postFrameCallback(choreographerFrameCallbackC0924e);
                }
                this.f5115U = 1;
            } else {
                this.f5115U = 2;
            }
        }
        if (a(f())) {
            return;
        }
        U3.f fVarG = g();
        if (fVarG != null) {
            k((int) fVarG.f9015b);
        } else {
            k((int) (choreographerFrameCallbackC0924e.o < 0.0f ? choreographerFrameCallbackC0924e.c() : choreographerFrameCallbackC0924e.b()));
        }
        choreographerFrameCallbackC0924e.g(true);
        choreographerFrameCallbackC0924e.e(choreographerFrameCallbackC0924e.d());
        if (isVisible()) {
            return;
        }
        this.f5115U = 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i(android.graphics.Canvas r11, X3.c r12) {
        /*
            Method dump skipped, instruction units count: 458
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: O3.j.i(android.graphics.Canvas, X3.c):void");
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        if (this.f5111Q) {
            return;
        }
        this.f5111Q = true;
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        ChoreographerFrameCallbackC0924e choreographerFrameCallbackC0924e = this.f5118m;
        if (choreographerFrameCallbackC0924e == null) {
            return false;
        }
        return choreographerFrameCallbackC0924e.f11107x;
    }

    public final void j() {
        if (this.f5127w == null) {
            this.f5120p.add(new e(this, 0));
            return;
        }
        c();
        boolean zA = a(f());
        ChoreographerFrameCallbackC0924e choreographerFrameCallbackC0924e = this.f5118m;
        if (zA || choreographerFrameCallbackC0924e.getRepeatCount() == 0) {
            if (isVisible()) {
                choreographerFrameCallbackC0924e.f11107x = true;
                choreographerFrameCallbackC0924e.g(false);
                Choreographer.getInstance().postFrameCallback(choreographerFrameCallbackC0924e);
                choreographerFrameCallbackC0924e.f11100q = 0L;
                if (choreographerFrameCallbackC0924e.d() && choreographerFrameCallbackC0924e.f11102s == choreographerFrameCallbackC0924e.c()) {
                    choreographerFrameCallbackC0924e.h(choreographerFrameCallbackC0924e.b());
                } else if (!choreographerFrameCallbackC0924e.d() && choreographerFrameCallbackC0924e.f11102s == choreographerFrameCallbackC0924e.b()) {
                    choreographerFrameCallbackC0924e.h(choreographerFrameCallbackC0924e.c());
                }
                Iterator it = choreographerFrameCallbackC0924e.f11098n.iterator();
                while (it.hasNext()) {
                    ((Animator.AnimatorPauseListener) it.next()).onAnimationResume(choreographerFrameCallbackC0924e);
                }
                this.f5115U = 1;
            } else {
                this.f5115U = 3;
            }
        }
        if (a(f())) {
            return;
        }
        k((int) (choreographerFrameCallbackC0924e.o < 0.0f ? choreographerFrameCallbackC0924e.c() : choreographerFrameCallbackC0924e.b()));
        choreographerFrameCallbackC0924e.g(true);
        choreographerFrameCallbackC0924e.e(choreographerFrameCallbackC0924e.d());
        if (isVisible()) {
            return;
        }
        this.f5115U = 1;
    }

    public final void k(final int i7) {
        if (this.l == null) {
            this.f5120p.add(new i() { // from class: O3.h
                @Override // O3.i
                public final void run() {
                    this.f5092a.k(i7);
                }
            });
        } else {
            this.f5118m.h(i7);
        }
    }

    public final void l(final float f10) {
        a aVar = this.l;
        if (aVar == null) {
            this.f5120p.add(new i() { // from class: O3.g
                @Override // O3.i
                public final void run() {
                    this.f5090a.l(f10);
                }
            });
        } else {
            this.f5118m.h(AbstractC0925f.e(aVar.l, aVar.f5078m, f10));
        }
    }

    public final boolean m() {
        a aVar = this.l;
        if (aVar == null) {
            return false;
        }
        float f10 = this.f5114T;
        float fA = this.f5118m.a();
        this.f5114T = fA;
        return Math.abs(fA - f10) * aVar.b() >= 50.0f;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j3) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.scheduleDrawable(this, runnable, j3);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        this.f5128x = i7;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        AbstractC0922c.a("Use addColorFilter instead.");
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z6, boolean z10) {
        boolean zIsVisible = isVisible();
        boolean visible = super.setVisible(z6, z10);
        if (z6) {
            int i7 = this.f5115U;
            if (i7 == 2) {
                h();
            } else if (i7 == 3) {
                j();
            }
        } else {
            ChoreographerFrameCallbackC0924e choreographerFrameCallbackC0924e = this.f5118m;
            if (choreographerFrameCallbackC0924e.f11107x) {
                this.f5120p.clear();
                choreographerFrameCallbackC0924e.g(true);
                Iterator it = choreographerFrameCallbackC0924e.f11098n.iterator();
                while (it.hasNext()) {
                    ((Animator.AnimatorPauseListener) it.next()).onAnimationPause(choreographerFrameCallbackC0924e);
                }
                if (!isVisible()) {
                    this.f5115U = 1;
                }
                this.f5115U = 3;
            } else if (zIsVisible) {
                this.f5115U = 1;
            }
        }
        return visible;
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        Drawable.Callback callback = getCallback();
        if ((callback instanceof View) && ((View) callback).isInEditMode()) {
            return;
        }
        h();
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        this.f5120p.clear();
        ChoreographerFrameCallbackC0924e choreographerFrameCallbackC0924e = this.f5118m;
        choreographerFrameCallbackC0924e.g(true);
        choreographerFrameCallbackC0924e.e(choreographerFrameCallbackC0924e.d());
        if (isVisible()) {
            return;
        }
        this.f5115U = 1;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.unscheduleDrawable(this, runnable);
    }
}
