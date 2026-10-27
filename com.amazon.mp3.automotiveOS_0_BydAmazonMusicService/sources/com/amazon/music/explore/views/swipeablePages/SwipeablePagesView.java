package com.amazon.music.explore.views.swipeablePages;

import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageLabelElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesTemplate;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import com.amazon.music.explore.R;
import com.amazon.music.explore.views.swipeablePages.pages.ScrollableListView;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageRelativeLayout;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageViewHolder;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.ScrollableListVerticalContainer;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.SwipeableSectionsVerticalContainer;
import com.amazon.music.explore.views.swipeablePages.peek.PeekItemAdapter;
import com.amazon.music.explore.views.swipeablePages.peek.PeekItemsView;
import com.amazon.music.explore.views.swipeablePages.peek.PeekProgressBar;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.managers.CountDownTimerManager;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeablePagesView extends SwipeablePageRelativeLayout implements PeekProgressBar {
    private static final long PAGE_CHANGED_DURATION_MS = 500;
    private static final float PULL_DOWN_THRESHOLD_PX = 200.0f;
    private static final long TIMER_INTERVAL_MS = 100;
    private final Context context;
    private boolean isChangingPages;
    private LinearLayoutManager layoutManager;
    private final MethodsDispatcher methodsDispatcher;
    private float onTouchedY;
    private final String ownerId;
    private CountDownTimerManager pageChangedTimer;
    private SwipeablePagesAdapter pagesAdapter;
    private PeekItemAdapter peekAdapter;
    private PeekItemsView peekItemsView;
    private LinearLayoutManager peekLayoutManager;
    private ProgressBar progressBar;
    private boolean shouldScrollToFirstPage;
    private PagerSnapHelper snapHelper;
    private SwipeablePagesStyleSheet styleSheet;
    private RecyclerView swipeablePages;
    private final SwipeablePagesTemplate template;
    private CountDownTimerManager timerManager;

    public interface SnapPositionChangeListener {
        void onSnapPositionChange(int i);
    }

    public SwipeablePagesView(String str, Context context, MethodsDispatcher methodsDispatcher, SwipeablePagesTemplate swipeablePagesTemplate) {
        super(context);
        this.context = context;
        this.ownerId = str;
        this.methodsDispatcher = methodsDispatcher;
        this.template = swipeablePagesTemplate;
    }

    private void init() {
        View.inflate(this.context, R.layout.swipeable_pages_view, this);
        this.swipeablePages = (RecyclerView) findViewById(R.id.swipeable_pages);
        this.pagesAdapter = new SwipeablePagesAdapter(this.ownerId, this.context, this.methodsDispatcher, this.template.pages(), this, this.styleSheet);
        this.layoutManager = new LinearLayoutManager(this.context);
        this.snapHelper = new PagerSnapHelper();
        this.layoutManager.setOrientation(1);
        this.snapHelper.attachToRecyclerView(this.swipeablePages);
        this.swipeablePages.setLayoutManager(this.layoutManager);
        this.swipeablePages.addItemDecoration(new OverlapItemDecoration(this.styleSheet.getPeekLeftHeight()));
        this.swipeablePages.setHasFixedSize(true);
        this.swipeablePages.setAdapter(this.pagesAdapter);
        this.swipeablePages.addOnScrollListener(new SnapScrollListener(this.snapHelper, new SnapPositionChangeListener() { // from class: com.amazon.music.explore.views.swipeablePages.SwipeablePagesView.1
            @Override // com.amazon.music.explore.views.swipeablePages.SwipeablePagesView.SnapPositionChangeListener
            public void onSnapPositionChange(int i) {
                SwipeablePagesView.this.goToPage(i);
            }
        }));
        this.swipeablePages.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() { // from class: com.amazon.music.explore.views.swipeablePages.SwipeablePagesView.2
            @Override // androidx.recyclerview.widget.RecyclerView.SimpleOnItemTouchListener, androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                SwipeablePageView currentPage = SwipeablePagesView.this.getCurrentPage();
                if (currentPage == null) {
                    return false;
                }
                View verticalContainer = currentPage.getVerticalContainer();
                int actionMasked = motionEvent.getActionMasked();
                if (actionMasked == 0) {
                    SwipeablePagesView.this.pauseProgress();
                    if (!(verticalContainer instanceof SwipeableSectionsVerticalContainer)) {
                        return false;
                    }
                    ((SwipeableSectionsVerticalContainer) verticalContainer).stopTimer();
                    return false;
                }
                if (actionMasked != 1) {
                    return false;
                }
                SwipeablePagesView.this.continueProgress();
                if (!(verticalContainer instanceof SwipeableSectionsVerticalContainer)) {
                    return false;
                }
                ((SwipeableSectionsVerticalContainer) verticalContainer).startTimer();
                return false;
            }
        });
        setPeekList(this.template);
        setProgressBar();
        initializeTimerManagers();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        SwipeablePageView page;
        if (this.shouldScrollToFirstPage) {
            this.shouldScrollToFirstPage = false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.onTouchedY = motionEvent.getY();
        } else if (actionMasked == 2 && (page = getPage(this.pagesAdapter.getCurrentPageIndex())) != null && page.isFirstPage() && motionEvent.getY() > this.onTouchedY && motionEvent.getY() - this.onTouchedY > PULL_DOWN_THRESHOLD_PX) {
            page.onClose();
            return true;
        }
        return this.pageChangedTimer.isRunning();
    }

    @Override // com.amazon.music.explore.views.swipeablePages.peek.PeekProgressBar
    public int getProgress() {
        return this.progressBar.getMax();
    }

    @Override // com.amazon.music.explore.views.swipeablePages.peek.PeekProgressBar
    public void restartProgress() {
        restartProgressBar();
    }

    @Override // com.amazon.music.explore.views.swipeablePages.peek.PeekProgressBar
    public void pauseProgress() {
        this.timerManager.stop();
    }

    @Override // com.amazon.music.explore.views.swipeablePages.peek.PeekProgressBar
    public void setProgressTo(int i) {
        if (i >= 0) {
            this.progressBar.setProgress(i);
        }
    }

    @Override // com.amazon.music.explore.views.swipeablePages.peek.PeekProgressBar
    public void continueProgress() {
        this.timerManager.restart(this.progressBar.getMax() - this.progressBar.getProgress());
    }

    public void setStyleSheet(SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        this.styleSheet = swipeablePagesStyleSheet;
        init();
    }

    public void clear() {
        SwipeablePagesAdapter swipeablePagesAdapter = this.pagesAdapter;
        if (swipeablePagesAdapter != null) {
            for (View view : swipeablePagesAdapter.getVerticalContainers()) {
                if (view instanceof ScrollableListVerticalContainer) {
                    ((ScrollableListVerticalContainer) view).stopAutoScrolling();
                } else if (view instanceof SwipeableSectionsVerticalContainer) {
                    ((SwipeableSectionsVerticalContainer) view).stopTimer();
                }
            }
            this.pagesAdapter.getVerticalContainers().clear();
        }
        CountDownTimerManager countDownTimerManager = this.timerManager;
        if (countDownTimerManager != null) {
            countDownTimerManager.stop();
        }
        CountDownTimerManager countDownTimerManager2 = this.pageChangedTimer;
        if (countDownTimerManager2 != null) {
            countDownTimerManager2.stop();
        }
    }

    private void initializeTimerManagers() {
        this.timerManager = new CountDownTimerManager(this.progressBar.getMax(), TIMER_INTERVAL_MS) { // from class: com.amazon.music.explore.views.swipeablePages.SwipeablePagesView.3
            @Override // com.amazon.music.skyfire.ui.managers.CountDownTimerManager
            public void onTicked(long j) {
                SwipeablePagesView.this.progressBar.setProgress(SwipeablePagesView.this.progressBar.getProgress() + 100);
            }

            @Override // com.amazon.music.skyfire.ui.managers.CountDownTimerManager
            public void onFinished() {
                SwipeablePagesView.this.pageChangedTimer.restart(SwipeablePagesView.PAGE_CHANGED_DURATION_MS);
                SwipeablePagesView.this.goToNextPage();
            }
        };
        this.pageChangedTimer = new CountDownTimerManager(PAGE_CHANGED_DURATION_MS, TIMER_INTERVAL_MS) { // from class: com.amazon.music.explore.views.swipeablePages.SwipeablePagesView.4
            @Override // com.amazon.music.skyfire.ui.managers.CountDownTimerManager
            public void onFinished() {
            }

            @Override // com.amazon.music.skyfire.ui.managers.CountDownTimerManager
            public void onTicked(long j) {
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SwipeablePageView getCurrentPage() {
        return getPage(this.pagesAdapter.getCurrentPageIndex());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SwipeablePageView getPage(Integer num) {
        SwipeablePageViewHolder swipeablePageViewHolder;
        if (num == null || (swipeablePageViewHolder = (SwipeablePageViewHolder) this.swipeablePages.findViewHolderForAdapterPosition(num.intValue())) == null) {
            return null;
        }
        return swipeablePageViewHolder.getView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goToNextPage() {
        if (this.pagesAdapter.getCurrentPageIndex().intValue() + 1 < this.pagesAdapter.getItemCount()) {
            goToPage(this.pagesAdapter.getCurrentPageIndex().intValue() + 1);
        } else if (this.pagesAdapter.getCurrentPageIndex().intValue() - 1 >= 0) {
            this.shouldScrollToFirstPage = true;
            goToPage(this.pagesAdapter.getCurrentPageIndex().intValue() - 1);
        } else {
            goToPage(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goToPage(int i) {
        SwipeablePageView currentPage;
        SwipeablePageView page;
        if (this.pagesAdapter.getCurrentPageIndex() == null || this.pagesAdapter.getCurrentPageIndex().intValue() != i) {
            SwipeablePageView page2 = getPage(Integer.valueOf(i));
            if (page2 != null) {
                this.peekLayoutManager.scrollToPositionWithOffset(i, 0);
                this.peekAdapter.updateSelectedItem(i);
                this.swipeablePages.smoothScrollToPosition(i);
                page2.begin();
                if (this.pagesAdapter.getCurrentPageIndex() != null && (currentPage = getCurrentPage()) != null) {
                    currentPage.reset();
                }
                this.pagesAdapter.setCurrentPageIndex(i);
                restartProgressBar();
                return;
            }
            return;
        }
        if (this.pagesAdapter.getCurrentPageIndex().intValue() == i && (page = getPage(Integer.valueOf(i))) != null && (page instanceof ScrollableListView) && page.isFirstPage() && page.isLastPage()) {
            page.begin();
            restartProgressBar();
        }
    }

    private void setProgressBar() {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.peek_progress_bar);
        this.progressBar = progressBar;
        ((LinearLayout.LayoutParams) progressBar.getLayoutParams()).bottomMargin = this.styleSheet.getPeekLeftHeight() - getResources().getDimensionPixelSize(R.dimen.swipeable_pages_progressbar_height);
        setProgressBarAngle();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void setPeekList(SwipeablePagesTemplate swipeablePagesTemplate) {
        this.peekItemsView = (PeekItemsView) findViewById(R.id.peek_items_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        this.peekLayoutManager = linearLayoutManager;
        this.peekItemsView.setLayoutManager(linearLayoutManager);
        PeekItemAdapter peekItemAdapter = new PeekItemAdapter(getSwipeablePageLabels(swipeablePagesTemplate), this.context);
        this.peekAdapter = peekItemAdapter;
        this.peekItemsView.setAdapter(peekItemAdapter);
        this.peekItemsView.getLayoutParams().width = this.styleSheet.getScreenWidth() / 2;
        this.peekItemsView.setPadding(this.styleSheet.getGridSize().getColumnSpace() + this.styleSheet.getGridSize().getMarginSpace(), this.peekItemsView.getPaddingTop(), 0, 0);
        this.peekItemsView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.music.explore.views.swipeablePages.SwipeablePagesView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$setPeekList$0(view, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setPeekList$0(View view, MotionEvent motionEvent) {
        if (!this.pageChangedTimer.isRunning() && motionEvent.getActionMasked() == 1) {
            goToNextPage();
        }
        return true;
    }

    private List<SwipeablePageLabelElement> getSwipeablePageLabels(SwipeablePagesTemplate swipeablePagesTemplate) {
        ArrayList arrayList = new ArrayList();
        Iterator<SwipeablePageElement> it = swipeablePagesTemplate.pages().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().label());
        }
        if (swipeablePagesTemplate.terminatingLabel() != null) {
            arrayList.add(swipeablePagesTemplate.terminatingLabel());
        }
        return arrayList;
    }

    private void restartProgressBar() {
        SwipeablePageView currentPage = getCurrentPage();
        if (currentPage != null) {
            this.progressBar.setProgress(0);
            this.progressBar.setMax(currentPage.getMaxProgress());
            this.timerManager.restart(this.progressBar.getMax());
        }
    }

    private void setProgressBarAngle() {
        this.progressBar.setRotation(findAngle(this.styleSheet.getPeekLeftHeight() - this.styleSheet.getPeekRightHeight(), this.styleSheet.getScreenWidth()));
    }

    private float findAngle(int i, int i2) {
        double d = i;
        double dHypot = Math.hypot(d, i2);
        float degrees = (float) Math.toDegrees(Math.asin(d / dHypot));
        this.progressBar.setScaleX(((float) dHypot) / this.styleSheet.getScreenWidth());
        return degrees;
    }

    private static class OverlapItemDecoration extends RecyclerView.ItemDecoration {
        private final int overlap;

        OverlapItemDecoration(int i) {
            this.overlap = i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            super.getItemOffsets(rect, view, recyclerView, state);
            rect.top = this.overlap * (-1);
        }
    }

    private class SnapScrollListener extends RecyclerView.OnScrollListener {
        private int currentPageIndex;
        private SwipeablePageView firstPage;
        private int onScrolledY;
        private final SnapHelper snapHelper;
        private int snapPosition = -1;
        private final SnapPositionChangeListener snapPositionChangeListener;
        private int upcomingPageIndex;

        public SnapScrollListener(SnapHelper snapHelper, SnapPositionChangeListener snapPositionChangeListener) {
            this.snapHelper = snapHelper;
            this.snapPositionChangeListener = snapPositionChangeListener;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            if (i2 != 0) {
                if (Math.abs(this.onScrolledY) == getPageMaxScrollY()) {
                    this.onScrolledY = 0;
                    this.currentPageIndex = this.snapPosition;
                }
                if (Math.abs(i2) > Math.abs(this.onScrolledY)) {
                    int i3 = this.currentPageIndex;
                    this.upcomingPageIndex = i2 > 0 ? i3 + 1 : i3 - 1;
                }
                this.onScrolledY = this.onScrolledY + i2;
                float fAbs = 1.0f - (Math.abs(r6) / SwipeablePagesView.PULL_DOWN_THRESHOLD_PX);
                float fAbs2 = Math.abs(this.onScrolledY) / SwipeablePagesView.PULL_DOWN_THRESHOLD_PX;
                SwipeablePageView page = SwipeablePagesView.this.getPage(Integer.valueOf(this.currentPageIndex));
                SwipeablePageView page2 = SwipeablePagesView.this.getPage(Integer.valueOf(this.upcomingPageIndex));
                if (page != null) {
                    page.getVerticalContainer().setAlpha(fAbs);
                }
                if (page2 != null && Math.abs(this.onScrolledY) >= getPageMaxScrollY() - SwipeablePagesView.PULL_DOWN_THRESHOLD_PX) {
                    page2.getVerticalContainer().setAlpha(fAbs2);
                }
            }
            notifySnapPositionChange(recyclerView);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (this.firstPage == null) {
                this.firstPage = SwipeablePagesView.this.getPage(0);
            }
            super.onScrollStateChanged(recyclerView, i);
            if (i == 0 && SwipeablePagesView.this.shouldScrollToFirstPage && this.firstPage == SwipeablePagesView.this.getPage(0)) {
                SwipeablePagesView.this.shouldScrollToFirstPage = false;
                SwipeablePagesView.this.goToPage(0);
            }
        }

        private int getSnapPosition(RecyclerView recyclerView) {
            View viewFindSnapView;
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager == null || (viewFindSnapView = this.snapHelper.findSnapView(layoutManager)) == null) {
                return -1;
            }
            return layoutManager.getPosition(viewFindSnapView);
        }

        private void notifySnapPositionChange(RecyclerView recyclerView) {
            int snapPosition = getSnapPosition(recyclerView);
            int i = this.snapPosition;
            if (i != snapPosition) {
                if (i != -1) {
                    SwipeablePagesView.this.pageChangedTimer.restart(SwipeablePagesView.PAGE_CHANGED_DURATION_MS);
                }
                this.snapPositionChangeListener.onSnapPositionChange(snapPosition);
                this.snapPosition = snapPosition;
            }
        }

        private int getPageMaxScrollY() {
            return ScreenUtils.getAvailableScreenHeight(SwipeablePagesView.this.context) - 2;
        }
    }
}
