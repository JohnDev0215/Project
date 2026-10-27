package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.g.j.t;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class ExpandableBehavior extends CoordinatorLayout.c<View> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2275a;

    public class a implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f2276b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f2277c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ c.b.a.a.o.a f2278d;

        public a(View view, int i, c.b.a.a.o.a aVar) {
            this.f2276b = view;
            this.f2277c = i;
            this.f2278d = aVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f2276b.getViewTreeObserver().removeOnPreDrawListener(this);
            if (ExpandableBehavior.this.f2275a == this.f2277c) {
                ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
                c.b.a.a.o.a aVar = this.f2278d;
                expandableBehavior.H((View) aVar, this.f2276b, aVar.a(), false);
            }
            return false;
        }
    }

    public ExpandableBehavior() {
        this.f2275a = 0;
    }

    public final boolean F(boolean z) {
        if (!z) {
            return this.f2275a == 1;
        }
        int i = this.f2275a;
        return i == 0 || i == 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public c.b.a.a.o.a G(CoordinatorLayout coordinatorLayout, View view) {
        List<View> listV = coordinatorLayout.v(view);
        int size = listV.size();
        for (int i = 0; i < size; i++) {
            View view2 = listV.get(i);
            if (e(coordinatorLayout, view, view2)) {
                return (c.b.a.a.o.a) view2;
            }
        }
        return null;
    }

    public abstract boolean H(View view, View view2, boolean z, boolean z2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
        c.b.a.a.o.a aVar = (c.b.a.a.o.a) view2;
        if (!F(aVar.a())) {
            return false;
        }
        this.f2275a = aVar.a() ? 1 : 2;
        return H((View) aVar, view, aVar.a(), true);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        c.b.a.a.o.a aVarG;
        if (t.P(view) || (aVarG = G(coordinatorLayout, view)) == null || !F(aVarG.a())) {
            return false;
        }
        int i2 = aVarG.a() ? 1 : 2;
        this.f2275a = i2;
        view.getViewTreeObserver().addOnPreDrawListener(new a(view, i2, aVarG));
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2275a = 0;
    }
}
