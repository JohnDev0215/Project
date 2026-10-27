package com.amazon.music.platform.playback.state;

import androidx.core.app.NotificationCompat;
import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.playback.data.PlaybackDevice;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DevicePlaybackState.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\b\u0010\"\u001a\u0004\u0018\u00010\u001d\u0012\u0006\u0010%\u001a\u00020$\u0012\u0006\u0010*\u001a\u00020)\u0012\u0006\u0010/\u001a\u00020.\u0012\u0006\u00103\u001a\u00020\u0007¢\u0006\u0004\b6\u00107J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0019\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0019\u0010\"\u001a\u0004\u0018\u00010\u001d8\u0006¢\u0006\f\n\u0004\b\"\u0010\u001f\u001a\u0004\b#\u0010!R\u0017\u0010%\u001a\u00020$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010*\u001a\u00020)8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u0017\u0010/\u001a\u00020.8\u0006¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102R\u0017\u00103\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b3\u00105¨\u00068"}, d2 = {"Lcom/amazon/music/platform/playback/state/DevicePlaybackState;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/playback/state/PlaybackProgress;", NotificationCompat.CATEGORY_PROGRESS, "Lcom/amazon/music/platform/playback/state/PlaybackProgress;", "getProgress", "()Lcom/amazon/music/platform/playback/state/PlaybackProgress;", "Lcom/amazon/music/platform/playback/state/LoopMode;", "loopMode", "Lcom/amazon/music/platform/playback/state/LoopMode;", "getLoopMode", "()Lcom/amazon/music/platform/playback/state/LoopMode;", "Lcom/amazon/music/platform/playback/state/ShuffleMode;", "shuffleMode", "Lcom/amazon/music/platform/playback/state/ShuffleMode;", "getShuffleMode", "()Lcom/amazon/music/platform/playback/state/ShuffleMode;", "Lcom/amazon/music/platform/playback/state/PlaybackState;", TetheredMessageKey.playbackState, "Lcom/amazon/music/platform/playback/state/PlaybackState;", "getPlaybackState", "()Lcom/amazon/music/platform/playback/state/PlaybackState;", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "currentEntityItemId", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "getCurrentEntityItemId", "()Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "parentEntityItemId", "getParentEntityItemId", "Lcom/amazon/music/platform/playback/data/PlaybackDevice;", "targetDevice", "Lcom/amazon/music/platform/playback/data/PlaybackDevice;", "getTargetDevice", "()Lcom/amazon/music/platform/playback/data/PlaybackDevice;", "Lcom/amazon/music/platform/playback/state/PlayQueueMode;", "queueMode", "Lcom/amazon/music/platform/playback/state/PlayQueueMode;", "getQueueMode", "()Lcom/amazon/music/platform/playback/state/PlayQueueMode;", "Lcom/amazon/music/platform/playback/state/DisplayMode;", "displayMode", "Lcom/amazon/music/platform/playback/state/DisplayMode;", "getDisplayMode", "()Lcom/amazon/music/platform/playback/state/DisplayMode;", "isUserInitiated", "Z", "()Z", "<init>", "(Lcom/amazon/music/platform/playback/state/PlaybackProgress;Lcom/amazon/music/platform/playback/state/LoopMode;Lcom/amazon/music/platform/playback/state/ShuffleMode;Lcom/amazon/music/platform/playback/state/PlaybackState;Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;Lcom/amazon/music/platform/playback/data/PlaybackDevice;Lcom/amazon/music/platform/playback/state/PlayQueueMode;Lcom/amazon/music/platform/playback/state/DisplayMode;Z)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class DevicePlaybackState {
    private final EntityItemIdentifier currentEntityItemId;
    private final DisplayMode displayMode;
    private final boolean isUserInitiated;
    private final LoopMode loopMode;
    private final EntityItemIdentifier parentEntityItemId;
    private final PlaybackState playbackState;
    private final PlaybackProgress progress;
    private final PlayQueueMode queueMode;
    private final ShuffleMode shuffleMode;
    private final PlaybackDevice targetDevice;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DevicePlaybackState)) {
            return false;
        }
        DevicePlaybackState devicePlaybackState = (DevicePlaybackState) other;
        return Intrinsics.areEqual(this.progress, devicePlaybackState.progress) && this.loopMode == devicePlaybackState.loopMode && this.shuffleMode == devicePlaybackState.shuffleMode && this.playbackState == devicePlaybackState.playbackState && Intrinsics.areEqual(this.currentEntityItemId, devicePlaybackState.currentEntityItemId) && Intrinsics.areEqual(this.parentEntityItemId, devicePlaybackState.parentEntityItemId) && Intrinsics.areEqual(this.targetDevice, devicePlaybackState.targetDevice) && this.queueMode == devicePlaybackState.queueMode && this.displayMode == devicePlaybackState.displayMode && this.isUserInitiated == devicePlaybackState.isUserInitiated;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v17, types: [int] */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v20 */
    public int hashCode() {
        int iHashCode = ((((((this.progress.hashCode() * 31) + this.loopMode.hashCode()) * 31) + this.shuffleMode.hashCode()) * 31) + this.playbackState.hashCode()) * 31;
        EntityItemIdentifier entityItemIdentifier = this.currentEntityItemId;
        int iHashCode2 = (iHashCode + (entityItemIdentifier == null ? 0 : entityItemIdentifier.hashCode())) * 31;
        EntityItemIdentifier entityItemIdentifier2 = this.parentEntityItemId;
        int iHashCode3 = (((((((iHashCode2 + (entityItemIdentifier2 != null ? entityItemIdentifier2.hashCode() : 0)) * 31) + this.targetDevice.hashCode()) * 31) + this.queueMode.hashCode()) * 31) + this.displayMode.hashCode()) * 31;
        boolean z = this.isUserInitiated;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode3 + r1;
    }

    public String toString() {
        return "DevicePlaybackState(progress=" + this.progress + ", loopMode=" + this.loopMode + ", shuffleMode=" + this.shuffleMode + ", playbackState=" + this.playbackState + ", currentEntityItemId=" + this.currentEntityItemId + ", parentEntityItemId=" + this.parentEntityItemId + ", targetDevice=" + this.targetDevice + ", queueMode=" + this.queueMode + ", displayMode=" + this.displayMode + ", isUserInitiated=" + this.isUserInitiated + ')';
    }

    public DevicePlaybackState(PlaybackProgress progress, LoopMode loopMode, ShuffleMode shuffleMode, PlaybackState playbackState, EntityItemIdentifier entityItemIdentifier, EntityItemIdentifier entityItemIdentifier2, PlaybackDevice targetDevice, PlayQueueMode queueMode, DisplayMode displayMode, boolean z) {
        Intrinsics.checkNotNullParameter(progress, "progress");
        Intrinsics.checkNotNullParameter(loopMode, "loopMode");
        Intrinsics.checkNotNullParameter(shuffleMode, "shuffleMode");
        Intrinsics.checkNotNullParameter(playbackState, "playbackState");
        Intrinsics.checkNotNullParameter(targetDevice, "targetDevice");
        Intrinsics.checkNotNullParameter(queueMode, "queueMode");
        Intrinsics.checkNotNullParameter(displayMode, "displayMode");
        this.progress = progress;
        this.loopMode = loopMode;
        this.shuffleMode = shuffleMode;
        this.playbackState = playbackState;
        this.currentEntityItemId = entityItemIdentifier;
        this.parentEntityItemId = entityItemIdentifier2;
        this.targetDevice = targetDevice;
        this.queueMode = queueMode;
        this.displayMode = displayMode;
        this.isUserInitiated = z;
        PlatformProviderUtilKt.freeze(this);
    }

    public final PlaybackState getPlaybackState() {
        return this.playbackState;
    }

    public final EntityItemIdentifier getCurrentEntityItemId() {
        return this.currentEntityItemId;
    }

    public final EntityItemIdentifier getParentEntityItemId() {
        return this.parentEntityItemId;
    }
}
