package defpackage;

import android.view.accessibility.AccessibilityManager;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: renamed from: dO, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0660dO {
    public AccessibilityManager.TouchExplorationStateChangeListener a;
    public final /* synthetic */ WindowAndroid b;

    public C0660dO(WindowAndroid windowAndroid) {
        this.b = windowAndroid;
        AccessibilityManagerTouchExplorationStateChangeListenerC0604cO accessibilityManagerTouchExplorationStateChangeListenerC0604cO = new AccessibilityManagerTouchExplorationStateChangeListenerC0604cO(this, windowAndroid);
        this.a = accessibilityManagerTouchExplorationStateChangeListenerC0604cO;
        windowAndroid.y.addTouchExplorationStateChangeListener(accessibilityManagerTouchExplorationStateChangeListenerC0604cO);
    }
}
