package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import c.g.k.t;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class ExpandableBehavior extends CoordinatorLayout.c<View> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3773a;

    class a implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f3774b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f3775c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ d.b.a.a.r.a f3776d;

        a(View view, int i, d.b.a.a.r.a aVar) {
            this.f3774b = view;
            this.f3775c = i;
            this.f3776d = aVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f3774b.getViewTreeObserver().removeOnPreDrawListener(this);
            if (ExpandableBehavior.this.f3773a == this.f3775c) {
                ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
                d.b.a.a.r.a aVar = this.f3776d;
                expandableBehavior.H((View) aVar, this.f3774b, aVar.a(), false);
            }
            return false;
        }
    }

    public ExpandableBehavior() {
        this.f3773a = 0;
    }

    private boolean F(boolean z) {
        if (!z) {
            return this.f3773a == 1;
        }
        int i = this.f3773a;
        return i == 0 || i == 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected d.b.a.a.r.a G(CoordinatorLayout coordinatorLayout, View view) {
        List<View> listV = coordinatorLayout.v(view);
        int size = listV.size();
        for (int i = 0; i < size; i++) {
            View view2 = listV.get(i);
            if (e(coordinatorLayout, view, view2)) {
                return (d.b.a.a.r.a) view2;
            }
        }
        return null;
    }

    protected abstract boolean H(View view, View view2, boolean z, boolean z2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
        d.b.a.a.r.a aVar = (d.b.a.a.r.a) view2;
        if (!F(aVar.a())) {
            return false;
        }
        this.f3773a = aVar.a() ? 1 : 2;
        return H((View) aVar, view, aVar.a(), true);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        d.b.a.a.r.a aVarG;
        if (t.P(view) || (aVarG = G(coordinatorLayout, view)) == null || !F(aVarG.a())) {
            return false;
        }
        int i2 = aVarG.a() ? 1 : 2;
        this.f3773a = i2;
        view.getViewTreeObserver().addOnPreDrawListener(new a(view, i2, aVarG));
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3773a = 0;
    }
}
