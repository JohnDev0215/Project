package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import androidx.appcompat.resources.R$drawable;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ResourceManagerInternal.java */
/* JADX INFO: loaded from: classes.dex */
public final class x2 {
    public static x2 b;
    public WeakHashMap<Context, h4<ColorStateList>> d;
    public g4<String, d> e;
    public h4<String> f;
    public final WeakHashMap<Context, d4<WeakReference<Drawable.ConstantState>>> g = new WeakHashMap<>(0);
    public TypedValue h;
    public boolean i;
    public e j;
    public static final PorterDuff.Mode a = PorterDuff.Mode.SRC_IN;
    public static final c c = new c(6);

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class a implements d {
        @Override // x2.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return v0.l(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class b implements d {
        @Override // x2.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return af.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class c extends e4<Integer, PorterDuffColorFilter> {
        public c(int i) {
            super(i);
        }

        public static int h(int i, PorterDuff.Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }

        public PorterDuffColorFilter i(int i, PorterDuff.Mode mode) {
            return c(Integer.valueOf(h(i, mode)));
        }

        public PorterDuffColorFilter j(int i, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return d(Integer.valueOf(h(i, mode)), porterDuffColorFilter);
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public interface d {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public interface e {
        Drawable a(x2 x2Var, Context context, int i);

        boolean b(Context context, int i, Drawable drawable);

        ColorStateList c(Context context, int i);

        boolean d(Context context, int i, Drawable drawable);

        PorterDuff.Mode e(int i);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class f implements d {
        @Override // x2.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return gf.c(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e);
                return null;
            }
        }
    }

    public static long e(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    public static PorterDuffColorFilter g(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return l(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized x2 h() {
        if (b == null) {
            x2 x2Var = new x2();
            b = x2Var;
            p(x2Var);
        }
        return b;
    }

    public static synchronized PorterDuffColorFilter l(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterI;
        c cVar = c;
        porterDuffColorFilterI = cVar.i(i, mode);
        if (porterDuffColorFilterI == null) {
            porterDuffColorFilterI = new PorterDuffColorFilter(i, mode);
            cVar.j(i, mode, porterDuffColorFilterI);
        }
        return porterDuffColorFilterI;
    }

    public static void p(x2 x2Var) {
        if (Build.VERSION.SDK_INT < 24) {
            x2Var.a("vector", new f());
            x2Var.a("animated-vector", new b());
            x2Var.a("animated-selector", new a());
        }
    }

    public static boolean q(Drawable drawable) {
        return (drawable instanceof gf) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    public static void w(Drawable drawable, e3 e3Var, int[] iArr) {
        if (s2.a(drawable) && drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        boolean z = e3Var.d;
        if (z || e3Var.c) {
            drawable.setColorFilter(g(z ? e3Var.a : null, e3Var.c ? e3Var.b : a, iArr));
        } else {
            drawable.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    public final void a(String str, d dVar) {
        if (this.e == null) {
            this.e = new g4<>();
        }
        this.e.put(str, dVar);
    }

    public final synchronized boolean b(Context context, long j, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        d4<WeakReference<Drawable.ConstantState>> d4Var = this.g.get(context);
        if (d4Var == null) {
            d4Var = new d4<>();
            this.g.put(context, d4Var);
        }
        d4Var.i(j, new WeakReference<>(constantState));
        return true;
    }

    public final void c(Context context, int i, ColorStateList colorStateList) {
        if (this.d == null) {
            this.d = new WeakHashMap<>();
        }
        h4<ColorStateList> h4Var = this.d.get(context);
        if (h4Var == null) {
            h4Var = new h4<>();
            this.d.put(context, h4Var);
        }
        h4Var.a(i, colorStateList);
    }

    public final void d(Context context) {
        if (this.i) {
            return;
        }
        this.i = true;
        Drawable drawableJ = j(context, R$drawable.abc_vector_test);
        if (drawableJ == null || !q(drawableJ)) {
            this.i = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    public final Drawable f(Context context, int i) {
        if (this.h == null) {
            this.h = new TypedValue();
        }
        TypedValue typedValue = this.h;
        context.getResources().getValue(i, typedValue, true);
        long jE = e(typedValue);
        Drawable drawableI = i(context, jE);
        if (drawableI != null) {
            return drawableI;
        }
        e eVar = this.j;
        Drawable drawableA = eVar == null ? null : eVar.a(this, context, i);
        if (drawableA != null) {
            drawableA.setChangingConfigurations(typedValue.changingConfigurations);
            b(context, jE, drawableA);
        }
        return drawableA;
    }

    public final synchronized Drawable i(Context context, long j) {
        d4<WeakReference<Drawable.ConstantState>> d4Var = this.g.get(context);
        if (d4Var == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> weakReferenceE = d4Var.e(j);
        if (weakReferenceE != null) {
            Drawable.ConstantState constantState = weakReferenceE.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            d4Var.j(j);
        }
        return null;
    }

    public synchronized Drawable j(Context context, int i) {
        return k(context, i, false);
    }

    public synchronized Drawable k(Context context, int i, boolean z) {
        Drawable drawableR;
        d(context);
        drawableR = r(context, i);
        if (drawableR == null) {
            drawableR = f(context, i);
        }
        if (drawableR == null) {
            drawableR = e7.d(context, i);
        }
        if (drawableR != null) {
            drawableR = v(context, i, z, drawableR);
        }
        if (drawableR != null) {
            s2.b(drawableR);
        }
        return drawableR;
    }

    public synchronized ColorStateList m(Context context, int i) {
        ColorStateList colorStateListN;
        colorStateListN = n(context, i);
        if (colorStateListN == null) {
            e eVar = this.j;
            colorStateListN = eVar == null ? null : eVar.c(context, i);
            if (colorStateListN != null) {
                c(context, i, colorStateListN);
            }
        }
        return colorStateListN;
    }

    public final ColorStateList n(Context context, int i) {
        h4<ColorStateList> h4Var;
        WeakHashMap<Context, h4<ColorStateList>> weakHashMap = this.d;
        if (weakHashMap == null || (h4Var = weakHashMap.get(context)) == null) {
            return null;
        }
        return h4Var.e(i);
    }

    public PorterDuff.Mode o(int i) {
        e eVar = this.j;
        if (eVar == null) {
            return null;
        }
        return eVar.e(i);
    }

    public final Drawable r(Context context, int i) {
        int next;
        g4<String, d> g4Var = this.e;
        if (g4Var == null || g4Var.isEmpty()) {
            return null;
        }
        h4<String> h4Var = this.f;
        if (h4Var != null) {
            String strE = h4Var.e(i);
            if ("appcompat_skip_skip".equals(strE) || (strE != null && this.e.get(strE) == null)) {
                return null;
            }
        } else {
            this.f = new h4<>();
        }
        if (this.h == null) {
            this.h = new TypedValue();
        }
        TypedValue typedValue = this.h;
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        long jE = e(typedValue);
        Drawable drawableI = i(context, jE);
        if (drawableI != null) {
            return drawableI;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i);
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
                this.f.a(i, name);
                d dVar = this.e.get(name);
                if (dVar != null) {
                    drawableI = dVar.a(context, xml, attributeSetAsAttributeSet, context.getTheme());
                }
                if (drawableI != null) {
                    drawableI.setChangingConfigurations(typedValue.changingConfigurations);
                    b(context, jE, drawableI);
                }
            } catch (Exception e2) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e2);
            }
        }
        if (drawableI == null) {
            this.f.a(i, "appcompat_skip_skip");
        }
        return drawableI;
    }

    public synchronized void s(Context context) {
        d4<WeakReference<Drawable.ConstantState>> d4Var = this.g.get(context);
        if (d4Var != null) {
            d4Var.b();
        }
    }

    public synchronized Drawable t(Context context, l3 l3Var, int i) {
        Drawable drawableR = r(context, i);
        if (drawableR == null) {
            drawableR = l3Var.c(i);
        }
        if (drawableR == null) {
            return null;
        }
        return v(context, i, false, drawableR);
    }

    public synchronized void u(e eVar) {
        this.j = eVar;
    }

    public final Drawable v(Context context, int i, boolean z, Drawable drawable) {
        ColorStateList colorStateListM = m(context, i);
        if (colorStateListM == null) {
            e eVar = this.j;
            if ((eVar == null || !eVar.d(context, i, drawable)) && !x(context, i, drawable) && z) {
                return null;
            }
            return drawable;
        }
        if (s2.a(drawable)) {
            drawable = drawable.mutate();
        }
        Drawable drawableR = y7.r(drawable);
        y7.o(drawableR, colorStateListM);
        PorterDuff.Mode modeO = o(i);
        if (modeO == null) {
            return drawableR;
        }
        y7.p(drawableR, modeO);
        return drawableR;
    }

    public boolean x(Context context, int i, Drawable drawable) {
        e eVar = this.j;
        return eVar != null && eVar.b(context, i, drawable);
    }
}
