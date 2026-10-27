package com.amazon.music.recommendation;

import com.amazon.hermes.RequestInterceptor;
import com.amazon.musicensembleservice.AllowedParentalControls;
import java.net.URL;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public final class Configuration {
    private final URL recommendationUrl;
    private final RequestInterceptor requestInterceptor;

    private Configuration(RequestInterceptor requestInterceptor, URL url) {
        this.requestInterceptor = requestInterceptor;
        this.recommendationUrl = url;
    }

    RequestInterceptor getRequestInterceptor() {
        return this.requestInterceptor;
    }

    URL getRecommendationUrl() {
        return this.recommendationUrl;
    }

    public static final class Builder {
        private URL recommendationUrl;
        private RequestInterceptor requestInterceptor;

        public Builder withRequestInterceptor(RequestInterceptor requestInterceptor) {
            Validate.notNull(requestInterceptor, "requestInterceptor can't be null", new Object[0]);
            this.requestInterceptor = requestInterceptor;
            return this;
        }

        public Builder withRecommendationURL(URL url) {
            Validate.notNull(url, "recommendationUrl can't be null", new Object[0]);
            this.recommendationUrl = url;
            return this;
        }

        public Configuration build() {
            return new Configuration(this.requestInterceptor, this.recommendationUrl);
        }
    }

    protected static AllowedParentalControls getParentalControls(boolean z) {
        AllowedParentalControls allowedParentalControls = new AllowedParentalControls();
        allowedParentalControls.setHasExplicitLanguage(Boolean.valueOf(!z));
        return allowedParentalControls;
    }
}
