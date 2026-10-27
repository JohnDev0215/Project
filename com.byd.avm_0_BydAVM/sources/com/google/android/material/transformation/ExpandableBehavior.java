package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.g.k.t;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class ExpandableBehavior extends CoordinatorLayout.c<View> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2580a;

    class a implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f2581b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f2582c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ c.a.a.a.r.a f2583d;

        a(View view, int i, c.a.a.a.r.a aVar) {
            this.f2581b = view;
            this.f2582c = i;
            this.f2583d = aVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f2581b.getViewTreeObserver().removeOnPreDrawListener(this);
            if (ExpandableBehavior.this.f2580a == this.f2582c) {
                ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
                c.a.a.a.r.a aVar = this.f2583d;
                expandableBehavior.H((View) aVar, this.f2581b, aVar.a(), false);
            }
            return false;
        }
    }

    public ExpandableBehavior() {
        this.f2580a = 0;
    }

    private boolean F(boolean z) {
        if (!z) {
            return this.f2580a == 1;
        }
        int i = this.f2580a;
        return i == 0 || i == 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected c.a.a.a.r.a G(CoordinatorLayout coordinatorLayout, View view) {
        List<View> listV = coordinatorLayout.v(view);
        int size = listV.size();
        for (int i = 0; i < size; i++) {
            View view2 = listV.get(i);
            if (e(coordinatorLayout, view, view2)) {
                return (c.a.a.a.r.a) view2;
            }
        }
        return null;
    }

    protected abstract boolean H(View view, View view2, boolean z, boolean z2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
        c.a.a.a.r.a aVar = (c.a.a.a.r.a) view2;
        if (!F(aVar.a())) {
            return false;
        }
        this.f2580a = aVar.a() ? 1 : 2;
        return H((View) aVar, view, aVar.a(), true);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        c.a.a.a.r.a aVarG;
        if (t.P(view) || (aVarG = G(coordinatorLayout, view)) == null || !F(aVarG.a())) {
            return false;
        }
        int i2 = aVarG.a() ? 1 : 2;
        this.f2580a = i2;
        view.getViewTreeObserver().addOnPreDrawListener(new a(view, i2, aVarG));
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2580a = 0;
    }
}
