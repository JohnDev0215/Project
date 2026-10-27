package com.google.android.material.internal;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import i0.c0;
import i0.p;
import i0.u;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: compiled from: ViewUtils.java */
    static class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ View f4666e;

        a(View view) {
            this.f4666e = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((InputMethodManager) this.f4666e.getContext().getSystemService("input_method")).showSoftInput(this.f4666e, 1);
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    static class b implements p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f4667a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ e f4668b;

        b(d dVar, e eVar) {
            this.f4667a = dVar;
            this.f4668b = eVar;
        }

        @Override // i0.p
        public c0 a(View view, c0 c0Var) {
            return this.f4667a.a(view, c0Var, new e(this.f4668b));
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    static class c implements View.OnAttachStateChangeListener {
        c() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            view.removeOnAttachStateChangeListener(this);
            u.h0(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public interface d {
        c0 a(View view, c0 c0Var, e eVar);
    }

    public static void a(View view, d dVar) {
        u.v0(view, new b(dVar, new e(u.E(view), view.getPaddingTop(), u.D(view), view.getPaddingBottom())));
        f(view);
    }

    public static float b(Context context, int i7) {
        return TypedValue.applyDimension(1, i7, context.getResources().getDisplayMetrics());
    }

    public static float c(View view) {
        float fT = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            fT += u.t((View) parent);
        }
        return fT;
    }

    public static boolean d(View view) {
        return u.y(view) == 1;
    }

    public static PorterDuff.Mode e(int i7, PorterDuff.Mode mode) {
        if (i7 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i7 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i7 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i7) {
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

    public static void f(View view) {
        if (u.O(view)) {
            u.h0(view);
        } else {
            view.addOnAttachStateChangeListener(new c());
        }
    }

    public static void g(View view) {
        view.requestFocus();
        view.post(new a(view));
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4669a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4670b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f4671c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f4672d;

        public e(int i7, int i8, int i9, int i10) {
            this.f4669a = i7;
            this.f4670b = i8;
            this.f4671c = i9;
            this.f4672d = i10;
        }

        public e(e eVar) {
            this.f4669a = eVar.f4669a;
            this.f4670b = eVar.f4670b;
            this.f4671c = eVar.f4671c;
            this.f4672d = eVar.f4672d;
        }
    }
}
