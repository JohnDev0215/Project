package androidx.appcompat.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import defpackage.sd0;

/* JADX INFO: loaded from: classes.dex */
public class DrawableContainerCompat extends Drawable implements Drawable.Callback {
    public static final /* synthetic */ int q = 0;
    public DrawableContainerState e;
    public Rect f;
    public Drawable g;
    public Drawable h;
    public boolean j;
    public boolean l;
    public Runnable m;
    public long n;
    public long o;
    public BlockInvalidateCallback p;
    public int i = 255;
    public int k = -1;

    public static class Api21Impl {
        private Api21Impl() {
        }
    }

    public static abstract class DrawableContainerState extends Drawable.ConstantState {
        public int A;
        public boolean B;
        public ColorFilter C;
        public boolean D;
        public ColorStateList E;
        public PorterDuff.Mode F;
        public boolean G;
        public boolean H;
        public final DrawableContainerCompat a;
        public Resources b;
        public int c;
        public int d;
        public int e;
        public SparseArray<Drawable.ConstantState> f;
        public Drawable[] g;
        public int h;
        public boolean i;
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
        public int y;
        public int z;

        public DrawableContainerState(DrawableContainerState drawableContainerState, DrawableContainerCompat drawableContainerCompat, Resources resources) {
            this.i = false;
            this.l = false;
            this.x = true;
            this.z = 0;
            this.A = 0;
            this.a = drawableContainerCompat;
            this.b = resources != null ? resources : drawableContainerState != null ? drawableContainerState.b : null;
            int i = drawableContainerState != null ? drawableContainerState.c : 0;
            int i2 = DrawableContainerCompat.q;
            i = resources != null ? resources.getDisplayMetrics().densityDpi : i;
            i = i == 0 ? 160 : i;
            this.c = i;
            if (drawableContainerState == null) {
                this.g = new Drawable[10];
                this.h = 0;
                return;
            }
            this.d = drawableContainerState.d;
            this.e = drawableContainerState.e;
            this.v = true;
            this.w = true;
            this.i = drawableContainerState.i;
            this.l = drawableContainerState.l;
            this.x = drawableContainerState.x;
            this.y = drawableContainerState.y;
            this.z = drawableContainerState.z;
            this.A = drawableContainerState.A;
            this.B = drawableContainerState.B;
            this.C = drawableContainerState.C;
            this.D = drawableContainerState.D;
            this.E = drawableContainerState.E;
            this.F = drawableContainerState.F;
            this.G = drawableContainerState.G;
            this.H = drawableContainerState.H;
            if (drawableContainerState.c == i) {
                if (drawableContainerState.j) {
                    this.k = drawableContainerState.k != null ? new Rect(drawableContainerState.k) : null;
                    this.j = true;
                }
                if (drawableContainerState.m) {
                    this.n = drawableContainerState.n;
                    this.o = drawableContainerState.o;
                    this.p = drawableContainerState.p;
                    this.q = drawableContainerState.q;
                    this.m = true;
                }
            }
            if (drawableContainerState.r) {
                this.s = drawableContainerState.s;
                this.r = true;
            }
            if (drawableContainerState.t) {
                this.u = drawableContainerState.u;
                this.t = true;
            }
            Drawable[] drawableArr = drawableContainerState.g;
            this.g = new Drawable[drawableArr.length];
            this.h = drawableContainerState.h;
            SparseArray<Drawable.ConstantState> sparseArray = drawableContainerState.f;
            if (sparseArray != null) {
                this.f = sparseArray.clone();
            } else {
                this.f = new SparseArray<>(this.h);
            }
            int i3 = this.h;
            for (int i4 = 0; i4 < i3; i4++) {
                Drawable drawable = drawableArr[i4];
                if (drawable != null) {
                    Drawable.ConstantState constantState = drawable.getConstantState();
                    if (constantState != null) {
                        this.f.put(i4, constantState);
                    } else {
                        this.g[i4] = drawableArr[i4];
                    }
                }
            }
        }

        public final void a() {
            this.m = true;
            b();
            int i = this.h;
            Drawable[] drawableArr = this.g;
            this.o = -1;
            this.n = -1;
            this.q = 0;
            this.p = 0;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
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

        public final void b() {
            SparseArray<Drawable.ConstantState> sparseArray = this.f;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i = 0; i < size; i++) {
                    int iKeyAt = this.f.keyAt(i);
                    Drawable.ConstantState constantStateValueAt = this.f.valueAt(i);
                    Drawable[] drawableArr = this.g;
                    Drawable drawableNewDrawable = constantStateValueAt.newDrawable(this.b);
                    drawableNewDrawable.setLayoutDirection(this.y);
                    Drawable drawableMutate = drawableNewDrawable.mutate();
                    drawableMutate.setCallback(this.a);
                    drawableArr[iKeyAt] = drawableMutate;
                }
                this.f = null;
            }
        }

        public final Drawable c(int i) {
            int iIndexOfKey;
            Drawable drawable = this.g[i];
            if (drawable != null) {
                return drawable;
            }
            SparseArray<Drawable.ConstantState> sparseArray = this.f;
            if (sparseArray == null || (iIndexOfKey = sparseArray.indexOfKey(i)) < 0) {
                return null;
            }
            Drawable drawableNewDrawable = this.f.valueAt(iIndexOfKey).newDrawable(this.b);
            drawableNewDrawable.setLayoutDirection(this.y);
            Drawable drawableMutate = drawableNewDrawable.mutate();
            drawableMutate.setCallback(this.a);
            this.g[i] = drawableMutate;
            this.f.removeAt(iIndexOfKey);
            if (this.f.size() == 0) {
                this.f = null;
            }
            return drawableMutate;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final boolean canApplyTheme() {
            int i = this.h;
            Drawable[] drawableArr = this.g;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                if (drawable == null) {
                    Drawable.ConstantState constantState = this.f.get(i2);
                    if (constantState != null && constantState.canApplyTheme()) {
                        return true;
                    }
                } else if (drawable.canApplyTheme()) {
                    return true;
                }
            }
            return false;
        }

        public void d() {
            int i = this.h;
            Drawable[] drawableArr = this.g;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                if (drawable != null) {
                    drawable.mutate();
                }
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final int getChangingConfigurations() {
            return this.e | this.d;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0066 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(boolean r14) {
        /*
            r13 = this;
            r0 = 1
            r13.j = r0
            long r1 = android.os.SystemClock.uptimeMillis()
            android.graphics.drawable.Drawable r3 = r13.g
            r4 = 255(0xff, double:1.26E-321)
            r6 = 0
            r8 = 0
            if (r3 == 0) goto L36
            long r9 = r13.n
            int r11 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r11 == 0) goto L38
            int r11 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r11 > 0) goto L22
            int r9 = r13.i
            r3.setAlpha(r9)
            r13.n = r6
            goto L38
        L22:
            long r9 = r9 - r1
            long r9 = r9 * r4
            int r9 = (int) r9
            androidx.appcompat.graphics.drawable.DrawableContainerCompat$DrawableContainerState r10 = r13.e
            int r10 = r10.z
            int r9 = r9 / r10
            int r9 = 255 - r9
            int r10 = r13.i
            int r9 = r9 * r10
            int r9 = r9 / 255
            r3.setAlpha(r9)
            r3 = r0
            goto L39
        L36:
            r13.n = r6
        L38:
            r3 = r8
        L39:
            android.graphics.drawable.Drawable r9 = r13.h
            if (r9 == 0) goto L61
            long r10 = r13.o
            int r12 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r12 == 0) goto L63
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 > 0) goto L50
            r9.setVisible(r8, r8)
            r0 = 0
            r13.h = r0
            r13.o = r6
            goto L63
        L50:
            long r10 = r10 - r1
            long r10 = r10 * r4
            int r3 = (int) r10
            androidx.appcompat.graphics.drawable.DrawableContainerCompat$DrawableContainerState r4 = r13.e
            int r4 = r4.A
            int r3 = r3 / r4
            int r4 = r13.i
            int r3 = r3 * r4
            int r3 = r3 / 255
            r9.setAlpha(r3)
            goto L64
        L61:
            r13.o = r6
        L63:
            r0 = r3
        L64:
            if (r14 == 0) goto L70
            if (r0 == 0) goto L70
            java.lang.Runnable r14 = r13.m
            r3 = 16
            long r1 = r1 + r3
            r13.scheduleSelf(r14, r1)
        L70:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.graphics.drawable.DrawableContainerCompat.a(boolean):void");
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        DrawableContainerState drawableContainerState = this.e;
        if (theme == null) {
            drawableContainerState.getClass();
            return;
        }
        drawableContainerState.b();
        int i = drawableContainerState.h;
        Drawable[] drawableArr = drawableContainerState.g;
        for (int i2 = 0; i2 < i; i2++) {
            Drawable drawable = drawableArr[i2];
            if (drawable != null && drawable.canApplyTheme()) {
                drawableArr[i2].applyTheme(theme);
                drawableContainerState.e |= drawableArr[i2].getChangingConfigurations();
            }
        }
        Resources resources = theme.getResources();
        if (resources != null) {
            drawableContainerState.b = resources;
            int i3 = resources.getDisplayMetrics().densityDpi;
            if (i3 == 0) {
                i3 = 160;
            }
            int i4 = drawableContainerState.c;
            drawableContainerState.c = i3;
            if (i4 != i3) {
                drawableContainerState.m = false;
                drawableContainerState.j = false;
            }
        }
    }

    public DrawableContainerState b() {
        return this.e;
    }

    public final void c(Drawable drawable) {
        if (this.p == null) {
            this.p = new BlockInvalidateCallback();
        }
        BlockInvalidateCallback blockInvalidateCallback = this.p;
        blockInvalidateCallback.e = drawable.getCallback();
        drawable.setCallback(blockInvalidateCallback);
        try {
            if (this.e.z <= 0 && this.j) {
                drawable.setAlpha(this.i);
            }
            DrawableContainerState drawableContainerState = this.e;
            if (drawableContainerState.D) {
                drawable.setColorFilter(drawableContainerState.C);
            } else {
                if (drawableContainerState.G) {
                    drawable.setTintList(drawableContainerState.E);
                }
                DrawableContainerState drawableContainerState2 = this.e;
                if (drawableContainerState2.H) {
                    drawable.setTintMode(drawableContainerState2.F);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.e.x);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            drawable.setLayoutDirection(getLayoutDirection());
            drawable.setAutoMirrored(this.e.B);
            Rect rect = this.f;
            if (rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            BlockInvalidateCallback blockInvalidateCallback2 = this.p;
            Drawable.Callback callback = blockInvalidateCallback2.e;
            blockInvalidateCallback2.e = null;
            drawable.setCallback(callback);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        return this.e.canApplyTheme();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d(int r10) {
        /*
            r9 = this;
            int r0 = r9.k
            r1 = 0
            if (r10 != r0) goto L6
            return r1
        L6:
            long r2 = android.os.SystemClock.uptimeMillis()
            androidx.appcompat.graphics.drawable.DrawableContainerCompat$DrawableContainerState r0 = r9.e
            int r0 = r0.A
            r4 = 0
            r5 = 0
            if (r0 <= 0) goto L2e
            android.graphics.drawable.Drawable r0 = r9.h
            if (r0 == 0) goto L1a
            r0.setVisible(r1, r1)
        L1a:
            android.graphics.drawable.Drawable r0 = r9.g
            if (r0 == 0) goto L29
            r9.h = r0
            androidx.appcompat.graphics.drawable.DrawableContainerCompat$DrawableContainerState r0 = r9.e
            int r0 = r0.A
            long r0 = (long) r0
            long r0 = r0 + r2
            r9.o = r0
            goto L35
        L29:
            r9.h = r4
            r9.o = r5
            goto L35
        L2e:
            android.graphics.drawable.Drawable r0 = r9.g
            if (r0 == 0) goto L35
            r0.setVisible(r1, r1)
        L35:
            if (r10 < 0) goto L55
            androidx.appcompat.graphics.drawable.DrawableContainerCompat$DrawableContainerState r0 = r9.e
            int r1 = r0.h
            if (r10 >= r1) goto L55
            android.graphics.drawable.Drawable r0 = r0.c(r10)
            r9.g = r0
            r9.k = r10
            if (r0 == 0) goto L5a
            androidx.appcompat.graphics.drawable.DrawableContainerCompat$DrawableContainerState r10 = r9.e
            int r10 = r10.z
            if (r10 <= 0) goto L51
            long r7 = (long) r10
            long r2 = r2 + r7
            r9.n = r2
        L51:
            r9.c(r0)
            goto L5a
        L55:
            r9.g = r4
            r10 = -1
            r9.k = r10
        L5a:
            long r0 = r9.n
            int r10 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            r0 = 1
            if (r10 != 0) goto L67
            long r1 = r9.o
            int r10 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r10 == 0) goto L7c
        L67:
            java.lang.Runnable r10 = r9.m
            if (r10 != 0) goto L76
            androidx.appcompat.graphics.drawable.DrawableContainerCompat$1 r10 = new androidx.appcompat.graphics.drawable.DrawableContainerCompat$1
            r1 = r9
            androidx.appcompat.graphics.drawable.StateListDrawableCompat r1 = (androidx.appcompat.graphics.drawable.StateListDrawableCompat) r1
            r10.<init>()
            r9.m = r10
            goto L79
        L76:
            r9.unscheduleSelf(r10)
        L79:
            r9.a(r0)
        L7c:
            r9.invalidateSelf()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.graphics.drawable.DrawableContainerCompat.d(int):boolean");
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Drawable drawable = this.g;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    public void e(DrawableContainerState drawableContainerState) {
        this.e = drawableContainerState;
        int i = this.k;
        if (i >= 0) {
            Drawable drawableC = drawableContainerState.c(i);
            this.g = drawableC;
            if (drawableC != null) {
                c(drawableC);
            }
        }
        this.h = null;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.i;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        return this.e.getChangingConfigurations() | super.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        boolean z;
        DrawableContainerState drawableContainerState = this.e;
        if (!drawableContainerState.v) {
            drawableContainerState.b();
            drawableContainerState.v = true;
            int i = drawableContainerState.h;
            Drawable[] drawableArr = drawableContainerState.g;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    drawableContainerState.w = true;
                    z = true;
                    break;
                }
                if (drawableArr[i2].getConstantState() == null) {
                    drawableContainerState.w = false;
                    z = false;
                    break;
                }
                i2++;
            }
        } else {
            z = drawableContainerState.w;
        }
        if (!z) {
            return null;
        }
        this.e.d = getChangingConfigurations();
        return this.e;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable getCurrent() {
        return this.g;
    }

    @Override // android.graphics.drawable.Drawable
    public final void getHotspotBounds(Rect rect) {
        Rect rect2 = this.f;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        DrawableContainerState drawableContainerState = this.e;
        if (drawableContainerState.l) {
            if (!drawableContainerState.m) {
                drawableContainerState.a();
            }
            return drawableContainerState.o;
        }
        Drawable drawable = this.g;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        DrawableContainerState drawableContainerState = this.e;
        if (drawableContainerState.l) {
            if (!drawableContainerState.m) {
                drawableContainerState.a();
            }
            return drawableContainerState.n;
        }
        Drawable drawable = this.g;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumHeight() {
        DrawableContainerState drawableContainerState = this.e;
        if (drawableContainerState.l) {
            if (!drawableContainerState.m) {
                drawableContainerState.a();
            }
            return drawableContainerState.q;
        }
        Drawable drawable = this.g;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumWidth() {
        DrawableContainerState drawableContainerState = this.e;
        if (drawableContainerState.l) {
            if (!drawableContainerState.m) {
                drawableContainerState.a();
            }
            return drawableContainerState.p;
        }
        Drawable drawable = this.g;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.g;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        DrawableContainerState drawableContainerState = this.e;
        if (drawableContainerState.r) {
            return drawableContainerState.s;
        }
        drawableContainerState.b();
        int i = drawableContainerState.h;
        Drawable[] drawableArr = drawableContainerState.g;
        int opacity = i > 0 ? drawableArr[0].getOpacity() : -2;
        for (int i2 = 1; i2 < i; i2++) {
            opacity = Drawable.resolveOpacity(opacity, drawableArr[i2].getOpacity());
        }
        drawableContainerState.s = opacity;
        drawableContainerState.r = true;
        return opacity;
    }

    @Override // android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        Drawable drawable = this.g;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        boolean padding;
        DrawableContainerState drawableContainerState = this.e;
        boolean z = false;
        Rect rect2 = null;
        if (!drawableContainerState.i) {
            Rect rect3 = drawableContainerState.k;
            if (rect3 != null || drawableContainerState.j) {
                rect2 = rect3;
            } else {
                drawableContainerState.b();
                Rect rect4 = new Rect();
                int i = drawableContainerState.h;
                Drawable[] drawableArr = drawableContainerState.g;
                for (int i2 = 0; i2 < i; i2++) {
                    if (drawableArr[i2].getPadding(rect4)) {
                        if (rect2 == null) {
                            rect2 = new Rect(0, 0, 0, 0);
                        }
                        int i3 = rect4.left;
                        if (i3 > rect2.left) {
                            rect2.left = i3;
                        }
                        int i4 = rect4.top;
                        if (i4 > rect2.top) {
                            rect2.top = i4;
                        }
                        int i5 = rect4.right;
                        if (i5 > rect2.right) {
                            rect2.right = i5;
                        }
                        int i6 = rect4.bottom;
                        if (i6 > rect2.bottom) {
                            rect2.bottom = i6;
                        }
                    }
                }
                drawableContainerState.j = true;
                drawableContainerState.k = rect2;
            }
        }
        if (rect2 != null) {
            rect.set(rect2);
            padding = (((rect2.left | rect2.top) | rect2.bottom) | rect2.right) != 0;
        } else {
            Drawable drawable = this.g;
            padding = drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
        }
        if (isAutoMirrored() && getLayoutDirection() == 1) {
            z = true;
        }
        if (z) {
            int i7 = rect.left;
            rect.left = rect.right;
            rect.right = i7;
        }
        return padding;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        DrawableContainerState drawableContainerState = this.e;
        if (drawableContainerState != null) {
            drawableContainerState.r = false;
            drawableContainerState.t = false;
        }
        if (drawable != this.g || getCallback() == null) {
            return;
        }
        getCallback().invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        return this.e.B;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        DrawableContainerState drawableContainerState = this.e;
        if (drawableContainerState.t) {
            return drawableContainerState.u;
        }
        drawableContainerState.b();
        int i = drawableContainerState.h;
        Drawable[] drawableArr = drawableContainerState.g;
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                break;
            }
            if (drawableArr[i2].isStateful()) {
                z = true;
                break;
            }
            i2++;
        }
        drawableContainerState.u = z;
        drawableContainerState.t = true;
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        boolean z;
        Drawable drawable = this.h;
        boolean z2 = true;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.h = null;
            z = true;
        } else {
            z = false;
        }
        Drawable drawable2 = this.g;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.j) {
                this.g.setAlpha(this.i);
            }
        }
        if (this.o != 0) {
            this.o = 0L;
            z = true;
        }
        if (this.n != 0) {
            this.n = 0L;
        } else {
            z2 = z;
        }
        if (z2) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.l && super.mutate() == this) {
            DrawableContainerState drawableContainerStateB = b();
            drawableContainerStateB.d();
            e(drawableContainerStateB);
            this.l = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.h;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.g;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLayoutDirectionChanged(int i) {
        DrawableContainerState drawableContainerState = this.e;
        int i2 = this.k;
        int i3 = drawableContainerState.h;
        Drawable[] drawableArr = drawableContainerState.g;
        boolean z = false;
        for (int i4 = 0; i4 < i3; i4++) {
            Drawable drawable = drawableArr[i4];
            if (drawable != null) {
                boolean layoutDirection = drawable.setLayoutDirection(i);
                if (i4 == i2) {
                    z = layoutDirection;
                }
            }
        }
        drawableContainerState.y = i;
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i) {
        Drawable drawable = this.h;
        if (drawable != null) {
            return drawable.setLevel(i);
        }
        Drawable drawable2 = this.g;
        if (drawable2 != null) {
            return drawable2.setLevel(i);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.h;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        Drawable drawable2 = this.g;
        if (drawable2 != null) {
            return drawable2.setState(iArr);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (drawable != this.g || getCallback() == null) {
            return;
        }
        getCallback().scheduleDrawable(this, runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (this.j && this.i == i) {
            return;
        }
        this.j = true;
        this.i = i;
        Drawable drawable = this.g;
        if (drawable != null) {
            if (this.n == 0) {
                drawable.setAlpha(i);
            } else {
                a(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z) {
        DrawableContainerState drawableContainerState = this.e;
        if (drawableContainerState.B != z) {
            drawableContainerState.B = z;
            Drawable drawable = this.g;
            if (drawable != null) {
                drawable.setAutoMirrored(z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        DrawableContainerState drawableContainerState = this.e;
        drawableContainerState.D = true;
        if (drawableContainerState.C != colorFilter) {
            drawableContainerState.C = colorFilter;
            Drawable drawable = this.g;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setDither(boolean z) {
        DrawableContainerState drawableContainerState = this.e;
        if (drawableContainerState.x != z) {
            drawableContainerState.x = z;
            Drawable drawable = this.g;
            if (drawable != null) {
                drawable.setDither(z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setHotspot(float f, float f2) {
        Drawable drawable = this.g;
        if (drawable != null) {
            drawable.setHotspot(f, f2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setHotspotBounds(int i, int i2, int i3, int i4) {
        Rect rect = this.f;
        if (rect == null) {
            this.f = new Rect(i, i2, i3, i4);
        } else {
            rect.set(i, i2, i3, i4);
        }
        Drawable drawable = this.g;
        if (drawable != null) {
            drawable.setHotspotBounds(i, i2, i3, i4);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        DrawableContainerState drawableContainerState = this.e;
        drawableContainerState.G = true;
        if (drawableContainerState.E != colorStateList) {
            drawableContainerState.E = colorStateList;
            sd0.b(this.g, colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        DrawableContainerState drawableContainerState = this.e;
        drawableContainerState.H = true;
        if (drawableContainerState.F != mode) {
            drawableContainerState.F = mode;
            sd0.c(this.g, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.h;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        Drawable drawable2 = this.g;
        if (drawable2 != null) {
            drawable2.setVisible(z, z2);
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable != this.g || getCallback() == null) {
            return;
        }
        getCallback().unscheduleDrawable(this, runnable);
    }

    public static class BlockInvalidateCallback implements Drawable.Callback {
        public Drawable.Callback e;

        @Override // android.graphics.drawable.Drawable.Callback
        public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            Drawable.Callback callback = this.e;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            Drawable.Callback callback = this.e;
            if (callback != null) {
                callback.unscheduleDrawable(drawable, runnable);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public final void invalidateDrawable(Drawable drawable) {
        }
    }
}
