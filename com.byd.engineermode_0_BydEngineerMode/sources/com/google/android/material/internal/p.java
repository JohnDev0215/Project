package com.google.android.material.internal;

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
import b.g.k.b0;
import b.g.k.t;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: compiled from: ViewUtils.java */
    static class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f2842a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f2843b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f2844c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ d f2845d;

        a(boolean z, boolean z2, boolean z3, d dVar) {
            this.f2842a = z;
            this.f2843b = z2;
            this.f2844c = z3;
            this.f2845d = dVar;
        }

        @Override // com.google.android.material.internal.p.d
        public b0 a(View view, b0 b0Var, e eVar) {
            if (this.f2842a) {
                eVar.f2851d += b0Var.e();
            }
            boolean zH = p.h(view);
            if (this.f2843b) {
                if (zH) {
                    eVar.f2850c += b0Var.f();
                } else {
                    eVar.f2848a += b0Var.f();
                }
            }
            if (this.f2844c) {
                if (zH) {
                    eVar.f2848a += b0Var.g();
                } else {
                    eVar.f2850c += b0Var.g();
                }
            }
            eVar.a(view);
            d dVar = this.f2845d;
            return dVar != null ? dVar.a(view, b0Var, eVar) : b0Var;
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    static class b implements b.g.k.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f2846a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ e f2847b;

        b(d dVar, e eVar) {
            this.f2846a = dVar;
            this.f2847b = eVar;
        }

        @Override // b.g.k.p
        public b0 a(View view, b0 b0Var) {
            return this.f2846a.a(view, b0Var, new e(this.f2847b));
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    static class c implements View.OnAttachStateChangeListener {
        c() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            view.removeOnAttachStateChangeListener(this);
            t.g0(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public interface d {
        b0 a(View view, b0 b0Var, e eVar);
    }

    public static void a(View view, AttributeSet attributeSet, int i, int i2, d dVar) {
        TypedArray typedArrayObtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, c.b.a.a.l.H2, i, i2);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(c.b.a.a.l.I2, false);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(c.b.a.a.l.J2, false);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(c.b.a.a.l.K2, false);
        typedArrayObtainStyledAttributes.recycle();
        b(view, new a(z, z2, z3, dVar));
    }

    public static void b(View view, d dVar) {
        t.v0(view, new b(dVar, new e(t.E(view), view.getPaddingTop(), t.D(view), view.getPaddingBottom())));
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
        if (t.O(view)) {
            t.g0(view);
        } else {
            view.addOnAttachStateChangeListener(new c());
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2848a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2849b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2850c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2851d;

        public e(int i, int i2, int i3, int i4) {
            this.f2848a = i;
            this.f2849b = i2;
            this.f2850c = i3;
            this.f2851d = i4;
        }

        public void a(View view) {
            t.w0(view, this.f2848a, this.f2849b, this.f2850c, this.f2851d);
        }

        public e(e eVar) {
            this.f2848a = eVar.f2848a;
            this.f2849b = eVar.f2849b;
            this.f2850c = eVar.f2850c;
            this.f2851d = eVar.f2851d;
        }
    }
}
