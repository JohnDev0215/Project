package com.amazon.music.media.playback.video;

import com.amazon.music.marketplace.Marketplace;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ServiceEnvironmentProd.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00048TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/media/playback/video/ServiceEnvironmentProd;", "Lcom/amazon/music/media/playback/video/ServiceEnvironment;", "()V", "content", "", "", "getContent", "()Ljava/util/Map;", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ServiceEnvironmentProd extends ServiceEnvironment {
    public ServiceEnvironmentProd() {
        super("VideoServiceDelivery_Prod");
    }

    @Override // com.amazon.music.media.playback.video.ServiceEnvironment
    protected Map<String, String> getContent() {
        HashMap map = new HashMap();
        String obfuscatedId = Marketplace.USA.getObfuscatedId();
        Intrinsics.checkNotNullExpressionValue(obfuscatedId, "USA.obfuscatedId");
        map.put(obfuscatedId, "https://music.amazon.com/NA/api/dmvds/videoDelivery/playbackToken/");
        String obfuscatedId2 = Marketplace.UK.getObfuscatedId();
        Intrinsics.checkNotNullExpressionValue(obfuscatedId2, "UK.obfuscatedId");
        map.put(obfuscatedId2, "https://music.amazon.com/EU/api/dmvds/videoDelivery/playbackToken/");
        String obfuscatedId3 = Marketplace.GERMANY.getObfuscatedId();
        Intrinsics.checkNotNullExpressionValue(obfuscatedId3, "GERMANY.obfuscatedId");
        map.put(obfuscatedId3, "https://music.amazon.com/EU/api/dmvds/videoDelivery/playbackToken/");
        String obfuscatedId4 = Marketplace.JAPAN.getObfuscatedId();
        Intrinsics.checkNotNullExpressionValue(obfuscatedId4, "JAPAN.obfuscatedId");
        map.put(obfuscatedId4, "https://music.amazon.com/FE/api/dmvds/videoDelivery/playbackToken/");
        String obfuscatedId5 = Marketplace.FRANCE.getObfuscatedId();
        Intrinsics.checkNotNullExpressionValue(obfuscatedId5, "FRANCE.obfuscatedId");
        map.put(obfuscatedId5, "https://music.amazon.com/EU/api/dmvds/videoDelivery/playbackToken/");
        String obfuscatedId6 = Marketplace.ITALY.getObfuscatedId();
        Intrinsics.checkNotNullExpressionValue(obfuscatedId6, "ITALY.obfuscatedId");
        map.put(obfuscatedId6, "https://music.amazon.com/EU/api/dmvds/videoDelivery/playbackToken/");
        String obfuscatedId7 = Marketplace.SPAIN.getObfuscatedId();
        Intrinsics.checkNotNullExpressionValue(obfuscatedId7, "SPAIN.obfuscatedId");
        map.put(obfuscatedId7, "https://music.amazon.com/EU/api/dmvds/videoDelivery/playbackToken/");
        String obfuscatedId8 = Marketplace.ROW_NA.getObfuscatedId();
        Intrinsics.checkNotNullExpressionValue(obfuscatedId8, "ROW_NA.obfuscatedId");
        map.put(obfuscatedId8, "https://music.amazon.com/NA/api/dmvds/videoDelivery/playbackToken/");
        String obfuscatedId9 = Marketplace.ROE_EU.getObfuscatedId();
        Intrinsics.checkNotNullExpressionValue(obfuscatedId9, "ROE_EU.obfuscatedId");
        map.put(obfuscatedId9, "https://music.amazon.com/EU/api/dmvds/videoDelivery/playbackToken/");
        String obfuscatedId10 = Marketplace.ROW_FE.getObfuscatedId();
        Intrinsics.checkNotNullExpressionValue(obfuscatedId10, "ROW_FE.obfuscatedId");
        map.put(obfuscatedId10, "https://music.amazon.com/FE/api/dmvds/videoDelivery/playbackToken/");
        return map;
    }
}
