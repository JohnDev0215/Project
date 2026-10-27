package defpackage;

import J.N;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Arrays;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: hA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0869hA extends C0813gA {
    public C0869hA(WebContents webContents) {
        super(webContents);
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public void addExtraDataToAccessibilityNodeInfo(int i, AccessibilityNodeInfo accessibilityNodeInfo, String str, Bundle bundle) {
        int[] iArrMVBiMGvZ;
        if (str.equals("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY")) {
            if (!N.MZcfOSQW(this.w, this, i)) {
                N.M2WbOJ7$(this.w, this, i);
            }
            int i2 = bundle.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX", -1);
            int i3 = bundle.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH", -1);
            if (i3 <= 0 || i2 < 0 || (iArrMVBiMGvZ = N.MVBiMGvZ(this.w, this, i, i2, i3)) == null) {
                return;
            }
            RectF[] rectFArr = new RectF[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = i4 * 4;
                Rect rect = new Rect(iArrMVBiMGvZ[i5 + 0], iArrMVBiMGvZ[i5 + 1], iArrMVBiMGvZ[i5 + 2], iArrMVBiMGvZ[i5 + 3]);
                g(rect);
                rectFArr[i4] = new RectF(rect);
            }
            accessibilityNodeInfo.getExtras().putParcelableArray(str, rectFArr);
        }
    }

    @Override // defpackage.AbstractC0701eA, org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
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
        accessibilityNodeInfo.setHintText(str3);
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityNodeInfoOAttributes(AccessibilityNodeInfo accessibilityNodeInfo, boolean z) {
        if (z) {
            accessibilityNodeInfo.setAvailableExtraData(Arrays.asList("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY"));
        }
    }
}
