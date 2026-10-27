package com.amazon.digitalmusicplayback;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PlaybackListener {
    public abstract void audioAttributesChanged(AudioAttributes audioAttributes);

    public abstract void bassSettingsChanged(FrequencySettings frequencySettings, boolean z);

    public abstract void enable24BitPlaybackChanged(boolean z, boolean z2, EngineError engineError);

    public abstract void equalizerSettingsChanged(EqualizerSettings equalizerSettings, boolean z);

    public abstract void finishedPlayback(int i);

    public abstract void frameReady(int i, int i2, AudioFormat audioFormat, ArrayList<ByteBuffer> arrayList, int i3);

    public abstract void liveStreamUpdated(long j);

    public abstract void outputDeviceAttributesChanged(OutputDeviceAttributes outputDeviceAttributes);

    public abstract void playbackMuted(boolean z);

    public abstract void playbackPaused(boolean z);

    public abstract void playbackStalled(boolean z, int i);

    public abstract void playbackVolumeChanged(double d);

    public abstract void prebufferChanged(int i, long j, long j2);

    public abstract void prioritizeDownloadsChanged(boolean z, EngineError engineError);

    public abstract void seekFailed(EngineError engineError);

    public abstract void seekFinished(long j);

    public abstract void skippedToNextTrack(int i, int i2);

    public abstract void startedPlayback(int i);

    public abstract void tempoChanged(double d, boolean z);

    public abstract void trackInitFailed(int i, EngineError engineError);

    public abstract void trackPlaybackFailed(int i, EngineError engineError);

    public abstract void trackProgressed(int i, long j);

    public abstract void trebleSettingsChanged(FrequencySettings frequencySettings, boolean z);
}
