package com.stingray.companion.model;

import A7.m;
import M5.b;
import h2.AbstractC1347d;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b.\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\u0007HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u00103\u001a\u00020\fHÆ\u0003J\t\u00104\u001a\u00020\u0007HÆ\u0003J\t\u00105\u001a\u00020\u0007HÆ\u0003J\t\u00106\u001a\u00020\u0007HÆ\u0003Jg\u00107\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u0007HÆ\u0001J\u0013\u00108\u001a\u00020\f2\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010:\u001a\u00020;HÖ\u0001J\t\u0010<\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010\r\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001b\"\u0004\b)\u0010\u001dR\u001a\u0010\u000e\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001b\"\u0004\b+\u0010\u001dR\u001a\u0010\u000f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001b\"\u0004\b-\u0010\u001d¨\u0006="}, d2 = {"Lcom/stingray/companion/model/PlayerInfo;", "", "mediaId", "", "playerState", "Lcom/stingray/companion/model/PlayerState;", "playTimeInMs", "", "localTime", "playbackMode", "Lcom/stingray/companion/model/PlaybackMode;", "showCredits", "", "creditsDurationMs", "creditsFadeInDurationMs", "closingDurationMs", "<init>", "(Ljava/lang/String;Lcom/stingray/companion/model/PlayerState;JLjava/lang/String;Lcom/stingray/companion/model/PlaybackMode;ZJJJ)V", "getMediaId", "()Ljava/lang/String;", "setMediaId", "(Ljava/lang/String;)V", "getPlayerState", "()Lcom/stingray/companion/model/PlayerState;", "setPlayerState", "(Lcom/stingray/companion/model/PlayerState;)V", "getPlayTimeInMs", "()J", "setPlayTimeInMs", "(J)V", "getLocalTime", "setLocalTime", "getPlaybackMode", "()Lcom/stingray/companion/model/PlaybackMode;", "setPlaybackMode", "(Lcom/stingray/companion/model/PlaybackMode;)V", "getShowCredits", "()Z", "setShowCredits", "(Z)V", "getCreditsDurationMs", "setCreditsDurationMs", "getCreditsFadeInDurationMs", "setCreditsFadeInDurationMs", "getClosingDurationMs", "setClosingDurationMs", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "companion_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class PlayerInfo {
    private long closingDurationMs;
    private long creditsDurationMs;
    private long creditsFadeInDurationMs;
    private String localTime;
    private String mediaId;
    private long playTimeInMs;
    private PlaybackMode playbackMode;
    private PlayerState playerState;
    private boolean showCredits;

    public PlayerInfo(String str, PlayerState playerState, long j3, String str2, PlaybackMode playbackMode, boolean z6, long j10, long j11, long j12) {
        m.f(playerState, "playerState");
        m.f(str2, "localTime");
        this.mediaId = str;
        this.playerState = playerState;
        this.playTimeInMs = j3;
        this.localTime = str2;
        this.playbackMode = playbackMode;
        this.showCredits = z6;
        this.creditsDurationMs = j10;
        this.creditsFadeInDurationMs = j11;
        this.closingDurationMs = j12;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getMediaId() {
        return this.mediaId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PlayerState getPlayerState() {
        return this.playerState;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getPlayTimeInMs() {
        return this.playTimeInMs;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getLocalTime() {
        return this.localTime;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final PlaybackMode getPlaybackMode() {
        return this.playbackMode;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getShowCredits() {
        return this.showCredits;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getCreditsDurationMs() {
        return this.creditsDurationMs;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getCreditsFadeInDurationMs() {
        return this.creditsFadeInDurationMs;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final long getClosingDurationMs() {
        return this.closingDurationMs;
    }

    public final PlayerInfo copy(String mediaId, PlayerState playerState, long playTimeInMs, String localTime, PlaybackMode playbackMode, boolean showCredits, long creditsDurationMs, long creditsFadeInDurationMs, long closingDurationMs) {
        m.f(playerState, "playerState");
        m.f(localTime, "localTime");
        return new PlayerInfo(mediaId, playerState, playTimeInMs, localTime, playbackMode, showCredits, creditsDurationMs, creditsFadeInDurationMs, closingDurationMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayerInfo)) {
            return false;
        }
        PlayerInfo playerInfo = (PlayerInfo) other;
        return m.a(this.mediaId, playerInfo.mediaId) && this.playerState == playerInfo.playerState && this.playTimeInMs == playerInfo.playTimeInMs && m.a(this.localTime, playerInfo.localTime) && this.playbackMode == playerInfo.playbackMode && this.showCredits == playerInfo.showCredits && this.creditsDurationMs == playerInfo.creditsDurationMs && this.creditsFadeInDurationMs == playerInfo.creditsFadeInDurationMs && this.closingDurationMs == playerInfo.closingDurationMs;
    }

    public final long getClosingDurationMs() {
        return this.closingDurationMs;
    }

    public final long getCreditsDurationMs() {
        return this.creditsDurationMs;
    }

    public final long getCreditsFadeInDurationMs() {
        return this.creditsFadeInDurationMs;
    }

    public final String getLocalTime() {
        return this.localTime;
    }

    public final String getMediaId() {
        return this.mediaId;
    }

    public final long getPlayTimeInMs() {
        return this.playTimeInMs;
    }

    public final PlaybackMode getPlaybackMode() {
        return this.playbackMode;
    }

    public final PlayerState getPlayerState() {
        return this.playerState;
    }

    public final boolean getShowCredits() {
        return this.showCredits;
    }

    public int hashCode() {
        String str = this.mediaId;
        int iC = b.c(this.localTime, AbstractC1347d.c((this.playerState.hashCode() + ((str == null ? 0 : str.hashCode()) * 31)) * 31, 31, this.playTimeInMs), 31);
        PlaybackMode playbackMode = this.playbackMode;
        return Long.hashCode(this.closingDurationMs) + AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.d((iC + (playbackMode != null ? playbackMode.hashCode() : 0)) * 31, 31, this.showCredits), 31, this.creditsDurationMs), 31, this.creditsFadeInDurationMs);
    }

    public final void setClosingDurationMs(long j3) {
        this.closingDurationMs = j3;
    }

    public final void setCreditsDurationMs(long j3) {
        this.creditsDurationMs = j3;
    }

    public final void setCreditsFadeInDurationMs(long j3) {
        this.creditsFadeInDurationMs = j3;
    }

    public final void setLocalTime(String str) {
        m.f(str, "<set-?>");
        this.localTime = str;
    }

    public final void setMediaId(String str) {
        this.mediaId = str;
    }

    public final void setPlayTimeInMs(long j3) {
        this.playTimeInMs = j3;
    }

    public final void setPlaybackMode(PlaybackMode playbackMode) {
        this.playbackMode = playbackMode;
    }

    public final void setPlayerState(PlayerState playerState) {
        m.f(playerState, "<set-?>");
        this.playerState = playerState;
    }

    public final void setShowCredits(boolean z6) {
        this.showCredits = z6;
    }

    public String toString() {
        return "PlayerInfo(mediaId=" + this.mediaId + ", playerState=" + this.playerState + ", playTimeInMs=" + this.playTimeInMs + ", localTime=" + this.localTime + ", playbackMode=" + this.playbackMode + ", showCredits=" + this.showCredits + ", creditsDurationMs=" + this.creditsDurationMs + ", creditsFadeInDurationMs=" + this.creditsFadeInDurationMs + ", closingDurationMs=" + this.closingDurationMs + ")";
    }

    public /* synthetic */ PlayerInfo(String str, PlayerState playerState, long j3, String str2, PlaybackMode playbackMode, boolean z6, long j10, long j11, long j12, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, playerState, j3, str2, (i7 & 16) != 0 ? PlaybackMode.NORMAL : playbackMode, (i7 & 32) != 0 ? true : z6, j10, (i7 & 128) != 0 ? 500L : j11, (i7 & 256) != 0 ? 500L : j12);
    }
}
