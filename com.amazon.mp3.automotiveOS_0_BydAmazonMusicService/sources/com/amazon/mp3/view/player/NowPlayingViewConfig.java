package com.amazon.mp3.view.player;

import UpsellInterface.v1_0.UpsellType;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.cloudqueue.sequencer.AdMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueTrackMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.InterludeMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.StormTrackMediaItem;
import com.amazon.mp3.explore.playback.HttpsMediaItem;
import com.amazon.mp3.library.provider.source.nowplaying.AlexaMediaItem;
import com.amazon.mp3.playback.casting.CastingMediaItem;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.sequencer.impl.StationMediaItem;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NowPlayingViewConfig.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b%\b\u0086\b\u0018\u0000 32\u00020\u0001:\u000234Bk\b\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0013\u001a\u00020\b\u0012\b\b\u0002\u0010\u0019\u001a\u00020\b\u0012\b\b\u0002\u0010\u001c\u001a\u00020\b\u0012\b\b\u0002\u0010\u001f\u001a\u00020\b\u0012\b\b\u0002\u0010\"\u001a\u00020\b\u0012\b\b\u0002\u0010%\u001a\u00020\b\u0012\b\b\u0002\u0010(\u001a\u00020\b\u0012\b\b\u0002\u0010+\u001a\u00020\b\u0012\b\b\u0002\u0010.\u001a\u00020\b¢\u0006\u0004\b1\u00102J\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006J\t\u0010\u000b\u001a\u00020\nHÖ\u0001J\t\u0010\r\u001a\u00020\fHÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\"\u0010\u001c\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u0014\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R\"\u0010\u001f\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010\u0014\u001a\u0004\b \u0010\u0016\"\u0004\b!\u0010\u0018R\"\u0010\"\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u0014\u001a\u0004\b#\u0010\u0016\"\u0004\b$\u0010\u0018R\"\u0010%\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010\u0014\u001a\u0004\b&\u0010\u0016\"\u0004\b'\u0010\u0018R\"\u0010(\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b(\u0010\u0014\u001a\u0004\b)\u0010\u0016\"\u0004\b*\u0010\u0018R\"\u0010+\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010\u0014\u001a\u0004\b,\u0010\u0016\"\u0004\b-\u0010\u0018R\"\u0010.\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b.\u0010\u0014\u001a\u0004\b/\u0010\u0016\"\u0004\b0\u0010\u0018¨\u00065"}, d2 = {"Lcom/amazon/mp3/view/player/NowPlayingViewConfig;", "", "Lcom/amazon/music/media/playback/MediaItem;", "mediaItem", "", "equalsPlayerContentType", "Lcom/amazon/mp3/view/player/PlayerViewAction;", "playerAction", "Lcom/amazon/mp3/view/player/EnabledViewState;", "getEnabledState", "", "toString", "", "hashCode", "other", "equals", "Lcom/amazon/mp3/view/player/NowPlayingViewConfig$PlayerContentType;", "playerContentType", "Lcom/amazon/mp3/view/player/NowPlayingViewConfig$PlayerContentType;", "showPlayPause", "Lcom/amazon/mp3/view/player/EnabledViewState;", "getShowPlayPause", "()Lcom/amazon/mp3/view/player/EnabledViewState;", "setShowPlayPause", "(Lcom/amazon/mp3/view/player/EnabledViewState;)V", "showNext", "getShowNext", "setShowNext", "showPrevious", "getShowPrevious", "setShowPrevious", "showShuffle", "getShowShuffle", "setShowShuffle", "showRepeat", "getShowRepeat", "setShowRepeat", "showPlayQueue", "getShowPlayQueue", "setShowPlayQueue", "showAlexa", "getShowAlexa", "setShowAlexa", "showThumbsUp", "getShowThumbsUp", "setShowThumbsUp", "showLike", "getShowLike", "setShowLike", "<init>", "(Lcom/amazon/mp3/view/player/NowPlayingViewConfig$PlayerContentType;Lcom/amazon/mp3/view/player/EnabledViewState;Lcom/amazon/mp3/view/player/EnabledViewState;Lcom/amazon/mp3/view/player/EnabledViewState;Lcom/amazon/mp3/view/player/EnabledViewState;Lcom/amazon/mp3/view/player/EnabledViewState;Lcom/amazon/mp3/view/player/EnabledViewState;Lcom/amazon/mp3/view/player/EnabledViewState;Lcom/amazon/mp3/view/player/EnabledViewState;Lcom/amazon/mp3/view/player/EnabledViewState;)V", "Companion", "PlayerContentType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class NowPlayingViewConfig {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = NowPlayingViewConfig.class.getSimpleName();
    private final PlayerContentType playerContentType;
    private EnabledViewState showAlexa;
    private EnabledViewState showLike;
    private EnabledViewState showNext;
    private EnabledViewState showPlayPause;
    private EnabledViewState showPlayQueue;
    private EnabledViewState showPrevious;
    private EnabledViewState showRepeat;
    private EnabledViewState showShuffle;
    private EnabledViewState showThumbsUp;

    /* JADX INFO: compiled from: NowPlayingViewConfig.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlayerViewAction.values().length];
            iArr[PlayerViewAction.ALEXA.ordinal()] = 1;
            iArr[PlayerViewAction.NEXT.ordinal()] = 2;
            iArr[PlayerViewAction.PLAY.ordinal()] = 3;
            iArr[PlayerViewAction.PAUSE.ordinal()] = 4;
            iArr[PlayerViewAction.PLAY_QUEUE.ordinal()] = 5;
            iArr[PlayerViewAction.PREVIOUS.ordinal()] = 6;
            iArr[PlayerViewAction.RATE.ordinal()] = 7;
            iArr[PlayerViewAction.REPEAT.ordinal()] = 8;
            iArr[PlayerViewAction.SHUFFLE.ordinal()] = 9;
            iArr[PlayerViewAction.LIKE.ordinal()] = 10;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final NowPlayingViewConfig fromMediaItem(MediaItem mediaItem) {
        return INSTANCE.fromMediaItem(mediaItem);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NowPlayingViewConfig)) {
            return false;
        }
        NowPlayingViewConfig nowPlayingViewConfig = (NowPlayingViewConfig) other;
        return this.playerContentType == nowPlayingViewConfig.playerContentType && this.showPlayPause == nowPlayingViewConfig.showPlayPause && this.showNext == nowPlayingViewConfig.showNext && this.showPrevious == nowPlayingViewConfig.showPrevious && this.showShuffle == nowPlayingViewConfig.showShuffle && this.showRepeat == nowPlayingViewConfig.showRepeat && this.showPlayQueue == nowPlayingViewConfig.showPlayQueue && this.showAlexa == nowPlayingViewConfig.showAlexa && this.showThumbsUp == nowPlayingViewConfig.showThumbsUp && this.showLike == nowPlayingViewConfig.showLike;
    }

    public int hashCode() {
        return (((((((((((((((((this.playerContentType.hashCode() * 31) + this.showPlayPause.hashCode()) * 31) + this.showNext.hashCode()) * 31) + this.showPrevious.hashCode()) * 31) + this.showShuffle.hashCode()) * 31) + this.showRepeat.hashCode()) * 31) + this.showPlayQueue.hashCode()) * 31) + this.showAlexa.hashCode()) * 31) + this.showThumbsUp.hashCode()) * 31) + this.showLike.hashCode();
    }

    public String toString() {
        return "NowPlayingViewConfig(playerContentType=" + this.playerContentType + ", showPlayPause=" + this.showPlayPause + ", showNext=" + this.showNext + ", showPrevious=" + this.showPrevious + ", showShuffle=" + this.showShuffle + ", showRepeat=" + this.showRepeat + ", showPlayQueue=" + this.showPlayQueue + ", showAlexa=" + this.showAlexa + ", showThumbsUp=" + this.showThumbsUp + ", showLike=" + this.showLike + ')';
    }

    private NowPlayingViewConfig(PlayerContentType playerContentType, EnabledViewState enabledViewState, EnabledViewState enabledViewState2, EnabledViewState enabledViewState3, EnabledViewState enabledViewState4, EnabledViewState enabledViewState5, EnabledViewState enabledViewState6, EnabledViewState enabledViewState7, EnabledViewState enabledViewState8, EnabledViewState enabledViewState9) {
        this.playerContentType = playerContentType;
        this.showPlayPause = enabledViewState;
        this.showNext = enabledViewState2;
        this.showPrevious = enabledViewState3;
        this.showShuffle = enabledViewState4;
        this.showRepeat = enabledViewState5;
        this.showPlayQueue = enabledViewState6;
        this.showAlexa = enabledViewState7;
        this.showThumbsUp = enabledViewState8;
        this.showLike = enabledViewState9;
    }

    /* synthetic */ NowPlayingViewConfig(PlayerContentType playerContentType, EnabledViewState enabledViewState, EnabledViewState enabledViewState2, EnabledViewState enabledViewState3, EnabledViewState enabledViewState4, EnabledViewState enabledViewState5, EnabledViewState enabledViewState6, EnabledViewState enabledViewState7, EnabledViewState enabledViewState8, EnabledViewState enabledViewState9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(playerContentType, (i & 2) != 0 ? EnabledViewState.GONE : enabledViewState, (i & 4) != 0 ? EnabledViewState.GONE : enabledViewState2, (i & 8) != 0 ? EnabledViewState.GONE : enabledViewState3, (i & 16) != 0 ? EnabledViewState.GONE : enabledViewState4, (i & 32) != 0 ? EnabledViewState.GONE : enabledViewState5, (i & 64) != 0 ? EnabledViewState.GONE : enabledViewState6, (i & 128) != 0 ? EnabledViewState.GONE : enabledViewState7, (i & 256) != 0 ? EnabledViewState.GONE : enabledViewState8, (i & 512) != 0 ? EnabledViewState.GONE : enabledViewState9);
    }

    public final EnabledViewState getShowRepeat() {
        return this.showRepeat;
    }

    /* JADX INFO: compiled from: NowPlayingViewConfig.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/view/player/NowPlayingViewConfig$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "fromMediaItem", "Lcom/amazon/mp3/view/player/NowPlayingViewConfig;", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: NowPlayingViewConfig.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[PlayerContentType.values().length];
                iArr[PlayerContentType.AD.ordinal()] = 1;
                iArr[PlayerContentType.MUSIC.ordinal()] = 2;
                iArr[PlayerContentType.HTTPS.ordinal()] = 3;
                iArr[PlayerContentType.FREE.ordinal()] = 4;
                iArr[PlayerContentType.ALEXA.ordinal()] = 5;
                iArr[PlayerContentType.STATION.ordinal()] = 6;
                iArr[PlayerContentType.VIDEO.ordinal()] = 7;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final NowPlayingViewConfig fromMediaItem(MediaItem mediaItem) {
            EnabledViewState enabledViewState;
            EnabledViewState enabledViewState2 = AmazonApplication.getCapabilities().isNPSLikesSupported() ? EnabledViewState.GONE : EnabledViewState.ENABLED;
            EnabledViewState enabledViewState3 = AmazonApplication.getCapabilities().isNPSLikesSupported() ? EnabledViewState.ENABLED : EnabledViewState.GONE;
            PlayerContentType playerContentTypeFromMediaItem = PlayerContentType.INSTANCE.fromMediaItem(mediaItem);
            switch (WhenMappings.$EnumSwitchMapping$0[playerContentTypeFromMediaItem.ordinal()]) {
                case 1:
                    EnabledViewState enabledViewState4 = EnabledViewState.ENABLED;
                    EnabledViewState enabledViewState5 = EnabledViewState.DISABLED;
                    return new NowPlayingViewConfig(playerContentTypeFromMediaItem, enabledViewState4, enabledViewState5, enabledViewState5, null, null, null, enabledViewState4, null, null, 880, null);
                case 2:
                case 3:
                    EnabledViewState enabledViewState6 = EnabledViewState.ENABLED;
                    return new NowPlayingViewConfig(playerContentTypeFromMediaItem, enabledViewState6, enabledViewState6, enabledViewState6, enabledViewState6, enabledViewState6, enabledViewState6, enabledViewState6, null, enabledViewState3, 256, null);
                case 4:
                    EnabledViewState enabledViewState7 = EnabledViewState.ENABLED;
                    return new NowPlayingViewConfig(playerContentTypeFromMediaItem, enabledViewState7, enabledViewState7, enabledViewState7, null, null, EnabledViewState.DISABLED, enabledViewState7, enabledViewState2, enabledViewState3, 48, null);
                case 5:
                    EnabledViewState enabledViewState8 = EnabledViewState.ENABLED;
                    return new NowPlayingViewConfig(playerContentTypeFromMediaItem, enabledViewState8, enabledViewState8, enabledViewState8, null, null, EnabledViewState.DISABLED, enabledViewState8, enabledViewState2, enabledViewState3, 48, null);
                case 6:
                    if (((mediaItem instanceof StormTrackMediaItem) || (mediaItem instanceof InterludeMediaItem)) && CloudQueueSequencer.INSTANCE.isCloudQueuePlaying()) {
                        enabledViewState = EnabledViewState.ENABLED;
                    } else {
                        enabledViewState = EnabledViewState.GONE;
                    }
                    EnabledViewState enabledViewState9 = EnabledViewState.ENABLED;
                    return new NowPlayingViewConfig(playerContentTypeFromMediaItem, enabledViewState9, enabledViewState9, enabledViewState9, null, null, enabledViewState, enabledViewState9, enabledViewState2, enabledViewState3, 48, null);
                case 7:
                    EnabledViewState enabledViewState10 = EnabledViewState.ENABLED;
                    return new NowPlayingViewConfig(playerContentTypeFromMediaItem, enabledViewState10, enabledViewState10, enabledViewState10, null, null, enabledViewState10, enabledViewState10, null, null, 816, null);
                default:
                    return new NowPlayingViewConfig(playerContentTypeFromMediaItem, null, null, null, null, null, null, null, null, null, 1022, null);
            }
        }
    }

    /* JADX INFO: compiled from: NowPlayingViewConfig.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/view/player/NowPlayingViewConfig$PlayerContentType;", "", "(Ljava/lang/String;I)V", "AD", "ALEXA", UpsellType.FREE, "HTTPS", "MUSIC", "STATION", "VIDEO", "DEFAULT", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum PlayerContentType {
        AD,
        ALEXA,
        FREE,
        HTTPS,
        MUSIC,
        STATION,
        VIDEO,
        DEFAULT;


        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JvmStatic
        public static final PlayerContentType fromMediaItem(MediaItem mediaItem) {
            return INSTANCE.fromMediaItem(mediaItem);
        }

        /* JADX INFO: compiled from: NowPlayingViewConfig.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/view/player/NowPlayingViewConfig$PlayerContentType$Companion;", "", "()V", "fromMediaItem", "Lcom/amazon/mp3/view/player/NowPlayingViewConfig$PlayerContentType;", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final PlayerContentType fromMediaItem(MediaItem mediaItem) {
                if (mediaItem == null) {
                    return PlayerContentType.DEFAULT;
                }
                if (mediaItem instanceof AdMediaItem) {
                    return PlayerContentType.AD;
                }
                if (mediaItem instanceof AlexaMediaItem) {
                    return PlayerContentType.ALEXA;
                }
                if (mediaItem instanceof HttpsMediaItem) {
                    return PlayerContentType.HTTPS;
                }
                if (mediaItem instanceof CloudQueueTrackMediaItem) {
                    return PlayerContentType.FREE;
                }
                if (mediaItem instanceof StationMediaItem) {
                    return PlayerContentType.STATION;
                }
                if (!(mediaItem instanceof CastingMediaItem ? true : mediaItem instanceof StormTrackMediaItem)) {
                    String str = NowPlayingViewConfig.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unhandled mediaItem: ");
                    sb.append(mediaItem);
                    sb.append(" type. Defaulting to ");
                    PlayerContentType playerContentType = PlayerContentType.MUSIC;
                    sb.append(playerContentType);
                    Log.warning(str, sb.toString());
                    return playerContentType;
                }
                if (mediaItem.canRate()) {
                    return PlayerContentType.STATION;
                }
                MediaItemId mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.PID);
                if ((mediaItemId == null ? null : mediaItemId.getId()) != null) {
                    return PlayerContentType.VIDEO;
                }
                String str2 = NowPlayingViewConfig.TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Defaulting ");
                sb2.append(mediaItem);
                sb2.append(" type to ");
                PlayerContentType playerContentType2 = PlayerContentType.MUSIC;
                sb2.append(playerContentType2);
                Log.warning(str2, sb2.toString());
                return playerContentType2;
            }
        }
    }

    public final boolean equalsPlayerContentType(MediaItem mediaItem) {
        return PlayerContentType.INSTANCE.fromMediaItem(mediaItem) == this.playerContentType;
    }

    public final EnabledViewState getEnabledState(PlayerViewAction playerAction) {
        Intrinsics.checkNotNullParameter(playerAction, "playerAction");
        switch (WhenMappings.$EnumSwitchMapping$0[playerAction.ordinal()]) {
            case 1:
                return this.showAlexa;
            case 2:
                return this.showNext;
            case 3:
            case 4:
                return this.showPlayPause;
            case 5:
                return this.showPlayQueue;
            case 6:
                return this.showPrevious;
            case 7:
                return this.showThumbsUp;
            case 8:
                return this.showRepeat;
            case 9:
                return this.showShuffle;
            case 10:
                return this.showLike;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
