package defpackage;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class H extends Drawable implements Drawable.Callback {
    public Runnable A;
    public long B;
    public long C;
    public F D;
    public G s;
    public Rect t;
    public Drawable u;
    public Drawable v;
    public boolean x;
    public boolean z;
    public int w = 255;
    public int y = -1;

    public static int c(Resources resources, int i) {
        if (resources != null) {
            i = resources.getDisplayMetrics().densityDpi;
        }
        if (i == 0) {
            return 160;
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(boolean r14) {
        /*
            r13 = this;
            r0 = 1
            r13.x = r0
            long r1 = android.os.SystemClock.uptimeMillis()
            android.graphics.drawable.Drawable r3 = r13.u
            r4 = 255(0xff, double:1.26E-321)
            r6 = 0
            r7 = 0
            if (r3 == 0) goto L38
            long r9 = r13.B
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 == 0) goto L3a
            int r11 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r11 > 0) goto L22
            int r9 = r13.w
            r3.setAlpha(r9)
            r13.B = r7
            goto L3a
        L22:
            long r9 = r9 - r1
            long r9 = r9 * r4
            int r10 = (int) r9
            G r9 = r13.s
            int r9 = r9.A
            int r10 = r10 / r9
            int r9 = 255 - r10
            int r10 = r13.w
            int r9 = r9 * r10
            int r9 = r9 / 255
            r3.setAlpha(r9)
            r3 = 1
            goto L3b
        L38:
            r13.B = r7
        L3a:
            r3 = 0
        L3b:
            android.graphics.drawable.Drawable r9 = r13.v
            if (r9 == 0) goto L65
            long r10 = r13.C
            int r12 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r12 == 0) goto L67
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 > 0) goto L52
            r9.setVisible(r6, r6)
            r0 = 0
            r13.v = r0
            r13.C = r7
            goto L67
        L52:
            long r10 = r10 - r1
            long r10 = r10 * r4
            int r3 = (int) r10
            G r4 = r13.s
            int r4 = r4.B
            int r3 = r3 / r4
            int r4 = r13.w
            int r3 = r3 * r4
            int r3 = r3 / 255
            r9.setAlpha(r3)
            goto L68
        L65:
            r13.C = r7
        L67:
            r0 = r3
        L68:
            if (r14 == 0) goto L74
            if (r0 == 0) goto L74
            java.lang.Runnable r14 = r13.A
            r3 = 16
            long r1 = r1 + r3
            r13.scheduleSelf(r14, r1)
        L74:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.H.a(boolean):void");
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        G g = this.s;
        Objects.requireNonNull(g);
        if (theme != null) {
            g.c();
            int i = g.h;
            Drawable[] drawableArr = g.g;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2] != null && drawableArr[i2].canApplyTheme()) {
                    drawableArr[i2].applyTheme(theme);
                    g.e |= drawableArr[i2].getChangingConfigurations();
                }
            }
            g.f(theme.getResources());
        }
    }

    public final void b(Drawable drawable) {
        if (this.D == null) {
            this.D = new F();
        }
        F f = this.D;
        f.s = drawable.getCallback();
        drawable.setCallback(f);
        try {
            if (this.s.A <= 0 && this.x) {
                drawable.setAlpha(this.w);
            }
            if (this.s.E) {
                drawable.setColorFilter(this.s.D);
            } else {
                if (this.s.H) {
                    drawable.setTintList(this.s.F);
                }
                if (this.s.I) {
                    drawable.setTintMode(this.s.G);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.s.x);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            drawable.setAutoMirrored(this.s.C);
            Rect rect = this.t;
            if (rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            F f2 = this.D;
            Drawable.Callback callback = f2.s;
            f2.s = null;
            drawable.setCallback(callback);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return this.s.canApplyTheme();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean d(int r10) {
        /*
            r9 = this;
            int r0 = r9.y
            r1 = 0
            if (r10 != r0) goto L6
            return r1
        L6:
            long r2 = android.os.SystemClock.uptimeMillis()
            G r0 = r9.s
            int r0 = r0.B
            r4 = 0
            r5 = 0
            if (r0 <= 0) goto L2e
            android.graphics.drawable.Drawable r0 = r9.v
            if (r0 == 0) goto L1a
            r0.setVisible(r1, r1)
        L1a:
            android.graphics.drawable.Drawable r0 = r9.u
            if (r0 == 0) goto L29
            r9.v = r0
            G r0 = r9.s
            int r0 = r0.B
            long r0 = (long) r0
            long r0 = r0 + r2
            r9.C = r0
            goto L35
        L29:
            r9.v = r4
            r9.C = r5
            goto L35
        L2e:
            android.graphics.drawable.Drawable r0 = r9.u
            if (r0 == 0) goto L35
            r0.setVisible(r1, r1)
        L35:
            if (r10 < 0) goto L55
            G r0 = r9.s
            int r1 = r0.h
            if (r10 >= r1) goto L55
            android.graphics.drawable.Drawable r0 = r0.d(r10)
            r9.u = r0
            r9.y = r10
            if (r0 == 0) goto L5a
            G r10 = r9.s
            int r10 = r10.A
            if (r10 <= 0) goto L51
            long r7 = (long) r10
            long r2 = r2 + r7
            r9.B = r2
        L51:
            r9.b(r0)
            goto L5a
        L55:
            r9.u = r4
            r10 = -1
            r9.y = r10
        L5a:
            long r0 = r9.B
            r10 = 1
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 != 0) goto L67
            long r0 = r9.C
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 == 0) goto L79
        L67:
            java.lang.Runnable r0 = r9.A
            if (r0 != 0) goto L73
            E r0 = new E
            r0.<init>(r9)
            r9.A = r0
            goto L76
        L73:
            r9.unscheduleSelf(r0)
        L76:
            r9.a(r10)
        L79:
            r9.invalidateSelf()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.H.d(int):boolean");
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.u;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.v;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    public void e(G g) {
        this.s = g;
        int i = this.y;
        if (i >= 0) {
            Drawable drawableD = g.d(i);
            this.u = drawableD;
            if (drawableD != null) {
                b(drawableD);
            }
        }
        this.v = null;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.w;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.s.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        boolean z;
        G g = this.s;
        synchronized (g) {
            z = false;
            if (!g.v) {
                g.c();
                g.v = true;
                int i = g.h;
                Drawable[] drawableArr = g.g;
                int i2 = 0;
                while (true) {
                    if (i2 >= i) {
                        g.w = true;
                        z = true;
                        break;
                    }
                    if (drawableArr[i2].getConstantState() == null) {
                        g.w = false;
                        break;
                    }
                    i2++;
                }
            } else {
                z = g.w;
            }
        }
        if (!z) {
            return null;
        }
        this.s.d = getChangingConfigurations();
        return this.s;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.u;
    }

    @Override // android.graphics.drawable.Drawable
    public void getHotspotBounds(Rect rect) {
        Rect rect2 = this.t;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        G g = this.s;
        if (g.l) {
            if (!g.m) {
                g.b();
            }
            return g.o;
        }
        Drawable drawable = this.u;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        G g = this.s;
        if (g.l) {
            if (!g.m) {
                g.b();
            }
            return g.n;
        }
        Drawable drawable = this.u;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        G g = this.s;
        if (g.l) {
            if (!g.m) {
                g.b();
            }
            return g.q;
        }
        Drawable drawable = this.u;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        G g = this.s;
        if (g.l) {
            if (!g.m) {
                g.b();
            }
            return g.p;
        }
        Drawable drawable = this.u;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.u;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        G g = this.s;
        if (g.r) {
            return g.s;
        }
        g.c();
        int i = g.h;
        Drawable[] drawableArr = g.g;
        int opacity = i > 0 ? drawableArr[0].getOpacity() : -2;
        for (int i2 = 1; i2 < i; i2++) {
            opacity = Drawable.resolveOpacity(opacity, drawableArr[i2].getOpacity());
        }
        g.s = opacity;
        g.r = true;
        return opacity;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Drawable drawable = this.u;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        boolean padding;
        G g = this.s;
        Rect rect2 = null;
        if (!g.i) {
            if (g.k != null || g.j) {
                rect2 = g.k;
            } else {
                g.c();
                Rect rect3 = new Rect();
                int i = g.h;
                Drawable[] drawableArr = g.g;
                for (int i2 = 0; i2 < i; i2++) {
                    if (drawableArr[i2].getPadding(rect3)) {
                        if (rect2 == null) {
                            rect2 = new Rect(0, 0, 0, 0);
                        }
                        int i3 = rect3.left;
                        if (i3 > rect2.left) {
                            rect2.left = i3;
                        }
                        int i4 = rect3.top;
                        if (i4 > rect2.top) {
                            rect2.top = i4;
                        }
                        int i5 = rect3.right;
                        if (i5 > rect2.right) {
                            rect2.right = i5;
                        }
                        int i6 = rect3.bottom;
                        if (i6 > rect2.bottom) {
                            rect2.bottom = i6;
                        }
                    }
                }
                g.j = true;
                g.k = rect2;
            }
        }
        if (rect2 != null) {
            rect.set(rect2);
            padding = (((rect2.left | rect2.top) | rect2.bottom) | rect2.right) != 0;
        } else {
            Drawable drawable = this.u;
            padding = drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
        }
        if (this.s.C && getLayoutDirection() == 1) {
            int i7 = rect.left;
            rect.left = rect.right;
            rect.right = i7;
        }
        return padding;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        G g = this.s;
        if (g != null) {
            g.r = false;
            g.t = false;
        }
        if (drawable != this.u || getCallback() == null) {
            return;
        }
        getCallback().invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.s.C;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        boolean z;
        Drawable drawable = this.v;
        boolean z2 = true;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.v = null;
            z = true;
        } else {
            z = false;
        }
        Drawable drawable2 = this.u;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.x) {
                this.u.setAlpha(this.w);
            }
        }
        if (this.C != 0) {
            this.C = 0L;
            z = true;
        }
        if (this.B != 0) {
            this.B = 0L;
        } else {
            z2 = z;
        }
        if (z2) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.z && super.mutate() == this) {
            D d = (D) this;
            C1796y c1796y = new C1796y(d.G, d, null);
            c1796y.e();
            e(c1796y);
            this.z = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.v;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.u;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        G g = this.s;
        int i2 = this.y;
        int i3 = g.h;
        Drawable[] drawableArr = g.g;
        boolean z = false;
        for (int i4 = 0; i4 < i3; i4++) {
            if (drawableArr[i4] != null) {
                boolean layoutDirection = Build.VERSION.SDK_INT >= 23 ? drawableArr[i4].setLayoutDirection(i) : false;
                if (i4 == i2) {
                    z = layoutDirection;
                }
            }
        }
        g.z = i;
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        Drawable drawable = this.v;
        if (drawable != null) {
            return drawable.setLevel(i);
        }
        Drawable drawable2 = this.u;
        if (drawable2 != null) {
            return drawable2.setLevel(i);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (drawable != this.u || getCallback() == null) {
            return;
        }
        getCallback().scheduleDrawable(this, runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.x && this.w == i) {
            return;
        }
        this.x = true;
        this.w = i;
        Drawable drawable = this.u;
        if (drawable != null) {
            if (this.B == 0) {
                drawable.setAlpha(i);
            } else {
                a(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        G g = this.s;
        if (g.C != z) {
            g.C = z;
            Drawable drawable = this.u;
            if (drawable != null) {
                drawable.setAutoMirrored(z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        G g = this.s;
        g.E = true;
        if (g.D != colorFilter) {
            g.D = colorFilter;
            Drawable drawable = this.u;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        G g = this.s;
        if (g.x != z) {
            g.x = z;
            Drawable drawable = this.u;
            if (drawable != null) {
                drawable.setDither(z);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f2) {
        Drawable drawable = this.u;
        if (drawable != null) {
            drawable.setHotspot(f, f2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Rect rect = this.t;
        if (rect == null) {
            this.t = new Rect(i, i2, i3, i4);
        } else {
            rect.set(i, i2, i3, i4);
        }
        Drawable drawable = this.u;
        if (drawable != null) {
            drawable.setHotspotBounds(i, i2, i3, i4);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        G g = this.s;
        g.H = true;
        if (g.F != colorStateList) {
            g.F = colorStateList;
            this.u.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        G g = this.s;
        g.I = true;
        if (g.G != mode) {
            g.G = mode;
            this.u.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.v;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        Drawable drawable2 = this.u;
        if (drawable2 != null) {
            drawable2.setVisible(z, z2);
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable != this.u || getCallback() == null) {
            return;
        }
        getCallback().unscheduleDrawable(this, runnable);
    }
}
