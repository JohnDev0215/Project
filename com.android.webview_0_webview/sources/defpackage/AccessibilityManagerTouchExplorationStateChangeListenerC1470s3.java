package defpackage;

import android.view.accessibility.AccessibilityManager;

/* JADX INFO: renamed from: s3, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AccessibilityManagerTouchExplorationStateChangeListenerC1470s3 implements AccessibilityManager.TouchExplorationStateChangeListener {
    public final InterfaceC1415r3 a;

    public AccessibilityManagerTouchExplorationStateChangeListenerC1470s3(InterfaceC1415r3 interfaceC1415r3) {
        this.a = interfaceC1415r3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AccessibilityManagerTouchExplorationStateChangeListenerC1470s3.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((AccessibilityManagerTouchExplorationStateChangeListenerC1470s3) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public void onTouchExplorationStateChanged(boolean z) {
        AbstractC0154Ik abstractC0154Ik = ((C0136Hk) this.a).a;
        abstractC0154Ik.setClickable(!z);
        abstractC0154Ik.setFocusable(z);
    }
}
