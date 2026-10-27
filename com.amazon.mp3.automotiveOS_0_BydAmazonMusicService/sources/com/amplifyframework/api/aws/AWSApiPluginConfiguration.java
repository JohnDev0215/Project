package com.amplifyframework.api.aws;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.util.Immutable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AWSApiPluginConfiguration {
    private final Map<String, ApiConfiguration> apiDetails;

    private AWSApiPluginConfiguration(@NonNull Map<String, ApiConfiguration> map) {
        HashMap map2 = new HashMap();
        this.apiDetails = map2;
        map2.putAll(map);
    }

    @Nullable
    ApiConfiguration getApi(@NonNull String str) {
        return this.apiDetails.get(str);
    }

    @NonNull
    Map<String, ApiConfiguration> getApis() {
        return Immutable.of(this.apiDetails);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AWSApiPluginConfiguration.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(this.apiDetails, ((AWSApiPluginConfiguration) obj).apiDetails);
    }

    public int hashCode() {
        return this.apiDetails.hashCode();
    }

    @NonNull
    @SuppressLint({"SyntheticAccessor"})
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Map<String, ApiConfiguration> apiDetails;

        private Builder() {
            this.apiDetails = new HashMap();
        }

        @NonNull
        Builder addApi(@NonNull String str, @NonNull ApiConfiguration apiConfiguration) {
            Objects.requireNonNull(str);
            Objects.requireNonNull(apiConfiguration);
            this.apiDetails.put(str, apiConfiguration);
            return this;
        }

        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        public AWSApiPluginConfiguration build() {
            return new AWSApiPluginConfiguration(this.apiDetails);
        }
    }
}
