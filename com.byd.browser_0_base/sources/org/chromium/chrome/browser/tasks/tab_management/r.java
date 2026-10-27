package org.chromium.chrome.browser.tasks.tab_management;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.browser.tab.TabUtils;

/* JADX INFO: compiled from: TabGridPanelViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r {

    /* JADX INFO: compiled from: TabGridPanelViewBinder.java */
    public static class a {
        public final TabGroupUiToolbarView a;
        public final RecyclerView b;
        public final TabGridDialogView c;

        public a(TabGroupUiToolbarView tabGroupUiToolbarView, RecyclerView recyclerView, TabGridDialogView tabGridDialogView) {
            this.a = tabGroupUiToolbarView;
            this.b = recyclerView;
            this.c = tabGridDialogView;
        }
    }

    public static void a(int i, RecyclerView recyclerView) {
        int iComputeVerticalScrollRange;
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int width = recyclerView.getWidth();
        int height = recyclerView.getHeight();
        int iMax = 0;
        if (!(linearLayoutManager instanceof GridLayoutManager)) {
            if (linearLayoutManager.D() != 0) {
                iComputeVerticalScrollRange = recyclerView.computeVerticalScrollRange() / linearLayoutManager.D();
            }
            linearLayoutManager.g1(i, iMax);
        }
        iComputeVerticalScrollRange = (int) ((width / ((GridLayoutManager) linearLayoutManager).F) / TabUtils.c(recyclerView.getContext()));
        iMax = Math.max(0, (height / 2) - (iComputeVerticalScrollRange / 2));
        linearLayoutManager.g1(i, iMax);
    }
}
