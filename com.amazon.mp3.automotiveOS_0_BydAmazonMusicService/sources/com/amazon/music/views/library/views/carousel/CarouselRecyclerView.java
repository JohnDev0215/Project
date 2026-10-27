package com.amazon.music.views.library.views.carousel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.amazon.music.views.library.R$styleable;
import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarouselRecyclerView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010I\u001a\u00020H¢\u0006\u0004\bJ\u0010KJ\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0016J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0017\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\u0018\u0010\u0015J\u0019\u0010\u001e\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010!\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010#\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\"\u0010 J\u000f\u0010%\u001a\u00020\u0004H\u0000¢\u0006\u0004\b$\u0010 J\u0017\u0010(\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u001aH\u0000¢\u0006\u0004\b'\u0010\u001dJ\u0017\u0010-\u001a\u00020\u00042\u0006\u0010*\u001a\u00020)H\u0000¢\u0006\u0004\b+\u0010,R\u0014\u0010.\u001a\u00020\u000b8\u0002X\u0082D¢\u0006\u0006\n\u0004\b.\u0010/R\u0014\u00100\u001a\u00020\u000b8\u0002X\u0082D¢\u0006\u0006\n\u0004\b0\u0010/R\u0014\u00101\u001a\u00020\u000b8\u0002X\u0082D¢\u0006\u0006\n\u0004\b1\u0010/R\u0016\u0010\u0017\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010/R\"\u00102\u001a\u00020\u000e8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b2\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u0016\u00109\u001a\u0002088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u0016\u0010;\u001a\u0002088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010:R\u0016\u0010\u0013\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010/R\"\u0010<\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b<\u0010/\u001a\u0004\b=\u0010>\"\u0004\b?\u0010\u0015R\u0016\u0010A\u001a\u00020@8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bA\u0010BR\u001a\u0010D\u001a\u00020C8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G¨\u0006L"}, d2 = {"Lcom/amazon/music/views/library/views/carousel/CarouselRecyclerView;", "Landroidx/recyclerview/widget/RecyclerView;", "Landroid/content/res/TypedArray;", "typedArray", "", "init", "Landroidx/recyclerview/widget/LinearLayoutManager;", "linearLayoutManager", "prepareAutoScrollingComponent$DMMViewLibrary_release", "(Landroidx/recyclerview/widget/LinearLayoutManager;)V", "prepareAutoScrollingComponent", "", "velocityX", "velocityY", "", "fling", "Landroid/view/MotionEvent;", "motionEvent", "onInterceptTouchEvent", "itemPadding", "setItemPadding$DMMViewLibrary_release", "(I)V", "setItemPadding", "stepTimeMilliseconds", "setStepTimeMilliseconds$DMMViewLibrary_release", "setStepTimeMilliseconds", "Landroid/view/View;", "v", "scrollViewToCenter$DMMViewLibrary_release", "(Landroid/view/View;)V", "scrollViewToCenter", "start$DMMViewLibrary_release", "()V", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "stop$DMMViewLibrary_release", AsmString.METHOD_FIREBASE_PERF_TRACE_STOP, "pause$DMMViewLibrary_release", "pause", "target", "postScrollViewToCenter$DMMViewLibrary_release", "postScrollViewToCenter", "Lcom/amazon/music/views/library/views/carousel/CarouselAdapter;", "carouselAdapter", "setAdapter$DMMViewLibrary_release", "(Lcom/amazon/music/views/library/views/carousel/CarouselAdapter;)V", "setAdapter", "NEXT_ITEM_SWIPE_VELOCITY", "I", "DEFAULT_STEP_TIME_MILLISECONDS", "DEFAULT_NUM_PREFETCH_VIEWS", "isStarted", "Z", "isStarted$DMMViewLibrary_release", "()Z", "setStarted$DMMViewLibrary_release", "(Z)V", "", "startX", "F", "startY", "numPrefetchViews", "getNumPrefetchViews", "()I", "setNumPrefetchViews", "Ljava/lang/Runnable;", "stepScroller", "Ljava/lang/Runnable;", "Landroidx/core/view/GestureDetectorCompat;", "gestureDetectorCompat", "Landroidx/core/view/GestureDetectorCompat;", "getGestureDetectorCompat$DMMViewLibrary_release", "()Landroidx/core/view/GestureDetectorCompat;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class CarouselRecyclerView extends RecyclerView {
    private final int DEFAULT_NUM_PREFETCH_VIEWS;
    private final int DEFAULT_STEP_TIME_MILLISECONDS;
    private final int NEXT_ITEM_SWIPE_VELOCITY;
    private final GestureDetectorCompat gestureDetectorCompat;
    private boolean isStarted;
    private int itemPadding;
    private int numPrefetchViews;
    private float startX;
    private float startY;
    private Runnable stepScroller;
    private int stepTimeMilliseconds;

    /* JADX INFO: renamed from: isStarted$DMMViewLibrary_release, reason: from getter */
    public final boolean getIsStarted() {
        return this.isStarted;
    }

    public final void setStarted$DMMViewLibrary_release(boolean z) {
        this.isStarted = z;
    }

    public final int getNumPrefetchViews() {
        return this.numPrefetchViews;
    }

    public final void setNumPrefetchViews(int i) {
        this.numPrefetchViews = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarouselRecyclerView(Context context) {
        super(context, null);
        Intrinsics.checkNotNullParameter(context, "context");
        this.NEXT_ITEM_SWIPE_VELOCITY = 1000;
        this.DEFAULT_STEP_TIME_MILLISECONDS = PathInterpolatorCompat.MAX_NUM_POINTS;
        this.DEFAULT_NUM_PREFETCH_VIEWS = 1;
        this.stepTimeMilliseconds = PathInterpolatorCompat.MAX_NUM_POINTS;
        this.gestureDetectorCompat = new GestureDetectorCompat(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.amazon.music.views.library.views.carousel.CarouselRecyclerView$gestureDetectorCompat$1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent e) {
                Intrinsics.checkNotNullParameter(e, "e");
                return true;
            }
        });
        init(null);
    }

    /* JADX INFO: renamed from: getGestureDetectorCompat$DMMViewLibrary_release, reason: from getter */
    public final GestureDetectorCompat getGestureDetectorCompat() {
        return this.gestureDetectorCompat;
    }

    private final void init(TypedArray typedArray) {
        this.numPrefetchViews = this.DEFAULT_NUM_PREFETCH_VIEWS;
        if (typedArray != null) {
            this.itemPadding = typedArray.getDimensionPixelSize(R$styleable.Carousel_item_padding, 0);
            this.stepTimeMilliseconds = typedArray.getInteger(R$styleable.Carousel_step_time_milliseconds, this.DEFAULT_STEP_TIME_MILLISECONDS);
        }
        addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.amazon.music.views.library.views.carousel.CarouselRecyclerView.init.1
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                Intrinsics.checkNotNullParameter(outRect, "outRect");
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(state, "state");
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = CarouselRecyclerView.this.itemPadding / 2;
                outRect.right = CarouselRecyclerView.this.itemPadding / 2;
            }
        });
    }

    public final void prepareAutoScrollingComponent$DMMViewLibrary_release(LinearLayoutManager linearLayoutManager) {
        Intrinsics.checkNotNullParameter(linearLayoutManager, "linearLayoutManager");
        addOnItemTouchListener(new CarouselOnItemTouchListener(linearLayoutManager, this));
        this.stepScroller = new StepScrollerRunnable(linearLayoutManager, this);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean fling(int velocityX, int velocityY) {
        int width = getWidth();
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        View viewFindViewByPosition = linearLayoutManager.findViewByPosition(linearLayoutManager.findLastVisibleItemPosition());
        View viewFindViewByPosition2 = linearLayoutManager.findViewByPosition(linearLayoutManager.findFirstVisibleItemPosition());
        Intrinsics.checkNotNull(viewFindViewByPosition);
        int width2 = (width - viewFindViewByPosition.getWidth()) / 2;
        Intrinsics.checkNotNull(viewFindViewByPosition2);
        int width3 = ((width - viewFindViewByPosition2.getWidth()) / 2) + viewFindViewByPosition2.getWidth();
        int left = viewFindViewByPosition.getLeft();
        int right = viewFindViewByPosition2.getRight();
        int i = left - width2;
        int i2 = width3 - right;
        if (Math.abs(velocityX) >= this.NEXT_ITEM_SWIPE_VELOCITY) {
            if (velocityX > 0) {
                smoothScrollBy(i, 0);
                return true;
            }
            smoothScrollBy(-i2, 0);
            return true;
        }
        int i3 = width / 2;
        if (left > i3) {
            smoothScrollBy(-i2, 0);
            return true;
        }
        if (right < i3) {
            smoothScrollBy(i, 0);
            return true;
        }
        if (velocityX > 0) {
            smoothScrollBy(-i2, 0);
            return true;
        }
        smoothScrollBy(i, 0);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        int action = motionEvent.getAction();
        if (action == 0) {
            this.startX = motionEvent.getX();
            this.startY = motionEvent.getY();
        } else if (action == 2) {
            if (Math.abs(motionEvent.getX() - this.startX) >= Math.abs(motionEvent.getY() - this.startY)) {
                getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public final void setItemPadding$DMMViewLibrary_release(int itemPadding) {
        this.itemPadding = itemPadding;
    }

    public final void setStepTimeMilliseconds$DMMViewLibrary_release(int stepTimeMilliseconds) {
        this.stepTimeMilliseconds = stepTimeMilliseconds;
    }

    public final void scrollViewToCenter$DMMViewLibrary_release(View v) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        if (v != null) {
            smoothScrollBy(v.getLeft() - ((getWidth() - v.getWidth()) / 2), 0);
        } else {
            View viewFindViewByPosition = linearLayoutManager.findViewByPosition(linearLayoutManager.findFirstVisibleItemPosition());
            if (viewFindViewByPosition != null) {
                smoothScrollBy(viewFindViewByPosition.getRight() + this.itemPadding, 0);
            }
        }
        Runnable runnable = this.stepScroller;
        Runnable runnable2 = null;
        if (runnable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stepScroller");
            runnable = null;
        }
        removeCallbacks(runnable);
        if (this.isStarted) {
            Runnable runnable3 = this.stepScroller;
            if (runnable3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stepScroller");
            } else {
                runnable2 = runnable3;
            }
            postDelayed(runnable2, this.stepTimeMilliseconds);
        }
    }

    public final void start$DMMViewLibrary_release() {
        this.isStarted = true;
        Runnable runnable = this.stepScroller;
        if (runnable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stepScroller");
            runnable = null;
        }
        postDelayed(runnable, this.stepTimeMilliseconds);
    }

    public final void stop$DMMViewLibrary_release() {
        this.isStarted = false;
        Runnable runnable = this.stepScroller;
        if (runnable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stepScroller");
            runnable = null;
        }
        removeCallbacks(runnable);
    }

    public final void pause$DMMViewLibrary_release() {
        Runnable runnable = this.stepScroller;
        if (runnable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stepScroller");
            runnable = null;
        }
        removeCallbacks(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: postScrollViewToCenter$lambda-0, reason: not valid java name */
    public static final void m2440postScrollViewToCenter$lambda0(CarouselRecyclerView this$0, View target) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(target, "$target");
        this$0.scrollViewToCenter$DMMViewLibrary_release(target);
    }

    public final void postScrollViewToCenter$DMMViewLibrary_release(final View target) {
        Intrinsics.checkNotNullParameter(target, "target");
        post(new Runnable() { // from class: com.amazon.music.views.library.views.carousel.CarouselRecyclerView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                CarouselRecyclerView.m2440postScrollViewToCenter$lambda0(this.f$0, target);
            }
        });
    }

    public final void setAdapter$DMMViewLibrary_release(final CarouselAdapter carouselAdapter) {
        Intrinsics.checkNotNullParameter(carouselAdapter, "carouselAdapter");
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        }
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        setAdapter(carouselAdapter);
        post(new Runnable() { // from class: com.amazon.music.views.library.views.carousel.CarouselRecyclerView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CarouselRecyclerView.m2441setAdapter$lambda1(linearLayoutManager, this, carouselAdapter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setAdapter$lambda-1, reason: not valid java name */
    public static final void m2441setAdapter$lambda1(LinearLayoutManager linearLayoutManager, CarouselRecyclerView this$0, CarouselAdapter carouselAdapter) {
        Intrinsics.checkNotNullParameter(linearLayoutManager, "$linearLayoutManager");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(carouselAdapter, "$carouselAdapter");
        View viewFindViewByPosition = linearLayoutManager.findViewByPosition(linearLayoutManager.findFirstVisibleItemPosition());
        if (viewFindViewByPosition != null) {
            int left = viewFindViewByPosition.getLeft();
            linearLayoutManager.scrollToPositionWithOffset(carouselAdapter.getMiddle$DMMViewLibrary_release(), ((this$0.getWidth() - viewFindViewByPosition.getWidth()) / 2) - left);
        }
    }
}
