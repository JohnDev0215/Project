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
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.resources.R$drawable;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public final class J {
    private static J i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakHashMap<Context, b.a.h<ColorStateList>> f282a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b.a.g<String, e> f283b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b.a.h<String> f284c;
    private final WeakHashMap<Context, b.a.e<WeakReference<Drawable.ConstantState>>> d = new WeakHashMap<>(0);
    private TypedValue e;
    private boolean f;
    private f g;
    private static final PorterDuff.Mode h = PorterDuff.Mode.SRC_IN;
    private static final c j = new c(6);

    /* JADX INFO: compiled from: caradapter-bug */
    static class a implements e {
        a() {
        }

        @Override // androidx.appcompat.widget.J.e
        public Drawable a(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
            try {
                return androidx.appcompat.graphics.drawable.a.l(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class b implements e {
        b() {
        }

        @Override // androidx.appcompat.widget.J.e
        public Drawable a(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
            try {
                return androidx.vectordrawable.a.a.b.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class c extends b.a.f<Integer, PorterDuffColorFilter> {
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

    /* JADX INFO: compiled from: caradapter-bug */
    static class d implements e {
        d() {
        }

        @Override // androidx.appcompat.widget.J.e
        public Drawable a(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
            String classAttribute = attributeSet.getClassAttribute();
            if (classAttribute != null) {
                try {
                    Drawable drawable = (Drawable) d.class.getClassLoader().loadClass(classAttribute).asSubclass(Drawable.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    if (Build.VERSION.SDK_INT >= 21) {
                        drawable.inflate(context.getResources(), xmlPullParser, attributeSet, theme);
                    } else {
                        drawable.inflate(context.getResources(), xmlPullParser, attributeSet);
                    }
                    return drawable;
                } catch (Exception e) {
                    Log.e("DrawableDelegate", "Exception while inflating <drawable>", e);
                }
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private interface e {
        Drawable a(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    interface f {
        Drawable a(@NonNull J j, @NonNull Context context, @DrawableRes int i);

        boolean b(@NonNull Context context, @DrawableRes int i, @NonNull Drawable drawable);

        ColorStateList c(@NonNull Context context, @DrawableRes int i);

        boolean d(@NonNull Context context, @DrawableRes int i, @NonNull Drawable drawable);

        PorterDuff.Mode e(int i);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class g implements e {
        g() {
        }

        @Override // androidx.appcompat.widget.J.e
        public Drawable a(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
            try {
                return androidx.vectordrawable.a.a.h.c(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e);
                return null;
            }
        }
    }

    private void a(@NonNull String str, @NonNull e eVar) {
        if (this.f283b == null) {
            this.f283b = new b.a.g<>();
        }
        this.f283b.put(str, eVar);
    }

    private synchronized boolean b(@NonNull Context context, long j2, @NonNull Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        b.a.e<WeakReference<Drawable.ConstantState>> eVar = this.d.get(context);
        if (eVar == null) {
            eVar = new b.a.e<>();
            this.d.put(context, eVar);
        }
        eVar.h(j2, new WeakReference<>(constantState));
        return true;
    }

    private void c(@NonNull Context context, @DrawableRes int i2, @NonNull ColorStateList colorStateList) {
        if (this.f282a == null) {
            this.f282a = new WeakHashMap<>();
        }
        b.a.h<ColorStateList> hVar = this.f282a.get(context);
        if (hVar == null) {
            hVar = new b.a.h<>();
            this.f282a.put(context, hVar);
        }
        hVar.a(i2, colorStateList);
    }

    private void d(@NonNull Context context) {
        if (this.f) {
            return;
        }
        this.f = true;
        Drawable drawableJ = j(context, R$drawable.abc_vector_test);
        if (drawableJ == null || !q(drawableJ)) {
            this.f = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    private static long e(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    private Drawable f(@NonNull Context context, @DrawableRes int i2) {
        if (this.e == null) {
            this.e = new TypedValue();
        }
        TypedValue typedValue = this.e;
        context.getResources().getValue(i2, typedValue, true);
        long jE = e(typedValue);
        Drawable drawableI = i(context, jE);
        if (drawableI != null) {
            return drawableI;
        }
        f fVar = this.g;
        Drawable drawableA = fVar == null ? null : fVar.a(this, context, i2);
        if (drawableA != null) {
            drawableA.setChangingConfigurations(typedValue.changingConfigurations);
            b(context, jE, drawableA);
        }
        return drawableA;
    }

    private static PorterDuffColorFilter g(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return l(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized J h() {
        if (i == null) {
            J j2 = new J();
            i = j2;
            p(j2);
        }
        return i;
    }

    private synchronized Drawable i(@NonNull Context context, long j2) {
        b.a.e<WeakReference<Drawable.ConstantState>> eVar = this.d.get(context);
        if (eVar == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> weakReferenceE = eVar.e(j2);
        if (weakReferenceE != null) {
            Drawable.ConstantState constantState = weakReferenceE.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            eVar.i(j2);
        }
        return null;
    }

    public static synchronized PorterDuffColorFilter l(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterI;
        porterDuffColorFilterI = j.i(i2, mode);
        if (porterDuffColorFilterI == null) {
            porterDuffColorFilterI = new PorterDuffColorFilter(i2, mode);
            j.j(i2, mode, porterDuffColorFilterI);
        }
        return porterDuffColorFilterI;
    }

    private ColorStateList n(@NonNull Context context, @DrawableRes int i2) {
        b.a.h<ColorStateList> hVar;
        WeakHashMap<Context, b.a.h<ColorStateList>> weakHashMap = this.f282a;
        if (weakHashMap == null || (hVar = weakHashMap.get(context)) == null) {
            return null;
        }
        return hVar.e(i2);
    }

    private static void p(@NonNull J j2) {
        if (Build.VERSION.SDK_INT < 24) {
            j2.a("vector", new g());
            j2.a("animated-vector", new b());
            j2.a("animated-selector", new a());
            j2.a("drawable", new d());
        }
    }

    private static boolean q(@NonNull Drawable drawable) {
        return (drawable instanceof androidx.vectordrawable.a.a.h) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    private Drawable r(@NonNull Context context, @DrawableRes int i2) {
        int next;
        b.a.g<String, e> gVar = this.f283b;
        if (gVar == null || gVar.isEmpty()) {
            return null;
        }
        b.a.h<String> hVar = this.f284c;
        if (hVar != null) {
            String strE = hVar.e(i2);
            if ("appcompat_skip_skip".equals(strE) || (strE != null && this.f283b.get(strE) == null)) {
                return null;
            }
        } else {
            this.f284c = new b.a.h<>();
        }
        if (this.e == null) {
            this.e = new TypedValue();
        }
        TypedValue typedValue = this.e;
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
                this.f284c.a(i2, name);
                e eVar = this.f283b.get(name);
                if (eVar != null) {
                    drawableI = eVar.a(context, xml, attributeSetAsAttributeSet, context.getTheme());
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
            this.f284c.a(i2, "appcompat_skip_skip");
        }
        return drawableI;
    }

    private Drawable v(@NonNull Context context, @DrawableRes int i2, boolean z, @NonNull Drawable drawable) {
        ColorStateList colorStateListM = m(context, i2);
        if (colorStateListM == null) {
            f fVar = this.g;
            if ((fVar == null || !fVar.d(context, i2, drawable)) && !x(context, i2, drawable) && z) {
                return null;
            }
            return drawable;
        }
        if (C.a(drawable)) {
            drawable = drawable.mutate();
        }
        Drawable drawableQ = androidx.core.graphics.drawable.a.q(drawable);
        androidx.core.graphics.drawable.a.n(drawableQ, colorStateListM);
        PorterDuff.Mode modeO = o(i2);
        if (modeO == null) {
            return drawableQ;
        }
        androidx.core.graphics.drawable.a.o(drawableQ, modeO);
        return drawableQ;
    }

    static void w(Drawable drawable, S s, int[] iArr) {
        if (C.a(drawable) && drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        if (s.d || s.f303c) {
            drawable.setColorFilter(g(s.d ? s.f301a : null, s.f303c ? s.f302b : h, iArr));
        } else {
            drawable.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    public synchronized Drawable j(@NonNull Context context, @DrawableRes int i2) {
        return k(context, i2, false);
    }

    synchronized Drawable k(@NonNull Context context, @DrawableRes int i2, boolean z) {
        Drawable drawableR;
        d(context);
        drawableR = r(context, i2);
        if (drawableR == null) {
            drawableR = f(context, i2);
        }
        if (drawableR == null) {
            drawableR = androidx.core.content.a.d(context, i2);
        }
        if (drawableR != null) {
            drawableR = v(context, i2, z, drawableR);
        }
        if (drawableR != null) {
            C.b(drawableR);
        }
        return drawableR;
    }

    synchronized ColorStateList m(@NonNull Context context, @DrawableRes int i2) {
        ColorStateList colorStateListN;
        colorStateListN = n(context, i2);
        if (colorStateListN == null) {
            colorStateListN = this.g == null ? null : this.g.c(context, i2);
            if (colorStateListN != null) {
                c(context, i2, colorStateListN);
            }
        }
        return colorStateListN;
    }

    PorterDuff.Mode o(int i2) {
        f fVar = this.g;
        if (fVar == null) {
            return null;
        }
        return fVar.e(i2);
    }

    public synchronized void s(@NonNull Context context) {
        b.a.e<WeakReference<Drawable.ConstantState>> eVar = this.d.get(context);
        if (eVar != null) {
            eVar.b();
        }
    }

    synchronized Drawable t(@NonNull Context context, @NonNull Z z, @DrawableRes int i2) {
        Drawable drawableR = r(context, i2);
        if (drawableR == null) {
            drawableR = z.c(i2);
        }
        if (drawableR == null) {
            return null;
        }
        return v(context, i2, false, drawableR);
    }

    public synchronized void u(f fVar) {
        this.g = fVar;
    }

    boolean x(@NonNull Context context, @DrawableRes int i2, @NonNull Drawable drawable) {
        f fVar = this.g;
        return fVar != null && fVar.b(context, i2, drawable);
    }
}
