package androidx.appcompat.widget;

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
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ResourceManagerInternal.java */
/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static v f1018b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private WeakHashMap<Context, c.d.h<ColorStateList>> f1020d;
    private c.d.g<String, d> e;
    private c.d.h<String> f;
    private final WeakHashMap<Context, c.d.d<WeakReference<Drawable.ConstantState>>> g = new WeakHashMap<>(0);
    private TypedValue h;
    private boolean i;
    private e j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final PorterDuff.Mode f1017a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final c f1019c = new c(6);

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    static class a implements d {
        a() {
        }

        @Override // androidx.appcompat.widget.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return c.a.l.a.a.l(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    private static class b implements d {
        b() {
        }

        @Override // androidx.appcompat.widget.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return c.o.a.a.c.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    private static class c extends c.d.e<Integer, PorterDuffColorFilter> {
        public c(int i) {
            super(i);
        }

        private static int h(int i, PorterDuff.Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }

        PorterDuffColorFilter i(int i, PorterDuff.Mode mode) {
            return c(Integer.valueOf(h(i, mode)));
        }

        PorterDuffColorFilter j(int i, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return d(Integer.valueOf(h(i, mode)), porterDuffColorFilter);
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    private interface d {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    interface e {
        boolean a(Context context, int i, Drawable drawable);

        PorterDuff.Mode b(int i);

        Drawable c(v vVar, Context context, int i);

        ColorStateList d(Context context, int i);

        boolean e(Context context, int i, Drawable drawable);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    private static class f implements d {
        f() {
        }

        @Override // androidx.appcompat.widget.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return c.o.a.a.i.c(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e);
                return null;
            }
        }
    }

    private void a(String str, d dVar) {
        if (this.e == null) {
            this.e = new c.d.g<>();
        }
        this.e.put(str, dVar);
    }

    private synchronized boolean b(Context context, long j, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        c.d.d<WeakReference<Drawable.ConstantState>> dVar = this.g.get(context);
        if (dVar == null) {
            dVar = new c.d.d<>();
            this.g.put(context, dVar);
        }
        dVar.i(j, new WeakReference<>(constantState));
        return true;
    }

    private void c(Context context, int i, ColorStateList colorStateList) {
        if (this.f1020d == null) {
            this.f1020d = new WeakHashMap<>();
        }
        c.d.h<ColorStateList> hVar = this.f1020d.get(context);
        if (hVar == null) {
            hVar = new c.d.h<>();
            this.f1020d.put(context, hVar);
        }
        hVar.a(i, colorStateList);
    }

    private void d(Context context) {
        if (this.i) {
            return;
        }
        this.i = true;
        Drawable drawableJ = j(context, c.a.m.a.f2024a);
        if (drawableJ == null || !q(drawableJ)) {
            this.i = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    private static long e(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    private Drawable f(Context context, int i) {
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
        Drawable drawableC = eVar == null ? null : eVar.c(this, context, i);
        if (drawableC != null) {
            drawableC.setChangingConfigurations(typedValue.changingConfigurations);
            b(context, jE, drawableC);
        }
        return drawableC;
    }

    private static PorterDuffColorFilter g(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return l(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized v h() {
        if (f1018b == null) {
            v vVar = new v();
            f1018b = vVar;
            p(vVar);
        }
        return f1018b;
    }

    private synchronized Drawable i(Context context, long j) {
        c.d.d<WeakReference<Drawable.ConstantState>> dVar = this.g.get(context);
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

    public static synchronized PorterDuffColorFilter l(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterI;
        c cVar = f1019c;
        porterDuffColorFilterI = cVar.i(i, mode);
        if (porterDuffColorFilterI == null) {
            porterDuffColorFilterI = new PorterDuffColorFilter(i, mode);
            cVar.j(i, mode, porterDuffColorFilterI);
        }
        return porterDuffColorFilterI;
    }

    private ColorStateList n(Context context, int i) {
        c.d.h<ColorStateList> hVar;
        WeakHashMap<Context, c.d.h<ColorStateList>> weakHashMap = this.f1020d;
        if (weakHashMap == null || (hVar = weakHashMap.get(context)) == null) {
            return null;
        }
        return hVar.e(i);
    }

    private static void p(v vVar) {
        if (Build.VERSION.SDK_INT < 24) {
            vVar.a("vector", new f());
            vVar.a("animated-vector", new b());
            vVar.a("animated-selector", new a());
        }
    }

    private static boolean q(Drawable drawable) {
        return (drawable instanceof c.o.a.a.i) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    private Drawable r(Context context, int i) {
        int next;
        c.d.g<String, d> gVar = this.e;
        if (gVar == null || gVar.isEmpty()) {
            return null;
        }
        c.d.h<String> hVar = this.f;
        if (hVar != null) {
            String strE = hVar.e(i);
            if ("appcompat_skip_skip".equals(strE) || (strE != null && this.e.get(strE) == null)) {
                return null;
            }
        } else {
            this.f = new c.d.h<>();
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

    private Drawable v(Context context, int i, boolean z, Drawable drawable) {
        ColorStateList colorStateListM = m(context, i);
        if (colorStateListM == null) {
            e eVar = this.j;
            if ((eVar == null || !eVar.e(context, i, drawable)) && !x(context, i, drawable) && z) {
                return null;
            }
            return drawable;
        }
        if (q.a(drawable)) {
            drawable = drawable.mutate();
        }
        Drawable drawableR = androidx.core.graphics.drawable.a.r(drawable);
        androidx.core.graphics.drawable.a.o(drawableR, colorStateListM);
        PorterDuff.Mode modeO = o(i);
        if (modeO == null) {
            return drawableR;
        }
        androidx.core.graphics.drawable.a.p(drawableR, modeO);
        return drawableR;
    }

    static void w(Drawable drawable, c0 c0Var, int[] iArr) {
        if (q.a(drawable) && drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        boolean z = c0Var.f938d;
        if (z || c0Var.f937c) {
            drawable.setColorFilter(g(z ? c0Var.f935a : null, c0Var.f937c ? c0Var.f936b : f1017a, iArr));
        } else {
            drawable.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    public synchronized Drawable j(Context context, int i) {
        return k(context, i, false);
    }

    synchronized Drawable k(Context context, int i, boolean z) {
        Drawable drawableR;
        d(context);
        drawableR = r(context, i);
        if (drawableR == null) {
            drawableR = f(context, i);
        }
        if (drawableR == null) {
            drawableR = androidx.core.content.a.d(context, i);
        }
        if (drawableR != null) {
            drawableR = v(context, i, z, drawableR);
        }
        if (drawableR != null) {
            q.b(drawableR);
        }
        return drawableR;
    }

    synchronized ColorStateList m(Context context, int i) {
        ColorStateList colorStateListN;
        colorStateListN = n(context, i);
        if (colorStateListN == null) {
            e eVar = this.j;
            colorStateListN = eVar == null ? null : eVar.d(context, i);
            if (colorStateListN != null) {
                c(context, i, colorStateListN);
            }
        }
        return colorStateListN;
    }

    PorterDuff.Mode o(int i) {
        e eVar = this.j;
        if (eVar == null) {
            return null;
        }
        return eVar.b(i);
    }

    public synchronized void s(Context context) {
        c.d.d<WeakReference<Drawable.ConstantState>> dVar = this.g.get(context);
        if (dVar != null) {
            dVar.b();
        }
    }

    synchronized Drawable t(Context context, j0 j0Var, int i) {
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

    boolean x(Context context, int i, Drawable drawable) {
        e eVar = this.j;
        return eVar != null && eVar.a(context, i, drawable);
    }
}
