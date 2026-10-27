package com.google.android.material.transformation;

import a.h.k.q;
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
    public int f2494a;

    public class a implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f2495a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2496b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ b.d.a.a.x.a f2497c;

        public a(View view, int i, b.d.a.a.x.a aVar) {
            this.f2495a = view;
            this.f2496b = i;
            this.f2497c = aVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f2495a.getViewTreeObserver().removeOnPreDrawListener(this);
            ExpandableBehavior expandableBehavior = ExpandableBehavior.this;
            if (expandableBehavior.f2494a == this.f2496b) {
                b.d.a.a.x.a aVar = this.f2497c;
                expandableBehavior.C((View) aVar, this.f2495a, aVar.a(), false);
            }
            return false;
        }
    }

    public ExpandableBehavior() {
        this.f2494a = 0;
    }

    public ExpandableBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2494a = 0;
    }

    public final boolean B(boolean z) {
        int i = this.f2494a;
        return z ? i == 0 || i == 2 : i == 1;
    }

    public abstract boolean C(View view, View view2, boolean z, boolean z2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean g(CoordinatorLayout coordinatorLayout, View view, View view2) {
        b.d.a.a.x.a aVar = (b.d.a.a.x.a) view2;
        if (!B(aVar.a())) {
            return false;
        }
        this.f2494a = aVar.a() ? 1 : 2;
        return C((View) aVar, view, aVar.a(), true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, View view, int i) {
        b.d.a.a.x.a aVar;
        if (!q.C(view)) {
            List<View> listD = coordinatorLayout.d(view);
            int size = listD.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    aVar = null;
                    break;
                }
                View view2 = listD.get(i2);
                if (d(coordinatorLayout, view, view2)) {
                    aVar = (b.d.a.a.x.a) view2;
                    break;
                }
                i2++;
            }
            if (aVar != null && B(aVar.a())) {
                int i3 = aVar.a() ? 1 : 2;
                this.f2494a = i3;
                view.getViewTreeObserver().addOnPreDrawListener(new a(view, i3, aVar));
            }
        }
        return false;
    }
}
