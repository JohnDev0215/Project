package com.amazon.mp3.cloudqueue;

import androidx.annotation.NonNull;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.marketplace.Marketplace;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public final class Configuration {
    private final String deviceId;
    private final String deviceType;
    private final String language;
    private final Marketplace marketplace;
    private final URL mpqsV2Url;
    private final String musicTerritory;
    private final RequestInterceptor requestInterceptor;

    private Configuration(final Marketplace marketplace, final String musicTerritory, @NonNull final String deviceId, @NonNull final String deviceType, @NonNull final URL mpqsV2Url, @NonNull final RequestInterceptor requestInterceptor, final String language) {
        if (marketplace == null && StringUtils.isEmpty(musicTerritory)) {
            throw null;
        }
        Validate.notBlank(deviceId);
        Validate.notBlank(deviceType);
        Validate.notNull(mpqsV2Url);
        this.marketplace = marketplace;
        this.musicTerritory = musicTerritory;
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.mpqsV2Url = mpqsV2Url;
        this.requestInterceptor = requestInterceptor;
        this.language = language;
    }

    public static class Builder {
        private final String deviceId;
        private final String deviceType;
        private final URL mpqsV2Url;
        private final String musicTerritory;
        private final RequestInterceptor requestInterceptor;
        private Marketplace marketplace = null;
        private String language = null;

        public Builder(@NonNull final String musicTerritory, @NonNull final String deviceId, @NonNull final String deviceType, @NonNull final URL mpqsV2Url, @NonNull final RequestInterceptor requestInterceptor) {
            this.musicTerritory = musicTerritory;
            this.deviceId = deviceId;
            this.deviceType = deviceType;
            this.mpqsV2Url = mpqsV2Url;
            this.requestInterceptor = requestInterceptor;
        }

        public Builder withLanguage(@NonNull final String language) {
            this.language = language;
            return this;
        }

        public Configuration build() {
            return new Configuration(this.marketplace, this.musicTerritory, this.deviceId, this.deviceType, this.mpqsV2Url, this.requestInterceptor, this.language);
        }
    }

    URL getMpqsUrl() {
        return this.mpqsV2Url;
    }

    RequestInterceptor getRequestInterceptor() {
        return this.requestInterceptor;
    }
}
