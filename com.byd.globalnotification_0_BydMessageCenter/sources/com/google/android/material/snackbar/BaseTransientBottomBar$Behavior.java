package com.google.android.material.snackbar;

import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.d.a.a.f0.a;
import b.d.a.a.f0.d;
import b.d.a.a.f0.e;
import com.google.android.material.behavior.SwipeDismissBehavior;

/* JADX INFO: loaded from: classes.dex */
public class BaseTransientBottomBar$Behavior extends SwipeDismissBehavior<View> {
    public final a j = new a(this);

    @Override // com.google.android.material.behavior.SwipeDismissBehavior
    public boolean B(View view) {
        if (this.j != null) {
            return view instanceof d;
        }
        throw null;
    }

    @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean j(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (this.j == null) {
            throw null;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1 || actionMasked == 3) {
                if (e.f1889c == null) {
                    e.f1889c = new e();
                }
                synchronized (e.f1889c.f1890a) {
                }
            }
        } else if (coordinatorLayout.j(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
            if (e.f1889c == null) {
                e.f1889c = new e();
            }
            synchronized (e.f1889c.f1890a) {
            }
        }
        return super.j(coordinatorLayout, view, motionEvent);
    }
}
