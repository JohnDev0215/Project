package defpackage;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.material.R$styleable;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class oj {

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class a implements d {
        public final /* synthetic */ boolean a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ boolean c;
        public final /* synthetic */ d d;

        public a(boolean z, boolean z2, boolean z3, d dVar) {
            this.a = z;
            this.b = z2;
            this.c = z3;
            this.d = dVar;
        }

        @Override // oj.d
        public ba a(View view, ba baVar, e eVar) {
            if (this.a) {
                eVar.d += baVar.e();
            }
            boolean zH = oj.h(view);
            if (this.b) {
                if (zH) {
                    eVar.c += baVar.f();
                } else {
                    eVar.a += baVar.f();
                }
            }
            if (this.c) {
                if (zH) {
                    eVar.a += baVar.g();
                } else {
                    eVar.c += baVar.g();
                }
            }
            eVar.a(view);
            d dVar = this.d;
            return dVar != null ? dVar.a(view, baVar, eVar) : baVar;
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class b implements p9 {
        public final /* synthetic */ d a;
        public final /* synthetic */ e b;

        public b(d dVar, e eVar) {
            this.a = dVar;
            this.b = eVar;
        }

        @Override // defpackage.p9
        public ba a(View view, ba baVar) {
            return this.a.a(view, baVar, new e(this.b));
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class c implements View.OnAttachStateChangeListener {
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            view.removeOnAttachStateChangeListener(this);
            t9.g0(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public interface d {
        ba a(View view, ba baVar, e eVar);
    }

    public static void a(View view, AttributeSet attributeSet, int i, int i2, d dVar) {
        TypedArray typedArrayObtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R$styleable.Insets, i, i2);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingBottomSystemWindowInsets, false);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingLeftSystemWindowInsets, false);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingRightSystemWindowInsets, false);
        typedArrayObtainStyledAttributes.recycle();
        b(view, new a(z, z2, z3, dVar));
    }

    public static void b(View view, d dVar) {
        t9.v0(view, new b(dVar, new e(t9.E(view), view.getPaddingTop(), t9.D(view), view.getPaddingBottom())));
        j(view);
    }

    public static float c(Context context, int i) {
        return TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    public static ViewGroup d(View view) {
        if (view == null) {
            return null;
        }
        View rootView = view.getRootView();
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(R.id.content);
        if (viewGroup != null) {
            return viewGroup;
        }
        if (rootView == view || !(rootView instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) rootView;
    }

    public static nj e(View view) {
        return f(d(view));
    }

    public static nj f(View view) {
        if (view == null) {
            return null;
        }
        return Build.VERSION.SDK_INT >= 18 ? new mj(view) : lj.c(view);
    }

    public static float g(View view) {
        float fU = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            fU += t9.u((View) parent);
        }
        return fU;
    }

    public static boolean h(View view) {
        return t9.z(view) == 1;
    }

    public static PorterDuff.Mode i(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    public static void j(View view) {
        if (t9.O(view)) {
            t9.g0(view);
        } else {
            view.addOnAttachStateChangeListener(new c());
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class e {
        public int a;
        public int b;
        public int c;
        public int d;

        public e(int i, int i2, int i3, int i4) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }

        public void a(View view) {
            t9.w0(view, this.a, this.b, this.c, this.d);
        }

        public e(e eVar) {
            this.a = eVar.a;
            this.b = eVar.b;
            this.c = eVar.c;
            this.d = eVar.d;
        }
    }
}
