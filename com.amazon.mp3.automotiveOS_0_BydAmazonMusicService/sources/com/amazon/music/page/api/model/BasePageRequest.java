package com.amazon.music.page.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BasePageRequest {
    private final String browseId;
    private final int count;
    private final String customerId;
    private final String deviceId;
    private final String deviceType;
    private final List<String> featureFlags;
    private final boolean isExplicitLanguageFilterEnabled;
    private final String locale;
    private final String mricToken;
    private final String musicTerritory;
    private final String nextToken;
    private final String region;
    private final String requestedContent;
    private final String stage;
    private final String uri;
    private String widgetId;

    BasePageRequest(Builder<?> builder) {
        this.customerId = ((Builder) builder).customerId;
        this.deviceId = ((Builder) builder).deviceId;
        this.deviceType = ((Builder) builder).deviceType;
        this.musicTerritory = ((Builder) builder).musicTerritory;
        this.requestedContent = ((Builder) builder).requestedContent;
        this.uri = ((Builder) builder).uri;
        this.browseId = ((Builder) builder).browseId;
        this.nextToken = ((Builder) builder).nextToken;
        this.count = ((Builder) builder).count;
        this.isExplicitLanguageFilterEnabled = ((Builder) builder).isExplicitLanguageFilterEnabled;
        this.region = ((Builder) builder).region;
        this.stage = ((Builder) builder).stage == null ? "prod" : ((Builder) builder).stage;
        this.locale = ((Builder) builder).locale == null ? null : ((Builder) builder).locale.toString();
        this.mricToken = ((Builder) builder).mricToken;
        this.featureFlags = builder.featureFlags;
        this.widgetId = ((Builder) builder).widgetId;
    }

    public String getLocale() {
        return this.locale;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public int getCount() {
        return this.count;
    }

    public boolean isExplicitLanguageFilterEnabled() {
        return this.isExplicitLanguageFilterEnabled;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getUri() {
        return this.uri;
    }

    public String getBrowseId() {
        return this.browseId;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public String getRequestedContent() {
        return this.requestedContent;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getRegion() {
        return this.region;
    }

    public String getStage() {
        return this.stage;
    }

    public String getMusicRequestIdentityContextToken() {
        return this.mricToken;
    }

    public List<String> getFeatureFlags() {
        return this.featureFlags;
    }

    public String getWidgetId() {
        return this.widgetId;
    }

    public void setWidgetId(String str) {
        this.widgetId = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static abstract class Builder<T extends Builder> {
        private String browseId;
        private int count;
        private final String customerId;
        private final String deviceId;
        private final String deviceType;
        protected final List<String> featureFlags = new ArrayList();
        private boolean isExplicitLanguageFilterEnabled;
        private Locale locale;
        private String mricToken;
        private final String musicTerritory;
        private String nextToken;
        private String region;
        private final String requestedContent;
        private String stage;
        private final String uri;
        private String widgetId;

        protected abstract T self();

        Builder(String str, String str2, String str3, String str4, String str5, String str6) {
            this.customerId = str;
            this.deviceId = str2;
            this.deviceType = str3;
            this.musicTerritory = str4;
            this.requestedContent = str5;
            this.uri = str6;
        }

        public T withBrowseId(String str) {
            this.browseId = str;
            return (T) self();
        }

        public T withNextToken(String str) {
            this.nextToken = str;
            return (T) self();
        }

        public T withCount(int i) {
            this.count = i;
            return (T) self();
        }

        public T setIsExplicitLanguageFilterEnabled(boolean z) {
            this.isExplicitLanguageFilterEnabled = z;
            return (T) self();
        }

        public T withLocale(Locale locale) {
            this.locale = locale;
            return (T) self();
        }

        public T withRegion(String str) {
            this.region = str;
            return (T) self();
        }

        public T withStage(String str) {
            this.stage = str;
            return (T) self();
        }

        public T setMusicRequestIdentityContextToken(String str) {
            this.mricToken = str;
            return (T) self();
        }

        public T withWidgetId(String str) {
            this.widgetId = str;
            return (T) self();
        }
    }
}
