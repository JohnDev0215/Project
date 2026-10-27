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
    private int f4092a;

    class a implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f4093b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f4094c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ c.b.a.a.r.a f4095d;

        a(View view, int i, c.b.a.a.r.a aVar) {
            this.f4093b = view;
            this.f4094c = i;
            this.f4095d = aVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f4093b.getViewTreeObserver().removeOnPreDrawListener(this);
            if (ExpandableBehavior.this.f4092a == this.f4094c) {
                ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
                c.b.a.a.r.a aVar = this.f4095d;
                expandableBehavior.H((View) aVar, this.f4093b, aVar.a(), false);
            }
            return false;
        }
    }

    public ExpandableBehavior() {
        this.f4092a = 0;
    }

    private boolean F(boolean z) {
        if (!z) {
            return this.f4092a == 1;
        }
        int i = this.f4092a;
        return i == 0 || i == 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected c.b.a.a.r.a G(CoordinatorLayout coordinatorLayout, View view) {
        List<View> listV = coordinatorLayout.v(view);
        int size = listV.size();
        for (int i = 0; i < size; i++) {
            View view2 = listV.get(i);
            if (e(coordinatorLayout, view, view2)) {
                return (c.b.a.a.r.a) view2;
            }
        }
        return null;
    }

    protected abstract boolean H(View view, View view2, boolean z, boolean z2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
        c.b.a.a.r.a aVar = (c.b.a.a.r.a) view2;
        if (!F(aVar.a())) {
            return false;
        }
        this.f4092a = aVar.a() ? 1 : 2;
        return H((View) aVar, view, aVar.a(), true);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        c.b.a.a.r.a aVarG;
        if (t.P(view) || (aVarG = G(coordinatorLayout, view)) == null || !F(aVarG.a())) {
            return false;
        }
        int i2 = aVarG.a() ? 1 : 2;
        this.f4092a = i2;
        view.getViewTreeObserver().addOnPreDrawListener(new a(view, i2, aVarG));
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4092a = 0;
    }
}
