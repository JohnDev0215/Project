package r;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.TypedValue;
import com.stingray.drivemodedetector.R;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import u.C2459G;
import u.C2472l;
import w3.AbstractC2598a;

/* JADX INFO: loaded from: classes.dex */
public final class l0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static l0 f21525g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakHashMap f21527a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final WeakHashMap f21528b = new WeakHashMap(0);
    public TypedValue c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public H0.b f21529e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final PorterDuff.Mode f21524f = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final k0 f21526h = new k0(6);

    public static synchronized l0 b() {
        try {
            if (f21525g == null) {
                f21525g = new l0();
            }
        } catch (Throwable th) {
            throw th;
        }
        return f21525g;
    }

    public static synchronized PorterDuffColorFilter e(int i7, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        k0 k0Var = f21526h;
        k0Var.getClass();
        int i10 = (31 + i7) * 31;
        porterDuffColorFilter = (PorterDuffColorFilter) k0Var.g(Integer.valueOf(mode.hashCode() + i10));
        if (porterDuffColorFilter == null) {
            porterDuffColorFilter = new PorterDuffColorFilter(i7, mode);
        }
        return porterDuffColorFilter;
    }

    public final Drawable a(Context context, int i7) {
        Drawable drawableNewDrawable;
        WeakReference weakReference;
        if (this.c == null) {
            this.c = new TypedValue();
        }
        TypedValue typedValue = this.c;
        context.getResources().getValue(i7, typedValue, true);
        long j3 = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
        synchronized (this) {
            C2472l c2472l = (C2472l) this.f21528b.get(context);
            drawableNewDrawable = null;
            if (c2472l != null && (weakReference = (WeakReference) c2472l.b(j3)) != null) {
                Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
                if (constantState != null) {
                    drawableNewDrawable = constantState.newDrawable(context.getResources());
                } else {
                    c2472l.f(j3);
                }
            }
        }
        if (drawableNewDrawable != null) {
            return drawableNewDrawable;
        }
        LayerDrawable layerDrawableA = null;
        if (this.f21529e != null) {
            if (i7 == R.drawable.abc_cab_background_top_material) {
                layerDrawableA = new LayerDrawable(new Drawable[]{c(context, R.drawable.abc_cab_background_internal_bg), c(context, R.drawable.abc_cab_background_top_mtrl_alpha)});
            } else if (i7 == R.drawable.abc_ratingbar_material) {
                layerDrawableA = H0.b.A(this, context, R.dimen.abc_star_big);
            } else if (i7 == R.drawable.abc_ratingbar_indicator_material) {
                layerDrawableA = H0.b.A(this, context, R.dimen.abc_star_medium);
            } else if (i7 == R.drawable.abc_ratingbar_small_material) {
                layerDrawableA = H0.b.A(this, context, R.dimen.abc_star_small);
            }
        }
        if (layerDrawableA != null) {
            layerDrawableA.setChangingConfigurations(typedValue.changingConfigurations);
            synchronized (this) {
                try {
                    Drawable.ConstantState constantState2 = layerDrawableA.getConstantState();
                    if (constantState2 != null) {
                        C2472l c2472l2 = (C2472l) this.f21528b.get(context);
                        if (c2472l2 == null) {
                            c2472l2 = new C2472l((Object) null);
                            this.f21528b.put(context, c2472l2);
                        }
                        c2472l2.e(j3, new WeakReference(constantState2));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return layerDrawableA;
    }

    public final synchronized Drawable c(Context context, int i7) {
        return d(context, i7);
    }

    public final synchronized Drawable d(Context context, int i7) {
        Drawable drawableA;
        try {
            if (!this.d) {
                this.d = true;
                Drawable drawableC = c(context, R.drawable.abc_vector_test);
                if (drawableC == null || (!(drawableC instanceof AbstractC2598a) && !"android.graphics.drawable.VectorDrawable".equals(drawableC.getClass().getName()))) {
                    this.d = false;
                    throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
                }
            }
            drawableA = a(context, i7);
            if (drawableA == null) {
                drawableA = context.getDrawable(i7);
            }
            if (drawableA != null) {
                drawableA = g(context, i7, drawableA);
            }
            if (drawableA != null) {
                AbstractC2225M.a(drawableA);
            }
        } catch (Throwable th) {
            throw th;
        }
        return drawableA;
    }

    public final synchronized ColorStateList f(Context context, int i7) {
        ColorStateList colorStateList;
        C2459G c2459g;
        WeakHashMap weakHashMap = this.f21527a;
        ColorStateList colorStateListC = null;
        colorStateList = (weakHashMap == null || (c2459g = (C2459G) weakHashMap.get(context)) == null) ? null : (ColorStateList) c2459g.c(i7);
        if (colorStateList == null) {
            H0.b bVar = this.f21529e;
            if (bVar != null) {
                colorStateListC = bVar.C(context, i7);
            }
            if (colorStateListC != null) {
                if (this.f21527a == null) {
                    this.f21527a = new WeakHashMap();
                }
                C2459G c2459g2 = (C2459G) this.f21527a.get(context);
                if (c2459g2 == null) {
                    c2459g2 = new C2459G(0);
                    this.f21527a.put(context, c2459g2);
                }
                c2459g2.a(i7, colorStateListC);
            }
            colorStateList = colorStateListC;
        }
        return colorStateList;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.drawable.Drawable g(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r.l0.g(android.content.Context, int, android.graphics.drawable.Drawable):android.graphics.drawable.Drawable");
    }
}
