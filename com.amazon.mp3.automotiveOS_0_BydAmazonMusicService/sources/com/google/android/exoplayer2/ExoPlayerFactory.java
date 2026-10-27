package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Looper;
import com.google.android.exoplayer2.trackselection.TrackSelector;

/* JADX INFO: loaded from: classes6.dex */
public final class ExoPlayerFactory {
    private ExoPlayerFactory() {
    }

    @Deprecated
    public static SimpleExoPlayer newSimpleInstance(Context context, TrackSelector trackSelector, LoadControl loadControl) {
        return newSimpleInstance(new DefaultRenderersFactory(context), trackSelector, loadControl);
    }

    public static SimpleExoPlayer newSimpleInstance(RenderersFactory renderersFactory, TrackSelector trackSelector, LoadControl loadControl) {
        return newSimpleInstance(renderersFactory, trackSelector, loadControl, null);
    }

    public static SimpleExoPlayer newSimpleInstance(RenderersFactory renderersFactory, TrackSelector trackSelector, LoadControl loadControl, Looper looper) {
        return new SimpleExoPlayer(renderersFactory, trackSelector, loadControl, looper);
    }

    public static ExoPlayer newInstance(Renderer[] rendererArr, TrackSelector trackSelector) {
        return newInstance(rendererArr, trackSelector, new DefaultLoadControl());
    }

    public static ExoPlayer newInstance(Renderer[] rendererArr, TrackSelector trackSelector, LoadControl loadControl) {
        return new ExoPlayerImpl(rendererArr, trackSelector, loadControl);
    }
}
