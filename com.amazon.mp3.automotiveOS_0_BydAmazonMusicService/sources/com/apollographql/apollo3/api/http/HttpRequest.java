package com.apollographql.apollo3.api.http;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Http.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u0019B1\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lcom/apollographql/apollo3/api/http/HttpRequest;", "", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "method", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "getMethod", "()Lcom/apollographql/apollo3/api/http/HttpMethod;", "", ImagesContract.URL, "Ljava/lang/String;", "getUrl", "()Ljava/lang/String;", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "headers", "Ljava/util/List;", "getHeaders", "()Ljava/util/List;", "Lcom/apollographql/apollo3/api/http/HttpBody;", "body", "Lcom/apollographql/apollo3/api/http/HttpBody;", "getBody", "()Lcom/apollographql/apollo3/api/http/HttpBody;", "<init>", "(Lcom/apollographql/apollo3/api/http/HttpMethod;Ljava/lang/String;Ljava/util/List;Lcom/apollographql/apollo3/api/http/HttpBody;)V", "Builder", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class HttpRequest {
    private final HttpBody body;
    private final List<HttpHeader> headers;
    private final HttpMethod method;
    private final String url;

    public /* synthetic */ HttpRequest(HttpMethod httpMethod, String str, List list, HttpBody httpBody, DefaultConstructorMarker defaultConstructorMarker) {
        this(httpMethod, str, list, httpBody);
    }

    private HttpRequest(HttpMethod httpMethod, String str, List<HttpHeader> list, HttpBody httpBody) {
        this.method = httpMethod;
        this.url = str;
        this.headers = list;
        this.body = httpBody;
    }

    public final HttpMethod getMethod() {
        return this.method;
    }

    public final String getUrl() {
        return this.url;
    }

    public final List<HttpHeader> getHeaders() {
        return this.headers;
    }

    public final HttpBody getBody() {
        return this.body;
    }

    /* JADX INFO: compiled from: Http.kt */
    @Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0013\u0010\u0014J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u0014\u0010\u0007\u001a\u00020\u00002\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u0006\u0010\t\u001a\u00020\bR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u0010R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/apollographql/apollo3/api/http/HttpRequest$Builder;", "", "Lcom/apollographql/apollo3/api/http/HttpBody;", "body", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "headers", "addHeaders", "Lcom/apollographql/apollo3/api/http/HttpRequest;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/apollographql/apollo3/api/http/HttpMethod;", "method", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "", ImagesContract.URL, "Ljava/lang/String;", "Lcom/apollographql/apollo3/api/http/HttpBody;", "", "Ljava/util/List;", "<init>", "(Lcom/apollographql/apollo3/api/http/HttpMethod;Ljava/lang/String;)V", "apollo-api"}, k = 1, mv = {1, 5, 1})
    public static final class Builder {
        private HttpBody body;
        private final List<HttpHeader> headers;
        private final HttpMethod method;
        private final String url;

        public Builder(HttpMethod method, String url) {
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(url, "url");
            this.method = method;
            this.url = url;
            this.headers = new ArrayList();
        }

        public final Builder body(HttpBody body) {
            Intrinsics.checkNotNullParameter(body, "body");
            this.body = body;
            return this;
        }

        public final Builder addHeaders(List<HttpHeader> headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.headers.addAll(headers);
            return this;
        }

        public final HttpRequest build() {
            return new HttpRequest(this.method, this.url, this.headers, this.body, null);
        }
    }
}
