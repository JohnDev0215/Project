package com.amazon.video.sdk;

import android.content.Context;
import com.amazon.avod.content.urlvending.EPrivacyConsentData;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.video.sdk.content.LocalContentManagerConfig;
import com.amazon.video.sdk.content.LocalContentManagerConfigData;
import com.amazon.video.sdk.download.DownloadFeatureConfig;
import com.amazon.video.sdk.download.DownloadFeatureConfigData;
import com.amazon.video.sdk.player.HdcpLevelProvider;
import com.amazon.video.sdk.player.NoOpPlaybackEnvelopeValidator;
import com.amazon.video.sdk.player.PlaybackEnvelopeValidator;
import com.amazon.video.sdk.player.UnsupportedPlatformHdcpLevelProvider;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerSdk.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001By\b\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0002\u0012\b\b\u0002\u0010!\u001a\u00020\u0004\u0012\b\b\u0002\u0010&\u001a\u00020%\u0012\b\b\u0002\u0010+\u001a\u00020*\u0012\b\b\u0002\u00100\u001a\u00020/\u0012\b\b\u0002\u00105\u001a\u000204\u0012\b\b\u0002\u0010:\u001a\u000209¢\u0006\u0004\b>\u0010?J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\u00020\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0019\u0010\u0017R\u001a\u0010\u001b\u001a\u00020\u001a8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001f\u0010\u0015\u001a\u0004\b \u0010\u0017R\u001a\u0010!\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u001a\u0010&\u001a\u00020%8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u001a\u0010+\u001a\u00020*8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u001a\u00100\u001a\u00020/8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u001a\u00105\u001a\u0002048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u001a\u0010:\u001a\u0002098\u0016X\u0096\u0004¢\u0006\f\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=¨\u0006@"}, d2 = {"Lcom/amazon/video/sdk/PlayerSdkConfigData;", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "", "toString", "", "hashCode", "", "other", "", "equals", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/video/sdk/AuthContext;", "authContext", "Lcom/amazon/video/sdk/AuthContext;", "getAuthContext", "()Lcom/amazon/video/sdk/AuthContext;", "route", "Ljava/lang/String;", "getRoute", "()Ljava/lang/String;", Splash.PARAMS_DEVICE_ID, "getDeviceId", "Lcom/amazon/video/sdk/player/PlaybackEnvelopeValidator;", "playbackEnvelopeValidator", "Lcom/amazon/video/sdk/player/PlaybackEnvelopeValidator;", "getPlaybackEnvelopeValidator", "()Lcom/amazon/video/sdk/player/PlaybackEnvelopeValidator;", "deviceTypeId", "getDeviceTypeId", "rendererDrmFlags", "I", "getRendererDrmFlags", "()I", "Lcom/amazon/video/sdk/AdConfigProvider;", "adConfigProvider", "Lcom/amazon/video/sdk/AdConfigProvider;", "getAdConfigProvider", "()Lcom/amazon/video/sdk/AdConfigProvider;", "Lcom/amazon/video/sdk/EPrivacyConsentDataProvider;", "ePrivacyConsentDataProvider", "Lcom/amazon/video/sdk/EPrivacyConsentDataProvider;", "getEPrivacyConsentDataProvider", "()Lcom/amazon/video/sdk/EPrivacyConsentDataProvider;", "Lcom/amazon/video/sdk/content/LocalContentManagerConfig;", "localContentManagerConfig", "Lcom/amazon/video/sdk/content/LocalContentManagerConfig;", "getLocalContentManagerConfig", "()Lcom/amazon/video/sdk/content/LocalContentManagerConfig;", "Lcom/amazon/video/sdk/download/DownloadFeatureConfig;", "downloadFeatureConfig", "Lcom/amazon/video/sdk/download/DownloadFeatureConfig;", "getDownloadFeatureConfig", "()Lcom/amazon/video/sdk/download/DownloadFeatureConfig;", "Lcom/amazon/video/sdk/player/HdcpLevelProvider;", "hdcpLevelProvider", "Lcom/amazon/video/sdk/player/HdcpLevelProvider;", "getHdcpLevelProvider", "()Lcom/amazon/video/sdk/player/HdcpLevelProvider;", "<init>", "(Landroid/content/Context;Lcom/amazon/video/sdk/AuthContext;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/video/sdk/player/PlaybackEnvelopeValidator;Ljava/lang/String;ILcom/amazon/video/sdk/AdConfigProvider;Lcom/amazon/video/sdk/EPrivacyConsentDataProvider;Lcom/amazon/video/sdk/content/LocalContentManagerConfig;Lcom/amazon/video/sdk/download/DownloadFeatureConfig;Lcom/amazon/video/sdk/player/HdcpLevelProvider;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class PlayerSdkConfigData implements PlayerSdkConfig {
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

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PlayerSdkConfigData(Context context, AuthContext authContext, String route, String deviceId, PlaybackEnvelopeValidator playbackEnvelopeValidator, String deviceTypeId, int i, AdConfigProvider adConfigProvider, EPrivacyConsentDataProvider ePrivacyConsentDataProvider, LocalContentManagerConfig localContentManagerConfig) {
        this(context, authContext, route, deviceId, playbackEnvelopeValidator, deviceTypeId, i, adConfigProvider, ePrivacyConsentDataProvider, localContentManagerConfig, null, null, 3072, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(authContext, "authContext");
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(playbackEnvelopeValidator, "playbackEnvelopeValidator");
        Intrinsics.checkNotNullParameter(deviceTypeId, "deviceTypeId");
        Intrinsics.checkNotNullParameter(adConfigProvider, "adConfigProvider");
        Intrinsics.checkNotNullParameter(ePrivacyConsentDataProvider, "ePrivacyConsentDataProvider");
        Intrinsics.checkNotNullParameter(localContentManagerConfig, "localContentManagerConfig");
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayerSdkConfigData)) {
            return false;
        }
        PlayerSdkConfigData playerSdkConfigData = (PlayerSdkConfigData) other;
        return Intrinsics.areEqual(getContext(), playerSdkConfigData.getContext()) && Intrinsics.areEqual(getAuthContext(), playerSdkConfigData.getAuthContext()) && Intrinsics.areEqual(getRoute(), playerSdkConfigData.getRoute()) && Intrinsics.areEqual(getDeviceId(), playerSdkConfigData.getDeviceId()) && Intrinsics.areEqual(getPlaybackEnvelopeValidator(), playerSdkConfigData.getPlaybackEnvelopeValidator()) && Intrinsics.areEqual(getDeviceTypeId(), playerSdkConfigData.getDeviceTypeId()) && getRendererDrmFlags() == playerSdkConfigData.getRendererDrmFlags() && Intrinsics.areEqual(getAdConfigProvider(), playerSdkConfigData.getAdConfigProvider()) && Intrinsics.areEqual(getEPrivacyConsentDataProvider(), playerSdkConfigData.getEPrivacyConsentDataProvider()) && Intrinsics.areEqual(getLocalContentManagerConfig(), playerSdkConfigData.getLocalContentManagerConfig()) && Intrinsics.areEqual(getDownloadFeatureConfig(), playerSdkConfigData.getDownloadFeatureConfig()) && Intrinsics.areEqual(getHdcpLevelProvider(), playerSdkConfigData.getHdcpLevelProvider());
    }

    public int hashCode() {
        return (((((((((((((((((((((getContext().hashCode() * 31) + getAuthContext().hashCode()) * 31) + getRoute().hashCode()) * 31) + getDeviceId().hashCode()) * 31) + getPlaybackEnvelopeValidator().hashCode()) * 31) + getDeviceTypeId().hashCode()) * 31) + Integer.hashCode(getRendererDrmFlags())) * 31) + getAdConfigProvider().hashCode()) * 31) + getEPrivacyConsentDataProvider().hashCode()) * 31) + getLocalContentManagerConfig().hashCode()) * 31) + getDownloadFeatureConfig().hashCode()) * 31) + getHdcpLevelProvider().hashCode();
    }

    public String toString() {
        return "PlayerSdkConfigData(context=" + getContext() + ", authContext=" + getAuthContext() + ", route=" + getRoute() + ", deviceId=" + getDeviceId() + ", playbackEnvelopeValidator=" + getPlaybackEnvelopeValidator() + ", deviceTypeId=" + getDeviceTypeId() + ", rendererDrmFlags=" + getRendererDrmFlags() + ", adConfigProvider=" + getAdConfigProvider() + ", ePrivacyConsentDataProvider=" + getEPrivacyConsentDataProvider() + ", localContentManagerConfig=" + getLocalContentManagerConfig() + ", downloadFeatureConfig=" + getDownloadFeatureConfig() + ", hdcpLevelProvider=" + getHdcpLevelProvider() + ')';
    }

    @JvmOverloads
    public PlayerSdkConfigData(Context context, AuthContext authContext, String route, String deviceId, PlaybackEnvelopeValidator playbackEnvelopeValidator, String deviceTypeId, int i, AdConfigProvider adConfigProvider, EPrivacyConsentDataProvider ePrivacyConsentDataProvider, LocalContentManagerConfig localContentManagerConfig, DownloadFeatureConfig downloadFeatureConfig, HdcpLevelProvider hdcpLevelProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(authContext, "authContext");
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(playbackEnvelopeValidator, "playbackEnvelopeValidator");
        Intrinsics.checkNotNullParameter(deviceTypeId, "deviceTypeId");
        Intrinsics.checkNotNullParameter(adConfigProvider, "adConfigProvider");
        Intrinsics.checkNotNullParameter(ePrivacyConsentDataProvider, "ePrivacyConsentDataProvider");
        Intrinsics.checkNotNullParameter(localContentManagerConfig, "localContentManagerConfig");
        Intrinsics.checkNotNullParameter(downloadFeatureConfig, "downloadFeatureConfig");
        Intrinsics.checkNotNullParameter(hdcpLevelProvider, "hdcpLevelProvider");
        this.context = context;
        this.authContext = authContext;
        this.route = route;
        this.deviceId = deviceId;
        this.playbackEnvelopeValidator = playbackEnvelopeValidator;
        this.deviceTypeId = deviceTypeId;
        this.rendererDrmFlags = i;
        this.adConfigProvider = adConfigProvider;
        this.ePrivacyConsentDataProvider = ePrivacyConsentDataProvider;
        this.localContentManagerConfig = localContentManagerConfig;
        this.downloadFeatureConfig = downloadFeatureConfig;
        this.hdcpLevelProvider = hdcpLevelProvider;
    }

    @Override // com.amazon.video.sdk.PlayerSdkConfig
    public Context getContext() {
        return this.context;
    }

    @Override // com.amazon.video.sdk.PlayerSdkConfig
    public AuthContext getAuthContext() {
        return this.authContext;
    }

    @Override // com.amazon.video.sdk.PlayerSdkConfig
    public String getRoute() {
        return this.route;
    }

    @Override // com.amazon.video.sdk.PlayerSdkConfig
    public String getDeviceId() {
        return this.deviceId;
    }

    public /* synthetic */ PlayerSdkConfigData(Context context, AuthContext authContext, String str, String str2, PlaybackEnvelopeValidator playbackEnvelopeValidator, String str3, int i, AdConfigProvider adConfigProvider, EPrivacyConsentDataProvider ePrivacyConsentDataProvider, LocalContentManagerConfig localContentManagerConfig, DownloadFeatureConfig downloadFeatureConfig, HdcpLevelProvider hdcpLevelProvider, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, authContext, str, str2, (i2 & 16) != 0 ? new NoOpPlaybackEnvelopeValidator() : playbackEnvelopeValidator, (i2 & 32) != 0 ? "A1MPSLFC7L5AFK" : str3, (i2 & 64) != 0 ? 0 : i, (i2 & 128) != 0 ? new AdConfigProvider() { // from class: com.amazon.video.sdk.PlayerSdkConfigData.1
            @Override // com.amazon.video.sdk.AdConfigProvider
            public AdConfig getAdConfig() {
                return AdConfigData.INSTANCE.getDefaultConfig();
            }
        } : adConfigProvider, (i2 & 256) != 0 ? new EPrivacyConsentDataProvider() { // from class: com.amazon.video.sdk.PlayerSdkConfigData.2
            @Override // com.amazon.video.sdk.EPrivacyConsentDataProvider
            public EPrivacyConsentData getEPrivacyConsentData() {
                EPrivacyConsentData EMPTY = EPrivacyConsentData.EMPTY;
                Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
                return EMPTY;
            }
        } : ePrivacyConsentDataProvider, (i2 & 512) != 0 ? LocalContentManagerConfigData.INSTANCE.getDefaultConfig() : localContentManagerConfig, (i2 & 1024) != 0 ? DownloadFeatureConfigData.INSTANCE.getDefaultConfig() : downloadFeatureConfig, (i2 & 2048) != 0 ? new UnsupportedPlatformHdcpLevelProvider() : hdcpLevelProvider);
    }

    @Override // com.amazon.video.sdk.PlayerSdkConfig
    public PlaybackEnvelopeValidator getPlaybackEnvelopeValidator() {
        return this.playbackEnvelopeValidator;
    }

    @Override // com.amazon.video.sdk.PlayerSdkConfig
    public String getDeviceTypeId() {
        return this.deviceTypeId;
    }

    @Override // com.amazon.video.sdk.PlayerSdkConfig
    public int getRendererDrmFlags() {
        return this.rendererDrmFlags;
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
    public LocalContentManagerConfig getLocalContentManagerConfig() {
        return this.localContentManagerConfig;
    }

    public DownloadFeatureConfig getDownloadFeatureConfig() {
        return this.downloadFeatureConfig;
    }

    @Override // com.amazon.video.sdk.PlayerSdkConfig
    public HdcpLevelProvider getHdcpLevelProvider() {
        return this.hdcpLevelProvider;
    }
}
