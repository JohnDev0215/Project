package com.amazon.music.chromecast;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.WeblabProvider;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class CustomData {
    private String chromecastDeviceId;
    private String clientAppVersion;
    private String clientId;
    private final ChromecastController controller;
    private String customerId;
    private String deviceId;
    private String deviceTypeId;

    @Nullable
    private List<String> enabledTrackTypes;
    private String itemId;
    private String locale;
    private String marketplaceRegion;
    private String token;

    public String getStatsVersion() {
        return "Android/1";
    }

    private CustomData(ChromecastController chromecastController) {
        this.controller = chromecastController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setToken(String str) {
        this.token = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceTypeId(String str) {
        this.deviceTypeId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setClientId(String str) {
        this.clientId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setClientAppVersion(String str) {
        this.clientAppVersion = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setItemId(String str) {
        this.itemId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMarketplaceRegion(String str) {
        this.marketplaceRegion = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChromecastDeviceId(String str) {
        this.chromecastDeviceId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEnabledTrackTypes(@Nullable List<String> list) {
        this.enabledTrackTypes = list;
    }

    void setLocale(@Nullable String str) {
        this.locale = str;
    }

    public Boolean getNormalizeLoudness() {
        return Boolean.valueOf(this.controller.normalizeLoudness());
    }

    @Nullable
    public String getDeveloperFlag() {
        WeblabTreatment treatment;
        WeblabProvider weblabProvider = (WeblabProvider) Providers.INSTANCE.get(WeblabProvider.class);
        if (weblabProvider == null || (treatment = weblabProvider.getTreatment("DM_PBX_CHROMECAST_DEV_FLAG_579029", true)) == WeblabTreatment.C) {
            return null;
        }
        return treatment.getTreatment();
    }

    public JSONObject toJSONObject() throws JSONException {
        List<String> list = this.enabledTrackTypes;
        return new JSONObject().put("customerId", this.customerId).put(Splash.PARAMS_DEVICE_ID, this.deviceId).put("deviceTypeId", this.deviceTypeId).put("clientId", this.clientId).put("clientAppVersion", this.clientAppVersion).put("token", this.token).put("useLegacyHlsFallback", false).put("itemId", this.itemId).put("marketplaceRegion", this.marketplaceRegion).put("chromecastDeviceId", this.chromecastDeviceId).putOpt("chromecastModelName", this.controller.chromecastModelName()).putOpt("enabledTrackTypes", list != null ? new JSONArray((Collection<?>) list) : null).putOpt("normalizeLoudness", getNormalizeLoudness()).putOpt("statsVersion", getStatsVersion()).putOpt(Splash.PARAMS_LOCALE, this.locale).putOpt("developerFlag", getDeveloperFlag());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String chromecastDeviceId;
        private String clientAppVersion;
        private String clientId;
        private final ChromecastController controller;
        private String customerId;
        private String deviceId;
        private String deviceTypeId;

        @Nullable
        private List<String> enabledTrackTypes;
        private String itemId;
        private String marketplaceRegion;
        private String token;

        public Builder() {
            this(ChromecastController.getInstance());
        }

        @VisibleForTesting
        Builder(ChromecastController chromecastController) {
            this.controller = chromecastController;
        }

        public Builder withCustomerId(String str) {
            this.customerId = str;
            return this;
        }

        public Builder withDeviceId(String str) {
            this.deviceId = str;
            return this;
        }

        public Builder withDeviceTypeId(String str) {
            this.deviceTypeId = str;
            return this;
        }

        public Builder withClientId(String str) {
            this.clientId = str;
            return this;
        }

        public Builder withClientAppVersion(String str) {
            this.clientAppVersion = str;
            return this;
        }

        public Builder withToken(String str) {
            this.token = str;
            return this;
        }

        public Builder withItemId(String str) {
            this.itemId = str;
            return this;
        }

        public Builder withMarketplaceRegion(String str) {
            this.marketplaceRegion = str;
            return this;
        }

        public Builder withChromecastDeviceId(String str) {
            this.chromecastDeviceId = str;
            return this;
        }

        public Builder withEnabledTrackTypes(@Nullable List<String> list) {
            this.enabledTrackTypes = list;
            return this;
        }

        protected void populate(CustomData customData) {
            customData.setCustomerId(this.customerId);
            customData.setClientAppVersion(this.clientAppVersion);
            customData.setClientId(this.clientId);
            customData.setDeviceId(this.deviceId);
            customData.setDeviceTypeId(this.deviceTypeId);
            customData.setToken(this.token);
            customData.setItemId(this.itemId);
            customData.setMarketplaceRegion(this.marketplaceRegion);
            customData.setChromecastDeviceId(this.chromecastDeviceId);
            customData.setEnabledTrackTypes(this.enabledTrackTypes);
        }

        public CustomData build() {
            CustomData customData = new CustomData(this.controller);
            populate(customData);
            return customData;
        }
    }

    public static class Update {

        @Nullable
        private List<String> enabledTrackTypes;

        @Nullable
        private Boolean normalizeLoudness;

        private Update(@Nullable List<String> list, @Nullable Boolean bool) {
            this.enabledTrackTypes = list;
            this.normalizeLoudness = bool;
        }

        @Nullable
        public Boolean normalizeLoudness() {
            return this.normalizeLoudness;
        }

        public JSONObject toJSONObject() throws JSONException {
            List<String> list = this.enabledTrackTypes;
            return new JSONObject().putOpt("enabledTrackTypes", list != null ? new JSONArray((Collection<?>) list) : null).putOpt("normalizeLoudness", this.normalizeLoudness);
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {

            @Nullable
            private List<String> enabledTrackTypes;

            @Nullable
            private Boolean normalizeLoudness;

            public Builder withEnabledTrackTypes(@Nullable List<String> list) {
                this.enabledTrackTypes = list;
                return this;
            }

            public Builder withNormalizeLoudness(@Nullable Boolean bool) {
                this.normalizeLoudness = bool;
                return this;
            }

            public Update build() {
                return new Update(this.enabledTrackTypes, this.normalizeLoudness);
            }
        }
    }
}
