package com.amazon.music.explore.views.swipeablePages;

import Touch.SwipeablePagesTemplateInterface.v1_0.ScrollableListElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.explore.views.swipeablePages.pages.ScrollableListView;
import com.amazon.music.explore.views.swipeablePages.pages.ScrollableListViewHolder;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageViewHolder;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeableSectionsView;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeableSectionsViewHolder;
import com.amazon.music.explore.views.swipeablePages.peek.PeekProgressBar;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeablePagesAdapter extends RecyclerView.Adapter<SwipeablePageViewHolder> {
    private final Context context;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final List<SwipeablePageElement> pages;
    private final PeekProgressBar progressBar;
    private final SwipeablePagesStyleSheet styleSheet;
    private List<View> verticalContainers = new ArrayList();
    private Integer currentPage = null;

    private enum SwipeablePageType {
        SwipeableSections(1),
        ScrollableList(2);

        private final int value;

        SwipeablePageType(int i) {
            this.value = i;
        }
    }

    public SwipeablePagesAdapter(String str, Context context, MethodsDispatcher methodsDispatcher, List<SwipeablePageElement> list, PeekProgressBar peekProgressBar, SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        this.ownerId = str;
        this.context = context;
        this.methodsDispatcher = methodsDispatcher;
        this.pages = list;
        this.progressBar = peekProgressBar;
        this.styleSheet = swipeablePagesStyleSheet;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SwipeablePageElement> list = this.pages;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.pages.get(i) instanceof ScrollableListElement ? SwipeablePageType.ScrollableList.value : SwipeablePageType.SwipeableSections.value;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public SwipeablePageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == SwipeablePageType.ScrollableList.value) {
            return new ScrollableListViewHolder(new ScrollableListView(this.ownerId, this.context, this.methodsDispatcher, this.progressBar, this.styleSheet));
        }
        return new SwipeableSectionsViewHolder(new SwipeableSectionsView(this.ownerId, this.context, this.methodsDispatcher, this.progressBar, this.styleSheet));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(SwipeablePageViewHolder swipeablePageViewHolder, int i) {
        SwipeablePageView view = swipeablePageViewHolder.getView();
        view.bind(this.pages.get(i));
        view.setIsFirstPage(i == 0);
        view.setIsLastPage(i == this.pages.size() - 1);
        int availableScreenHeight = (ScreenUtils.getAvailableScreenHeight(this.context) + this.styleSheet.getPeekLeftHeight()) - 1;
        if (!view.isFirstPage() && !view.isLastPage()) {
            availableScreenHeight--;
        }
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, availableScreenHeight));
        this.verticalContainers.add(view.getVerticalContainer());
    }

    public List<View> getVerticalContainers() {
        return this.verticalContainers;
    }

    public Integer getCurrentPageIndex() {
        return this.currentPage;
    }

    public void setCurrentPageIndex(int i) {
        this.currentPage = Integer.valueOf(i);
    }
}
