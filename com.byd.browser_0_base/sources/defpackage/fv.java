package defpackage;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import org.chromium.components.embedder_support.delegate.ColorSuggestion;
import org.chromium.components.embedder_support.delegate.a;

/* JADX INFO: compiled from: ColorSuggestionListAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fv extends View.AccessibilityDelegate {
    public final /* synthetic */ int a;
    public final /* synthetic */ ColorSuggestion b;
    public final /* synthetic */ a c;

    public fv(a aVar, int i, ColorSuggestion colorSuggestion) {
        this.c = aVar;
        this.a = i;
        this.b = colorSuggestion;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain(this.a, 1, 1, 1, false));
        accessibilityNodeInfo.setSelected(this.b.a == this.c.h);
    }
}
