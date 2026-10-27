package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MetricsListener {
    public abstract void localPlaybackFinished(int i, MTSAttributes mTSAttributes);

    public abstract void playbackTerminated(int i, MTSAttributes mTSAttributes);

    public abstract void streamingInitFailed(int i, MTSAttributes mTSAttributes);

    public abstract void streamingInitiated(int i, MTSAttributes mTSAttributes);

    public abstract void streamingRequiredRebuffering(int i, float f, MTSAttributes mTSAttributes);

    public abstract void trackPlayedLocally(int i, MTSAttributes mTSAttributes);

    public abstract void trackStreamed(int i, MTSAttributes mTSAttributes);
}
