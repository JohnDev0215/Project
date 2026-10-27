package com.amazon.mp3.audioquality.nerdstats.models;

import android.content.Context;
import android.media.AudioManager;
import androidx.annotation.StringRes;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModelFactory;
import com.amazon.mp3.capability.Capabilities;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.download.DownloadQualityPreferences;
import com.amazon.mp3.download.helper.UserPreferenceProvider;
import com.amazon.mp3.platform.providers.DeviceInfoProviderImpl;
import com.amazon.mp3.playback.KatanaNPSBadgeProvider;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.chromecast.ChromecastController;
import com.amazon.music.chromecast.alert.ReceiverAlert;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NerdStatsViewModelFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0002\u000b\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "Companion", "InjectedAccessImpl", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NerdStatsViewModelFactory implements ViewModelProvider.Factory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Context context;

    public NerdStatsViewModelFactory(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    /* JADX INFO: compiled from: NerdStatsViewModelFactory.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModelFactory$Companion;", "", "()V", "getViewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ViewModelProvider.Factory getViewModelFactory(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new NerdStatsViewModelFactory(context);
        }
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(NerdStatsViewModel.class)) {
            return new NerdStatsViewModel(new InjectedAccessFactory() { // from class: com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModelFactory$create$injectedAccessFactory$1
                private final AudioManager audioManager;
                private final ChromecastController chromecastController;
                private final DeviceInfoProviderImpl deviceInfoProvider;
                private final PlaybackController playbackController;
                private final UserPreferenceProvider userPreferenceProvider;

                {
                    Object systemService = this.this$0.context.getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO);
                    if (systemService == null) {
                        throw new NullPointerException("null cannot be cast to non-null type android.media.AudioManager");
                    }
                    this.audioManager = (AudioManager) systemService;
                    this.chromecastController = ChromecastController.getInstance();
                    this.deviceInfoProvider = new DeviceInfoProviderImpl(this.this$0.context);
                    this.playbackController = Playback.getInstance().getPlaybackController(ControlSource.NONE);
                    this.userPreferenceProvider = new UserPreferenceProvider(this.this$0.context);
                }

                @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedAccessFactory
                public InjectedAccess getInjectedAccess() {
                    AudioManager audioManager = this.audioManager;
                    ChromecastController chromecastController = this.chromecastController;
                    Intrinsics.checkNotNullExpressionValue(chromecastController, "chromecastController");
                    Context context = this.this$0.context;
                    DeviceInfoProviderImpl deviceInfoProviderImpl = this.deviceInfoProvider;
                    PlaybackController playbackController = this.playbackController;
                    Intrinsics.checkNotNullExpressionValue(playbackController, "playbackController");
                    return new NerdStatsViewModelFactory.InjectedAccessImpl(audioManager, chromecastController, context, deviceInfoProviderImpl, playbackController, this.userPreferenceProvider);
                }
            });
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: NerdStatsViewModelFactory.kt */
    @Metadata(bv = {}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010'\u001a\u00020\u0010\u0012\u0006\u0010*\u001a\u00020)\u0012\u0006\u0010-\u001a\u00020,\u0012\u0006\u00100\u001a\u00020/\u0012\u0006\u0010;\u001a\u00020:\u0012\u0006\u00103\u001a\u000202¢\u0006\u0004\b<\u0010=J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016J\u000e\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\nH\u0016J\b\u0010\f\u001a\u00020\u0004H\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0015\u001a\u00020\u00022\b\b\u0001\u0010\u0014\u001a\u00020\u0013H\u0016J-\u0010\u0015\u001a\u00020\u00022\b\b\u0001\u0010\u0014\u001a\u00020\u00132\u0012\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u0016\"\u00020\u0017H\u0016¢\u0006\u0004\b\u0015\u0010\u0019J\b\u0010\u001a\u001a\u00020\u000eH\u0016J\b\u0010\u001b\u001a\u00020\u0004H\u0016J\b\u0010\u001d\u001a\u00020\u001cH\u0016J\n\u0010\u001f\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010 \u001a\u00020\u001cH\u0016J\b\u0010!\u001a\u00020\u0002H\u0016J\b\u0010\"\u001a\u00020\u0002H\u0016J\b\u0010#\u001a\u00020\u0004H\u0016J\b\u0010$\u001a\u00020\u0004H\u0016J\b\u0010&\u001a\u00020%H\u0016R\u0014\u0010'\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0014\u00103\u001a\u0002028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u0014\u00105\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R\u0016\u00107\u001a\u0004\u0018\u00010\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0014\u00109\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u00106¨\u0006>"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModelFactory$InjectedAccessImpl;", "Lcom/amazon/mp3/audioquality/nerdstats/models/InjectedAccess;", "", "getCastingDeviceName", "", "isCasting", "isCastingToAlexa", "isCastingToChromecast", "enableChromecastSpatialToggle", "isChromecastNerdStatsChannelActive", "Lcom/amazon/music/chromecast/alert/ReceiverAlert;", "chromecastReceiverAlert", "hasAnyInternetConnection", "isWifiConnected", "Lcom/amazon/music/media/playback/PlaybackAudioQuality;", "getMaxContentEncodingQuality", "Landroid/media/AudioManager;", "getAudioManager", "getDeviceModelName", "", "id", "getString", "", "", "formatArgs", "(I[Ljava/lang/Object;)Ljava/lang/String;", "getMaxPlaybackQuality", "isCurrentMediaItemDownloaded", "Lcom/amazon/music/media/playback/PlaybackAttributes;", "getBestAvailablePlaybackAttributes", "Lcom/amazon/music/media/playback/MediaItem;", "getCurrentMediaItem", "getCurrentPlaybackAttributes", "getMultiBitrateStreamingPreferenceForOTA", "getMultiBitrateStreamingPreferenceForWifi", "isPrioritizeDownloadsEnabled", "isSpatialModeSettingEnabled", "Lcom/amazon/mp3/download/DownloadQualityPreferences;", "getCurrentDownloadQualityPreference", "audioManager", "Landroid/media/AudioManager;", "Lcom/amazon/music/chromecast/ChromecastController;", "chromecastController", "Lcom/amazon/music/chromecast/ChromecastController;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "Lcom/amazon/mp3/download/helper/UserPreferenceProvider;", "userPreferenceProvider", "Lcom/amazon/mp3/download/helper/UserPreferenceProvider;", "bestAvailablePlaybackAttributes", "Lcom/amazon/music/media/playback/PlaybackAttributes;", "currentMediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "currentPlaybackAttributes", "Lcom/amazon/music/media/playback/PlaybackController;", "playbackController", "<init>", "(Landroid/media/AudioManager;Lcom/amazon/music/chromecast/ChromecastController;Landroid/content/Context;Lcom/amazon/music/platform/providers/DeviceInfoProvider;Lcom/amazon/music/media/playback/PlaybackController;Lcom/amazon/mp3/download/helper/UserPreferenceProvider;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    static final class InjectedAccessImpl implements InjectedAccess {
        private final AudioManager audioManager;
        private final PlaybackAttributes bestAvailablePlaybackAttributes;
        private final ChromecastController chromecastController;
        private final Context context;
        private final MediaItem currentMediaItem;
        private final PlaybackAttributes currentPlaybackAttributes;
        private final DeviceInfoProvider deviceInfoProvider;
        private final UserPreferenceProvider userPreferenceProvider;

        public InjectedAccessImpl(AudioManager audioManager, ChromecastController chromecastController, Context context, DeviceInfoProvider deviceInfoProvider, PlaybackController playbackController, UserPreferenceProvider userPreferenceProvider) {
            Intrinsics.checkNotNullParameter(audioManager, "audioManager");
            Intrinsics.checkNotNullParameter(chromecastController, "chromecastController");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(deviceInfoProvider, "deviceInfoProvider");
            Intrinsics.checkNotNullParameter(playbackController, "playbackController");
            Intrinsics.checkNotNullParameter(userPreferenceProvider, "userPreferenceProvider");
            this.audioManager = audioManager;
            this.chromecastController = chromecastController;
            this.context = context;
            this.deviceInfoProvider = deviceInfoProvider;
            this.userPreferenceProvider = userPreferenceProvider;
            PlaybackAttributes bestAvailablePlaybackAttributes = playbackController.getBestAvailablePlaybackAttributes();
            Intrinsics.checkNotNullExpressionValue(bestAvailablePlaybackAttributes, "playbackController.bestAvailablePlaybackAttributes");
            this.bestAvailablePlaybackAttributes = bestAvailablePlaybackAttributes;
            this.currentMediaItem = playbackController.getCurrentMediaItem();
            PlaybackAttributes currentPlaybackAttributes = playbackController.getCurrentPlaybackAttributes();
            Intrinsics.checkNotNullExpressionValue(currentPlaybackAttributes, "playbackController.currentPlaybackAttributes");
            this.currentPlaybackAttributes = currentPlaybackAttributes;
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedCastingSessionManager
        public String getCastingDeviceName() {
            CastingDevice castingDevice = CastingSessionManager.getInstance().getCastingDevice();
            if (castingDevice == null) {
                return null;
            }
            return castingDevice.getName();
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedCastingUtil
        public boolean isCasting() {
            return CastingUtil.isCasting();
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedCastingUtil
        public boolean isCastingToAlexa() {
            return CastingUtil.isCastingToAlexa();
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedCastingUtil
        public boolean isCastingToChromecast() {
            return CastingUtil.isCastingToChromecast();
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedChromecastController
        public boolean enableChromecastSpatialToggle() {
            return this.chromecastController.enableSpatialToggle();
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedChromecastController
        public boolean isChromecastNerdStatsChannelActive() {
            return this.chromecastController.isChromecastNerdStatsChannelActive();
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedChromecastController
        public ReceiverAlert<?> chromecastReceiverAlert() {
            return this.chromecastController.receiverAlert();
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedConnectivityUtil
        public boolean hasAnyInternetConnection() {
            return ConnectivityUtil.hasAnyInternetConnection(this.context);
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedConnectivityUtil
        public boolean isWifiConnected() {
            return ConnectivityUtil.isWifiConnected(this.context);
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedContentEncodingDecoder
        public PlaybackAudioQuality getMaxContentEncodingQuality() {
            List<ContentEncoding> contentEncodings;
            MediaItem mediaItem = this.currentMediaItem;
            PlaybackAudioQuality maxPlaybackAudioQuality = null;
            if (mediaItem != null && (contentEncodings = mediaItem.getContentEncodings()) != null) {
                maxPlaybackAudioQuality = ContentEncodingDecoder.getMaxPlaybackAudioQuality(contentEncodings);
            }
            return maxPlaybackAudioQuality == null ? PlaybackAudioQuality.UNKNOWN : maxPlaybackAudioQuality;
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedContext
        public AudioManager getAudioManager() {
            return this.audioManager;
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedContext
        public String getDeviceModelName() {
            return this.deviceInfoProvider.getModel();
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedStringAccess
        public String getString(@StringRes int id) {
            String string = this.context.getString(id);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(id)");
            return string;
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedStringAccess
        public String getString(@StringRes int id, Object... formatArgs) {
            Intrinsics.checkNotNullParameter(formatArgs, "formatArgs");
            String string = this.context.getString(id, Arrays.copyOf(formatArgs, formatArgs.length));
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(id, *formatArgs)");
            return string;
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedKatanaNPSBadgeProvider
        public PlaybackAudioQuality getMaxPlaybackQuality() {
            PlaybackAudioQuality maxPlaybackQuality = KatanaNPSBadgeProvider.getMaxPlaybackQuality(this.context, this.currentMediaItem);
            Intrinsics.checkNotNullExpressionValue(maxPlaybackQuality, "getMaxPlaybackQuality(context, currentMediaItem)");
            return maxPlaybackQuality;
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedKatanaNPSBadgeProvider
        public boolean isCurrentMediaItemDownloaded() {
            Boolean boolIsCurrentMediaItemDownloaded = KatanaNPSBadgeProvider.isCurrentMediaItemDownloaded(this.currentMediaItem);
            if (boolIsCurrentMediaItemDownloaded == null) {
                return false;
            }
            return boolIsCurrentMediaItemDownloaded.booleanValue();
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedPlaybackController
        public PlaybackAttributes getBestAvailablePlaybackAttributes() {
            return this.bestAvailablePlaybackAttributes;
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedPlaybackController
        public MediaItem getCurrentMediaItem() {
            return this.currentMediaItem;
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedPlaybackController
        public PlaybackAttributes getCurrentPlaybackAttributes() {
            return this.currentPlaybackAttributes;
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedSettingsUtil
        public String getMultiBitrateStreamingPreferenceForOTA() {
            String multiBitrateStreamingPreferenceForOTA = SettingsUtil.getMultiBitrateStreamingPreferenceForOTA(this.context);
            Intrinsics.checkNotNullExpressionValue(multiBitrateStreamingPreferenceForOTA, "getMultiBitrateStreamingPreferenceForOTA(context)");
            return multiBitrateStreamingPreferenceForOTA;
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedSettingsUtil
        public String getMultiBitrateStreamingPreferenceForWifi() {
            String multiBitrateStreamingPreferenceForWifi = SettingsUtil.getMultiBitrateStreamingPreferenceForWifi(this.context);
            Intrinsics.checkNotNullExpressionValue(multiBitrateStreamingPreferenceForWifi, "getMultiBitrateStreamingPreferenceForWifi(context)");
            return multiBitrateStreamingPreferenceForWifi;
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedSettingsUtil
        public boolean isPrioritizeDownloadsEnabled() {
            return Capabilities.isPrioritizeDownloadsSupported() && SettingsUtil.isPrioritizeDownloadsEnabled(this.context);
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedSettingsUtil
        public boolean isSpatialModeSettingEnabled() {
            return SettingsUtil.isSpatialModeSettingEnabled(this.context);
        }

        @Override // com.amazon.mp3.audioquality.nerdstats.models.InjectedUserPreferenceProvider
        public DownloadQualityPreferences getCurrentDownloadQualityPreference() {
            DownloadQualityPreferences currentDownloadQualityPreference = this.userPreferenceProvider.getCurrentDownloadQualityPreference();
            Intrinsics.checkNotNullExpressionValue(currentDownloadQualityPreference, "userPreferenceProvider.c…DownloadQualityPreference");
            return currentDownloadQualityPreference;
        }
    }
}
