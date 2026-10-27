package com.amazon.music.social;

import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.marketplace.Marketplace;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class Configuration {
    private final RequestInterceptor mRequestInterceptor;
    private final Marketplace marketPlace;

    private Configuration(RequestInterceptor requestInterceptor, Marketplace marketplace) {
        this.mRequestInterceptor = requestInterceptor;
        this.marketPlace = marketplace;
    }

    public RequestInterceptor getRequestInterceptor() {
        return this.mRequestInterceptor;
    }

    public Marketplace getMarketPlace() {
        return this.marketPlace;
    }

    public static final class Builder {
        private RequestInterceptor mRequestInterceptor;
        private Marketplace marketPlace;

        public Builder withRequestInterceptor(RequestInterceptor requestInterceptor) {
            this.mRequestInterceptor = requestInterceptor;
            return this;
        }

        public Builder withMarketplace(Marketplace marketplace) {
            this.marketPlace = marketplace;
            return this;
        }

        public Configuration build() {
            Validate.notNull(this.mRequestInterceptor, "requestInterceptor cannot be null", new Object[0]);
            Validate.notNull(this.marketPlace, "marketplace cannot be null", new Object[0]);
            return new Configuration(this.mRequestInterceptor, this.marketPlace);
        }
    }
}
