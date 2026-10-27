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
    private int f4373a;

    class a implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f4374b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f4375c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ d.b.a.a.s.a f4376d;

        a(View view, int i2, d.b.a.a.s.a aVar) {
            this.f4374b = view;
            this.f4375c = i2;
            this.f4376d = aVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f4374b.getViewTreeObserver().removeOnPreDrawListener(this);
            if (ExpandableBehavior.this.f4373a == this.f4375c) {
                ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
                d.b.a.a.s.a aVar = this.f4376d;
                expandableBehavior.H((View) aVar, this.f4374b, aVar.a(), false);
            }
            return false;
        }
    }

    public ExpandableBehavior() {
        this.f4373a = 0;
    }

    private boolean F(boolean z) {
        if (!z) {
            return this.f4373a == 1;
        }
        int i2 = this.f4373a;
        return i2 == 0 || i2 == 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected d.b.a.a.s.a G(CoordinatorLayout coordinatorLayout, View view) {
        List<View> listV = coordinatorLayout.v(view);
        int size = listV.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view2 = listV.get(i2);
            if (e(coordinatorLayout, view, view2)) {
                return (d.b.a.a.s.a) view2;
            }
        }
        return null;
    }

    protected abstract boolean H(View view, View view2, boolean z, boolean z2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
        d.b.a.a.s.a aVar = (d.b.a.a.s.a) view2;
        if (!F(aVar.a())) {
            return false;
        }
        this.f4373a = aVar.a() ? 1 : 2;
        return H((View) aVar, view, aVar.a(), true);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i2) {
        d.b.a.a.s.a aVarG;
        if (t.P(view) || (aVarG = G(coordinatorLayout, view)) == null || !F(aVarG.a())) {
            return false;
        }
        int i3 = aVarG.a() ? 1 : 2;
        this.f4373a = i3;
        view.getViewTreeObserver().addOnPreDrawListener(new a(view, i3, aVarG));
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4373a = 0;
    }
}
