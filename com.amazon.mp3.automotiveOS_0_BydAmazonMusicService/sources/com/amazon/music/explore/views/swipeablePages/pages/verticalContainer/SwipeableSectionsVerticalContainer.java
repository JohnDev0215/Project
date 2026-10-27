package com.amazon.music.explore.views.swipeablePages.pages.verticalContainer;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesStyleSheet;
import com.amazon.music.explore.views.swipeablePages.TouchEventHandler;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageRelativeLayout;
import com.amazon.music.explore.views.swipeablePages.peek.PeekProgressBar;
import com.amazon.music.skyfire.ui.managers.CountDownTimerManager;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeableSectionsVerticalContainer extends SwipeablePageRelativeLayout {
    private static final String ALPHA_PROPERTY = "alpha";
    private static final float PULL_DOWN_THRESHOLD = 100.0f;
    private static final float TICK_DURATION = 0.05f;
    private static final String Y_PROPERTY = "y";
    private int activePointerId;
    private boolean doesTouchedAreaNotHaveClickListener;
    private boolean isSwipeable;
    private float onTouchedPointerY;
    private int onTouchedTopMargin;
    private float onTouchedY;
    private PeekProgressBar progressBar;
    private long sectionDuration;
    private float speed;
    private SwipeablePagesStyleSheet stylesheet;
    private CountDownTimerManager timerManager;
    private TouchEventHandler touchEventHandler;

    public SwipeableSectionsVerticalContainer(Context context) {
        super(context);
    }

    public SwipeableSectionsVerticalContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SwipeableSectionsVerticalContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        handleTouch(motionEvent);
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.doesTouchedAreaNotHaveClickListener = true;
        } else if (actionMasked == 1) {
            if (this.doesTouchedAreaNotHaveClickListener) {
                handleTouch(motionEvent);
            }
            this.doesTouchedAreaNotHaveClickListener = false;
        } else if (actionMasked == 2) {
            handleTouch(motionEvent);
        }
        return true;
    }

    public boolean isSwipeable() {
        return this.isSwipeable;
    }

    public long getSectionDuration() {
        return this.sectionDuration;
    }

    public void setProgressBar(PeekProgressBar peekProgressBar) {
        this.progressBar = peekProgressBar;
    }

    public void setTouchEventHandler(TouchEventHandler touchEventHandler) {
        this.touchEventHandler = touchEventHandler;
    }

    public void setSectionDuration(int i) {
        this.sectionDuration = i;
        initializeTimerManager();
    }

    public void setSpeed(int i) {
        this.speed = TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    public void setStyleSheet(SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        this.stylesheet = swipeablePagesStyleSheet;
    }

    public void startTimer() {
        this.doesTouchedAreaNotHaveClickListener = false;
        this.isSwipeable = true;
        this.timerManager.start();
    }

    public void restartTimer() {
        this.doesTouchedAreaNotHaveClickListener = false;
        this.isSwipeable = true;
        this.timerManager.restart(this.sectionDuration);
    }

    public void stopTimer() {
        this.doesTouchedAreaNotHaveClickListener = false;
        this.timerManager.stop();
    }

    public void centerInParent(int i) {
        if (this.stylesheet != null) {
            setY(((ScreenUtils.getAvailableScreenHeight(getContext()) / 2.0f) - (i / 2.0f)) + this.stylesheet.getPeekLeftHeight());
        }
    }

    private void initializeTimerManager() {
        this.timerManager = new CountDownTimerManager(this.sectionDuration, 50L) { // from class: com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.SwipeableSectionsVerticalContainer.1
            @Override // com.amazon.music.skyfire.ui.managers.CountDownTimerManager
            public void onTicked(long j) {
                SwipeableSectionsVerticalContainer swipeableSectionsVerticalContainer = SwipeableSectionsVerticalContainer.this;
                swipeableSectionsVerticalContainer.setY(swipeableSectionsVerticalContainer.getY() - (SwipeableSectionsVerticalContainer.this.speed * SwipeableSectionsVerticalContainer.TICK_DURATION));
            }

            @Override // com.amazon.music.skyfire.ui.managers.CountDownTimerManager
            public void onFinished() {
                SwipeableSectionsVerticalContainer.this.isSwipeable = false;
                SwipeableSectionsVerticalContainer.this.touchEventHandler.onSwipeUp();
            }
        };
        this.isSwipeable = true;
    }

    private void undoSwipeAnimations() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this, Y_PROPERTY, this.onTouchedY), ObjectAnimator.ofFloat(this, ALPHA_PROPERTY, 1.0f));
        animatorSet.start();
        this.timerManager.start();
    }

    private void handleTouch(MotionEvent motionEvent) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.onTouchedTopMargin = layoutParams.topMargin;
            this.onTouchedPointerY = motionEvent.getY();
            this.onTouchedY = getY();
            this.activePointerId = motionEvent.getPointerId(0);
            this.timerManager.stop();
            this.progressBar.pauseProgress();
            return;
        }
        if (actionMasked == 1) {
            if (Math.abs(layoutParams.topMargin - this.onTouchedTopMargin) <= PULL_DOWN_THRESHOLD) {
                undoSwipeAnimations();
            } else if (layoutParams.topMargin < this.onTouchedTopMargin) {
                this.touchEventHandler.onSwipeUp();
            } else {
                this.touchEventHandler.onSwipeDown();
            }
            this.progressBar.continueProgress();
            return;
        }
        if (actionMasked == 2) {
            float y = motionEvent.getY(motionEvent.findPointerIndex(this.activePointerId)) - this.onTouchedPointerY;
            float fAbs = 1.0f - (Math.abs(layoutParams.topMargin - this.onTouchedTopMargin) / PULL_DOWN_THRESHOLD);
            layoutParams.topMargin = (int) (layoutParams.topMargin + y);
            setAlpha(fAbs);
            setLayoutParams(layoutParams);
            return;
        }
        if (actionMasked != 6) {
            return;
        }
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.activePointerId) {
            int i = actionIndex == 0 ? 1 : 0;
            this.onTouchedPointerY = motionEvent.getY(i);
            this.activePointerId = motionEvent.getPointerId(i);
        }
    }
}
