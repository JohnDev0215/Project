package p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.TypedValue;
import com.spotify.music.R;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class h42 {
    public static h42 g;
    public WeakHashMap a;
    public final WeakHashMap b = new WeakHashMap(0);
    public TypedValue c;
    public boolean d;
    public gu0 e;
    public static final PorterDuff.Mode f = PorterDuff.Mode.SRC_IN;
    public static final g42 h = new g42(6);

    public static synchronized h42 c() {
        try {
            if (g == null) {
                g = new h42();
            }
        } catch (Throwable th) {
            throw th;
        }
        return g;
    }

    public static synchronized PorterDuffColorFilter g(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        g42 g42Var = h;
        g42Var.getClass();
        int i2 = (31 + i) * 31;
        porterDuffColorFilter = (PorterDuffColorFilter) g42Var.b(Integer.valueOf(mode.hashCode() + i2));
        if (porterDuffColorFilter == null) {
            porterDuffColorFilter = new PorterDuffColorFilter(i, mode);
        }
        return porterDuffColorFilter;
    }

    public final synchronized void a(Context context, long j, Drawable drawable) {
        try {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                wd1 wd1Var = (wd1) this.b.get(context);
                if (wd1Var == null) {
                    wd1Var = new wd1();
                    this.b.put(context, wd1Var);
                }
                wd1Var.g(j, new WeakReference(constantState));
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final Drawable b(Context context, int i) {
        if (this.c == null) {
            this.c = new TypedValue();
        }
        TypedValue typedValue = this.c;
        context.getResources().getValue(i, typedValue, true);
        long j = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
        Drawable drawableD = d(context, j);
        if (drawableD != null) {
            return drawableD;
        }
        LayerDrawable layerDrawableK = null;
        if (this.e != null) {
            if (i == R.drawable.abc_cab_background_top_material) {
                layerDrawableK = new LayerDrawable(new Drawable[]{e(context, R.drawable.abc_cab_background_internal_bg), e(context, R.drawable.abc_cab_background_top_mtrl_alpha)});
            } else if (i == R.drawable.abc_ratingbar_material) {
                layerDrawableK = gu0.k(this, context, R.dimen.abc_star_big);
            } else if (i == R.drawable.abc_ratingbar_indicator_material) {
                layerDrawableK = gu0.k(this, context, R.dimen.abc_star_medium);
            } else if (i == R.drawable.abc_ratingbar_small_material) {
                layerDrawableK = gu0.k(this, context, R.dimen.abc_star_small);
            }
        }
        if (layerDrawableK != null) {
            layerDrawableK.setChangingConfigurations(typedValue.changingConfigurations);
            a(context, j, layerDrawableK);
        }
        return layerDrawableK;
    }

    public final synchronized Drawable d(Context context, long j) {
        wd1 wd1Var = (wd1) this.b.get(context);
        if (wd1Var == null) {
            return null;
        }
        WeakReference weakReference = (WeakReference) wd1Var.e(j);
        if (weakReference != null) {
            Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            int iJ = zu.j(wd1Var.e, wd1Var.l, j);
            if (iJ >= 0) {
                Object[] objArr = wd1Var.k;
                Object obj = objArr[iJ];
                Object obj2 = xd1.a;
                if (obj != obj2) {
                    objArr[iJ] = obj2;
                    wd1Var.c = true;
                }
            }
        }
        return null;
    }

    public final synchronized Drawable e(Context context, int i) {
        return f(context, i, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00cb A[Catch: all -> 0x00da, TryCatch #0 {, blocks: (B:3:0x0001, B:12:0x0026, B:14:0x002c, B:16:0x0034, B:18:0x003b, B:25:0x0052, B:23:0x004e, B:26:0x0057, B:30:0x006e, B:37:0x00a4, B:38:0x00cb, B:43:0x00d6, B:6:0x0006, B:8:0x0012, B:10:0x0016, B:50:0x00df, B:51:0x00e8), top: B:54:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized android.graphics.drawable.Drawable f(android.content.Context r9, int r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.h42.f(android.content.Context, int, boolean):android.graphics.drawable.Drawable");
    }

    public final synchronized ColorStateList h(Context context, int i) {
        ColorStateList colorStateList;
        pe2 pe2Var;
        WeakHashMap weakHashMap = this.a;
        ColorStateList colorStateListM = null;
        colorStateList = (weakHashMap == null || (pe2Var = (pe2) weakHashMap.get(context)) == null) ? null : (ColorStateList) pe2Var.e(i);
        if (colorStateList == null) {
            gu0 gu0Var = this.e;
            if (gu0Var != null) {
                colorStateListM = gu0Var.m(context, i);
            }
            if (colorStateListM != null) {
                if (this.a == null) {
                    this.a = new WeakHashMap();
                }
                pe2 pe2Var2 = (pe2) this.a.get(context);
                if (pe2Var2 == null) {
                    pe2Var2 = new pe2();
                    this.a.put(context, pe2Var2);
                }
                pe2Var2.a(i, colorStateListM);
            }
            colorStateList = colorStateListM;
        }
        return colorStateList;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean i(android.content.Context r8, int r9, android.graphics.drawable.Drawable r10) {
        /*
            r7 = this;
            p.gu0 r0 = r7.e
            r1 = 0
            if (r0 == 0) goto L76
            android.graphics.PorterDuff$Mode r2 = p.u8.b
            java.lang.Object r3 = r0.a
            int[] r3 = (int[]) r3
            boolean r3 = p.gu0.b(r3, r9)
            r4 = 1
            r5 = -1
            if (r3 == 0) goto L19
            r9 = 2130968873(0x7f040129, float:1.7546412E38)
        L16:
            r3 = r4
        L17:
            r0 = r5
            goto L55
        L19:
            java.lang.Object r3 = r0.c
            int[] r3 = (int[]) r3
            boolean r3 = p.gu0.b(r3, r9)
            if (r3 == 0) goto L27
            r9 = 2130968871(0x7f040127, float:1.7546408E38)
            goto L16
        L27:
            java.lang.Object r0 = r0.d
            int[] r0 = (int[]) r0
            boolean r0 = p.gu0.b(r0, r9)
            r3 = 16842801(0x1010031, float:2.3693695E-38)
            if (r0 == 0) goto L38
            android.graphics.PorterDuff$Mode r2 = android.graphics.PorterDuff.Mode.MULTIPLY
        L36:
            r9 = r3
            goto L16
        L38:
            r0 = 2131230763(0x7f08002b, float:1.8077588E38)
            if (r9 != r0) goto L4c
            r9 = 1109603123(0x42233333, float:40.8)
            int r9 = java.lang.Math.round(r9)
            r0 = 16842800(0x1010030, float:2.3693693E-38)
            r3 = r4
            r6 = r0
            r0 = r9
            r9 = r6
            goto L55
        L4c:
            r0 = 2131230745(0x7f080019, float:1.8077551E38)
            if (r9 != r0) goto L52
            goto L36
        L52:
            r9 = r1
            r3 = r9
            goto L17
        L55:
            if (r3 == 0) goto L76
            int[] r1 = p.cb0.a
            android.graphics.drawable.Drawable r10 = r10.mutate()
            int r8 = p.ik2.c(r8, r9)
            java.lang.Class<p.u8> r9 = p.u8.class
            monitor-enter(r9)
            android.graphics.PorterDuffColorFilter r8 = g(r8, r2)     // Catch: java.lang.Throwable -> L73
            monitor-exit(r9)
            r10.setColorFilter(r8)
            if (r0 == r5) goto L71
            r10.setAlpha(r0)
        L71:
            r1 = r4
            goto L76
        L73:
            r8 = move-exception
            monitor-exit(r9)
            throw r8
        L76:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p.h42.i(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
    }
}
