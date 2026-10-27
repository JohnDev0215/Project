package b.b.m.a;

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
    public boolean E3;
    public boolean G3;
    public Runnable H3;
    public long I3;
    public long J3;
    public C0008b K3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f841c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Rect f842d;
    public Drawable s;
    public Drawable x;
    public int y = 255;
    public int F3 = -1;

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

    /* JADX INFO: renamed from: b.b.m.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DrawableContainer.java */
    public static class C0008b implements Drawable.Callback {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Drawable.Callback f844c;

        public Drawable.Callback a() {
            Drawable.Callback callback = this.f844c;
            this.f844c = null;
            return callback;
        }

        public C0008b b(Drawable.Callback callback) {
            this.f844c = callback;
            return this;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            Drawable.Callback callback = this.f844c;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            Drawable.Callback callback = this.f844c;
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
        public final b f845a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Resources f846b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f847c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f848d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f849e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public SparseArray<Drawable.ConstantState> f850f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Drawable[] f851g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f852h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f853i;
        public boolean j;
        public Rect k;
        public boolean l;
        public boolean m;
        public int n;
        public int o;
        public int p;
        public int q;
        public boolean r;
        public int s;
        public boolean t;
        public boolean u;
        public boolean v;
        public boolean w;
        public boolean x;
        public boolean y;
        public int z;

        public c(c cVar, b bVar, Resources resources) {
            this.f847c = 160;
            this.f853i = false;
            this.l = false;
            this.x = true;
            this.A = 0;
            this.B = 0;
            this.f845a = bVar;
            this.f846b = resources != null ? resources : cVar != null ? cVar.f846b : null;
            int iResolveDensity = b.resolveDensity(resources, cVar != null ? cVar.f847c : 0);
            this.f847c = iResolveDensity;
            if (cVar == null) {
                this.f851g = new Drawable[10];
                this.f852h = 0;
                return;
            }
            this.f848d = cVar.f848d;
            this.f849e = cVar.f849e;
            this.v = true;
            this.w = true;
            this.f853i = cVar.f853i;
            this.l = cVar.l;
            this.x = cVar.x;
            this.y = cVar.y;
            this.z = cVar.z;
            this.A = cVar.A;
            this.B = cVar.B;
            this.C = cVar.C;
            this.D = cVar.D;
            this.E = cVar.E;
            this.F = cVar.F;
            this.G = cVar.G;
            this.H = cVar.H;
            this.I = cVar.I;
            if (cVar.f847c == iResolveDensity) {
                if (cVar.j) {
                    this.k = new Rect(cVar.k);
                    this.j = true;
                }
                if (cVar.m) {
                    this.n = cVar.n;
                    this.o = cVar.o;
                    this.p = cVar.p;
                    this.q = cVar.q;
                    this.m = true;
                }
            }
            if (cVar.r) {
                this.s = cVar.s;
                this.r = true;
            }
            if (cVar.t) {
                this.u = cVar.u;
                this.t = true;
            }
            Drawable[] drawableArr = cVar.f851g;
            this.f851g = new Drawable[drawableArr.length];
            int i2 = cVar.f852h;
            this.f852h = i2;
            SparseArray<Drawable.ConstantState> sparseArray = cVar.f850f;
            if (sparseArray != null) {
                this.f850f = sparseArray.clone();
            } else {
                this.f850f = new SparseArray<>(i2);
            }
            int i3 = this.f852h;
            for (int i4 = 0; i4 < i3; i4++) {
                if (drawableArr[i4] != null) {
                    Drawable.ConstantState constantState = drawableArr[i4].getConstantState();
                    if (constantState != null) {
                        this.f850f.put(i4, constantState);
                    } else {
                        this.f851g[i4] = drawableArr[i4];
                    }
                }
            }
        }

        public final int a(Drawable drawable) {
            int i2 = this.f852h;
            if (i2 >= this.f851g.length) {
                p(i2, i2 + 10);
            }
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(this.f845a);
            this.f851g[i2] = drawable;
            this.f852h++;
            this.f849e = drawable.getChangingConfigurations() | this.f849e;
            q();
            this.k = null;
            this.j = false;
            this.m = false;
            this.v = false;
            return i2;
        }

        public final void b(Resources.Theme theme) {
            if (theme != null) {
                f();
                int i2 = this.f852h;
                Drawable[] drawableArr = this.f851g;
                for (int i3 = 0; i3 < i2; i3++) {
                    if (drawableArr[i3] != null && drawableArr[i3].canApplyTheme()) {
                        drawableArr[i3].applyTheme(theme);
                        this.f849e |= drawableArr[i3].getChangingConfigurations();
                    }
                }
                z(theme.getResources());
            }
        }

        public synchronized boolean c() {
            if (this.v) {
                return this.w;
            }
            f();
            this.v = true;
            int i2 = this.f852h;
            Drawable[] drawableArr = this.f851g;
            for (int i3 = 0; i3 < i2; i3++) {
                if (drawableArr[i3].getConstantState() == null) {
                    this.w = false;
                    return false;
                }
            }
            this.w = true;
            return true;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            int i2 = this.f852h;
            Drawable[] drawableArr = this.f851g;
            for (int i3 = 0; i3 < i2; i3++) {
                Drawable drawable = drawableArr[i3];
                if (drawable == null) {
                    Drawable.ConstantState constantState = this.f850f.get(i3);
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
            this.y = false;
        }

        public void e() {
            this.m = true;
            f();
            int i2 = this.f852h;
            Drawable[] drawableArr = this.f851g;
            this.o = -1;
            this.n = -1;
            this.q = 0;
            this.p = 0;
            for (int i3 = 0; i3 < i2; i3++) {
                Drawable drawable = drawableArr[i3];
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth > this.n) {
                    this.n = intrinsicWidth;
                }
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicHeight > this.o) {
                    this.o = intrinsicHeight;
                }
                int minimumWidth = drawable.getMinimumWidth();
                if (minimumWidth > this.p) {
                    this.p = minimumWidth;
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (minimumHeight > this.q) {
                    this.q = minimumHeight;
                }
            }
        }

        public final void f() {
            SparseArray<Drawable.ConstantState> sparseArray = this.f850f;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.f851g[this.f850f.keyAt(i2)] = t(this.f850f.valueAt(i2).newDrawable(this.f846b));
                }
                this.f850f = null;
            }
        }

        public final int g() {
            return this.f851g.length;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f848d | this.f849e;
        }

        public final Drawable h(int i2) {
            int iIndexOfKey;
            Drawable drawable = this.f851g[i2];
            if (drawable != null) {
                return drawable;
            }
            SparseArray<Drawable.ConstantState> sparseArray = this.f850f;
            if (sparseArray == null || (iIndexOfKey = sparseArray.indexOfKey(i2)) < 0) {
                return null;
            }
            Drawable drawableT = t(this.f850f.valueAt(iIndexOfKey).newDrawable(this.f846b));
            this.f851g[i2] = drawableT;
            this.f850f.removeAt(iIndexOfKey);
            if (this.f850f.size() == 0) {
                this.f850f = null;
            }
            return drawableT;
        }

        public final int i() {
            return this.f852h;
        }

        public final int j() {
            if (!this.m) {
                e();
            }
            return this.o;
        }

        public final int k() {
            if (!this.m) {
                e();
            }
            return this.q;
        }

        public final int l() {
            if (!this.m) {
                e();
            }
            return this.p;
        }

        public final Rect m() {
            Rect rect = null;
            if (this.f853i) {
                return null;
            }
            Rect rect2 = this.k;
            if (rect2 != null || this.j) {
                return rect2;
            }
            f();
            Rect rect3 = new Rect();
            int i2 = this.f852h;
            Drawable[] drawableArr = this.f851g;
            for (int i3 = 0; i3 < i2; i3++) {
                if (drawableArr[i3].getPadding(rect3)) {
                    if (rect == null) {
                        rect = new Rect(0, 0, 0, 0);
                    }
                    int i4 = rect3.left;
                    if (i4 > rect.left) {
                        rect.left = i4;
                    }
                    int i5 = rect3.top;
                    if (i5 > rect.top) {
                        rect.top = i5;
                    }
                    int i6 = rect3.right;
                    if (i6 > rect.right) {
                        rect.right = i6;
                    }
                    int i7 = rect3.bottom;
                    if (i7 > rect.bottom) {
                        rect.bottom = i7;
                    }
                }
            }
            this.j = true;
            this.k = rect;
            return rect;
        }

        public final int n() {
            if (!this.m) {
                e();
            }
            return this.n;
        }

        public final int o() {
            if (this.r) {
                return this.s;
            }
            f();
            int i2 = this.f852h;
            Drawable[] drawableArr = this.f851g;
            int opacity = i2 > 0 ? drawableArr[0].getOpacity() : -2;
            for (int i3 = 1; i3 < i2; i3++) {
                opacity = Drawable.resolveOpacity(opacity, drawableArr[i3].getOpacity());
            }
            this.s = opacity;
            this.r = true;
            return opacity;
        }

        public void p(int i2, int i3) {
            Drawable[] drawableArr = new Drawable[i3];
            System.arraycopy(this.f851g, 0, drawableArr, 0, i2);
            this.f851g = drawableArr;
        }

        public void q() {
            this.r = false;
            this.t = false;
        }

        public final boolean r() {
            return this.l;
        }

        public abstract void s();

        public final Drawable t(Drawable drawable) {
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(this.z);
            }
            Drawable drawableMutate = drawable.mutate();
            drawableMutate.setCallback(this.f845a);
            return drawableMutate;
        }

        public final void u(boolean z) {
            this.l = z;
        }

        public final void v(int i2) {
            this.A = i2;
        }

        public final void w(int i2) {
            this.B = i2;
        }

        public final boolean x(int i2, int i3) {
            int i4 = this.f852h;
            Drawable[] drawableArr = this.f851g;
            boolean z = false;
            for (int i5 = 0; i5 < i4; i5++) {
                if (drawableArr[i5] != null) {
                    boolean layoutDirection = Build.VERSION.SDK_INT >= 23 ? drawableArr[i5].setLayoutDirection(i2) : false;
                    if (i5 == i3) {
                        z = layoutDirection;
                    }
                }
            }
            this.z = i2;
            return z;
        }

        public final void y(boolean z) {
            this.f853i = z;
        }

        public final void z(Resources resources) {
            if (resources != null) {
                this.f846b = resources;
                int iResolveDensity = b.resolveDensity(resources, this.f847c);
                int i2 = this.f847c;
                this.f847c = iResolveDensity;
                if (i2 != iResolveDensity) {
                    this.m = false;
                    this.j = false;
                }
            }
        }
    }

    public static int resolveDensity(Resources resources, int i2) {
        if (resources != null) {
            i2 = resources.getDisplayMetrics().densityDpi;
        }
        if (i2 == 0) {
            return 160;
        }
        return i2;
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
            r13.E3 = r0
            long r1 = android.os.SystemClock.uptimeMillis()
            android.graphics.drawable.Drawable r3 = r13.s
            r4 = 255(0xff, double:1.26E-321)
            r6 = 0
            r7 = 0
            if (r3 == 0) goto L36
            long r9 = r13.I3
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 == 0) goto L38
            int r11 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r11 > 0) goto L22
            int r9 = r13.y
            r3.setAlpha(r9)
            r13.I3 = r7
            goto L38
        L22:
            long r9 = r9 - r1
            long r9 = r9 * r4
            int r9 = (int) r9
            b.b.m.a.b$c r10 = r13.f841c
            int r10 = r10.A
            int r9 = r9 / r10
            int r9 = 255 - r9
            int r10 = r13.y
            int r9 = r9 * r10
            int r9 = r9 / 255
            r3.setAlpha(r9)
            r3 = r0
            goto L39
        L36:
            r13.I3 = r7
        L38:
            r3 = r6
        L39:
            android.graphics.drawable.Drawable r9 = r13.x
            if (r9 == 0) goto L61
            long r10 = r13.J3
            int r12 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r12 == 0) goto L63
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 > 0) goto L50
            r9.setVisible(r6, r6)
            r0 = 0
            r13.x = r0
            r13.J3 = r7
            goto L63
        L50:
            long r10 = r10 - r1
            long r10 = r10 * r4
            int r3 = (int) r10
            b.b.m.a.b$c r4 = r13.f841c
            int r4 = r4.B
            int r3 = r3 / r4
            int r4 = r13.y
            int r3 = r3 * r4
            int r3 = r3 / 255
            r9.setAlpha(r3)
            goto L64
        L61:
            r13.J3 = r7
        L63:
            r0 = r3
        L64:
            if (r14 == 0) goto L70
            if (r0 == 0) goto L70
            java.lang.Runnable r14 = r13.H3
            r3 = 16
            long r1 = r1 + r3
            r13.scheduleSelf(r14, r1)
        L70:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.m.a.b.a(boolean):void");
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        this.f841c.b(theme);
    }

    public c b() {
        throw null;
    }

    public int c() {
        return this.F3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return this.f841c.canApplyTheme();
    }

    public void clearMutated() {
        this.f841c.d();
        this.G3 = false;
    }

    public final void d(Drawable drawable) {
        if (this.K3 == null) {
            this.K3 = new C0008b();
        }
        drawable.setCallback(this.K3.b(drawable.getCallback()));
        try {
            if (this.f841c.A <= 0 && this.E3) {
                drawable.setAlpha(this.y);
            }
            c cVar = this.f841c;
            if (cVar.E) {
                drawable.setColorFilter(cVar.D);
            } else {
                if (cVar.H) {
                    b.f.g.l.a.n(drawable, cVar.F);
                }
                c cVar2 = this.f841c;
                if (cVar2.I) {
                    b.f.g.l.a.o(drawable, cVar2.G);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.f841c.x);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 23) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            if (i2 >= 19) {
                drawable.setAutoMirrored(this.f841c.C);
            }
            Rect rect = this.f842d;
            if (i2 >= 21 && rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            drawable.setCallback(this.K3.a());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.x;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    public final boolean e() {
        return isAutoMirrored() && b.f.g.l.a.e(this) == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean f(int r10) {
        /*
            r9 = this;
            int r0 = r9.F3
            r1 = 0
            if (r10 != r0) goto L6
            return r1
        L6:
            long r2 = android.os.SystemClock.uptimeMillis()
            b.b.m.a.b$c r0 = r9.f841c
            int r0 = r0.B
            r4 = 0
            r5 = 0
            if (r0 <= 0) goto L2e
            android.graphics.drawable.Drawable r0 = r9.x
            if (r0 == 0) goto L1a
            r0.setVisible(r1, r1)
        L1a:
            android.graphics.drawable.Drawable r0 = r9.s
            if (r0 == 0) goto L29
            r9.x = r0
            b.b.m.a.b$c r0 = r9.f841c
            int r0 = r0.B
            long r0 = (long) r0
            long r0 = r0 + r2
            r9.J3 = r0
            goto L35
        L29:
            r9.x = r4
            r9.J3 = r5
            goto L35
        L2e:
            android.graphics.drawable.Drawable r0 = r9.s
            if (r0 == 0) goto L35
            r0.setVisible(r1, r1)
        L35:
            if (r10 < 0) goto L55
            b.b.m.a.b$c r0 = r9.f841c
            int r1 = r0.f852h
            if (r10 >= r1) goto L55
            android.graphics.drawable.Drawable r0 = r0.h(r10)
            r9.s = r0
            r9.F3 = r10
            if (r0 == 0) goto L5a
            b.b.m.a.b$c r10 = r9.f841c
            int r10 = r10.A
            if (r10 <= 0) goto L51
            long r7 = (long) r10
            long r2 = r2 + r7
            r9.I3 = r2
        L51:
            r9.d(r0)
            goto L5a
        L55:
            r9.s = r4
            r10 = -1
            r9.F3 = r10
        L5a:
            long r0 = r9.I3
            int r10 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            r0 = 1
            if (r10 != 0) goto L67
            long r1 = r9.J3
            int r10 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r10 == 0) goto L79
        L67:
            java.lang.Runnable r10 = r9.H3
            if (r10 != 0) goto L73
            b.b.m.a.b$a r10 = new b.b.m.a.b$a
            r10.<init>()
            r9.H3 = r10
            goto L76
        L73:
            r9.unscheduleSelf(r10)
        L76:
            r9.a(r0)
        L79:
            r9.invalidateSelf()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.m.a.b.f(int):boolean");
    }

    public void g(c cVar) {
        this.f841c = cVar;
        int i2 = this.F3;
        if (i2 >= 0) {
            Drawable drawableH = cVar.h(i2);
            this.s = drawableH;
            if (drawableH != null) {
                d(drawableH);
            }
        }
        this.x = null;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.y;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.f841c.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (!this.f841c.c()) {
            return null;
        }
        this.f841c.f848d = getChangingConfigurations();
        return this.f841c;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.s;
    }

    @Override // android.graphics.drawable.Drawable
    public void getHotspotBounds(Rect rect) {
        Rect rect2 = this.f842d;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.f841c.r()) {
            return this.f841c.j();
        }
        Drawable drawable = this.s;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.f841c.r()) {
            return this.f841c.n();
        }
        Drawable drawable = this.s;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        if (this.f841c.r()) {
            return this.f841c.k();
        }
        Drawable drawable = this.s;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        if (this.f841c.r()) {
            return this.f841c.l();
        }
        Drawable drawable = this.s;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.s;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        return this.f841c.o();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Drawable drawable = this.s;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        boolean padding;
        Rect rectM = this.f841c.m();
        if (rectM != null) {
            rect.set(rectM);
            padding = (rectM.right | ((rectM.left | rectM.top) | rectM.bottom)) != 0;
        } else {
            Drawable drawable = this.s;
            padding = drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
        }
        if (e()) {
            int i2 = rect.left;
            rect.left = rect.right;
            rect.right = i2;
        }
        return padding;
    }

    public final void h(Resources resources) {
        this.f841c.z(resources);
    }

    public void invalidateDrawable(Drawable drawable) {
        c cVar = this.f841c;
        if (cVar != null) {
            cVar.q();
        }
        if (drawable != this.s || getCallback() == null) {
            return;
        }
        getCallback().invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.f841c.C;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        boolean z;
        Drawable drawable = this.x;
        boolean z2 = true;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.x = null;
            z = true;
        } else {
            z = false;
        }
        Drawable drawable2 = this.s;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.E3) {
                this.s.setAlpha(this.y);
            }
        }
        if (this.J3 != 0) {
            this.J3 = 0L;
            z = true;
        }
        if (this.I3 != 0) {
            this.I3 = 0L;
        } else {
            z2 = z;
        }
        if (z2) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.G3 && super.mutate() == this) {
            c cVarB = b();
            cVarB.s();
            g(cVarB);
            this.G3 = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.x;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.s;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i2) {
        return this.f841c.x(i2, c());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i2) {
        Drawable drawable = this.x;
        if (drawable != null) {
            return drawable.setLevel(i2);
        }
        Drawable drawable2 = this.s;
        if (drawable2 != null) {
            return drawable2.setLevel(i2);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.x;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        Drawable drawable2 = this.s;
        if (drawable2 != null) {
            return drawable2.setState(iArr);
        }
        return false;
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (drawable != this.s || getCallback() == null) {
            return;
        }
        getCallback().scheduleDrawable(this, runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (this.E3 && this.y == i2) {
            return;
        }
        this.E3 = true;
        this.y = i2;
        Drawable drawable = this.s;
        if (drawable != null) {
            if (this.I3 == 0) {
                drawable.setAlpha(i2);
            } else {
                a(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        c cVar = this.f841c;
        if (cVar.C != z) {
            cVar.C = z;
            Drawable drawable = this.s;
            if (drawable != null) {
                b.f.g.l.a.i(drawable, z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        c cVar = this.f841c;
        cVar.E = true;
        if (cVar.D != colorFilter) {
            cVar.D = colorFilter;
            Drawable drawable = this.s;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        c cVar = this.f841c;
        if (cVar.x != z) {
            cVar.x = z;
            Drawable drawable = this.s;
            if (drawable != null) {
                drawable.setDither(z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f2, float f3) {
        Drawable drawable = this.s;
        if (drawable != null) {
            b.f.g.l.a.j(drawable, f2, f3);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i2, int i3, int i4, int i5) {
        Rect rect = this.f842d;
        if (rect == null) {
            this.f842d = new Rect(i2, i3, i4, i5);
        } else {
            rect.set(i2, i3, i4, i5);
        }
        Drawable drawable = this.s;
        if (drawable != null) {
            b.f.g.l.a.k(drawable, i2, i3, i4, i5);
        }
    }

    @Override // android.graphics.drawable.Drawable, b.f.g.l.b
    public void setTintList(ColorStateList colorStateList) {
        c cVar = this.f841c;
        cVar.H = true;
        if (cVar.F != colorStateList) {
            cVar.F = colorStateList;
            b.f.g.l.a.n(this.s, colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, b.f.g.l.b
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f841c;
        cVar.I = true;
        if (cVar.G != mode) {
            cVar.G = mode;
            b.f.g.l.a.o(this.s, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.x;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        Drawable drawable2 = this.s;
        if (drawable2 != null) {
            drawable2.setVisible(z, z2);
        }
        return visible;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable != this.s || getCallback() == null) {
            return;
        }
        getCallback().unscheduleDrawable(this, runnable);
    }
}
