package com.amazon.video.sdk;

import com.amazon.video.sdk.content.LocalContentManager;
import com.amazon.video.sdk.player.Player;
import com.amazon.video.sdk.player.PlayerConfig;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlayerSdk.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0007\u001a\u00020\u0006H&R\u0014\u0010\u000b\u001a\u00020\b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000f\u001a\u00020\f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/amazon/video/sdk/PlayerSdk;", "", "Lcom/amazon/video/sdk/player/PlayerConfig;", "config", "Lcom/amazon/video/sdk/player/Player;", "getPlayerFeature", "Lcom/amazon/video/sdk/content/LocalContentManager;", "getLocalContentManagerFeature", "", "getVersion", "()Ljava/lang/String;", "version", "Lcom/amazon/video/sdk/PlayerSdkConfig;", "getSdkConfig", "()Lcom/amazon/video/sdk/PlayerSdkConfig;", "sdkConfig", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface PlayerSdk {
    LocalContentManager getLocalContentManagerFeature();

    Player getPlayerFeature(PlayerConfig config);

    PlayerSdkConfig getSdkConfig();

    String getVersion();
}
