package android.view;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public class ScrollCaptureTargetResolver {
    private static final boolean DEBUG = true;
    private static final String TAG = "ScrollCaptureTargetRes";
    private long mDeadlineMillis;
    private boolean mFinished;
    private Handler mHandler;
    private int mPendingBoundsRequests;
    private ScrollCaptureTarget mResult;
    private boolean mStarted;
    private final Queue<ScrollCaptureTarget> mTargets;
    private long mTimeLimitMillis;
    private Consumer<ScrollCaptureTarget> mWhenComplete;
    private final Object mLock = new Object();
    private final Runnable mTimeoutRunnable = new Runnable() { // from class: android.view.ScrollCaptureTargetResolver.1
        @Override // java.lang.Runnable
        public void run() {
            ScrollCaptureTargetResolver.this.checkThread();
            ScrollCaptureTargetResolver.this.supplyResult(null);
        }
    };

    private static int area(Rect rect) {
        return rect.width() * rect.height();
    }

    private static boolean nullOrEmpty(Rect rect) {
        return rect == null || rect.isEmpty();
    }

    private static ScrollCaptureTarget chooseTarget(ScrollCaptureTarget scrollCaptureTarget, ScrollCaptureTarget scrollCaptureTarget2) {
        Log.d(TAG, "chooseTarget: " + scrollCaptureTarget + " or " + scrollCaptureTarget2);
        if (scrollCaptureTarget == null && scrollCaptureTarget2 == null) {
            Log.d(TAG, "chooseTarget: (both null) return " + ((Object) null));
            return null;
        }
        if (scrollCaptureTarget == null || scrollCaptureTarget2 == null) {
            if (scrollCaptureTarget == null) {
                scrollCaptureTarget = scrollCaptureTarget2;
            }
            Log.d(TAG, "chooseTarget: (other is null) return " + scrollCaptureTarget);
            return scrollCaptureTarget;
        }
        boolean zNullOrEmpty = nullOrEmpty(scrollCaptureTarget.getScrollBounds());
        boolean zNullOrEmpty2 = nullOrEmpty(scrollCaptureTarget2.getScrollBounds());
        if (zNullOrEmpty || zNullOrEmpty2) {
            if (zNullOrEmpty && zNullOrEmpty2) {
                Log.d(TAG, "chooseTarget: (both have empty or null bounds) return " + ((Object) null));
                return null;
            }
            if (zNullOrEmpty) {
                Log.d(TAG, "chooseTarget: (a has empty or null bounds) return " + scrollCaptureTarget2);
                return scrollCaptureTarget2;
            }
            Log.d(TAG, "chooseTarget: (b has empty or null bounds) return " + scrollCaptureTarget);
            return scrollCaptureTarget;
        }
        View containingView = scrollCaptureTarget.getContainingView();
        View containingView2 = scrollCaptureTarget2.getContainingView();
        boolean zHasIncludeHint = hasIncludeHint(containingView);
        if (zHasIncludeHint != hasIncludeHint(containingView2)) {
            if (!zHasIncludeHint) {
                scrollCaptureTarget = scrollCaptureTarget2;
            }
            Log.d(TAG, "chooseTarget: (has hint=INCLUDE) return " + scrollCaptureTarget);
            return scrollCaptureTarget;
        }
        if (isDescendant(containingView, containingView2)) {
            Log.d(TAG, "chooseTarget: (b is descendant of a) return " + scrollCaptureTarget2);
            return scrollCaptureTarget2;
        }
        if (isDescendant(containingView2, containingView)) {
            Log.d(TAG, "chooseTarget: (a is descendant of b) return " + scrollCaptureTarget);
            return scrollCaptureTarget;
        }
        if (area(scrollCaptureTarget.getScrollBounds()) < area(scrollCaptureTarget2.getScrollBounds())) {
            scrollCaptureTarget = scrollCaptureTarget2;
        }
        Log.d(TAG, "chooseTarget: return " + scrollCaptureTarget);
        return scrollCaptureTarget;
    }

    public ScrollCaptureTargetResolver(Queue<ScrollCaptureTarget> queue) {
        this.mTargets = queue;
    }

    void checkThread() {
        if (this.mHandler.getLooper() == Looper.myLooper()) {
            return;
        }
        throw new IllegalStateException("Called from wrong thread! (" + Thread.currentThread().getName() + ")");
    }

    public ScrollCaptureTarget waitForResult() throws InterruptedException {
        synchronized (this.mLock) {
            while (!this.mFinished) {
                this.mLock.wait();
            }
        }
        return this.mResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void supplyResult(ScrollCaptureTarget scrollCaptureTarget) {
        checkThread();
        if (this.mFinished) {
            return;
        }
        this.mResult = chooseTarget(this.mResult, scrollCaptureTarget);
        if (this.mPendingBoundsRequests == 0 || SystemClock.elapsedRealtime() >= this.mDeadlineMillis) {
            System.err.println("We think we're done, or timed out");
            this.mPendingBoundsRequests = 0;
            this.mWhenComplete.accept(this.mResult);
            synchronized (this.mLock) {
                this.mFinished = true;
                this.mLock.notify();
            }
            this.mWhenComplete = null;
        }
    }

    public void start(Handler handler, final long j, final Consumer<ScrollCaptureTarget> consumer) {
        synchronized (this.mLock) {
            if (this.mStarted) {
                throw new IllegalStateException("already started!");
            }
            if (j < 0) {
                throw new IllegalArgumentException("Time limit must be positive");
            }
            this.mHandler = handler;
            this.mTimeLimitMillis = j;
            this.mWhenComplete = consumer;
            if (this.mTargets.isEmpty()) {
                this.mHandler.post(new Runnable() { // from class: android.view.-$$Lambda$ScrollCaptureTargetResolver$EzFCJjUw-13D2CDqIgLoFuJ5qdY
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$start$0$ScrollCaptureTargetResolver();
                    }
                });
            } else {
                this.mStarted = true;
                handler.post(new Runnable() { // from class: android.view.-$$Lambda$ScrollCaptureTargetResolver$yVQQiWyGWiKm9-d01s2pn78mS0k
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$start$1$ScrollCaptureTargetResolver(j, consumer);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$start$0$ScrollCaptureTargetResolver() {
        supplyResult(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: run, reason: merged with bridge method [inline-methods] */
    public void lambda$start$1$ScrollCaptureTargetResolver(long j, Consumer<ScrollCaptureTarget> consumer) {
        checkThread();
        this.mPendingBoundsRequests = this.mTargets.size();
        Iterator<ScrollCaptureTarget> it = this.mTargets.iterator();
        while (it.hasNext()) {
            queryTarget(it.next());
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime() + this.mTimeLimitMillis;
        this.mDeadlineMillis = jElapsedRealtime;
        this.mHandler.postAtTime(this.mTimeoutRunnable, jElapsedRealtime);
    }

    private void queryTarget(final ScrollCaptureTarget scrollCaptureTarget) {
        checkThread();
        scrollCaptureTarget.getCallback().onScrollCaptureSearch(new SingletonConsumer(new Consumer() { // from class: android.view.-$$Lambda$ScrollCaptureTargetResolver$J9O9ShCuuF3gTNBsMj2uaRnxEtQ
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$queryTarget$3$ScrollCaptureTargetResolver(scrollCaptureTarget, (Rect) obj);
            }
        }));
    }

    public /* synthetic */ void lambda$queryTarget$3$ScrollCaptureTargetResolver(final ScrollCaptureTarget scrollCaptureTarget, final Rect rect) {
        this.mHandler.post(new Runnable() { // from class: android.view.-$$Lambda$ScrollCaptureTargetResolver$gdmLlyNUqtKoShKx1WC6kEG8ZHs
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$queryTarget$2$ScrollCaptureTargetResolver(scrollCaptureTarget, rect);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onScrollBoundsProvided, reason: merged with bridge method [inline-methods] */
    public void lambda$queryTarget$2$ScrollCaptureTargetResolver(ScrollCaptureTarget scrollCaptureTarget, Rect rect) {
        checkThread();
        if (this.mFinished) {
            return;
        }
        this.mPendingBoundsRequests--;
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        if (this.mPendingBoundsRequests != 0) {
            int i = (SystemClock.elapsedRealtime() > this.mDeadlineMillis ? 1 : (SystemClock.elapsedRealtime() == this.mDeadlineMillis ? 0 : -1));
        }
        View containingView = scrollCaptureTarget.getContainingView();
        if (!nullOrEmpty(rect) && containingView.isAggregatedVisible()) {
            scrollCaptureTarget.updatePositionInWindow();
            scrollCaptureTarget.setScrollBounds(rect);
            supplyResult(scrollCaptureTarget);
        }
        System.err.println("mPendingBoundsRequests: " + this.mPendingBoundsRequests);
        System.err.println("mDeadlineMillis: " + this.mDeadlineMillis);
        System.err.println("SystemClock.elapsedRealtime(): " + SystemClock.elapsedRealtime());
        if (this.mFinished) {
            return;
        }
        System.err.println("We think we're NOT done yet and will check back at " + this.mDeadlineMillis);
        this.mHandler.postAtTime(this.mTimeoutRunnable, this.mDeadlineMillis);
    }

    private static boolean hasIncludeHint(View view) {
        return (view.getScrollCaptureHint() & 2) != 0;
    }

    private static boolean isDescendant(View view, View view2) {
        if (view == view2) {
            return false;
        }
        ViewParent parent = view2.getParent();
        while (parent != view && parent != null) {
            parent = parent.getParent();
        }
        return parent == view;
    }

    private static int findRelation(View view, View view2) {
        if (view == view2) {
            return 0;
        }
        ViewParent parent = view.getParent();
        ViewParent parent2 = view2.getParent();
        while (true) {
            if ((parent == null && parent2 == null) || parent == parent2) {
                return 0;
            }
            if (parent == view2) {
                return 1;
            }
            if (parent2 == view) {
                return -1;
            }
            if (parent != null) {
                parent = parent.getParent();
            }
            if (parent2 != null) {
                parent2 = parent2.getParent();
            }
        }
    }

    static class SingletonConsumer<T> implements Consumer<T> {
        final AtomicReference<Consumer<T>> mAtomicRef;

        SingletonConsumer(Consumer<T> consumer) {
            this.mAtomicRef = new AtomicReference<>(consumer);
        }

        @Override // java.util.function.Consumer
        public void accept(T t) {
            Consumer<T> andSet = this.mAtomicRef.getAndSet(null);
            if (andSet != null) {
                andSet.accept(t);
            }
        }
    }
}
