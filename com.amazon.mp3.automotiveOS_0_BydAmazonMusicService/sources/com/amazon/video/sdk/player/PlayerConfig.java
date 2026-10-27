package com.amazon.video.sdk.player;

import android.content.Context;
import kotlin.Metadata;

/* JADX INFO: compiled from: Player.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerConfig;", "", "additionalConfigs", "Lcom/amazon/video/sdk/player/AdditionalPlayerConfigs;", "getAdditionalConfigs", "()Lcom/amazon/video/sdk/player/AdditionalPlayerConfigs;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "features", "Lcom/amazon/video/sdk/player/FeatureConfigs;", "getFeatures", "()Lcom/amazon/video/sdk/player/FeatureConfigs;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface PlayerConfig {
    AdditionalPlayerConfigs getAdditionalConfigs();

    Context getContext();

    FeatureConfigs getFeatures();
}
