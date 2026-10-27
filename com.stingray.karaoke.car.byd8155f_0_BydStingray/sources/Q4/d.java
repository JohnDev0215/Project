package Q4;

import B2.O;
import F1.C0243b;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.NavigationMenuItemView;

/* JADX INFO: loaded from: classes.dex */
public final class d extends C0243b {
    public final /* synthetic */ int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ View f6238e;

    public /* synthetic */ d(View view, int i7) {
        this.d = i7;
        this.f6238e = view;
    }

    @Override // F1.C0243b
    public void c(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.d) {
            case 1:
                super.c(view, accessibilityEvent);
                accessibilityEvent.setChecked(((CheckableImageButton) this.f6238e).o);
                break;
            default:
                super.c(view, accessibilityEvent);
                break;
        }
    }

    @Override // F1.C0243b
    public final void d(View view, G1.e eVar) {
        View view2 = this.f6238e;
        View.AccessibilityDelegate accessibilityDelegate = this.f2324a;
        switch (this.d) {
            case 0:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, eVar.f2646a);
                int i7 = MaterialButtonToggleGroup.f12808v;
                MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) view2;
                materialButtonToggleGroup.getClass();
                int i10 = -1;
                if (view instanceof MaterialButton) {
                    int i11 = 0;
                    int i12 = 0;
                    while (true) {
                        if (i11 < materialButtonToggleGroup.getChildCount()) {
                            if (materialButtonToggleGroup.getChildAt(i11) == view) {
                                i10 = i12;
                            } else {
                                if ((materialButtonToggleGroup.getChildAt(i11) instanceof MaterialButton) && materialButtonToggleGroup.c(i11)) {
                                    i12++;
                                }
                                i11++;
                            }
                        }
                    }
                }
                eVar.j(O.f0(((MaterialButton) view).f12806y, 0, 1, i10, 1));
                break;
            case 1:
                AccessibilityNodeInfo accessibilityNodeInfo = eVar.f2646a;
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                CheckableImageButton checkableImageButton = (CheckableImageButton) view2;
                accessibilityNodeInfo.setCheckable(checkableImageButton.f12847p);
                accessibilityNodeInfo.setChecked(checkableImageButton.o);
                break;
            default:
                AccessibilityNodeInfo accessibilityNodeInfo2 = eVar.f2646a;
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo2);
                accessibilityNodeInfo2.setCheckable(((NavigationMenuItemView) view2).f12852I);
                break;
        }
    }
}
