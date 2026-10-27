package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.WeakHashMap;
import p.du2;
import p.hk0;
import p.it2;
import p.j00;
import p.uh2;
import p.w2;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends j00 {
    public du2 a;
    public boolean b;
    public int c = 2;
    public final float d = 0.5f;
    public float e = 0.0f;
    public float f = 0.5f;
    public final uh2 g = new uh2(this);

    @Override // p.j00
    public boolean f(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean zO = this.b;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zO = coordinatorLayout.o(view, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.b = zO;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.b = false;
        }
        if (!zO) {
            return false;
        }
        if (this.a == null) {
            this.a = new du2(coordinatorLayout.getContext(), coordinatorLayout, this.g);
        }
        return this.a.p(motionEvent);
    }

    @Override // p.j00
    public final boolean g(CoordinatorLayout coordinatorLayout, View view, int i) {
        WeakHashMap weakHashMap = zt2.a;
        if (it2.c(view) == 0) {
            it2.s(view, 1);
            zt2.h(view, 1048576);
            zt2.f(view, 0);
            if (r(view)) {
                zt2.i(view, w2.j, new hk0(16, this));
            }
        }
        return false;
    }

    @Override // p.j00
    public final boolean q(View view, MotionEvent motionEvent) {
        du2 du2Var = this.a;
        if (du2Var == null) {
            return false;
        }
        du2Var.j(motionEvent);
        return true;
    }

    public boolean r(View view) {
        return true;
    }
}
