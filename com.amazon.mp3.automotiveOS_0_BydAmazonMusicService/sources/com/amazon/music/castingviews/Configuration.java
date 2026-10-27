package com.amazon.music.castingviews;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.casting.session.CastingSessionConfig;
import com.amazon.music.marketplace.Marketplace;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class Configuration {
    private static boolean isDebugMode;
    private final Context activityContext;
    private final boolean alexaDevicesEnabled;
    private final CastingSessionConfig castingSessionConfig;
    private final boolean chromecastDevicesEnabled;
    private final String demoWarningMessage;
    private final String deviceId;
    private final String disabledReason;
    private final boolean hasTracksLoaded;
    private final String helpUrl;
    private final boolean isDemoMode;
    private final Marketplace marketplace;
    private final RequestInterceptor requestInterceptor;
    private final boolean showAlexaDevices;
    private final boolean showChromecastDevices;
    private final boolean wrapDeviceName;

    private Configuration(Context context, CastingSessionConfig castingSessionConfig, RequestInterceptor requestInterceptor, Marketplace marketplace, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String str, String str2, String str3, String str4) {
        this.activityContext = context;
        this.castingSessionConfig = castingSessionConfig;
        this.requestInterceptor = requestInterceptor;
        this.marketplace = marketplace;
        this.showChromecastDevices = z;
        this.showAlexaDevices = z2;
        this.chromecastDevicesEnabled = z3;
        this.alexaDevicesEnabled = z4;
        this.hasTracksLoaded = z5;
        this.disabledReason = str2;
        this.helpUrl = str3;
        this.deviceId = str4;
        this.isDemoMode = z6;
        this.wrapDeviceName = z7;
        this.demoWarningMessage = str;
    }

    public CastingSessionConfig getCastingSessionConfig() {
        return this.castingSessionConfig;
    }

    public RequestInterceptor getRequestInterceptor() {
        return this.requestInterceptor;
    }

    public Marketplace getMarketplace() {
        return this.marketplace;
    }

    public Context getActivityContext() {
        return this.activityContext;
    }

    public boolean showChromecastDevices() {
        return this.showChromecastDevices;
    }

    public boolean showAlexaDevices() {
        return this.showAlexaDevices;
    }

    public boolean chromecastDevicesEnabled() {
        return this.chromecastDevicesEnabled;
    }

    public boolean alexaDevicesEnabled() {
        return this.alexaDevicesEnabled;
    }

    public boolean demoModeEnabled() {
        return this.isDemoMode;
    }

    public boolean wrapDeviceNameEnabled() {
        return this.wrapDeviceName;
    }

    public String getDemoWarningMessage() {
        return this.demoWarningMessage;
    }

    public String getDisabledReason() {
        return this.disabledReason;
    }

    public boolean hasTracksLoaded() {
        return this.hasTracksLoaded;
    }

    public String getHelpUrl() {
        return this.helpUrl;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public static boolean isDebugMode() {
        return isDebugMode;
    }

    public static Builder builder(@NonNull Context context, @NonNull CastingSessionConfig castingSessionConfig, @NonNull RequestInterceptor requestInterceptor, @NonNull Marketplace marketplace) {
        return new Builder(context, castingSessionConfig, requestInterceptor, marketplace);
    }

    public static class Builder {
        private final Context activityContext;
        private boolean alexaDevicesEnabled;
        private CastingSessionConfig castingSessionConfig;
        private boolean chromecastDevicesEnabled;
        private String demoWarningMessage;
        private String deviceId;
        private String disabledReason;
        private boolean hasTracksLoaded;
        private String helpUrl;
        private boolean isDemoMode;
        private Marketplace marketplace;
        private RequestInterceptor requestInterceptor;
        private boolean showAlexaDevices;
        private boolean showChromecastDevices;
        private boolean wrapDeviceName;

        private Builder(Context context, CastingSessionConfig castingSessionConfig, RequestInterceptor requestInterceptor, Marketplace marketplace) {
            this.demoWarningMessage = "";
            Validate.notNull(context, "context cannot be null", new Object[0]);
            Validate.notNull(requestInterceptor, "requestInterceptor cannot be null", new Object[0]);
            Validate.notNull(marketplace, "marketplace cannot be null", new Object[0]);
            Validate.notNull(castingSessionConfig, "castingSessionConfig cannot be null", new Object[0]);
            this.activityContext = context;
            this.showChromecastDevices = true;
            this.showAlexaDevices = true;
            this.chromecastDevicesEnabled = true;
            this.alexaDevicesEnabled = true;
            this.castingSessionConfig = castingSessionConfig;
            this.requestInterceptor = requestInterceptor;
            this.marketplace = marketplace;
            this.hasTracksLoaded = false;
            this.deviceId = "";
            this.wrapDeviceName = false;
        }

        public Builder withChromecastDevices(boolean z) {
            this.showChromecastDevices = z;
            return this;
        }

        public Builder withAlexaDevices(boolean z) {
            this.showAlexaDevices = z;
            return this;
        }

        public Builder withHelpUrl(String str) {
            this.helpUrl = str;
            return this;
        }

        public Builder disableAlexaDevices(String str) {
            this.alexaDevicesEnabled = false;
            this.disabledReason = str;
            return this;
        }

        public Builder hasTracksLoaded() {
            this.hasTracksLoaded = true;
            return this;
        }

        public Builder withCurrentDeviceId(String str) {
            this.deviceId = str;
            return this;
        }

        public Builder withDemoMode(boolean z) {
            this.isDemoMode = z;
            return this;
        }

        public Builder withWrapDeviceName(boolean z) {
            this.wrapDeviceName = z;
            return this;
        }

        public Builder withDemoModeWarningMessage(String str) {
            this.demoWarningMessage = str;
            return this;
        }

        public Configuration build() {
            return new Configuration(this.activityContext, this.castingSessionConfig, this.requestInterceptor, this.marketplace, this.showChromecastDevices, this.showAlexaDevices, this.chromecastDevicesEnabled, this.alexaDevicesEnabled, this.hasTracksLoaded, this.isDemoMode, this.wrapDeviceName, this.demoWarningMessage, this.disabledReason, this.helpUrl, this.deviceId);
        }
    }
}
