package I1;

import F1.C0243b;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ScrollView;
import androidx.core.widget.NestedScrollView;

/* JADX INFO: loaded from: classes.dex */
public final class e extends C0243b {
    public final /* synthetic */ int d;

    public /* synthetic */ e(int i7) {
        this.d = i7;
    }

    @Override // F1.C0243b
    public void c(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.d) {
            case 0:
                super.c(view, accessibilityEvent);
                NestedScrollView nestedScrollView = (NestedScrollView) view;
                accessibilityEvent.setClassName(ScrollView.class.getName());
                accessibilityEvent.setScrollable(nestedScrollView.getScrollRange() > 0);
                accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
                accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
                accessibilityEvent.setMaxScrollX(nestedScrollView.getScrollX());
                accessibilityEvent.setMaxScrollY(nestedScrollView.getScrollRange());
                break;
            default:
                super.c(view, accessibilityEvent);
                break;
        }
    }

    @Override // F1.C0243b
    public final void d(View view, G1.e eVar) {
        int scrollRange;
        switch (this.d) {
            case 0:
                View.AccessibilityDelegate accessibilityDelegate = this.f2324a;
                AccessibilityNodeInfo accessibilityNodeInfo = eVar.f2646a;
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                NestedScrollView nestedScrollView = (NestedScrollView) view;
                eVar.h("android.widget.ScrollView");
                if (nestedScrollView.isEnabled() && (scrollRange = nestedScrollView.getScrollRange()) > 0) {
                    accessibilityNodeInfo.setScrollable(true);
                    if (nestedScrollView.getScrollY() > 0) {
                        eVar.b(G1.d.f2638i);
                        eVar.b(G1.d.f2641m);
                    }
                    if (nestedScrollView.getScrollY() < scrollRange) {
                        eVar.b(G1.d.f2637h);
                        eVar.b(G1.d.o);
                    }
                    break;
                }
                break;
            default:
                this.f2324a.onInitializeAccessibilityNodeInfo(view, eVar.f2646a);
                eVar.i(null);
                break;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0076  */
    @Override // F1.C0243b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean g(android.view.View r4, int r5, android.os.Bundle r6) {
        /*
            r3 = this;
            int r0 = r3.d
            switch(r0) {
                case 0: goto La;
                default: goto L5;
            }
        L5:
            boolean r3 = super.g(r4, r5, r6)
            return r3
        La:
            boolean r3 = super.g(r4, r5, r6)
            r6 = 1
            if (r3 == 0) goto L13
            goto La0
        L13:
            androidx.core.widget.NestedScrollView r4 = (androidx.core.widget.NestedScrollView) r4
            boolean r3 = r4.isEnabled()
            r0 = 0
            if (r3 != 0) goto L1f
        L1c:
            r6 = r0
            goto La0
        L1f:
            int r3 = r4.getHeight()
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            android.graphics.Matrix r2 = r4.getMatrix()
            boolean r2 = r2.isIdentity()
            if (r2 == 0) goto L3c
            boolean r2 = r4.getGlobalVisibleRect(r1)
            if (r2 == 0) goto L3c
            int r3 = r1.height()
        L3c:
            r1 = 4096(0x1000, float:5.74E-42)
            if (r5 == r1) goto L76
            r1 = 8192(0x2000, float:1.148E-41)
            if (r5 == r1) goto L4f
            r1 = 16908344(0x1020038, float:2.3877386E-38)
            if (r5 == r1) goto L4f
            r1 = 16908346(0x102003a, float:2.3877392E-38)
            if (r5 == r1) goto L76
            goto L1c
        L4f:
            int r5 = r4.getPaddingBottom()
            int r3 = r3 - r5
            int r5 = r4.getPaddingTop()
            int r3 = r3 - r5
            int r5 = r4.getScrollY()
            int r5 = r5 - r3
            int r3 = java.lang.Math.max(r5, r0)
            int r5 = r4.getScrollY()
            if (r3 == r5) goto L1c
            int r5 = r4.getScrollX()
            int r0 = r0 - r5
            int r5 = r4.getScrollY()
            int r3 = r3 - r5
            r4.t(r0, r3, r6)
            goto La0
        L76:
            int r5 = r4.getPaddingBottom()
            int r3 = r3 - r5
            int r5 = r4.getPaddingTop()
            int r3 = r3 - r5
            int r5 = r4.getScrollY()
            int r5 = r5 + r3
            int r3 = r4.getScrollRange()
            int r3 = java.lang.Math.min(r5, r3)
            int r5 = r4.getScrollY()
            if (r3 == r5) goto L1c
            int r5 = r4.getScrollX()
            int r0 = r0 - r5
            int r5 = r4.getScrollY()
            int r3 = r3 - r5
            r4.t(r0, r3, r6)
        La0:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: I1.e.g(android.view.View, int, android.os.Bundle):boolean");
    }
}
