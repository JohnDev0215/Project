package com.amplifyframework.geo.options;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class GetMapStyleDescriptorOptions {
    private final String mapName;

    private GetMapStyleDescriptorOptions(Builder builder) {
        this.mapName = builder.mapName;
    }

    @Nullable
    public String getMapName() {
        return this.mapName;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public static GetMapStyleDescriptorOptions defaults() {
        return builder().build();
    }

    public static final class Builder {
        private String mapName;

        private Builder() {
        }

        @NonNull
        public Builder mapName(@NonNull String str) {
            Objects.requireNonNull(str);
            this.mapName = str;
            return this;
        }

        @NonNull
        public GetMapStyleDescriptorOptions build() {
            return new GetMapStyleDescriptorOptions(this);
        }
    }
}
