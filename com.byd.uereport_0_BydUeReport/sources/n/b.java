package n;

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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f6461d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Rect f6462e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f6463f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f6464g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f6466i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f6468k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Runnable f6469l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f6470m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f6471n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public C0092b f6472o;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f6465h = 255;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f6467j = -1;

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

    /* JADX INFO: renamed from: n.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DrawableContainer.java */
    public static class C0092b implements Drawable.Callback {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Drawable.Callback f6474d;

        public Drawable.Callback a() {
            Drawable.Callback callback = this.f6474d;
            this.f6474d = null;
            return callback;
        }

        public C0092b b(Drawable.Callback callback) {
            this.f6474d = callback;
            return this;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j8) {
            Drawable.Callback callback = this.f6474d;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j8);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            Drawable.Callback callback = this.f6474d;
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
        public final b f6475a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Resources f6476b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f6477c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f6478d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f6479e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public SparseArray<Drawable.ConstantState> f6480f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Drawable[] f6481g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f6482h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f6483i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public boolean f6484j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public Rect f6485k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public boolean f6486l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public boolean f6487m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f6488n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public int f6489o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f6490p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f6491q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public boolean f6492r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f6493s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public boolean f6494t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public boolean f6495u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public boolean f6496v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public boolean f6497w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public boolean f6498x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public boolean f6499y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public int f6500z;

        public c(c cVar, b bVar, Resources resources) {
            this.f6477c = 160;
            this.f6483i = false;
            this.f6486l = false;
            this.f6498x = true;
            this.A = 0;
            this.B = 0;
            this.f6475a = bVar;
            this.f6476b = resources != null ? resources : cVar != null ? cVar.f6476b : null;
            int iResolveDensity = b.resolveDensity(resources, cVar != null ? cVar.f6477c : 0);
            this.f6477c = iResolveDensity;
            if (cVar == null) {
                this.f6481g = new Drawable[10];
                this.f6482h = 0;
                return;
            }
            this.f6478d = cVar.f6478d;
            this.f6479e = cVar.f6479e;
            this.f6496v = true;
            this.f6497w = true;
            this.f6483i = cVar.f6483i;
            this.f6486l = cVar.f6486l;
            this.f6498x = cVar.f6498x;
            this.f6499y = cVar.f6499y;
            this.f6500z = cVar.f6500z;
            this.A = cVar.A;
            this.B = cVar.B;
            this.C = cVar.C;
            this.D = cVar.D;
            this.E = cVar.E;
            this.F = cVar.F;
            this.G = cVar.G;
            this.H = cVar.H;
            this.I = cVar.I;
            if (cVar.f6477c == iResolveDensity) {
                if (cVar.f6484j) {
                    this.f6485k = new Rect(cVar.f6485k);
                    this.f6484j = true;
                }
                if (cVar.f6487m) {
                    this.f6488n = cVar.f6488n;
                    this.f6489o = cVar.f6489o;
                    this.f6490p = cVar.f6490p;
                    this.f6491q = cVar.f6491q;
                    this.f6487m = true;
                }
            }
            if (cVar.f6492r) {
                this.f6493s = cVar.f6493s;
                this.f6492r = true;
            }
            if (cVar.f6494t) {
                this.f6495u = cVar.f6495u;
                this.f6494t = true;
            }
            Drawable[] drawableArr = cVar.f6481g;
            this.f6481g = new Drawable[drawableArr.length];
            int i8 = cVar.f6482h;
            this.f6482h = i8;
            SparseArray<Drawable.ConstantState> sparseArray = cVar.f6480f;
            if (sparseArray != null) {
                this.f6480f = sparseArray.clone();
            } else {
                this.f6480f = new SparseArray<>(i8);
            }
            int i9 = this.f6482h;
            for (int i10 = 0; i10 < i9; i10++) {
                if (drawableArr[i10] != null) {
                    Drawable.ConstantState constantState = drawableArr[i10].getConstantState();
                    if (constantState != null) {
                        this.f6480f.put(i10, constantState);
                    } else {
                        this.f6481g[i10] = drawableArr[i10];
                    }
                }
            }
        }

        public final int a(Drawable drawable) {
            int i8 = this.f6482h;
            if (i8 >= this.f6481g.length) {
                p(i8, i8 + 10);
            }
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(this.f6475a);
            this.f6481g[i8] = drawable;
            this.f6482h++;
            this.f6479e = drawable.getChangingConfigurations() | this.f6479e;
            q();
            this.f6485k = null;
            this.f6484j = false;
            this.f6487m = false;
            this.f6496v = false;
            return i8;
        }

        public final void b(Resources.Theme theme) {
            if (theme != null) {
                f();
                int i8 = this.f6482h;
                Drawable[] drawableArr = this.f6481g;
                for (int i9 = 0; i9 < i8; i9++) {
                    if (drawableArr[i9] != null && drawableArr[i9].canApplyTheme()) {
                        drawableArr[i9].applyTheme(theme);
                        this.f6479e |= drawableArr[i9].getChangingConfigurations();
                    }
                }
                z(theme.getResources());
            }
        }

        public synchronized boolean c() {
            if (this.f6496v) {
                return this.f6497w;
            }
            f();
            this.f6496v = true;
            int i8 = this.f6482h;
            Drawable[] drawableArr = this.f6481g;
            for (int i9 = 0; i9 < i8; i9++) {
                if (drawableArr[i9].getConstantState() == null) {
                    this.f6497w = false;
                    return false;
                }
            }
            this.f6497w = true;
            return true;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            int i8 = this.f6482h;
            Drawable[] drawableArr = this.f6481g;
            for (int i9 = 0; i9 < i8; i9++) {
                Drawable drawable = drawableArr[i9];
                if (drawable == null) {
                    Drawable.ConstantState constantState = this.f6480f.get(i9);
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
            this.f6499y = false;
        }

        public void e() {
            this.f6487m = true;
            f();
            int i8 = this.f6482h;
            Drawable[] drawableArr = this.f6481g;
            this.f6489o = -1;
            this.f6488n = -1;
            this.f6491q = 0;
            this.f6490p = 0;
            for (int i9 = 0; i9 < i8; i9++) {
                Drawable drawable = drawableArr[i9];
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth > this.f6488n) {
                    this.f6488n = intrinsicWidth;
                }
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicHeight > this.f6489o) {
                    this.f6489o = intrinsicHeight;
                }
                int minimumWidth = drawable.getMinimumWidth();
                if (minimumWidth > this.f6490p) {
                    this.f6490p = minimumWidth;
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (minimumHeight > this.f6491q) {
                    this.f6491q = minimumHeight;
                }
            }
        }

        public final void f() {
            SparseArray<Drawable.ConstantState> sparseArray = this.f6480f;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i8 = 0; i8 < size; i8++) {
                    this.f6481g[this.f6480f.keyAt(i8)] = t(this.f6480f.valueAt(i8).newDrawable(this.f6476b));
                }
                this.f6480f = null;
            }
        }

        public final int g() {
            return this.f6481g.length;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f6478d | this.f6479e;
        }

        public final Drawable h(int i8) {
            int iIndexOfKey;
            Drawable drawable = this.f6481g[i8];
            if (drawable != null) {
                return drawable;
            }
            SparseArray<Drawable.ConstantState> sparseArray = this.f6480f;
            if (sparseArray == null || (iIndexOfKey = sparseArray.indexOfKey(i8)) < 0) {
                return null;
            }
            Drawable drawableT = t(this.f6480f.valueAt(iIndexOfKey).newDrawable(this.f6476b));
            this.f6481g[i8] = drawableT;
            this.f6480f.removeAt(iIndexOfKey);
            if (this.f6480f.size() == 0) {
                this.f6480f = null;
            }
            return drawableT;
        }

        public final int i() {
            return this.f6482h;
        }

        public final int j() {
            if (!this.f6487m) {
                e();
            }
            return this.f6489o;
        }

        public final int k() {
            if (!this.f6487m) {
                e();
            }
            return this.f6491q;
        }

        public final int l() {
            if (!this.f6487m) {
                e();
            }
            return this.f6490p;
        }

        public final Rect m() {
            Rect rect = null;
            if (this.f6483i) {
                return null;
            }
            Rect rect2 = this.f6485k;
            if (rect2 != null || this.f6484j) {
                return rect2;
            }
            f();
            Rect rect3 = new Rect();
            int i8 = this.f6482h;
            Drawable[] drawableArr = this.f6481g;
            for (int i9 = 0; i9 < i8; i9++) {
                if (drawableArr[i9].getPadding(rect3)) {
                    if (rect == null) {
                        rect = new Rect(0, 0, 0, 0);
                    }
                    int i10 = rect3.left;
                    if (i10 > rect.left) {
                        rect.left = i10;
                    }
                    int i11 = rect3.top;
                    if (i11 > rect.top) {
                        rect.top = i11;
                    }
                    int i12 = rect3.right;
                    if (i12 > rect.right) {
                        rect.right = i12;
                    }
                    int i13 = rect3.bottom;
                    if (i13 > rect.bottom) {
                        rect.bottom = i13;
                    }
                }
            }
            this.f6484j = true;
            this.f6485k = rect;
            return rect;
        }

        public final int n() {
            if (!this.f6487m) {
                e();
            }
            return this.f6488n;
        }

        public final int o() {
            if (this.f6492r) {
                return this.f6493s;
            }
            f();
            int i8 = this.f6482h;
            Drawable[] drawableArr = this.f6481g;
            int opacity = i8 > 0 ? drawableArr[0].getOpacity() : -2;
            for (int i9 = 1; i9 < i8; i9++) {
                opacity = Drawable.resolveOpacity(opacity, drawableArr[i9].getOpacity());
            }
            this.f6493s = opacity;
            this.f6492r = true;
            return opacity;
        }

        public void p(int i8, int i9) {
            Drawable[] drawableArr = new Drawable[i9];
            System.arraycopy(this.f6481g, 0, drawableArr, 0, i8);
            this.f6481g = drawableArr;
        }

        public void q() {
            this.f6492r = false;
            this.f6494t = false;
        }

        public final boolean r() {
            return this.f6486l;
        }

        public abstract void s();

        public final Drawable t(Drawable drawable) {
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(this.f6500z);
            }
            Drawable drawableMutate = drawable.mutate();
            drawableMutate.setCallback(this.f6475a);
            return drawableMutate;
        }

        public final void u(boolean z7) {
            this.f6486l = z7;
        }

        public final void v(int i8) {
            this.A = i8;
        }

        public final void w(int i8) {
            this.B = i8;
        }

        public final boolean x(int i8, int i9) {
            int i10 = this.f6482h;
            Drawable[] drawableArr = this.f6481g;
            boolean z7 = false;
            for (int i11 = 0; i11 < i10; i11++) {
                if (drawableArr[i11] != null) {
                    boolean layoutDirection = Build.VERSION.SDK_INT >= 23 ? drawableArr[i11].setLayoutDirection(i8) : false;
                    if (i11 == i9) {
                        z7 = layoutDirection;
                    }
                }
            }
            this.f6500z = i8;
            return z7;
        }

        public final void y(boolean z7) {
            this.f6483i = z7;
        }

        public final void z(Resources resources) {
            if (resources != null) {
                this.f6476b = resources;
                int iResolveDensity = b.resolveDensity(resources, this.f6477c);
                int i8 = this.f6477c;
                this.f6477c = iResolveDensity;
                if (i8 != iResolveDensity) {
                    this.f6487m = false;
                    this.f6484j = false;
                }
            }
        }
    }

    public static int resolveDensity(Resources resources, int i8) {
        if (resources != null) {
            i8 = resources.getDisplayMetrics().densityDpi;
        }
        if (i8 == 0) {
            return 160;
        }
        return i8;
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
            r13.f6466i = r0
            long r1 = android.os.SystemClock.uptimeMillis()
            android.graphics.drawable.Drawable r3 = r13.f6463f
            r4 = 255(0xff, double:1.26E-321)
            r6 = 0
            r7 = 0
            if (r3 == 0) goto L36
            long r9 = r13.f6470m
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 == 0) goto L38
            int r11 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r11 > 0) goto L22
            int r9 = r13.f6465h
            r3.setAlpha(r9)
            r13.f6470m = r7
            goto L38
        L22:
            long r9 = r9 - r1
            long r9 = r9 * r4
            int r9 = (int) r9
            n.b$c r10 = r13.f6461d
            int r10 = r10.A
            int r9 = r9 / r10
            int r9 = 255 - r9
            int r10 = r13.f6465h
            int r9 = r9 * r10
            int r9 = r9 / 255
            r3.setAlpha(r9)
            r3 = r0
            goto L39
        L36:
            r13.f6470m = r7
        L38:
            r3 = r6
        L39:
            android.graphics.drawable.Drawable r9 = r13.f6464g
            if (r9 == 0) goto L61
            long r10 = r13.f6471n
            int r12 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r12 == 0) goto L63
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 > 0) goto L50
            r9.setVisible(r6, r6)
            r0 = 0
            r13.f6464g = r0
            r13.f6471n = r7
            goto L63
        L50:
            long r10 = r10 - r1
            long r10 = r10 * r4
            int r3 = (int) r10
            n.b$c r4 = r13.f6461d
            int r4 = r4.B
            int r3 = r3 / r4
            int r4 = r13.f6465h
            int r3 = r3 * r4
            int r3 = r3 / 255
            r9.setAlpha(r3)
            goto L64
        L61:
            r13.f6471n = r7
        L63:
            r0 = r3
        L64:
            if (r14 == 0) goto L70
            if (r0 == 0) goto L70
            java.lang.Runnable r14 = r13.f6469l
            r3 = 16
            long r1 = r1 + r3
            r13.scheduleSelf(r14, r1)
        L70:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: n.b.a(boolean):void");
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        this.f6461d.b(theme);
    }

    public c b() {
        throw null;
    }

    public int c() {
        return this.f6467j;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return this.f6461d.canApplyTheme();
    }

    public void clearMutated() {
        this.f6461d.d();
        this.f6468k = false;
    }

    public final void d(Drawable drawable) {
        if (this.f6472o == null) {
            this.f6472o = new C0092b();
        }
        drawable.setCallback(this.f6472o.b(drawable.getCallback()));
        try {
            if (this.f6461d.A <= 0 && this.f6466i) {
                drawable.setAlpha(this.f6465h);
            }
            c cVar = this.f6461d;
            if (cVar.E) {
                drawable.setColorFilter(cVar.D);
            } else {
                if (cVar.H) {
                    h0.a.o(drawable, cVar.F);
                }
                c cVar2 = this.f6461d;
                if (cVar2.I) {
                    h0.a.p(drawable, cVar2.G);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.f6461d.f6498x);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            int i8 = Build.VERSION.SDK_INT;
            if (i8 >= 23) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            if (i8 >= 19) {
                drawable.setAutoMirrored(this.f6461d.C);
            }
            Rect rect = this.f6462e;
            if (i8 >= 21 && rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            drawable.setCallback(this.f6472o.a());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f6463f;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.f6464g;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    public final boolean e() {
        return isAutoMirrored() && h0.a.f(this) == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean f(int r10) {
        /*
            r9 = this;
            int r0 = r9.f6467j
            r1 = 0
            if (r10 != r0) goto L6
            return r1
        L6:
            long r2 = android.os.SystemClock.uptimeMillis()
            n.b$c r0 = r9.f6461d
            int r0 = r0.B
            r4 = 0
            r5 = 0
            if (r0 <= 0) goto L2e
            android.graphics.drawable.Drawable r0 = r9.f6464g
            if (r0 == 0) goto L1a
            r0.setVisible(r1, r1)
        L1a:
            android.graphics.drawable.Drawable r0 = r9.f6463f
            if (r0 == 0) goto L29
            r9.f6464g = r0
            n.b$c r0 = r9.f6461d
            int r0 = r0.B
            long r0 = (long) r0
            long r0 = r0 + r2
            r9.f6471n = r0
            goto L35
        L29:
            r9.f6464g = r4
            r9.f6471n = r5
            goto L35
        L2e:
            android.graphics.drawable.Drawable r0 = r9.f6463f
            if (r0 == 0) goto L35
            r0.setVisible(r1, r1)
        L35:
            if (r10 < 0) goto L55
            n.b$c r0 = r9.f6461d
            int r1 = r0.f6482h
            if (r10 >= r1) goto L55
            android.graphics.drawable.Drawable r0 = r0.h(r10)
            r9.f6463f = r0
            r9.f6467j = r10
            if (r0 == 0) goto L5a
            n.b$c r10 = r9.f6461d
            int r10 = r10.A
            if (r10 <= 0) goto L51
            long r7 = (long) r10
            long r2 = r2 + r7
            r9.f6470m = r2
        L51:
            r9.d(r0)
            goto L5a
        L55:
            r9.f6463f = r4
            r10 = -1
            r9.f6467j = r10
        L5a:
            long r0 = r9.f6470m
            int r10 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            r0 = 1
            if (r10 != 0) goto L67
            long r1 = r9.f6471n
            int r10 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r10 == 0) goto L79
        L67:
            java.lang.Runnable r10 = r9.f6469l
            if (r10 != 0) goto L73
            n.b$a r10 = new n.b$a
            r10.<init>()
            r9.f6469l = r10
            goto L76
        L73:
            r9.unscheduleSelf(r10)
        L76:
            r9.a(r0)
        L79:
            r9.invalidateSelf()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: n.b.f(int):boolean");
    }

    public void g(c cVar) {
        this.f6461d = cVar;
        int i8 = this.f6467j;
        if (i8 >= 0) {
            Drawable drawableH = cVar.h(i8);
            this.f6463f = drawableH;
            if (drawableH != null) {
                d(drawableH);
            }
        }
        this.f6464g = null;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f6465h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.f6461d.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (!this.f6461d.c()) {
            return null;
        }
        this.f6461d.f6478d = getChangingConfigurations();
        return this.f6461d;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.f6463f;
    }

    @Override // android.graphics.drawable.Drawable
    public void getHotspotBounds(Rect rect) {
        Rect rect2 = this.f6462e;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.f6461d.r()) {
            return this.f6461d.j();
        }
        Drawable drawable = this.f6463f;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.f6461d.r()) {
            return this.f6461d.n();
        }
        Drawable drawable = this.f6463f;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        if (this.f6461d.r()) {
            return this.f6461d.k();
        }
        Drawable drawable = this.f6463f;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        if (this.f6461d.r()) {
            return this.f6461d.l();
        }
        Drawable drawable = this.f6463f;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f6463f;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        return this.f6461d.o();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Drawable drawable = this.f6463f;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        boolean padding;
        Rect rectM = this.f6461d.m();
        if (rectM != null) {
            rect.set(rectM);
            padding = (rectM.right | ((rectM.left | rectM.top) | rectM.bottom)) != 0;
        } else {
            Drawable drawable = this.f6463f;
            padding = drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
        }
        if (e()) {
            int i8 = rect.left;
            rect.left = rect.right;
            rect.right = i8;
        }
        return padding;
    }

    public final void h(Resources resources) {
        this.f6461d.z(resources);
    }

    public void invalidateDrawable(Drawable drawable) {
        c cVar = this.f6461d;
        if (cVar != null) {
            cVar.q();
        }
        if (drawable != this.f6463f || getCallback() == null) {
            return;
        }
        getCallback().invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.f6461d.C;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        boolean z7;
        Drawable drawable = this.f6464g;
        boolean z8 = true;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.f6464g = null;
            z7 = true;
        } else {
            z7 = false;
        }
        Drawable drawable2 = this.f6463f;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.f6466i) {
                this.f6463f.setAlpha(this.f6465h);
            }
        }
        if (this.f6471n != 0) {
            this.f6471n = 0L;
            z7 = true;
        }
        if (this.f6470m != 0) {
            this.f6470m = 0L;
        } else {
            z8 = z7;
        }
        if (z8) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f6468k && super.mutate() == this) {
            c cVarB = b();
            cVarB.s();
            g(cVarB);
            this.f6468k = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f6464g;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.f6463f;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i8) {
        return this.f6461d.x(i8, c());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i8) {
        Drawable drawable = this.f6464g;
        if (drawable != null) {
            return drawable.setLevel(i8);
        }
        Drawable drawable2 = this.f6463f;
        if (drawable2 != null) {
            return drawable2.setLevel(i8);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f6464g;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        Drawable drawable2 = this.f6463f;
        if (drawable2 != null) {
            return drawable2.setState(iArr);
        }
        return false;
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j8) {
        if (drawable != this.f6463f || getCallback() == null) {
            return;
        }
        getCallback().scheduleDrawable(this, runnable, j8);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
        if (this.f6466i && this.f6465h == i8) {
            return;
        }
        this.f6466i = true;
        this.f6465h = i8;
        Drawable drawable = this.f6463f;
        if (drawable != null) {
            if (this.f6470m == 0) {
                drawable.setAlpha(i8);
            } else {
                a(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z7) {
        c cVar = this.f6461d;
        if (cVar.C != z7) {
            cVar.C = z7;
            Drawable drawable = this.f6463f;
            if (drawable != null) {
                h0.a.j(drawable, z7);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        c cVar = this.f6461d;
        cVar.E = true;
        if (cVar.D != colorFilter) {
            cVar.D = colorFilter;
            Drawable drawable = this.f6463f;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z7) {
        c cVar = this.f6461d;
        if (cVar.f6498x != z7) {
            cVar.f6498x = z7;
            Drawable drawable = this.f6463f;
            if (drawable != null) {
                drawable.setDither(z7);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f8, float f9) {
        Drawable drawable = this.f6463f;
        if (drawable != null) {
            h0.a.k(drawable, f8, f9);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i8, int i9, int i10, int i11) {
        Rect rect = this.f6462e;
        if (rect == null) {
            this.f6462e = new Rect(i8, i9, i10, i11);
        } else {
            rect.set(i8, i9, i10, i11);
        }
        Drawable drawable = this.f6463f;
        if (drawable != null) {
            h0.a.l(drawable, i8, i9, i10, i11);
        }
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTintList(ColorStateList colorStateList) {
        c cVar = this.f6461d;
        cVar.H = true;
        if (cVar.F != colorStateList) {
            cVar.F = colorStateList;
            h0.a.o(this.f6463f, colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f6461d;
        cVar.I = true;
        if (cVar.G != mode) {
            cVar.G = mode;
            h0.a.p(this.f6463f, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z7, boolean z8) {
        boolean visible = super.setVisible(z7, z8);
        Drawable drawable = this.f6464g;
        if (drawable != null) {
            drawable.setVisible(z7, z8);
        }
        Drawable drawable2 = this.f6463f;
        if (drawable2 != null) {
            drawable2.setVisible(z7, z8);
        }
        return visible;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable != this.f6463f || getCallback() == null) {
            return;
        }
        getCallback().unscheduleDrawable(this, runnable);
    }
}
