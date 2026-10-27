package defpackage;

import android.view.accessibility.AccessibilityManager;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: renamed from: cO, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AccessibilityManagerTouchExplorationStateChangeListenerC0604cO implements AccessibilityManager.TouchExplorationStateChangeListener {
    public final /* synthetic */ C0660dO a;

    public AccessibilityManagerTouchExplorationStateChangeListenerC0604cO(C0660dO c0660dO, WindowAndroid windowAndroid) {
        this.a = c0660dO;
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public void onTouchExplorationStateChanged(boolean z) {
        WindowAndroid windowAndroid = this.a.b;
        windowAndroid.A = windowAndroid.y.isTouchExplorationEnabled();
        this.a.b.W();
    }
}
