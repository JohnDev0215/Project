package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.transformation.FabTransformationBehavior;
import i0.u;
import java.util.HashMap;
import java.util.Map;
import o2.a;
import p2.h;
import p2.j;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FabTransformationSheetBehavior extends FabTransformationBehavior {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Map<View, Integer> f5008i;

    public FabTransformationSheetBehavior() {
    }

    private void g0(View view, boolean z6) {
        ViewParent parent = view.getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z6) {
                this.f5008i = new HashMap(childCount);
            }
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = coordinatorLayout.getChildAt(i7);
                boolean z7 = (childAt.getLayoutParams() instanceof CoordinatorLayout.f) && (((CoordinatorLayout.f) childAt.getLayoutParams()).f() instanceof FabTransformationScrimBehavior);
                if (childAt != view && !z7) {
                    if (z6) {
                        this.f5008i.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        u.t0(childAt, 4);
                    } else {
                        Map<View, Integer> map = this.f5008i;
                        if (map != null && map.containsKey(childAt)) {
                            u.t0(childAt, this.f5008i.get(childAt).intValue());
                        }
                    }
                }
            }
            if (z6) {
                return;
            }
            this.f5008i = null;
        }
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior, com.google.android.material.transformation.ExpandableBehavior
    protected boolean H(View view, View view2, boolean z6, boolean z7) {
        g0(view2, z6);
        return super.H(view, view2, z6, z7);
    }

    @Override // com.google.android.material.transformation.FabTransformationBehavior
    protected FabTransformationBehavior.e e0(Context context, boolean z6) {
        int i7 = z6 ? a.mtrl_fab_transformation_sheet_expand_spec : a.mtrl_fab_transformation_sheet_collapse_spec;
        FabTransformationBehavior.e eVar = new FabTransformationBehavior.e();
        eVar.f5001a = h.c(context, i7);
        eVar.f5002b = new j(17, 0.0f, 0.0f);
        return eVar;
    }

    public FabTransformationSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
