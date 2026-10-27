package com.amazon.mp3.playback.view.lyrics;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public class LyricsScrollPolicy extends RecyclerView.OnScrollListener implements View.OnTouchListener {
    private boolean mIsUserHoldsView = false;
    private boolean mIsUserScrolling = false;
    private long mAutoScrollingBlockStart = 0;
    private int mScrollState = 0;

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int scrollState) {
        super.onScrollStateChanged(recyclerView, scrollState);
        this.mScrollState = scrollState;
        if (this.mIsUserScrolling && scrollState == 0) {
            this.mIsUserScrolling = false;
            this.mAutoScrollingBlockStart = SystemClock.uptimeMillis();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (this.mIsUserHoldsView) {
            this.mIsUserScrolling = true;
        }
    }

    public boolean isScrollBlocked() {
        return this.mIsUserHoldsView || this.mIsUserScrolling || isTimedBlockActive();
    }

    public void resetScrollBlock() {
        this.mAutoScrollingBlockStart = 0L;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            this.mIsUserHoldsView = true;
        } else if (action != 2) {
            this.mIsUserHoldsView = false;
            if (this.mScrollState == 0) {
                this.mIsUserScrolling = false;
            }
        }
        return false;
    }

    private boolean isTimedBlockActive() {
        return SystemClock.uptimeMillis() - this.mAutoScrollingBlockStart < CoroutineLiveDataKt.DEFAULT_TIMEOUT;
    }
}
