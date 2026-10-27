package c.b.a.a.d0;

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
import android.view.inputmethod.InputMethodManager;
import b.h.m.b0;
import b.h.m.t;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f3802b;

        public a(View view) {
            this.f3802b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((InputMethodManager) this.f3802b.getContext().getSystemService("input_method")).showSoftInput(this.f3802b, 1);
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class b implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3803a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f3804b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f3805c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ e f3806d;

        public b(boolean z, boolean z2, boolean z3, e eVar) {
            this.f3803a = z;
            this.f3804b = z2;
            this.f3805c = z3;
            this.f3806d = eVar;
        }

        @Override // c.b.a.a.d0.p.e
        public b0 a(View view, b0 b0Var, f fVar) {
            if (this.f3803a) {
                fVar.f3812d += b0Var.e();
            }
            boolean zH = p.h(view);
            if (this.f3804b) {
                if (zH) {
                    fVar.f3811c += b0Var.f();
                } else {
                    fVar.f3809a += b0Var.f();
                }
            }
            if (this.f3805c) {
                if (zH) {
                    fVar.f3809a += b0Var.g();
                } else {
                    fVar.f3811c += b0Var.g();
                }
            }
            fVar.a(view);
            e eVar = this.f3806d;
            return eVar != null ? eVar.a(view, b0Var, fVar) : b0Var;
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class c implements b.h.m.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ e f3807a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ f f3808b;

        public c(e eVar, f fVar) {
            this.f3807a = eVar;
            this.f3808b = fVar;
        }

        @Override // b.h.m.p
        public b0 a(View view, b0 b0Var) {
            return this.f3807a.a(view, b0Var, new f(this.f3808b));
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class d implements View.OnAttachStateChangeListener {
        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            view.removeOnAttachStateChangeListener(this);
            t.h0(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public interface e {
        b0 a(View view, b0 b0Var, f fVar);
    }

    public static void a(View view, AttributeSet attributeSet, int i2, int i3, e eVar) {
        TypedArray typedArrayObtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, c.b.a.a.l.Insets, i2, i3);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(c.b.a.a.l.Insets_paddingBottomSystemWindowInsets, false);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(c.b.a.a.l.Insets_paddingLeftSystemWindowInsets, false);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(c.b.a.a.l.Insets_paddingRightSystemWindowInsets, false);
        typedArrayObtainStyledAttributes.recycle();
        b(view, new b(z, z2, z3, eVar));
    }

    public static void b(View view, e eVar) {
        t.x0(view, new c(eVar, new f(t.E(view), view.getPaddingTop(), t.D(view), view.getPaddingBottom())));
        j(view);
    }

    public static float c(Context context, int i2) {
        return TypedValue.applyDimension(1, i2, context.getResources().getDisplayMetrics());
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
        float fU = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            fU += t.u((View) parent);
        }
        return fU;
    }

    public static boolean h(View view) {
        return t.z(view) == 1;
    }

    public static PorterDuff.Mode i(int i2, PorterDuff.Mode mode) {
        if (i2 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i2 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i2 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i2) {
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
        if (t.O(view)) {
            t.h0(view);
        } else {
            view.addOnAttachStateChangeListener(new d());
        }
    }

    public static void k(View view) {
        view.requestFocus();
        view.post(new a(view));
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3809a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3810b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3811c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3812d;

        public f(int i2, int i3, int i4, int i5) {
            this.f3809a = i2;
            this.f3810b = i3;
            this.f3811c = i4;
            this.f3812d = i5;
        }

        public void a(View view) {
            t.y0(view, this.f3809a, this.f3810b, this.f3811c, this.f3812d);
        }

        public f(f fVar) {
            this.f3809a = fVar.f3809a;
            this.f3810b = fVar.f3810b;
            this.f3811c = fVar.f3811c;
            this.f3812d = fVar.f3812d;
        }
    }
}
