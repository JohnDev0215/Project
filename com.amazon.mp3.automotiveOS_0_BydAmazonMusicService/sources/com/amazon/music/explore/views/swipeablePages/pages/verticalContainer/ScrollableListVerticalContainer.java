package com.amazon.music.explore.views.swipeablePages.pages.verticalContainer;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.ScrollView;
import com.amazon.music.explore.views.swipeablePages.TouchEventHandler;
import com.amazon.music.explore.views.swipeablePages.peek.PeekProgressBar;
import com.amazon.music.skyfire.ui.managers.ObjectAnimatorManager;

/* JADX INFO: loaded from: classes4.dex */
public class ScrollableListVerticalContainer extends ScrollView {
    private ObjectAnimatorManager animatorManager;
    private boolean attemptedAutoScroll;
    private int distanceToMaxScrollY;
    private boolean isScrollable;
    private int maxScrollYPosition;
    private PeekProgressBar progressBar;
    private int progressBarMax;
    private long scrollingDuration;
    private int speed;
    private TouchEventHandler touchEventHandler;
    private VerticalContainerView verticalContainerView;

    @Override // android.widget.ScrollView
    public void fling(int i) {
    }

    public ScrollableListVerticalContainer(Context context) {
        super(context);
        init(context);
    }

    public ScrollableListVerticalContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ScrollableListVerticalContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.touchEventHandler = new TouchEventHandler(context);
        initializeAnimator();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.ScrollableListVerticalContainer.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int height = ScrollableListVerticalContainer.this.verticalContainerView.getHeight();
                if (ScrollableListVerticalContainer.this.getHeight() < height) {
                    ScrollableListVerticalContainer.this.isScrollable = true;
                    ScrollableListVerticalContainer scrollableListVerticalContainer = ScrollableListVerticalContainer.this;
                    scrollableListVerticalContainer.maxScrollYPosition = height - (scrollableListVerticalContainer.getHeight() / 2);
                    ScrollableListVerticalContainer.this.verticalContainerView.setPadding(0, 0, 0, ScrollableListVerticalContainer.this.getHeight() / 2);
                    ScrollableListVerticalContainer scrollableListVerticalContainer2 = ScrollableListVerticalContainer.this;
                    scrollableListVerticalContainer2.distanceToMaxScrollY = scrollableListVerticalContainer2.maxScrollYPosition - ScrollableListVerticalContainer.this.getScrollY();
                    ScrollableListVerticalContainer scrollableListVerticalContainer3 = ScrollableListVerticalContainer.this;
                    scrollableListVerticalContainer3.progressBarMax = scrollableListVerticalContainer3.distanceToMaxScrollY * 1000;
                    if (ScrollableListVerticalContainer.this.attemptedAutoScroll) {
                        ScrollableListVerticalContainer.this.progressBar.restartProgress();
                        ScrollableListVerticalContainer.this.startAutoScrolling();
                    }
                }
                ScrollableListVerticalContainer.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        onTouchEvent(motionEvent);
        this.touchEventHandler.onTouch(this, motionEvent);
        return motionEvent.getActionMasked() == 1 && !this.touchEventHandler.wasSingleTapped();
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            stopAutoScrolling();
        } else if (actionMasked == 1) {
            startAutoScrolling();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        this.distanceToMaxScrollY = this.maxScrollYPosition - i2;
        this.progressBar.pauseProgress();
        PeekProgressBar peekProgressBar = this.progressBar;
        peekProgressBar.setProgressTo(peekProgressBar.getProgress() - (this.distanceToMaxScrollY * 1000));
        this.progressBar.continueProgress();
    }

    public PeekProgressBar getProgressBar() {
        return this.progressBar;
    }

    public int getMaxProgress() {
        return this.progressBarMax;
    }

    public void setProgressBar(PeekProgressBar peekProgressBar) {
        this.progressBar = peekProgressBar;
    }

    public void setMinDuration(int i) {
        long j = i;
        this.scrollingDuration = j;
        this.progressBarMax = (int) j;
    }

    public void setSpeed(int i) {
        this.speed = (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    public void addVerticalContainerView(VerticalContainerView verticalContainerView) {
        this.verticalContainerView = verticalContainerView;
        addView(verticalContainerView);
    }

    public void stopAutoScrolling() {
        if (this.isScrollable) {
            this.animatorManager.stop();
        }
    }

    public void startAutoScrolling() {
        if (this.isScrollable) {
            this.scrollingDuration = (this.distanceToMaxScrollY / this.speed) * 1000;
            this.animatorManager.setIntValues(getScrollY(), this.maxScrollYPosition);
            this.animatorManager.setDuration(this.scrollingDuration);
            this.animatorManager.start();
        }
        if (this.attemptedAutoScroll) {
            return;
        }
        this.attemptedAutoScroll = true;
    }

    private void initializeAnimator() {
        ObjectAnimatorManager objectAnimatorManager = new ObjectAnimatorManager(this, "scrollY");
        this.animatorManager = objectAnimatorManager;
        objectAnimatorManager.setInterpolator(new LinearInterpolator());
    }
}
