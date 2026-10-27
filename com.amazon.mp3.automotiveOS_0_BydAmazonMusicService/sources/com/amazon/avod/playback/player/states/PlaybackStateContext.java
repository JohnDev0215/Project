package com.amazon.avod.playback.player.states;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.drm.DecryptionContext;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.amazon.avod.playback.subtitles.SubtitlesEngine;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackStateContext {
    private ContentSession mContentSession;
    private DecryptionContext mDecryptionContext;
    private MediaException mFatalException;
    private LifecycleProfiler mLifecycleProfiler;
    private boolean mPlaybackStarted = false;
    private PlaybackSessionProtocol mProtocol;
    private int mRendererStatusFlags;
    private SubtitlesEngine mSubtitlesEngine;
    private VideoSpecification mVideoSpec;

    public void setProtocol(PlaybackSessionProtocol playbackSessionProtocol) {
        this.mProtocol = playbackSessionProtocol;
    }

    @Nullable
    public PlaybackSessionProtocol getProtocol() {
        return this.mProtocol;
    }

    public void setDecryptionContext(@Nonnull DecryptionContext decryptionContext) {
        this.mDecryptionContext = (DecryptionContext) Preconditions.checkNotNull(decryptionContext, "context");
    }

    @Nullable
    public DecryptionContext getDecryptionContext() {
        return this.mDecryptionContext;
    }

    public void setSpecification(VideoSpecification videoSpecification) {
        this.mVideoSpec = videoSpecification;
    }

    @Nullable
    public VideoSpecification getSpecification() {
        return this.mVideoSpec;
    }

    public void setContentSession(ContentSession contentSession) {
        this.mContentSession = contentSession;
    }

    @Nullable
    public ContentSession getContentSession() {
        return this.mContentSession;
    }

    public int getRendererStatusFlags() {
        return this.mRendererStatusFlags;
    }

    public void setRendererStatusFlags(int i) {
        this.mRendererStatusFlags = i;
    }

    @Nullable
    public SubtitlesEngine getSubtitlesEngine() {
        return this.mSubtitlesEngine;
    }

    public void setSubtitlesEngine(@Nonnull SubtitlesEngine subtitlesEngine) {
        this.mSubtitlesEngine = (SubtitlesEngine) Preconditions.checkNotNull(subtitlesEngine, "subtitlesEngine");
    }

    @Nullable
    public LifecycleProfiler getLifecycleProfiler() {
        return this.mLifecycleProfiler;
    }

    public void setLifecycleProfiler(@Nonnull LifecycleProfiler lifecycleProfiler) {
        this.mLifecycleProfiler = (LifecycleProfiler) Preconditions.checkNotNull(lifecycleProfiler, "lifecycleProfiler");
    }

    @Nullable
    public MediaException getFatalException() {
        return this.mFatalException;
    }

    public void setFatalException(@Nonnull MediaException mediaException) {
        this.mFatalException = (MediaException) Preconditions.checkNotNull(mediaException, "fatalException");
    }

    public boolean getPlaybackStarted() {
        return this.mPlaybackStarted;
    }

    public void notifyPlaybackStarted() {
        this.mPlaybackStarted = true;
    }
}
