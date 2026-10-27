package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import i0.u;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class ExpandableBehavior extends CoordinatorLayout.c<View> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4977a;

    class a implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ View f4978e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ int f4979g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ y2.a f4980h;

        a(View view, int i7, y2.a aVar) {
            this.f4978e = view;
            this.f4979g = i7;
            this.f4980h = aVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f4978e.getViewTreeObserver().removeOnPreDrawListener(this);
            if (ExpandableBehavior.this.f4977a == this.f4979g) {
                ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
                y2.a aVar = this.f4980h;
                expandableBehavior.H((View) aVar, this.f4978e, aVar.a(), false);
            }
            return false;
        }
    }

    public ExpandableBehavior() {
        this.f4977a = 0;
    }

    private boolean F(boolean z6) {
        if (!z6) {
            return this.f4977a == 1;
        }
        int i7 = this.f4977a;
        return i7 == 0 || i7 == 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected y2.a G(CoordinatorLayout coordinatorLayout, View view) {
        List<View> listR = coordinatorLayout.r(view);
        int size = listR.size();
        for (int i7 = 0; i7 < size; i7++) {
            View view2 = listR.get(i7);
            if (e(coordinatorLayout, view, view2)) {
                return (y2.a) view2;
            }
        }
        return null;
    }

    protected abstract boolean H(View view, View view2, boolean z6, boolean z7);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public abstract boolean e(CoordinatorLayout coordinatorLayout, View view, View view2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
        y2.a aVar = (y2.a) view2;
        if (!F(aVar.a())) {
            return false;
        }
        this.f4977a = aVar.a() ? 1 : 2;
        return H((View) aVar, view, aVar.a(), true);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i7) {
        y2.a aVarG;
        if (u.P(view) || (aVarG = G(coordinatorLayout, view)) == null || !F(aVarG.a())) {
            return false;
        }
        int i8 = aVarG.a() ? 1 : 2;
        this.f4977a = i8;
        view.getViewTreeObserver().addOnPreDrawListener(new a(view, i8, aVarG));
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4977a = 0;
    }
}
