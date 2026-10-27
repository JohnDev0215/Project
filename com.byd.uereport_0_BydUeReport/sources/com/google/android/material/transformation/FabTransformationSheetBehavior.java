package com.google.android.material.transformation;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R$animator;
import com.google.android.material.transformation.FabTransformationBehavior;
import j5.h;
import j5.j;
import java.util.HashMap;
import java.util.Map;
import o0.t;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class FabTransformationSheetBehavior extends FabTransformationBehavior {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Map<View, Integer> f4197i;

    public FabTransformationSheetBehavior() {
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior, com.google.android.material.transformation.ExpandableBehavior
    public boolean H(View view, View view2, boolean z7, boolean z8) {
        g0(view2, z7);
        return super.H(view, view2, z7, z8);
    }

    @Override // com.google.android.material.transformation.FabTransformationBehavior
    public FabTransformationBehavior.e e0(Context context, boolean z7) {
        int i8 = z7 ? R$animator.mtrl_fab_transformation_sheet_expand_spec : R$animator.mtrl_fab_transformation_sheet_collapse_spec;
        FabTransformationBehavior.e eVar = new FabTransformationBehavior.e();
        eVar.f4190a = h.d(context, i8);
        eVar.f4191b = new j(17, 0.0f, 0.0f);
        return eVar;
    }

    public final void g0(View view, boolean z7) {
        ViewParent parent = view.getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (Build.VERSION.SDK_INT >= 16 && z7) {
                this.f4197i = new HashMap(childCount);
            }
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = coordinatorLayout.getChildAt(i8);
                boolean z8 = (childAt.getLayoutParams() instanceof CoordinatorLayout.f) && (((CoordinatorLayout.f) childAt.getLayoutParams()).f() instanceof FabTransformationScrimBehavior);
                if (childAt != view && !z8) {
                    if (z7) {
                        if (Build.VERSION.SDK_INT >= 16) {
                            this.f4197i.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        }
                        t.v0(childAt, 4);
                    } else {
                        Map<View, Integer> map = this.f4197i;
                        if (map != null && map.containsKey(childAt)) {
                            t.v0(childAt, this.f4197i.get(childAt).intValue());
                        }
                    }
                }
            }
            if (z7) {
                return;
            }
            this.f4197i = null;
        }
    }

    public FabTransformationSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
