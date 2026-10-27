package com.amazon.mp3.ondemandplaylists.service;

import com.amazon.mls.config.internal.core.configuration.Region;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: UsherEndpointFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\tj\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/ondemandplaylists/service/UsherEndpointFactory;", "", "()V", "DEFAULT_ENDPOINT", "", "USHER_SERVICE_EU_ENDPOINT", "USHER_SERVICE_FE_ENDPOINT", "USHER_SERVICE_NA_ENDPOINT", "USHER_SERVICE_REGION_ENDPOINT_MAP", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getUsherServiceEndpoint", "region", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UsherEndpointFactory {
    private static final String TAG = Reflection.getOrCreateKotlinClass(UsherEndpointFactory.class).getSimpleName();
    private final String DEFAULT_ENDPOINT;
    private final String USHER_SERVICE_EU_ENDPOINT;
    private final String USHER_SERVICE_FE_ENDPOINT;
    private final String USHER_SERVICE_NA_ENDPOINT;
    private final HashMap<String, String> USHER_SERVICE_REGION_ENDPOINT_MAP;

    public UsherEndpointFactory() {
        HashMap<String, String> map = new HashMap<>();
        this.USHER_SERVICE_REGION_ENDPOINT_MAP = map;
        this.USHER_SERVICE_NA_ENDPOINT = "https://3a0hz1nlki.execute-api.us-east-1.amazonaws.com/prod";
        this.USHER_SERVICE_EU_ENDPOINT = "https://is5dk7kxj8.execute-api.eu-west-1.amazonaws.com/prod";
        this.USHER_SERVICE_FE_ENDPOINT = "https://vr9xqkvt65.execute-api.us-west-2.amazonaws.com/prod";
        this.DEFAULT_ENDPOINT = "https://3a0hz1nlki.execute-api.us-east-1.amazonaws.com/prod";
        map.put(Region.NA.name(), "https://3a0hz1nlki.execute-api.us-east-1.amazonaws.com/prod");
        map.put(Region.EU.name(), "https://is5dk7kxj8.execute-api.eu-west-1.amazonaws.com/prod");
        map.put(Region.FE.name(), "https://vr9xqkvt65.execute-api.us-west-2.amazonaws.com/prod");
    }

    public final String getUsherServiceEndpoint(String region) {
        String str = this.USHER_SERVICE_REGION_ENDPOINT_MAP.get(region);
        return str == null ? this.DEFAULT_ENDPOINT : str;
    }
}
