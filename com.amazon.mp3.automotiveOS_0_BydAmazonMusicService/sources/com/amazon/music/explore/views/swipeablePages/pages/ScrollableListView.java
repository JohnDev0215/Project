package com.amazon.music.explore.views.swipeablePages.pages;

import Touch.SwipeablePagesTemplateInterface.v1_0.ScrollableListElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.amazon.music.explore.R;
import com.amazon.music.explore.views.swipeablePages.GradientMaskView;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesStyleSheet;
import com.amazon.music.explore.views.swipeablePages.binders.ScrollableListBinder;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.ScrollableListVerticalContainer;
import com.amazon.music.explore.views.swipeablePages.peek.PeekProgressBar;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;
import com.amazon.music.views.library.styles.keys.SpacerKey;

/* JADX INFO: loaded from: classes4.dex */
public class ScrollableListView extends SwipeablePageView {
    private SwipeablePageImageView background;
    private SwipeablePageImageView backgroundOverlay;
    private GradientMaskView gradientMask;
    private ScrollableListElement pageElement;
    private ScrollableListVerticalContainer verticalContainer;

    public ScrollableListView(String str, Context context, MethodsDispatcher methodsDispatcher, PeekProgressBar peekProgressBar, SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        super(str, context, methodsDispatcher, peekProgressBar, swipeablePagesStyleSheet);
        init();
    }

    private void init() {
        View.inflate(this.context, R.layout.scrollable_list_view, this);
        this.verticalContainer = (ScrollableListVerticalContainer) findViewById(R.id.scrollable_list_vertical_container);
        this.background = (SwipeablePageImageView) findViewById(R.id.scrollable_list_background);
        this.backgroundOverlay = (SwipeablePageImageView) findViewById(R.id.scrollable_list_background_overlay);
        this.gradientMask = (GradientMaskView) findViewById(R.id.scrollable_list_gradient_mask);
        this.verticalContainer.setProgressBar(this.progressBar);
        setStyleSheets();
        addTopMargin();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.onTouchedY = motionEvent.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (actionMasked == 2 && motionEvent.getY() > this.onTouchedY && this.verticalContainer.getScrollY() <= 100.0f) {
            if (isFirstPage()) {
                onClose();
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public void bind(SwipeablePageElement swipeablePageElement) {
        ScrollableListElement scrollableListElement = (ScrollableListElement) swipeablePageElement;
        this.pageElement = scrollableListElement;
        ScrollableListBinder.bind(this, scrollableListElement, this.styleSheet);
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public void reset() {
        this.verticalContainer.stopAutoScrolling();
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public void begin() {
        if (!this.hasBeenViewed) {
            this.methodsDispatcher.dispatchMethods(this.ownerId, this.pageElement.onViewed());
            this.hasBeenViewed = true;
        }
        this.verticalContainer.scrollTo(0, 0);
        this.verticalContainer.startAutoScrolling();
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public int getMaxProgress() {
        return this.verticalContainer.getMaxProgress();
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public ScrollableListVerticalContainer getVerticalContainer() {
        return this.verticalContainer;
    }

    private void setStyleSheets() {
        this.background.setStyleSheet(this.styleSheet);
        this.backgroundOverlay.setStyleSheet(this.styleSheet);
        this.gradientMask.setStyleSheet(this.styleSheet);
    }

    private void addTopMargin() {
        int dimensionPixelSize = this.resources.getDimensionPixelSize(R.dimen.swipeable_pages_miniplayer_height);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.verticalContainer.getLayoutParams();
        layoutParams.topMargin = dimensionPixelSize + (this.styleSheet.getSpacerInPixels(SpacerKey.MEDIUM).intValue() * 2) + this.styleSheet.getPeekLeftHeight() + ScreenUtils.getStatusBarHeight(this.context);
        layoutParams.bottomMargin = this.styleSheet.getPeekLeftHeight();
    }
}
