package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.h.m.t;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class ExpandableBehavior extends CoordinatorLayout.c<View> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4898a;

    public class a implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f4899b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f4900c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ c.b.a.a.b0.a f4901d;

        public a(View view, int i2, c.b.a.a.b0.a aVar) {
            this.f4899b = view;
            this.f4900c = i2;
            this.f4901d = aVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f4899b.getViewTreeObserver().removeOnPreDrawListener(this);
            if (ExpandableBehavior.this.f4898a == this.f4900c) {
                ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
                c.b.a.a.b0.a aVar = this.f4901d;
                expandableBehavior.H((View) aVar, this.f4899b, aVar.a(), false);
            }
            return false;
        }
    }

    public ExpandableBehavior() {
        this.f4898a = 0;
    }

    public final boolean F(boolean z) {
        if (!z) {
            return this.f4898a == 1;
        }
        int i2 = this.f4898a;
        return i2 == 0 || i2 == 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public c.b.a.a.b0.a G(CoordinatorLayout coordinatorLayout, View view) {
        List<View> listV = coordinatorLayout.v(view);
        int size = listV.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view2 = listV.get(i2);
            if (e(coordinatorLayout, view, view2)) {
                return (c.b.a.a.b0.a) view2;
            }
        }
        return null;
    }

    public abstract boolean H(View view, View view2, boolean z, boolean z2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
        c.b.a.a.b0.a aVar = (c.b.a.a.b0.a) view2;
        if (!F(aVar.a())) {
            return false;
        }
        this.f4898a = aVar.a() ? 1 : 2;
        return H((View) aVar, view, aVar.a(), true);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i2) {
        c.b.a.a.b0.a aVarG;
        if (t.P(view) || (aVarG = G(coordinatorLayout, view)) == null || !F(aVarG.a())) {
            return false;
        }
        int i3 = aVarG.a() ? 1 : 2;
        this.f4898a = i3;
        view.getViewTreeObserver().addOnPreDrawListener(new a(view, i3, aVarG));
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4898a = 0;
    }
}
