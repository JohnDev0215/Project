package r;

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
    public static v f7648b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WeakHashMap<Context, v.h<ColorStateList>> f7650d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public v.g<String, d> f7651e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public v.h<String> f7652f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final WeakHashMap<Context, v.d<WeakReference<Drawable.ConstantState>>> f7653g = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TypedValue f7654h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f7655i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public e f7656j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final PorterDuff.Mode f7647a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f7649c = new c(6);

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class a implements d {
        @Override // r.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return n.a.l(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e8) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e8);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class b implements d {
        @Override // r.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return l1.b.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e8) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e8);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class c extends v.e<Integer, PorterDuffColorFilter> {
        public c(int i8) {
            super(i8);
        }

        public static int h(int i8, PorterDuff.Mode mode) {
            return ((i8 + 31) * 31) + mode.hashCode();
        }

        public PorterDuffColorFilter i(int i8, PorterDuff.Mode mode) {
            return c(Integer.valueOf(h(i8, mode)));
        }

        public PorterDuffColorFilter j(int i8, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return d(Integer.valueOf(h(i8, mode)), porterDuffColorFilter);
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public interface d {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public interface e {
        boolean a(Context context, int i8, Drawable drawable);

        PorterDuff.Mode b(int i8);

        Drawable c(v vVar, Context context, int i8);

        ColorStateList d(Context context, int i8);

        boolean e(Context context, int i8, Drawable drawable);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class f implements d {
        @Override // r.v.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return l1.h.c(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e8) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e8);
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
        if (f7648b == null) {
            v vVar = new v();
            f7648b = vVar;
            p(vVar);
        }
        return f7648b;
    }

    public static synchronized PorterDuffColorFilter l(int i8, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterI;
        c cVar = f7649c;
        porterDuffColorFilterI = cVar.i(i8, mode);
        if (porterDuffColorFilterI == null) {
            porterDuffColorFilterI = new PorterDuffColorFilter(i8, mode);
            cVar.j(i8, mode, porterDuffColorFilterI);
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
        return (drawable instanceof l1.h) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    public static void w(Drawable drawable, c0 c0Var, int[] iArr) {
        if (q.a(drawable) && drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        boolean z7 = c0Var.f7497d;
        if (z7 || c0Var.f7496c) {
            drawable.setColorFilter(g(z7 ? c0Var.f7494a : null, c0Var.f7496c ? c0Var.f7495b : f7647a, iArr));
        } else {
            drawable.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    public final void a(String str, d dVar) {
        if (this.f7651e == null) {
            this.f7651e = new v.g<>();
        }
        this.f7651e.put(str, dVar);
    }

    public final synchronized boolean b(Context context, long j8, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        v.d<WeakReference<Drawable.ConstantState>> dVar = this.f7653g.get(context);
        if (dVar == null) {
            dVar = new v.d<>();
            this.f7653g.put(context, dVar);
        }
        dVar.k(j8, new WeakReference<>(constantState));
        return true;
    }

    public final void c(Context context, int i8, ColorStateList colorStateList) {
        if (this.f7650d == null) {
            this.f7650d = new WeakHashMap<>();
        }
        v.h<ColorStateList> hVar = this.f7650d.get(context);
        if (hVar == null) {
            hVar = new v.h<>();
            this.f7650d.put(context, hVar);
        }
        hVar.a(i8, colorStateList);
    }

    public final void d(Context context) {
        if (this.f7655i) {
            return;
        }
        this.f7655i = true;
        Drawable drawableJ = j(context, R$drawable.abc_vector_test);
        if (drawableJ == null || !q(drawableJ)) {
            this.f7655i = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    public final Drawable f(Context context, int i8) {
        if (this.f7654h == null) {
            this.f7654h = new TypedValue();
        }
        TypedValue typedValue = this.f7654h;
        context.getResources().getValue(i8, typedValue, true);
        long jE = e(typedValue);
        Drawable drawableI = i(context, jE);
        if (drawableI != null) {
            return drawableI;
        }
        e eVar = this.f7656j;
        Drawable drawableC = eVar == null ? null : eVar.c(this, context, i8);
        if (drawableC != null) {
            drawableC.setChangingConfigurations(typedValue.changingConfigurations);
            b(context, jE, drawableC);
        }
        return drawableC;
    }

    public final synchronized Drawable i(Context context, long j8) {
        v.d<WeakReference<Drawable.ConstantState>> dVar = this.f7653g.get(context);
        if (dVar == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> weakReferenceF = dVar.f(j8);
        if (weakReferenceF != null) {
            Drawable.ConstantState constantState = weakReferenceF.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            dVar.l(j8);
        }
        return null;
    }

    public synchronized Drawable j(Context context, int i8) {
        return k(context, i8, false);
    }

    public synchronized Drawable k(Context context, int i8, boolean z7) {
        Drawable drawableR;
        d(context);
        drawableR = r(context, i8);
        if (drawableR == null) {
            drawableR = f(context, i8);
        }
        if (drawableR == null) {
            drawableR = e0.a.d(context, i8);
        }
        if (drawableR != null) {
            drawableR = v(context, i8, z7, drawableR);
        }
        if (drawableR != null) {
            q.b(drawableR);
        }
        return drawableR;
    }

    public synchronized ColorStateList m(Context context, int i8) {
        ColorStateList colorStateListN;
        colorStateListN = n(context, i8);
        if (colorStateListN == null) {
            e eVar = this.f7656j;
            colorStateListN = eVar == null ? null : eVar.d(context, i8);
            if (colorStateListN != null) {
                c(context, i8, colorStateListN);
            }
        }
        return colorStateListN;
    }

    public final ColorStateList n(Context context, int i8) {
        v.h<ColorStateList> hVar;
        WeakHashMap<Context, v.h<ColorStateList>> weakHashMap = this.f7650d;
        if (weakHashMap == null || (hVar = weakHashMap.get(context)) == null) {
            return null;
        }
        return hVar.e(i8);
    }

    public PorterDuff.Mode o(int i8) {
        e eVar = this.f7656j;
        if (eVar == null) {
            return null;
        }
        return eVar.b(i8);
    }

    public final Drawable r(Context context, int i8) {
        int next;
        v.g<String, d> gVar = this.f7651e;
        if (gVar == null || gVar.isEmpty()) {
            return null;
        }
        v.h<String> hVar = this.f7652f;
        if (hVar != null) {
            String strE = hVar.e(i8);
            if ("appcompat_skip_skip".equals(strE) || (strE != null && this.f7651e.get(strE) == null)) {
                return null;
            }
        } else {
            this.f7652f = new v.h<>();
        }
        if (this.f7654h == null) {
            this.f7654h = new TypedValue();
        }
        TypedValue typedValue = this.f7654h;
        Resources resources = context.getResources();
        resources.getValue(i8, typedValue, true);
        long jE = e(typedValue);
        Drawable drawableI = i(context, jE);
        if (drawableI != null) {
            return drawableI;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i8);
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
                this.f7652f.a(i8, name);
                d dVar = this.f7651e.get(name);
                if (dVar != null) {
                    drawableI = dVar.a(context, xml, attributeSetAsAttributeSet, context.getTheme());
                }
                if (drawableI != null) {
                    drawableI.setChangingConfigurations(typedValue.changingConfigurations);
                    b(context, jE, drawableI);
                }
            } catch (Exception e8) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e8);
            }
        }
        if (drawableI == null) {
            this.f7652f.a(i8, "appcompat_skip_skip");
        }
        return drawableI;
    }

    public synchronized void s(Context context) {
        v.d<WeakReference<Drawable.ConstantState>> dVar = this.f7653g.get(context);
        if (dVar != null) {
            dVar.b();
        }
    }

    public synchronized Drawable t(Context context, j0 j0Var, int i8) {
        Drawable drawableR = r(context, i8);
        if (drawableR == null) {
            drawableR = j0Var.c(i8);
        }
        if (drawableR == null) {
            return null;
        }
        return v(context, i8, false, drawableR);
    }

    public synchronized void u(e eVar) {
        this.f7656j = eVar;
    }

    public final Drawable v(Context context, int i8, boolean z7, Drawable drawable) {
        ColorStateList colorStateListM = m(context, i8);
        if (colorStateListM == null) {
            e eVar = this.f7656j;
            if ((eVar == null || !eVar.e(context, i8, drawable)) && !x(context, i8, drawable) && z7) {
                return null;
            }
            return drawable;
        }
        if (q.a(drawable)) {
            drawable = drawable.mutate();
        }
        Drawable drawableR = h0.a.r(drawable);
        h0.a.o(drawableR, colorStateListM);
        PorterDuff.Mode modeO = o(i8);
        if (modeO == null) {
            return drawableR;
        }
        h0.a.p(drawableR, modeO);
        return drawableR;
    }

    public boolean x(Context context, int i8, Drawable drawable) {
        e eVar = this.f7656j;
        return eVar != null && eVar.a(context, i8, drawable);
    }
}
