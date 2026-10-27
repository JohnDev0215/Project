package com.amazon.avod.ads.api.livetracking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Builder.class)
public class LiveAdTracking {
    private final List<Avails> mAvails;

    private LiveAdTracking(Builder builder) {
        this.mAvails = builder.mAvails;
    }

    @Nullable
    public List<Avails> getAvails() {
        return this.mAvails;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private List<Avails> mAvails;

        public Builder avails(@Nullable List<Avails> list) {
            this.mAvails = list;
            return this;
        }

        public LiveAdTracking build() {
            return new LiveAdTracking(this);
        }
    }
}
