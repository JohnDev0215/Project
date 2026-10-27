package defpackage;

import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: renamed from: z, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccessibilityManagerCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class AccessibilityManagerTouchExplorationStateChangeListenerC0222z implements AccessibilityManager.TouchExplorationStateChangeListener {
    public final InterfaceC0220y a;

    public AccessibilityManagerTouchExplorationStateChangeListenerC0222z(InterfaceC0220y interfaceC0220y) {
        this.a = interfaceC0220y;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AccessibilityManagerTouchExplorationStateChangeListenerC0222z) {
            return this.a.equals(((AccessibilityManagerTouchExplorationStateChangeListenerC0222z) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public final void onTouchExplorationStateChanged(boolean z) {
        pe0 pe0Var = (pe0) ((ke0) this.a).f;
        AutoCompleteTextView autoCompleteTextView = pe0Var.e;
        if (autoCompleteTextView != null) {
            if (autoCompleteTextView.getInputType() != 0) {
                return;
            }
            int i = z ? 2 : 1;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.p(pe0Var.d, i);
        }
    }
}
