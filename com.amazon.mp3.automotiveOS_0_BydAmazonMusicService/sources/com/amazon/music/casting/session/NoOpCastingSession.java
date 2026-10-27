package com.amazon.music.casting.session;

import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.session.things.ClientThingShadow;
import com.amazon.music.casting.session.things.NowPlayingThingShadow;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.musicplayqueueservice.client.common.CreateQueueRequest;
import com.amazon.musicplayqueueservice.client.common.TransferQueueRequest;

/* JADX INFO: loaded from: classes4.dex */
public class NoOpCastingSession implements CastingSession {
    @Override // com.amazon.music.casting.session.CastingSession
    public void connect(CastingDevice castingDevice, CastingConnectionCallback castingConnectionCallback, CastingPlaybackCallback castingPlaybackCallback, CastingConnectionType castingConnectionType) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void disconnect(TerminationReason terminationReason) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public CastingDevice getCastingDevice() {
        return null;
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public NowPlayingThingShadow.State.ReportedNode getNowPlayingReportedNode() {
        return null;
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public float getVolume() {
        return 0.0f;
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public boolean isConnected() {
        return false;
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public boolean isConnecting() {
        return false;
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void next() {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void onVolumeDown() {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void onVolumeUp() {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void pause() {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void play() {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void previous() {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void restore(CastingDevice castingDevice, CastingConnectionCallback castingConnectionCallback, CastingPlaybackCallback castingPlaybackCallback) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void seek(long j) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void setLoopMode(NowPlayingThingShadow.State.LoopMode loopMode) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void setShuffled(boolean z) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void setVolume(float f) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void updateAllowedParentalControls(ClientThingShadow.State.ReportedNode.AllowedParentalControls allowedParentalControls) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void updateQueue(CreateQueueRequest createQueueRequest, CastingQueueCallback castingQueueCallback) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public void updateQueue(TransferQueueRequest transferQueueRequest, CastingQueueCallback castingQueueCallback) {
    }

    @Override // com.amazon.music.casting.session.CastingSession
    public synchronized void refresh() {
    }
}
