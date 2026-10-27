package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ScrollView;
import androidx.core.widget.NestedScrollView;

/* JADX INFO: renamed from: w3, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1690w3 extends AbstractC0750f3 {
    @Override // defpackage.AbstractC0750f3
    public void a(View view, AccessibilityEvent accessibilityEvent) {
        AbstractC0750f3.b.onInitializeAccessibilityEvent(view, accessibilityEvent);
        NestedScrollView nestedScrollView = (NestedScrollView) view;
        accessibilityEvent.setClassName(ScrollView.class.getName());
        accessibilityEvent.setScrollable(nestedScrollView.p() > 0);
        accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
        accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
        accessibilityEvent.setMaxScrollX(nestedScrollView.getScrollX());
        accessibilityEvent.setMaxScrollY(nestedScrollView.p());
    }

    @Override // defpackage.AbstractC0750f3
    public void b(View view, C1525t3 c1525t3) {
        int iP;
        super.b(view, c1525t3);
        NestedScrollView nestedScrollView = (NestedScrollView) view;
        c1525t3.a.setClassName(ScrollView.class.getName());
        if (!nestedScrollView.isEnabled() || (iP = nestedScrollView.p()) <= 0) {
            return;
        }
        c1525t3.a.setScrollable(true);
        if (nestedScrollView.getScrollY() > 0) {
            c1525t3.a.addAction(8192);
        }
        if (nestedScrollView.getScrollY() < iP) {
            c1525t3.a.addAction(4096);
        }
    }

    @Override // defpackage.AbstractC0750f3
    public boolean c(View view, int i, Bundle bundle) {
        if (super.c(view, i, bundle)) {
            return true;
        }
        NestedScrollView nestedScrollView = (NestedScrollView) view;
        if (!nestedScrollView.isEnabled()) {
            return false;
        }
        if (i == 4096) {
            int iMin = Math.min(nestedScrollView.getScrollY() + ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), nestedScrollView.p());
            if (iMin == nestedScrollView.getScrollY()) {
                return false;
            }
            nestedScrollView.y(0 - nestedScrollView.getScrollX(), iMin - nestedScrollView.getScrollY());
            return true;
        }
        if (i != 8192) {
            return false;
        }
        int iMax = Math.max(nestedScrollView.getScrollY() - ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
        if (iMax == nestedScrollView.getScrollY()) {
            return false;
        }
        nestedScrollView.y(0 - nestedScrollView.getScrollX(), iMax - nestedScrollView.getScrollY());
        return true;
    }
}
