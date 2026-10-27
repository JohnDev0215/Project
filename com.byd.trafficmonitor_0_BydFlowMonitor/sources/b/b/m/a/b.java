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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f1510b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Rect f1511c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Drawable f1512d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f1513e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1515g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1517i;
    public Runnable j;
    public long k;
    public long l;
    public C0017b m;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1514f = 255;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1516h = -1;

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
    public static class C0017b implements Drawable.Callback {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Drawable.Callback f1519b;

        public Drawable.Callback a() {
            Drawable.Callback callback = this.f1519b;
            this.f1519b = null;
            return callback;
        }

        public C0017b b(Drawable.Callback callback) {
            this.f1519b = callback;
            return this;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            Drawable.Callback callback = this.f1519b;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            Drawable.Callback callback = this.f1519b;
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
        public final b f1520a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Resources f1521b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1522c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1523d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1524e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public SparseArray<Drawable.ConstantState> f1525f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public Drawable[] f1526g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1527h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f1528i;
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
            this.f1522c = 160;
            this.f1528i = false;
            this.l = false;
            this.x = true;
            this.A = 0;
            this.B = 0;
            this.f1520a = bVar;
            this.f1521b = resources != null ? resources : cVar != null ? cVar.f1521b : null;
            int iResolveDensity = b.resolveDensity(resources, cVar != null ? cVar.f1522c : 0);
            this.f1522c = iResolveDensity;
            if (cVar == null) {
                this.f1526g = new Drawable[10];
                this.f1527h = 0;
                return;
            }
            this.f1523d = cVar.f1523d;
            this.f1524e = cVar.f1524e;
            this.v = true;
            this.w = true;
            this.f1528i = cVar.f1528i;
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
            if (cVar.f1522c == iResolveDensity) {
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
            Drawable[] drawableArr = cVar.f1526g;
            this.f1526g = new Drawable[drawableArr.length];
            int i2 = cVar.f1527h;
            this.f1527h = i2;
            SparseArray<Drawable.ConstantState> sparseArray = cVar.f1525f;
            if (sparseArray != null) {
                this.f1525f = sparseArray.clone();
            } else {
                this.f1525f = new SparseArray<>(i2);
            }
            int i3 = this.f1527h;
            for (int i4 = 0; i4 < i3; i4++) {
                if (drawableArr[i4] != null) {
                    Drawable.ConstantState constantState = drawableArr[i4].getConstantState();
                    if (constantState != null) {
                        this.f1525f.put(i4, constantState);
                    } else {
                        this.f1526g[i4] = drawableArr[i4];
                    }
                }
            }
        }

        public final int a(Drawable drawable) {
            int i2 = this.f1527h;
            if (i2 >= this.f1526g.length) {
                p(i2, i2 + 10);
            }
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(this.f1520a);
            this.f1526g[i2] = drawable;
            this.f1527h++;
            this.f1524e = drawable.getChangingConfigurations() | this.f1524e;
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
                int i2 = this.f1527h;
                Drawable[] drawableArr = this.f1526g;
                for (int i3 = 0; i3 < i2; i3++) {
                    if (drawableArr[i3] != null && drawableArr[i3].canApplyTheme()) {
                        drawableArr[i3].applyTheme(theme);
                        this.f1524e |= drawableArr[i3].getChangingConfigurations();
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
            int i2 = this.f1527h;
            Drawable[] drawableArr = this.f1526g;
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
            int i2 = this.f1527h;
            Drawable[] drawableArr = this.f1526g;
            for (int i3 = 0; i3 < i2; i3++) {
                Drawable drawable = drawableArr[i3];
                if (drawable == null) {
                    Drawable.ConstantState constantState = this.f1525f.get(i3);
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
            int i2 = this.f1527h;
            Drawable[] drawableArr = this.f1526g;
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
            SparseArray<Drawable.ConstantState> sparseArray = this.f1525f;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.f1526g[this.f1525f.keyAt(i2)] = t(this.f1525f.valueAt(i2).newDrawable(this.f1521b));
                }
                this.f1525f = null;
            }
        }

        public final int g() {
            return this.f1526g.length;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1523d | this.f1524e;
        }

        public final Drawable h(int i2) {
            int iIndexOfKey;
            Drawable drawable = this.f1526g[i2];
            if (drawable != null) {
                return drawable;
            }
            SparseArray<Drawable.ConstantState> sparseArray = this.f1525f;
            if (sparseArray == null || (iIndexOfKey = sparseArray.indexOfKey(i2)) < 0) {
                return null;
            }
            Drawable drawableT = t(this.f1525f.valueAt(iIndexOfKey).newDrawable(this.f1521b));
            this.f1526g[i2] = drawableT;
            this.f1525f.removeAt(iIndexOfKey);
            if (this.f1525f.size() == 0) {
                this.f1525f = null;
            }
            return drawableT;
        }

        public final int i() {
            return this.f1527h;
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
            if (this.f1528i) {
                return null;
            }
            Rect rect2 = this.k;
            if (rect2 != null || this.j) {
                return rect2;
            }
            f();
            Rect rect3 = new Rect();
            int i2 = this.f1527h;
            Drawable[] drawableArr = this.f1526g;
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
            int i2 = this.f1527h;
            Drawable[] drawableArr = this.f1526g;
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
            System.arraycopy(this.f1526g, 0, drawableArr, 0, i2);
            this.f1526g = drawableArr;
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
            drawableMutate.setCallback(this.f1520a);
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
            int i4 = this.f1527h;
            Drawable[] drawableArr = this.f1526g;
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
            this.f1528i = z;
        }

        public final void z(Resources resources) {
            if (resources != null) {
                this.f1521b = resources;
                int iResolveDensity = b.resolveDensity(resources, this.f1522c);
                int i2 = this.f1522c;
                this.f1522c = iResolveDensity;
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
            r13.f1515g = r0
            long r1 = android.os.SystemClock.uptimeMillis()
            android.graphics.drawable.Drawable r3 = r13.f1512d
            r4 = 255(0xff, double:1.26E-321)
            r6 = 0
            r7 = 0
            if (r3 == 0) goto L36
            long r9 = r13.k
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 == 0) goto L38
            int r11 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r11 > 0) goto L22
            int r9 = r13.f1514f
            r3.setAlpha(r9)
            r13.k = r7
            goto L38
        L22:
            long r9 = r9 - r1
            long r9 = r9 * r4
            int r9 = (int) r9
            b.b.m.a.b$c r10 = r13.f1510b
            int r10 = r10.A
            int r9 = r9 / r10
            int r9 = 255 - r9
            int r10 = r13.f1514f
            int r9 = r9 * r10
            int r9 = r9 / 255
            r3.setAlpha(r9)
            r3 = r0
            goto L39
        L36:
            r13.k = r7
        L38:
            r3 = r6
        L39:
            android.graphics.drawable.Drawable r9 = r13.f1513e
            if (r9 == 0) goto L61
            long r10 = r13.l
            int r12 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r12 == 0) goto L63
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 > 0) goto L50
            r9.setVisible(r6, r6)
            r0 = 0
            r13.f1513e = r0
            r13.l = r7
            goto L63
        L50:
            long r10 = r10 - r1
            long r10 = r10 * r4
            int r3 = (int) r10
            b.b.m.a.b$c r4 = r13.f1510b
            int r4 = r4.B
            int r3 = r3 / r4
            int r4 = r13.f1514f
            int r3 = r3 * r4
            int r3 = r3 / 255
            r9.setAlpha(r3)
            goto L64
        L61:
            r13.l = r7
        L63:
            r0 = r3
        L64:
            if (r14 == 0) goto L70
            if (r0 == 0) goto L70
            java.lang.Runnable r14 = r13.j
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
        this.f1510b.b(theme);
    }

    public c b() {
        throw null;
    }

    public int c() {
        return this.f1516h;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return this.f1510b.canApplyTheme();
    }

    public void clearMutated() {
        this.f1510b.d();
        this.f1517i = false;
    }

    public final void d(Drawable drawable) {
        if (this.m == null) {
            this.m = new C0017b();
        }
        drawable.setCallback(this.m.b(drawable.getCallback()));
        try {
            if (this.f1510b.A <= 0 && this.f1515g) {
                drawable.setAlpha(this.f1514f);
            }
            c cVar = this.f1510b;
            if (cVar.E) {
                drawable.setColorFilter(cVar.D);
            } else {
                if (cVar.H) {
                    b.h.f.l.a.o(drawable, cVar.F);
                }
                c cVar2 = this.f1510b;
                if (cVar2.I) {
                    b.h.f.l.a.p(drawable, cVar2.G);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.f1510b.x);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 23) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            if (i2 >= 19) {
                drawable.setAutoMirrored(this.f1510b.C);
            }
            Rect rect = this.f1511c;
            if (i2 >= 21 && rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            drawable.setCallback(this.m.a());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f1512d;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.f1513e;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    public final boolean e() {
        return isAutoMirrored() && b.h.f.l.a.f(this) == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean f(int r10) {
        /*
            r9 = this;
            int r0 = r9.f1516h
            r1 = 0
            if (r10 != r0) goto L6
            return r1
        L6:
            long r2 = android.os.SystemClock.uptimeMillis()
            b.b.m.a.b$c r0 = r9.f1510b
            int r0 = r0.B
            r4 = 0
            r5 = 0
            if (r0 <= 0) goto L2e
            android.graphics.drawable.Drawable r0 = r9.f1513e
            if (r0 == 0) goto L1a
            r0.setVisible(r1, r1)
        L1a:
            android.graphics.drawable.Drawable r0 = r9.f1512d
            if (r0 == 0) goto L29
            r9.f1513e = r0
            b.b.m.a.b$c r0 = r9.f1510b
            int r0 = r0.B
            long r0 = (long) r0
            long r0 = r0 + r2
            r9.l = r0
            goto L35
        L29:
            r9.f1513e = r4
            r9.l = r5
            goto L35
        L2e:
            android.graphics.drawable.Drawable r0 = r9.f1512d
            if (r0 == 0) goto L35
            r0.setVisible(r1, r1)
        L35:
            if (r10 < 0) goto L55
            b.b.m.a.b$c r0 = r9.f1510b
            int r1 = r0.f1527h
            if (r10 >= r1) goto L55
            android.graphics.drawable.Drawable r0 = r0.h(r10)
            r9.f1512d = r0
            r9.f1516h = r10
            if (r0 == 0) goto L5a
            b.b.m.a.b$c r10 = r9.f1510b
            int r10 = r10.A
            if (r10 <= 0) goto L51
            long r7 = (long) r10
            long r2 = r2 + r7
            r9.k = r2
        L51:
            r9.d(r0)
            goto L5a
        L55:
            r9.f1512d = r4
            r10 = -1
            r9.f1516h = r10
        L5a:
            long r0 = r9.k
            int r10 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            r0 = 1
            if (r10 != 0) goto L67
            long r1 = r9.l
            int r10 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r10 == 0) goto L79
        L67:
            java.lang.Runnable r10 = r9.j
            if (r10 != 0) goto L73
            b.b.m.a.b$a r10 = new b.b.m.a.b$a
            r10.<init>()
            r9.j = r10
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
        this.f1510b = cVar;
        int i2 = this.f1516h;
        if (i2 >= 0) {
            Drawable drawableH = cVar.h(i2);
            this.f1512d = drawableH;
            if (drawableH != null) {
                d(drawableH);
            }
        }
        this.f1513e = null;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f1514f;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.f1510b.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (!this.f1510b.c()) {
            return null;
        }
        this.f1510b.f1523d = getChangingConfigurations();
        return this.f1510b;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.f1512d;
    }

    @Override // android.graphics.drawable.Drawable
    public void getHotspotBounds(Rect rect) {
        Rect rect2 = this.f1511c;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.f1510b.r()) {
            return this.f1510b.j();
        }
        Drawable drawable = this.f1512d;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.f1510b.r()) {
            return this.f1510b.n();
        }
        Drawable drawable = this.f1512d;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        if (this.f1510b.r()) {
            return this.f1510b.k();
        }
        Drawable drawable = this.f1512d;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        if (this.f1510b.r()) {
            return this.f1510b.l();
        }
        Drawable drawable = this.f1512d;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f1512d;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        return this.f1510b.o();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Drawable drawable = this.f1512d;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        boolean padding;
        Rect rectM = this.f1510b.m();
        if (rectM != null) {
            rect.set(rectM);
            padding = (rectM.right | ((rectM.left | rectM.top) | rectM.bottom)) != 0;
        } else {
            Drawable drawable = this.f1512d;
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
        this.f1510b.z(resources);
    }

    public void invalidateDrawable(Drawable drawable) {
        c cVar = this.f1510b;
        if (cVar != null) {
            cVar.q();
        }
        if (drawable != this.f1512d || getCallback() == null) {
            return;
        }
        getCallback().invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.f1510b.C;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        boolean z;
        Drawable drawable = this.f1513e;
        boolean z2 = true;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.f1513e = null;
            z = true;
        } else {
            z = false;
        }
        Drawable drawable2 = this.f1512d;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.f1515g) {
                this.f1512d.setAlpha(this.f1514f);
            }
        }
        if (this.l != 0) {
            this.l = 0L;
            z = true;
        }
        if (this.k != 0) {
            this.k = 0L;
        } else {
            z2 = z;
        }
        if (z2) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f1517i && super.mutate() == this) {
            c cVarB = b();
            cVarB.s();
            g(cVarB);
            this.f1517i = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f1513e;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.f1512d;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i2) {
        return this.f1510b.x(i2, c());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i2) {
        Drawable drawable = this.f1513e;
        if (drawable != null) {
            return drawable.setLevel(i2);
        }
        Drawable drawable2 = this.f1512d;
        if (drawable2 != null) {
            return drawable2.setLevel(i2);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f1513e;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        Drawable drawable2 = this.f1512d;
        if (drawable2 != null) {
            return drawable2.setState(iArr);
        }
        return false;
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (drawable != this.f1512d || getCallback() == null) {
            return;
        }
        getCallback().scheduleDrawable(this, runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (this.f1515g && this.f1514f == i2) {
            return;
        }
        this.f1515g = true;
        this.f1514f = i2;
        Drawable drawable = this.f1512d;
        if (drawable != null) {
            if (this.k == 0) {
                drawable.setAlpha(i2);
            } else {
                a(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        c cVar = this.f1510b;
        if (cVar.C != z) {
            cVar.C = z;
            Drawable drawable = this.f1512d;
            if (drawable != null) {
                b.h.f.l.a.j(drawable, z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        c cVar = this.f1510b;
        cVar.E = true;
        if (cVar.D != colorFilter) {
            cVar.D = colorFilter;
            Drawable drawable = this.f1512d;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        c cVar = this.f1510b;
        if (cVar.x != z) {
            cVar.x = z;
            Drawable drawable = this.f1512d;
            if (drawable != null) {
                drawable.setDither(z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f2, float f3) {
        Drawable drawable = this.f1512d;
        if (drawable != null) {
            b.h.f.l.a.k(drawable, f2, f3);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i2, int i3, int i4, int i5) {
        Rect rect = this.f1511c;
        if (rect == null) {
            this.f1511c = new Rect(i2, i3, i4, i5);
        } else {
            rect.set(i2, i3, i4, i5);
        }
        Drawable drawable = this.f1512d;
        if (drawable != null) {
            b.h.f.l.a.l(drawable, i2, i3, i4, i5);
        }
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTintList(ColorStateList colorStateList) {
        c cVar = this.f1510b;
        cVar.H = true;
        if (cVar.F != colorStateList) {
            cVar.F = colorStateList;
            b.h.f.l.a.o(this.f1512d, colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f1510b;
        cVar.I = true;
        if (cVar.G != mode) {
            cVar.G = mode;
            b.h.f.l.a.p(this.f1512d, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.f1513e;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        Drawable drawable2 = this.f1512d;
        if (drawable2 != null) {
            drawable2.setVisible(z, z2);
        }
        return visible;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable != this.f1512d || getCallback() == null) {
            return;
        }
        getCallback().unscheduleDrawable(this, runnable);
    }
}
