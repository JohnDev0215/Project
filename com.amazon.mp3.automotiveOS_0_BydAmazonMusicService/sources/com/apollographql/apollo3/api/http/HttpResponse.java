package com.apollographql.apollo3.api.http;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: compiled from: Http.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001\u0018B3\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\r8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0019"}, d2 = {"Lcom/apollographql/apollo3/api/http/HttpResponse;", "", "", "statusCode", "I", "getStatusCode", "()I", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "headers", "Ljava/util/List;", "getHeaders", "()Ljava/util/List;", "Lokio/BufferedSource;", "bodySource", "Lokio/BufferedSource;", "Lokio/ByteString;", "bodyString", "Lokio/ByteString;", "getBody", "()Lokio/BufferedSource;", "body", "<init>", "(ILjava/util/List;Lokio/BufferedSource;Lokio/ByteString;)V", "Builder", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class HttpResponse {
    private final BufferedSource bodySource;
    private final ByteString bodyString;
    private final List<HttpHeader> headers;
    private final int statusCode;

    public /* synthetic */ HttpResponse(int i, List list, BufferedSource bufferedSource, ByteString byteString, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, list, bufferedSource, byteString);
    }

    private HttpResponse(int i, List<HttpHeader> list, BufferedSource bufferedSource, ByteString byteString) {
        this.statusCode = i;
        this.headers = list;
        this.bodySource = bufferedSource;
        this.bodyString = byteString;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final List<HttpHeader> getHeaders() {
        return this.headers;
    }

    public final BufferedSource getBody() {
        BufferedSource bufferedSource = this.bodySource;
        if (bufferedSource != null) {
            return bufferedSource;
        }
        ByteString byteString = this.bodyString;
        if (byteString == null) {
            return null;
        }
        return new Buffer().write(byteString);
    }

    /* JADX INFO: compiled from: Http.kt */
    @Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u001a\u0010\u001bJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u0014\u0010\b\u001a\u00020\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0006\u0010\n\u001a\u00020\tR\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u0010R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0015R\u0014\u0010\u0019\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcom/apollographql/apollo3/api/http/HttpResponse$Builder;", "", "Lokio/BufferedSource;", "bodySource", "body", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "headers", "addHeaders", "Lcom/apollographql/apollo3/api/http/HttpResponse;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "statusCode", "I", "getStatusCode", "()I", "Lokio/BufferedSource;", "Lokio/ByteString;", "bodyString", "Lokio/ByteString;", "", "Ljava/util/List;", "", "getHasBody", "()Z", "hasBody", "<init>", "(I)V", "apollo-api"}, k = 1, mv = {1, 5, 1})
    public static final class Builder {
        private BufferedSource bodySource;
        private ByteString bodyString;
        private final List<HttpHeader> headers = new ArrayList();
        private final int statusCode;

        public Builder(int i) {
            this.statusCode = i;
        }

        public final int getStatusCode() {
            return this.statusCode;
        }

        private final boolean getHasBody() {
            return (this.bodySource == null && this.bodyString == null) ? false : true;
        }

        public final Builder body(BufferedSource bodySource) {
            Intrinsics.checkNotNullParameter(bodySource, "bodySource");
            if (!(!getHasBody())) {
                throw new IllegalStateException("body() can only be called once".toString());
            }
            this.bodySource = bodySource;
            return this;
        }

        public final Builder addHeaders(List<HttpHeader> headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.headers.addAll(headers);
            return this;
        }

        public final HttpResponse build() {
            return new HttpResponse(this.statusCode, this.headers, this.bodySource, this.bodyString, null);
        }
    }
}
