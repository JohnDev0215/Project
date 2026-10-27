package com.amazon.music.featuregating.configuration;

import com.amazon.music.inappmessaging.internal.model.Splash;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TerritoryInfo.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/amazon/music/featuregating/configuration/TerritoryInfo;", "", Splash.PARAMS_MUSIC_TERRITORY, "", "marketplaceId", "(Ljava/lang/String;Ljava/lang/String;)V", "getMarketplaceId", "()Ljava/lang/String;", "getMusicTerritory", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class TerritoryInfo {
    private final String marketplaceId;
    private final String musicTerritory;

    public TerritoryInfo(String musicTerritory, String marketplaceId) {
        Intrinsics.checkNotNullParameter(musicTerritory, "musicTerritory");
        Intrinsics.checkNotNullParameter(marketplaceId, "marketplaceId");
        this.musicTerritory = musicTerritory;
        this.marketplaceId = marketplaceId;
    }

    public final String getMarketplaceId() {
        return this.marketplaceId;
    }

    public final String getMusicTerritory() {
        return this.musicTerritory;
    }
}
