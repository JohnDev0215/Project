package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.in3;
import defpackage.mi0;
import defpackage.om3;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class ExpandableBehavior extends CoordinatorLayout.Behavior<View> {
    public int a;

    public class a implements ViewTreeObserver.OnPreDrawListener {
        public final /* synthetic */ View e;
        public final /* synthetic */ int f;
        public final /* synthetic */ mi0 g;

        public a(View view, int i, mi0 mi0Var) {
            this.e = view;
            this.f = i;
            this.g = mi0Var;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public final boolean onPreDraw() {
            View view = this.e;
            view.getViewTreeObserver().removeOnPreDrawListener(this);
            ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
            if (expandableBehavior.a == this.f) {
                mi0 mi0Var = this.g;
                expandableBehavior.s((View) mi0Var, view, mi0Var.a(), false);
            }
            return false;
        }
    }

    public ExpandableBehavior() {
        this.a = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public abstract boolean b(View view, View view2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean d(CoordinatorLayout coordinatorLayout, View view, View view2) {
        int i;
        mi0 mi0Var = (mi0) view2;
        if (!(!mi0Var.a() ? this.a != 1 : !((i = this.a) == 0 || i == 2))) {
            return false;
        }
        this.a = mi0Var.a() ? 1 : 2;
        s((View) mi0Var, view, mi0Var.a(), true);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean h(CoordinatorLayout coordinatorLayout, View view, int i) {
        mi0 mi0Var;
        int i2;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (!om3.g.c(view)) {
            List<View> listP = coordinatorLayout.p(view);
            int size = listP.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    mi0Var = null;
                    break;
                }
                View view2 = listP.get(i3);
                if (b(view, view2)) {
                    mi0Var = (mi0) view2;
                    break;
                }
                i3++;
            }
            if (mi0Var != null) {
                if (!mi0Var.a() ? this.a != 1 : !((i2 = this.a) == 0 || i2 == 2)) {
                    int i4 = mi0Var.a() ? 1 : 2;
                    this.a = i4;
                    view.getViewTreeObserver().addOnPreDrawListener(new a(view, i4, mi0Var));
                }
            }
        }
        return false;
    }

    public abstract void s(View view, View view2, boolean z, boolean z2);

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0;
    }
}
