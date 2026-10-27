package com.amazon.music.media.playback.config;

import android.os.Build;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.media.playback.util.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class ClientIdentification {
    private final String appVersion;
    private final String clientID;
    private final String customerID;
    private final String customerServiceTier;
    private final String deviceCategory;
    private final String deviceDescription;
    private final String deviceID;
    private final String deviceTypeID;
    private final String marketplaceID;
    private final String osVersion;
    private final RequestInterceptor requestInterceptor;
    private final String territoryID;
    private final boolean useGammaEndpoints;

    private ClientIdentification(Builder builder) {
        this.deviceTypeID = (String) Validate.notEmpty(builder.deviceTypeID, "The client must include a non-empty device type ID", new Object[0]);
        this.deviceID = (String) Validate.notEmpty(builder.deviceID, "The client must include a non-empty device ID", new Object[0]);
        this.marketplaceID = (String) Validate.notEmpty(builder.marketplaceID, "The client must include a non-empty obfuscated marketplace ID", new Object[0]);
        this.territoryID = (String) Validate.notEmpty(builder.territoryID, "The client must include a non-empty territory ID", new Object[0]);
        this.clientID = (String) Validate.notNull(builder.clientID, "The client must include a non-null client ID", new Object[0]);
        this.appVersion = (String) Validate.notNull(builder.appVersion, "The client must include a non-null app version", new Object[0]);
        this.deviceDescription = (String) Validate.notNull(builder.appVersion, "The client must include a non-null device description", new Object[0]);
        this.deviceCategory = (String) Validate.notNull(builder.appVersion, "The client must include a non-null device category", new Object[0]);
        this.customerServiceTier = (String) Validate.notNull(builder.customerServiceTier, "The client must include a non-null customer service tier", new Object[0]);
        this.requestInterceptor = (RequestInterceptor) Validate.notNull(builder.requestInterceptor, "The client must include a non-empty request interceptor", new Object[0]);
        this.customerID = StringUtils.nullToEmpty(builder.customerID);
        this.osVersion = builder.osVersion != null ? builder.osVersion : Build.VERSION.RELEASE;
        this.useGammaEndpoints = builder.useGammaEndpoints;
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public String getDeviceTypeID() {
        return this.deviceTypeID;
    }

    public String getDeviceID() {
        return this.deviceID;
    }

    public String getClientID() {
        return this.clientID;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getMarketplaceID() {
        return this.marketplaceID;
    }

    public String getTerritoryID() {
        return this.territoryID;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getCustomerServiceTier() {
        return this.customerServiceTier;
    }

    public boolean useGammaEndpoints() {
        return this.useGammaEndpoints;
    }

    public RequestInterceptor getRequestInterceptor() {
        return this.requestInterceptor;
    }

    public static class Builder {
        private String appVersion;
        private String clientID;
        private String customerID;
        private String customerServiceTier;
        private String deviceCategory;
        private String deviceDescription;
        private String deviceID;
        private String deviceTypeID;
        private String marketplaceID;
        private String osVersion;
        private RequestInterceptor requestInterceptor;
        private String territoryID;
        private boolean useGammaEndpoints;

        public ClientIdentification build() {
            return new ClientIdentification(this);
        }

        public Builder setCustomerID(String str) {
            this.customerID = str;
            return this;
        }

        public Builder setDeviceTypeID(String str) {
            this.deviceTypeID = str;
            return this;
        }

        public Builder setDeviceID(String str) {
            this.deviceID = str;
            return this;
        }

        public Builder setClientID(String str) {
            this.clientID = str;
            return this;
        }

        public Builder setAppVersion(String str) {
            this.appVersion = str;
            return this;
        }

        public Builder setMarketplaceID(String str) {
            this.marketplaceID = str;
            return this;
        }

        public Builder setTerritoryID(String str) {
            this.territoryID = str;
            return this;
        }

        public Builder setOsVersion(String str) {
            this.osVersion = str;
            return this;
        }

        public Builder setDeviceDescription(String str) {
            this.deviceDescription = str;
            return this;
        }

        public Builder setDeviceCategory(String str) {
            this.deviceCategory = str;
            return this;
        }

        public Builder setCustomerServiceTier(String str) {
            this.customerServiceTier = str;
            return this;
        }

        public Builder setRequestInterceptor(RequestInterceptor requestInterceptor) {
            this.requestInterceptor = requestInterceptor;
            return this;
        }
    }
}
