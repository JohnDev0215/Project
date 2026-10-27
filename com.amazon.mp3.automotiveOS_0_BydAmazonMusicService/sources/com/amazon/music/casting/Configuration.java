package com.amazon.music.casting;

import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.casting.session.CastingSessionConfig;
import com.amazon.music.marketplace.Marketplace;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class Configuration {
    private final CastingSessionConfig castingSessionConfig;
    private boolean isDebugMode;
    private final Marketplace marketplace;
    private final RequestInterceptor requestInterceptor;

    private Configuration(RequestInterceptor requestInterceptor, Marketplace marketplace, CastingSessionConfig castingSessionConfig) {
        this.requestInterceptor = requestInterceptor;
        this.marketplace = marketplace;
        this.castingSessionConfig = castingSessionConfig;
    }

    public RequestInterceptor getRequestInterceptor() {
        return this.requestInterceptor;
    }

    public Marketplace getMarketplace() {
        return this.marketplace;
    }

    public CastingSessionConfig getCastingSessionConfig() {
        return this.castingSessionConfig;
    }

    public void setIsDebugMode(boolean z) {
        this.isDebugMode = z;
    }

    public static final class Builder {
        private CastingSessionConfig castingSessionConfig;
        private Marketplace marketplace;
        private RequestInterceptor requestInterceptor;

        public Builder withRequestInterceptor(RequestInterceptor requestInterceptor) {
            this.requestInterceptor = requestInterceptor;
            return this;
        }

        public Builder withMarketplace(Marketplace marketplace) {
            this.marketplace = marketplace;
            return this;
        }

        public Builder withCastingSessionConfig(CastingSessionConfig castingSessionConfig) {
            this.castingSessionConfig = castingSessionConfig;
            return this;
        }

        public Configuration build() {
            Validate.notNull(this.requestInterceptor, "requestInterceptor cannot be null", new Object[0]);
            Validate.notNull(this.marketplace, "marketplace cannot be null", new Object[0]);
            Validate.notNull(this.castingSessionConfig, "castingSessionConfig cannot be null", new Object[0]);
            return new Configuration(this.requestInterceptor, this.marketplace, this.castingSessionConfig);
        }
    }
}
