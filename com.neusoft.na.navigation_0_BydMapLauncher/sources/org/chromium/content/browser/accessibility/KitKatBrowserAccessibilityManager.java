package org.chromium.content.browser.accessibility;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.ContentViewCore;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class KitKatBrowserAccessibilityManager extends BrowserAccessibilityManager {
    private String mSupportedHtmlElementTypes;

    KitKatBrowserAccessibilityManager(long j, ContentViewCore contentViewCore) {
        super(j, contentViewCore);
        this.mSupportedHtmlElementTypes = nativeGetSupportedHtmlElementTypes(j);
    }

    @Override // org.chromium.content.browser.accessibility.BrowserAccessibilityManager
    protected void setAccessibilityNodeInfoKitKatAttributes(AccessibilityNodeInfo accessibilityNodeInfo, boolean z, boolean z2, String str) {
        Bundle extras = accessibilityNodeInfo.getExtras();
        extras.putCharSequence("AccessibilityNodeInfo.roleDescription", str);
        if (z) {
            extras.putCharSequence("ACTION_ARGUMENT_HTML_ELEMENT_STRING_VALUES", this.mSupportedHtmlElementTypes);
        }
        if (z2) {
            accessibilityNodeInfo.setEditable(true);
        }
    }
}
