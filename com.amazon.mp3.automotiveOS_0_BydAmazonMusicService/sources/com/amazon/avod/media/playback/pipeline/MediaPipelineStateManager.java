package com.amazon.avod.media.playback.pipeline;

/* JADX INFO: loaded from: classes2.dex */
public class MediaPipelineStateManager extends AbstractMediaComponent {
    public boolean canConfigure() {
        return isUnconfigured();
    }

    public boolean canStart() {
        return isIdle();
    }

    public boolean canStop() {
        return isRunning();
    }

    public boolean canFlush() {
        return isIdle();
    }

    public boolean canRelease() {
        return isIdle() || isUnconfigured();
    }

    public boolean canSetOutputSurface() {
        return !isUnconfigured();
    }
}
