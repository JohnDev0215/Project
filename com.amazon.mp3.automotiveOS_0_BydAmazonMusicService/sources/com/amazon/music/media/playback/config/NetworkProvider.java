package com.amazon.music.media.playback.config;

import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class NetworkProvider {
    private final String name;
    private static final Map<String, NetworkProvider> providers = new HashMap();
    public static final NetworkProvider WIFI = forCarrier("WIFI");
    public static final NetworkProvider WIRED = forCarrier("WIRED");
    public static final NetworkProvider CACHED = forCarrier("LOCAL");
    public static final NetworkProvider SD_CARD = forCarrier("SD_CARD");
    public static final NetworkProvider UNKNOWN = forCarrier("UNKNOWN");

    public static NetworkProvider forCarrier(@NonNull String str) {
        NetworkProvider networkProvider;
        Validate.notEmpty(str);
        Map<String, NetworkProvider> map = providers;
        synchronized (map) {
            networkProvider = map.get(str);
            if (networkProvider == null) {
                networkProvider = new NetworkProvider(str);
                map.put(str, networkProvider);
            }
        }
        return networkProvider;
    }

    private NetworkProvider(String str) {
        this.name = (String) Validate.notEmpty(str);
    }

    public String getMetricsName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
