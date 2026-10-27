package com.amazon.music.platform.aom.hints;

import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AHSMarketplaceHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\b\tB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/music/platform/aom/hints/AHSMarketplaceHelper;", "", "()V", "rowMarketplaceMap", "", "", "getMarketplace", "originalMarketplace", "AHSMarketplace", "ROWMarketplace", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class AHSMarketplaceHelper {
    private final Map<String, String> rowMarketplaceMap;

    /* JADX INFO: compiled from: AHSMarketplaceHelper.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0010\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/aom/hints/AHSMarketplaceHelper$AHSMarketplace;", "", "(Ljava/lang/String;I)V", "US", "UK", "DE", "FR", "ES", "IN", "IT", "JP", "CA", "CN", "BR", "AU", "MX", "NL", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum AHSMarketplace {
        US,
        UK,
        DE,
        FR,
        ES,
        IN,
        IT,
        JP,
        CA,
        CN,
        BR,
        AU,
        MX,
        NL
    }

    /* JADX INFO: compiled from: AHSMarketplaceHelper.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/platform/aom/hints/AHSMarketplaceHelper$ROWMarketplace;", "", "(Ljava/lang/String;I)V", "CO", "CL", "AR", "PE", "NZ", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum ROWMarketplace {
        CO,
        CL,
        AR,
        PE,
        NZ
    }

    public AHSMarketplaceHelper() {
        String strName = ROWMarketplace.CO.name();
        AHSMarketplace aHSMarketplace = AHSMarketplace.MX;
        this.rowMarketplaceMap = MapsKt__MapsKt.mutableMapOf(TuplesKt.to(strName, aHSMarketplace.name()), TuplesKt.to(ROWMarketplace.CL.name(), aHSMarketplace.name()), TuplesKt.to(ROWMarketplace.AR.name(), aHSMarketplace.name()), TuplesKt.to(ROWMarketplace.PE.name(), aHSMarketplace.name()), TuplesKt.to(ROWMarketplace.NZ.name(), AHSMarketplace.AU.name()));
    }

    public final String getMarketplace(String originalMarketplace) {
        Intrinsics.checkNotNullParameter(originalMarketplace, "originalMarketplace");
        String str = this.rowMarketplaceMap.get(originalMarketplace);
        return str == null ? originalMarketplace : str;
    }
}
