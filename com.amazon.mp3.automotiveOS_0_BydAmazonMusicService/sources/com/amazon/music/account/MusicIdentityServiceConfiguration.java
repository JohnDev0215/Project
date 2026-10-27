package com.amazon.music.account;

import com.amazon.hermes.RequestInterceptor;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class MusicIdentityServiceConfiguration {
    private final List<RequestInterceptor> requestInterceptors;
    private final MusicIdentityServiceURLProvider urlProvider;

    private MusicIdentityServiceConfiguration(Builder builder) {
        this.urlProvider = builder.urlProvider;
        this.requestInterceptors = builder.requestInterceptors;
    }

    URL getMusicIdentityServiceURL() {
        return this.urlProvider.getMusicIdentityServiceURL();
    }

    List<RequestInterceptor> getRequestInterceptors() {
        return this.requestInterceptors;
    }

    public static final class Builder {
        private final List<RequestInterceptor> requestInterceptors = new ArrayList();
        private final MusicIdentityServiceURLProvider urlProvider;

        public Builder(MusicIdentityServiceURLProvider musicIdentityServiceURLProvider) {
            this.urlProvider = (MusicIdentityServiceURLProvider) Validate.notNull(musicIdentityServiceURLProvider, "MIS url provider cannot be null", new Object[0]);
        }

        public Builder withRequestInterceptor(RequestInterceptor requestInterceptor) {
            this.requestInterceptors.add((RequestInterceptor) Validate.notNull(requestInterceptor, "RequestInterceptor cannot be null", new Object[0]));
            return this;
        }

        public MusicIdentityServiceConfiguration build() {
            return new MusicIdentityServiceConfiguration(this);
        }
    }
}
