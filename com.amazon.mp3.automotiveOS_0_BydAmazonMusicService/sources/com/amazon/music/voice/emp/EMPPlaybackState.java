package com.amazon.music.voice.emp;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EMPPlaybackPlugin.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/voice/emp/EMPPlaybackState;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/voice/emp/EMPPlayStatus;", "playStatus", "Lcom/amazon/music/voice/emp/EMPPlayStatus;", "getPlayStatus", "()Lcom/amazon/music/voice/emp/EMPPlayStatus;", "mediaItemId", "Ljava/lang/String;", "getMediaItemId", "()Ljava/lang/String;", "<init>", "(Lcom/amazon/music/voice/emp/EMPPlayStatus;Ljava/lang/String;)V", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class EMPPlaybackState {
    private final String mediaItemId;
    private final EMPPlayStatus playStatus;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EMPPlaybackState)) {
            return false;
        }
        EMPPlaybackState eMPPlaybackState = (EMPPlaybackState) other;
        return this.playStatus == eMPPlaybackState.playStatus && Intrinsics.areEqual(this.mediaItemId, eMPPlaybackState.mediaItemId);
    }

    public int hashCode() {
        return (this.playStatus.hashCode() * 31) + this.mediaItemId.hashCode();
    }

    public String toString() {
        return "EMPPlaybackState(playStatus=" + this.playStatus + ", mediaItemId=" + this.mediaItemId + ')';
    }

    public EMPPlaybackState(EMPPlayStatus playStatus, String mediaItemId) {
        Intrinsics.checkNotNullParameter(playStatus, "playStatus");
        Intrinsics.checkNotNullParameter(mediaItemId, "mediaItemId");
        this.playStatus = playStatus;
        this.mediaItemId = mediaItemId;
    }

    public final String getMediaItemId() {
        return this.mediaItemId;
    }

    public final EMPPlayStatus getPlayStatus() {
        return this.playStatus;
    }
}
