package com.amazon.music.browse;

import com.amazon.hermes.RequestInterceptor;
import java.net.URL;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public final class Configuration {
    private final URL getHomeURL;
    private final URL museURL;
    private final RequestInterceptor requestInterceptor;
    private final URL trackLookupURL;

    private Configuration(RequestInterceptor requestInterceptor, URL url, URL url2, URL url3) {
        this.requestInterceptor = requestInterceptor;
        this.trackLookupURL = url;
        this.getHomeURL = url2;
        this.museURL = url3;
    }

    public RequestInterceptor getRequestInterceptor() {
        return this.requestInterceptor;
    }

    public URL getTrackLookupURL() {
        return this.trackLookupURL;
    }

    public URL getHomeURL() {
        return this.getHomeURL;
    }

    public URL getMuseURL() {
        return this.museURL;
    }

    public static final class Builder {
        private URL getHomeURL;
        private URL museURL;
        private RequestInterceptor requestInterceptor;
        private URL trackLookupURL;

        public Builder withRequestInterceptor(RequestInterceptor requestInterceptor) {
            this.requestInterceptor = requestInterceptor;
            return this;
        }

        public Builder withTrackLookupURL(URL url) {
            this.trackLookupURL = url;
            return this;
        }

        public Builder withGetHomeURL(URL url) {
            this.getHomeURL = url;
            return this;
        }

        public Builder withMuseURL(URL url) {
            this.museURL = url;
            return this;
        }

        public Configuration build() {
            Validate.notNull(this.requestInterceptor, "requestInterceptor cannot be null", new Object[0]);
            return new Configuration(this.requestInterceptor, this.trackLookupURL, this.getHomeURL, this.museURL);
        }
    }
}
