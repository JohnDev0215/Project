package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.TypedValue;
import defpackage.nq2;
import defpackage.sa1;
import defpackage.ua1;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ResourceManagerInternal {
    public static ResourceManagerInternal g;
    public WeakHashMap<Context, nq2<ColorStateList>> a;
    public final WeakHashMap<Context, sa1<WeakReference<Drawable.ConstantState>>> b = new WeakHashMap<>(0);
    public TypedValue c;
    public boolean d;
    public ResourceManagerHooks e;
    public static final PorterDuff.Mode f = PorterDuff.Mode.SRC_IN;
    public static final ColorFilterLruCache h = new ColorFilterLruCache();

    public static class AsldcInflateDelegate implements InflateDelegate {
    }

    public static class AvdcInflateDelegate implements InflateDelegate {
    }

    public static class ColorFilterLruCache extends ua1<Integer, PorterDuffColorFilter> {
        public ColorFilterLruCache() {
            super(6);
        }
    }

    public static class DrawableDelegate implements InflateDelegate {
    }

    public interface InflateDelegate {
    }

    public interface ResourceManagerHooks {
        boolean a(Context context, int i, Drawable drawable);

        PorterDuff.Mode b(int i);

        LayerDrawable c(ResourceManagerInternal resourceManagerInternal, Context context, int i);

        ColorStateList d(Context context, int i);

        boolean e(Context context, int i, Drawable drawable);
    }

    public static class VdcInflateDelegate implements InflateDelegate {
    }

    public static synchronized ResourceManagerInternal b() {
        if (g == null) {
            g = new ResourceManagerInternal();
        }
        return g;
    }

    public static synchronized PorterDuffColorFilter f(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterB;
        ColorFilterLruCache colorFilterLruCache = h;
        colorFilterLruCache.getClass();
        int i2 = (i + 31) * 31;
        porterDuffColorFilterB = colorFilterLruCache.b(Integer.valueOf(mode.hashCode() + i2));
        if (porterDuffColorFilterB == null) {
            porterDuffColorFilterB = new PorterDuffColorFilter(i, mode);
            colorFilterLruCache.getClass();
            colorFilterLruCache.c(Integer.valueOf(mode.hashCode() + i2), porterDuffColorFilterB);
        }
        return porterDuffColorFilterB;
    }

    public final synchronized void a(Context context, long j, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            sa1<WeakReference<Drawable.ConstantState>> sa1Var = this.b.get(context);
            if (sa1Var == null) {
                sa1Var = new sa1<>();
                this.b.put(context, sa1Var);
            }
            sa1Var.k(j, new WeakReference<>(constantState));
        }
    }

    public final synchronized Drawable c(Context context, long j) {
        sa1<WeakReference<Drawable.ConstantState>> sa1Var = this.b.get(context);
        if (sa1Var == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> weakReferenceG = sa1Var.g(j);
        if (weakReferenceG != null) {
            Drawable.ConstantState constantState = weakReferenceG.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            sa1Var.l(j);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002a, code lost:
    
        if (((r0 instanceof defpackage.el3) || "android.graphics.drawable.VectorDrawable".equals(r0.getClass().getName())) != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized android.graphics.drawable.Drawable d(int r6, android.content.Context r7, boolean r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.d     // Catch: java.lang.Throwable -> L7a
            r1 = 1
            if (r0 == 0) goto L7
            goto L2c
        L7:
            r5.d = r1     // Catch: java.lang.Throwable -> L7a
            int r0 = androidx.appcompat.resources.R.drawable.abc_vector_test     // Catch: java.lang.Throwable -> L7a
            android.graphics.drawable.Drawable r0 = r5.e(r7, r0)     // Catch: java.lang.Throwable -> L7a
            r2 = 0
            if (r0 == 0) goto L7c
            boolean r3 = r0 instanceof defpackage.el3     // Catch: java.lang.Throwable -> L7a
            if (r3 != 0) goto L29
            java.lang.Class r0 = r0.getClass()     // Catch: java.lang.Throwable -> L7a
            java.lang.String r0 = r0.getName()     // Catch: java.lang.Throwable -> L7a
            java.lang.String r3 = "android.graphics.drawable.VectorDrawable"
            boolean r0 = r3.equals(r0)     // Catch: java.lang.Throwable -> L7a
            if (r0 == 0) goto L27
            goto L29
        L27:
            r0 = r2
            goto L2a
        L29:
            r0 = r1
        L2a:
            if (r0 == 0) goto L7c
        L2c:
            android.util.TypedValue r0 = r5.c     // Catch: java.lang.Throwable -> L7a
            if (r0 != 0) goto L37
            android.util.TypedValue r0 = new android.util.TypedValue     // Catch: java.lang.Throwable -> L7a
            r0.<init>()     // Catch: java.lang.Throwable -> L7a
            r5.c = r0     // Catch: java.lang.Throwable -> L7a
        L37:
            android.util.TypedValue r0 = r5.c     // Catch: java.lang.Throwable -> L7a
            android.content.res.Resources r2 = r7.getResources()     // Catch: java.lang.Throwable -> L7a
            r2.getValue(r6, r0, r1)     // Catch: java.lang.Throwable -> L7a
            int r1 = r0.assetCookie     // Catch: java.lang.Throwable -> L7a
            long r1 = (long) r1     // Catch: java.lang.Throwable -> L7a
            r3 = 32
            long r1 = r1 << r3
            int r3 = r0.data     // Catch: java.lang.Throwable -> L7a
            long r3 = (long) r3     // Catch: java.lang.Throwable -> L7a
            long r1 = r1 | r3
            android.graphics.drawable.Drawable r3 = r5.c(r7, r1)     // Catch: java.lang.Throwable -> L7a
            if (r3 == 0) goto L51
            goto L65
        L51:
            androidx.appcompat.widget.ResourceManagerInternal$ResourceManagerHooks r3 = r5.e     // Catch: java.lang.Throwable -> L7a
            if (r3 != 0) goto L57
            r3 = 0
            goto L5b
        L57:
            android.graphics.drawable.LayerDrawable r3 = r3.c(r5, r7, r6)     // Catch: java.lang.Throwable -> L7a
        L5b:
            if (r3 == 0) goto L65
            int r0 = r0.changingConfigurations     // Catch: java.lang.Throwable -> L7a
            r3.setChangingConfigurations(r0)     // Catch: java.lang.Throwable -> L7a
            r5.a(r7, r1, r3)     // Catch: java.lang.Throwable -> L7a
        L65:
            if (r3 != 0) goto L6d
            java.lang.Object r0 = defpackage.bz.a     // Catch: java.lang.Throwable -> L7a
            android.graphics.drawable.Drawable r3 = r7.getDrawable(r6)     // Catch: java.lang.Throwable -> L7a
        L6d:
            if (r3 == 0) goto L73
            android.graphics.drawable.Drawable r3 = r5.h(r7, r6, r8, r3)     // Catch: java.lang.Throwable -> L7a
        L73:
            if (r3 == 0) goto L78
            androidx.appcompat.widget.DrawableUtils.a(r3)     // Catch: java.lang.Throwable -> L7a
        L78:
            monitor-exit(r5)
            return r3
        L7a:
            r6 = move-exception
            goto L86
        L7c:
            r5.d = r2     // Catch: java.lang.Throwable -> L7a
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L7a
            java.lang.String r7 = "This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat."
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L7a
            throw r6     // Catch: java.lang.Throwable -> L7a
        L86:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ResourceManagerInternal.d(int, android.content.Context, boolean):android.graphics.drawable.Drawable");
    }

    public final synchronized Drawable e(Context context, int i) {
        return d(i, context, false);
    }

    public final synchronized ColorStateList g(Context context, int i) {
        ColorStateList colorStateListG;
        nq2<ColorStateList> nq2Var;
        WeakHashMap<Context, nq2<ColorStateList>> weakHashMap = this.a;
        ColorStateList colorStateListD = null;
        colorStateListG = (weakHashMap == null || (nq2Var = weakHashMap.get(context)) == null) ? null : nq2Var.g(i);
        if (colorStateListG == null) {
            ResourceManagerHooks resourceManagerHooks = this.e;
            if (resourceManagerHooks != null) {
                colorStateListD = resourceManagerHooks.d(context, i);
            }
            if (colorStateListD != null) {
                if (this.a == null) {
                    this.a = new WeakHashMap<>();
                }
                nq2<ColorStateList> nq2Var2 = this.a.get(context);
                if (nq2Var2 == null) {
                    nq2Var2 = new nq2<>();
                    this.a.put(context, nq2Var2);
                }
                nq2Var2.b(i, colorStateListD);
            }
            colorStateListG = colorStateListD;
        }
        return colorStateListG;
    }

    public final Drawable h(Context context, int i, boolean z, Drawable drawable) {
        ColorStateList colorStateListG = g(context, i);
        if (colorStateListG != null) {
            int[] iArr = DrawableUtils.a;
            Drawable drawableMutate = drawable.mutate();
            drawableMutate.setTintList(colorStateListG);
            ResourceManagerHooks resourceManagerHooks = this.e;
            PorterDuff.Mode modeB = resourceManagerHooks != null ? resourceManagerHooks.b(i) : null;
            if (modeB == null) {
                return drawableMutate;
            }
            drawableMutate.setTintMode(modeB);
            return drawableMutate;
        }
        ResourceManagerHooks resourceManagerHooks2 = this.e;
        if (resourceManagerHooks2 != null && resourceManagerHooks2.e(context, i, drawable)) {
            return drawable;
        }
        ResourceManagerHooks resourceManagerHooks3 = this.e;
        if ((resourceManagerHooks3 != null && resourceManagerHooks3.a(context, i, drawable)) || !z) {
            return drawable;
        }
        return null;
    }
}
