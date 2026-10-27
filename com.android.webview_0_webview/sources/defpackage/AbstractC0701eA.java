package defpackage;

import J.N;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Iterator;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: eA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0701eA extends WebContentsAccessibilityImpl {
    public String V;

    public AbstractC0701eA(WebContents webContents) {
        super(webContents);
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public int getAccessibilityServiceCapabilitiesMask() {
        Iterator<AccessibilityServiceInfo> it = this.t.getEnabledAccessibilityServiceList(-1).iterator();
        int capabilities = 0;
        while (it.hasNext()) {
            capabilities |= it.next().getCapabilities();
        }
        return capabilities;
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void s() {
        this.G = -1;
        this.H = -1;
        this.y = false;
        this.A = -1;
        this.V = N.MPyIoFYC(this.w, this);
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityNodeInfoKitKatAttributes(AccessibilityNodeInfo accessibilityNodeInfo, boolean z, boolean z2, String str, String str2, String str3, int i, int i2, boolean z3, boolean z4, String str4) {
        Bundle extras = accessibilityNodeInfo.getExtras();
        extras.putCharSequence("AccessibilityNodeInfo.chromeRole", str);
        extras.putCharSequence("AccessibilityNodeInfo.roleDescription", str2);
        extras.putCharSequence("AccessibilityNodeInfo.hint", str3);
        if (!str4.isEmpty()) {
            extras.putCharSequence("AccessibilityNodeInfo.targetUrl", str4);
        }
        if (z3) {
            extras.putCharSequence("AccessibilityNodeInfo.hasImage", "true");
        }
        if (z) {
            extras.putCharSequence("ACTION_ARGUMENT_HTML_ELEMENT_STRING_VALUES", this.V);
        }
        if (z2) {
            accessibilityNodeInfo.setEditable(true);
            accessibilityNodeInfo.setTextSelection(i, i2);
        }
        accessibilityNodeInfo.setContentInvalid(z4);
    }
}
