package com.amazon.mp3.audioquality.nerdstats.models;

import UpsellInterface.v1_0.PlaybackMode;
import android.media.AudioManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.amazon.mp3.audioquality.nerdstats.models.NerdStatsReducedQualityAlertType;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.KatanaNPSBadgeProvider;
import com.amazon.mp3.playback.PlaybackAudioQualityToAudioQuality;
import com.amazon.mp3.util.Log;
import com.amazon.music.chromecast.alert.ReceiverAlert;
import com.amazon.music.chromecast.alert.UpdateFirmwareAlert;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import java.text.DecimalFormat;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NerdStatsViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 B2\u00020\u0001:\u0004BCDEB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0002J\u0010\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u000fH\u0002J\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u0015H\u0002J\u0010\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u000fH\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0003J\u001e\u0010#\u001a\u0004\u0018\u00010\r2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010%H\u0002J\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\t0(J\u001a\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u00152\u0006\u0010,\u001a\u00020\u000fH\u0002J\b\u0010-\u001a\u00020\u0013H\u0002J$\u0010.\u001a\u00020/2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010%H\u0002J\u0010\u00100\u001a\u0002012\u0006\u0010\u001e\u001a\u00020\u000fH\u0002J\b\u00102\u001a\u00020\rH\u0002J\u0010\u00103\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u00104\u001a\u00020\r2\u0006\u00105\u001a\u000206H\u0002J2\u00107\u001a\u00020%2\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\b\u00108\u001a\u0004\u0018\u00010%H\u0002J\u000e\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<J\u001a\u0010=\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010>\u001a\u0004\u0018\u00010?H\u0002J\"\u0010@\u001a\u00020\u000f2\b\u0010A\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModel;", "Landroidx/lifecycle/ViewModel;", "injectedAccessFactory", "Lcom/amazon/mp3/audioquality/nerdstats/models/InjectedAccessFactory;", "(Lcom/amazon/mp3/audioquality/nerdstats/models/InjectedAccessFactory;)V", "injectedAccess", "Lcom/amazon/mp3/audioquality/nerdstats/models/InjectedAccess;", "nerdStats", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStats;", "qualityMap", "Lcom/amazon/mp3/playback/PlaybackAudioQualityToAudioQuality;", "chromecastReceiverAlertMessage", "", "enableSpatialToggle", "", "representations", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModel$Representations;", "nerdStatsState", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsState;", "getAudioBadge", "Lcom/amazon/mp3/playback/KatanaNPSBadgeProvider$AudioQualityBadge;", "maxQuality", "Lcom/amazon/music/media/playback/PlaybackAudioQuality;", "currentPlaybackQuality", "getAudioQualityBadge", "forceStereoBadge", "getAudioQualityString", "audioBadge", "getCurrentPlaybackPropertyValue", "isSpatialToggleComplete", "getExternalDeviceType", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModel$ExternalOutput;", "audioManager", "Landroid/media/AudioManager;", "getInactiveQualityDescription", "stereoStats", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsSignalFlowStats;", "spatialStats", "getLiveData", "Landroidx/lifecycle/LiveData;", "getNerdStatsAlert", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsReducedQualityAlertType;", "audioQualityBadge", "isActive", "getNerdStatsState", "getNerdStatsSummary", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsSummary;", "getOutputDataModel", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsOutputQuality;", "getTrackQualityValue", "isSpatialActive", "makeQualityDetailsString", "playbackAttributes", "Lcom/amazon/music/media/playback/PlaybackAttributes;", "makeSignalFlowStats", "priorStats", "refresh", "", "reason", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModel$RefreshReason;", "selectRepresentations", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "shouldSuppressRefresh", "priorNerdStats", "Companion", "ExternalOutput", "RefreshReason", "Representations", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NerdStatsViewModel extends ViewModel {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = NerdStatsViewModel.class.getSimpleName();
    private InjectedAccess injectedAccess;
    private final InjectedAccessFactory injectedAccessFactory;
    private final MutableLiveData<NerdStats> nerdStats;
    private final PlaybackAudioQualityToAudioQuality qualityMap;

    /* JADX INFO: compiled from: NerdStatsViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModel$ExternalOutput;", "", "(Ljava/lang/String;I)V", "WIRED_HEADPHONES", "EXTERNAL_DEVICE", "BLUETOOTH", PlaybackMode.NONE, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private enum ExternalOutput {
        WIRED_HEADPHONES,
        EXTERNAL_DEVICE,
        BLUETOOTH,
        NONE
    }

    /* JADX INFO: compiled from: NerdStatsViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModel$RefreshReason;", "", "(Ljava/lang/String;I)V", "Init", "PlayerChange", "PlayStateChange", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum RefreshReason {
        Init,
        PlayerChange,
        PlayStateChange
    }

    /* JADX INFO: compiled from: NerdStatsViewModel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[NerdStatsDataModels$NerdStatsState.values().length];
            iArr[NerdStatsDataModels$NerdStatsState.AlexaCast.ordinal()] = 1;
            iArr[NerdStatsDataModels$NerdStatsState.Offline.ordinal()] = 2;
            iArr[NerdStatsDataModels$NerdStatsState.Chromecast.ordinal()] = 3;
            iArr[NerdStatsDataModels$NerdStatsState.Normal.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[KatanaNPSBadgeProvider.AudioQualityBadge.values().length];
            iArr2[KatanaNPSBadgeProvider.AudioQualityBadge.HD.ordinal()] = 1;
            iArr2[KatanaNPSBadgeProvider.AudioQualityBadge.HD_ALERT.ordinal()] = 2;
            iArr2[KatanaNPSBadgeProvider.AudioQualityBadge.UHD.ordinal()] = 3;
            iArr2[KatanaNPSBadgeProvider.AudioQualityBadge.UHD_ALERT.ordinal()] = 4;
            iArr2[KatanaNPSBadgeProvider.AudioQualityBadge.DOLBY_ATMOS.ordinal()] = 5;
            iArr2[KatanaNPSBadgeProvider.AudioQualityBadge.DOLBY_ATMOS_ALERT.ordinal()] = 6;
            iArr2[KatanaNPSBadgeProvider.AudioQualityBadge.SONY_360.ordinal()] = 7;
            iArr2[KatanaNPSBadgeProvider.AudioQualityBadge.SONY_360_ALERT.ordinal()] = 8;
            iArr2[KatanaNPSBadgeProvider.AudioQualityBadge.THREE_DIMENSIONAL.ordinal()] = 9;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[ExternalOutput.values().length];
            iArr3[ExternalOutput.WIRED_HEADPHONES.ordinal()] = 1;
            iArr3[ExternalOutput.BLUETOOTH.ordinal()] = 2;
            iArr3[ExternalOutput.EXTERNAL_DEVICE.ordinal()] = 3;
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    public NerdStatsViewModel(InjectedAccessFactory injectedAccessFactory) {
        Intrinsics.checkNotNullParameter(injectedAccessFactory, "injectedAccessFactory");
        this.injectedAccessFactory = injectedAccessFactory;
        this.nerdStats = new MutableLiveData<>();
        this.qualityMap = new PlaybackAudioQualityToAudioQuality();
        refresh(RefreshReason.Init);
    }

    public final LiveData<NerdStats> getLiveData() {
        return this.nerdStats;
    }

    public final void refresh(RefreshReason reason) {
        NerdStats nerdStats;
        NerdStatsSignalFlowStats nerdStatsSignalFlowStatsMakeSignalFlowStats;
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.injectedAccess = this.injectedAccessFactory.getInjectedAccess();
        NerdStatsDataModels$NerdStatsState nerdStatsState = getNerdStatsState();
        InjectedAccess injectedAccess = this.injectedAccess;
        NerdStatsSignalFlowStats nerdStatsSignalFlowStatsMakeSignalFlowStats2 = null;
        if (injectedAccess == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess = null;
        }
        MediaItem currentMediaItem = injectedAccess.getCurrentMediaItem();
        Representations representationsSelectRepresentations = selectRepresentations(nerdStatsState, currentMediaItem);
        boolean zIsSpatialActive = isSpatialActive(representationsSelectRepresentations);
        boolean zIsSpatialToggleComplete = isSpatialToggleComplete(representationsSelectRepresentations, nerdStatsState);
        String asin = MediaItemHelper.getAsin(currentMediaItem);
        NerdStats value = this.nerdStats.getValue();
        if (value == null) {
            nerdStats = null;
        } else {
            if (reason == RefreshReason.PlayerChange || !Intrinsics.areEqual(value.getMediaItemAsin(), asin)) {
                value = null;
            }
            nerdStats = value;
        }
        if (shouldSuppressRefresh(nerdStats, representationsSelectRepresentations, zIsSpatialToggleComplete)) {
            return;
        }
        if (representationsSelectRepresentations.getHasSpatial()) {
            nerdStatsSignalFlowStatsMakeSignalFlowStats = makeSignalFlowStats(zIsSpatialActive, zIsSpatialToggleComplete, false, nerdStatsState, nerdStats == null ? null : nerdStats.getSpatialStats());
        } else {
            nerdStatsSignalFlowStatsMakeSignalFlowStats = null;
        }
        if (representationsSelectRepresentations.getHasStereo()) {
            nerdStatsSignalFlowStatsMakeSignalFlowStats2 = makeSignalFlowStats(!zIsSpatialActive, zIsSpatialToggleComplete, representationsSelectRepresentations.getHasSpatial(), nerdStatsState, nerdStats == null ? null : nerdStats.getStereoStats());
        }
        this.nerdStats.setValue(new NerdStats(getNerdStatsSummary(nerdStatsState, nerdStatsSignalFlowStatsMakeSignalFlowStats2, nerdStatsSignalFlowStatsMakeSignalFlowStats), nerdStatsSignalFlowStatsMakeSignalFlowStats, nerdStatsSignalFlowStatsMakeSignalFlowStats2, enableSpatialToggle(representationsSelectRepresentations, nerdStatsState), zIsSpatialToggleComplete, asin));
    }

    /* JADX INFO: compiled from: NerdStatsViewModel.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModel$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "isAudioQualityBadgeHigherThanCurrentPlayback", "", "currentPlaybackQuality", "Lcom/amazon/music/media/playback/PlaybackAudioQuality;", "audioQualityBadge", "Lcom/amazon/mp3/playback/KatanaNPSBadgeProvider$AudioQualityBadge;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: NerdStatsViewModel.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[KatanaNPSBadgeProvider.AudioQualityBadge.values().length];
                iArr[KatanaNPSBadgeProvider.AudioQualityBadge.HD.ordinal()] = 1;
                iArr[KatanaNPSBadgeProvider.AudioQualityBadge.HD_ALERT.ordinal()] = 2;
                iArr[KatanaNPSBadgeProvider.AudioQualityBadge.UHD.ordinal()] = 3;
                iArr[KatanaNPSBadgeProvider.AudioQualityBadge.UHD_ALERT.ordinal()] = 4;
                iArr[KatanaNPSBadgeProvider.AudioQualityBadge.DOLBY_ATMOS.ordinal()] = 5;
                iArr[KatanaNPSBadgeProvider.AudioQualityBadge.DOLBY_ATMOS_ALERT.ordinal()] = 6;
                iArr[KatanaNPSBadgeProvider.AudioQualityBadge.SONY_360.ordinal()] = 7;
                iArr[KatanaNPSBadgeProvider.AudioQualityBadge.SONY_360_ALERT.ordinal()] = 8;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isAudioQualityBadgeHigherThanCurrentPlayback(PlaybackAudioQuality currentPlaybackQuality, KatanaNPSBadgeProvider.AudioQualityBadge audioQualityBadge) {
            PlaybackAudioQualityToAudioQuality playbackAudioQualityToAudioQuality = new PlaybackAudioQualityToAudioQuality();
            switch (audioQualityBadge == null ? -1 : WhenMappings.$EnumSwitchMapping$0[audioQualityBadge.ordinal()]) {
                case 1:
                case 2:
                    return playbackAudioQualityToAudioQuality.isStandard(currentPlaybackQuality);
                case 3:
                case 4:
                    return playbackAudioQualityToAudioQuality.isStandard(currentPlaybackQuality) || playbackAudioQualityToAudioQuality.isHD(currentPlaybackQuality);
                case 5:
                case 6:
                case 7:
                case 8:
                    return !playbackAudioQualityToAudioQuality.isAtmosOr360(currentPlaybackQuality);
                default:
                    return false;
            }
        }
    }

    private final boolean enableSpatialToggle(Representations representations, NerdStatsDataModels$NerdStatsState nerdStatsState) {
        int i = WhenMappings.$EnumSwitchMapping$0[nerdStatsState.ordinal()];
        if (i == 1 || i == 2) {
            return false;
        }
        if (i != 3) {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            if (!representations.getHasSpatial() || !representations.getHasStereo()) {
                return false;
            }
        } else {
            if (!representations.getHasSpatial() || !representations.getHasStereo()) {
                return false;
            }
            InjectedAccess injectedAccess = this.injectedAccess;
            if (injectedAccess == null) {
                Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                injectedAccess = null;
            }
            if (!injectedAccess.enableChromecastSpatialToggle()) {
                return false;
            }
        }
        return true;
    }

    private final boolean shouldSuppressRefresh(NerdStats priorNerdStats, Representations representations, boolean isSpatialToggleComplete) {
        if (priorNerdStats == null) {
            return false;
        }
        InjectedAccess injectedAccess = this.injectedAccess;
        if (injectedAccess == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess = null;
        }
        if (injectedAccess.getCurrentPlaybackAttributes().getPlaybackAudioQuality() != PlaybackAudioQuality.UNKNOWN) {
            return false;
        }
        if (!isSpatialToggleComplete || priorNerdStats.getIsSpatialToggleComplete()) {
            return (priorNerdStats.getSpatialStats() != null) == representations.getHasSpatial() && (priorNerdStats.getStereoStats() != null) == representations.getHasStereo();
        }
        return false;
    }

    private final NerdStatsDataModels$NerdStatsState getNerdStatsState() {
        InjectedAccess injectedAccess = this.injectedAccess;
        InjectedAccess injectedAccess2 = null;
        if (injectedAccess == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess = null;
        }
        if (injectedAccess.isCastingToChromecast()) {
            return NerdStatsDataModels$NerdStatsState.Chromecast;
        }
        InjectedAccess injectedAccess3 = this.injectedAccess;
        if (injectedAccess3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess3 = null;
        }
        if (injectedAccess3.isCasting()) {
            return NerdStatsDataModels$NerdStatsState.AlexaCast;
        }
        InjectedAccess injectedAccess4 = this.injectedAccess;
        if (injectedAccess4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess4 = null;
        }
        if (!injectedAccess4.hasAnyInternetConnection()) {
            return NerdStatsDataModels$NerdStatsState.Offline;
        }
        InjectedAccess injectedAccess5 = this.injectedAccess;
        if (injectedAccess5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess5 = null;
        }
        if (injectedAccess5.isPrioritizeDownloadsEnabled()) {
            InjectedAccess injectedAccess6 = this.injectedAccess;
            if (injectedAccess6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            } else {
                injectedAccess2 = injectedAccess6;
            }
            if (injectedAccess2.isCurrentMediaItemDownloaded()) {
                return NerdStatsDataModels$NerdStatsState.Offline;
            }
        }
        return NerdStatsDataModels$NerdStatsState.Normal;
    }

    /* JADX WARN: Removed duplicated region for block: B:83:0x0132 A[PHI: r11
  0x0132: PHI (r11v9 java.lang.String) = (r11v7 java.lang.String), (r11v12 java.lang.String), (r11v17 java.lang.String) binds: [B:102:0x0172, B:66:0x00ff, B:81:0x012f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.mp3.audioquality.nerdstats.models.NerdStatsSummary getNerdStatsSummary(com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsState r11, com.amazon.mp3.audioquality.nerdstats.models.NerdStatsSignalFlowStats r12, com.amazon.mp3.audioquality.nerdstats.models.NerdStatsSignalFlowStats r13) {
        /*
            Method dump skipped, instruction units count: 392
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel.getNerdStatsSummary(com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsState, com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsSignalFlowStats, com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsSignalFlowStats):com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsSummary");
    }

    private final String chromecastReceiverAlertMessage() {
        KatanaNPSBadgeProvider.AudioQualityBadge audioQualityBadgeValueOf;
        InjectedAccess injectedAccess = this.injectedAccess;
        InjectedAccess injectedAccess2 = null;
        if (injectedAccess == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess = null;
        }
        ReceiverAlert<?> receiverAlertChromecastReceiverAlert = injectedAccess.chromecastReceiverAlert();
        if (!(receiverAlertChromecastReceiverAlert instanceof UpdateFirmwareAlert)) {
            return null;
        }
        UpdateFirmwareAlert updateFirmwareAlert = (UpdateFirmwareAlert) receiverAlertChromecastReceiverAlert;
        String attribute = updateFirmwareAlert.getAttribute(UpdateFirmwareAlert.Attribute.UNUSABLE_AUDIO_QUALITY);
        if (attribute == null) {
            audioQualityBadgeValueOf = null;
        } else {
            try {
                audioQualityBadgeValueOf = KatanaNPSBadgeProvider.AudioQualityBadge.valueOf(attribute);
            } catch (IllegalArgumentException unused) {
                Log.error(TAG, Intrinsics.stringPlus("Chromecast firmware update alert ignored for AudioQualityBadge.", attribute));
                audioQualityBadgeValueOf = null;
            }
        }
        if (audioQualityBadgeValueOf == null) {
            return null;
        }
        String audioQualityString = getAudioQualityString(audioQualityBadgeValueOf);
        String attribute2 = updateFirmwareAlert.getAttribute(UpdateFirmwareAlert.Attribute.MODEL_NAME);
        if (attribute2 == null) {
            InjectedAccess injectedAccess3 = this.injectedAccess;
            if (injectedAccess3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                injectedAccess3 = null;
            }
            attribute2 = injectedAccess3.getCastingDeviceName();
            if (attribute2 == null) {
                InjectedAccess injectedAccess4 = this.injectedAccess;
                if (injectedAccess4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                    injectedAccess4 = null;
                }
                attribute2 = injectedAccess4.getString(R.string.dmusic_nerd_stats_unknown_output);
            }
        }
        Intrinsics.checkNotNullExpressionValue(attribute2, "receiverAlert.getAttribu…erd_stats_unknown_output)");
        InjectedAccess injectedAccess5 = this.injectedAccess;
        if (injectedAccess5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
        } else {
            injectedAccess2 = injectedAccess5;
        }
        return injectedAccess2.getString(R.string.dmusic_nerd_stats_chromecast_firmware_message, audioQualityString, attribute2);
    }

    private final String getInactiveQualityDescription(NerdStatsSignalFlowStats stereoStats, NerdStatsSignalFlowStats spatialStats) {
        KatanaNPSBadgeProvider.AudioQualityBadge audioQualityBadge;
        if (spatialStats == null || stereoStats == null) {
            return null;
        }
        if (spatialStats.getSignalFlowState() == NerdStatsDataModels$SignalFlowState.Inactive) {
            audioQualityBadge = spatialStats.getTrackBadging().getAudioQualityBadge();
        } else {
            audioQualityBadge = stereoStats.getTrackBadging().getAudioQualityBadge();
        }
        return getAudioQualityString(audioQualityBadge);
    }

    private final String getAudioQualityString(KatanaNPSBadgeProvider.AudioQualityBadge audioBadge) {
        InjectedAccess injectedAccess = null;
        switch (WhenMappings.$EnumSwitchMapping$1[audioBadge.ordinal()]) {
            case 1:
            case 2:
                InjectedAccess injectedAccess2 = this.injectedAccess;
                if (injectedAccess2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                } else {
                    injectedAccess = injectedAccess2;
                }
                return injectedAccess.getString(R.string.dmusic_nerd_stats_hd_quality);
            case 3:
            case 4:
                InjectedAccess injectedAccess3 = this.injectedAccess;
                if (injectedAccess3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                } else {
                    injectedAccess = injectedAccess3;
                }
                return injectedAccess.getString(R.string.dmusic_nerd_stats_ultra_hd_quality);
            case 5:
            case 6:
                InjectedAccess injectedAccess4 = this.injectedAccess;
                if (injectedAccess4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                } else {
                    injectedAccess = injectedAccess4;
                }
                return injectedAccess.getString(R.string.dmusic_nerd_stats_dolby_atmos_quality);
            case 7:
            case 8:
                InjectedAccess injectedAccess5 = this.injectedAccess;
                if (injectedAccess5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                } else {
                    injectedAccess = injectedAccess5;
                }
                return injectedAccess.getString(R.string.dmusic_nerd_stats_360_quality);
            default:
                InjectedAccess injectedAccess6 = this.injectedAccess;
                if (injectedAccess6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                } else {
                    injectedAccess = injectedAccess6;
                }
                return injectedAccess.getString(R.string.dmusic_nerd_stats_standard_quality);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:156:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x02b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.mp3.audioquality.nerdstats.models.NerdStatsSignalFlowStats makeSignalFlowStats(boolean r20, boolean r21, boolean r22, com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsState r23, com.amazon.mp3.audioquality.nerdstats.models.NerdStatsSignalFlowStats r24) {
        /*
            Method dump skipped, instruction units count: 724
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel.makeSignalFlowStats(boolean, boolean, boolean, com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsState, com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsSignalFlowStats):com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsSignalFlowStats");
    }

    private final NerdStatsOutputQuality getOutputDataModel(boolean isSpatialToggleComplete) {
        String currentPlaybackPropertyValue;
        int i;
        String string;
        int i2;
        String castingDeviceName;
        InjectedAccess injectedAccess = this.injectedAccess;
        InjectedAccess injectedAccess2 = null;
        if (injectedAccess == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess = null;
        }
        AudioManager audioManager = injectedAccess.getAudioManager();
        InjectedAccess injectedAccess3 = this.injectedAccess;
        if (injectedAccess3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess3 = null;
        }
        String string2 = injectedAccess3.getString(R.string.dmusic_nerd_stats_builtin_speaker_output);
        int i3 = R.drawable.ic_phone;
        InjectedAccess injectedAccess4 = this.injectedAccess;
        if (injectedAccess4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess4 = null;
        }
        PlaybackAudioQuality playbackAudioQuality = injectedAccess4.getCurrentPlaybackAttributes().getPlaybackAudioQuality();
        Intrinsics.checkNotNullExpressionValue(playbackAudioQuality, "injectedAccess.getCurren…es().playbackAudioQuality");
        if (this.qualityMap.isRa360(playbackAudioQuality)) {
            InjectedAccess injectedAccess5 = this.injectedAccess;
            if (injectedAccess5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                injectedAccess5 = null;
            }
            currentPlaybackPropertyValue = injectedAccess5.getString(R.string.dmusic_nerd_stats_360_quality);
        } else if (this.qualityMap.isAtmos(playbackAudioQuality)) {
            InjectedAccess injectedAccess6 = this.injectedAccess;
            if (injectedAccess6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                injectedAccess6 = null;
            }
            currentPlaybackPropertyValue = injectedAccess6.getString(R.string.dmusic_nerd_stats_dolby_atmos_quality);
        } else {
            currentPlaybackPropertyValue = getCurrentPlaybackPropertyValue(isSpatialToggleComplete);
        }
        InjectedAccess injectedAccess7 = this.injectedAccess;
        if (injectedAccess7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess7 = null;
        }
        if (injectedAccess7.isCasting()) {
            InjectedAccess injectedAccess8 = this.injectedAccess;
            if (injectedAccess8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                injectedAccess8 = null;
            }
            castingDeviceName = injectedAccess8.getCastingDeviceName();
            if (castingDeviceName == null) {
                InjectedAccess injectedAccess9 = this.injectedAccess;
                if (injectedAccess9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                    injectedAccess9 = null;
                }
                castingDeviceName = injectedAccess9.getString(R.string.dmusic_nerd_stats_unknown_output);
            }
            InjectedAccess injectedAccess10 = this.injectedAccess;
            if (injectedAccess10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            } else {
                injectedAccess2 = injectedAccess10;
            }
            i2 = injectedAccess2.isCastingToAlexa() ? R.drawable.ic_navigation_alexa_white : R.drawable.ic_cast_connected_24dp;
        } else {
            int i4 = WhenMappings.$EnumSwitchMapping$2[getExternalDeviceType(audioManager).ordinal()];
            if (i4 == 1) {
                i = R.drawable.ic_headphones;
                InjectedAccess injectedAccess11 = this.injectedAccess;
                if (injectedAccess11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                } else {
                    injectedAccess2 = injectedAccess11;
                }
                string = injectedAccess2.getString(R.string.dmusic_nerd_stats_wired_headphones_output);
            } else if (i4 != 2) {
                if (i4 == 3) {
                    i3 = R.drawable.ic_other_song;
                    InjectedAccess injectedAccess12 = this.injectedAccess;
                    if (injectedAccess12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                    } else {
                        injectedAccess2 = injectedAccess12;
                    }
                    string2 = injectedAccess2.getString(R.string.dmusic_nerd_stats_external_device);
                }
                castingDeviceName = string2;
                i2 = i3;
            } else {
                i = R.drawable.ic_bluetooth;
                InjectedAccess injectedAccess13 = this.injectedAccess;
                if (injectedAccess13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                } else {
                    injectedAccess2 = injectedAccess13;
                }
                string = injectedAccess2.getString(R.string.dmusic_nerd_stats_bluetooth_device_output);
            }
            String str = string;
            i2 = i;
            castingDeviceName = str;
        }
        return new NerdStatsOutputQuality(castingDeviceName, currentPlaybackPropertyValue, i2);
    }

    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    @androidx.annotation.RequiresApi(23)
    private final com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel.ExternalOutput getExternalDeviceType(android.media.AudioManager r5) {
        /*
            r4 = this;
            r0 = 2
            android.media.AudioDeviceInfo[] r5 = r5.getDevices(r0)
            java.lang.String r0 = "devices"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
            int r0 = r5.length
            r1 = 0
        Lc:
            if (r1 >= r0) goto L30
            r2 = r5[r1]
            int r1 = r1 + 1
            int r2 = r2.getType()
            r3 = 3
            if (r2 == r3) goto L2d
            r3 = 4
            if (r2 == r3) goto L2d
            r3 = 22
            if (r2 == r3) goto L2d
            switch(r2) {
                case 6: goto L2a;
                case 7: goto L27;
                case 8: goto L27;
                case 9: goto L2a;
                default: goto L23;
            }
        L23:
            switch(r2) {
                case 11: goto L2a;
                case 12: goto L2a;
                case 13: goto L2a;
                default: goto L26;
            }
        L26:
            goto Lc
        L27:
            com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel$ExternalOutput r5 = com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel.ExternalOutput.BLUETOOTH
            return r5
        L2a:
            com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel$ExternalOutput r5 = com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel.ExternalOutput.EXTERNAL_DEVICE
            return r5
        L2d:
            com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel$ExternalOutput r5 = com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel.ExternalOutput.WIRED_HEADPHONES
            return r5
        L30:
            com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel$ExternalOutput r5 = com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel.ExternalOutput.NONE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel.getExternalDeviceType(android.media.AudioManager):com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel$ExternalOutput");
    }

    private final NerdStatsReducedQualityAlertType getNerdStatsAlert(KatanaNPSBadgeProvider.AudioQualityBadge audioQualityBadge, boolean isActive) {
        InjectedAccess injectedAccess = this.injectedAccess;
        InjectedAccess injectedAccess2 = null;
        if (injectedAccess == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess = null;
        }
        PlaybackAudioQuality playbackAudioQuality = injectedAccess.getCurrentPlaybackAttributes().getPlaybackAudioQuality();
        Intrinsics.checkNotNullExpressionValue(playbackAudioQuality, "injectedAccess.getCurren…es().playbackAudioQuality");
        if (this.qualityMap.isAtmosOr360(playbackAudioQuality) || this.qualityMap.isUnknown(playbackAudioQuality) || !INSTANCE.isAudioQualityBadgeHigherThanCurrentPlayback(playbackAudioQuality, audioQualityBadge) || !isActive) {
            return new NerdStatsReducedQualityAlertType.None(null, 1, null);
        }
        NerdStatsReducedQualityAlertType.Companion companion = NerdStatsReducedQualityAlertType.INSTANCE;
        InjectedAccess injectedAccess3 = this.injectedAccess;
        if (injectedAccess3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
        } else {
            injectedAccess2 = injectedAccess3;
        }
        return companion.getQualityAlert(injectedAccess2, this.qualityMap);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.mp3.playback.KatanaNPSBadgeProvider.AudioQualityBadge getAudioQualityBadge(boolean r4) {
        /*
            r3 = this;
            com.amazon.mp3.audioquality.nerdstats.models.InjectedAccess r0 = r3.injectedAccess
            java.lang.String r1 = "injectedAccess"
            r2 = 0
            if (r0 != 0) goto Lb
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        Lb:
            com.amazon.music.media.playback.MediaItem r0 = r0.getCurrentMediaItem()
            if (r0 != 0) goto L13
        L11:
            r4 = r2
            goto L4a
        L13:
            java.util.List r0 = r0.getContentEncodings()
            if (r0 != 0) goto L1a
            goto L11
        L1a:
            if (r4 == 0) goto L35
            com.amazon.music.contentEncoding.ContentEncoding r4 = com.amazon.music.contentEncoding.ContentEncoding.UHD
            boolean r4 = r0.contains(r4)
            if (r4 == 0) goto L27
            com.amazon.mp3.playback.KatanaNPSBadgeProvider$AudioQualityBadge r4 = com.amazon.mp3.playback.KatanaNPSBadgeProvider.AudioQualityBadge.UHD
            goto L4a
        L27:
            com.amazon.music.contentEncoding.ContentEncoding r4 = com.amazon.music.contentEncoding.ContentEncoding.HD
            boolean r4 = r0.contains(r4)
            if (r4 == 0) goto L32
            com.amazon.mp3.playback.KatanaNPSBadgeProvider$AudioQualityBadge r4 = com.amazon.mp3.playback.KatanaNPSBadgeProvider.AudioQualityBadge.HD
            goto L4a
        L32:
            com.amazon.mp3.playback.KatanaNPSBadgeProvider$AudioQualityBadge r4 = com.amazon.mp3.playback.KatanaNPSBadgeProvider.AudioQualityBadge.STANDARD
            goto L4a
        L35:
            com.amazon.music.contentEncoding.ContentEncoding r4 = com.amazon.music.contentEncoding.ContentEncoding.DOLBY_ATMOS
            boolean r4 = r0.contains(r4)
            if (r4 == 0) goto L40
            com.amazon.mp3.playback.KatanaNPSBadgeProvider$AudioQualityBadge r4 = com.amazon.mp3.playback.KatanaNPSBadgeProvider.AudioQualityBadge.DOLBY_ATMOS
            goto L4a
        L40:
            com.amazon.music.contentEncoding.ContentEncoding r4 = com.amazon.music.contentEncoding.ContentEncoding.SONY_360
            boolean r4 = r0.contains(r4)
            if (r4 == 0) goto L11
            com.amazon.mp3.playback.KatanaNPSBadgeProvider$AudioQualityBadge r4 = com.amazon.mp3.playback.KatanaNPSBadgeProvider.AudioQualityBadge.SONY_360
        L4a:
            if (r4 != 0) goto L72
            com.amazon.mp3.audioquality.nerdstats.models.InjectedAccess r4 = r3.injectedAccess
            if (r4 != 0) goto L54
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r4 = r2
        L54:
            com.amazon.music.media.playback.PlaybackAudioQuality r4 = r4.getMaxPlaybackQuality()
            com.amazon.mp3.audioquality.nerdstats.models.InjectedAccess r0 = r3.injectedAccess
            if (r0 != 0) goto L60
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L61
        L60:
            r2 = r0
        L61:
            com.amazon.music.media.playback.PlaybackAttributes r0 = r2.getCurrentPlaybackAttributes()
            com.amazon.music.media.playback.PlaybackAudioQuality r0 = r0.getPlaybackAudioQuality()
            java.lang.String r1 = "injectedAccess.getCurren…es().playbackAudioQuality"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            com.amazon.mp3.playback.KatanaNPSBadgeProvider$AudioQualityBadge r4 = r3.getAudioBadge(r4, r0)
        L72:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel.getAudioQualityBadge(boolean):com.amazon.mp3.playback.KatanaNPSBadgeProvider$AudioQualityBadge");
    }

    private final KatanaNPSBadgeProvider.AudioQualityBadge getAudioBadge(PlaybackAudioQuality maxQuality, PlaybackAudioQuality currentPlaybackQuality) {
        if (this.qualityMap.isHD(maxQuality)) {
            if (!this.qualityMap.isHD(currentPlaybackQuality)) {
                return KatanaNPSBadgeProvider.AudioQualityBadge.HD_ALERT;
            }
            return KatanaNPSBadgeProvider.AudioQualityBadge.HD;
        }
        if (this.qualityMap.isUHD(maxQuality)) {
            if (!this.qualityMap.isUHD(currentPlaybackQuality)) {
                return KatanaNPSBadgeProvider.AudioQualityBadge.UHD_ALERT;
            }
            return KatanaNPSBadgeProvider.AudioQualityBadge.UHD;
        }
        if (this.qualityMap.isAtmos(maxQuality)) {
            if (!this.qualityMap.isAtmos(currentPlaybackQuality)) {
                return KatanaNPSBadgeProvider.AudioQualityBadge.DOLBY_ATMOS_ALERT;
            }
            return KatanaNPSBadgeProvider.AudioQualityBadge.DOLBY_ATMOS;
        }
        if (this.qualityMap.isRa360(maxQuality)) {
            if (!this.qualityMap.isRa360(currentPlaybackQuality)) {
                return KatanaNPSBadgeProvider.AudioQualityBadge.SONY_360_ALERT;
            }
            return KatanaNPSBadgeProvider.AudioQualityBadge.SONY_360;
        }
        return KatanaNPSBadgeProvider.AudioQualityBadge.STANDARD;
    }

    private final String getTrackQualityValue() {
        InjectedAccess injectedAccess = this.injectedAccess;
        if (injectedAccess == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess = null;
        }
        return makeQualityDetailsString(injectedAccess.getBestAvailablePlaybackAttributes());
    }

    private final String getCurrentPlaybackPropertyValue(boolean isSpatialToggleComplete) {
        InjectedAccess injectedAccess = this.injectedAccess;
        InjectedAccess injectedAccess2 = null;
        if (injectedAccess == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess = null;
        }
        if (injectedAccess.isCastingToAlexa() || !isSpatialToggleComplete) {
            return "";
        }
        InjectedAccess injectedAccess3 = this.injectedAccess;
        if (injectedAccess3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
        } else {
            injectedAccess2 = injectedAccess3;
        }
        return makeQualityDetailsString(injectedAccess2.getCurrentPlaybackAttributes());
    }

    private final String makeQualityDetailsString(PlaybackAttributes playbackAttributes) {
        PlaybackAudioQuality playbackAudioQuality = playbackAttributes.getPlaybackAudioQuality();
        Intrinsics.checkNotNullExpressionValue(playbackAudioQuality, "playbackAttributes.playbackAudioQuality");
        InjectedAccess injectedAccess = this.injectedAccess;
        InjectedAccess injectedAccess2 = null;
        if (injectedAccess == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess = null;
        }
        DecimalFormat decimalFormat = new DecimalFormat(injectedAccess.getString(R.string.dmusic_nerd_stats_decimal_pattern));
        if (this.qualityMap.isKatana(playbackAudioQuality) || this.qualityMap.isAtmosOr360(playbackAudioQuality)) {
            String strValueOf = playbackAttributes.getBitDepth() != -1 ? String.valueOf(playbackAttributes.getBitDepth()) : null;
            String str = playbackAttributes.getSampleRate() != -1 ? decimalFormat.format(Float.valueOf(playbackAttributes.getSampleRate() / 1000)) : null;
            if (strValueOf != null && str != null) {
                InjectedAccess injectedAccess3 = this.injectedAccess;
                if (injectedAccess3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                    injectedAccess3 = null;
                }
                String string = injectedAccess3.getString(R.string.dmusic_audio_stats_bitdepth_suffix);
                InjectedAccess injectedAccess4 = this.injectedAccess;
                if (injectedAccess4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                } else {
                    injectedAccess2 = injectedAccess4;
                }
                return ((Object) strValueOf) + string + " / " + ((Object) str) + ' ' + injectedAccess2.getString(R.string.dmusic_audio_stats_frequency_suffix);
            }
        } else if (this.qualityMap.isStandard(playbackAudioQuality)) {
            InjectedAccess injectedAccess5 = this.injectedAccess;
            if (injectedAccess5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            } else {
                injectedAccess2 = injectedAccess5;
            }
            return injectedAccess2.getString(R.string.dmusic_nps_audio_quality_badge_standard_text);
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean isSpatialToggleComplete(com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel.Representations r5, com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsState r6) {
        /*
            r4 = this;
            boolean r0 = r5.getHasSpatial()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L10
            boolean r5 = r5.getHasStereo()
            if (r5 == 0) goto L10
            r5 = r2
            goto L11
        L10:
            r5 = r1
        L11:
            if (r5 == 0) goto L61
            com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsState r5 = com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsState.Offline
            if (r6 != r5) goto L18
            goto L61
        L18:
            com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsState r5 = com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsState.Chromecast
            r0 = 0
            java.lang.String r3 = "injectedAccess"
            if (r6 != r5) goto L2e
            com.amazon.mp3.audioquality.nerdstats.models.InjectedAccess r5 = r4.injectedAccess
            if (r5 != 0) goto L27
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r5 = r0
        L27:
            boolean r5 = r5.enableChromecastSpatialToggle()
            if (r5 != 0) goto L2e
            goto L61
        L2e:
            com.amazon.mp3.audioquality.nerdstats.models.InjectedAccess r5 = r4.injectedAccess
            if (r5 != 0) goto L36
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r5 = r0
        L36:
            com.amazon.music.media.playback.PlaybackAttributes r5 = r5.getCurrentPlaybackAttributes()
            com.amazon.music.media.playback.PlaybackAudioQuality r5 = r5.getPlaybackAudioQuality()
            java.lang.String r6 = "injectedAccess.getCurren…es().playbackAudioQuality"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            com.amazon.mp3.playback.PlaybackAudioQualityToAudioQuality r6 = r4.qualityMap
            boolean r6 = r6.isUnknown(r5)
            if (r6 == 0) goto L4c
            goto L61
        L4c:
            com.amazon.mp3.audioquality.nerdstats.models.InjectedAccess r6 = r4.injectedAccess
            if (r6 != 0) goto L54
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L55
        L54:
            r0 = r6
        L55:
            boolean r6 = r0.isSpatialModeSettingEnabled()
            com.amazon.mp3.playback.PlaybackAudioQualityToAudioQuality r0 = r4.qualityMap
            boolean r5 = r0.isAtmosOr360(r5)
            if (r6 != r5) goto L62
        L61:
            r1 = r2
        L62:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel.isSpatialToggleComplete(com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel$Representations, com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsState):boolean");
    }

    /* JADX INFO: compiled from: NerdStatsViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModel$Representations;", "", "", "toString", "", "hashCode", "other", "", "equals", "hasSpatial", "Z", "getHasSpatial", "()Z", "hasStereo", "getHasStereo", "<init>", "(ZZ)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    private static final /* data */ class Representations {
        private final boolean hasSpatial;
        private final boolean hasStereo;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Representations)) {
                return false;
            }
            Representations representations = (Representations) other;
            return this.hasSpatial == representations.hasSpatial && this.hasStereo == representations.hasStereo;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        /* JADX WARN: Type inference failed for: r1v0 */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v2 */
        public int hashCode() {
            boolean z = this.hasSpatial;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            boolean z2 = this.hasStereo;
            return i + (z2 ? 1 : z2);
        }

        public String toString() {
            return "Representations(hasSpatial=" + this.hasSpatial + ", hasStereo=" + this.hasStereo + ')';
        }

        public Representations(boolean z, boolean z2) {
            this.hasSpatial = z;
            this.hasStereo = z2;
        }

        public final boolean getHasSpatial() {
            return this.hasSpatial;
        }

        public final boolean getHasStereo() {
            return this.hasStereo;
        }
    }

    private final Representations selectRepresentations(NerdStatsDataModels$NerdStatsState nerdStatsState, MediaItem mediaItem) {
        int i = WhenMappings.$EnumSwitchMapping$0[nerdStatsState.ordinal()];
        if (i == 1) {
            return new Representations(false, false);
        }
        if (i != 2 && i != 3 && i != 4) {
            throw new NoWhenBranchMatchedException();
        }
        if (mediaItem == null) {
            return new Representations(false, false);
        }
        InjectedAccess injectedAccess = this.injectedAccess;
        InjectedAccess injectedAccess2 = null;
        if (injectedAccess == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            injectedAccess = null;
        }
        PlaybackAudioQuality maxContentEncodingQuality = injectedAccess.getMaxContentEncodingQuality();
        if (maxContentEncodingQuality != PlaybackAudioQuality.UNKNOWN) {
            return new Representations(this.qualityMap.isAtmosOr360(maxContentEncodingQuality), true);
        }
        InjectedAccess injectedAccess3 = this.injectedAccess;
        if (injectedAccess3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
        } else {
            injectedAccess2 = injectedAccess3;
        }
        PlaybackAudioQuality playbackAudioQuality = injectedAccess2.getCurrentPlaybackAttributes().getPlaybackAudioQuality();
        Intrinsics.checkNotNullExpressionValue(playbackAudioQuality, "injectedAccess.getCurren…es().playbackAudioQuality");
        if (this.qualityMap.isAtmosOr360(playbackAudioQuality)) {
            return new Representations(true, false);
        }
        return new Representations(false, true);
    }

    private final boolean isSpatialActive(Representations representations) {
        if (representations.getHasSpatial() && representations.getHasStereo()) {
            InjectedAccess injectedAccess = this.injectedAccess;
            InjectedAccess injectedAccess2 = null;
            if (injectedAccess == null) {
                Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
                injectedAccess = null;
            }
            PlaybackAudioQuality playbackAudioQuality = injectedAccess.getCurrentPlaybackAttributes().getPlaybackAudioQuality();
            Intrinsics.checkNotNullExpressionValue(playbackAudioQuality, "injectedAccess.getCurren…es().playbackAudioQuality");
            if (playbackAudioQuality != PlaybackAudioQuality.UNKNOWN) {
                return this.qualityMap.isAtmosOr360(playbackAudioQuality);
            }
            InjectedAccess injectedAccess3 = this.injectedAccess;
            if (injectedAccess3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("injectedAccess");
            } else {
                injectedAccess2 = injectedAccess3;
            }
            return injectedAccess2.isSpatialModeSettingEnabled();
        }
        return representations.getHasSpatial();
    }
}
