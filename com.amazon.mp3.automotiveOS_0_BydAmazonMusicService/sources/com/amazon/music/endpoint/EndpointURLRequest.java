package com.amazon.music.endpoint;

import com.amazon.music.marketplace.Marketplace;

/* JADX INFO: loaded from: classes4.dex */
public class EndpointURLRequest {
    private final Environment environment;
    private final Marketplace marketplace;
    private final boolean voiceEnabled;

    private EndpointURLRequest(Builder builder) {
        this.marketplace = builder.marketplace;
        this.environment = builder.environment;
        this.voiceEnabled = builder.voiceEnabled;
    }

    public static class Builder {
        private Marketplace marketplace;
        private Environment environment = Environment.PROD;
        private boolean voiceEnabled = true;

        public Builder(Marketplace marketplace) {
            this.marketplace = marketplace;
        }

        public EndpointURLRequest build() {
            return new EndpointURLRequest(this);
        }
    }

    public Marketplace getMarketplace() {
        return this.marketplace;
    }
}
