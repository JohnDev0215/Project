package b.b.f;

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
    public static v f831b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WeakHashMap<Context, b.e.h<ColorStateList>> f833d;
    public b.e.g<String, d> e;
    public b.e.h<String> f;
    public final WeakHashMap<Context, b.e.d<WeakReference<Drawable.ConstantState>>> g = new WeakHashMap<>(0);
    public TypedValue h;
    public boolean i;
    public e j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final PorterDuff.Mode f830a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f832c = new c(6);

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class a implements d {
        @Override // b.b.f.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return b.b.c.a.a.m(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class b implements d {
        @Override // b.b.f.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return b.r.a.a.b.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class c extends b.e.e<Integer, PorterDuffColorFilter> {
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
        Drawable a(v vVar, Context context, int i);

        boolean b(Context context, int i, Drawable drawable);

        ColorStateList c(Context context, int i);

        boolean d(Context context, int i, Drawable drawable);

        PorterDuff.Mode e(int i);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class f implements d {
        @Override // b.b.f.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return b.r.a.a.h.c(context.getResources(), xmlPullParser, attributeSet, theme);
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

    public static synchronized v h() {
        if (f831b == null) {
            v vVar = new v();
            f831b = vVar;
            p(vVar);
        }
        return f831b;
    }

    public static synchronized PorterDuffColorFilter l(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterI;
        c cVar = f832c;
        porterDuffColorFilterI = cVar.i(i, mode);
        if (porterDuffColorFilterI == null) {
            porterDuffColorFilterI = new PorterDuffColorFilter(i, mode);
            cVar.j(i, mode, porterDuffColorFilterI);
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
        return (drawable instanceof b.r.a.a.h) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    public static void w(Drawable drawable, c0 c0Var, int[] iArr) {
        if (q.a(drawable) && drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        boolean z = c0Var.f751d;
        if (z || c0Var.f750c) {
            drawable.setColorFilter(g(z ? c0Var.f748a : null, c0Var.f750c ? c0Var.f749b : f830a, iArr));
        } else {
            drawable.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    public final void a(String str, d dVar) {
        if (this.e == null) {
            this.e = new b.e.g<>();
        }
        this.e.put(str, dVar);
    }

    public final synchronized boolean b(Context context, long j, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        b.e.d<WeakReference<Drawable.ConstantState>> dVar = this.g.get(context);
        if (dVar == null) {
            dVar = new b.e.d<>();
            this.g.put(context, dVar);
        }
        dVar.i(j, new WeakReference<>(constantState));
        return true;
    }

    public final void c(Context context, int i, ColorStateList colorStateList) {
        if (this.f833d == null) {
            this.f833d = new WeakHashMap<>();
        }
        b.e.h<ColorStateList> hVar = this.f833d.get(context);
        if (hVar == null) {
            hVar = new b.e.h<>();
            this.f833d.put(context, hVar);
        }
        hVar.a(i, colorStateList);
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
        b.e.d<WeakReference<Drawable.ConstantState>> dVar = this.g.get(context);
        if (dVar == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> weakReferenceE = dVar.e(j);
        if (weakReferenceE != null) {
            Drawable.ConstantState constantState = weakReferenceE.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            dVar.j(j);
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
            drawableR = b.g.b.a.d(context, i);
        }
        if (drawableR != null) {
            drawableR = v(context, i, z, drawableR);
        }
        if (drawableR != null) {
            q.b(drawableR);
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
        b.e.h<ColorStateList> hVar;
        WeakHashMap<Context, b.e.h<ColorStateList>> weakHashMap = this.f833d;
        if (weakHashMap == null || (hVar = weakHashMap.get(context)) == null) {
            return null;
        }
        return hVar.e(i);
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
        b.e.g<String, d> gVar = this.e;
        if (gVar == null || gVar.isEmpty()) {
            return null;
        }
        b.e.h<String> hVar = this.f;
        if (hVar != null) {
            String strE = hVar.e(i);
            if ("appcompat_skip_skip".equals(strE) || (strE != null && this.e.get(strE) == null)) {
                return null;
            }
        } else {
            this.f = new b.e.h<>();
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
        b.e.d<WeakReference<Drawable.ConstantState>> dVar = this.g.get(context);
        if (dVar != null) {
            dVar.b();
        }
    }

    public synchronized Drawable t(Context context, j0 j0Var, int i) {
        Drawable drawableR = r(context, i);
        if (drawableR == null) {
            drawableR = j0Var.c(i);
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
        if (q.a(drawable)) {
            drawable = drawable.mutate();
        }
        Drawable drawableR = b.g.c.l.a.r(drawable);
        b.g.c.l.a.o(drawableR, colorStateListM);
        PorterDuff.Mode modeO = o(i);
        if (modeO == null) {
            return drawableR;
        }
        b.g.c.l.a.p(drawableR, modeO);
        return drawableR;
    }

    public boolean x(Context context, int i, Drawable drawable) {
        e eVar = this.j;
        return eVar != null && eVar.b(context, i, drawable);
    }
}
