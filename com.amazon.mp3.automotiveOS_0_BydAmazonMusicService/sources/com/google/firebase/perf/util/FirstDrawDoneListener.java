package com.google.firebase.perf.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes6.dex */
public class FirstDrawDoneListener implements ViewTreeObserver.OnDrawListener {
    private final Runnable callback;

    @SuppressLint({"ThreadPoolCreation"})
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private final AtomicReference<View> viewReference;

    public static void registerForNextDraw(View view, Runnable runnable) {
        FirstDrawDoneListener firstDrawDoneListener = new FirstDrawDoneListener(view, runnable);
        if (Build.VERSION.SDK_INT < 26 && !isAliveAndAttached(view)) {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.google.firebase.perf.util.FirstDrawDoneListener.1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view2) {
                    view2.getViewTreeObserver().addOnDrawListener(FirstDrawDoneListener.this);
                    view2.removeOnAttachStateChangeListener(this);
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view2) {
                    view2.removeOnAttachStateChangeListener(this);
                }
            });
        } else {
            view.getViewTreeObserver().addOnDrawListener(firstDrawDoneListener);
        }
    }

    private FirstDrawDoneListener(View view, Runnable runnable) {
        this.viewReference = new AtomicReference<>(view);
        this.callback = runnable;
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public void onDraw() {
        final View andSet = this.viewReference.getAndSet(null);
        if (andSet == null) {
            return;
        }
        andSet.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.google.firebase.perf.util.FirstDrawDoneListener$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                this.f$0.lambda$onDraw$0(andSet);
            }
        });
        this.mainThreadHandler.postAtFrontOfQueue(this.callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onDraw$0(View view) {
        view.getViewTreeObserver().removeOnDrawListener(this);
    }

    private static boolean isAliveAndAttached(View view) {
        return view.getViewTreeObserver().isAlive() && isAttachedToWindow(view);
    }

    private static boolean isAttachedToWindow(View view) {
        return view.isAttachedToWindow();
    }
}
