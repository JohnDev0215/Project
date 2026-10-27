package k;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;

/* JADX INFO: compiled from: DrawableContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends Drawable implements Drawable.Callback {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f3534b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Rect f3535c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Drawable f3536d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f3537e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f3539g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3541i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Runnable f3542j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f3543k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f3544l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C0030b f3545m;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3538f = 255;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3540h = -1;

    /* JADX INFO: compiled from: DrawableContainer.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.a(true);
            b.this.invalidateSelf();
        }
    }

    /* JADX INFO: renamed from: k.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DrawableContainer.java */
    public static class C0030b implements Drawable.Callback {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Drawable.Callback f3547b;

        public Drawable.Callback a() {
            Drawable.Callback callback = this.f3547b;
            this.f3547b = null;
            return callback;
        }

        public C0030b b(Drawable.Callback callback) {
            this.f3547b = callback;
            return this;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j3) {
            Drawable.Callback callback = this.f3547b;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j3);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            Drawable.Callback callback = this.f3547b;
            if (callback != null) {
                callback.unscheduleDrawable(drawable, runnable);
            }
        }
    }

    /* JADX INFO: compiled from: DrawableContainer.java */
    public static abstract class c extends Drawable.ConstantState {
        public int A;
        public int B;
        public boolean C;
        public ColorFilter D;
        public boolean E;
        public ColorStateList F;
        public PorterDuff.Mode G;
        public boolean H;
        public boolean I;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b f3548a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Resources f3549b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3550c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3551d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3552e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public SparseArray<Drawable.ConstantState> f3553f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Drawable[] f3554g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f3555h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f3556i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f3557j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public Rect f3558k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public boolean f3559l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f3560m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f3561n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f3562o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f3563p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f3564q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public boolean f3565r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f3566s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public boolean f3567t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public boolean f3568u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public boolean f3569v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public boolean f3570w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public boolean f3571x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public boolean f3572y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public int f3573z;

        public c(c cVar, b bVar, Resources resources) {
            this.f3550c = 160;
            this.f3556i = false;
            this.f3559l = false;
            this.f3571x = true;
            this.A = 0;
            this.B = 0;
            this.f3548a = bVar;
            this.f3549b = resources != null ? resources : cVar != null ? cVar.f3549b : null;
            int iResolveDensity = b.resolveDensity(resources, cVar != null ? cVar.f3550c : 0);
            this.f3550c = iResolveDensity;
            if (cVar == null) {
                this.f3554g = new Drawable[10];
                this.f3555h = 0;
                return;
            }
            this.f3551d = cVar.f3551d;
            this.f3552e = cVar.f3552e;
            this.f3569v = true;
            this.f3570w = true;
            this.f3556i = cVar.f3556i;
            this.f3559l = cVar.f3559l;
            this.f3571x = cVar.f3571x;
            this.f3572y = cVar.f3572y;
            this.f3573z = cVar.f3573z;
            this.A = cVar.A;
            this.B = cVar.B;
            this.C = cVar.C;
            this.D = cVar.D;
            this.E = cVar.E;
            this.F = cVar.F;
            this.G = cVar.G;
            this.H = cVar.H;
            this.I = cVar.I;
            if (cVar.f3550c == iResolveDensity) {
                if (cVar.f3557j) {
                    this.f3558k = new Rect(cVar.f3558k);
                    this.f3557j = true;
                }
                if (cVar.f3560m) {
                    this.f3561n = cVar.f3561n;
                    this.f3562o = cVar.f3562o;
                    this.f3563p = cVar.f3563p;
                    this.f3564q = cVar.f3564q;
                    this.f3560m = true;
                }
            }
            if (cVar.f3565r) {
                this.f3566s = cVar.f3566s;
                this.f3565r = true;
            }
            if (cVar.f3567t) {
                this.f3568u = cVar.f3568u;
                this.f3567t = true;
            }
            Drawable[] drawableArr = cVar.f3554g;
            this.f3554g = new Drawable[drawableArr.length];
            int i3 = cVar.f3555h;
            this.f3555h = i3;
            SparseArray<Drawable.ConstantState> sparseArray = cVar.f3553f;
            if (sparseArray != null) {
                this.f3553f = sparseArray.clone();
            } else {
                this.f3553f = new SparseArray<>(i3);
            }
            int i4 = this.f3555h;
            for (int i5 = 0; i5 < i4; i5++) {
                if (drawableArr[i5] != null) {
                    Drawable.ConstantState constantState = drawableArr[i5].getConstantState();
                    if (constantState != null) {
                        this.f3553f.put(i5, constantState);
                    } else {
                        this.f3554g[i5] = drawableArr[i5];
                    }
                }
            }
        }

        public final int a(Drawable drawable) {
            int i3 = this.f3555h;
            if (i3 >= this.f3554g.length) {
                p(i3, i3 + 10);
            }
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(this.f3548a);
            this.f3554g[i3] = drawable;
            this.f3555h++;
            this.f3552e = drawable.getChangingConfigurations() | this.f3552e;
            q();
            this.f3558k = null;
            this.f3557j = false;
            this.f3560m = false;
            this.f3569v = false;
            return i3;
        }

        public final void b(Resources.Theme theme) {
            if (theme != null) {
                f();
                int i3 = this.f3555h;
                Drawable[] drawableArr = this.f3554g;
                for (int i4 = 0; i4 < i3; i4++) {
                    if (drawableArr[i4] != null && drawableArr[i4].canApplyTheme()) {
                        drawableArr[i4].applyTheme(theme);
                        this.f3552e |= drawableArr[i4].getChangingConfigurations();
                    }
                }
                z(theme.getResources());
            }
        }

        public synchronized boolean c() {
            if (this.f3569v) {
                return this.f3570w;
            }
            f();
            this.f3569v = true;
            int i3 = this.f3555h;
            Drawable[] drawableArr = this.f3554g;
            for (int i4 = 0; i4 < i3; i4++) {
                if (drawableArr[i4].getConstantState() == null) {
                    this.f3570w = false;
                    return false;
                }
            }
            this.f3570w = true;
            return true;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            int i3 = this.f3555h;
            Drawable[] drawableArr = this.f3554g;
            for (int i4 = 0; i4 < i3; i4++) {
                Drawable drawable = drawableArr[i4];
                if (drawable == null) {
                    Drawable.ConstantState constantState = this.f3553f.get(i4);
                    if (constantState != null && constantState.canApplyTheme()) {
                        return true;
                    }
                } else if (drawable.canApplyTheme()) {
                    return true;
                }
            }
            return false;
        }

        public final void d() {
            this.f3572y = false;
        }

        public void e() {
            this.f3560m = true;
            f();
            int i3 = this.f3555h;
            Drawable[] drawableArr = this.f3554g;
            this.f3562o = -1;
            this.f3561n = -1;
            this.f3564q = 0;
            this.f3563p = 0;
            for (int i4 = 0; i4 < i3; i4++) {
                Drawable drawable = drawableArr[i4];
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth > this.f3561n) {
                    this.f3561n = intrinsicWidth;
                }
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicHeight > this.f3562o) {
                    this.f3562o = intrinsicHeight;
                }
                int minimumWidth = drawable.getMinimumWidth();
                if (minimumWidth > this.f3563p) {
                    this.f3563p = minimumWidth;
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (minimumHeight > this.f3564q) {
                    this.f3564q = minimumHeight;
                }
            }
        }

        public final void f() {
            SparseArray<Drawable.ConstantState> sparseArray = this.f3553f;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i3 = 0; i3 < size; i3++) {
                    this.f3554g[this.f3553f.keyAt(i3)] = t(this.f3553f.valueAt(i3).newDrawable(this.f3549b));
                }
                this.f3553f = null;
            }
        }

        public final int g() {
            return this.f3554g.length;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f3551d | this.f3552e;
        }

        public final Drawable h(int i3) {
            int iIndexOfKey;
            Drawable drawable = this.f3554g[i3];
            if (drawable != null) {
                return drawable;
            }
            SparseArray<Drawable.ConstantState> sparseArray = this.f3553f;
            if (sparseArray == null || (iIndexOfKey = sparseArray.indexOfKey(i3)) < 0) {
                return null;
            }
            Drawable drawableT = t(this.f3553f.valueAt(iIndexOfKey).newDrawable(this.f3549b));
            this.f3554g[i3] = drawableT;
            this.f3553f.removeAt(iIndexOfKey);
            if (this.f3553f.size() == 0) {
                this.f3553f = null;
            }
            return drawableT;
        }

        public final int i() {
            return this.f3555h;
        }

        public final int j() {
            if (!this.f3560m) {
                e();
            }
            return this.f3562o;
        }

        public final int k() {
            if (!this.f3560m) {
                e();
            }
            return this.f3564q;
        }

        public final int l() {
            if (!this.f3560m) {
                e();
            }
            return this.f3563p;
        }

        public final Rect m() {
            Rect rect = null;
            if (this.f3556i) {
                return null;
            }
            Rect rect2 = this.f3558k;
            if (rect2 != null || this.f3557j) {
                return rect2;
            }
            f();
            Rect rect3 = new Rect();
            int i3 = this.f3555h;
            Drawable[] drawableArr = this.f3554g;
            for (int i4 = 0; i4 < i3; i4++) {
                if (drawableArr[i4].getPadding(rect3)) {
                    if (rect == null) {
                        rect = new Rect(0, 0, 0, 0);
                    }
                    int i5 = rect3.left;
                    if (i5 > rect.left) {
                        rect.left = i5;
                    }
                    int i6 = rect3.top;
                    if (i6 > rect.top) {
                        rect.top = i6;
                    }
                    int i7 = rect3.right;
                    if (i7 > rect.right) {
                        rect.right = i7;
                    }
                    int i8 = rect3.bottom;
                    if (i8 > rect.bottom) {
                        rect.bottom = i8;
                    }
                }
            }
            this.f3557j = true;
            this.f3558k = rect;
            return rect;
        }

        public final int n() {
            if (!this.f3560m) {
                e();
            }
            return this.f3561n;
        }

        public final int o() {
            if (this.f3565r) {
                return this.f3566s;
            }
            f();
            int i3 = this.f3555h;
            Drawable[] drawableArr = this.f3554g;
            int opacity = i3 > 0 ? drawableArr[0].getOpacity() : -2;
            for (int i4 = 1; i4 < i3; i4++) {
                opacity = Drawable.resolveOpacity(opacity, drawableArr[i4].getOpacity());
            }
            this.f3566s = opacity;
            this.f3565r = true;
            return opacity;
        }

        public void p(int i3, int i4) {
            Drawable[] drawableArr = new Drawable[i4];
            System.arraycopy(this.f3554g, 0, drawableArr, 0, i3);
            this.f3554g = drawableArr;
        }

        public void q() {
            this.f3565r = false;
            this.f3567t = false;
        }

        public final boolean r() {
            return this.f3559l;
        }

        public abstract void s();

        public final Drawable t(Drawable drawable) {
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(this.f3573z);
            }
            Drawable drawableMutate = drawable.mutate();
            drawableMutate.setCallback(this.f3548a);
            return drawableMutate;
        }

        public final void u(boolean z3) {
            this.f3559l = z3;
        }

        public final void v(int i3) {
            this.A = i3;
        }

        public final void w(int i3) {
            this.B = i3;
        }

        public final boolean x(int i3, int i4) {
            int i5 = this.f3555h;
            Drawable[] drawableArr = this.f3554g;
            boolean z3 = false;
            for (int i6 = 0; i6 < i5; i6++) {
                if (drawableArr[i6] != null) {
                    boolean layoutDirection = Build.VERSION.SDK_INT >= 23 ? drawableArr[i6].setLayoutDirection(i3) : false;
                    if (i6 == i4) {
                        z3 = layoutDirection;
                    }
                }
            }
            this.f3573z = i3;
            return z3;
        }

        public final void y(boolean z3) {
            this.f3556i = z3;
        }

        public final void z(Resources resources) {
            if (resources != null) {
                this.f3549b = resources;
                int iResolveDensity = b.resolveDensity(resources, this.f3550c);
                int i3 = this.f3550c;
                this.f3550c = iResolveDensity;
                if (i3 != iResolveDensity) {
                    this.f3560m = false;
                    this.f3557j = false;
                }
            }
        }
    }

    public static int resolveDensity(Resources resources, int i3) {
        if (resources != null) {
            i3 = resources.getDisplayMetrics().densityDpi;
        }
        if (i3 == 0) {
            return 160;
        }
        return i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0066 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(boolean r14) {
        /*
            r13 = this;
            r0 = 1
            r13.f3539g = r0
            long r1 = android.os.SystemClock.uptimeMillis()
            android.graphics.drawable.Drawable r3 = r13.f3536d
            r4 = 255(0xff, double:1.26E-321)
            r6 = 0
            r7 = 0
            if (r3 == 0) goto L36
            long r9 = r13.f3543k
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 == 0) goto L38
            int r11 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r11 > 0) goto L22
            int r9 = r13.f3538f
            r3.setAlpha(r9)
            r13.f3543k = r7
            goto L38
        L22:
            long r9 = r9 - r1
            long r9 = r9 * r4
            int r9 = (int) r9
            k.b$c r10 = r13.f3534b
            int r10 = r10.A
            int r9 = r9 / r10
            int r9 = 255 - r9
            int r10 = r13.f3538f
            int r9 = r9 * r10
            int r9 = r9 / 255
            r3.setAlpha(r9)
            r3 = r0
            goto L39
        L36:
            r13.f3543k = r7
        L38:
            r3 = r6
        L39:
            android.graphics.drawable.Drawable r9 = r13.f3537e
            if (r9 == 0) goto L61
            long r10 = r13.f3544l
            int r12 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r12 == 0) goto L63
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 > 0) goto L50
            r9.setVisible(r6, r6)
            r0 = 0
            r13.f3537e = r0
            r13.f3544l = r7
            goto L63
        L50:
            long r10 = r10 - r1
            long r10 = r10 * r4
            int r3 = (int) r10
            k.b$c r4 = r13.f3534b
            int r4 = r4.B
            int r3 = r3 / r4
            int r4 = r13.f3538f
            int r3 = r3 * r4
            int r3 = r3 / 255
            r9.setAlpha(r3)
            goto L64
        L61:
            r13.f3544l = r7
        L63:
            r0 = r3
        L64:
            if (r14 == 0) goto L70
            if (r0 == 0) goto L70
            java.lang.Runnable r14 = r13.f3542j
            r3 = 16
            long r1 = r1 + r3
            r13.scheduleSelf(r14, r1)
        L70:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: k.b.a(boolean):void");
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        this.f3534b.b(theme);
    }

    public c b() {
        throw null;
    }

    public int c() {
        return this.f3540h;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return this.f3534b.canApplyTheme();
    }

    public void clearMutated() {
        this.f3534b.d();
        this.f3541i = false;
    }

    public final void d(Drawable drawable) {
        if (this.f3545m == null) {
            this.f3545m = new C0030b();
        }
        drawable.setCallback(this.f3545m.b(drawable.getCallback()));
        try {
            if (this.f3534b.A <= 0 && this.f3539g) {
                drawable.setAlpha(this.f3538f);
            }
            c cVar = this.f3534b;
            if (cVar.E) {
                drawable.setColorFilter(cVar.D);
            } else {
                if (cVar.H) {
                    e0.a.o(drawable, cVar.F);
                }
                c cVar2 = this.f3534b;
                if (cVar2.I) {
                    e0.a.p(drawable, cVar2.G);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.f3534b.f3571x);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 23) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            if (i3 >= 19) {
                drawable.setAutoMirrored(this.f3534b.C);
            }
            Rect rect = this.f3535c;
            if (i3 >= 21 && rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            drawable.setCallback(this.f3545m.a());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f3536d;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.f3537e;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    public final boolean e() {
        return isAutoMirrored() && e0.a.f(this) == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean f(int r10) {
        /*
            r9 = this;
            int r0 = r9.f3540h
            r1 = 0
            if (r10 != r0) goto L6
            return r1
        L6:
            long r2 = android.os.SystemClock.uptimeMillis()
            k.b$c r0 = r9.f3534b
            int r0 = r0.B
            r4 = 0
            r5 = 0
            if (r0 <= 0) goto L2e
            android.graphics.drawable.Drawable r0 = r9.f3537e
            if (r0 == 0) goto L1a
            r0.setVisible(r1, r1)
        L1a:
            android.graphics.drawable.Drawable r0 = r9.f3536d
            if (r0 == 0) goto L29
            r9.f3537e = r0
            k.b$c r0 = r9.f3534b
            int r0 = r0.B
            long r0 = (long) r0
            long r0 = r0 + r2
            r9.f3544l = r0
            goto L35
        L29:
            r9.f3537e = r4
            r9.f3544l = r5
            goto L35
        L2e:
            android.graphics.drawable.Drawable r0 = r9.f3536d
            if (r0 == 0) goto L35
            r0.setVisible(r1, r1)
        L35:
            if (r10 < 0) goto L55
            k.b$c r0 = r9.f3534b
            int r1 = r0.f3555h
            if (r10 >= r1) goto L55
            android.graphics.drawable.Drawable r0 = r0.h(r10)
            r9.f3536d = r0
            r9.f3540h = r10
            if (r0 == 0) goto L5a
            k.b$c r10 = r9.f3534b
            int r10 = r10.A
            if (r10 <= 0) goto L51
            long r7 = (long) r10
            long r2 = r2 + r7
            r9.f3543k = r2
        L51:
            r9.d(r0)
            goto L5a
        L55:
            r9.f3536d = r4
            r10 = -1
            r9.f3540h = r10
        L5a:
            long r0 = r9.f3543k
            int r10 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            r0 = 1
            if (r10 != 0) goto L67
            long r1 = r9.f3544l
            int r10 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r10 == 0) goto L79
        L67:
            java.lang.Runnable r10 = r9.f3542j
            if (r10 != 0) goto L73
            k.b$a r10 = new k.b$a
            r10.<init>()
            r9.f3542j = r10
            goto L76
        L73:
            r9.unscheduleSelf(r10)
        L76:
            r9.a(r0)
        L79:
            r9.invalidateSelf()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: k.b.f(int):boolean");
    }

    public void g(c cVar) {
        this.f3534b = cVar;
        int i3 = this.f3540h;
        if (i3 >= 0) {
            Drawable drawableH = cVar.h(i3);
            this.f3536d = drawableH;
            if (drawableH != null) {
                d(drawableH);
            }
        }
        this.f3537e = null;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f3538f;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.f3534b.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (!this.f3534b.c()) {
            return null;
        }
        this.f3534b.f3551d = getChangingConfigurations();
        return this.f3534b;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.f3536d;
    }

    @Override // android.graphics.drawable.Drawable
    public void getHotspotBounds(Rect rect) {
        Rect rect2 = this.f3535c;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.f3534b.r()) {
            return this.f3534b.j();
        }
        Drawable drawable = this.f3536d;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.f3534b.r()) {
            return this.f3534b.n();
        }
        Drawable drawable = this.f3536d;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        if (this.f3534b.r()) {
            return this.f3534b.k();
        }
        Drawable drawable = this.f3536d;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        if (this.f3534b.r()) {
            return this.f3534b.l();
        }
        Drawable drawable = this.f3536d;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f3536d;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        return this.f3534b.o();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Drawable drawable = this.f3536d;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        boolean padding;
        Rect rectM = this.f3534b.m();
        if (rectM != null) {
            rect.set(rectM);
            padding = (rectM.right | ((rectM.left | rectM.top) | rectM.bottom)) != 0;
        } else {
            Drawable drawable = this.f3536d;
            padding = drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
        }
        if (e()) {
            int i3 = rect.left;
            rect.left = rect.right;
            rect.right = i3;
        }
        return padding;
    }

    public final void h(Resources resources) {
        this.f3534b.z(resources);
    }

    public void invalidateDrawable(Drawable drawable) {
        c cVar = this.f3534b;
        if (cVar != null) {
            cVar.q();
        }
        if (drawable != this.f3536d || getCallback() == null) {
            return;
        }
        getCallback().invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.f3534b.C;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        boolean z3;
        Drawable drawable = this.f3537e;
        boolean z4 = true;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.f3537e = null;
            z3 = true;
        } else {
            z3 = false;
        }
        Drawable drawable2 = this.f3536d;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.f3539g) {
                this.f3536d.setAlpha(this.f3538f);
            }
        }
        if (this.f3544l != 0) {
            this.f3544l = 0L;
            z3 = true;
        }
        if (this.f3543k != 0) {
            this.f3543k = 0L;
        } else {
            z4 = z3;
        }
        if (z4) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f3541i && super.mutate() == this) {
            c cVarB = b();
            cVarB.s();
            g(cVarB);
            this.f3541i = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f3537e;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.f3536d;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i3) {
        return this.f3534b.x(i3, c());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i3) {
        Drawable drawable = this.f3537e;
        if (drawable != null) {
            return drawable.setLevel(i3);
        }
        Drawable drawable2 = this.f3536d;
        if (drawable2 != null) {
            return drawable2.setLevel(i3);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f3537e;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        Drawable drawable2 = this.f3536d;
        if (drawable2 != null) {
            return drawable2.setState(iArr);
        }
        return false;
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j3) {
        if (drawable != this.f3536d || getCallback() == null) {
            return;
        }
        getCallback().scheduleDrawable(this, runnable, j3);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i3) {
        if (this.f3539g && this.f3538f == i3) {
            return;
        }
        this.f3539g = true;
        this.f3538f = i3;
        Drawable drawable = this.f3536d;
        if (drawable != null) {
            if (this.f3543k == 0) {
                drawable.setAlpha(i3);
            } else {
                a(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z3) {
        c cVar = this.f3534b;
        if (cVar.C != z3) {
            cVar.C = z3;
            Drawable drawable = this.f3536d;
            if (drawable != null) {
                e0.a.j(drawable, z3);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        c cVar = this.f3534b;
        cVar.E = true;
        if (cVar.D != colorFilter) {
            cVar.D = colorFilter;
            Drawable drawable = this.f3536d;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z3) {
        c cVar = this.f3534b;
        if (cVar.f3571x != z3) {
            cVar.f3571x = z3;
            Drawable drawable = this.f3536d;
            if (drawable != null) {
                drawable.setDither(z3);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f3, float f4) {
        Drawable drawable = this.f3536d;
        if (drawable != null) {
            e0.a.k(drawable, f3, f4);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i3, int i4, int i5, int i6) {
        Rect rect = this.f3535c;
        if (rect == null) {
            this.f3535c = new Rect(i3, i4, i5, i6);
        } else {
            rect.set(i3, i4, i5, i6);
        }
        Drawable drawable = this.f3536d;
        if (drawable != null) {
            e0.a.l(drawable, i3, i4, i5, i6);
        }
    }

    @Override // android.graphics.drawable.Drawable, e0.b
    public void setTintList(ColorStateList colorStateList) {
        c cVar = this.f3534b;
        cVar.H = true;
        if (cVar.F != colorStateList) {
            cVar.F = colorStateList;
            e0.a.o(this.f3536d, colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, e0.b
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f3534b;
        cVar.I = true;
        if (cVar.G != mode) {
            cVar.G = mode;
            e0.a.p(this.f3536d, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z3, boolean z4) {
        boolean visible = super.setVisible(z3, z4);
        Drawable drawable = this.f3537e;
        if (drawable != null) {
            drawable.setVisible(z3, z4);
        }
        Drawable drawable2 = this.f3536d;
        if (drawable2 != null) {
            drawable2.setVisible(z3, z4);
        }
        return visible;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable != this.f3536d || getCallback() == null) {
            return;
        }
        getCallback().unscheduleDrawable(this, runnable);
    }
}
