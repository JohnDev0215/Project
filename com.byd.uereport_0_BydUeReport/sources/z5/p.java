package z5;

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
import o0.b0;
import o0.t;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class p {

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f9610a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f9611b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f9612c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ d f9613d;

        public a(boolean z7, boolean z8, boolean z9, d dVar) {
            this.f9610a = z7;
            this.f9611b = z8;
            this.f9612c = z9;
            this.f9613d = dVar;
        }

        @Override // z5.p.d
        public b0 a(View view, b0 b0Var, e eVar) {
            if (this.f9610a) {
                eVar.f9619d += b0Var.e();
            }
            boolean zH = p.h(view);
            if (this.f9611b) {
                if (zH) {
                    eVar.f9618c += b0Var.f();
                } else {
                    eVar.f9616a += b0Var.f();
                }
            }
            if (this.f9612c) {
                if (zH) {
                    eVar.f9616a += b0Var.g();
                } else {
                    eVar.f9618c += b0Var.g();
                }
            }
            eVar.a(view);
            d dVar = this.f9613d;
            return dVar != null ? dVar.a(view, b0Var, eVar) : b0Var;
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class b implements o0.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d f9614a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ e f9615b;

        public b(d dVar, e eVar) {
            this.f9614a = dVar;
            this.f9615b = eVar;
        }

        @Override // o0.p
        public b0 a(View view, b0 b0Var) {
            return this.f9614a.a(view, b0Var, new e(this.f9615b));
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class c implements View.OnAttachStateChangeListener {
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            view.removeOnAttachStateChangeListener(this);
            t.i0(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public interface d {
        b0 a(View view, b0 b0Var, e eVar);
    }

    public static void a(View view, AttributeSet attributeSet, int i8, int i9, d dVar) {
        TypedArray typedArrayObtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R$styleable.Insets, i8, i9);
        boolean z7 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingBottomSystemWindowInsets, false);
        boolean z8 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingLeftSystemWindowInsets, false);
        boolean z9 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingRightSystemWindowInsets, false);
        typedArrayObtainStyledAttributes.recycle();
        b(view, new a(z7, z8, z9, dVar));
    }

    public static void b(View view, d dVar) {
        t.y0(view, new b(dVar, new e(t.F(view), view.getPaddingTop(), t.E(view), view.getPaddingBottom())));
        j(view);
    }

    public static float c(Context context, int i8) {
        return TypedValue.applyDimension(1, i8, context.getResources().getDisplayMetrics());
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

    public static o e(View view) {
        return f(d(view));
    }

    public static o f(View view) {
        if (view == null) {
            return null;
        }
        return Build.VERSION.SDK_INT >= 18 ? new n(view) : m.c(view);
    }

    public static float g(View view) {
        float fV = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            fV += t.v((View) parent);
        }
        return fV;
    }

    public static boolean h(View view) {
        return t.A(view) == 1;
    }

    public static PorterDuff.Mode i(int i8, PorterDuff.Mode mode) {
        if (i8 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i8 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i8 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i8) {
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
        if (t.P(view)) {
            t.i0(view);
        } else {
            view.addOnAttachStateChangeListener(new c());
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9616a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f9617b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f9618c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f9619d;

        public e(int i8, int i9, int i10, int i11) {
            this.f9616a = i8;
            this.f9617b = i9;
            this.f9618c = i10;
            this.f9619d = i11;
        }

        public void a(View view) {
            t.z0(view, this.f9616a, this.f9617b, this.f9618c, this.f9619d);
        }

        public e(e eVar) {
            this.f9616a = eVar.f9616a;
            this.f9617b = eVar.f9617b;
            this.f9618c = eVar.f9618c;
            this.f9619d = eVar.f9619d;
        }
    }
}
