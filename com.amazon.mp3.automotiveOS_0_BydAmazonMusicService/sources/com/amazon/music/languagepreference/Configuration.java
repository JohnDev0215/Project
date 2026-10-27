package com.amazon.music.languagepreference;

import UpsellInterface.v1_0.UpsellType;
import android.content.Context;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;
import java.util.Locale;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class Configuration {
    private final Context context;
    private final String deviceId;
    private final String deviceType;
    private final String displayLanguage;
    private final URL museUrl;
    private final String musicTerritory;
    private final RequestContent requestContent;
    private final RequestInterceptor requestInterceptor;
    private final URL stratusUrl;

    public enum RequestContent {
        PRIME(UpsellType.PRIME),
        MUSIC_SUBSCRIPTION("MUSIC_SUBSCRIPTION");

        private final String content;

        RequestContent(String str) {
            this.content = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.content;
        }
    }

    public Configuration(Context context, URL url, URL url2, RequestInterceptor requestInterceptor, String str, String str2, String str3, Locale locale, RequestContent requestContent) {
        Validate.notNull(context, "context cannot be null", new Object[0]);
        Validate.notNull(requestInterceptor, "requestInterceptor cannot be null", new Object[0]);
        Validate.notNull(url, "stratus url cannot be null", new Object[0]);
        Validate.notNull(url2, "muse url cannot be null", new Object[0]);
        Validate.notBlank(str, "device id cannot be null", new Object[0]);
        Validate.notBlank(str2, "device type cannot be null", new Object[0]);
        Validate.notBlank(str3, "music territory cannot be null", new Object[0]);
        Validate.notNull(locale, "display language cannot be null", new Object[0]);
        Validate.notNull(requestContent, "request content cannot be null", new Object[0]);
        this.context = context;
        this.requestInterceptor = requestInterceptor;
        this.stratusUrl = url;
        this.museUrl = url2;
        this.deviceId = str;
        this.deviceType = str2;
        this.musicTerritory = str3;
        this.displayLanguage = locale.toString();
        this.requestContent = requestContent;
    }

    public Context getContext() {
        return this.context;
    }

    public RequestInterceptor getRequestInterceptor() {
        return this.requestInterceptor;
    }

    public URL getStratusUrl() {
        return this.stratusUrl;
    }

    public URL getMuseUrl() {
        return this.museUrl;
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

    public String getDisplayLanguage() {
        return this.displayLanguage;
    }

    public String getRequestContent() {
        return this.requestContent.toString();
    }
}
