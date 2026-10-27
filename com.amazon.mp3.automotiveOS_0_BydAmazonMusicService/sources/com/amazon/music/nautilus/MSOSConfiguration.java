package com.amazon.music.nautilus;

import com.amazon.hermes.RequestInterceptor;
import java.net.URL;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class MSOSConfiguration {
    private final String deviceId;
    private final String deviceType;
    private final String musicTerritory;
    private final RequestInterceptor requestInterceptor;
    private final URL url;

    public MSOSConfiguration(Builder builder) {
        this.deviceId = builder.deviceId;
        this.deviceType = builder.deviceType;
        this.requestInterceptor = builder.requestInterceptor;
        this.musicTerritory = builder.musicTerritory;
        this.url = builder.url;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public RequestInterceptor getRequestInterceptor() {
        return this.requestInterceptor;
    }

    public URL getUrl() {
        return this.url;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public static final class Builder {
        private final String deviceId;
        private final String deviceType;
        private final String musicTerritory;
        private final RequestInterceptor requestInterceptor;
        private final URL url;

        public Builder(String str, String str2, String str3, URL url, RequestInterceptor requestInterceptor) {
            this.deviceId = (String) Validate.notEmpty(str, "deviceId cannot be null or empty", new Object[0]);
            this.deviceType = (String) Validate.notEmpty(str2, "deviceType cannot be null or empty", new Object[0]);
            this.musicTerritory = (String) Validate.notEmpty(str3, "Music territory cannot be null", new Object[0]);
            this.url = (URL) Validate.notNull(url, "musicSubscriptionUrl cannot be null", new Object[0]);
            this.requestInterceptor = (RequestInterceptor) Validate.notNull(requestInterceptor, "requestInterceptor cannot be null", new Object[0]);
        }
    }
}
