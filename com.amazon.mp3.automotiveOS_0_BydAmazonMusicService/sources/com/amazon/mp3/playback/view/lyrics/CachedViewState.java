package com.amazon.mp3.playback.view.lyrics;

/* JADX INFO: loaded from: classes3.dex */
public class CachedViewState {
    private static CachedViewState sInstance;
    private ViewState mViewState = ViewState.GONE;
    private ViewState mLastNonGoneState = ViewState.UNKNOWN;

    public static CachedViewState getInstance() {
        if (sInstance == null) {
            sInstance = new CachedViewState();
        }
        return sInstance;
    }

    public void setViewState(final ViewState viewState) {
        this.mViewState = viewState;
        if (viewState != ViewState.GONE) {
            this.mLastNonGoneState = viewState;
        }
    }

    public ViewState getViewState() {
        return this.mViewState;
    }

    public ViewState getLastNonGoneState() {
        return this.mLastNonGoneState;
    }

    private CachedViewState() {
    }
}
