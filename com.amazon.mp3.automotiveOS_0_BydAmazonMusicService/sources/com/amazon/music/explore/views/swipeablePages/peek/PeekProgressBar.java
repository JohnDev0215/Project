package com.amazon.music.explore.views.swipeablePages.peek;

/* JADX INFO: loaded from: classes4.dex */
public interface PeekProgressBar {
    void continueProgress();

    int getProgress();

    void pauseProgress();

    void restartProgress();

    void setProgressTo(int i);
}
