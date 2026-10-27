package com.amazon.avod.media.ads;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdClipVideoPlayerSetEvent {

    @Nullable
    private final Object mVideoPlayer;

    public AdClipVideoPlayerSetEvent(@Nullable Object obj) {
        this.mVideoPlayer = obj;
    }

    @Nullable
    public Object getVideoPlayer() {
        return this.mVideoPlayer;
    }
}
