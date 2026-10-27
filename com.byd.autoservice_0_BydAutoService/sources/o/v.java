package o;

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
public final class v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static v f4389b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WeakHashMap<Context, s.h<ColorStateList>> f4391d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public s.g<String, d> f4392e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public s.h<String> f4393f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final WeakHashMap<Context, s.d<WeakReference<Drawable.ConstantState>>> f4394g = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TypedValue f4395h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4396i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public e f4397j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final PorterDuff.Mode f4388a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f4390c = new c(6);

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class a implements d {
        @Override // o.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return k.a.l(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e3) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e3);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class b implements d {
        @Override // o.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return z0.b.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e3) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e3);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class c extends s.e<Integer, PorterDuffColorFilter> {
        public c(int i3) {
            super(i3);
        }

        public static int h(int i3, PorterDuff.Mode mode) {
            return ((i3 + 31) * 31) + mode.hashCode();
        }

        public PorterDuffColorFilter i(int i3, PorterDuff.Mode mode) {
            return c(Integer.valueOf(h(i3, mode)));
        }

        public PorterDuffColorFilter j(int i3, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return d(Integer.valueOf(h(i3, mode)), porterDuffColorFilter);
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public interface d {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public interface e {
        Drawable a(v vVar, Context context, int i3);

        boolean b(Context context, int i3, Drawable drawable);

        ColorStateList c(Context context, int i3);

        boolean d(Context context, int i3, Drawable drawable);

        PorterDuff.Mode e(int i3);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class f implements d {
        @Override // o.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return z0.h.c(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e3) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e3);
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

    public static synchronized v h() {
        if (f4389b == null) {
            v vVar = new v();
            f4389b = vVar;
            p(vVar);
        }
        return f4389b;
    }

    public static synchronized PorterDuffColorFilter l(int i3, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterI;
        c cVar = f4390c;
        porterDuffColorFilterI = cVar.i(i3, mode);
        if (porterDuffColorFilterI == null) {
            porterDuffColorFilterI = new PorterDuffColorFilter(i3, mode);
            cVar.j(i3, mode, porterDuffColorFilterI);
        }
        return porterDuffColorFilterI;
    }

    public static void p(v vVar) {
        if (Build.VERSION.SDK_INT < 24) {
            vVar.a("vector", new f());
            vVar.a("animated-vector", new b());
            vVar.a("animated-selector", new a());
        }
    }

    public static boolean q(Drawable drawable) {
        return (drawable instanceof z0.h) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    public static void w(Drawable drawable, c0 c0Var, int[] iArr) {
        if (q.a(drawable) && drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        boolean z3 = c0Var.f4238d;
        if (z3 || c0Var.f4237c) {
            drawable.setColorFilter(g(z3 ? c0Var.f4235a : null, c0Var.f4237c ? c0Var.f4236b : f4388a, iArr));
        } else {
            drawable.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    public final void a(String str, d dVar) {
        if (this.f4392e == null) {
            this.f4392e = new s.g<>();
        }
        this.f4392e.put(str, dVar);
    }

    public final synchronized boolean b(Context context, long j3, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        s.d<WeakReference<Drawable.ConstantState>> dVar = this.f4394g.get(context);
        if (dVar == null) {
            dVar = new s.d<>();
            this.f4394g.put(context, dVar);
        }
        dVar.i(j3, new WeakReference<>(constantState));
        return true;
    }

    public final void c(Context context, int i3, ColorStateList colorStateList) {
        if (this.f4391d == null) {
            this.f4391d = new WeakHashMap<>();
        }
        s.h<ColorStateList> hVar = this.f4391d.get(context);
        if (hVar == null) {
            hVar = new s.h<>();
            this.f4391d.put(context, hVar);
        }
        hVar.a(i3, colorStateList);
    }

    public final void d(Context context) {
        if (this.f4396i) {
            return;
        }
        this.f4396i = true;
        Drawable drawableJ = j(context, R$drawable.abc_vector_test);
        if (drawableJ == null || !q(drawableJ)) {
            this.f4396i = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    public final Drawable f(Context context, int i3) {
        if (this.f4395h == null) {
            this.f4395h = new TypedValue();
        }
        TypedValue typedValue = this.f4395h;
        context.getResources().getValue(i3, typedValue, true);
        long jE = e(typedValue);
        Drawable drawableI = i(context, jE);
        if (drawableI != null) {
            return drawableI;
        }
        e eVar = this.f4397j;
        Drawable drawableA = eVar == null ? null : eVar.a(this, context, i3);
        if (drawableA != null) {
            drawableA.setChangingConfigurations(typedValue.changingConfigurations);
            b(context, jE, drawableA);
        }
        return drawableA;
    }

    public final synchronized Drawable i(Context context, long j3) {
        s.d<WeakReference<Drawable.ConstantState>> dVar = this.f4394g.get(context);
        if (dVar == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> weakReferenceE = dVar.e(j3);
        if (weakReferenceE != null) {
            Drawable.ConstantState constantState = weakReferenceE.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            dVar.j(j3);
        }
        return null;
    }

    public synchronized Drawable j(Context context, int i3) {
        return k(context, i3, false);
    }

    public synchronized Drawable k(Context context, int i3, boolean z3) {
        Drawable drawableR;
        d(context);
        drawableR = r(context, i3);
        if (drawableR == null) {
            drawableR = f(context, i3);
        }
        if (drawableR == null) {
            drawableR = b0.a.d(context, i3);
        }
        if (drawableR != null) {
            drawableR = v(context, i3, z3, drawableR);
        }
        if (drawableR != null) {
            q.b(drawableR);
        }
        return drawableR;
    }

    public synchronized ColorStateList m(Context context, int i3) {
        ColorStateList colorStateListN;
        colorStateListN = n(context, i3);
        if (colorStateListN == null) {
            e eVar = this.f4397j;
            colorStateListN = eVar == null ? null : eVar.c(context, i3);
            if (colorStateListN != null) {
                c(context, i3, colorStateListN);
            }
        }
        return colorStateListN;
    }

    public final ColorStateList n(Context context, int i3) {
        s.h<ColorStateList> hVar;
        WeakHashMap<Context, s.h<ColorStateList>> weakHashMap = this.f4391d;
        if (weakHashMap == null || (hVar = weakHashMap.get(context)) == null) {
            return null;
        }
        return hVar.e(i3);
    }

    public PorterDuff.Mode o(int i3) {
        e eVar = this.f4397j;
        if (eVar == null) {
            return null;
        }
        return eVar.e(i3);
    }

    public final Drawable r(Context context, int i3) {
        int next;
        s.g<String, d> gVar = this.f4392e;
        if (gVar == null || gVar.isEmpty()) {
            return null;
        }
        s.h<String> hVar = this.f4393f;
        if (hVar != null) {
            String strE = hVar.e(i3);
            if ("appcompat_skip_skip".equals(strE) || (strE != null && this.f4392e.get(strE) == null)) {
                return null;
            }
        } else {
            this.f4393f = new s.h<>();
        }
        if (this.f4395h == null) {
            this.f4395h = new TypedValue();
        }
        TypedValue typedValue = this.f4395h;
        Resources resources = context.getResources();
        resources.getValue(i3, typedValue, true);
        long jE = e(typedValue);
        Drawable drawableI = i(context, jE);
        if (drawableI != null) {
            return drawableI;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i3);
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
                this.f4393f.a(i3, name);
                d dVar = this.f4392e.get(name);
                if (dVar != null) {
                    drawableI = dVar.a(context, xml, attributeSetAsAttributeSet, context.getTheme());
                }
                if (drawableI != null) {
                    drawableI.setChangingConfigurations(typedValue.changingConfigurations);
                    b(context, jE, drawableI);
                }
            } catch (Exception e3) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e3);
            }
        }
        if (drawableI == null) {
            this.f4393f.a(i3, "appcompat_skip_skip");
        }
        return drawableI;
    }

    public synchronized void s(Context context) {
        s.d<WeakReference<Drawable.ConstantState>> dVar = this.f4394g.get(context);
        if (dVar != null) {
            dVar.b();
        }
    }

    public synchronized Drawable t(Context context, j0 j0Var, int i3) {
        Drawable drawableR = r(context, i3);
        if (drawableR == null) {
            drawableR = j0Var.c(i3);
        }
        if (drawableR == null) {
            return null;
        }
        return v(context, i3, false, drawableR);
    }

    public synchronized void u(e eVar) {
        this.f4397j = eVar;
    }

    public final Drawable v(Context context, int i3, boolean z3, Drawable drawable) {
        ColorStateList colorStateListM = m(context, i3);
        if (colorStateListM == null) {
            e eVar = this.f4397j;
            if ((eVar == null || !eVar.d(context, i3, drawable)) && !x(context, i3, drawable) && z3) {
                return null;
            }
            return drawable;
        }
        if (q.a(drawable)) {
            drawable = drawable.mutate();
        }
        Drawable drawableR = e0.a.r(drawable);
        e0.a.o(drawableR, colorStateListM);
        PorterDuff.Mode modeO = o(i3);
        if (modeO == null) {
            return drawableR;
        }
        e0.a.p(drawableR, modeO);
        return drawableR;
    }

    public boolean x(Context context, int i3, Drawable drawable) {
        e eVar = this.f4397j;
        return eVar != null && eVar.b(context, i3, drawable);
    }
}
