package com.google.android.material.behavior;

import F1.H;
import L1.d;
import N1.C0420a;
import N4.b;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import t1.AbstractC2425a;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends AbstractC2425a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f12747a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f12748b;
    public int c = 2;
    public float d = 0.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f12749e = 0.5f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b f12750f = new b(this);

    @Override // t1.AbstractC2425a
    public boolean f(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean zO = this.f12748b;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zO = coordinatorLayout.o(view, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.f12748b = zO;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f12748b = false;
        }
        if (!zO) {
            return false;
        }
        if (this.f12747a == null) {
            this.f12747a = new d(coordinatorLayout.getContext(), coordinatorLayout, this.f12750f);
        }
        return this.f12747a.p(motionEvent);
    }

    @Override // t1.AbstractC2425a
    public final boolean g(CoordinatorLayout coordinatorLayout, View view, int i7) {
        int[] iArr = H.f2303a;
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
            H.e(view, 1048576);
            H.c(view, 0);
            if (r(view)) {
                H.f(view, G1.d.l, new C0420a(this));
            }
        }
        return false;
    }

    @Override // t1.AbstractC2425a
    public final boolean q(View view, MotionEvent motionEvent) {
        d dVar = this.f12747a;
        if (dVar == null) {
            return false;
        }
        dVar.j(motionEvent);
        return true;
    }

    public boolean r(View view) {
        return true;
    }
}
