package com.amazon.avod.ads.api.livetracking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Builder.class)
public class BeaconUrls {
    private String mUrl;

    @Nonnull
    public String getUrl() {
        return this.mUrl;
    }

    private BeaconUrls(Builder builder) {
        this.mUrl = builder.mUrl;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private String mUrl;

        public Builder url(@Nonnull String str) {
            this.mUrl = (String) Preconditions.checkNotNull(str, ImagesContract.URL);
            return this;
        }

        public BeaconUrls build() {
            return new BeaconUrls(this);
        }
    }
}
