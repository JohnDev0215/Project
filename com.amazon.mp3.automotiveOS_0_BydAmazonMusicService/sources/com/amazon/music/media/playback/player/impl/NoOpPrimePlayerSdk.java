package com.amazon.music.media.playback.player.impl;

import android.content.Context;
import com.amazon.avod.content.urlvending.EPrivacyConsentData;
import com.amazon.avod.media.TimeSpan;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.media.playback.player.NoOpPrimeVideoFeaturePlayer;
import com.amazon.video.sdk.AdConfig;
import com.amazon.video.sdk.AdConfigProvider;
import com.amazon.video.sdk.AuthContext;
import com.amazon.video.sdk.EPrivacyConsentDataProvider;
import com.amazon.video.sdk.PlayerSdk;
import com.amazon.video.sdk.PlayerSdkConfig;
import com.amazon.video.sdk.PlayerSdkFeature;
import com.amazon.video.sdk.content.CacheContentConfig;
import com.amazon.video.sdk.content.LocalContentManager;
import com.amazon.video.sdk.content.LocalContentManagerConfig;
import com.amazon.video.sdk.download.DownloadFeatureConfig;
import com.amazon.video.sdk.player.HdcpLevelProvider;
import com.amazon.video.sdk.player.NoOpPlaybackEnvelopeValidator;
import com.amazon.video.sdk.player.PlaybackEnvelopeValidator;
import com.amazon.video.sdk.player.Player;
import com.amazon.video.sdk.player.PlayerConfig;
import com.amazon.video.sdk.player.UnsupportedPlatformHdcpLevelProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoOpPrimePlayerSdk.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000f\u0018\u00002\u00020\u0001:\b\u001f !\"#$%&B\u000f\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u001d\u0010\u001eJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0014\u001a\u00020\u00138\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0019\u001a\u00020\u00188\u0016X\u0096D¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006'"}, d2 = {"Lcom/amazon/music/media/playback/player/impl/NoOpPrimePlayerSdk;", "Lcom/amazon/video/sdk/PlayerSdk;", "Lcom/amazon/video/sdk/content/LocalContentManager;", "getLocalContentManagerFeature", "Lcom/amazon/video/sdk/player/PlayerConfig;", "config", "Lcom/amazon/video/sdk/player/Player;", "getPlayerFeature", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "", "Lcom/amazon/video/sdk/PlayerSdkFeature;", "features", "Ljava/util/List;", "getFeatures", "()Ljava/util/List;", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "sdkConfig", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "getSdkConfig", "()Lcom/amazon/video/sdk/PlayerSdkConfig;", "", "version", "Ljava/lang/String;", "getVersion", "()Ljava/lang/String;", "<init>", "(Landroid/content/Context;)V", "NoOpAdConfig", "NoOpAdConfigProvider", "NoOpAuthContext", "NoOpDownloadFeatureConfig", "NoOpEPrivacyConsentDataProvider", "NoOpLocalContentManager", "NoOpLocalContentManagerConfig", "NoOpPlayerSdkConfig", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class NoOpPrimePlayerSdk implements PlayerSdk {
    private final Context context;
    private final List<PlayerSdkFeature> features;
    private final PlayerSdkConfig sdkConfig;
    private final String version;

    /* JADX INFO: compiled from: NoOpPrimePlayerSdk.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/music/media/playback/player/impl/NoOpPrimePlayerSdk$NoOpLocalContentManager;", "Lcom/amazon/video/sdk/content/LocalContentManager;", "", "Lcom/amazon/video/sdk/content/CacheContentConfig;", "cacheContentConfigList", "", "cache", "<init>", "()V", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final class NoOpLocalContentManager implements LocalContentManager {
        @Override // com.amazon.video.sdk.content.LocalContentManager
        public void cache(List<? extends CacheContentConfig> cacheContentConfigList) {
            Intrinsics.checkNotNullParameter(cacheContentConfigList, "cacheContentConfigList");
        }
    }

    public NoOpPrimePlayerSdk(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.features = CollectionsKt__CollectionsKt.emptyList();
        this.sdkConfig = new NoOpPlayerSdkConfig(context, 0, 2, null);
        this.version = "noOpVersion";
    }

    @Override // com.amazon.video.sdk.PlayerSdk
    public PlayerSdkConfig getSdkConfig() {
        return this.sdkConfig;
    }

    @Override // com.amazon.video.sdk.PlayerSdk
    public String getVersion() {
        return this.version;
    }

    @Override // com.amazon.video.sdk.PlayerSdk
    public LocalContentManager getLocalContentManagerFeature() {
        return new NoOpLocalContentManager();
    }

    @Override // com.amazon.video.sdk.PlayerSdk
    public Player getPlayerFeature(PlayerConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return new NoOpPrimeVideoFeaturePlayer();
    }

    /* JADX INFO: compiled from: NoOpPrimePlayerSdk.kt */
    @Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b8\u00109R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\b\u001a\u00020\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\r\u001a\u00020\f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u00118\u0016X\u0096D¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u00118\u0016X\u0096D¢\u0006\f\n\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u001a\u0010\u0019\u001a\u00020\u00188\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001e\u001a\u00020\u001d8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u00118\u0016X\u0096D¢\u0006\f\n\u0004\b\"\u0010\u0013\u001a\u0004\b#\u0010\u0015R\u001a\u0010%\u001a\u00020$8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u001a\u0010*\u001a\u00020)8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u001a\u0010/\u001a\u00020.8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102R\u001a\u00104\u001a\u0002038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107¨\u0006:"}, d2 = {"Lcom/amazon/music/media/playback/player/impl/NoOpPrimePlayerSdk$NoOpPlayerSdkConfig;", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "", "rendererDrmFlags", "I", "getRendererDrmFlags", "()I", "Lcom/amazon/video/sdk/AuthContext;", "authContext", "Lcom/amazon/video/sdk/AuthContext;", "getAuthContext", "()Lcom/amazon/video/sdk/AuthContext;", "", Splash.PARAMS_DEVICE_ID, "Ljava/lang/String;", "getDeviceId", "()Ljava/lang/String;", "deviceTypeId", "getDeviceTypeId", "Lcom/amazon/video/sdk/download/DownloadFeatureConfig;", "downloadFeatureConfig", "Lcom/amazon/video/sdk/download/DownloadFeatureConfig;", "getDownloadFeatureConfig", "()Lcom/amazon/video/sdk/download/DownloadFeatureConfig;", "Lcom/amazon/video/sdk/content/LocalContentManagerConfig;", "localContentManagerConfig", "Lcom/amazon/video/sdk/content/LocalContentManagerConfig;", "getLocalContentManagerConfig", "()Lcom/amazon/video/sdk/content/LocalContentManagerConfig;", "route", "getRoute", "Lcom/amazon/video/sdk/AdConfigProvider;", "adConfigProvider", "Lcom/amazon/video/sdk/AdConfigProvider;", "getAdConfigProvider", "()Lcom/amazon/video/sdk/AdConfigProvider;", "Lcom/amazon/video/sdk/EPrivacyConsentDataProvider;", "ePrivacyConsentDataProvider", "Lcom/amazon/video/sdk/EPrivacyConsentDataProvider;", "getEPrivacyConsentDataProvider", "()Lcom/amazon/video/sdk/EPrivacyConsentDataProvider;", "Lcom/amazon/video/sdk/player/HdcpLevelProvider;", "hdcpLevelProvider", "Lcom/amazon/video/sdk/player/HdcpLevelProvider;", "getHdcpLevelProvider", "()Lcom/amazon/video/sdk/player/HdcpLevelProvider;", "Lcom/amazon/video/sdk/player/PlaybackEnvelopeValidator;", "playbackEnvelopeValidator", "Lcom/amazon/video/sdk/player/PlaybackEnvelopeValidator;", "getPlaybackEnvelopeValidator", "()Lcom/amazon/video/sdk/player/PlaybackEnvelopeValidator;", "<init>", "(Landroid/content/Context;I)V", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final class NoOpPlayerSdkConfig implements PlayerSdkConfig {
        private final AdConfigProvider adConfigProvider;
        private final AuthContext authContext;
        private final Context context;
        private final String deviceId;
        private final String deviceTypeId;
        private final DownloadFeatureConfig downloadFeatureConfig;
        private final EPrivacyConsentDataProvider ePrivacyConsentDataProvider;
        private final HdcpLevelProvider hdcpLevelProvider;
        private final LocalContentManagerConfig localContentManagerConfig;
        private final PlaybackEnvelopeValidator playbackEnvelopeValidator;
        private final int rendererDrmFlags;
        private final String route;

        public NoOpPlayerSdkConfig(Context context, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
            this.rendererDrmFlags = i;
            this.authContext = new NoOpAuthContext();
            this.deviceId = "noOpDeviceId";
            this.deviceTypeId = "noOpDeviceTypeId";
            this.downloadFeatureConfig = new NoOpDownloadFeatureConfig();
            this.localContentManagerConfig = new NoOpLocalContentManagerConfig();
            this.route = "noOpRoute";
            this.adConfigProvider = new NoOpAdConfigProvider(null, 1, 0 == true ? 1 : 0);
            this.ePrivacyConsentDataProvider = new NoOpEPrivacyConsentDataProvider();
            this.hdcpLevelProvider = new UnsupportedPlatformHdcpLevelProvider();
            this.playbackEnvelopeValidator = new NoOpPlaybackEnvelopeValidator();
        }

        public /* synthetic */ NoOpPlayerSdkConfig(Context context, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(context, (i2 & 2) != 0 ? 0 : i);
        }

        @Override // com.amazon.video.sdk.PlayerSdkConfig
        public Context getContext() {
            return this.context;
        }

        @Override // com.amazon.video.sdk.PlayerSdkConfig
        public int getRendererDrmFlags() {
            return this.rendererDrmFlags;
        }

        @Override // com.amazon.video.sdk.PlayerSdkConfig
        public AuthContext getAuthContext() {
            return this.authContext;
        }

        @Override // com.amazon.video.sdk.PlayerSdkConfig
        public String getDeviceId() {
            return this.deviceId;
        }

        @Override // com.amazon.video.sdk.PlayerSdkConfig
        public String getDeviceTypeId() {
            return this.deviceTypeId;
        }

        @Override // com.amazon.video.sdk.PlayerSdkConfig
        public LocalContentManagerConfig getLocalContentManagerConfig() {
            return this.localContentManagerConfig;
        }

        @Override // com.amazon.video.sdk.PlayerSdkConfig
        public String getRoute() {
            return this.route;
        }

        @Override // com.amazon.video.sdk.PlayerSdkConfig
        public AdConfigProvider getAdConfigProvider() {
            return this.adConfigProvider;
        }

        @Override // com.amazon.video.sdk.PlayerSdkConfig
        public EPrivacyConsentDataProvider getEPrivacyConsentDataProvider() {
            return this.ePrivacyConsentDataProvider;
        }

        @Override // com.amazon.video.sdk.PlayerSdkConfig
        public HdcpLevelProvider getHdcpLevelProvider() {
            return this.hdcpLevelProvider;
        }

        @Override // com.amazon.video.sdk.PlayerSdkConfig
        public PlaybackEnvelopeValidator getPlaybackEnvelopeValidator() {
            return this.playbackEnvelopeValidator;
        }
    }

    /* JADX INFO: compiled from: NoOpPrimePlayerSdk.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/media/playback/player/impl/NoOpPrimePlayerSdk$NoOpAdConfigProvider;", "Lcom/amazon/video/sdk/AdConfigProvider;", "adConfig", "Lcom/amazon/video/sdk/AdConfig;", "(Lcom/amazon/video/sdk/AdConfig;)V", "getAdConfig", "()Lcom/amazon/video/sdk/AdConfig;", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class NoOpAdConfigProvider implements AdConfigProvider {
        private final AdConfig adConfig;

        public NoOpAdConfigProvider() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public NoOpAdConfigProvider(AdConfig adConfig) {
            Intrinsics.checkNotNullParameter(adConfig, "adConfig");
            this.adConfig = adConfig;
        }

        public /* synthetic */ NoOpAdConfigProvider(AdConfig adConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                adConfig = new NoOpAdConfig(null, false, 3, 0 == true ? 1 : 0);
            }
            this(adConfig);
        }

        @Override // com.amazon.video.sdk.AdConfigProvider
        public AdConfig getAdConfig() {
            return this.adConfig;
        }
    }

    /* JADX INFO: compiled from: NoOpPrimePlayerSdk.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/media/playback/player/impl/NoOpPrimePlayerSdk$NoOpAdConfig;", "Lcom/amazon/video/sdk/AdConfig;", "advertisingId", "", "optOut", "", "(Ljava/lang/String;Z)V", "getAdvertisingId", "()Ljava/lang/String;", "getOptOut", "()Z", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class NoOpAdConfig implements AdConfig {
        private final String advertisingId;
        private final boolean optOut;

        public NoOpAdConfig() {
            this(null, false, 3, 0 == true ? 1 : 0);
        }

        public NoOpAdConfig(String advertisingId, boolean z) {
            Intrinsics.checkNotNullParameter(advertisingId, "advertisingId");
            this.advertisingId = advertisingId;
            this.optOut = z;
        }

        public /* synthetic */ NoOpAdConfig(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? false : z);
        }

        @Override // com.amazon.video.sdk.AdConfig
        public String getAdvertisingId() {
            return this.advertisingId;
        }

        @Override // com.amazon.video.sdk.AdConfig
        public boolean getOptOut() {
            return this.optOut;
        }
    }

    /* JADX INFO: compiled from: NoOpPrimePlayerSdk.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\f\u0010\rR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096D¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/media/playback/player/impl/NoOpPrimePlayerSdk$NoOpLocalContentManagerConfig;", "Lcom/amazon/video/sdk/content/LocalContentManagerConfig;", "", "allocationBytes", "J", "getAllocationBytes", "()J", "Lcom/amazon/avod/media/TimeSpan;", "cacheDuration", "Lcom/amazon/avod/media/TimeSpan;", "getCacheDuration", "()Lcom/amazon/avod/media/TimeSpan;", "<init>", "()V", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final class NoOpLocalContentManagerConfig implements LocalContentManagerConfig {
        private final long allocationBytes;
        private final TimeSpan cacheDuration = new TimeSpan(0);

        @Override // com.amazon.video.sdk.content.LocalContentManagerConfig
        public long getAllocationBytes() {
            return this.allocationBytes;
        }

        @Override // com.amazon.video.sdk.content.LocalContentManagerConfig
        public TimeSpan getCacheDuration() {
            return this.cacheDuration;
        }
    }

    /* JADX INFO: compiled from: NoOpPrimePlayerSdk.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\t\u0010\nJ(\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00022\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002H\u0016R#\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0005\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/media/playback/player/impl/NoOpPrimePlayerSdk$NoOpAuthContext;", "Lcom/amazon/video/sdk/AuthContext;", "", "", "sessionContext", "getHeaders", "headers", "Ljava/util/Map;", "()Ljava/util/Map;", "<init>", "()V", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final class NoOpAuthContext implements AuthContext {
        private final Map<String, String> headers = new HashMap();

        @Override // com.amazon.video.sdk.AuthContext
        public Map<String, String> getHeaders(Map<String, String> sessionContext) {
            Intrinsics.checkNotNullParameter(sessionContext, "sessionContext");
            return this.headers;
        }
    }

    /* JADX INFO: compiled from: NoOpPrimePlayerSdk.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/media/playback/player/impl/NoOpPrimePlayerSdk$NoOpDownloadFeatureConfig;", "Lcom/amazon/video/sdk/download/DownloadFeatureConfig;", "()V", "minimumDownloadProgressForPlayback", "", "getMinimumDownloadProgressForPlayback", "()D", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class NoOpDownloadFeatureConfig implements DownloadFeatureConfig {
        private final double minimumDownloadProgressForPlayback;

        @Override // com.amazon.video.sdk.download.DownloadFeatureConfig
        public double getMinimumDownloadProgressForPlayback() {
            return this.minimumDownloadProgressForPlayback;
        }
    }

    /* JADX INFO: compiled from: NoOpPrimePlayerSdk.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/media/playback/player/impl/NoOpPrimePlayerSdk$NoOpEPrivacyConsentDataProvider;", "Lcom/amazon/video/sdk/EPrivacyConsentDataProvider;", "()V", "ePrivacyConsentData", "Lcom/amazon/avod/content/urlvending/EPrivacyConsentData;", "getEPrivacyConsentData", "()Lcom/amazon/avod/content/urlvending/EPrivacyConsentData;", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class NoOpEPrivacyConsentDataProvider implements EPrivacyConsentDataProvider {
        private final EPrivacyConsentData ePrivacyConsentData;

        public NoOpEPrivacyConsentDataProvider() {
            EPrivacyConsentData EMPTY = EPrivacyConsentData.EMPTY;
            Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
            this.ePrivacyConsentData = EMPTY;
        }

        @Override // com.amazon.video.sdk.EPrivacyConsentDataProvider
        public EPrivacyConsentData getEPrivacyConsentData() {
            return this.ePrivacyConsentData;
        }
    }
}
