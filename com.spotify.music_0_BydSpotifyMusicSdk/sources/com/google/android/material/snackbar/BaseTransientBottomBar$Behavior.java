package com.google.android.material.snackbar;

import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;
import p.gh2;
import p.kl;
import p.p01;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public class BaseTransientBottomBar$Behavior extends SwipeDismissBehavior<View> {
    public final p01 h = new p01((SwipeDismissBehavior) this);

    @Override // com.google.android.material.behavior.SwipeDismissBehavior, p.j00
    public final boolean f(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        p01 p01Var = this.h;
        p01Var.getClass();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1 || actionMasked == 3) {
                if (gh2.n == null) {
                    gh2.n = new gh2(8);
                }
                gh2 gh2Var = gh2.n;
                u4.z(p01Var.e);
                synchronized (gh2Var.e) {
                    u4.z(gh2Var.l);
                }
            }
        } else if (coordinatorLayout.o(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
            if (gh2.n == null) {
                gh2.n = new gh2(8);
            }
            gh2 gh2Var2 = gh2.n;
            u4.z(p01Var.e);
            synchronized (gh2Var2.e) {
                u4.z(gh2Var2.l);
            }
        }
        return super.f(coordinatorLayout, view, motionEvent);
    }

    @Override // com.google.android.material.behavior.SwipeDismissBehavior
    public final boolean r(View view) {
        this.h.getClass();
        return view instanceof kl;
    }
}
