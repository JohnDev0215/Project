package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
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
public final class i0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static i0 f1090i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakHashMap<Context, n.h<ColorStateList>> f1092a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private n.g<String, b> f1093b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private n.h<String> f1094c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final WeakHashMap<Context, n.d<WeakReference<Drawable.ConstantState>>> f1095d = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TypedValue f1096e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f1097f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private c f1098g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final PorterDuff.Mode f1089h = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final a f1091j = new a(6);

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    private static class a extends n.e<Integer, PorterDuffColorFilter> {
        public a(int i7) {
            super(i7);
        }

        private static int h(int i7, PorterDuff.Mode mode) {
            return ((i7 + 31) * 31) + mode.hashCode();
        }

        PorterDuffColorFilter i(int i7, PorterDuff.Mode mode) {
            return c(Integer.valueOf(h(i7, mode)));
        }

        PorterDuffColorFilter j(int i7, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return d(Integer.valueOf(h(i7, mode)), porterDuffColorFilter);
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    private interface b {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public interface c {
        Drawable a(i0 i0Var, Context context, int i7);

        boolean b(Context context, int i7, Drawable drawable);

        ColorStateList c(Context context, int i7);

        boolean d(Context context, int i7, Drawable drawable);

        PorterDuff.Mode e(int i7);
    }

    private synchronized boolean a(Context context, long j7, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        n.d<WeakReference<Drawable.ConstantState>> dVar = this.f1095d.get(context);
        if (dVar == null) {
            dVar = new n.d<>();
            this.f1095d.put(context, dVar);
        }
        dVar.j(j7, new WeakReference<>(constantState));
        return true;
    }

    private void b(Context context, int i7, ColorStateList colorStateList) {
        if (this.f1092a == null) {
            this.f1092a = new WeakHashMap<>();
        }
        n.h<ColorStateList> hVar = this.f1092a.get(context);
        if (hVar == null) {
            hVar = new n.h<>();
            this.f1092a.put(context, hVar);
        }
        hVar.a(i7, colorStateList);
    }

    private void c(Context context) {
        if (this.f1097f) {
            return;
        }
        this.f1097f = true;
        Drawable drawableI = i(context, h.a.abc_vector_test);
        if (drawableI == null || !p(drawableI)) {
            this.f1097f = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    private static long d(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    private Drawable e(Context context, int i7) {
        if (this.f1096e == null) {
            this.f1096e = new TypedValue();
        }
        TypedValue typedValue = this.f1096e;
        context.getResources().getValue(i7, typedValue, true);
        long jD = d(typedValue);
        Drawable drawableH = h(context, jD);
        if (drawableH != null) {
            return drawableH;
        }
        c cVar = this.f1098g;
        Drawable drawableA = cVar == null ? null : cVar.a(this, context, i7);
        if (drawableA != null) {
            drawableA.setChangingConfigurations(typedValue.changingConfigurations);
            a(context, jD, drawableA);
        }
        return drawableA;
    }

    private static PorterDuffColorFilter f(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return k(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized i0 g() {
        if (f1090i == null) {
            i0 i0Var = new i0();
            f1090i = i0Var;
            o(i0Var);
        }
        return f1090i;
    }

    private synchronized Drawable h(Context context, long j7) {
        n.d<WeakReference<Drawable.ConstantState>> dVar = this.f1095d.get(context);
        if (dVar == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> weakReferenceE = dVar.e(j7);
        if (weakReferenceE != null) {
            Drawable.ConstantState constantState = weakReferenceE.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            dVar.k(j7);
        }
        return null;
    }

    public static synchronized PorterDuffColorFilter k(int i7, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterI;
        a aVar = f1091j;
        porterDuffColorFilterI = aVar.i(i7, mode);
        if (porterDuffColorFilterI == null) {
            porterDuffColorFilterI = new PorterDuffColorFilter(i7, mode);
            aVar.j(i7, mode, porterDuffColorFilterI);
        }
        return porterDuffColorFilterI;
    }

    private ColorStateList m(Context context, int i7) {
        n.h<ColorStateList> hVar;
        WeakHashMap<Context, n.h<ColorStateList>> weakHashMap = this.f1092a;
        if (weakHashMap == null || (hVar = weakHashMap.get(context)) == null) {
            return null;
        }
        return hVar.e(i7);
    }

    private static void o(i0 i0Var) {
    }

    private static boolean p(Drawable drawable) {
        return (drawable instanceof f1.c) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    private Drawable q(Context context, int i7) {
        int next;
        n.g<String, b> gVar = this.f1093b;
        if (gVar == null || gVar.isEmpty()) {
            return null;
        }
        n.h<String> hVar = this.f1094c;
        if (hVar != null) {
            String strE = hVar.e(i7);
            if ("appcompat_skip_skip".equals(strE) || (strE != null && this.f1093b.get(strE) == null)) {
                return null;
            }
        } else {
            this.f1094c = new n.h<>();
        }
        if (this.f1096e == null) {
            this.f1096e = new TypedValue();
        }
        TypedValue typedValue = this.f1096e;
        Resources resources = context.getResources();
        resources.getValue(i7, typedValue, true);
        long jD = d(typedValue);
        Drawable drawableH = h(context, jD);
        if (drawableH != null) {
            return drawableH;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i7);
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
                this.f1094c.a(i7, name);
                b bVar = this.f1093b.get(name);
                if (bVar != null) {
                    drawableH = bVar.a(context, xml, attributeSetAsAttributeSet, context.getTheme());
                }
                if (drawableH != null) {
                    drawableH.setChangingConfigurations(typedValue.changingConfigurations);
                    a(context, jD, drawableH);
                }
            } catch (Exception e7) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e7);
            }
        }
        if (drawableH == null) {
            this.f1094c.a(i7, "appcompat_skip_skip");
        }
        return drawableH;
    }

    private Drawable u(Context context, int i7, boolean z6, Drawable drawable) {
        ColorStateList colorStateListL = l(context, i7);
        if (colorStateListL == null) {
            c cVar = this.f1098g;
            if ((cVar == null || !cVar.d(context, i7, drawable)) && !w(context, i7, drawable) && z6) {
                return null;
            }
            return drawable;
        }
        if (b0.a(drawable)) {
            drawable = drawable.mutate();
        }
        Drawable drawableL = a0.a.l(drawable);
        a0.a.i(drawableL, colorStateListL);
        PorterDuff.Mode modeN = n(i7);
        if (modeN == null) {
            return drawableL;
        }
        a0.a.j(drawableL, modeN);
        return drawableL;
    }

    static void v(Drawable drawable, q0 q0Var, int[] iArr) {
        if (b0.a(drawable) && drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        boolean z6 = q0Var.f1179d;
        if (z6 || q0Var.f1178c) {
            drawable.setColorFilter(f(z6 ? q0Var.f1176a : null, q0Var.f1178c ? q0Var.f1177b : f1089h, iArr));
        } else {
            drawable.clearColorFilter();
        }
    }

    public synchronized Drawable i(Context context, int i7) {
        return j(context, i7, false);
    }

    synchronized Drawable j(Context context, int i7, boolean z6) {
        Drawable drawableQ;
        c(context);
        drawableQ = q(context, i7);
        if (drawableQ == null) {
            drawableQ = e(context, i7);
        }
        if (drawableQ == null) {
            drawableQ = x.a.c(context, i7);
        }
        if (drawableQ != null) {
            drawableQ = u(context, i7, z6, drawableQ);
        }
        if (drawableQ != null) {
            b0.b(drawableQ);
        }
        return drawableQ;
    }

    synchronized ColorStateList l(Context context, int i7) {
        ColorStateList colorStateListM;
        colorStateListM = m(context, i7);
        if (colorStateListM == null) {
            c cVar = this.f1098g;
            colorStateListM = cVar == null ? null : cVar.c(context, i7);
            if (colorStateListM != null) {
                b(context, i7, colorStateListM);
            }
        }
        return colorStateListM;
    }

    PorterDuff.Mode n(int i7) {
        c cVar = this.f1098g;
        if (cVar == null) {
            return null;
        }
        return cVar.e(i7);
    }

    public synchronized void r(Context context) {
        n.d<WeakReference<Drawable.ConstantState>> dVar = this.f1095d.get(context);
        if (dVar != null) {
            dVar.a();
        }
    }

    synchronized Drawable s(Context context, v0 v0Var, int i7) {
        Drawable drawableQ = q(context, i7);
        if (drawableQ == null) {
            drawableQ = v0Var.c(i7);
        }
        if (drawableQ == null) {
            return null;
        }
        return u(context, i7, false, drawableQ);
    }

    public synchronized void t(c cVar) {
        this.f1098g = cVar;
    }

    boolean w(Context context, int i7, Drawable drawable) {
        c cVar = this.f1098g;
        return cVar != null && cVar.b(context, i7, drawable);
    }
}
