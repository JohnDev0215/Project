package com.google.android.material.timepicker;

import B2.O;
import F1.C0243b;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.stingray.drivemodedetector.R;

/* JADX INFO: loaded from: classes.dex */
public final class c extends C0243b {
    public final /* synthetic */ ClockFaceView d;

    public c(ClockFaceView clockFaceView) {
        this.d = clockFaceView;
    }

    @Override // F1.C0243b
    public final void d(View view, G1.e eVar) {
        View.AccessibilityDelegate accessibilityDelegate = this.f2324a;
        AccessibilityNodeInfo accessibilityNodeInfo = eVar.f2646a;
        accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        int iIntValue = ((Integer) view.getTag(R.id.material_value_index)).intValue();
        if (iIntValue > 0) {
            accessibilityNodeInfo.setTraversalAfter((View) this.d.f12951H.get(iIntValue - 1));
        }
        eVar.j(O.f0(view.isSelected(), 0, 1, iIntValue, 1));
    }
}
