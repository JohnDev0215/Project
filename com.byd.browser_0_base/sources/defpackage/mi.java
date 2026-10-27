package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/* JADX INFO: compiled from: BottomSheetDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class mi extends C0214v {
    public final /* synthetic */ BottomSheetDialog d;

    public mi(BottomSheetDialog bottomSheetDialog) {
        this.d = bottomSheetDialog;
    }

    @Override // defpackage.C0214v
    public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        View.AccessibilityDelegate accessibilityDelegate = this.a;
        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.a;
        accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        if (!this.d.n) {
            accessibilityNodeInfo.setDismissable(false);
        } else {
            accessibilityNodeInfoCompat.a(1048576);
            accessibilityNodeInfo.setDismissable(true);
        }
    }

    @Override // defpackage.C0214v
    public final boolean g(View view, int i, Bundle bundle) {
        if (i == 1048576) {
            BottomSheetDialog bottomSheetDialog = this.d;
            if (bottomSheetDialog.n) {
                bottomSheetDialog.cancel();
                return true;
            }
        }
        return super.g(view, i, bundle);
    }
}
