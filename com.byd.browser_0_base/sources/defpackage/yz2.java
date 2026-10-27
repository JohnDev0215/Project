package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* JADX INFO: compiled from: TabGridDialogView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yz2 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabGridDialogView e;

    public yz2(TabGridDialogView tabGridDialogView) {
        this.e = tabGridDialogView;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        TabGridDialogView tabGridDialogView = this.e;
        tabGridDialogView.u = null;
        tabGridDialogView.o.requestFocus();
        tabGridDialogView.o.sendAccessibilityEvent(8);
        ViewGroup viewGroup = (ViewGroup) tabGridDialogView.getParent();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != tabGridDialogView) {
                tabGridDialogView.F.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                childAt.setImportantForAccessibility(4);
            }
        }
    }
}
