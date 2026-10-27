package com.google.android.exoplayer2.ui;

/* JADX INFO: loaded from: classes6.dex */
public interface TimeBar {

    public interface OnScrubListener {
        void onScrubMove(TimeBar timeBar, long j);

        void onScrubStart(TimeBar timeBar, long j);

        void onScrubStop(TimeBar timeBar, long j, boolean z);
    }

    void setKeyCountIncrement(int i);
}
