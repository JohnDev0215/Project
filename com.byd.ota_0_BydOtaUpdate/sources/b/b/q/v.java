package b.b.q;

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
    public static v f1104b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WeakHashMap<Context, b.d.h<ColorStateList>> f1106d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b.d.g<String, d> f1107e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b.d.h<String> f1108f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final WeakHashMap<Context, b.d.d<WeakReference<Drawable.ConstantState>>> f1109g = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TypedValue f1110h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1111i;
    public e j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final PorterDuff.Mode f1103a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f1105c = new c(6);

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class a implements d {
        @Override // b.b.q.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return b.b.m.a.a.l(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e2);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class b implements d {
        @Override // b.b.q.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return b.p.a.a.b.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e2);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class c extends b.d.e<Integer, PorterDuffColorFilter> {
        public c(int i2) {
            super(i2);
        }

        public static int h(int i2, PorterDuff.Mode mode) {
            return ((i2 + 31) * 31) + mode.hashCode();
        }

        public PorterDuffColorFilter i(int i2, PorterDuff.Mode mode) {
            return c(Integer.valueOf(h(i2, mode)));
        }

        public PorterDuffColorFilter j(int i2, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return d(Integer.valueOf(h(i2, mode)), porterDuffColorFilter);
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public interface d {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public interface e {
        Drawable a(v vVar, Context context, int i2);

        boolean b(Context context, int i2, Drawable drawable);

        ColorStateList c(Context context, int i2);

        boolean d(Context context, int i2, Drawable drawable);

        PorterDuff.Mode e(int i2);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class f implements d {
        @Override // b.b.q.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return b.p.a.a.h.c(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e2);
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
        if (f1104b == null) {
            v vVar = new v();
            f1104b = vVar;
            p(vVar);
        }
        return f1104b;
    }

    public static synchronized PorterDuffColorFilter l(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterI;
        c cVar = f1105c;
        porterDuffColorFilterI = cVar.i(i2, mode);
        if (porterDuffColorFilterI == null) {
            porterDuffColorFilterI = new PorterDuffColorFilter(i2, mode);
            cVar.j(i2, mode, porterDuffColorFilterI);
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
        return (drawable instanceof b.p.a.a.h) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    public static void w(Drawable drawable, c0 c0Var, int[] iArr) {
        if (q.a(drawable) && drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        boolean z = c0Var.f998d;
        if (z || c0Var.f997c) {
            drawable.setColorFilter(g(z ? c0Var.f995a : null, c0Var.f997c ? c0Var.f996b : f1103a, iArr));
        } else {
            drawable.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    public final void a(String str, d dVar) {
        if (this.f1107e == null) {
            this.f1107e = new b.d.g<>();
        }
        this.f1107e.put(str, dVar);
    }

    public final synchronized boolean b(Context context, long j, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        b.d.d<WeakReference<Drawable.ConstantState>> dVar = this.f1109g.get(context);
        if (dVar == null) {
            dVar = new b.d.d<>();
            this.f1109g.put(context, dVar);
        }
        dVar.j(j, new WeakReference<>(constantState));
        return true;
    }

    public final void c(Context context, int i2, ColorStateList colorStateList) {
        if (this.f1106d == null) {
            this.f1106d = new WeakHashMap<>();
        }
        b.d.h<ColorStateList> hVar = this.f1106d.get(context);
        if (hVar == null) {
            hVar = new b.d.h<>();
            this.f1106d.put(context, hVar);
        }
        hVar.a(i2, colorStateList);
    }

    public final void d(Context context) {
        if (this.f1111i) {
            return;
        }
        this.f1111i = true;
        Drawable drawableJ = j(context, b.b.n.a.f855a);
        if (drawableJ == null || !q(drawableJ)) {
            this.f1111i = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    public final Drawable f(Context context, int i2) {
        if (this.f1110h == null) {
            this.f1110h = new TypedValue();
        }
        TypedValue typedValue = this.f1110h;
        context.getResources().getValue(i2, typedValue, true);
        long jE = e(typedValue);
        Drawable drawableI = i(context, jE);
        if (drawableI != null) {
            return drawableI;
        }
        e eVar = this.j;
        Drawable drawableA = eVar == null ? null : eVar.a(this, context, i2);
        if (drawableA != null) {
            drawableA.setChangingConfigurations(typedValue.changingConfigurations);
            b(context, jE, drawableA);
        }
        return drawableA;
    }

    public final synchronized Drawable i(Context context, long j) {
        b.d.d<WeakReference<Drawable.ConstantState>> dVar = this.f1109g.get(context);
        if (dVar == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> weakReferenceG = dVar.g(j);
        if (weakReferenceG != null) {
            Drawable.ConstantState constantState = weakReferenceG.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            dVar.k(j);
        }
        return null;
    }

    public synchronized Drawable j(Context context, int i2) {
        return k(context, i2, false);
    }

    public synchronized Drawable k(Context context, int i2, boolean z) {
        Drawable drawableR;
        d(context);
        drawableR = r(context, i2);
        if (drawableR == null) {
            drawableR = f(context, i2);
        }
        if (drawableR == null) {
            drawableR = b.f.f.a.c(context, i2);
        }
        if (drawableR != null) {
            drawableR = v(context, i2, z, drawableR);
        }
        if (drawableR != null) {
            q.b(drawableR);
        }
        return drawableR;
    }

    public synchronized ColorStateList m(Context context, int i2) {
        ColorStateList colorStateListN;
        colorStateListN = n(context, i2);
        if (colorStateListN == null) {
            e eVar = this.j;
            colorStateListN = eVar == null ? null : eVar.c(context, i2);
            if (colorStateListN != null) {
                c(context, i2, colorStateListN);
            }
        }
        return colorStateListN;
    }

    public final ColorStateList n(Context context, int i2) {
        b.d.h<ColorStateList> hVar;
        WeakHashMap<Context, b.d.h<ColorStateList>> weakHashMap = this.f1106d;
        if (weakHashMap == null || (hVar = weakHashMap.get(context)) == null) {
            return null;
        }
        return hVar.g(i2);
    }

    public PorterDuff.Mode o(int i2) {
        e eVar = this.j;
        if (eVar == null) {
            return null;
        }
        return eVar.e(i2);
    }

    public final Drawable r(Context context, int i2) {
        int next;
        b.d.g<String, d> gVar = this.f1107e;
        if (gVar == null || gVar.isEmpty()) {
            return null;
        }
        b.d.h<String> hVar = this.f1108f;
        if (hVar != null) {
            String strG = hVar.g(i2);
            if ("appcompat_skip_skip".equals(strG) || (strG != null && this.f1107e.get(strG) == null)) {
                return null;
            }
        } else {
            this.f1108f = new b.d.h<>();
        }
        if (this.f1110h == null) {
            this.f1110h = new TypedValue();
        }
        TypedValue typedValue = this.f1110h;
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        long jE = e(typedValue);
        Drawable drawableI = i(context, jE);
        if (drawableI != null) {
            return drawableI;
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
                this.f1108f.a(i2, name);
                d dVar = this.f1107e.get(name);
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
            this.f1108f.a(i2, "appcompat_skip_skip");
        }
        return drawableI;
    }

    public synchronized void s(Context context) {
        b.d.d<WeakReference<Drawable.ConstantState>> dVar = this.f1109g.get(context);
        if (dVar != null) {
            dVar.b();
        }
    }

    public synchronized Drawable t(Context context, j0 j0Var, int i2) {
        Drawable drawableR = r(context, i2);
        if (drawableR == null) {
            drawableR = j0Var.c(i2);
        }
        if (drawableR == null) {
            return null;
        }
        return v(context, i2, false, drawableR);
    }

    public synchronized void u(e eVar) {
        this.j = eVar;
    }

    public final Drawable v(Context context, int i2, boolean z, Drawable drawable) {
        ColorStateList colorStateListM = m(context, i2);
        if (colorStateListM == null) {
            e eVar = this.j;
            if ((eVar == null || !eVar.d(context, i2, drawable)) && !x(context, i2, drawable) && z) {
                return null;
            }
            return drawable;
        }
        if (q.a(drawable)) {
            drawable = drawable.mutate();
        }
        Drawable drawableQ = b.f.g.l.a.q(drawable);
        b.f.g.l.a.n(drawableQ, colorStateListM);
        PorterDuff.Mode modeO = o(i2);
        if (modeO == null) {
            return drawableQ;
        }
        b.f.g.l.a.o(drawableQ, modeO);
        return drawableQ;
    }

    public boolean x(Context context, int i2, Drawable drawable) {
        e eVar = this.j;
        return eVar != null && eVar.b(context, i2, drawable);
    }
}
