package com.amazon.avod.media.playback;

import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.playback.internal.listener.VideoPresentationEventListenerSet;
import com.amazon.avod.playback.PlaybackEventListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VideoPlayerLifecyleEventHandler implements PlaybackEventListener {
    private final VideoPresentationEventListenerSet mListenerSet;
    private final Object mMutex;
    private VideoPresentation mPresentation;

    public VideoPlayerLifecyleEventHandler() {
        this(new VideoPresentationEventListenerSet());
    }

    VideoPlayerLifecyleEventHandler(VideoPresentationEventListenerSet videoPresentationEventListenerSet) {
        this.mMutex = new Object();
        this.mListenerSet = videoPresentationEventListenerSet;
    }

    public void setPresentation(VideoPresentation videoPresentation) {
        this.mPresentation = (VideoPresentation) Preconditions.checkNotNull(videoPresentation);
    }

    public void addConcreteListener(@Nonnull VideoPresentationEventListener videoPresentationEventListener) {
        Preconditions.checkNotNull(videoPresentationEventListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.mMutex) {
            this.mListenerSet.addListener(videoPresentationEventListener);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onCompletion() {
        synchronized (this.mMutex) {
            this.mListenerSet.onCompletion(this.mPresentation);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onError(MediaErrorCode mediaErrorCode) {
        synchronized (this.mMutex) {
            this.mListenerSet.onError(this.mPresentation, mediaErrorCode);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onPrepared(PlaybackDataSource playbackDataSource) {
        synchronized (this.mMutex) {
            this.mListenerSet.onPrepared(this.mPresentation, playbackDataSource);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onStarted(@Nonnull PlaybackDataSource playbackDataSource) {
        synchronized (this.mMutex) {
            this.mListenerSet.onStarted(this.mPresentation, playbackDataSource);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onTerminated() {
        synchronized (this.mMutex) {
            this.mListenerSet.onTerminated();
        }
    }
}
