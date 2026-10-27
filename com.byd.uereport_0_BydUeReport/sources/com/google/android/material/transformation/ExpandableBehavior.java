package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.List;
import o0.t;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public abstract class ExpandableBehavior extends CoordinatorLayout.c<View> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4166a;

    public class a implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ View f4167d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f4168e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ x5.a f4169f;

        public a(View view, int i8, x5.a aVar) {
            this.f4167d = view;
            this.f4168e = i8;
            this.f4169f = aVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f4167d.getViewTreeObserver().removeOnPreDrawListener(this);
            if (ExpandableBehavior.this.f4166a == this.f4168e) {
                ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
                x5.a aVar = this.f4169f;
                expandableBehavior.H((View) aVar, this.f4167d, aVar.a(), false);
            }
            return false;
        }
    }

    public ExpandableBehavior() {
        this.f4166a = 0;
    }

    public final boolean F(boolean z7) {
        if (!z7) {
            return this.f4166a == 1;
        }
        int i8 = this.f4166a;
        return i8 == 0 || i8 == 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public x5.a G(CoordinatorLayout coordinatorLayout, View view) {
        List<View> listV = coordinatorLayout.v(view);
        int size = listV.size();
        for (int i8 = 0; i8 < size; i8++) {
            View view2 = listV.get(i8);
            if (e(coordinatorLayout, view, view2)) {
                return (x5.a) view2;
            }
        }
        return null;
    }

    public abstract boolean H(View view, View view2, boolean z7, boolean z8);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
        x5.a aVar = (x5.a) view2;
        if (!F(aVar.a())) {
            return false;
        }
        this.f4166a = aVar.a() ? 1 : 2;
        return H((View) aVar, view, aVar.a(), true);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i8) {
        x5.a aVarG;
        if (t.Q(view) || (aVarG = G(coordinatorLayout, view)) == null || !F(aVarG.a())) {
            return false;
        }
        int i9 = aVarG.a() ? 1 : 2;
        this.f4166a = i9;
        view.getViewTreeObserver().addOnPreDrawListener(new a(view, i9, aVarG));
        return false;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4166a = 0;
    }
}
