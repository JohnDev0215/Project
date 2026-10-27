package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: renamed from: f3, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0750f3 {
    public static final View.AccessibilityDelegate b = new View.AccessibilityDelegate();
    public final View.AccessibilityDelegate a = new C0694e3(this);

    public void a(View view, AccessibilityEvent accessibilityEvent) {
        b.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void b(View view, C1525t3 c1525t3) {
        b.onInitializeAccessibilityNodeInfo(view, c1525t3.a);
    }

    public boolean c(View view, int i, Bundle bundle) {
        return b.performAccessibilityAction(view, i, bundle);
    }
}
