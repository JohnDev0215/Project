package com.byd.music.view.musicPlayingSlideView;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes5.dex */
public class PlayingSlideController {
    private WeakReference<PlayingSlideProxy> mTarget;

    public void setTarget(PlayingSlideProxy playingSlideProxy) {
        this.mTarget = new WeakReference<>(playingSlideProxy);
    }

    public void cancel() {
        this.mTarget.get().cancel();
    }

    public void pause() {
        this.mTarget.get().pause();
    }

    public void start() {
        this.mTarget.get().start();
    }
}
