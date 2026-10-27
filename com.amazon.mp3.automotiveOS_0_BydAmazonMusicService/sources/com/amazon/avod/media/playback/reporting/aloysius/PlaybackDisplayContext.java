package com.amazon.avod.media.playback.reporting.aloysius;

import android.app.Activity;
import android.view.View;
import com.amazon.avod.media.VideoResolution;
import java.lang.ref.WeakReference;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDisplayContext {
    private WeakReference<Activity> mActivity;
    private WeakReference<View> mPlaybackView;
    private VideoResolution mVideoResolution;

    private static class SingletonHolder {
        private static final PlaybackDisplayContext INSTANCE = new PlaybackDisplayContext();

        private SingletonHolder() {
        }
    }

    public static final PlaybackDisplayContext getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Nullable
    public WeakReference<Activity> getActivity() {
        return this.mActivity;
    }

    public void setActivity(@Nullable WeakReference<Activity> weakReference) {
        this.mActivity = weakReference;
    }

    @Nullable
    public WeakReference<View> getPlaybackView() {
        return this.mPlaybackView;
    }

    public void setPlaybackView(@Nullable WeakReference<View> weakReference) {
        this.mPlaybackView = weakReference;
    }

    @Nullable
    public VideoResolution getVideoResolution() {
        return this.mVideoResolution;
    }

    public void setVideoResolution(VideoResolution videoResolution) {
        this.mVideoResolution = videoResolution;
    }

    private PlaybackDisplayContext() {
    }

    public void reset() {
        this.mPlaybackView = null;
        this.mActivity = null;
    }
}
