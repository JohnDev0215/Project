package com.amazon.video.sdk;

import android.content.Context;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.video.sdk.content.LocalContentManagerConfig;
import com.amazon.video.sdk.player.HdcpLevelProvider;
import com.amazon.video.sdk.player.PlaybackEnvelopeValidator;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlayerSdk.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\r\u001a\u00020\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000f\u001a\u00020\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u0011\u001a\u00020\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\fR\u0014\u0010\u0015\u001a\u00020\u00128&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0019\u001a\u00020\u00168&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001d\u001a\u00020\u001a8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010!\u001a\u00020\u001e8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010%\u001a\u00020\"8&X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0014\u0010)\u001a\u00020&8&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(¨\u0006*"}, d2 = {"Lcom/amazon/video/sdk/PlayerSdkConfig;", "", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context", "Lcom/amazon/video/sdk/AuthContext;", "getAuthContext", "()Lcom/amazon/video/sdk/AuthContext;", "authContext", "", "getRoute", "()Ljava/lang/String;", "route", "getDeviceId", Splash.PARAMS_DEVICE_ID, "getDeviceTypeId", "deviceTypeId", "", "getRendererDrmFlags", "()I", "rendererDrmFlags", "Lcom/amazon/video/sdk/content/LocalContentManagerConfig;", "getLocalContentManagerConfig", "()Lcom/amazon/video/sdk/content/LocalContentManagerConfig;", "localContentManagerConfig", "Lcom/amazon/video/sdk/AdConfigProvider;", "getAdConfigProvider", "()Lcom/amazon/video/sdk/AdConfigProvider;", "adConfigProvider", "Lcom/amazon/video/sdk/player/PlaybackEnvelopeValidator;", "getPlaybackEnvelopeValidator", "()Lcom/amazon/video/sdk/player/PlaybackEnvelopeValidator;", "playbackEnvelopeValidator", "Lcom/amazon/video/sdk/player/HdcpLevelProvider;", "getHdcpLevelProvider", "()Lcom/amazon/video/sdk/player/HdcpLevelProvider;", "hdcpLevelProvider", "Lcom/amazon/video/sdk/EPrivacyConsentDataProvider;", "getEPrivacyConsentDataProvider", "()Lcom/amazon/video/sdk/EPrivacyConsentDataProvider;", "ePrivacyConsentDataProvider", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface PlayerSdkConfig {
    AdConfigProvider getAdConfigProvider();

    AuthContext getAuthContext();

    Context getContext();

    String getDeviceId();

    String getDeviceTypeId();

    EPrivacyConsentDataProvider getEPrivacyConsentDataProvider();

    HdcpLevelProvider getHdcpLevelProvider();

    LocalContentManagerConfig getLocalContentManagerConfig();

    PlaybackEnvelopeValidator getPlaybackEnvelopeValidator();

    int getRendererDrmFlags();

    String getRoute();
}
