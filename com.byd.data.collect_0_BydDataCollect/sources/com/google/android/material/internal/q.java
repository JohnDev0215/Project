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
import android.view.inputmethod.InputMethodManager;
import b.g.k.b0;
import b.g.k.t;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: compiled from: ViewUtils.java */
    static class a implements Runnable {

        /* JADX INFO: renamed from: b */
        final /* synthetic */ View f4179b;

        a(View view) {
            this.f4179b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((InputMethodManager) this.f4179b.getContext().getSystemService("input_method")).showSoftInput(this.f4179b, 1);
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    static class b implements e {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ boolean f4180a;

        /* JADX INFO: renamed from: b */
        final /* synthetic */ boolean f4181b;

        /* JADX INFO: renamed from: c */
        final /* synthetic */ boolean f4182c;

        /* JADX INFO: renamed from: d */
        final /* synthetic */ e f4183d;

        b(boolean z, boolean z2, boolean z3, e eVar) {
            this.f4180a = z;
            this.f4181b = z2;
            this.f4182c = z3;
            this.f4183d = eVar;
        }

        @Override // com.google.android.material.internal.q.e
        public b0 a(View view, b0 b0Var, f fVar) {
            if (this.f4180a) {
                fVar.f4189d += b0Var.f();
            }
            boolean zH = q.h(view);
            if (this.f4181b) {
                if (zH) {
                    fVar.f4188c += b0Var.g();
                } else {
                    fVar.f4186a += b0Var.g();
                }
            }
            if (this.f4182c) {
                if (zH) {
                    fVar.f4186a += b0Var.h();
                } else {
                    fVar.f4188c += b0Var.h();
                }
            }
            fVar.a(view);
            e eVar = this.f4183d;
            return eVar != null ? eVar.a(view, b0Var, fVar) : b0Var;
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    static class c implements b.g.k.p {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ e f4184a;

        /* JADX INFO: renamed from: b */
        final /* synthetic */ f f4185b;

        c(e eVar, f fVar) {
            this.f4184a = eVar;
            this.f4185b = fVar;
        }

        @Override // b.g.k.p
        public b0 a(View view, b0 b0Var) {
            return this.f4184a.a(view, b0Var, new f(this.f4185b));
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    static class d implements View.OnAttachStateChangeListener {
        d() {
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
    public interface e {
        b0 a(View view, b0 b0Var, f fVar);
    }

    public static void a(View view, AttributeSet attributeSet, int i2, int i3, e eVar) {
        TypedArray typedArrayObtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, d.b.a.a.l.Insets, i2, i3);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(d.b.a.a.l.Insets_paddingBottomSystemWindowInsets, false);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(d.b.a.a.l.Insets_paddingLeftSystemWindowInsets, false);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(d.b.a.a.l.Insets_paddingRightSystemWindowInsets, false);
        typedArrayObtainStyledAttributes.recycle();
        b(view, new b(z, z2, z3, eVar));
    }

    public static void b(View view, e eVar) {
        t.v0(view, new c(eVar, new f(t.E(view), view.getPaddingTop(), t.D(view), view.getPaddingBottom())));
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

    public static p e(View view) {
        return f(d(view));
    }

    public static p f(View view) {
        if (view == null) {
            return null;
        }
        return Build.VERSION.SDK_INT >= 18 ? new o(view) : n.c(view);
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
            t.g0(view);
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

        /* JADX INFO: renamed from: a */
        public int f4186a;

        /* JADX INFO: renamed from: b */
        public int f4187b;

        /* JADX INFO: renamed from: c */
        public int f4188c;

        /* JADX INFO: renamed from: d */
        public int f4189d;

        public f(int i2, int i3, int i4, int i5) {
            this.f4186a = i2;
            this.f4187b = i3;
            this.f4188c = i4;
            this.f4189d = i5;
        }

        public void a(View view) {
            t.w0(view, this.f4186a, this.f4187b, this.f4188c, this.f4189d);
        }

        public f(f fVar) {
            this.f4186a = fVar.f4186a;
            this.f4187b = fVar.f4187b;
            this.f4188c = fVar.f4188c;
            this.f4189d = fVar.f4189d;
        }
    }
}
