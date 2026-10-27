package com.amazon.mp3.playback.view;

import android.view.View;
import com.amazon.mp3.view.DragAnimator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewHorizontalDragAnimator.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J$\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u0015\u001a\u00020\u000eJ\u001e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/playback/view/ViewHorizontalDragAnimator;", "", "()V", "mCurrentView", "Landroid/view/View;", "mCurrentViewDragAnimator", "Lcom/amazon/mp3/view/DragAnimator;", "mNextView", "mNextViewDragAnimator", "mPreviousView", "mPreviousViewDragAnimator", "width", "", "dragView", "", "x", "", "initializeViews", "currentView", "previousView", "nextView", "resetViews", "updateViews", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ViewHorizontalDragAnimator {
    private View mCurrentView;
    private View mNextView;
    private View mPreviousView;
    private int width;
    private final DragAnimator mCurrentViewDragAnimator = new DragAnimator();
    private final DragAnimator mPreviousViewDragAnimator = new DragAnimator();
    private final DragAnimator mNextViewDragAnimator = new DragAnimator();

    public final void initializeViews(View currentView, View previousView, View nextView) {
        if (currentView == null) {
            return;
        }
        this.mCurrentView = currentView;
        this.width = currentView.getWidth();
        this.mCurrentViewDragAnimator.initView(currentView);
        if (previousView != null) {
            this.mPreviousView = previousView;
            previousView.setTranslationX(-this.width);
            this.mPreviousViewDragAnimator.initView(previousView);
        }
        if (nextView == null) {
            return;
        }
        this.mNextView = nextView;
        nextView.setTranslationX(this.width);
        this.mNextViewDragAnimator.initView(nextView);
    }

    public final void updateViews(View currentView, View previousView, View nextView) {
        Intrinsics.checkNotNullParameter(currentView, "currentView");
        Intrinsics.checkNotNullParameter(previousView, "previousView");
        Intrinsics.checkNotNullParameter(nextView, "nextView");
        this.mCurrentViewDragAnimator.setView(currentView);
        this.mPreviousViewDragAnimator.setView(previousView);
        this.mNextViewDragAnimator.setView(nextView);
    }

    public final void dragView(float x) {
        if (this.mCurrentView == null || Math.abs(x) > this.width) {
            return;
        }
        float fAbs = Math.abs(x) / this.width;
        this.mCurrentViewDragAnimator.drag(x, 0.0f, 1 - fAbs);
        if (x < 0.0f) {
            View view = this.mPreviousView;
            if (view != null) {
                view.setVisibility(8);
            }
            View view2 = this.mNextView;
            if (view2 != null) {
                view2.setVisibility(0);
            }
            this.mNextViewDragAnimator.drag(x, 0.0f, fAbs);
            return;
        }
        View view3 = this.mNextView;
        if (view3 != null) {
            view3.setVisibility(8);
        }
        View view4 = this.mPreviousView;
        if (view4 != null) {
            view4.setVisibility(0);
        }
        this.mPreviousViewDragAnimator.drag(x, 0.0f, fAbs);
    }

    public final void resetViews() {
        this.mCurrentViewDragAnimator.resetView();
        this.mPreviousViewDragAnimator.resetView();
        this.mNextViewDragAnimator.resetView();
    }
}
