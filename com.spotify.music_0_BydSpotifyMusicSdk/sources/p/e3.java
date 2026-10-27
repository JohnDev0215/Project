package p;

import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class e3 extends d3 {
    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final AccessibilityNodeInfo findFocus(int i) {
        c3 c3VarU = this.a.u(i);
        if (c3VarU == null) {
            return null;
        }
        return c3VarU.a;
    }
}
