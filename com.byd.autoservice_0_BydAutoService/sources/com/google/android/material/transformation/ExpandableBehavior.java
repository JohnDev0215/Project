package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.List;
import l0.t;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class ExpandableBehavior extends CoordinatorLayout.c<View> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2591a;

    public class a implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f2592b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f2593c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ g2.a f2594d;

        public a(View view, int i3, g2.a aVar) {
            this.f2592b = view;
            this.f2593c = i3;
            this.f2594d = aVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f2592b.getViewTreeObserver().removeOnPreDrawListener(this);
            if (ExpandableBehavior.this.f2591a == this.f2593c) {
                ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
                g2.a aVar = this.f2594d;
                expandableBehavior.H((View) aVar, this.f2592b, aVar.a(), false);
            }
            return false;
        }
    }

    public ExpandableBehavior() {
        this.f2591a = 0;
    }

    public final boolean F(boolean z3) {
        if (!z3) {
            return this.f2591a == 1;
        }
        int i3 = this.f2591a;
        return i3 == 0 || i3 == 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public g2.a G(CoordinatorLayout coordinatorLayout, View view) {
        List<View> listV = coordinatorLayout.v(view);
        int size = listV.size();
        for (int i3 = 0; i3 < size; i3++) {
            View view2 = listV.get(i3);
            if (e(coordinatorLayout, view, view2)) {
                return (g2.a) view2;
            }
        }
        return null;
    }

    public abstract boolean H(View view, View view2, boolean z3, boolean z4);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
        g2.a aVar = (g2.a) view2;
        if (!F(aVar.a())) {
            return false;
        }
        this.f2591a = aVar.a() ? 1 : 2;
        return H((View) aVar, view, aVar.a(), true);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i3) {
        g2.a aVarG;
        if (t.P(view) || (aVarG = G(coordinatorLayout, view)) == null || !F(aVarG.a())) {
            return false;
        }
        int i4 = aVarG.a() ? 1 : 2;
        this.f2591a = i4;
        view.getViewTreeObserver().addOnPreDrawListener(new a(view, i4, aVarG));
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2591a = 0;
    }
}
