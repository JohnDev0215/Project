package p;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.datepicker.MaterialCalendar;
import com.spotify.music.R;

/* JADX INFO: loaded from: classes.dex */
public final class ff1 extends o2 {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ ff1(int i, Object obj) {
        this.d = i;
        this.e = obj;
    }

    @Override // p.o2
    public final void d(View view, c3 c3Var) {
        AccessibilityNodeInfo accessibilityNodeInfo = c3Var.a;
        int i = this.d;
        View.AccessibilityDelegate accessibilityDelegate = this.a;
        switch (i) {
            case 0:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.setCollectionInfo(null);
                break;
            case 1:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                MaterialCalendar materialCalendar = (MaterialCalendar) this.e;
                accessibilityNodeInfo.setHintText(materialCalendar.k0.getVisibility() == 0 ? materialCalendar.B().getResources().getString(R.string.mtrl_picker_toggle_to_year_selection) : materialCalendar.B().getResources().getString(R.string.mtrl_picker_toggle_to_day_selection));
                break;
            default:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.setCollectionInfo(null);
                break;
        }
    }
}
