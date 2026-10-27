package p;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.NavigationMenuItemView;

/* JADX INFO: loaded from: classes.dex */
public final class cf1 extends o2 {
    public final /* synthetic */ int d;
    public final /* synthetic */ View e;

    public /* synthetic */ cf1(View view, int i) {
        this.d = i;
        this.e = view;
    }

    @Override // p.o2
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.d) {
            case 1:
                super.c(view, accessibilityEvent);
                accessibilityEvent.setChecked(((CheckableImageButton) this.e).isChecked());
                break;
            default:
                super.c(view, accessibilityEvent);
                break;
        }
    }

    @Override // p.o2
    public final void d(View view, c3 c3Var) {
        AccessibilityNodeInfo accessibilityNodeInfo = c3Var.a;
        int i = this.d;
        View view2 = this.e;
        View.AccessibilityDelegate accessibilityDelegate = this.a;
        switch (i) {
            case 0:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) view2;
                int i2 = MaterialButtonToggleGroup.s;
                materialButtonToggleGroup.getClass();
                int i3 = -1;
                if (view instanceof MaterialButton) {
                    int i4 = 0;
                    int i5 = 0;
                    while (true) {
                        if (i4 < materialButtonToggleGroup.getChildCount()) {
                            if (materialButtonToggleGroup.getChildAt(i4) == view) {
                                i3 = i5;
                            } else {
                                if ((materialButtonToggleGroup.getChildAt(i4) instanceof MaterialButton) && materialButtonToggleGroup.c(i4)) {
                                    i5++;
                                }
                                i4++;
                            }
                        }
                    }
                }
                c3Var.h(b3.a(0, 1, i3, 1, ((MaterialButton) view).isChecked()));
                break;
            case 1:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                CheckableImageButton checkableImageButton = (CheckableImageButton) view2;
                accessibilityNodeInfo.setCheckable(checkableImageButton.m);
                accessibilityNodeInfo.setChecked(checkableImageButton.isChecked());
                break;
            default:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.setCheckable(((NavigationMenuItemView) view2).F);
                break;
        }
    }
}
