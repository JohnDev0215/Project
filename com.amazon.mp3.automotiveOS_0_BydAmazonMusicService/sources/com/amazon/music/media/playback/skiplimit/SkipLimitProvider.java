package com.amazon.music.media.playback.skiplimit;

/* JADX INFO: loaded from: classes4.dex */
public interface SkipLimitProvider {
    void decrement();

    int getCount();

    void onSkip();

    void setCount(int i);
}
