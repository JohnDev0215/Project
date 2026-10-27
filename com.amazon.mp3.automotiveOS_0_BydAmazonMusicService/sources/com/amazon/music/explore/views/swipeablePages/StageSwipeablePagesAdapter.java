package com.amazon.music.explore.views.swipeablePages;

import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.explore.views.swipeablePages.pages.StageSwipeableSectionsView;
import com.amazon.music.explore.views.swipeablePages.pages.StageSwipeableSectionsViewHolder;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageViewHolder;
import com.amazon.music.explore.views.swipeablePages.pages.manager.StageProgressManager;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import java.util.ArrayList;
import java.util.List;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes4.dex */
public class StageSwipeablePagesAdapter extends RecyclerView.Adapter<SwipeablePageViewHolder> {
    private final Context context;
    private boolean isFullScreen;
    private final MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private final List<SwipeablePageElement> pages;
    private StageProgressManager stageProgressManager;
    private final SwipeablePagesStyleSheet styleSheet;
    private List<View> verticalContainers = new ArrayList();
    private PublishSubject<Pair<Integer, Integer>> xrayStateEvent;

    public StageSwipeablePagesAdapter(String str, Context context, MethodsDispatcher methodsDispatcher, List<SwipeablePageElement> list, StageProgressManager stageProgressManager, SwipeablePagesStyleSheet swipeablePagesStyleSheet, boolean z, PublishSubject<Pair<Integer, Integer>> publishSubject) {
        this.ownerId = str;
        this.context = context;
        this.methodsDispatcher = methodsDispatcher;
        this.pages = list;
        this.styleSheet = swipeablePagesStyleSheet;
        this.stageProgressManager = stageProgressManager;
        this.isFullScreen = z;
        this.xrayStateEvent = publishSubject;
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
    public SwipeablePageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new StageSwipeableSectionsViewHolder(new StageSwipeableSectionsView(this.ownerId, this.context, this.methodsDispatcher, this.stageProgressManager, this.styleSheet, this.isFullScreen));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(SwipeablePageViewHolder swipeablePageViewHolder, int i) {
        SwipeablePageView view = swipeablePageViewHolder.getView();
        view.bind(this.pages.get(i));
        view.getVerticalContainer().setContentDescription(this.pages.get(i).label().title());
        if (view instanceof StageSwipeableSectionsView) {
            StageSwipeableSectionsView stageSwipeableSectionsView = (StageSwipeableSectionsView) view;
            stageSwipeableSectionsView.setPosition(i);
            stageSwipeableSectionsView.setXrayStateEvent(this.xrayStateEvent);
        }
        view.setIsFirstPage(i == 0);
        view.setIsLastPage(i == this.pages.size() - 1);
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.verticalContainers.add(view.getVerticalContainer());
    }

    public void setIsFullScreen(boolean z) {
        this.isFullScreen = z;
    }

    public List<View> getVerticalContainers() {
        return this.verticalContainers;
    }
}
