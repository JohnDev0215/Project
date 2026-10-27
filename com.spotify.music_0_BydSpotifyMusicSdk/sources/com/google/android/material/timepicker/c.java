package com.google.android.material.timepicker;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.spotify.music.R;
import p.b3;
import p.c3;
import p.o2;

/* JADX INFO: loaded from: classes.dex */
public final class c extends o2 {
    public final /* synthetic */ ClockFaceView d;

    public c(ClockFaceView clockFaceView) {
        this.d = clockFaceView;
    }

    @Override // p.o2
    public final void d(View view, c3 c3Var) {
        View.AccessibilityDelegate accessibilityDelegate = this.a;
        AccessibilityNodeInfo accessibilityNodeInfo = c3Var.a;
        accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        int iIntValue = ((Integer) view.getTag(R.id.material_value_index)).intValue();
        if (iIntValue > 0) {
            accessibilityNodeInfo.setTraversalAfter((View) this.d.E.get(iIntValue - 1));
        }
        c3Var.h(b3.a(0, 1, iIntValue, 1, view.isSelected()));
    }
}
