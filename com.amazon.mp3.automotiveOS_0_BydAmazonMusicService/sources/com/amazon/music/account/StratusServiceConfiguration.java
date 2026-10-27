package com.amazon.music.account;

import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.marketplace.Marketplace;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class StratusServiceConfiguration {
    private final List<RequestInterceptor> requestInterceptors;
    private final StratusUrlProvider urlProvider;

    private StratusServiceConfiguration(Builder builder) {
        this.urlProvider = builder.urlProvider;
        this.requestInterceptors = builder.requestInterceptors;
    }

    URL getStratusUrl(Marketplace marketplace) {
        return this.urlProvider.getStratusUrl(marketplace);
    }

    URL getDefaultStratusUrl() {
        return this.urlProvider.getDefaultStratusUrl();
    }

    List<RequestInterceptor> getRequestInterceptors() {
        return this.requestInterceptors;
    }

    public static final class Builder {
        private final List<RequestInterceptor> requestInterceptors;
        private final StratusUrlProvider urlProvider;

        public Builder(StratusUrlProvider stratusUrlProvider, RequestInterceptor requestInterceptor) {
            this.urlProvider = (StratusUrlProvider) Validate.notNull(stratusUrlProvider, "Stratus url provider cannot be null", new Object[0]);
            ArrayList arrayList = new ArrayList();
            this.requestInterceptors = arrayList;
            arrayList.add((RequestInterceptor) Validate.notNull(requestInterceptor, "RequestInterceptor cannot be null", new Object[0]));
        }

        public Builder withRequestInterceptor(RequestInterceptor requestInterceptor) {
            this.requestInterceptors.add((RequestInterceptor) Validate.notNull(requestInterceptor, "RequestInterceptor cannot be null", new Object[0]));
            return this;
        }

        public StratusServiceConfiguration build() {
            return new StratusServiceConfiguration(this);
        }
    }
}
