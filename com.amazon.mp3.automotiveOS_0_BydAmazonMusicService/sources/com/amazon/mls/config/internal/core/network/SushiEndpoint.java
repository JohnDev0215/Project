package com.amazon.mls.config.internal.core.network;

import com.amazon.mls.config.internal.core.configuration.Region;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes3.dex */
public enum SushiEndpoint {
    NA("unagi-na.amazon.com", "na"),
    EU("unagi-eu.amazon.com", "eu"),
    FE("unagi-fe.amazon.com", "fe"),
    CN("unagi.amazon.cn", "cn");

    private static final String EVENTS_PREFIX = "/1/events/";
    private static final int PORT = 443;
    private static final String PROTOCOL = "https";
    private final String endpoint;
    private final String region;

    public int getPort() {
        return PORT;
    }

    public String getProtocol() {
        return PROTOCOL;
    }

    SushiEndpoint(String str, String str2) {
        this.endpoint = str;
        this.region = str2;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public String getRegion() {
        return this.region;
    }

    public URL getURL(String str) throws MalformedURLException {
        return new URL(PROTOCOL, this.endpoint, PORT, EVENTS_PREFIX + str);
    }

    /* JADX INFO: renamed from: com.amazon.mls.config.internal.core.network.SushiEndpoint$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mls$config$internal$core$configuration$Region;

        static {
            int[] iArr = new int[Region.values().length];
            $SwitchMap$com$amazon$mls$config$internal$core$configuration$Region = iArr;
            try {
                iArr[Region.NA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mls$config$internal$core$configuration$Region[Region.EU.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mls$config$internal$core$configuration$Region[Region.FE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mls$config$internal$core$configuration$Region[Region.CN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static SushiEndpoint getEndpointFromRegion(Region region) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mls$config$internal$core$configuration$Region[region.ordinal()];
        if (i == 1) {
            return NA;
        }
        if (i == 2) {
            return EU;
        }
        if (i == 3) {
            return FE;
        }
        if (i == 4) {
            return CN;
        }
        return NA;
    }
}
