package com.amazon.video.sdk.player;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Player.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0015\u001a\u00020\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001b"}, d2 = {"Lcom/amazon/video/sdk/player/PlayerConfigData;", "Lcom/amazon/video/sdk/player/PlayerConfig;", "", "toString", "", "hashCode", "", "other", "", "equals", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/video/sdk/player/FeatureConfigs;", "features", "Lcom/amazon/video/sdk/player/FeatureConfigs;", "getFeatures", "()Lcom/amazon/video/sdk/player/FeatureConfigs;", "Lcom/amazon/video/sdk/player/AdditionalPlayerConfigs;", "additionalConfigs", "Lcom/amazon/video/sdk/player/AdditionalPlayerConfigs;", "getAdditionalConfigs", "()Lcom/amazon/video/sdk/player/AdditionalPlayerConfigs;", "<init>", "(Landroid/content/Context;Lcom/amazon/video/sdk/player/FeatureConfigs;Lcom/amazon/video/sdk/player/AdditionalPlayerConfigs;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class PlayerConfigData implements PlayerConfig {
    private final AdditionalPlayerConfigs additionalConfigs;
    private final Context context;
    private final FeatureConfigs features;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayerConfigData)) {
            return false;
        }
        PlayerConfigData playerConfigData = (PlayerConfigData) other;
        return Intrinsics.areEqual(getContext(), playerConfigData.getContext()) && Intrinsics.areEqual(getFeatures(), playerConfigData.getFeatures()) && Intrinsics.areEqual(getAdditionalConfigs(), playerConfigData.getAdditionalConfigs());
    }

    public int hashCode() {
        return (((getContext().hashCode() * 31) + (getFeatures() == null ? 0 : getFeatures().hashCode())) * 31) + getAdditionalConfigs().hashCode();
    }

    public String toString() {
        return "PlayerConfigData(context=" + getContext() + ", features=" + getFeatures() + ", additionalConfigs=" + getAdditionalConfigs() + ')';
    }

    @JvmOverloads
    public PlayerConfigData(Context context, FeatureConfigs featureConfigs, AdditionalPlayerConfigs additionalConfigs) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(additionalConfigs, "additionalConfigs");
        this.context = context;
        this.features = featureConfigs;
        this.additionalConfigs = additionalConfigs;
    }

    @Override // com.amazon.video.sdk.player.PlayerConfig
    public Context getContext() {
        return this.context;
    }

    @Override // com.amazon.video.sdk.player.PlayerConfig
    public FeatureConfigs getFeatures() {
        return this.features;
    }

    @Override // com.amazon.video.sdk.player.PlayerConfig
    public AdditionalPlayerConfigs getAdditionalConfigs() {
        return this.additionalConfigs;
    }
}
