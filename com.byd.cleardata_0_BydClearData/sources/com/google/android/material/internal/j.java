package com.google.android.material.internal;

import a.g.k.b0;
import a.g.k.p;
import a.g.k.t;
import android.content.Context;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes.dex */
public class j {

    static class a implements p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c f3155a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ d f3156b;

        a(c cVar, d dVar) {
            this.f3155a = cVar;
            this.f3156b = dVar;
        }

        @Override // a.g.k.p
        public b0 a(View view, b0 b0Var) {
            return this.f3155a.a(view, b0Var, new d(this.f3156b));
        }
    }

    static class b implements View.OnAttachStateChangeListener {
        b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            view.removeOnAttachStateChangeListener(this);
            t.e0(view);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    public interface c {
        b0 a(View view, b0 b0Var, d dVar);
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3157a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3158b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3159c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3160d;

        public d(int i, int i2, int i3, int i4) {
            this.f3157a = i;
            this.f3158b = i2;
            this.f3159c = i3;
            this.f3160d = i4;
        }

        public d(d dVar) {
            this.f3157a = dVar.f3157a;
            this.f3158b = dVar.f3158b;
            this.f3159c = dVar.f3159c;
            this.f3160d = dVar.f3160d;
        }
    }

    public static void a(View view, c cVar) {
        t.s0(view, new a(cVar, new d(t.D(view), view.getPaddingTop(), t.C(view), view.getPaddingBottom())));
        f(view);
    }

    public static float b(Context context, int i) {
        return TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    public static float c(View view) {
        float fT = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            fT += t.t((View) parent);
        }
        return fT;
    }

    public static boolean d(View view) {
        return t.y(view) == 1;
    }

    public static PorterDuff.Mode e(int i, PorterDuff.Mode mode) {
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

    public static void f(View view) {
        if (t.M(view)) {
            t.e0(view);
        } else {
            view.addOnAttachStateChangeListener(new b());
        }
    }
}
