package com.google.android.material.transformation;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R$animator;
import com.google.android.material.transformation.FabTransformationBehavior;
import java.util.HashMap;
import java.util.Map;
import l0.t;
import s1.h;
import s1.j;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FabTransformationSheetBehavior extends FabTransformationBehavior {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Map<View, Integer> f2622i;

    public FabTransformationSheetBehavior() {
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior, com.google.android.material.transformation.ExpandableBehavior
    public boolean H(View view, View view2, boolean z3, boolean z4) {
        g0(view2, z3);
        return super.H(view, view2, z3, z4);
    }

    @Override // com.google.android.material.transformation.FabTransformationBehavior
    public FabTransformationBehavior.e e0(Context context, boolean z3) {
        int i3 = z3 ? R$animator.mtrl_fab_transformation_sheet_expand_spec : R$animator.mtrl_fab_transformation_sheet_collapse_spec;
        FabTransformationBehavior.e eVar = new FabTransformationBehavior.e();
        eVar.f2615a = h.d(context, i3);
        eVar.f2616b = new j(17, 0.0f, 0.0f);
        return eVar;
    }

    public final void g0(View view, boolean z3) {
        ViewParent parent = view.getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (Build.VERSION.SDK_INT >= 16 && z3) {
                this.f2622i = new HashMap(childCount);
            }
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = coordinatorLayout.getChildAt(i3);
                boolean z4 = (childAt.getLayoutParams() instanceof CoordinatorLayout.f) && (((CoordinatorLayout.f) childAt.getLayoutParams()).f() instanceof FabTransformationScrimBehavior);
                if (childAt != view && !z4) {
                    if (z3) {
                        if (Build.VERSION.SDK_INT >= 16) {
                            this.f2622i.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        }
                        t.t0(childAt, 4);
                    } else {
                        Map<View, Integer> map = this.f2622i;
                        if (map != null && map.containsKey(childAt)) {
                            t.t0(childAt, this.f2622i.get(childAt).intValue());
                        }
                    }
                }
            }
            if (z3) {
                return;
            }
            this.f2622i = null;
        }
    }

    public FabTransformationSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
