package defpackage;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.ReceiverCallNotAllowedException;
import android.text.SpannableString;
import android.text.style.LocaleSpan;
import android.text.style.SuggestionSpan;
import android.util.SparseArray;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Locale;
import org.chromium.content.browser.accessibility.captioning.CaptioningController;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: gA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0813gA extends AbstractC0701eA {
    public static SparseArray Y = new SparseArray();
    public String W;
    public BroadcastReceiver X;

    public C0813gA(WebContents webContents) {
        super(webContents);
    }

    public final void D() {
        if (o()) {
            try {
                Is.a.registerReceiver(this.X, new IntentFilter("android.intent.action.LOCALE_CHANGED"));
            } catch (ReceiverCallNotAllowedException unused) {
            }
            this.W = Locale.getDefault().toLanguageTag();
        }
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void c(AccessibilityNodeInfo accessibilityNodeInfo, int i) {
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction = (AccessibilityNodeInfo.AccessibilityAction) Y.get(i);
        if (accessibilityAction == null) {
            accessibilityAction = new AccessibilityNodeInfo.AccessibilityAction(i, null);
            Y.put(i, accessibilityAction);
        }
        accessibilityNodeInfo.addAction(accessibilityAction);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [android.text.SpannableString] */
    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public CharSequence f(String str, boolean z, String str2, int[] iArr, int[] iArr2, String[] strArr) {
        CharSequence charSequenceF = super.f(str, z, str2, iArr, iArr2, strArr);
        ?? spannableString = charSequenceF;
        if (!str2.isEmpty()) {
            spannableString = charSequenceF;
            if (!str2.equals(this.W)) {
                SpannableString spannableString2 = charSequenceF instanceof SpannableString ? (SpannableString) charSequenceF : new SpannableString(charSequenceF);
                spannableString2.setSpan(new LocaleSpan(Locale.forLanguageTag(str2)), 0, spannableString2.length(), 0);
                spannableString = spannableString2;
            }
        }
        if (iArr != null && iArr.length > 0) {
            spannableString = spannableString instanceof SpannableString ? (SpannableString) spannableString : new SpannableString(spannableString);
            int length = spannableString.length();
            for (int i = 0; i < iArr.length; i++) {
                int i2 = iArr[i];
                int i3 = iArr2[i];
                if (i2 >= 0 && i2 <= length && i3 >= 0 && i3 <= length && i2 <= i3) {
                    spannableString.setSpan(new SuggestionSpan(this.u, new String[]{strArr[i]}, 2), i2, i3, 0);
                }
            }
        }
        return spannableString;
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl, defpackage.Yz
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        D();
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl, defpackage.Yz
    public void onDetachedFromWindow() {
        this.t.removeAccessibilityStateChangeListener(this);
        CaptioningController captioningController = this.I;
        captioningController.a.c(captioningController);
        if (o()) {
            Is.a.unregisterReceiver(this.X);
        }
    }

    @Override // defpackage.AbstractC0701eA, org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void s() {
        super.s();
        this.X = new C0757fA(this);
        if (this.B.isAttachedToWindow()) {
            D();
        }
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityEventCollectionInfo(AccessibilityEvent accessibilityEvent, int i, int i2, boolean z) {
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityEventCollectionItemInfo(AccessibilityEvent accessibilityEvent, int i, int i2, int i3, int i4) {
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityEventHeadingFlag(AccessibilityEvent accessibilityEvent, boolean z) {
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityEventLollipopAttributes(AccessibilityEvent accessibilityEvent, boolean z, boolean z2, boolean z3, boolean z4, int i, int i2) {
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityEventRangeInfo(AccessibilityEvent accessibilityEvent, int i, float f, float f2, float f3) {
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityNodeInfoCollectionInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2, boolean z) {
        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z));
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityNodeInfoCollectionItemInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2, int i3, int i4, boolean z) {
        accessibilityNodeInfo.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z));
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityNodeInfoLollipopAttributes(AccessibilityNodeInfo accessibilityNodeInfo, boolean z, boolean z2, boolean z3, boolean z4, int i, int i2, String str) {
        accessibilityNodeInfo.setCanOpenPopup(z);
        accessibilityNodeInfo.setContentInvalid(z2);
        accessibilityNodeInfo.setDismissable(z2);
        accessibilityNodeInfo.setMultiLine(z4);
        accessibilityNodeInfo.setInputType(i);
        accessibilityNodeInfo.setLiveRegion(i2);
        accessibilityNodeInfo.setError(str);
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityNodeInfoRangeInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, float f, float f2, float f3) {
        accessibilityNodeInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(i, f, f2, f3));
    }

    @Override // org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl
    public void setAccessibilityNodeInfoViewIdResourceName(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        accessibilityNodeInfo.setViewIdResourceName(str);
    }
}
