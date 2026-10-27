package androidx.wear.ble.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
public class WearableListView extends RecyclerView {
    private static final float BOTTOM_TAP_REGION_PERCENTAGE = 0.33f;
    private static final long CENTERING_ANIMATION_DURATION_MS = 150;
    private static final long FLIP_ANIMATION_DURATION_MS = 150;
    private static final String TAG = "WearableListView";
    private static final int THIRD = 3;
    private static final float TOP_TAP_REGION_PERCENTAGE = 0.33f;
    private boolean mCanClick;
    private ClickListener mClickListener;
    private boolean mGestureDirectionLocked;
    private boolean mGestureNavigationEnabled;
    private boolean mGreedyTouchMode;
    private int mInitialOffset;
    private int mLastScrollChange;
    private final int[] mLocation;
    private final int mMaxFlingVelocity;
    private boolean mMaximizeSingleItem;
    private final int mMinFlingVelocity;
    private Runnable mNotifyChildrenPostLayoutRunnable;
    private final RecyclerView.AdapterDataObserver mObserver;
    private final List<OnCentralPositionChangedListener> mOnCentralPositionChangedListeners;
    private final List<OnScrollListener> mOnScrollListeners;
    private OnOverScrollListener mOverScrollListener;
    private boolean mPossibleVerticalSwipe;
    private final Runnable mPressedRunnable;
    private View mPressedView;
    private int mPreviousCentral;
    private final Runnable mReleasedRunnable;
    private Animator mScrollAnimator;
    private Scroller mScroller;
    private SetScrollVerticallyProperty mSetScrollVerticallyProperty;
    private float mStartFirstTop;
    private float mStartX;
    private float mStartY;
    private int mTapPositionX;
    private int mTapPositionY;
    private final float[] mTapRegions;
    private final int mTouchSlop;

    public static abstract class Adapter extends RecyclerView.Adapter<ViewHolder> {
    }

    public interface ClickListener {
        void onClick(ViewHolder viewHolder);

        void onTopEmptyRegionClick();
    }

    public interface OnCenterProximityListener {
        void onCenterPosition(boolean z);

        void onNonCenterPosition(boolean z);
    }

    public interface OnCentralPositionChangedListener {
        void onCentralPositionChanged(int i);
    }

    public interface OnOverScrollListener {
        void onOverScroll();
    }

    public interface OnScrollListener {
        @Deprecated
        void onAbsoluteScrollChange(int i);

        void onCentralPositionChanged(int i);

        void onScroll(int i);

        void onScrollStateChanged(int i);
    }

    public WearableListView(Context context) {
        this(context, null);
    }

    public WearableListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WearableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCanClick = true;
        this.mGestureNavigationEnabled = true;
        this.mSetScrollVerticallyProperty = new SetScrollVerticallyProperty();
        this.mOnScrollListeners = new ArrayList();
        this.mOnCentralPositionChangedListeners = new ArrayList();
        this.mInitialOffset = 0;
        this.mTapRegions = new float[2];
        this.mPreviousCentral = 0;
        this.mLocation = new int[2];
        this.mPressedView = null;
        this.mPressedRunnable = new Runnable() { // from class: androidx.wear.ble.view.WearableListView.1
            @Override // java.lang.Runnable
            public void run() {
                if (WearableListView.this.getChildCount() > 0) {
                    WearableListView wearableListView = WearableListView.this;
                    wearableListView.mPressedView = wearableListView.getChildAt(wearableListView.findCenterViewIndex());
                    WearableListView.this.mPressedView.setPressed(true);
                    return;
                }
                Log.w(WearableListView.TAG, "mPressedRunnable: the children were removed, skipping.");
            }
        };
        this.mReleasedRunnable = new Runnable() { // from class: androidx.wear.ble.view.WearableListView.2
            @Override // java.lang.Runnable
            public void run() {
                WearableListView.this.releasePressedItem();
            }
        };
        this.mNotifyChildrenPostLayoutRunnable = new Runnable() { // from class: androidx.wear.ble.view.WearableListView.3
            @Override // java.lang.Runnable
            public void run() {
                WearableListView.this.notifyChildrenAboutProximity(false);
            }
        };
        this.mObserver = new RecyclerView.AdapterDataObserver() { // from class: androidx.wear.ble.view.WearableListView.4
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                WearableListView.this.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: androidx.wear.ble.view.WearableListView.4.1
                    @Override // android.view.View.OnLayoutChangeListener
                    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                        WearableListView.this.removeOnLayoutChangeListener(this);
                        if (WearableListView.this.getChildCount() > 0) {
                            WearableListView.this.animateToCenter();
                        }
                    }
                });
            }
        };
        setHasFixedSize(true);
        setOverScrollMode(2);
        setLayoutManager(new LayoutManager());
        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() { // from class: androidx.wear.ble.view.WearableListView.5
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == 0 && WearableListView.this.getChildCount() > 0) {
                    WearableListView.this.handleTouchUp(null, newState);
                }
                for (OnScrollListener listener : WearableListView.this.mOnScrollListeners) {
                    listener.onScrollStateChanged(newState);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                WearableListView.this.onScroll(dy);
            }
        };
        setOnScrollListener(onScrollListener);
        ViewConfiguration vc = ViewConfiguration.get(context);
        this.mTouchSlop = vc.getScaledTouchSlop();
        this.mMinFlingVelocity = vc.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = vc.getScaledMaximumFlingVelocity();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        RecyclerView.Adapter currentAdapter = getAdapter();
        if (currentAdapter != null) {
            currentAdapter.unregisterAdapterDataObserver(this.mObserver);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.mObserver);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public int getBaseline() {
        if (getChildCount() == 0) {
            return super.getBaseline();
        }
        int centerChildIndex = findCenterViewIndex();
        int centerChildBaseline = getChildAt(centerChildIndex).getBaseline();
        if (centerChildBaseline == -1) {
            return super.getBaseline();
        }
        return getCentralViewTop() + centerChildBaseline;
    }

    public boolean isAtTop() {
        if (getChildCount() == 0) {
            return true;
        }
        int centerChildIndex = findCenterViewIndex();
        View centerView = getChildAt(centerChildIndex);
        return getChildAdapterPosition(centerView) == 0 && getScrollState() == 0;
    }

    public void resetLayoutManager() {
        setLayoutManager(new LayoutManager());
    }

    public void setGreedyTouchMode(boolean greedy) {
        this.mGreedyTouchMode = greedy;
    }

    public void setInitialOffset(int top) {
        this.mInitialOffset = top;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        if (this.mGreedyTouchMode && getChildCount() > 0) {
            int action = event.getActionMasked();
            if (action == 0) {
                this.mStartX = event.getX();
                this.mStartY = event.getY();
                this.mStartFirstTop = getChildCount() > 0 ? getChildAt(0).getTop() : 0.0f;
                this.mPossibleVerticalSwipe = true;
                this.mGestureDirectionLocked = false;
            } else if (action == 2 && this.mPossibleVerticalSwipe) {
                handlePossibleVerticalSwipe(event);
            }
            getParent().requestDisallowInterceptTouchEvent(this.mPossibleVerticalSwipe);
        }
        return super.onInterceptTouchEvent(event);
    }

    private boolean handlePossibleVerticalSwipe(MotionEvent event) {
        if (this.mGestureDirectionLocked) {
            return this.mPossibleVerticalSwipe;
        }
        float deltaX = Math.abs(this.mStartX - event.getX());
        float deltaY = Math.abs(this.mStartY - event.getY());
        float distance = (deltaX * deltaX) + (deltaY * deltaY);
        int i = this.mTouchSlop;
        if (distance > i * i) {
            if (deltaX > deltaY) {
                this.mPossibleVerticalSwipe = false;
            }
            this.mGestureDirectionLocked = true;
        }
        return this.mPossibleVerticalSwipe;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        int scrollState = getScrollState();
        boolean result = super.onTouchEvent(event);
        if (getChildCount() > 0) {
            int action = event.getActionMasked();
            if (action == 0) {
                handleTouchDown(event);
                return result;
            }
            if (action == 1) {
                handleTouchUp(event, scrollState);
                getParent().requestDisallowInterceptTouchEvent(false);
                return result;
            }
            if (action == 2) {
                if (Math.abs(this.mTapPositionX - ((int) event.getX())) >= this.mTouchSlop || Math.abs(this.mTapPositionY - ((int) event.getY())) >= this.mTouchSlop) {
                    releasePressedItem();
                    this.mCanClick = false;
                }
                boolean result2 = result | handlePossibleVerticalSwipe(event);
                getParent().requestDisallowInterceptTouchEvent(this.mPossibleVerticalSwipe);
                return result2;
            }
            if (action == 3) {
                getParent().requestDisallowInterceptTouchEvent(false);
                this.mCanClick = true;
                return result;
            }
            return result;
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releasePressedItem() {
        View view = this.mPressedView;
        if (view != null) {
            view.setPressed(false);
            this.mPressedView = null;
        }
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.mPressedRunnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onScroll(int dy) {
        for (OnScrollListener listener : this.mOnScrollListeners) {
            listener.onScroll(dy);
        }
        notifyChildrenAboutProximity(true);
    }

    public void addOnScrollListener(OnScrollListener listener) {
        this.mOnScrollListeners.add(listener);
    }

    public void removeOnScrollListener(OnScrollListener listener) {
        this.mOnScrollListeners.remove(listener);
    }

    public void addOnCentralPositionChangedListener(OnCentralPositionChangedListener listener) {
        this.mOnCentralPositionChangedListeners.add(listener);
    }

    public void removeOnCentralPositionChangedListener(OnCentralPositionChangedListener listener) {
        this.mOnCentralPositionChangedListeners.remove(listener);
    }

    public boolean isGestureNavigationEnabled() {
        return this.mGestureNavigationEnabled;
    }

    public void setEnableGestureNavigation(boolean enabled) {
        this.mGestureNavigationEnabled = enabled;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.mGestureNavigationEnabled) {
            switch (keyCode) {
                case 260:
                    fling(0, -this.mMinFlingVelocity);
                    return true;
                case 261:
                    fling(0, this.mMinFlingVelocity);
                    return true;
                case 262:
                    return tapCenterView();
                case 263:
                    return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean tapCenterView() {
        if (!isEnabled() || getVisibility() != 0) {
            return false;
        }
        int index = findCenterViewIndex();
        View view = getChildAt(index);
        ViewHolder holder = getChildViewHolder(view);
        ClickListener clickListener = this.mClickListener;
        if (clickListener == null) {
            return false;
        }
        clickListener.onClick(holder);
        return true;
    }

    private boolean checkForTap(MotionEvent event) {
        ClickListener clickListener;
        if (!isEnabled()) {
            return false;
        }
        float rawY = event.getRawY();
        int index = findCenterViewIndex();
        View view = getChildAt(index);
        ViewHolder holder = getChildViewHolder(view);
        computeTapRegions(this.mTapRegions);
        float[] fArr = this.mTapRegions;
        if (rawY > fArr[0] && rawY < fArr[1]) {
            ClickListener clickListener2 = this.mClickListener;
            if (clickListener2 != null) {
                clickListener2.onClick(holder);
            }
            return true;
        }
        if (index > 0 && rawY <= this.mTapRegions[0]) {
            animateToMiddle(index - 1, index);
            return true;
        }
        if (index < getChildCount() - 1 && rawY >= this.mTapRegions[1]) {
            animateToMiddle(index + 1, index);
            return true;
        }
        if (index != 0 || rawY > this.mTapRegions[0] || (clickListener = this.mClickListener) == null) {
            return false;
        }
        clickListener.onTopEmptyRegionClick();
        return true;
    }

    private void animateToMiddle(int newCenterIndex, int oldCenterIndex) {
        if (newCenterIndex == oldCenterIndex) {
            throw new IllegalArgumentException("newCenterIndex must be different from oldCenterIndex");
        }
        List<Animator> animators = new ArrayList<>();
        View child = getChildAt(newCenterIndex);
        int scrollToMiddle = getCentralViewTop() - child.getTop();
        startScrollAnimation(animators, scrollToMiddle, 150L);
    }

    private void startScrollAnimation(List<Animator> animators, int scroll, long duration) {
        startScrollAnimation(animators, scroll, duration, 0L);
    }

    private void startScrollAnimation(List<Animator> animators, int scroll, long duration, long delay) {
        startScrollAnimation(animators, scroll, duration, delay, null);
    }

    private void startScrollAnimation(int scroll, long duration, long delay, Animator.AnimatorListener listener) {
        startScrollAnimation(null, scroll, duration, delay, listener);
    }

    private void startScrollAnimation(List<Animator> animators, int scroll, long duration, long delay, Animator.AnimatorListener listener) {
        Animator animator = this.mScrollAnimator;
        if (animator != null) {
            animator.cancel();
        }
        this.mLastScrollChange = 0;
        ObjectAnimator scrollAnimator = ObjectAnimator.ofInt(this, this.mSetScrollVerticallyProperty, 0, -scroll);
        if (animators != null) {
            animators.add(scrollAnimator);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(animators);
            this.mScrollAnimator = animatorSet;
        } else {
            this.mScrollAnimator = scrollAnimator;
        }
        this.mScrollAnimator.setDuration(duration);
        if (listener != null) {
            this.mScrollAnimator.addListener(listener);
        }
        if (delay > 0) {
            this.mScrollAnimator.setStartDelay(delay);
        }
        this.mScrollAnimator.start();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean fling(int velocityX, int velocityY) {
        if (getChildCount() == 0) {
            return false;
        }
        int index = findCenterViewIndex();
        View child = getChildAt(index);
        int currentPosition = getChildPosition(child);
        if ((currentPosition != 0 || velocityY >= 0) && (currentPosition != getAdapter().getItemCount() - 1 || velocityY <= 0)) {
            if (Math.abs(velocityY) < this.mMinFlingVelocity) {
                return false;
            }
            int velocityY2 = Math.max(Math.min(velocityY, this.mMaxFlingVelocity), -this.mMaxFlingVelocity);
            if (this.mScroller == null) {
                this.mScroller = new Scroller(getContext(), null, true);
            }
            this.mScroller.fling(0, 0, 0, velocityY2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            int finalY = this.mScroller.getFinalY();
            int delta = finalY / (getPaddingTop() + (getAdjustedHeight() / 2));
            if (delta == 0) {
                delta = velocityY2 > 0 ? 1 : -1;
            }
            int finalPosition = Math.max(0, Math.min(getAdapter().getItemCount() - 1, currentPosition + delta));
            smoothScrollToPosition(finalPosition);
            return true;
        }
        return super.fling(velocityX, velocityY);
    }

    public void smoothScrollToPosition(int position, RecyclerView.SmoothScroller smoothScroller) {
        LayoutManager layoutManager = (LayoutManager) getLayoutManager();
        layoutManager.setCustomSmoothScroller(smoothScroller);
        smoothScrollToPosition(position);
        layoutManager.clearCustomSmoothScroller();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public ViewHolder getChildViewHolder(View child) {
        return (ViewHolder) super.getChildViewHolder(child);
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public void setOverScrollListener(OnOverScrollListener listener) {
        this.mOverScrollListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int findCenterViewIndex() {
        int count = getChildCount();
        int index = -1;
        int closest = Integer.MAX_VALUE;
        int centerY = getCenterYPos(this);
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int childCenterY = getTop() + getCenterYPos(child);
            int distance = Math.abs(centerY - childCenterY);
            if (distance < closest) {
                closest = distance;
                index = i;
            }
        }
        if (index == -1) {
            throw new IllegalStateException("Can't find central view.");
        }
        return index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getCenterYPos(View v) {
        return v.getTop() + v.getPaddingTop() + (getAdjustedHeight(v) / 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTouchUp(MotionEvent event, int scrollState) {
        if (this.mCanClick && event != null && checkForTap(event)) {
            Handler handler = getHandler();
            if (handler != null) {
                handler.postDelayed(this.mReleasedRunnable, ViewConfiguration.getTapTimeout());
                return;
            }
            return;
        }
        if (scrollState != 0) {
            return;
        }
        if (isOverScrolling()) {
            this.mOverScrollListener.onOverScroll();
        } else {
            animateToCenter();
        }
    }

    private boolean isOverScrolling() {
        return getChildCount() > 0 && this.mStartFirstTop <= ((float) getCentralViewTop()) && getChildAt(0).getTop() >= getTopViewMaxTop() && this.mOverScrollListener != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getTopViewMaxTop() {
        return getHeight() / 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getItemHeight() {
        return (getAdjustedHeight() / 3) + 1;
    }

    public int getCentralViewTop() {
        return getPaddingTop() + getItemHeight();
    }

    public void animateToCenter() {
        int index = findCenterViewIndex();
        View child = getChildAt(index);
        int scrollToMiddle = getCentralViewTop() - child.getTop();
        startScrollAnimation(scrollToMiddle, 150L, 0L, new SimpleAnimatorListener() { // from class: androidx.wear.ble.view.WearableListView.6
            @Override // androidx.wear.ble.view.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!wasCanceled()) {
                    WearableListView.this.mCanClick = true;
                }
            }
        });
    }

    public void animateToInitialPosition(final Runnable endAction) {
        View child = getChildAt(0);
        int scrollToMiddle = (getCentralViewTop() + this.mInitialOffset) - child.getTop();
        startScrollAnimation(scrollToMiddle, 150L, 0L, new SimpleAnimatorListener() { // from class: androidx.wear.ble.view.WearableListView.7
            @Override // androidx.wear.ble.view.SimpleAnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                Runnable runnable = endAction;
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    private void handleTouchDown(MotionEvent event) {
        Handler handler;
        if (this.mCanClick) {
            this.mTapPositionX = (int) event.getX();
            this.mTapPositionY = (int) event.getY();
            float rawY = event.getRawY();
            computeTapRegions(this.mTapRegions);
            float[] fArr = this.mTapRegions;
            if (rawY > fArr[0] && rawY < fArr[1]) {
                View view = getChildAt(findCenterViewIndex());
                if ((view instanceof OnCenterProximityListener) && (handler = getHandler()) != null) {
                    handler.removeCallbacks(this.mReleasedRunnable);
                    handler.postDelayed(this.mPressedRunnable, ViewConfiguration.getTapTimeout());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScrollVertically(int scroll) {
        scrollBy(0, scroll - this.mLastScrollChange);
        this.mLastScrollChange = scroll;
    }

    private int getAdjustedHeight() {
        return getAdjustedHeight(this);
    }

    private static int getAdjustedHeight(View v) {
        return (v.getHeight() - v.getPaddingBottom()) - v.getPaddingTop();
    }

    private void computeTapRegions(float[] tapRegions) {
        int[] iArr = this.mLocation;
        iArr[1] = 0;
        iArr[0] = 0;
        getLocationOnScreen(iArr);
        int mScreenTop = this.mLocation[1];
        int height = getHeight();
        tapRegions[0] = mScreenTop + (height * 0.33f);
        tapRegions[1] = mScreenTop + (height * 0.66999996f);
    }

    public boolean getMaximizeSingleItem() {
        return this.mMaximizeSingleItem;
    }

    public void setMaximizeSingleItem(boolean maximizeSingleItem) {
        this.mMaximizeSingleItem = maximizeSingleItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyChildrenAboutProximity(boolean animate) {
        LayoutManager layoutManager = (LayoutManager) getLayoutManager();
        int count = layoutManager.getChildCount();
        if (count == 0) {
            return;
        }
        int index = layoutManager.findCenterViewIndex();
        int i = 0;
        while (i < count) {
            View view = layoutManager.getChildAt(i);
            ViewHolder holder = getChildViewHolder(view);
            holder.onCenterProximity(i == index, animate);
            i++;
        }
        int position = getChildViewHolder(getChildAt(index)).getPosition();
        if (position != this.mPreviousCentral) {
            for (OnScrollListener listener : this.mOnScrollListeners) {
                listener.onCentralPositionChanged(position);
            }
            for (OnCentralPositionChangedListener listener2 : this.mOnCentralPositionChangedListeners) {
                listener2.onCentralPositionChanged(position);
            }
            this.mPreviousCentral = position;
        }
    }

    private class LayoutManager extends RecyclerView.LayoutManager {
        private int mAbsoluteScroll;
        private RecyclerView.SmoothScroller mDefaultSmoothScroller;
        private int mFirstPosition;
        private boolean mPushFirstHigher;
        private RecyclerView.SmoothScroller mSmoothScroller;
        private boolean mUseOldViewTop;
        private boolean mWasZoomedIn;

        private LayoutManager() {
            this.mUseOldViewTop = true;
            this.mWasZoomedIn = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int findCenterViewIndex() {
            int count = getChildCount();
            int index = -1;
            int closest = Integer.MAX_VALUE;
            int centerY = WearableListView.getCenterYPos(WearableListView.this);
            for (int i = 0; i < count; i++) {
                View child = WearableListView.this.getLayoutManager().getChildAt(i);
                int childCenterY = WearableListView.this.getTop() + WearableListView.getCenterYPos(child);
                int distance = Math.abs(centerY - childCenterY);
                if (distance < closest) {
                    closest = distance;
                    index = i;
                }
            }
            if (index == -1) {
                throw new IllegalStateException("Can't find central view.");
            }
            return index;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            int parentBottom = getHeight() - getPaddingBottom();
            int oldTop = WearableListView.this.getCentralViewTop() + WearableListView.this.mInitialOffset;
            if (this.mUseOldViewTop && getChildCount() > 0) {
                int index = findCenterViewIndex();
                int position = getPosition(getChildAt(index));
                if (position == -1) {
                    int i = 0;
                    int N = getChildCount();
                    while (true) {
                        if (index + i >= N && index - i < 0) {
                            break;
                        }
                        View child = getChildAt(index + i);
                        if (child != null && (position = getPosition(child)) != -1) {
                            index += i;
                            break;
                        }
                        View child2 = getChildAt(index - i);
                        if (child2 == null || (position = getPosition(child2)) == -1) {
                            i++;
                        } else {
                            index -= i;
                            break;
                        }
                    }
                }
                if (position == -1) {
                    oldTop = getChildAt(0).getTop();
                    int count = state.getItemCount();
                    while (true) {
                        int i2 = this.mFirstPosition;
                        if (i2 < count || i2 <= 0) {
                            break;
                        } else {
                            this.mFirstPosition = i2 - 1;
                        }
                    }
                } else {
                    if (!this.mWasZoomedIn) {
                        oldTop = getChildAt(index).getTop();
                    }
                    while (oldTop > getPaddingTop() && position > 0) {
                        position--;
                        oldTop -= WearableListView.this.getItemHeight();
                    }
                    if (position == 0 && oldTop > WearableListView.this.getCentralViewTop()) {
                        oldTop = WearableListView.this.getCentralViewTop();
                    }
                    this.mFirstPosition = position;
                }
            } else if (this.mPushFirstHigher) {
                oldTop = WearableListView.this.getCentralViewTop() - WearableListView.this.getItemHeight();
            }
            performLayoutChildren(recycler, state, parentBottom, oldTop);
            if (getChildCount() == 0) {
                setAbsoluteScroll(0);
            } else {
                View child3 = getChildAt(findCenterViewIndex());
                setAbsoluteScroll((child3.getTop() - WearableListView.this.getCentralViewTop()) + (getPosition(child3) * WearableListView.this.getItemHeight()));
            }
            this.mUseOldViewTop = true;
            this.mPushFirstHigher = false;
        }

        private void performLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state, int parentBottom, int top) {
            detachAndScrapAttachedViews(recycler);
            if (WearableListView.this.mMaximizeSingleItem && state.getItemCount() == 1) {
                performLayoutOneChild(recycler, parentBottom);
                this.mWasZoomedIn = true;
            } else {
                performLayoutMultipleChildren(recycler, state, parentBottom, top);
                this.mWasZoomedIn = false;
            }
            if (getChildCount() > 0) {
                WearableListView wearableListView = WearableListView.this;
                wearableListView.post(wearableListView.mNotifyChildrenPostLayoutRunnable);
            }
        }

        private void performLayoutOneChild(RecyclerView.Recycler recycler, int parentBottom) {
            int right = getWidth() - getPaddingRight();
            View v = recycler.getViewForPosition(getFirstPosition());
            addView(v, 0);
            measureZoomView(v);
            v.layout(getPaddingLeft(), getPaddingTop(), right, parentBottom);
        }

        private void performLayoutMultipleChildren(RecyclerView.Recycler recycler, RecyclerView.State state, int parentBottom, int top) {
            int left = getPaddingLeft();
            int right = getWidth() - getPaddingRight();
            int count = state.getItemCount();
            int i = 0;
            while (getFirstPosition() + i < count && top < parentBottom) {
                View v = recycler.getViewForPosition(getFirstPosition() + i);
                addView(v, i);
                measureThirdView(v);
                int bottom = WearableListView.this.getItemHeight() + top;
                v.layout(left, top, right, bottom);
                i++;
                top = bottom;
            }
        }

        private void setAbsoluteScroll(int absoluteScroll) {
            this.mAbsoluteScroll = absoluteScroll;
            for (OnScrollListener listener : WearableListView.this.mOnScrollListeners) {
                listener.onAbsoluteScrollChange(this.mAbsoluteScroll);
            }
        }

        private void measureView(View v, int height) {
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) v.getLayoutParams();
            int widthSpec = getChildMeasureSpec(getWidth(), getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin, lp.width, canScrollHorizontally());
            int heightSpec = getChildMeasureSpec(getHeight(), getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin, height, canScrollVertically());
            v.measure(widthSpec, heightSpec);
        }

        private void measureThirdView(View v) {
            measureView(v, (int) ((getHeight() / 3.0f) + 1.0f));
        }

        private void measureZoomView(View v) {
            measureView(v, getHeight());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public RecyclerView.LayoutParams generateDefaultLayoutParams() {
            return new RecyclerView.LayoutParams(-1, -2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean canScrollVertically() {
            return (getItemCount() == 1 && this.mWasZoomedIn) ? false : true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
            int maxScroll;
            if (getChildCount() == 0) {
                return 0;
            }
            int scrolled = 0;
            int left = getPaddingLeft();
            int right = getWidth() - getPaddingRight();
            if (dy < 0) {
                while (true) {
                    if (scrolled <= dy) {
                        break;
                    }
                    View topView = getChildAt(0);
                    if (getFirstPosition() > 0) {
                        int hangingTop = Math.max(-topView.getTop(), 0);
                        int scrollBy = Math.min(scrolled - dy, hangingTop);
                        scrolled -= scrollBy;
                        offsetChildrenVertical(scrollBy);
                        if (getFirstPosition() <= 0 || scrolled <= dy) {
                            break;
                        }
                        this.mFirstPosition--;
                        View v = recycler.getViewForPosition(getFirstPosition());
                        addView(v, 0);
                        measureThirdView(v);
                        int bottom = topView.getTop();
                        v.layout(left, bottom - WearableListView.this.getItemHeight(), right, bottom);
                    } else {
                        this.mPushFirstHigher = false;
                        if (WearableListView.this.mOverScrollListener == null) {
                            maxScroll = WearableListView.this.getTopViewMaxTop();
                        } else {
                            maxScroll = getHeight();
                        }
                        int scrollBy2 = Math.min((-dy) + scrolled, maxScroll - topView.getTop());
                        scrolled -= scrollBy2;
                        offsetChildrenVertical(scrollBy2);
                    }
                }
            } else if (dy > 0) {
                int parentHeight = getHeight();
                while (true) {
                    if (scrolled >= dy) {
                        break;
                    }
                    View bottomView = getChildAt(getChildCount() - 1);
                    if (state.getItemCount() > this.mFirstPosition + getChildCount()) {
                        int hangingBottom = Math.max(bottomView.getBottom() - parentHeight, 0);
                        int scrollBy3 = -Math.min(dy - scrolled, hangingBottom);
                        scrolled -= scrollBy3;
                        offsetChildrenVertical(scrollBy3);
                        if (scrolled >= dy) {
                            break;
                        }
                        View v2 = recycler.getViewForPosition(this.mFirstPosition + getChildCount());
                        int top = getChildAt(getChildCount() - 1).getBottom();
                        addView(v2);
                        measureThirdView(v2);
                        v2.layout(left, top, right, WearableListView.this.getItemHeight() + top);
                    } else {
                        int scrollBy4 = Math.max((-dy) + scrolled, (getHeight() / 2) - bottomView.getBottom());
                        scrolled -= scrollBy4;
                        offsetChildrenVertical(scrollBy4);
                        break;
                    }
                }
            }
            recycleViewsOutOfBounds(recycler);
            setAbsoluteScroll(this.mAbsoluteScroll + scrolled);
            return scrolled;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void scrollToPosition(int position) {
            this.mUseOldViewTop = false;
            if (position > 0) {
                this.mFirstPosition = position - 1;
                this.mPushFirstHigher = true;
            } else {
                this.mFirstPosition = position;
                this.mPushFirstHigher = false;
            }
            requestLayout();
        }

        public void setCustomSmoothScroller(RecyclerView.SmoothScroller smoothScroller) {
            this.mSmoothScroller = smoothScroller;
        }

        public void clearCustomSmoothScroller() {
            this.mSmoothScroller = null;
        }

        public RecyclerView.SmoothScroller getDefaultSmoothScroller(RecyclerView recyclerView) {
            if (this.mDefaultSmoothScroller == null) {
                this.mDefaultSmoothScroller = new SmoothScroller(recyclerView.getContext(), this);
            }
            return this.mDefaultSmoothScroller;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
            RecyclerView.SmoothScroller scroller = this.mSmoothScroller;
            if (scroller == null) {
                scroller = getDefaultSmoothScroller(recyclerView);
            }
            scroller.setTargetPosition(position);
            startSmoothScroll(scroller);
        }

        private void recycleViewsOutOfBounds(RecyclerView.Recycler recycler) {
            int childCount = getChildCount();
            int parentWidth = getWidth();
            int parentHeight = getHeight();
            boolean foundFirst = false;
            int first = 0;
            int last = 0;
            for (int i = 0; i < childCount; i++) {
                View v = getChildAt(i);
                if (v.hasFocus() || (v.getRight() >= 0 && v.getLeft() <= parentWidth && v.getBottom() >= 0 && v.getTop() <= parentHeight)) {
                    if (!foundFirst) {
                        first = i;
                        foundFirst = true;
                    }
                    last = i;
                }
            }
            for (int i2 = childCount - 1; i2 > last; i2--) {
                removeAndRecycleViewAt(i2, recycler);
            }
            for (int i3 = first - 1; i3 >= 0; i3--) {
                removeAndRecycleViewAt(i3, recycler);
            }
            int i4 = getChildCount();
            if (i4 == 0) {
                this.mFirstPosition = 0;
            } else if (first > 0) {
                this.mPushFirstHigher = true;
                this.mFirstPosition += first;
            }
        }

        public int getFirstPosition() {
            return this.mFirstPosition;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onAdapterChanged(RecyclerView.Adapter oldAdapter, RecyclerView.Adapter newAdapter) {
            removeAllViews();
        }
    }

    private static class SmoothScroller extends LinearSmoothScroller {
        private static final float MILLISECONDS_PER_INCH = 100.0f;
        private final LayoutManager mLayoutManager;

        public SmoothScroller(Context context, LayoutManager manager) {
            super(context);
            this.mLayoutManager = manager;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        protected void onStart() {
            super.onStart();
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
            return ((boxStart + boxEnd) / 2) - ((viewStart + viewEnd) / 2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
        public PointF computeScrollVectorForPosition(int targetPosition) {
            if (targetPosition < this.mLayoutManager.getFirstPosition()) {
                return new PointF(0.0f, -1.0f);
            }
            return new PointF(0.0f, 1.0f);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        protected void onCenterProximity(boolean isCentralItem, boolean animate) {
            if (!(this.itemView instanceof OnCenterProximityListener)) {
                return;
            }
            OnCenterProximityListener item = (OnCenterProximityListener) this.itemView;
            if (isCentralItem) {
                item.onCenterPosition(animate);
            } else {
                item.onNonCenterPosition(animate);
            }
        }
    }

    private class SetScrollVerticallyProperty extends Property<WearableListView, Integer> {
        public SetScrollVerticallyProperty() {
            super(Integer.class, "scrollVertically");
        }

        @Override // android.util.Property
        public Integer get(WearableListView wearableListView) {
            return Integer.valueOf(wearableListView.mLastScrollChange);
        }

        @Override // android.util.Property
        public void set(WearableListView wearableListView, Integer value) {
            wearableListView.setScrollVertically(value.intValue());
        }
    }
}
