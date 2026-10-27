package defpackage;

import android.view.accessibility.AccessibilityNodeInfo;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: iA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0925iA extends C0869hA {
    public C0925iA(WebContents webContents) {
        super(webContents);
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityNodeInfoPaneTitle(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        accessibilityNodeInfo.setPaneTitle(str);
    }
}
