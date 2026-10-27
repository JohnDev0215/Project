package defpackage;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import org.chromium.components.embedder_support.delegate.ColorPickerSimple;

/* JADX INFO: compiled from: ColorPickerSimple.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bv extends View.AccessibilityDelegate {
    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        int[] iArr = ColorPickerSimple.g;
        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(8, 1, false));
    }
}
