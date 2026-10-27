package defpackage;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: renamed from: d0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0636d0 {
    public static C0636d0 h;
    public WeakHashMap a;
    public C0971j1 b;
    public C1688w1 c;
    public final WeakHashMap d = new WeakHashMap(0);
    public TypedValue e;
    public boolean f;
    public static final PorterDuff.Mode g = PorterDuff.Mode.SRC_IN;
    public static final C0468a0 i = new C0468a0(6);
    public static final int[] j = {AbstractC0317Rm.D0, AbstractC0317Rm.B0, AbstractC0317Rm.a};
    public static final int[] k = {AbstractC0317Rm.w, AbstractC0317Rm.l0, AbstractC0317Rm.D, AbstractC0317Rm.y, AbstractC0317Rm.z, AbstractC0317Rm.C, AbstractC0317Rm.B};
    public static final int[] l = {AbstractC0317Rm.A0, AbstractC0317Rm.C0, AbstractC0317Rm.p, AbstractC0317Rm.t0, AbstractC0317Rm.u0, AbstractC0317Rm.w0, AbstractC0317Rm.y0, AbstractC0317Rm.v0, AbstractC0317Rm.x0, AbstractC0317Rm.z0};
    public static final int[] m = {AbstractC0317Rm.b0, AbstractC0317Rm.n, AbstractC0317Rm.a0};
    public static final int[] n = {AbstractC0317Rm.r0, AbstractC0317Rm.E0};
    public static final int[] o = {AbstractC0317Rm.d, AbstractC0317Rm.i};

    public static boolean c(int[] iArr, int i2) {
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    public static synchronized C0636d0 g() {
        if (h == null) {
            C0636d0 c0636d0 = new C0636d0();
            h = c0636d0;
            if (Build.VERSION.SDK_INT < 24) {
                c0636d0.a("vector", new C0580c0());
                c0636d0.a("animated-vector", new Z());
                c0636d0.a("animated-selector", new Y());
            }
        }
        return h;
    }

    public static synchronized PorterDuffColorFilter k(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        C0468a0 c0468a0 = i;
        Objects.requireNonNull(c0468a0);
        int i3 = (i2 + 31) * 31;
        porterDuffColorFilter = (PorterDuffColorFilter) c0468a0.a(Integer.valueOf(mode.hashCode() + i3));
        if (porterDuffColorFilter == null) {
            porterDuffColorFilter = new PorterDuffColorFilter(i2, mode);
            C0468a0 c0468a02 = i;
            Objects.requireNonNull(c0468a02);
        }
        return porterDuffColorFilter;
    }

    public static void n(Drawable drawable, int i2, PorterDuff.Mode mode) {
        if (AbstractC1248o0.a(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = g;
        }
        drawable.setColorFilter(k(i2, mode));
    }

    public static void p(Drawable drawable, J0 j0, int[] iArr) {
        if (!AbstractC1248o0.a(drawable) || drawable.mutate() == drawable) {
            if (j0.d || j0.c) {
                PorterDuffColorFilter porterDuffColorFilterK = null;
                ColorStateList colorStateList = j0.d ? j0.a : null;
                PorterDuff.Mode mode = j0.c ? j0.b : g;
                if (colorStateList != null && mode != null) {
                    porterDuffColorFilterK = k(colorStateList.getColorForState(iArr, 0), mode);
                }
                drawable.setColorFilter(porterDuffColorFilterK);
            } else {
                drawable.clearColorFilter();
            }
            if (Build.VERSION.SDK_INT <= 23) {
                drawable.invalidateSelf();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005f A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean q(android.content.Context r6, int r7, android.graphics.drawable.Drawable r8) {
        /*
            android.graphics.PorterDuff$Mode r0 = defpackage.C0636d0.g
            int[] r1 = defpackage.C0636d0.j
            boolean r1 = c(r1, r7)
            r2 = 16842801(0x1010031, float:2.3693695E-38)
            r3 = -1
            r4 = 0
            r5 = 1
            if (r1 == 0) goto L15
            int r2 = defpackage.AbstractC0245Nm.P1
        L12:
            r7 = -1
        L13:
            r1 = 1
            goto L42
        L15:
            int[] r1 = defpackage.C0636d0.l
            boolean r1 = c(r1, r7)
            if (r1 == 0) goto L20
            int r2 = defpackage.AbstractC0245Nm.N1
            goto L12
        L20:
            int[] r1 = defpackage.C0636d0.m
            boolean r1 = c(r1, r7)
            if (r1 == 0) goto L2b
            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
            goto L12
        L2b:
            int r1 = defpackage.AbstractC0317Rm.P
            if (r7 != r1) goto L3a
            r2 = 16842800(0x1010030, float:2.3693693E-38)
            r7 = 1109603123(0x42233333, float:40.8)
            int r7 = java.lang.Math.round(r7)
            goto L13
        L3a:
            int r1 = defpackage.AbstractC0317Rm.r
            if (r7 != r1) goto L3f
            goto L12
        L3f:
            r7 = -1
            r1 = 0
            r2 = 0
        L42:
            if (r1 == 0) goto L5f
            boolean r1 = defpackage.AbstractC1248o0.a(r8)
            if (r1 == 0) goto L4e
            android.graphics.drawable.Drawable r8 = r8.mutate()
        L4e:
            int r6 = defpackage.H0.b(r6, r2)
            android.graphics.PorterDuffColorFilter r6 = k(r6, r0)
            r8.setColorFilter(r6)
            if (r7 == r3) goto L5e
            r8.setAlpha(r7)
        L5e:
            return r5
        L5f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0636d0.q(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
    }

    public final void a(String str, InterfaceC0524b0 interfaceC0524b0) {
        if (this.b == null) {
            this.b = new C0971j1();
        }
        this.b.put(str, interfaceC0524b0);
    }

    public final synchronized boolean b(Context context, long j2, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        C1194n1 c1194n1 = (C1194n1) this.d.get(context);
        if (c1194n1 == null) {
            c1194n1 = new C1194n1();
            this.d.put(context, c1194n1);
        }
        c1194n1.g(j2, new WeakReference(constantState));
        return true;
    }

    public final ColorStateList d(Context context, int i2) {
        int iB = H0.b(context, AbstractC0245Nm.O1);
        return new ColorStateList(new int[][]{H0.b, H0.d, H0.c, H0.f}, new int[]{H0.a(context, AbstractC0245Nm.M1), AbstractC1250o2.a(iB, i2), AbstractC1250o2.a(iB, i2), i2});
    }

    public final Drawable e(Context context, int i2) {
        if (this.e == null) {
            this.e = new TypedValue();
        }
        TypedValue typedValue = this.e;
        context.getResources().getValue(i2, typedValue, true);
        long j2 = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
        Drawable drawableH = h(context, j2);
        if (drawableH != null) {
            return drawableH;
        }
        if (i2 == AbstractC0317Rm.o) {
            drawableH = new LayerDrawable(new Drawable[]{i(context, AbstractC0317Rm.n), i(context, AbstractC0317Rm.p)});
        }
        if (drawableH != null) {
            drawableH.setChangingConfigurations(typedValue.changingConfigurations);
            b(context, j2, drawableH);
        }
        return drawableH;
    }

    public final ColorStateList f(Context context) {
        int[][] iArr = new int[3][];
        int[] iArr2 = new int[3];
        ColorStateList colorStateListC = H0.c(context, AbstractC0245Nm.X1);
        if (colorStateListC == null || !colorStateListC.isStateful()) {
            iArr[0] = H0.b;
            iArr2[0] = H0.a(context, AbstractC0245Nm.X1);
            iArr[1] = H0.e;
            iArr2[1] = H0.b(context, AbstractC0245Nm.N1);
            iArr[2] = H0.f;
            iArr2[2] = H0.b(context, AbstractC0245Nm.X1);
        } else {
            iArr[0] = H0.b;
            iArr2[0] = colorStateListC.getColorForState(iArr[0], 0);
            iArr[1] = H0.e;
            iArr2[1] = H0.b(context, AbstractC0245Nm.N1);
            iArr[2] = H0.f;
            iArr2[2] = colorStateListC.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }

    public final synchronized Drawable h(Context context, long j2) {
        C1194n1 c1194n1 = (C1194n1) this.d.get(context);
        if (c1194n1 == null) {
            return null;
        }
        WeakReference weakReference = (WeakReference) c1194n1.f(j2, null);
        if (weakReference != null) {
            Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            int iB = AbstractC1139m1.b(c1194n1.t, c1194n1.v, j2);
            if (iB >= 0) {
                Object[] objArr = c1194n1.u;
                Object obj = objArr[iB];
                Object obj2 = C1194n1.w;
                Object obj3 = C1194n1.w;
                if (obj != obj3) {
                    objArr[iB] = obj3;
                    c1194n1.s = true;
                }
            }
        }
        return null;
    }

    public synchronized Drawable i(Context context, int i2) {
        return j(context, i2, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0028, code lost:
    
        if (r0 != false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized android.graphics.drawable.Drawable j(android.content.Context r5, int r6, boolean r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.f     // Catch: java.lang.Throwable -> L51
            if (r0 == 0) goto L6
            goto L2a
        L6:
            r0 = 1
            r4.f = r0     // Catch: java.lang.Throwable -> L51
            int r1 = defpackage.AbstractC0317Rm.F0     // Catch: java.lang.Throwable -> L51
            android.graphics.drawable.Drawable r1 = r4.i(r5, r1)     // Catch: java.lang.Throwable -> L51
            r2 = 0
            if (r1 == 0) goto L47
            boolean r3 = r1 instanceof defpackage.U7     // Catch: java.lang.Throwable -> L51
            if (r3 != 0) goto L28
            java.lang.String r3 = "android.graphics.drawable.VectorDrawable"
            java.lang.Class r1 = r1.getClass()     // Catch: java.lang.Throwable -> L51
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L51
            boolean r1 = r3.equals(r1)     // Catch: java.lang.Throwable -> L51
            if (r1 == 0) goto L27
            goto L28
        L27:
            r0 = 0
        L28:
            if (r0 == 0) goto L47
        L2a:
            android.graphics.drawable.Drawable r0 = r4.m(r5, r6)     // Catch: java.lang.Throwable -> L51
            if (r0 != 0) goto L34
            android.graphics.drawable.Drawable r0 = r4.e(r5, r6)     // Catch: java.lang.Throwable -> L51
        L34:
            if (r0 != 0) goto L3a
            android.graphics.drawable.Drawable r0 = defpackage.Y1.b(r5, r6)     // Catch: java.lang.Throwable -> L51
        L3a:
            if (r0 == 0) goto L40
            android.graphics.drawable.Drawable r0 = r4.o(r5, r6, r7, r0)     // Catch: java.lang.Throwable -> L51
        L40:
            if (r0 == 0) goto L45
            defpackage.AbstractC1248o0.b(r0)     // Catch: java.lang.Throwable -> L51
        L45:
            monitor-exit(r4)
            return r0
        L47:
            r4.f = r2     // Catch: java.lang.Throwable -> L51
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L51
            java.lang.String r6 = "This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat."
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L51
            throw r5     // Catch: java.lang.Throwable -> L51
        L51:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0636d0.j(android.content.Context, int, boolean):android.graphics.drawable.Drawable");
    }

    public synchronized ColorStateList l(Context context, int i2) {
        ColorStateList colorStateListA;
        C1688w1 c1688w1;
        WeakHashMap weakHashMap = this.a;
        colorStateListA = null;
        if (weakHashMap != null && (c1688w1 = (C1688w1) weakHashMap.get(context)) != null) {
            colorStateListA = (ColorStateList) c1688w1.e(i2, null);
        }
        if (colorStateListA == null) {
            if (i2 == AbstractC0317Rm.s) {
                colorStateListA = AbstractC1631v.a(context, AbstractC0281Pm.u);
            } else if (i2 == AbstractC0317Rm.q0) {
                colorStateListA = AbstractC1631v.a(context, AbstractC0281Pm.x);
            } else if (i2 == AbstractC0317Rm.p0) {
                colorStateListA = f(context);
            } else if (i2 == AbstractC0317Rm.h) {
                colorStateListA = d(context, H0.b(context, AbstractC0245Nm.M1));
            } else if (i2 == AbstractC0317Rm.c) {
                colorStateListA = d(context, 0);
            } else if (i2 == AbstractC0317Rm.g) {
                colorStateListA = d(context, H0.b(context, AbstractC0245Nm.J1));
            } else if (i2 == AbstractC0317Rm.n0 || i2 == AbstractC0317Rm.o0) {
                colorStateListA = AbstractC1631v.a(context, AbstractC0281Pm.w);
            } else if (c(k, i2)) {
                colorStateListA = H0.c(context, AbstractC0245Nm.P1);
            } else if (c(n, i2)) {
                colorStateListA = AbstractC1631v.a(context, AbstractC0281Pm.t);
            } else if (c(o, i2)) {
                colorStateListA = AbstractC1631v.a(context, AbstractC0281Pm.s);
            } else if (i2 == AbstractC0317Rm.k0) {
                colorStateListA = AbstractC1631v.a(context, AbstractC0281Pm.v);
            }
            if (colorStateListA != null) {
                if (this.a == null) {
                    this.a = new WeakHashMap();
                }
                C1688w1 c1688w12 = (C1688w1) this.a.get(context);
                if (c1688w12 == null) {
                    c1688w12 = new C1688w1(10);
                    this.a.put(context, c1688w12);
                }
                c1688w12.a(i2, colorStateListA);
            }
        }
        return colorStateListA;
    }

    public final Drawable m(Context context, int i2) {
        int next;
        C0971j1 c0971j1 = this.b;
        if (c0971j1 == null || c0971j1.isEmpty()) {
            return null;
        }
        C1688w1 c1688w1 = this.c;
        if (c1688w1 != null) {
            String str = (String) c1688w1.e(i2, null);
            if ("appcompat_skip_skip".equals(str) || (str != null && this.b.get(str) == null)) {
                return null;
            }
        } else {
            this.c = new C1688w1(10);
        }
        if (this.e == null) {
            this.e = new TypedValue();
        }
        TypedValue typedValue = this.e;
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        long j2 = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
        Drawable drawableH = h(context, j2);
        if (drawableH != null) {
            return drawableH;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i2);
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String name = xml.getName();
                this.c.a(i2, name);
                InterfaceC0524b0 interfaceC0524b0 = (InterfaceC0524b0) this.b.get(name);
                if (interfaceC0524b0 != null) {
                    drawableH = interfaceC0524b0.a(context, xml, attributeSetAsAttributeSet, context.getTheme());
                }
                if (drawableH != null) {
                    drawableH.setChangingConfigurations(typedValue.changingConfigurations);
                    b(context, j2, drawableH);
                }
            } catch (Exception e) {
                Log.e("AppCompatDrawableManag", "Exception while inflating drawable", e);
            }
        }
        if (drawableH == null) {
            this.c.a(i2, "appcompat_skip_skip");
        }
        return drawableH;
    }

    public final Drawable o(Context context, int i2, boolean z, Drawable drawable) {
        ColorStateList colorStateListL = l(context, i2);
        if (colorStateListL != null) {
            if (AbstractC1248o0.a(drawable)) {
                drawable = drawable.mutate();
            }
            Drawable drawableD = B2.d(drawable);
            drawableD.setTintList(colorStateListL);
            PorterDuff.Mode mode = i2 == AbstractC0317Rm.p0 ? PorterDuff.Mode.MULTIPLY : null;
            if (mode == null) {
                return drawableD;
            }
            drawableD.setTintMode(mode);
            return drawableD;
        }
        if (i2 == AbstractC0317Rm.m0) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            n(layerDrawable.findDrawableByLayerId(R.id.background), H0.b(context, AbstractC0245Nm.P1), g);
            n(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), H0.b(context, AbstractC0245Nm.P1), g);
            n(layerDrawable.findDrawableByLayerId(R.id.progress), H0.b(context, AbstractC0245Nm.N1), g);
            return drawable;
        }
        if (i2 != AbstractC0317Rm.d0 && i2 != AbstractC0317Rm.c0 && i2 != AbstractC0317Rm.e0) {
            if (q(context, i2, drawable) || !z) {
                return drawable;
            }
            return null;
        }
        LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
        n(layerDrawable2.findDrawableByLayerId(R.id.background), H0.a(context, AbstractC0245Nm.P1), g);
        n(layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress), H0.b(context, AbstractC0245Nm.N1), g);
        n(layerDrawable2.findDrawableByLayerId(R.id.progress), H0.b(context, AbstractC0245Nm.N1), g);
        return drawable;
    }
}
