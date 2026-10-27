package com.amazon.mp3.video.stories;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStoryAutoPlayEventHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R$\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\b\u0010\nR$\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u000b\u0010\t\u001a\u0004\b\f\u0010\nR$\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\r\u0010\t\u001a\u0004\b\r\u0010\nR$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u000e8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R.\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0007\u001a\u0004\u0018\u00010\u000e8\u0006@@X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/video/stories/AutoPlayStateManager;", "", "Lcom/amazon/mp3/video/stories/AutoPlayEvent;", NotificationCompat.CATEGORY_EVENT, "", "updateState", "", "<set-?>", "isPlaying", "Z", "()Z", "shouldPlay", "getShouldPlay", "isNewPlayback", "", "playbackRequestedTime", "J", "getPlaybackRequestedTime", "()J", "playbackStartedTime", "getPlaybackStartedTime", "initialPlaybackStartedTime", "Ljava/lang/Long;", "getInitialPlaybackStartedTime", "()Ljava/lang/Long;", "setInitialPlaybackStartedTime$DigitalMusicAndroid3P_automotiveProdRelease", "(Ljava/lang/Long;)V", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AutoPlayStateManager {
    private Long initialPlaybackStartedTime;
    private boolean isPlaying;
    private boolean shouldPlay;
    private boolean isNewPlayback = true;
    private long playbackRequestedTime = System.currentTimeMillis();
    private long playbackStartedTime = System.currentTimeMillis();

    /* JADX INFO: compiled from: VideoStoryAutoPlayEventHandler.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AutoPlayEvent.values().length];
            iArr[AutoPlayEvent.PLAY.ordinal()] = 1;
            iArr[AutoPlayEvent.PAUSE.ordinal()] = 2;
            iArr[AutoPlayEvent.PLAYBACK_STARTED.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: isPlaying, reason: from getter */
    public final boolean getIsPlaying() {
        return this.isPlaying;
    }

    public final boolean getShouldPlay() {
        return this.shouldPlay;
    }

    /* JADX INFO: renamed from: isNewPlayback, reason: from getter */
    public final boolean getIsNewPlayback() {
        return this.isNewPlayback;
    }

    public final long getPlaybackRequestedTime() {
        return this.playbackRequestedTime;
    }

    public final Long getInitialPlaybackStartedTime() {
        return this.initialPlaybackStartedTime;
    }

    public final void setInitialPlaybackStartedTime$DigitalMusicAndroid3P_automotiveProdRelease(Long l) {
        this.initialPlaybackStartedTime = l;
    }

    public final void updateState(AutoPlayEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        int i = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i == 1) {
            this.shouldPlay = true;
            this.isNewPlayback = true;
            this.isPlaying = false;
            this.playbackRequestedTime = System.currentTimeMillis();
            return;
        }
        if (i == 2) {
            this.shouldPlay = false;
            this.isPlaying = false;
        } else {
            if (i != 3) {
                return;
            }
            this.isPlaying = true;
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.playbackStartedTime = jCurrentTimeMillis;
            if (this.initialPlaybackStartedTime == null) {
                this.initialPlaybackStartedTime = Long.valueOf(jCurrentTimeMillis);
            }
            this.isNewPlayback = false;
        }
    }
}
