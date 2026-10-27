package org.chromium.content.browser.accessibility;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content.browser.ContentViewCore;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class LollipopBrowserAccessibilityManager extends KitKatBrowserAccessibilityManager {
    @Override // org.chromium.content.browser.accessibility.BrowserAccessibilityManager
    protected void setAccessibilityEventCollectionInfo(AccessibilityEvent accessibilityEvent, int i, int i2, boolean z) {
    }

    @Override // org.chromium.content.browser.accessibility.BrowserAccessibilityManager
    protected void setAccessibilityEventCollectionItemInfo(AccessibilityEvent accessibilityEvent, int i, int i2, int i3, int i4) {
    }

    @Override // org.chromium.content.browser.accessibility.BrowserAccessibilityManager
    protected void setAccessibilityEventHeadingFlag(AccessibilityEvent accessibilityEvent, boolean z) {
    }

    @Override // org.chromium.content.browser.accessibility.BrowserAccessibilityManager
    protected void setAccessibilityEventLollipopAttributes(AccessibilityEvent accessibilityEvent, boolean z, boolean z2, boolean z3, boolean z4, int i, int i2) {
    }

    @Override // org.chromium.content.browser.accessibility.BrowserAccessibilityManager
    protected void setAccessibilityEventRangeInfo(AccessibilityEvent accessibilityEvent, int i, float f, float f2, float f3) {
    }

    LollipopBrowserAccessibilityManager(long j, ContentViewCore contentViewCore) {
        super(j, contentViewCore);
    }

    @Override // org.chromium.content.browser.accessibility.BrowserAccessibilityManager
    protected void setAccessibilityNodeInfoLollipopAttributes(AccessibilityNodeInfo accessibilityNodeInfo, boolean z, boolean z2, boolean z3, boolean z4, int i, int i2) {
        accessibilityNodeInfo.setCanOpenPopup(z);
        accessibilityNodeInfo.setContentInvalid(z2);
        accessibilityNodeInfo.setDismissable(z2);
        accessibilityNodeInfo.setMultiLine(z4);
        accessibilityNodeInfo.setInputType(i);
        accessibilityNodeInfo.setLiveRegion(i2);
    }

    @Override // org.chromium.content.browser.accessibility.BrowserAccessibilityManager
    protected void setAccessibilityNodeInfoCollectionInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2, boolean z) {
        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z));
    }

    @Override // org.chromium.content.browser.accessibility.BrowserAccessibilityManager
    protected void setAccessibilityNodeInfoCollectionItemInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2, int i3, int i4, boolean z) {
        accessibilityNodeInfo.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z));
    }

    @Override // org.chromium.content.browser.accessibility.BrowserAccessibilityManager
    protected void setAccessibilityNodeInfoRangeInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, float f, float f2, float f3) {
        accessibilityNodeInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(i, f, f2, f3));
    }

    @Override // org.chromium.content.browser.accessibility.BrowserAccessibilityManager
    protected void setAccessibilityNodeInfoViewIdResourceName(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        accessibilityNodeInfo.setViewIdResourceName(str);
    }

    @Override // org.chromium.content.browser.accessibility.BrowserAccessibilityManager
    protected void addAccessibilityNodeInfoActions(AccessibilityNodeInfo accessibilityNodeInfo, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11) {
        accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_NEXT_HTML_ELEMENT);
        accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_PREVIOUS_HTML_ELEMENT);
        accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
        accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
        if (z8 && z9) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_TEXT);
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_SELECTION);
        }
        if (z) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
        }
        if (z2) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
        }
        if (z10) {
            if (z11) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLEAR_FOCUS);
            } else {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_FOCUS);
            }
        }
        if (this.mAccessibilityFocusId == i) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        } else {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_ACCESSIBILITY_FOCUS);
        }
        if (z7) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
        }
    }
}
