package com.google.android.material.transformation;

import a.g.k.t;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class ExpandableBehavior extends CoordinatorLayout.c<View> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3259a;

    class a implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f3260b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f3261c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ b.a.a.a.v.a f3262d;

        a(View view, int i, b.a.a.a.v.a aVar) {
            this.f3260b = view;
            this.f3261c = i;
            this.f3262d = aVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f3260b.getViewTreeObserver().removeOnPreDrawListener(this);
            if (ExpandableBehavior.this.f3259a == this.f3261c) {
                ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
                b.a.a.a.v.a aVar = this.f3262d;
                expandableBehavior.H((View) aVar, this.f3260b, aVar.a(), false);
            }
            return false;
        }
    }

    public ExpandableBehavior() {
        this.f3259a = 0;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3259a = 0;
    }

    private boolean F(boolean z) {
        if (!z) {
            return this.f3259a == 1;
        }
        int i = this.f3259a;
        return i == 0 || i == 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected b.a.a.a.v.a G(CoordinatorLayout coordinatorLayout, View view) {
        List<View> listR = coordinatorLayout.r(view);
        int size = listR.size();
        for (int i = 0; i < size; i++) {
            View view2 = listR.get(i);
            if (e(coordinatorLayout, view, view2)) {
                return (b.a.a.a.v.a) view2;
            }
        }
        return null;
    }

    protected abstract boolean H(View view, View view2, boolean z, boolean z2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
        b.a.a.a.v.a aVar = (b.a.a.a.v.a) view2;
        if (!F(aVar.a())) {
            return false;
        }
        this.f3259a = aVar.a() ? 1 : 2;
        return H((View) aVar, view, aVar.a(), true);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        b.a.a.a.v.a aVarG;
        if (t.N(view) || (aVarG = G(coordinatorLayout, view)) == null || !F(aVarG.a())) {
            return false;
        }
        int i2 = aVarG.a() ? 1 : 2;
        this.f3259a = i2;
        view.getViewTreeObserver().addOnPreDrawListener(new a(view, i2, aVarG));
        return false;
    }
}
