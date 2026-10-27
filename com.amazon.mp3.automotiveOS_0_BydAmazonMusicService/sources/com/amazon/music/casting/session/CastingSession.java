package com.amazon.music.casting.session;

import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.session.things.ClientThingShadow;
import com.amazon.music.casting.session.things.NowPlayingThingShadow;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.musicplayqueueservice.client.common.CreateQueueRequest;
import com.amazon.musicplayqueueservice.client.common.TransferQueueRequest;

/* JADX INFO: loaded from: classes4.dex */
public interface CastingSession {
    void connect(CastingDevice castingDevice, CastingConnectionCallback castingConnectionCallback, CastingPlaybackCallback castingPlaybackCallback, CastingConnectionType castingConnectionType);

    void disconnect(TerminationReason terminationReason);

    CastingDevice getCastingDevice();

    NowPlayingThingShadow.State.ReportedNode getNowPlayingReportedNode();

    float getVolume();

    boolean isConnected();

    boolean isConnecting();

    void next();

    void onVolumeDown();

    void onVolumeUp();

    void pause();

    void play();

    void previous();

    void refresh();

    void restore(CastingDevice castingDevice, CastingConnectionCallback castingConnectionCallback, CastingPlaybackCallback castingPlaybackCallback);

    void seek(long j);

    void setLoopMode(NowPlayingThingShadow.State.LoopMode loopMode);

    void setShuffled(boolean z);

    void setVolume(float f);

    void updateAllowedParentalControls(ClientThingShadow.State.ReportedNode.AllowedParentalControls allowedParentalControls);

    void updateQueue(CreateQueueRequest createQueueRequest, CastingQueueCallback castingQueueCallback);

    void updateQueue(TransferQueueRequest transferQueueRequest, CastingQueueCallback castingQueueCallback);
}
