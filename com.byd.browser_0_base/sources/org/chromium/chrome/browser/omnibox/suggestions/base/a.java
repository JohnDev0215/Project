package org.chromium.chrome.browser.omnibox.suggestions.base;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import org.chromium.chrome.browser.omnibox.suggestions.base.d;

/* JADX INFO: compiled from: BaseSuggestionViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends View.AccessibilityDelegate {
    public final /* synthetic */ d.a a;
    public final /* synthetic */ ImageView b;

    public a(d.a aVar, ImageView imageView) {
        this.a = aVar;
        this.b = imageView;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        String str;
        if (i == 16 && (str = this.a.d) != null) {
            this.b.announceForAccessibility(str);
        }
        return super.performAccessibilityAction(view, i, bundle);
    }
}
