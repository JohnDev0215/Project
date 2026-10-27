package com.amazon.avod.content.dash.quality.heuristic;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.urlvending.QoeConfigInterface;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface Heuristics {
    void bufferStateChanged() throws ContentException;

    @Nonnull
    HeuristicAlgorithm getAlgorithm();

    long getBandwidthAverageBytesPerSecond();

    long getBandwidthPredictionErrorAvgBytesPerSecond();

    long getBandwidthStandardDeviationBytesPerSecond();

    @Nonnull
    DownloadConfig getDownloadConfig();

    @Nullable
    HeuristicsPlaybackConfig getHeuristicsPlaybackConfig();

    long getLatencyAverageMillis();

    long getLatencyStandardDeviationMillis();

    @Nonnull
    QoeConfigInterface getQoeConfig();

    @Nonnull
    String getSettingsId();

    ByteBuffer getState() throws ContentException;

    @Nonnull
    StreamBehavior getStreamBehavior();

    float getVersionInfo();

    void initialize(@Nonnull HeuristicsCallbacks heuristicsCallbacks, ContentSessionContext contentSessionContext) throws ContentException;

    void onPlaybackStateChange(HeuristicsPlaybackState heuristicsPlaybackState);

    void pause() throws ContentException;

    void pause(int i) throws ContentException;

    void release() throws ContentException;

    void reportDownloadProgress(int i, int i2, long j, long j2, long j3, DownloadStatus downloadStatus, long j4) throws ContentException;

    void resume() throws ContentException;

    void resume(int i) throws ContentException;

    void setStreamHandle(int i, long j) throws ContentException;

    void setStreamIndex(int i, int i2) throws ContentException;
}
