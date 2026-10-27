package com.amazon.music.station;

import androidx.annotation.NonNull;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.marketplace.Marketplace;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public final class Configuration {
    private final StationContentSelector contentSelector;
    private final String deviceId;
    private final String deviceType;
    private final String language;
    private final Marketplace marketplace;
    private final String musicTerritory;
    private final RequestInterceptor requestInterceptor;
    private final URL stationBrowseURL;
    private final URL stationURL;

    private Configuration(Marketplace marketplace, String str, @NonNull String str2, @NonNull String str3, @NonNull URL url, @NonNull URL url2, @NonNull RequestInterceptor requestInterceptor, @NonNull StationContentSelector stationContentSelector, String str4) {
        if (marketplace == null && StringUtils.isEmpty(str)) {
            throw null;
        }
        Validate.notBlank(str2);
        Validate.notBlank(str3);
        Validate.notNull(url);
        Validate.notNull(url2);
        Validate.notNull(requestInterceptor);
        Validate.notNull(stationContentSelector);
        this.marketplace = marketplace;
        this.musicTerritory = str;
        this.deviceId = str2;
        this.deviceType = str3;
        this.stationURL = url;
        this.stationBrowseURL = url2;
        this.requestInterceptor = requestInterceptor;
        this.contentSelector = stationContentSelector;
        this.language = str4;
    }

    public static class Builder {
        private final StationContentSelector contentSelector;
        private final String deviceId;
        private final String deviceType;
        private final String musicTerritory;
        private final RequestInterceptor requestInterceptor;
        private final URL stationBrowseURL;
        private final URL stationURL;
        private Marketplace marketplace = null;
        private String language = null;

        public Builder(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull URL url, @NonNull URL url2, @NonNull RequestInterceptor requestInterceptor, @NonNull StationContentSelector stationContentSelector) {
            this.musicTerritory = str;
            this.deviceId = str2;
            this.deviceType = str3;
            this.stationURL = url;
            this.stationBrowseURL = url2;
            this.requestInterceptor = requestInterceptor;
            this.contentSelector = stationContentSelector;
        }

        public Builder withLanguage(@NonNull String str) {
            this.language = str;
            return this;
        }

        public Configuration build() {
            return new Configuration(this.marketplace, this.musicTerritory, this.deviceId, this.deviceType, this.stationURL, this.stationBrowseURL, this.requestInterceptor, this.contentSelector, this.language);
        }
    }

    Marketplace getMarketplace() {
        return this.marketplace;
    }

    String getMusicTerritory() {
        return this.musicTerritory;
    }

    String getDeviceId() {
        return this.deviceId;
    }

    String getDeviceType() {
        return this.deviceType;
    }

    URL getStationURL() {
        return this.stationURL;
    }

    URL getstationBrowseURL() {
        return this.stationBrowseURL;
    }

    RequestInterceptor getRequestInterceptor() {
        return this.requestInterceptor;
    }

    StationContentSelector getContentSelector() {
        return this.contentSelector;
    }

    String getLanguage() {
        return this.language;
    }
}
