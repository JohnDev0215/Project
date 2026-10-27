package org.chromium.chrome.browser.tasks.tab_management;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* JADX INFO: compiled from: TabGridDialogView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n extends AnimatorListenerAdapter {
    public final /* synthetic */ TabGridDialogView e;

    public n(TabGridDialogView tabGridDialogView) {
        this.e = tabGridDialogView;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        HashMap map;
        TabGridDialogView tabGridDialogView = this.e;
        tabGridDialogView.setVisibility(8);
        tabGridDialogView.u = null;
        tabGridDialogView.o.clearFocus();
        ViewGroup viewGroup = (ViewGroup) tabGridDialogView.getParent();
        int i = 0;
        while (true) {
            int childCount = viewGroup.getChildCount();
            map = tabGridDialogView.F;
            if (i >= childCount) {
                break;
            }
            View childAt = viewGroup.getChildAt(i);
            if (childAt != tabGridDialogView) {
                Integer num = (Integer) map.get(childAt);
                childAt.setImportantForAccessibility(num == null ? 0 : num.intValue());
            }
            i++;
        }
        map.clear();
        TabGridDialogView.f fVar = tabGridDialogView.t;
        if (fVar != null) {
            fVar.a();
        }
    }
}
