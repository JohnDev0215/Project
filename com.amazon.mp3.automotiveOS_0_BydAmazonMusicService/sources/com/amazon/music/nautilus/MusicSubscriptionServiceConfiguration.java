package com.amazon.music.nautilus;

import com.amazon.hermes.RequestInterceptor;
import java.net.URL;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class MusicSubscriptionServiceConfiguration {
    private final String deviceId;
    private final String deviceType;
    private final boolean disableInitialUpdate;
    private final URL musicSubscriptionUrl;
    private final String musicTerritory;
    private final RequestInterceptor requestInterceptor;

    private MusicSubscriptionServiceConfiguration(Builder builder) {
        this.deviceId = builder.deviceId;
        this.deviceType = builder.deviceType;
        this.musicTerritory = builder.musicTerritory;
        this.musicSubscriptionUrl = builder.musicSubscriptionUrl;
        this.requestInterceptor = builder.requestInterceptor;
        this.disableInitialUpdate = builder.disableInitialUpdate;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public URL getMusicSubscriptionUrl() {
        return this.musicSubscriptionUrl;
    }

    public RequestInterceptor getRequestInterceptor() {
        return this.requestInterceptor;
    }

    public boolean shouldDisableInitialUpdate() {
        return this.disableInitialUpdate;
    }

    public static final class Builder {
        private final String deviceId;
        private final String deviceType;
        private boolean disableInitialUpdate = false;
        private final URL musicSubscriptionUrl;
        private final String musicTerritory;
        private final RequestInterceptor requestInterceptor;

        public Builder withInitialUpdateDisabled() {
            this.disableInitialUpdate = true;
            return this;
        }

        public Builder(String str, String str2, String str3, URL url, RequestInterceptor requestInterceptor) {
            this.deviceId = (String) Validate.notEmpty(str, "deviceId cannot be null or empty", new Object[0]);
            this.deviceType = (String) Validate.notEmpty(str2, "deviceType cannot be null or empty", new Object[0]);
            this.musicTerritory = (String) Validate.notEmpty(str3, "musicTerritory cannot be null or empty", new Object[0]);
            this.musicSubscriptionUrl = (URL) Validate.notNull(url, "musicSubscriptionUrl cannot be null", new Object[0]);
            this.requestInterceptor = (RequestInterceptor) Validate.notNull(requestInterceptor, "requestInterceptor cannot be null", new Object[0]);
        }

        public MusicSubscriptionServiceConfiguration build() {
            return new MusicSubscriptionServiceConfiguration(this);
        }
    }
}
