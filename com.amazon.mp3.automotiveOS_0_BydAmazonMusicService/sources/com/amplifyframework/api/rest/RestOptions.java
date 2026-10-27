package com.amplifyframework.api.rest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.util.Immutable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class RestOptions {
    private final byte[] data;
    private final Map<String, String> headers;
    private final String path;
    private final Map<String, String> queryParameters;

    private RestOptions(String str, byte[] bArr, Map<String, String> map, Map<String, String> map2) {
        this.path = str;
        this.data = bArr == null ? null : Arrays.copyOf(bArr, bArr.length);
        this.headers = map == null ? Collections.emptyMap() : Immutable.of(map);
        this.queryParameters = map2 == null ? Collections.emptyMap() : Immutable.of(map2);
    }

    public String getPath() {
        return this.path;
    }

    public Map<String, String> getQueryParameters() {
        return this.queryParameters;
    }

    public byte[] getData() {
        return this.data;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public boolean hasData() {
        return this.data != null;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RestOptions.class != obj.getClass()) {
            return false;
        }
        RestOptions restOptions = (RestOptions) obj;
        if (ObjectsCompat.equals(getPath(), restOptions.getPath()) && ObjectsCompat.equals(getData(), restOptions.getData()) && ObjectsCompat.equals(getHeaders(), restOptions.getHeaders())) {
            return ObjectsCompat.equals(getQueryParameters(), restOptions.getQueryParameters());
        }
        return false;
    }

    public int hashCode() {
        return ((((((getPath() != null ? getPath().hashCode() : 0) * 31) + Arrays.hashCode(getData())) * 31) + (getHeaders() != null ? getHeaders().hashCode() : 0)) * 31) + (getQueryParameters() != null ? getQueryParameters().hashCode() : 0);
    }

    @NonNull
    public String toString() {
        return "RestOptions{path='" + this.path + "', data=" + Arrays.toString(this.data) + ", headers=" + this.headers + ", queryParameters=" + this.queryParameters + '}';
    }

    public static final class Builder {
        private byte[] data;
        private Map<String, String> headers;
        private String path;
        private Map<String, String> queryParameters;

        Builder() {
        }

        public Builder addPath(String str) {
            this.path = str;
            return this;
        }

        public Builder addBody(byte[] bArr) {
            this.data = bArr;
            return this;
        }

        public Builder addQueryParameters(Map<String, String> map) {
            if (this.queryParameters == null) {
                this.queryParameters = new HashMap();
            }
            this.queryParameters.putAll(map);
            return this;
        }

        public Builder addHeaders(Map<String, String> map) {
            if (this.headers == null) {
                this.headers = new HashMap();
            }
            this.headers.putAll(map);
            return this;
        }

        public Builder addHeader(String str, String str2) {
            if (this.headers == null) {
                this.headers = new HashMap();
            }
            this.headers.put(str, str2);
            return this;
        }

        public RestOptions build() {
            return new RestOptions(this.path, this.data, this.headers, this.queryParameters);
        }
    }
}
