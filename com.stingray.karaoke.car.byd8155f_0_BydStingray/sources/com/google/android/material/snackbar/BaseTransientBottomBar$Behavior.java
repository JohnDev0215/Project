package com.google.android.material.snackbar;

import X.e;
import Z4.d;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;
import io.sentry.hints.i;

/* JADX INFO: loaded from: classes.dex */
public class BaseTransientBottomBar$Behavior extends SwipeDismissBehavior<View> {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final i f12860g;

    public BaseTransientBottomBar$Behavior() {
        i iVar = new i(15);
        this.d = Math.min(Math.max(0.0f, 0.1f), 1.0f);
        this.f12749e = Math.min(Math.max(0.0f, 0.6f), 1.0f);
        this.c = 0;
        this.f12860g = iVar;
    }

    @Override // com.google.android.material.behavior.SwipeDismissBehavior, t1.AbstractC2425a
    public final boolean f(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        this.f12860g.getClass();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1 || actionMasked == 3) {
                if (e.f9951b == null) {
                    e.f9951b = new e();
                }
                synchronized (e.f9951b.f9952a) {
                }
            }
        } else if (coordinatorLayout.o(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
            if (e.f9951b == null) {
                e.f9951b = new e();
            }
            synchronized (e.f9951b.f9952a) {
            }
        }
        return super.f(coordinatorLayout, view, motionEvent);
    }

    @Override // com.google.android.material.behavior.SwipeDismissBehavior
    public final boolean r(View view) {
        this.f12860g.getClass();
        return view instanceof d;
    }
}
