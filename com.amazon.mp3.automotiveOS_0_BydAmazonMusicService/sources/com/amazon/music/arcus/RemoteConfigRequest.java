package com.amazon.music.arcus;

import com.amazon.music.marketplace.Marketplace;

/* JADX INFO: loaded from: classes4.dex */
public class RemoteConfigRequest {
    private final boolean isTestEnvironment;
    private final Marketplace marketplace;
    private final String territory;

    private RemoteConfigRequest(Builder builder) {
        this.marketplace = builder.marketplace;
        this.territory = builder.territory;
        this.isTestEnvironment = builder.isTestEnvironment;
    }

    public Marketplace getMarketplace() {
        return this.marketplace;
    }

    public String getTerritory() {
        return this.territory;
    }

    public boolean isTestEnvironment() {
        return this.isTestEnvironment;
    }

    public static class Builder {
        private Marketplace marketplace = null;
        private String territory = null;
        private boolean isTestEnvironment = false;

        public Builder withTestEnvironment() {
            this.isTestEnvironment = true;
            return this;
        }

        public RemoteConfigRequest build() {
            return new RemoteConfigRequest(this);
        }
    }
}
