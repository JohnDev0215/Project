package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.Objects;

/* JADX INFO: renamed from: e3, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0694e3 extends View.AccessibilityDelegate {
    public final AbstractC0750f3 a;

    public C0694e3(AbstractC0750f3 abstractC0750f3) {
        this.a = abstractC0750f3;
    }

    @Override // android.view.View.AccessibilityDelegate
    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        Objects.requireNonNull(this.a);
        return AbstractC0750f3.b.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
        Objects.requireNonNull(this.a);
        AccessibilityNodeProvider accessibilityNodeProvider = AbstractC0750f3.b.getAccessibilityNodeProvider(view);
        C1580u3 c1580u3 = accessibilityNodeProvider != null ? new C1580u3(accessibilityNodeProvider) : null;
        if (c1580u3 != null) {
            return (AccessibilityNodeProvider) c1580u3.a;
        }
        return null;
    }

    @Override // android.view.View.AccessibilityDelegate
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.a.a(view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        this.a.b(view, new C1525t3(accessibilityNodeInfo));
    }

    @Override // android.view.View.AccessibilityDelegate
    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        Objects.requireNonNull(this.a);
        AbstractC0750f3.b.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        Objects.requireNonNull(this.a);
        return AbstractC0750f3.b.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        return this.a.c(view, i, bundle);
    }

    @Override // android.view.View.AccessibilityDelegate
    public void sendAccessibilityEvent(View view, int i) {
        Objects.requireNonNull(this.a);
        AbstractC0750f3.b.sendAccessibilityEvent(view, i);
    }

    @Override // android.view.View.AccessibilityDelegate
    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        Objects.requireNonNull(this.a);
        AbstractC0750f3.b.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
