package com.amazon.music.views.library.recyclerview.indexer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.ColorInt;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$dimen;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import rx.Completable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/* JADX INFO: compiled from: FastScroller.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 ^2\u00020\u00012\u00020\u0002:\u0003_`^B\u001d\b\u0007\u0012\u0006\u0010Y\u001a\u00020X\u0012\n\b\u0002\u0010[\u001a\u0004\u0018\u00010Z¢\u0006\u0004\b\\\u0010]J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002J\b\u0010\f\u001a\u00020\u0005H\u0002J\u001a\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0001\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\nH\u0002J\u0010\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0017\u001a\u00020\u0005H\u0002J\b\u0010\u0018\u001a\u00020\u0005H\u0002J\b\u0010\u0019\u001a\u00020\u0005H\u0002J\b\u0010\u001a\u001a\u00020\u0005H\u0002J\b\u0010\u001b\u001a\u00020\u0005H\u0014J\u0012\u0010\u001d\u001a\u00020\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\rH\u0016J0\u0010$\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0014J\u0010\u0010%\u001a\u00020\u00052\b\b\u0001\u0010\u0010\u001a\u00020\u000fJ\u0010\u0010&\u001a\u00020\u00052\b\b\u0001\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010)\u001a\u00020\u00052\u0006\u0010(\u001a\u00020'J\u0014\u0010,\u001a\u00020\u00052\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00050*J\u000e\u0010-\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\nJ\u000e\u0010/\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u000fJ\u0018\u00100\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020'2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u00101\u001a\u00020\u00052\u0006\u0010(\u001a\u00020'2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u00103\u001a\u00020\u00052\u0006\u00102\u001a\u00020\u001eH\u0016J\u000e\u00104\u001a\u00020\u00052\u0006\u0010(\u001a\u00020'R\u0018\u00106\u001a\u0004\u0018\u0001058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u00107R\u001c\u00108\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\f\n\u0004\b8\u00109\u0012\u0004\b:\u0010;R\u001c\u0010<\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\f\n\u0004\b<\u00109\u0012\u0004\b=\u0010;R\u001c\u0010>\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\f\n\u0004\b>\u00109\u0012\u0004\b?\u0010;R\u0016\u0010@\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u001e\u0010C\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010'0B8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bC\u0010DR\u001c\u0010G\u001a\n F*\u0004\u0018\u00010E0E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bG\u0010HR\u0016\u0010J\u001a\u00020I8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR \u0010M\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050*0L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bM\u0010NR*\u0010\u0004\u001a\u00020\u00032\u0006\u0010O\u001a\u00020\u00038\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010P\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u0014\u0010V\u001a\u00020U8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010W¨\u0006a"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/indexer/FastScroller;", "Landroid/widget/RelativeLayout;", "Landroidx/recyclerview/widget/RecyclerView$OnItemTouchListener;", "Lcom/amazon/music/views/library/recyclerview/indexer/ScrollerViewProvider;", "viewProvider", "", "createAndAddViews", "initHandleMovement", "Landroid/view/MotionEvent;", NotificationCompat.CATEGORY_EVENT, "", "getRelativeTouchPosition", "invalidateStyle", "Landroid/view/View;", "view", "", "color", "setBackgroundTint", "relativePos", "setRecyclerViewPosition", "", "delay", "resetHideDelay", "show", "requestRedraw", "cancelHide", "hide", "onAttachedToWindow", "child", "onViewRemoved", "", "changed", "l", "t", "r", "b", "onLayout", "setHandleColor", "setScrollBarColor", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "setRecyclerView", "Lkotlin/Function0;", "action", "addScrollStoppedListener", "setScrollerPosition", "state", "setState", "onInterceptTouchEvent", "onTouchEvent", "disallowIntercept", "onRequestDisallowInterceptTouchEvent", "updateHandlePosition", "Lrx/Subscription;", "subscription", "Lrx/Subscription;", "animationState", "I", "getAnimationState$annotations", "()V", "currentState", "getCurrentState$annotations", "dragState", "getDragState$annotations", "verticalDragY", "F", "Ljava/lang/ref/WeakReference;", "recyclerViewRef", "Ljava/lang/ref/WeakReference;", "Landroid/animation/ValueAnimator;", "kotlin.jvm.PlatformType", "showHideAnimator", "Landroid/animation/ValueAnimator;", "Lcom/amazon/music/views/library/recyclerview/indexer/FastScrollViews;", "fastScrollViews", "Lcom/amazon/music/views/library/recyclerview/indexer/FastScrollViews;", "", "scrollStoppedListeners", "Ljava/util/Set;", "value", "Lcom/amazon/music/views/library/recyclerview/indexer/ScrollerViewProvider;", "getViewProvider", "()Lcom/amazon/music/views/library/recyclerview/indexer/ScrollerViewProvider;", "setViewProvider", "(Lcom/amazon/music/views/library/recyclerview/indexer/ScrollerViewProvider;)V", "Ljava/lang/Runnable;", "hideRunnable", "Ljava/lang/Runnable;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "AnimatorListener", "AnimatorUpdater", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
@SuppressLint({"ViewConstructor"})
public final class FastScroller extends RelativeLayout implements RecyclerView.OnItemTouchListener {
    private int animationState;
    private int currentState;
    private int dragState;
    private FastScrollViews fastScrollViews;
    private final Runnable hideRunnable;
    private WeakReference<RecyclerView> recyclerViewRef;
    private final Set<Function0<Unit>> scrollStoppedListeners;
    private final ValueAnimator showHideAnimator;
    private Subscription subscription;
    private float verticalDragY;
    private ScrollerViewProvider viewProvider;

    private static /* synthetic */ void getAnimationState$annotations() {
    }

    private static /* synthetic */ void getCurrentState$annotations() {
    }

    private static /* synthetic */ void getDragState$annotations() {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public FastScroller(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.recyclerViewRef = new WeakReference<>(null);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.showHideAnimator = valueAnimatorOfFloat;
        this.fastScrollViews = new FastScrollViews(ContextCompat.getColor(context, R$color.tertiary), ContextCompat.getColor(context, R$color.black_60), null, null);
        this.scrollStoppedListeners = new LinkedHashSet();
        this.viewProvider = new DefaultScrollerViewProvider();
        setClipChildren(false);
        valueAnimatorOfFloat.addListener(new AnimatorListener(this));
        valueAnimatorOfFloat.addUpdateListener(new AnimatorUpdater(this));
        setState(0);
        this.hideRunnable = new Runnable() { // from class: com.amazon.music.views.library.recyclerview.indexer.FastScroller$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FastScroller.m2387hideRunnable$lambda11(this.f$0);
            }
        };
    }

    public final ScrollerViewProvider getViewProvider() {
        return this.viewProvider;
    }

    public final void setViewProvider(ScrollerViewProvider value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!Intrinsics.areEqual(this.viewProvider, value) && isAttachedToWindow()) {
            createAndAddViews(value);
        }
        this.viewProvider = value;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        createAndAddViews(this.viewProvider);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        this.scrollStoppedListeners.clear();
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        RecyclerView recyclerView;
        super.onLayout(changed, l, t, r, b);
        initHandleMovement();
        invalidateStyle();
        if (isInEditMode() || (recyclerView = this.recyclerViewRef.get()) == null) {
            return;
        }
        updateHandlePosition(recyclerView);
    }

    public final void setHandleColor(@ColorInt int color) {
        this.fastScrollViews.setScrollHandleColor(color);
        invalidateStyle();
    }

    public final void setScrollBarColor(@ColorInt int color) {
        this.fastScrollViews.setScrollBarColor(color);
        invalidateStyle();
    }

    public final void setRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        this.recyclerViewRef = new WeakReference<>(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.music.views.library.recyclerview.indexer.FastScroller.setRecyclerView.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                FastScroller.this.updateHandlePosition(recyclerView2);
            }
        });
    }

    private final synchronized void createAndAddViews(ScrollerViewProvider viewProvider) {
        removeAllViews();
        viewProvider.setFastScroller(this);
        View viewProvideHandleView = viewProvider.provideHandleView();
        View viewProvideScrollBar = viewProvider.provideScrollBar();
        Context context = getContext();
        if (context != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(context.getResources().getDimensionPixelSize(R$dimen.spacer_16), context.getResources().getDimensionPixelSize(R$dimen.spacer_24));
            layoutParams.addRule(21);
            if (viewProvideHandleView != null) {
                viewProvideHandleView.setClipToOutline(false);
            }
            if (viewProvideHandleView != null) {
                viewProvideHandleView.setLayoutParams(layoutParams);
            }
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(context.getResources().getDimensionPixelSize(R$dimen.spacer_8), -1);
            layoutParams2.addRule(21);
            if (viewProvideScrollBar != null) {
                viewProvideScrollBar.setLayoutParams(layoutParams2);
            }
        }
        if (viewProvideScrollBar != null) {
            addView(viewProvideScrollBar);
        }
        if (viewProvideHandleView != null) {
            addView(viewProvideHandleView);
        }
        if (viewProvideHandleView != null && viewProvideScrollBar != null) {
            this.fastScrollViews = new FastScrollViews(ContextCompat.getColor(getContext(), R$color.tertiary), 0, viewProvideHandleView, viewProvideScrollBar, 2, null);
        }
    }

    public final void addScrollStoppedListener(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        this.scrollStoppedListeners.add(action);
    }

    public final void setScrollerPosition(float relativePos) {
        float height = getHeight() - (this.fastScrollViews.getScrollHandle() == null ? 0.0f : r1.getHeight());
        View scrollHandle = this.fastScrollViews.getScrollHandle();
        if (scrollHandle == null) {
            return;
        }
        scrollHandle.setY(Math.max(0.0f, Math.min(height, relativePos * height)));
    }

    private final void initHandleMovement() {
        RecyclerView recyclerView = this.recyclerViewRef.get();
        if (recyclerView != null) {
            recyclerView.removeOnItemTouchListener(this);
        }
        RecyclerView recyclerView2 = this.recyclerViewRef.get();
        if (recyclerView2 == null) {
            return;
        }
        recyclerView2.addOnItemTouchListener(this);
    }

    private final float getRelativeTouchPosition(MotionEvent event) {
        float rawY = event.getRawY();
        View scrollHandle = this.fastScrollViews.getScrollHandle();
        float windowY = rawY - (scrollHandle == null ? 0.0f : ViewExtensions.getWindowY(scrollHandle));
        int height = getHeight();
        View scrollHandle2 = this.fastScrollViews.getScrollHandle();
        int height2 = height - (scrollHandle2 == null ? 0 : scrollHandle2.getHeight());
        if (height2 == 0) {
            return 0.0f;
        }
        return windowY / height2;
    }

    private final void invalidateStyle() {
        View scrollBar;
        View scrollHandle;
        if (this.fastScrollViews.getScrollHandleColor() != -1 && (scrollHandle = this.fastScrollViews.getScrollHandle()) != null) {
            setBackgroundTint(scrollHandle, this.fastScrollViews.getScrollHandleColor());
        }
        if (this.fastScrollViews.getScrollBarColor() == -1 || (scrollBar = this.fastScrollViews.getScrollBar()) == null) {
            return;
        }
        setBackgroundTint(scrollBar, this.fastScrollViews.getScrollBarColor());
    }

    private final void setBackgroundTint(View view, @ColorInt int color) {
        Drawable drawableWrap = DrawableCompat.wrap(view.getBackground());
        if (drawableWrap == null) {
            return;
        }
        DrawableCompat.setTint(drawableWrap.mutate(), color);
        view.setBackground(drawableWrap);
    }

    private final int setRecyclerViewPosition(float relativePos) {
        RecyclerView.Adapter adapter;
        RecyclerView recyclerView = this.recyclerViewRef.get();
        int itemCount = (recyclerView == null || (adapter = recyclerView.getAdapter()) == null) ? 0 : adapter.getItemCount();
        if (itemCount == 0) {
            return 0;
        }
        int iMin = Math.min(itemCount - 1, Math.max(0, (int) (relativePos * itemCount)));
        RecyclerView recyclerView2 = this.recyclerViewRef.get();
        if (recyclerView2 != null) {
            recyclerView2.scrollToPosition(iMin);
        }
        Subscription subscription = this.subscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.subscription = Completable.timer(600L, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(new Action0() { // from class: com.amazon.music.views.library.recyclerview.indexer.FastScroller$$ExternalSyntheticLambda1
            @Override // rx.functions.Action0
            public final void call() {
                FastScroller.m2388setRecyclerViewPosition$lambda10(this.f$0);
            }
        });
        return iMin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setRecyclerViewPosition$lambda-10, reason: not valid java name */
    public static final void m2388setRecyclerViewPosition$lambda10(FastScroller this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Iterator<T> it = this$0.scrollStoppedListeners.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
    }

    public final void setState(int state) {
        if (state == 2 && this.currentState != 2) {
            cancelHide();
        }
        if (state == 0) {
            requestRedraw();
        } else {
            show();
        }
        if (this.currentState == 2 && state != 2) {
            resetHideDelay(1200L);
        } else if (state == 1) {
            resetHideDelay(1500L);
        }
        this.currentState = state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: hideRunnable$lambda-11, reason: not valid java name */
    public static final void m2387hideRunnable$lambda11(FastScroller this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hide();
    }

    private final void resetHideDelay(long delay) {
        cancelHide();
        RecyclerView recyclerView = this.recyclerViewRef.get();
        if (recyclerView == null) {
            return;
        }
        recyclerView.postDelayed(this.hideRunnable, delay);
    }

    private final void show() {
        int i = this.animationState;
        if (i == 0) {
            this.animationState = 1;
            ValueAnimator valueAnimator = this.showHideAnimator;
            float[] fArr = new float[2];
            Object animatedValue = valueAnimator.getAnimatedValue();
            if (animatedValue == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
            }
            fArr[0] = ((Float) animatedValue).floatValue();
            fArr[1] = 1.0f;
            valueAnimator.setFloatValues(fArr);
            this.showHideAnimator.setDuration(500L);
            this.showHideAnimator.setStartDelay(0L);
            this.showHideAnimator.start();
            return;
        }
        if (i != 3) {
            return;
        }
        this.showHideAnimator.cancel();
        this.animationState = 1;
        ValueAnimator valueAnimator2 = this.showHideAnimator;
        float[] fArr2 = new float[2];
        Object animatedValue2 = valueAnimator2.getAnimatedValue();
        if (animatedValue2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
        }
        fArr2[0] = ((Float) animatedValue2).floatValue();
        fArr2[1] = 1.0f;
        valueAnimator2.setFloatValues(fArr2);
        this.showHideAnimator.setDuration(500L);
        this.showHideAnimator.setStartDelay(0L);
        this.showHideAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestRedraw() {
        View scrollHandle = this.fastScrollViews.getScrollHandle();
        if (scrollHandle != null) {
            scrollHandle.invalidate();
        }
        View scrollBar = this.fastScrollViews.getScrollBar();
        if (scrollBar == null) {
            return;
        }
        scrollBar.invalidate();
    }

    private final void cancelHide() {
        RecyclerView recyclerView = this.recyclerViewRef.get();
        if (recyclerView == null) {
            return;
        }
        recyclerView.removeCallbacks(this.hideRunnable);
    }

    private final void hide() {
        int i = this.animationState;
        if (i == 1) {
            this.showHideAnimator.cancel();
            this.animationState = 3;
            ValueAnimator valueAnimator = this.showHideAnimator;
            float[] fArr = new float[2];
            Object animatedValue = valueAnimator.getAnimatedValue();
            if (animatedValue == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
            }
            fArr[0] = ((Float) animatedValue).floatValue();
            fArr[1] = 0.0f;
            valueAnimator.setFloatValues(fArr);
            this.showHideAnimator.setDuration(500L);
            this.showHideAnimator.start();
            return;
        }
        if (i != 2) {
            return;
        }
        this.animationState = 3;
        ValueAnimator valueAnimator2 = this.showHideAnimator;
        float[] fArr2 = new float[2];
        Object animatedValue2 = valueAnimator2.getAnimatedValue();
        if (animatedValue2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
        }
        fArr2[0] = ((Float) animatedValue2).floatValue();
        fArr2[1] = 0.0f;
        valueAnimator2.setFloatValues(fArr2);
        this.showHideAnimator.setDuration(500L);
        this.showHideAnimator.start();
    }

    /* JADX INFO: compiled from: FastScroller.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/indexer/FastScroller$AnimatorListener;", "Landroid/animation/AnimatorListenerAdapter;", "(Lcom/amazon/music/views/library/recyclerview/indexer/FastScroller;)V", "canceled", "", "onAnimationCancel", "", "animation", "Landroid/animation/Animator;", "onAnimationEnd", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class AnimatorListener extends AnimatorListenerAdapter {
        private boolean canceled;
        final /* synthetic */ FastScroller this$0;

        public AnimatorListener(FastScroller this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            Intrinsics.checkNotNullParameter(animation, "animation");
            if (!this.canceled) {
                Object animatedValue = this.this$0.showHideAnimator.getAnimatedValue();
                if (animatedValue == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
                }
                if (((Float) animatedValue).floatValue() == 0.0f) {
                    this.this$0.animationState = 0;
                    this.this$0.setState(0);
                    return;
                } else {
                    this.this$0.animationState = 2;
                    this.this$0.requestRedraw();
                    return;
                }
            }
            this.canceled = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animation) {
            Intrinsics.checkNotNullParameter(animation, "animation");
            this.canceled = true;
        }
    }

    /* JADX INFO: compiled from: FastScroller.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/indexer/FastScroller$AnimatorUpdater;", "Landroid/animation/ValueAnimator$AnimatorUpdateListener;", "(Lcom/amazon/music/views/library/recyclerview/indexer/FastScroller;)V", "onAnimationUpdate", "", "valueAnimator", "Landroid/animation/ValueAnimator;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class AnimatorUpdater implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ FastScroller this$0;

        public AnimatorUpdater(FastScroller this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            Intrinsics.checkNotNullParameter(valueAnimator, "valueAnimator");
            Object animatedValue = valueAnimator.getAnimatedValue();
            if (animatedValue != null) {
                float fFloatValue = ((Float) animatedValue).floatValue();
                View scrollHandle = this.this$0.fastScrollViews.getScrollHandle();
                if (scrollHandle != null) {
                    scrollHandle.setAlpha(fFloatValue);
                }
                View scrollBar = this.this$0.fastScrollViews.getScrollBar();
                if (scrollBar != null) {
                    scrollBar.setAlpha(fFloatValue);
                }
                this.this$0.requestRedraw();
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent event) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(event, "event");
        int i = this.currentState;
        if (i == 1) {
            View scrollHandle = this.fastScrollViews.getScrollHandle();
            boolean z = scrollHandle != null && ViewExtensions.isPointInsideBounds(scrollHandle, event.getX(), event.getY());
            if (event.getAction() != 0 || !z) {
                return false;
            }
            if (z) {
                this.dragState = 2;
                this.verticalDragY = event.getY();
            }
            setState(2);
        } else if (i != 2) {
            return false;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent event) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(event, "event");
        if (this.currentState == 0) {
            return;
        }
        boolean z = false;
        if (event.getAction() == 0) {
            View scrollHandle = this.fastScrollViews.getScrollHandle();
            if (scrollHandle != null && ViewExtensions.isPointInsideBounds(scrollHandle, event.getX(), event.getY())) {
                z = true;
            }
            if (z) {
                this.dragState = 2;
                this.verticalDragY = event.getY();
                setState(2);
                return;
            }
            return;
        }
        if (event.getAction() == 1 && this.currentState == 2) {
            this.verticalDragY = 0.0f;
            setState(1);
            this.dragState = 0;
            Iterator<T> it = this.scrollStoppedListeners.iterator();
            while (it.hasNext()) {
                ((Function0) it.next()).invoke();
            }
            return;
        }
        if (event.getAction() == 2 && this.currentState == 2) {
            show();
            if (this.dragState == 2) {
                float relativeTouchPosition = getRelativeTouchPosition(event);
                setScrollerPosition(relativeTouchPosition);
                setRecyclerViewPosition(relativeTouchPosition);
                updateHandlePosition(recyclerView);
            }
        }
    }

    public final void updateHandlePosition(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        int iComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        float fComputeVerticalScrollRange = recyclerView.computeVerticalScrollRange() - recyclerView.computeVerticalScrollExtent();
        setScrollerPosition((fComputeVerticalScrollRange > 0.0f ? 1 : (fComputeVerticalScrollRange == 0.0f ? 0 : -1)) == 0 ? 0.0f : iComputeVerticalScrollOffset / fComputeVerticalScrollRange);
        int i = this.currentState;
        if (i == 0 || i == 1) {
            setState(1);
        }
    }
}
