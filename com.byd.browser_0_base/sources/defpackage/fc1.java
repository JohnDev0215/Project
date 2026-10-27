package defpackage;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$string;
import com.google.android.material.datepicker.a;

/* JADX INFO: compiled from: MaterialCalendar.java */
/* JADX INFO: loaded from: classes.dex */
public final class fc1 extends C0214v {
    public final /* synthetic */ a d;

    public fc1(a aVar) {
        this.d = aVar;
    }

    @Override // defpackage.C0214v
    public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        View.AccessibilityDelegate accessibilityDelegate = this.a;
        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.a;
        accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        a aVar = this.d;
        accessibilityNodeInfo.setHintText(aVar.j0.getVisibility() == 0 ? aVar.O(R$string.mtrl_picker_toggle_to_year_selection) : aVar.O(R$string.mtrl_picker_toggle_to_day_selection));
    }
}
