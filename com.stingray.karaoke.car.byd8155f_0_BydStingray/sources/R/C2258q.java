package r;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;

/* JADX INFO: renamed from: r.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2258q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final PorterDuff.Mode f21546b = PorterDuff.Mode.SRC_IN;
    public static C2258q c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public l0 f21547a;

    public static synchronized PorterDuffColorFilter b(int i7, PorterDuff.Mode mode) {
        return l0.e(i7, mode);
    }

    public static synchronized void c() {
        if (c == null) {
            C2258q c2258q = new C2258q();
            c = c2258q;
            c2258q.f21547a = l0.b();
            l0 l0Var = c.f21547a;
            H0.b bVar = new H0.b(7);
            synchronized (l0Var) {
                l0Var.f21529e = bVar;
            }
        }
    }

    public static void d(Drawable drawable, c9.m mVar, int[] iArr) {
        PorterDuff.Mode mode = l0.f21524f;
        int[] state = drawable.getState();
        if (drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        if ((drawable instanceof LayerDrawable) && drawable.isStateful()) {
            drawable.setState(new int[0]);
            drawable.setState(state);
        }
        boolean z6 = mVar.f12588b;
        if (!z6 && !mVar.f12587a) {
            drawable.clearColorFilter();
            return;
        }
        PorterDuffColorFilter porterDuffColorFilterE = null;
        ColorStateList colorStateList = z6 ? (ColorStateList) mVar.c : null;
        PorterDuff.Mode mode2 = mVar.f12587a ? (PorterDuff.Mode) mVar.d : l0.f21524f;
        if (colorStateList != null && mode2 != null) {
            porterDuffColorFilterE = l0.e(colorStateList.getColorForState(iArr, 0), mode2);
        }
        drawable.setColorFilter(porterDuffColorFilterE);
    }

    public final synchronized Drawable a(Context context, int i7) {
        return this.f21547a.c(context, i7);
    }
}
