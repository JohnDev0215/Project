package com.amplifyframework.api.rest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.util.Immutable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class RestOperationRequest {
    private final byte[] data;
    private final Map<String, String> headers;
    private final HttpMethod httpMethod;
    private final String path;
    private final Map<String, String> queryParameters;

    public RestOperationRequest(HttpMethod httpMethod, String str, byte[] bArr, Map<String, String> map, Map<String, String> map2) {
        this.httpMethod = httpMethod;
        this.path = str;
        this.headers = map == null ? Collections.emptyMap() : Immutable.of(map);
        this.data = bArr == null ? null : Arrays.copyOf(bArr, bArr.length);
        this.queryParameters = map2 == null ? Collections.emptyMap() : Immutable.of(map2);
    }

    public RestOperationRequest(HttpMethod httpMethod, String str, Map<String, String> map, Map<String, String> map2) {
        this(httpMethod, str, null, map, map2);
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
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

    @NonNull
    public String toString() {
        return "RestOperationRequest{httpMethod=" + this.httpMethod + ", path='" + this.path + "', data=" + Arrays.toString(this.data) + ", headers=" + this.headers + ", queryParameters=" + this.queryParameters + '}';
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RestOperationRequest.class != obj.getClass()) {
            return false;
        }
        RestOperationRequest restOperationRequest = (RestOperationRequest) obj;
        if (ObjectsCompat.equals(getHttpMethod(), restOperationRequest.getHttpMethod()) && ObjectsCompat.equals(getPath(), restOperationRequest.getPath()) && ObjectsCompat.equals(getData(), restOperationRequest.getData()) && ObjectsCompat.equals(getHeaders(), restOperationRequest.getHeaders())) {
            return ObjectsCompat.equals(getQueryParameters(), restOperationRequest.getQueryParameters());
        }
        return false;
    }

    public int hashCode() {
        return ((((((((getHttpMethod() != null ? getHttpMethod().hashCode() : 0) * 31) + (getPath() != null ? getPath().hashCode() : 0)) * 31) + Arrays.hashCode(getData())) * 31) + (getHeaders() != null ? getHeaders().hashCode() : 0)) * 31) + (getQueryParameters() != null ? getQueryParameters().hashCode() : 0);
    }
}
