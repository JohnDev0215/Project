package com.amazon.avod.media.playback.pipeline;

import com.amazon.avod.media.playback.avsync.RendererDebugTracker;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MediaPipelineContext {
    private final RendererDebugTracker mRendererDebugTracker;
    private volatile boolean mStallAudioPipeline;

    public MediaPipelineContext() {
        this(new RendererDebugTracker());
    }

    public MediaPipelineContext(@Nonnull RendererDebugTracker rendererDebugTracker) {
        this.mStallAudioPipeline = false;
        this.mRendererDebugTracker = (RendererDebugTracker) Preconditions.checkNotNull(rendererDebugTracker, "rendererDebugTracker");
    }

    public boolean isAudioPipelineStalled() {
        return this.mStallAudioPipeline;
    }

    public void stallAudioPipeline(boolean z) {
        this.mStallAudioPipeline = z;
    }

    @Nonnull
    public RendererDebugTracker getRendererDebugTracker() {
        return this.mRendererDebugTracker;
    }
}
