package com.amazon.music.media.playback.player;

import android.net.Uri;
import androidx.media.VolumeProviderCompat;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnFrameChangedListener;
import com.amazon.music.media.playback.OnPlaybackMetricsListener;
import com.amazon.music.media.playback.OnPrebufferChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.PlayerCapabilities;
import com.amazon.music.media.playback.audioeffects.AudioEffectsControl;
import com.amazon.music.media.playback.concurrency.ConcurrencyProvider;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.impl.AudioFocusManager;
import com.amazon.music.media.playback.player.CompositePlayer;
import com.amazon.music.media.playback.player.Player;
import com.amazon.music.media.playback.player.impl.HarleyPlayer;
import com.amazon.music.media.playback.player.impl.PlayRequestStatusHandler;
import com.amazon.music.media.playback.player.impl.PrimeVideoPlayer;
import com.amazon.music.media.playback.util.PlayerUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CompositePlayer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 \u008b\u00012\u00020\u0001:\u0004\u008b\u0001\u008c\u0001B\t¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H\u0002J$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002J\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004J\b\u0010\u0012\u001a\u00020\u000eH\u0016J(\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019H\u0016J\b\u0010\u001c\u001a\u00020\u0017H\u0016JJ\u0010\"\u001a\u00020\u000e2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010!\u001a\u00020\bH\u0016JB\u0010%\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010!\u001a\u00020\bH\u0016J*\u0010(\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00062\b\u0010'\u001a\u0004\u0018\u00010&2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010!\u001a\u00020\bJ,\u0010)\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010!\u001a\u00020\bH\u0004JJ\u0010.\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010!\u001a\u00020\bJ4\u0010/\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010!\u001a\u00020\bH\u0016J\b\u00101\u001a\u000200H\u0016J\b\u00103\u001a\u000202H\u0016J\b\u00104\u001a\u000202H\u0016J\b\u00105\u001a\u00020\u001fH\u0016J\n\u00107\u001a\u0004\u0018\u000106H\u0016J$\u00108\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010!\u001a\u00020\bH\u0016J\u001c\u00109\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010:\u001a\u00020\u001fH\u0016J$\u0010;\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010<\u001a\u00020\bH\u0016J\b\u0010=\u001a\u00020\bH\u0016J\b\u0010>\u001a\u00020\u0006H\u0016J\b\u0010?\u001a\u00020\u0006H\u0016J\u000e\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00020@H\u0016J\n\u0010B\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010C\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0006H\u0016J\u0010\u0010F\u001a\u00020\u000e2\u0006\u0010E\u001a\u00020DH\u0016J\b\u0010G\u001a\u00020DH\u0016J\b\u0010H\u001a\u00020DH\u0016J\b\u0010J\u001a\u00020IH\u0016J\n\u0010L\u001a\u0004\u0018\u00010KH\u0016J\b\u0010M\u001a\u00020\u0006H\u0016J\u0010\u0010O\u001a\u00020\u000e2\u0006\u0010N\u001a\u00020\u001fH\u0016J\b\u0010P\u001a\u00020DH\u0016J\b\u0010Q\u001a\u00020\u000eH\u0016J\u0012\u0010R\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010S\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010T\u001a\u00020\u001fH\u0016J\b\u0010U\u001a\u00020\u001fH\u0016J\b\u0010W\u001a\u00020VH\u0016J\b\u0010Y\u001a\u00020XH\u0016J\u0010\u0010\\\u001a\u00020\u001f2\u0006\u0010[\u001a\u00020ZH\u0016J\b\u0010]\u001a\u00020\u000eH\u0016J\u0010\u0010`\u001a\u00020\u000e2\u0006\u0010_\u001a\u00020^H\u0016J\u0012\u0010c\u001a\u00020\u000e2\b\u0010b\u001a\u0004\u0018\u00010aH\u0016J\u0012\u0010f\u001a\u00020\u000e2\b\u0010e\u001a\u0004\u0018\u00010dH\u0016J\u0012\u0010i\u001a\u00020\u000e2\b\u0010h\u001a\u0004\u0018\u00010gH\u0016J\u0006\u0010j\u001a\u00020\u0001J\u0006\u0010k\u001a\u00020\u001fJ\u0010\u0010m\u001a\u00020\u001f2\b\u0010l\u001a\u0004\u0018\u00010\u0002J\u000e\u0010o\u001a\u00020\u000e2\u0006\u0010n\u001a\u00020\u0001R\u0017\u0010q\u001a\u00020p8\u0006¢\u0006\f\n\u0004\bq\u0010r\u001a\u0004\bs\u0010tR\"\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0014\u0010u\u001a\u0004\bv\u0010w\"\u0004\bx\u0010yR\"\u0010\u0018\u001a\u00020\u00178\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010z\u001a\u0004\b{\u0010|\"\u0004\b}\u0010~R\u0019\u0010\u007f\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R\u0018\u0010\u0082\u0001\u001a\u00030\u0081\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0014\u0010\u0086\u0001\u001a\u00020\u00018F¢\u0006\b\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0014\u0010\u0088\u0001\u001a\u00020\u00018F¢\u0006\b\u001a\u0006\b\u0087\u0001\u0010\u0085\u0001¨\u0006\u008d\u0001"}, d2 = {"Lcom/amazon/music/media/playback/player/CompositePlayer;", "Lcom/amazon/music/media/playback/player/Player;", "Lcom/amazon/music/media/playback/player/PlayRequest;", "request", "", "requests", "", "getSubQueueIndexForPlayRequest", "", "eventTimeMillis", "Lcom/amazon/music/media/playback/ChangeReason;", "changeReason", "Lcom/amazon/music/media/playback/ControlSource;", "controlSource", "", "updateCurrentRequest", "index", "getSubqueueForIndex", "close", "Lcom/amazon/music/media/playback/player/Player$Callback;", "callback", "Lcom/amazon/music/media/playback/config/PlaybackConfig;", "playbackConfig", "Lcom/amazon/music/media/playback/player/Player$State;", "state", "Lcom/amazon/music/media/playback/impl/AudioFocusManager;", "audioFocusManager", "connect", "disconnect", "startIndex", "positionMillis", "", "shouldPlay", "requestTime", "setQueue", "position", "numOldItems", "modifyQueue", "Lcom/amazon/music/media/playback/PlaybackException;", "playException", "setCurrentIndex", "terminate", "oldIndex", "newIndex", "startChangesIndex", "numNewItems", "onQueueChanged", "setIndex", "Lcom/amazon/music/media/playback/PlayStatus;", "getPlayStatus", "Lcom/amazon/music/media/playback/PlaybackAttributes;", "getCurrentPlaybackAttributes", "getDynamicBestAvailablePlaybackAttributes", "isStreaming", "Lcom/amazon/music/media/playback/PlayerCapabilities;", "getPlayerCapabilities", PageUriUtils.DO_PLAY_VALUE, "pause", "canSeek", "seek", "getPositionMillis", "getDurationMillis", "getNumPlayRequests", "getCurrentIndex", "", "getQueue", "getCurrentPlayRequest", "getPlayRequest", "", "normalVolume", "setNormalVolume", "getNormalVolume", "getMaxVolume", "Lcom/amazon/music/media/playback/concurrency/ConcurrencyProvider;", "getConcurrencyProvider", "Landroidx/media/VolumeProviderCompat;", "getVolumeProvider", "getAudioFocusStatus", "loopMediaItem", "setLoopMediaItem", "getPlaybackSpeed", "normalSpeed", "fastForward", "rewind", "canFastForward", "canRewind", "", "getName", "Lcom/amazon/music/media/playback/audioeffects/AudioEffectsControl;", "getAudioEffectsController", "Landroid/net/Uri;", "uri", "canPlay", "clearData", "", "speed", "setPlaybackSpeed", "Lcom/amazon/music/media/playback/OnPlaybackMetricsListener;", "playbackMetricsListener", "setPlaybackMetricsListener", "Lcom/amazon/music/media/playback/OnFrameChangedListener;", "frameChangedListener", "setOnFrameChangedListener", "Lcom/amazon/music/media/playback/OnPrebufferChangedListener;", "prebufferChangedListener", "setOnPrebufferChangedListener", "getCurrentPlayer", "isMasterCurrentIndexValid", "subPlayerPlayRequest", "isMasterPlayRequestTheSameAs", "player", "lateInitializePlayer", "Lcom/amazon/music/media/playback/player/CompositePlayer$PlayerSelector;", "playerSelector", "Lcom/amazon/music/media/playback/player/CompositePlayer$PlayerSelector;", "getPlayerSelector", "()Lcom/amazon/music/media/playback/player/CompositePlayer$PlayerSelector;", "Lcom/amazon/music/media/playback/player/Player$Callback;", "getCallback", "()Lcom/amazon/music/media/playback/player/Player$Callback;", "setCallback", "(Lcom/amazon/music/media/playback/player/Player$Callback;)V", "Lcom/amazon/music/media/playback/player/Player$State;", "getState", "()Lcom/amazon/music/media/playback/player/Player$State;", "setState", "(Lcom/amazon/music/media/playback/player/Player$State;)V", "currentRequest", "Lcom/amazon/music/media/playback/player/PlayRequest;", "Lcom/amazon/music/media/playback/player/impl/PlayRequestStatusHandler;", "playRequestStatusHandler", "Lcom/amazon/music/media/playback/player/impl/PlayRequestStatusHandler;", "getHarleyPlayer", "()Lcom/amazon/music/media/playback/player/Player;", "harleyPlayer", "getPrimeVideoPlayer", "primeVideoPlayer", "<init>", "()V", "Companion", "PlayerSelector", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class CompositePlayer implements Player {
    private static final Logger logger;
    public Player.Callback callback;
    private PlayRequest currentRequest;
    private final PlayerSelector playerSelector = new PlayerSelector(this);
    private Player.State state = new Player.State();
    private final PlayRequestStatusHandler playRequestStatusHandler = new PlayRequestStatusHandler();

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        logger = LoggerFactory.getLogger(companion.getClass().getSimpleName());
    }

    public final Player getHarleyPlayer() {
        Player localPlayer = Playback.getInstance().getPlaybackConfig().getLocalPlayer(null);
        Intrinsics.checkNotNullExpressionValue(localPlayer, "getInstance().playbackConfig.getLocalPlayer(null)");
        return localPlayer;
    }

    public final Player getPrimeVideoPlayer() {
        Player primeVideoPlayer = Playback.getInstance().getPlaybackConfig().getPrimeVideoPlayer();
        Intrinsics.checkNotNullExpressionValue(primeVideoPlayer, "getInstance().playbackConfig.primeVideoPlayer");
        return primeVideoPlayer;
    }

    public final Player.Callback getCallback() {
        Player.Callback callback = this.callback;
        if (callback != null) {
            return callback;
        }
        Intrinsics.throwUninitializedPropertyAccessException("callback");
        return null;
    }

    public final void setCallback(Player.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "<set-?>");
        this.callback = callback;
    }

    public final Player.State getState() {
        return this.state;
    }

    public final List<PlayRequest> getSubqueueForIndex(int index, List<? extends PlayRequest> requests) {
        PlayerSelector playerSelector;
        MediaItem mediaItem;
        Intrinsics.checkNotNullParameter(requests, "requests");
        if (index >= requests.size()) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        MediaItem startMediaItem = requests.get(index).getMediaItem();
        PlayerSelector playerSelector2 = this.playerSelector;
        Intrinsics.checkNotNullExpressionValue(startMediaItem, "startMediaItem");
        Player player = playerSelector2.getPlayer(startMediaItem);
        int i = index;
        while (true) {
            int i2 = i - 1;
            if (i2 < 0) {
                break;
            }
            PlayerSelector playerSelector3 = this.playerSelector;
            MediaItem mediaItem2 = requests.get(i2).getMediaItem();
            Intrinsics.checkNotNullExpressionValue(mediaItem2, "requests[lowerIndex - 1].mediaItem");
            if (!playerSelector3.isMediaItemForPlayer(mediaItem2, player)) {
                break;
            }
            i--;
        }
        do {
            index++;
            if (index >= requests.size()) {
                break;
            }
            playerSelector = this.playerSelector;
            mediaItem = requests.get(index).getMediaItem();
            Intrinsics.checkNotNullExpressionValue(mediaItem, "requests[upperIndex + 1].mediaItem");
        } while (playerSelector.isMediaItemForPlayer(mediaItem, player));
        return requests.subList(i, index);
    }

    private final int getSubQueueIndexForPlayRequest(PlayRequest request, List<? extends PlayRequest> requests) {
        return getSubqueueForIndex(requests.indexOf(request), requests).indexOf(request);
    }

    @Override // com.amazon.music.media.playback.player.Player, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.playerSelector.close();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void connect(Player.Callback callback, PlaybackConfig playbackConfig, Player.State state, AudioFocusManager audioFocusManager) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(playbackConfig, "playbackConfig");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(audioFocusManager, "audioFocusManager");
        setCallback(callback);
        this.playerSelector.connectPlayers(callback, playbackConfig, new Player.State(), audioFocusManager);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public Player.State disconnect() {
        Player.State state = this.state;
        this.playerSelector.disconnectPlayers();
        return state;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x012b  */
    @Override // com.amazon.music.media.playback.player.Player
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setQueue(java.util.List<? extends com.amazon.music.media.playback.player.PlayRequest> r27, int r28, long r29, boolean r31, com.amazon.music.media.playback.ChangeReason r32, com.amazon.music.media.playback.ControlSource r33, long r34) {
        /*
            Method dump skipped, instruction units count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.playback.player.CompositePlayer.setQueue(java.util.List, int, long, boolean, com.amazon.music.media.playback.ChangeReason, com.amazon.music.media.playback.ControlSource, long):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x018b  */
    @Override // com.amazon.music.media.playback.player.Player
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void modifyQueue(int r22, int r23, java.util.List<? extends com.amazon.music.media.playback.player.PlayRequest> r24, com.amazon.music.media.playback.ChangeReason r25, com.amazon.music.media.playback.ControlSource r26, long r27) {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.playback.player.CompositePlayer.modifyQueue(int, int, java.util.List, com.amazon.music.media.playback.ChangeReason, com.amazon.music.media.playback.ControlSource, long):void");
    }

    public final void setCurrentIndex(int index, PlaybackException playException, ChangeReason changeReason, long requestTime) {
        int currentIndex = getCurrentIndex();
        this.state.setCurIndex(index);
        updateCurrentRequest(requestTime, changeReason, null);
        if (getCallback() != null) {
            getCallback().onIndexChanged(currentIndex, index, changeReason, playException);
        }
    }

    private final void updateCurrentRequest(long eventTimeMillis, ChangeReason changeReason, ControlSource controlSource) {
        PlayRequest playRequest;
        PlayRequest currentPlayRequest = getCurrentPlayRequest();
        if (currentPlayRequest == null || (playRequest = this.currentRequest) == currentPlayRequest) {
            return;
        }
        if (playRequest != null && changeReason != ChangeReason.SWITCHING_PLAYERS && changeReason != ChangeReason.TOGGLE) {
            if (playRequest == null) {
                return;
            } else {
                terminate(playRequest, changeReason, controlSource, eventTimeMillis);
            }
        }
        this.currentRequest = currentPlayRequest;
    }

    protected final void terminate(PlayRequest request, ChangeReason changeReason, ControlSource controlSource, long requestTime) {
        Intrinsics.checkNotNullParameter(request, "request");
        logger.info("terminate(" + request + ", " + changeReason + ", " + controlSource + ", " + requestTime + ')');
        this.playRequestStatusHandler.setPlayRequestStatus(request, PlayRequestStatus.TERMINATED, getPositionMillis(), requestTime, -1.0f, null, changeReason, controlSource);
    }

    public final void onQueueChanged(int oldIndex, int newIndex, int startChangesIndex, int numOldItems, int numNewItems, ChangeReason changeReason, ControlSource controlSource, long requestTime) {
        List<PlayRequest> queue = getCurrentPlayer().getQueue();
        Intrinsics.checkNotNullExpressionValue(queue, "getCurrentPlayer().queue");
        List list = CollectionsKt___CollectionsKt.toList(queue);
        int currentIndex = getCurrentPlayer().getCurrentIndex();
        List<PlayRequest> playRequests = this.state.getPlayRequests();
        Intrinsics.checkNotNullExpressionValue(playRequests, "state.playRequests");
        List list2 = CollectionsKt___CollectionsKt.toList(getSubqueueForIndex(newIndex, playRequests));
        int iIndexOf = list2.indexOf(this.state.getPlayRequests().get(newIndex));
        List<PlayRequest> listEmptyList = list2.size() < iIndexOf ? CollectionsKt__CollectionsKt.emptyList() : list2.subList(0, iIndexOf);
        List listEmptyList2 = list.size() < currentIndex ? CollectionsKt__CollectionsKt.emptyList() : list.subList(0, currentIndex);
        if (!Intrinsics.areEqual(listEmptyList, listEmptyList2)) {
            getCurrentPlayer().modifyQueue(0, listEmptyList2.size(), listEmptyList, changeReason, controlSource, requestTime);
        }
        int i = iIndexOf + 1;
        if (i <= list2.size()) {
            iIndexOf = i;
        }
        List<PlayRequest> listEmptyList3 = list2.size() < iIndexOf ? CollectionsKt__CollectionsKt.emptyList() : list2.subList(iIndexOf, list2.size());
        int i2 = currentIndex + 1;
        if (i2 <= list.size()) {
            currentIndex = i2;
        }
        List listEmptyList4 = list.size() < currentIndex ? CollectionsKt__CollectionsKt.emptyList() : list.subList(currentIndex, list.size());
        if (Intrinsics.areEqual(listEmptyList3, listEmptyList4)) {
            return;
        }
        getCurrentPlayer().modifyQueue(i2, listEmptyList4.size(), listEmptyList3, changeReason, controlSource, requestTime);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ff  */
    @Override // com.amazon.music.media.playback.player.Player
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setIndex(int r18, boolean r19, com.amazon.music.media.playback.ChangeReason r20, com.amazon.music.media.playback.ControlSource r21, long r22) {
        /*
            Method dump skipped, instruction units count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.playback.player.CompositePlayer.setIndex(int, boolean, com.amazon.music.media.playback.ChangeReason, com.amazon.music.media.playback.ControlSource, long):void");
    }

    @Override // com.amazon.music.media.playback.player.Player
    public PlayStatus getPlayStatus() {
        PlayStatus playStatus = this.state.getPlayStatus();
        Intrinsics.checkNotNullExpressionValue(playStatus, "state.playStatus");
        return playStatus;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public PlaybackAttributes getCurrentPlaybackAttributes() {
        PlaybackAttributes currentPlaybackAttributes = getCurrentPlayer().getCurrentPlaybackAttributes();
        Intrinsics.checkNotNullExpressionValue(currentPlaybackAttributes, "getCurrentPlayer().currentPlaybackAttributes");
        return currentPlaybackAttributes;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public PlaybackAttributes getDynamicBestAvailablePlaybackAttributes() {
        PlaybackAttributes dynamicBestAvailablePlaybackAttributes = getCurrentPlayer().getDynamicBestAvailablePlaybackAttributes();
        Intrinsics.checkNotNullExpressionValue(dynamicBestAvailablePlaybackAttributes, "getCurrentPlayer().dynam…ailablePlaybackAttributes");
        return dynamicBestAvailablePlaybackAttributes;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public boolean isStreaming() {
        return getCurrentPlayer().isStreaming();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public PlayerCapabilities getPlayerCapabilities() {
        return getCurrentPlayer().getPlayerCapabilities();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void play(ChangeReason changeReason, ControlSource controlSource, long requestTime) {
        if (this.state.getCurIndex() >= this.state.getPlayRequests().size()) {
            return;
        }
        PlayerSelector playerSelector = this.playerSelector;
        MediaItem mediaItem = this.state.getPlayRequests().get(this.state.getCurIndex()).getMediaItem();
        Intrinsics.checkNotNullExpressionValue(mediaItem, "state.playRequests[state.curIndex].mediaItem");
        playerSelector.getPlayer(mediaItem).play(changeReason, controlSource, requestTime);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void pause(ChangeReason changeReason, ControlSource controlSource) {
        getCurrentPlayer().pause(changeReason, controlSource);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public boolean canSeek() {
        return getCurrentPlayer().canSeek();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void seek(long positionMillis, ChangeReason changeReason, ControlSource controlSource) {
        getCurrentPlayer().seek(positionMillis, changeReason, controlSource);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public long getPositionMillis() {
        return getCurrentPlayer().getPositionMillis();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public long getDurationMillis() {
        return getCurrentPlayer().getDurationMillis();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public int getNumPlayRequests() {
        return this.state.getPlayRequests().size();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public int getCurrentIndex() {
        return this.state.getCurIndex();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public List<PlayRequest> getQueue() {
        List<PlayRequest> playRequests = this.state.getPlayRequests();
        Intrinsics.checkNotNullExpressionValue(playRequests, "state.playRequests");
        return playRequests;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public PlayRequest getCurrentPlayRequest() {
        if (this.state.getCurIndex() < 0 || this.state.getCurIndex() >= this.state.getPlayRequests().size()) {
            return null;
        }
        return this.state.getPlayRequests().get(this.state.getCurIndex());
    }

    @Override // com.amazon.music.media.playback.player.Player
    public PlayRequest getPlayRequest(int index) {
        PlayRequest playRequest = this.state.getPlayRequests().get(index);
        Intrinsics.checkNotNullExpressionValue(playRequest, "state.playRequests[index]");
        return playRequest;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void setNormalVolume(float normalVolume) {
        getCurrentPlayer().setNormalVolume(normalVolume);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public float getNormalVolume() {
        return getCurrentPlayer().getNormalVolume();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public float getMaxVolume() {
        return getCurrentPlayer().getMaxVolume();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public ConcurrencyProvider getConcurrencyProvider() {
        ConcurrencyProvider concurrencyProvider = getHarleyPlayer().getConcurrencyProvider();
        Intrinsics.checkNotNullExpressionValue(concurrencyProvider, "harleyPlayer.concurrencyProvider");
        return concurrencyProvider;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public VolumeProviderCompat getVolumeProvider() {
        return getCurrentPlayer().getVolumeProvider();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public int getAudioFocusStatus() {
        return getCurrentPlayer().getAudioFocusStatus();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void setLoopMediaItem(boolean loopMediaItem) {
        getCurrentPlayer().setLoopMediaItem(loopMediaItem);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public float getPlaybackSpeed() {
        return getCurrentPlayer().getPlaybackSpeed();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void normalSpeed() {
        getCurrentPlayer().normalSpeed();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void fastForward(ControlSource controlSource) {
        getCurrentPlayer().fastForward(controlSource);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void rewind(ControlSource controlSource) {
        getCurrentPlayer().rewind(controlSource);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public boolean canFastForward() {
        return getCurrentPlayer().canFastForward();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public boolean canRewind() {
        return getCurrentPlayer().canRewind();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public String getName() {
        String name = getCurrentPlayer().getName();
        Intrinsics.checkNotNullExpressionValue(name, "getCurrentPlayer().name");
        return name;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public AudioEffectsControl getAudioEffectsController() {
        AudioEffectsControl audioEffectsController = getCurrentPlayer().getAudioEffectsController();
        Intrinsics.checkNotNullExpressionValue(audioEffectsController, "getCurrentPlayer().audioEffectsController");
        return audioEffectsController;
    }

    @Override // com.amazon.music.media.playback.player.Player
    public boolean canPlay(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return getCurrentPlayer().canPlay(uri);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void clearData() {
        getCurrentPlayer().clearData();
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void setPlaybackSpeed(double speed) {
        getCurrentPlayer().setPlaybackSpeed(speed);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void setPlaybackMetricsListener(OnPlaybackMetricsListener playbackMetricsListener) {
        getCurrentPlayer().setPlaybackMetricsListener(playbackMetricsListener);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void setOnFrameChangedListener(OnFrameChangedListener frameChangedListener) {
        getCurrentPlayer().setOnFrameChangedListener(frameChangedListener);
    }

    @Override // com.amazon.music.media.playback.player.Player
    public void setOnPrebufferChangedListener(OnPrebufferChangedListener prebufferChangedListener) {
        getCurrentPlayer().setOnPrebufferChangedListener(prebufferChangedListener);
    }

    public final Player getCurrentPlayer() {
        if (this.state.getPlayRequests().isEmpty() || this.state.getCurIndex() < 0 || this.state.getCurIndex() >= this.state.getPlayRequests().size()) {
            return getHarleyPlayer();
        }
        PlayerSelector playerSelector = this.playerSelector;
        MediaItem mediaItem = this.state.getPlayRequests().get(this.state.getCurIndex()).getMediaItem();
        Intrinsics.checkNotNullExpressionValue(mediaItem, "state.playRequests[state.curIndex].mediaItem");
        return playerSelector.getPlayer(mediaItem);
    }

    public final boolean isMasterCurrentIndexValid() {
        return this.state.getPlayRequests().size() > this.state.getCurIndex();
    }

    public final boolean isMasterPlayRequestTheSameAs(PlayRequest subPlayerPlayRequest) {
        return Intrinsics.areEqual(this.state.getPlayRequests().get(this.state.getCurIndex()), subPlayerPlayRequest);
    }

    public final void lateInitializePlayer(Player player) {
        Intrinsics.checkNotNullParameter(player, "player");
        this.playerSelector.connectPlayer(player);
    }

    /* JADX INFO: compiled from: CompositePlayer.kt */
    @Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b)\u0010*J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0016\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0004J&\u0010\u0012\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0014\u001a\u00020\u0011J\u0006\u0010\u0015\u001a\u00020\u0011R\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\n\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010\u0010\u001a\u00020\u000f8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0010\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0017\u0010%\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b%\u0010\u001b\u001a\u0004\b&\u0010\u001dR\u0017\u0010'\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b'\u0010\u001b\u001a\u0004\b(\u0010\u001d¨\u0006+"}, d2 = {"Lcom/amazon/music/media/playback/player/CompositePlayer$PlayerSelector;", "", "Lcom/amazon/music/media/playback/MediaItem;", "mediaItem", "Lcom/amazon/music/media/playback/player/Player;", "getPlayer", "player", "", "isMediaItemForPlayer", "Lcom/amazon/music/media/playback/player/Player$Callback;", "callback", "Lcom/amazon/music/media/playback/config/PlaybackConfig;", "playbackConfig", "Lcom/amazon/music/media/playback/player/Player$State;", "state", "Lcom/amazon/music/media/playback/impl/AudioFocusManager;", "audioFocusManager", "", "connectPlayers", "connectPlayer", "disconnectPlayers", "close", "Lcom/amazon/music/media/playback/player/CompositePlayer;", "compositePlayer", "Lcom/amazon/music/media/playback/player/CompositePlayer;", "getCompositePlayer", "()Lcom/amazon/music/media/playback/player/CompositePlayer;", "Lcom/amazon/music/media/playback/player/Player$Callback;", "getCallback", "()Lcom/amazon/music/media/playback/player/Player$Callback;", "setCallback", "(Lcom/amazon/music/media/playback/player/Player$Callback;)V", "Lcom/amazon/music/media/playback/impl/AudioFocusManager;", "getAudioFocusManager", "()Lcom/amazon/music/media/playback/impl/AudioFocusManager;", "setAudioFocusManager", "(Lcom/amazon/music/media/playback/impl/AudioFocusManager;)V", "harleyPlayerCallback", "getHarleyPlayerCallback", "primeVideoPlayerCallback", "getPrimeVideoPlayerCallback", "<init>", "(Lcom/amazon/music/media/playback/player/CompositePlayer;)V", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final class PlayerSelector {
        public AudioFocusManager audioFocusManager;
        public Player.Callback callback;
        private final CompositePlayer compositePlayer;
        private final Player.Callback harleyPlayerCallback;
        private final Player.Callback primeVideoPlayerCallback;

        public PlayerSelector(CompositePlayer compositePlayer) {
            Intrinsics.checkNotNullParameter(compositePlayer, "compositePlayer");
            this.compositePlayer = compositePlayer;
            this.harleyPlayerCallback = new Player.Callback() { // from class: com.amazon.music.media.playback.player.CompositePlayer$PlayerSelector$harleyPlayerCallback$1
                @Override // com.amazon.music.media.playback.player.Player.Callback
                public void onPlayStatusChanged(PlayStatus oldStatus, PlayStatus newStatus, PlaybackException exception) {
                    this.this$0.getCompositePlayer().getState().setPlayStatus(newStatus);
                    this.this$0.getCompositePlayer().getCallback().onPlayStatusChanged(oldStatus, newStatus, exception);
                }

                @Override // com.amazon.music.media.playback.player.Player.Callback
                public void onIndexChanged(int oldIndex, int newIndex, ChangeReason changeReason, PlaybackException exception) {
                    int i = newIndex - oldIndex;
                    int currentIndex = this.this$0.getCompositePlayer().getCurrentIndex();
                    int i2 = i + currentIndex;
                    int i3 = i2 >= 0 ? i2 : currentIndex;
                    if (i3 < this.this$0.getCompositePlayer().getState().getPlayRequests().size()) {
                        CompositePlayer.PlayerSelector playerSelector = this.this$0;
                        MediaItem mediaItem = playerSelector.getCompositePlayer().getState().getPlayRequests().get(i3).getMediaItem();
                        Intrinsics.checkNotNullExpressionValue(mediaItem, "compositePlayer.state.pl…itePlayerIndex].mediaItem");
                        if (playerSelector.getPlayer(mediaItem) instanceof HarleyPlayer) {
                            this.this$0.getCompositePlayer().getState().setCurIndex(i3);
                        }
                    }
                    this.this$0.getCompositePlayer().getCallback().onIndexChanged(currentIndex, i2, changeReason, exception);
                }

                @Override // com.amazon.music.media.playback.player.Player.Callback
                public void onMediaItemAlmostFinished() {
                    this.this$0.getCompositePlayer().getCallback().onMediaItemAlmostFinished();
                }

                @Override // com.amazon.music.media.playback.player.Player.Callback
                public void onPlaybackAttributesChanged() {
                    this.this$0.getCompositePlayer().getCallback().onPlaybackAttributesChanged();
                }

                @Override // com.amazon.music.media.playback.player.Player.Callback
                public void onRepeatingMediaItem() {
                    this.this$0.getCompositePlayer().getCallback().onRepeatingMediaItem();
                }

                @Override // com.amazon.music.media.playback.OnMetadataChangedListener
                public void onMetadataChanged(MediaItem mediaItem) {
                    this.this$0.getCompositePlayer().getCallback().onMetadataChanged(mediaItem);
                }

                @Override // com.amazon.music.media.playback.OnMetadataChangedListener
                public void onMediaCollectionInfoChanged(MediaCollectionInfo info) {
                    this.this$0.getCompositePlayer().getCallback().onMediaCollectionInfoChanged(info);
                }

                @Override // com.amazon.music.media.playback.player.Player.Callback
                public void onClearQueue() {
                    this.this$0.getCompositePlayer().getState().getPlayRequests().clear();
                    this.this$0.getCompositePlayer().getState().setCurIndex(0);
                    this.this$0.getCompositePlayer().currentRequest = null;
                }
            };
            this.primeVideoPlayerCallback = new Player.Callback() { // from class: com.amazon.music.media.playback.player.CompositePlayer$PlayerSelector$primeVideoPlayerCallback$1
                @Override // com.amazon.music.media.playback.player.Player.Callback
                public void onPlayStatusChanged(PlayStatus oldStatus, PlayStatus newStatus, PlaybackException exception) {
                    this.this$0.getCompositePlayer().getState().setPlayStatus(newStatus);
                    this.this$0.getCompositePlayer().getCallback().onPlayStatusChanged(oldStatus, newStatus, exception);
                }

                @Override // com.amazon.music.media.playback.player.Player.Callback
                public void onIndexChanged(int oldIndex, int newIndex, ChangeReason changeReason, PlaybackException exception) {
                    int i = newIndex - oldIndex;
                    if (this.this$0.getCompositePlayer().isMasterCurrentIndexValid() && this.this$0.getCompositePlayer().isMasterPlayRequestTheSameAs(Playback.getInstance().getPlaybackConfig().getPrimeVideoPlayer().getCurrentPlayRequest())) {
                        return;
                    }
                    this.this$0.getCompositePlayer().getCallback().onIndexChanged(oldIndex, i + oldIndex, changeReason, exception);
                }

                @Override // com.amazon.music.media.playback.player.Player.Callback
                public void onMediaItemAlmostFinished() {
                    this.this$0.getCompositePlayer().getCallback().onMediaItemAlmostFinished();
                }

                @Override // com.amazon.music.media.playback.player.Player.Callback
                public void onPlaybackAttributesChanged() {
                    this.this$0.getCompositePlayer().getCallback().onPlaybackAttributesChanged();
                }

                @Override // com.amazon.music.media.playback.player.Player.Callback
                public void onRepeatingMediaItem() {
                    this.this$0.getCompositePlayer().getCallback().onRepeatingMediaItem();
                }

                @Override // com.amazon.music.media.playback.OnMetadataChangedListener
                public void onMetadataChanged(MediaItem mediaItem) {
                    this.this$0.getCompositePlayer().getCallback().onMetadataChanged(mediaItem);
                }

                @Override // com.amazon.music.media.playback.OnMetadataChangedListener
                public void onMediaCollectionInfoChanged(MediaCollectionInfo info) {
                    this.this$0.getCompositePlayer().getCallback().onMediaCollectionInfoChanged(info);
                }

                @Override // com.amazon.music.media.playback.player.Player.Callback
                public void onClearQueue() {
                    this.this$0.getCompositePlayer().getState().getPlayRequests().clear();
                    this.this$0.getCompositePlayer().getState().setCurIndex(0);
                    this.this$0.getCompositePlayer().currentRequest = null;
                }
            };
        }

        public final CompositePlayer getCompositePlayer() {
            return this.compositePlayer;
        }

        public final void setCallback(Player.Callback callback) {
            Intrinsics.checkNotNullParameter(callback, "<set-?>");
            this.callback = callback;
        }

        public final AudioFocusManager getAudioFocusManager() {
            AudioFocusManager audioFocusManager = this.audioFocusManager;
            if (audioFocusManager != null) {
                return audioFocusManager;
            }
            Intrinsics.throwUninitializedPropertyAccessException("audioFocusManager");
            return null;
        }

        public final void setAudioFocusManager(AudioFocusManager audioFocusManager) {
            Intrinsics.checkNotNullParameter(audioFocusManager, "<set-?>");
            this.audioFocusManager = audioFocusManager;
        }

        public final Player getPlayer(MediaItem mediaItem) {
            Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
            if (PlayerUtil.INSTANCE.isVideoMediaItem(mediaItem)) {
                Player primeVideoPlayer = Playback.getInstance().getPlaybackConfig().getPrimeVideoPlayer();
                Intrinsics.checkNotNullExpressionValue(primeVideoPlayer, "getInstance().playbackConfig.primeVideoPlayer");
                return primeVideoPlayer;
            }
            Player localPlayer = Playback.getInstance().getPlaybackConfig().getLocalPlayer(null);
            Intrinsics.checkNotNullExpressionValue(localPlayer, "getInstance().playbackConfig.getLocalPlayer(null)");
            return localPlayer;
        }

        public final boolean isMediaItemForPlayer(MediaItem mediaItem, Player player) {
            Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
            Intrinsics.checkNotNullParameter(player, "player");
            return player instanceof PrimeVideoPlayer ? PlayerUtil.INSTANCE.isVideoMediaItem(mediaItem) : (player instanceof HarleyPlayer) && !PlayerUtil.INSTANCE.isVideoMediaItem(mediaItem);
        }

        public final void connectPlayers(Player.Callback callback, PlaybackConfig playbackConfig, Player.State state, AudioFocusManager audioFocusManager) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            Intrinsics.checkNotNullParameter(playbackConfig, "playbackConfig");
            Intrinsics.checkNotNullParameter(state, "state");
            Intrinsics.checkNotNullParameter(audioFocusManager, "audioFocusManager");
            setCallback(callback);
            setAudioFocusManager(audioFocusManager);
            Playback.getInstance().getPlaybackConfig().getLocalPlayer(null).connect(this.harleyPlayerCallback, playbackConfig, new Player.State(), audioFocusManager);
            if (Playback.getInstance().getPlaybackConfig().getPrimeVideoPlayer() instanceof NoOpPlayer) {
                return;
            }
            Playback.getInstance().getPlaybackConfig().getPrimeVideoPlayer().connect(this.primeVideoPlayerCallback, playbackConfig, new Player.State(), audioFocusManager);
        }

        public final void connectPlayer(Player player) {
            Intrinsics.checkNotNullParameter(player, "player");
            if (player instanceof HarleyPlayer) {
                player.connect(this.harleyPlayerCallback, Playback.getInstance().getPlaybackConfig(), new Player.State(), getAudioFocusManager());
            } else if (player instanceof PrimeVideoPlayer) {
                player.connect(this.primeVideoPlayerCallback, Playback.getInstance().getPlaybackConfig(), new Player.State(), getAudioFocusManager());
            }
        }

        public final void disconnectPlayers() {
            Playback.getInstance().getPlaybackConfig().getLocalPlayer(null).disconnect();
            if (Playback.getInstance().getPlaybackConfig().getPrimeVideoPlayer() instanceof NoOpPlayer) {
                return;
            }
            Playback.getInstance().getPlaybackConfig().getPrimeVideoPlayer().disconnect();
        }

        public final void close() {
            Playback.getInstance().getPlaybackConfig().getLocalPlayer(null).close();
            Playback.getInstance().getPlaybackConfig().getPrimeVideoPlayer().close();
        }
    }
}
